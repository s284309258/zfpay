package com.ruoyi.project.develop.task.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspos.mapper.AgentSysMposInfoMapper;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.test.IOSPushy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitParamConstants;
import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.constant.StatusTypeConstant;
import com.ruoyi.common.constant.SysParamCodeConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.ZhongFuInterfaceCodeConstant;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.TransactionDataProcessingMapper;
import com.ruoyi.project.develop.task.service.TransactionDataProcessingService;
import com.ruoyi.project.devemana.param.service.ManaSysParamService;
import com.ruoyi.project.devemana.user.mapper.ManaUserInfoMapper;

/**
 * 中付数据处理-业务层
 * @author i
 *
 */
@Service
public class TransactionDataProcessingServiceImpl implements TransactionDataProcessingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDataProcessingServiceImpl.class); 
	
	@Autowired
	private TransactionDataProcessingMapper transactionDataProcessingMapper;
	
	@Autowired
	private ManaSysParamService manaSysParamService;
	
	@Autowired
	private ManaUserInfoMapper manaUserInfoMapper;

	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;

	@Autowired
	private AgentSysMposInfoMapper agentSysMposInfoMapper;

	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;

	@Override
	public void processingTrades() {
		LOGGER.info("------------------处理交易数据分润-START-------------");
		try{
			//查询待处理的传统POS机交易
			List<Map<String, Object>> traposTradeList = transactionDataProcessingMapper.getTraposTradeList();
			if(traposTradeList!=null && traposTradeList.size()>0){
				for(Map<String, Object> traposTrade : traposTradeList){
					SpringUtils.getAopProxy(this).processingTransposTrade(traposTrade);
				}
			}
			
			//查询待处理的MPOS机交易
			List<Map<String, Object>> mposTradeList = transactionDataProcessingMapper.getMposTradeList();
			if(mposTradeList!=null && mposTradeList.size()>0){
				for(Map<String, Object> mposTrade : mposTradeList){
					SpringUtils.getAopProxy(this).processingMposTrade(mposTrade);
				}
			}
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingTrades方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------处理交易数据分润-END-------------");
	}
	
	/**
	 * 处理单笔传统POS交易
	 * @param traposTrade
	 */
	@Transactional
	public void processingTransposTrade(Map<String, Object> traposTrade){
		int num = 0;
		Map<String, Object> param = null;
		try{
			/************************************修改处理状态，开始处理交易********************************************/
			//修改待处理状态
			param = new HashMap<String, Object>();
			param.put("old_status", StatusTypeConstant.trapos_transaction_record_deal_status_00);
			param.put("new_status", StatusTypeConstant.trapos_transaction_record_deal_status_02);
			param.put("tran_ref_code", StringUtil.getMapValue(traposTrade, "tran_ref_code"));
			num = transactionDataProcessingMapper.updateTraposTransactionRecordDealStatus(param);
			if(num != 1){
				return;
			}
			try{
				num = transactionDataProcessingMapper.backupTraposTransactionRecord(traposTrade);
				if(num != 1){
					throw new Exception("交易数据移入正式表异常");
				}
			}catch(Exception e){
				LOGGER.error("移入正式表异常:" + traposTrade.toString() + ExceptionUtil.getExceptionAllinformation(e));
				param.put("old_status", StatusTypeConstant.trapos_transaction_record_deal_status_02);
				param.put("new_status", StatusTypeConstant.trapos_transaction_record_deal_status_08);
				param.put("tran_ref_code", StringUtil.getMapValue(traposTrade, "tran_ref_code"));
				num = transactionDataProcessingMapper.updateTraposTransactionRecordDealStatus(param);
				if(num != 1){
					throw new Exception("更新失败状态异常");
				}
				return;
			}
			/************************************查询分润相关信息记录********************************************/
			//查询系统参数
			Map<String, Object> trapos = transactionDataProcessingMapper.getTraposDetailInfo(StringUtil.getMapValue(traposTrade, "sn"));
			if(trapos == null){
				throw new Exception("未查询到POS机参数信息:" + StringUtil.getMapValue(traposTrade, "sn"));
			}
			//查询直属会员
			Map<String, Object> merchant = transactionDataProcessingMapper.getTraposMerchantInfo(StringUtil.getMapValue(traposTrade, "sn"));
			if(merchant == null){
				throw new Exception("未查询到POS机直属会员:" + StringUtil.getMapValue(traposTrade, "sn"));
			}
			//查询分润列表
			List<Map<String, Object>> agencyList = transactionDataProcessingMapper.getTraposAgencyList(merchant);
			
			/************************************开始处理结算底价收益********************************************/
			this.processingTransposSettlePrice(traposTrade, trapos, merchant, agencyList);
			
			/************************************开始处理单笔收益********************************************/
			this.processingTransposSingleBenefit(traposTrade, trapos, merchant, agencyList);
			
			/************************************开始处理交易汇总********************************************/
			this.processingTransposTradeSummary(traposTrade, trapos, merchant, agencyList);
			
			/************************************将已处理结束交易移到正式表**********************************/
			this.backupTraposTransactionRecord(traposTrade);
			
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingTransposTrade方法处理异常:" + traposTrade.toString() + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/***
	 *处理直属和代理结算分润
	 * @param traposTrade
	 * @param trapos
	 * @param merchant
	 * @param agencyList
	 * @throws Exception
	 */
	public void processingTransposSettlePrice(Map<String, Object> traposTrade, Map<String, Object> trapos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//费率
		BigDecimal rate = null;
		//获取结算价
		String settle_price = null;
		//封顶费
		BigDecimal mer_cap_fee = null;
		//云闪付交易额临界值条件（超过当前数量将使用刷卡费率）
		String cloudFlushCriticalValue = manaSysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cloudFlushCriticalValue);
		//交易金额
		BigDecimal transAmount = new BigDecimal(StringUtil.getMapValue(traposTrade, "trans_amount"));
		//获取分润金额

		if(ZhongFuInterfaceCodeConstant.card_type_new_2.equals(StringUtil.getMapValue(traposTrade, "card_type"))
				&& ZhongFuInterfaceCodeConstant.transProduct_2.equals(StringUtil.getMapValue(traposTrade,"trans_product"))){
			//贷记卡vip秒到(商圈)的交易结算低价add byqh 201912
			if(ZhongFuInterfaceCodeConstant.trans_type_3.equals(StringUtil.getMapValue(traposTrade, "trans_type"))){
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "weixin_rate"));
				settle_price = "weixin_settle_price";
			}else if(ZhongFuInterfaceCodeConstant.trans_type_4.equals(StringUtil.getMapValue(traposTrade, "trans_type"))){
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "zhifubao_rate"));
				settle_price = "zhifubao_settle_price";
			}else{
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate_vip"));
				settle_price = "card_settle_price_vip";
			}
		}else{
			//其他付款秒到(非商圈),星券秒到update byqh 201912
			if(ZhongFuInterfaceCodeConstant.trans_type_1.equals(StringUtil.getMapValue(traposTrade, "trans_type"))
					&&  ZhongFuInterfaceCodeConstant.card_type_new_4.equals(StringUtil.getMapValue(traposTrade, "card_type"))){
				if(transAmount.compareTo(new BigDecimal(cloudFlushCriticalValue))>0){
					rate = new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate"));
					settle_price = "card_settle_price";
				}else{
					rate = new BigDecimal(StringUtil.getMapValue(trapos, "cloud_flash_rate"));
					settle_price = "cloud_settle_price";
				}
			}else if(ZhongFuInterfaceCodeConstant.trans_type_1.equals(StringUtil.getMapValue(traposTrade, "trans_type"))
					&&  ZhongFuInterfaceCodeConstant.card_type_new_1.equals(StringUtil.getMapValue(traposTrade, "card_type"))){
				mer_cap_fee = new BigDecimal(StringUtil.getMapValue(traposTrade, "mer_cap_fee"));
				LOGGER.info(mer_cap_fee.toString());
				LOGGER.info(transAmount.multiply(new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate"))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN).toString());
				if(mer_cap_fee.compareTo(transAmount.multiply(new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate"))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN))<0){
					settle_price = "mer_cap_fee";
				}else{
					rate = new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate"));
					settle_price = "card_settle_price";
				}
				LOGGER.info(settle_price);
			}else if(ZhongFuInterfaceCodeConstant.trans_type_3.equals(StringUtil.getMapValue(traposTrade, "trans_type"))){
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "weixin_rate"));
				settle_price = "weixin_settle_price";
			}else if(ZhongFuInterfaceCodeConstant.trans_type_4.equals(StringUtil.getMapValue(traposTrade, "trans_type"))){
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "zhifubao_rate"));
				settle_price = "zhifubao_settle_price";
			}else{
				rate = new BigDecimal(StringUtil.getMapValue(trapos, "credit_card_rate"));
				settle_price = "card_settle_price";
			}
		}

		//下级结算价
		BigDecimal agency_settle = rate;
		//下级封顶费
		BigDecimal agency_mer_cap_fee = mer_cap_fee;
		//处理直属会员收益
		if("mer_cap_fee".equals(settle_price)){
			if(agency_mer_cap_fee.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)))>0){
				BigDecimal benefit_money = agency_mer_cap_fee.subtract(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)));
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_1, merchant, traposTrade, trapos);
				}
				agency_mer_cap_fee = new BigDecimal(StringUtil.getMapValue(merchant, settle_price));
			}
		}else{
			if(agency_settle.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)))>0){
				BigDecimal benefit_money = transAmount.multiply(agency_settle.subtract(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_1, merchant, traposTrade, trapos);
				}
				agency_settle = new BigDecimal(StringUtil.getMapValue(merchant, settle_price));
			}
		}
		
		//处理代理收益
		if(agencyList != null && agencyList.size() > 0){
			for(Map<String, Object> agency : agencyList){
				if("mer_cap_fee".equals(settle_price)){
					if(agency_mer_cap_fee.compareTo(new BigDecimal(StringUtil.getMapValue(agency, settle_price)))>0){
						BigDecimal benefit_money = agency_mer_cap_fee.subtract(new BigDecimal(StringUtil.getMapValue(agency, settle_price)));
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_0, agency, traposTrade, trapos);
						}
						agency_mer_cap_fee = new BigDecimal(StringUtil.getMapValue(agency, settle_price));
					}
				}else{
					if(agency_settle.compareTo(new BigDecimal(StringUtil.getMapValue(agency, settle_price)))>0){
						BigDecimal benefit_money = transAmount.multiply(agency_settle.subtract(new BigDecimal(StringUtil.getMapValue(agency, settle_price)))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_0, agency, traposTrade, trapos);
						}
						agency_settle = new BigDecimal(StringUtil.getMapValue(agency, settle_price));
					}
				}
			}
		}
		
	}
	
	/**
	 * 处理直属和代理单笔分润
	 * @param transposTrade
	 * @param transpos
	 * @param merchant
	 * @param agencyList
	 */
	public void processingTransposSingleBenefit(Map<String, Object> traposTrade, Map<String, Object> trapos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//判断是否有单笔费用
		if("".equals(StringUtil.getMapValue(traposTrade, "mer_withdraw_fee"))) return;
		//单笔分润比例
		BigDecimal agency_single_profit_rate = new BigDecimal(0);
		//单笔费用
		BigDecimal merWithdrawFee = new BigDecimal(StringUtil.getMapValue(traposTrade, "mer_withdraw_fee"));
				
		
		if(merWithdrawFee.compareTo(new BigDecimal(0))>0){
			//处理直属会员收益
			if(agency_single_profit_rate.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate")))<0){
				BigDecimal benefit_money = merWithdrawFee.multiply(new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate")).subtract(agency_single_profit_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_02, BenefitParamConstants.state_type_1, merchant, traposTrade, trapos);
				}
				agency_single_profit_rate = new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate"));
			}
			
			
			//处理代理收益
			if(agencyList != null && agencyList.size() > 0){
				for(Map<String, Object> agency : agencyList){
					if(agency_single_profit_rate.compareTo(new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate")))<0){
						BigDecimal benefit_money = merWithdrawFee.multiply(new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate")).subtract(agency_single_profit_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.transposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_02, BenefitParamConstants.state_type_0, agency, traposTrade, trapos);
						}
						agency_single_profit_rate = new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate"));
					}
				}
			}
		}
	}
	
	/**
	 * 处理单个会员收益及记录
	 * @param benefit_money
	 * @param op_type
	 * @param state_type
	 * @param benefit
	 * @param trade
	 * @param transpos
	 */
	public void transposShareBenefit(BigDecimal benefit_money, String op_type, String state_type, Map<String, Object> benefit, Map<String, Object> trade, Map<String, Object> trapos)throws Exception{
		int num = 0;
		//更新用户收益
		String order_id = StringUtil.getDateTimeAndRandomForID();
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		edit_user.put("money", benefit_money);
		edit_user.put("today_benefit", benefit_money);
		edit_user.put("total_benefit", benefit_money);
		edit_user.put("op_type", op_type);
		edit_user.put("op_order_id", order_id);
		edit_user.put("state_type", state_type);
		edit_user.put("sn", StringUtil.getMapValue(trade, "sn"));
		String sn = StringUtil.getMapValue(trade,"sn");
		System.out.println("需要查询的sn: " + sn);
		Map<String,Object> map = new HashMap<>();
		if(sn!=null && !sn.equals("")){
			map = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoBySn(sn);
		}else {
			return;
		}
		String mer_name = StringUtil.getMapValue(map,"mer_name");
		if(!mer_name.isEmpty()&& mer_name!=null){
			System.out.println("第一次查询到的商户名:" + mer_name);
		}else {
			mer_name = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosMer_nameInfoBySn(sn);
			System.out.println("第二次查询到的商户名:" + mer_name);
		}
		System.out.println("写入的商户名: " + mer_name);
		String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(trade, "sn"));
		if("epos".equals(pos_type)){
			edit_user.put("pos_type", BenefitParamConstants.pos_type_03);
		}else{
			edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
		}
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
		if(num != 1){
			throw new Exception("用户收益更新异常");
		}
		
		//记录结算分润
		Map<String, Object> record = new HashMap<>();
		record.put("order_id", order_id);
		record.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		record.put("sn", StringUtil.getMapValue(trade, "sn"));
		record.put("trans_amount", StringUtil.getMapValue(trade, "trans_amount"));
		record.put("trans_type", StringUtil.getMapValue(trade, "trans_type"));
		//add byqh 201912
		record.put("trans_product",StringUtil.getMapValue(trade, "trans_product"));

		record.put("card_type", StringUtil.getMapValue(trade, "card_type"));
		record.put("trans_time", StringUtil.getMapValue(trade, "trans_time"));
		record.put("benefit_type", op_type);
		record.put("state_type", state_type);
		record.put("single_amount", StringUtil.getMapValue(trade, "mer_withdraw_fee"));
		record.put("trade_mer_cap_fee", StringUtil.getMapValue(trade, "mer_cap_fee"));
		record.put("benefit_money", benefit_money);
		record.put("tran_ref_code", StringUtil.getMapValue(trade, "tran_ref_code"));
		record.put("card_settle_price", StringUtil.getMapValue(benefit, "card_settle_price"));
		record.put("weixin_settle_price", StringUtil.getMapValue(benefit, "weixin_settle_price"));
		record.put("zhifubao_settle_price", StringUtil.getMapValue(benefit, "zhifubao_settle_price"));
		record.put("cloud_settle_price", StringUtil.getMapValue(benefit, "cloud_settle_price"));
		record.put("single_profit_rate", StringUtil.getMapValue(benefit, "single_profit_rate"));
		record.put("mer_cap_fee", StringUtil.getMapValue(benefit, "mer_cap_fee"));
		record.put("credit_card_rate", StringUtil.getMapValue(trapos, "credit_card_rate"));
		record.put("cloud_flash_rate", StringUtil.getMapValue(trapos, "cloud_flash_rate"));
		record.put("weixin_rate", StringUtil.getMapValue(trapos, "weixin_rate"));
		record.put("zhifubao_rate", StringUtil.getMapValue(trapos, "zhifubao_rate"));
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		if("epos".equals(pos_type)){
			record.put("pos_type",BenefitParamConstants.pos_type_03);
		}
//		if(null!=mer_name && !"".equals(mer_name)){
//			record.put("mer_name",mer_name);
//		}
//		else {
//			record.put("mer_name", StringUtil.getMapValue(map,"sn"));
//		}
		if(!mer_name.isEmpty() && mer_name!=null){
			record.put("mer_name",mer_name);
		}else {
			record.put("mer_name",StringUtil.getMapValue(trapos,"mer_name"));
			System.out.println(mer_name);
		}
		//写入正式表
		num = transactionDataProcessingMapper.insertUserTraposShareBenefitRecord(record);
		//
		if(num != 1){
			throw new Exception("收益日志记录异常");
		}
		//记录通知
		Map<String, Object> message = new HashMap<>();
		message.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		message.put("order_id", order_id);
		message.put("op_type", op_type);
		message.put("money", benefit_money);
		message.put("sn", StringUtil.getMapValue(trade, "sn"));
		if("epos".equals(pos_type)){
			message.put("pos_type", BenefitParamConstants.pos_type_03);
		}else{
			message.put("pos_type", BenefitParamConstants.pos_type_01);
		}
		message.put("cre_date", TimeUtil.getDayString());
		message.put("cre_time", TimeUtil.getTimeString());
		num = transactionDataProcessingMapper.insertUserMessageInfo(message);
		if(num != 1){
			throw new Exception("通知记录异常");
		}
		try{
			//分润推送通知add byqh202003
			Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(benefit, "user_id"));
			if(StringUtil.getMapValue(userMap, "device_type").contains("iOS") &&
					!"".equals(StringUtil.getMapValue(userMap, "device_token"))){
				StringBuffer notice = new StringBuffer();
				notice.append("【中付钱柜】您收到一笔[");
				String tt = StringUtil.getMapValue(trade, "trans_type");
				if("1".equals(tt)){
					notice.append("刷卡");
				}else if("2".equals(tt)){
					notice.append("快捷支付");
				}else if("3".equals(tt)){
					notice.append("微信");
				}else if("4".equals(tt)){
					notice.append("支付宝");
				}else{
					notice.append("云闪付");
				}
				notice.append("]收益").append(benefit_money).append("元");
				IOSPushy.PushyMessage("",notice.toString(),StringUtil.getMapValue(userMap, "device_token"));
			}
		}catch(Exception e){
			throw new Exception("推送消息失败:" + e.getMessage());
		}

	}

	/***
	 * 交易额数据汇总
	 * @param traposTrade
	 * @param trapos
	 * @param merchant
	 * @param agencyList
	 * @throws Exception
	 */
	public void processingTransposTradeSummary(Map<String, Object> traposTrade, Map<String, Object> trapos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		int num = 0;
		int trade_num = 0;
		//判断是否有交易
		if(!BenefitParamConstants.trade_status_1.equals(StringUtil.getMapValue(merchant, "trade_status"))){
			trade_num = 1;
			merchant.put("up_date", TimeUtil.getDayString());
			merchant.put("up_time", TimeUtil.getTimeString());
			num = transactionDataProcessingMapper.updateTraposTradeStatus(merchant);
			if(num != 1){
				throw new Exception("交易状态更新异常");
			}
		}
		
		//判断是否有商户名称
		if("".equals(StringUtil.getMapValue(trapos, "mer_name"))){
			trapos.put("mer_name", StringUtil.getMapValue(traposTrade, "mer_name").trim());
			trapos.put("mer_id", StringUtil.getMapValue(traposTrade, "mer_id").trim());
			transactionDataProcessingMapper.updateTraposMerName(trapos);
		}


		
		//汇总参数
		Map<String, Object> param = new HashMap<>();
		param.put("performance", StringUtil.getMapValue(traposTrade, "trans_amount"));
		param.put("sn", StringUtil.getMapValue(traposTrade, "sn"));
		param.put("trade_num", trade_num);
		param.put("up_date", TimeUtil.getDayString());
		param.put("up_time", TimeUtil.getTimeString());
		//汇总直属商户交易额
		param.put("user_id", StringUtil.getMapValue(merchant, "user_id"));

		//汇总直属会员交易额
		String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(traposTrade, "sn"));
		if("epos".equals(pos_type)){
			num = transactionDataProcessingMapper.updateSummaryEposMerchantPerformance(param);
			if(num != 1){
				throw new Exception("商户交易额汇总异常");
			}
			num = transactionDataProcessingMapper.updateSummaryEposUserMerchantPerformance(param);
			if(num != 1){
				throw new Exception("用户交易额汇总异常");
			}
		}else{
			num = transactionDataProcessingMapper.updateSummaryTraditionalPosMerchantPerformance(param);
			if(num != 1){
				throw new Exception("商户交易额汇总异常");
			}
			num = transactionDataProcessingMapper.updateSummaryTraposUserMerchantPerformance(param);
			if(num != 1){
				throw new Exception("用户交易额汇总异常");
			}
		}
		//处理代理
		if(agencyList != null && agencyList.size() > 0){
			for(Map<String, Object> agency : agencyList){
				//汇总代理商户交易额
				param.put("user_id", StringUtil.getMapValue(agency, "user_id"));
				//汇总代理会员交易额
				if("epos".equals(pos_type)){
					num = transactionDataProcessingMapper.updateSummaryEposAgencyPerformance(param);
					if(num != 1){
						throw new Exception("代理商户交易额汇总异常");
					}
					num = transactionDataProcessingMapper.updateSummaryEposUserAgencyPerformance(param);
					if(num != 1){
						throw new Exception("代理用户交易额汇总异常");
					}
				}else{
					num = transactionDataProcessingMapper.updateSummaryTraditionalPosAgencyPerformance(param);
					if(num != 1){
						throw new Exception("代理商户交易额汇总异常");
					}
					num = transactionDataProcessingMapper.updateSummaryTraposUserAgencyPerformance(param);
					if(num != 1){
						throw new Exception("代理用户交易额汇总异常");
					}
				}
			}
		}
		
	}
	
	/**
	 * 将待处理表数据移入正式表
	 * @param traposTrade
	 */
	public void backupTraposTransactionRecord(Map<String, Object> traposTrade)throws Exception{
		int num = 0;
//		//移入正式表
//		num = transactionDataProcessingMapper.backupTraposTransactionRecord(traposTrade);
//		if(num != 1){
//			throw new Exception("交易数据移入正式表异常");
//		}
		
		//删除待处理表
		num = transactionDataProcessingMapper.deleteTraposTransactionRecordDeal(traposTrade);
		if(num != 1){
			throw new Exception("待处理交易数据删除异常");
		}
	}
	
	/**
	 * 处理单笔MPOS交易
	 * @param mposTrade
	 */
	@Transactional
	public void processingMposTrade(Map<String, Object> mposTrade){
		int num = 0;
		Map<String, Object> param = null;
		try{
			/************************************修改处理状态，开始处理交易********************************************/
			//修改待处理状态
			param = new HashMap<String, Object>();
			param.put("old_status", StatusTypeConstant.mpos_transaction_record_deal_status_00);
			param.put("new_status", StatusTypeConstant.mpos_transaction_record_deal_status_02);
			param.put("tran_ref_code", StringUtil.getMapValue(mposTrade, "tran_ref_code"));
			num = transactionDataProcessingMapper.updateMposTransactionRecordDealStatus(param);
			if(num != 1){
				return;
			}
			
			//保存到正式表
			try{
				//移入正式表
				num = transactionDataProcessingMapper.backupMposTransactionRecord(mposTrade);
				if(num != 1){
					throw new Exception("交易数据移入正式表异常");
				}
			}catch(Exception e){
				LOGGER.error("移入正式表异常:" + mposTrade.toString() + ExceptionUtil.getExceptionAllinformation(e));
				param.put("old_status", StatusTypeConstant.mpos_transaction_record_deal_status_02);
				param.put("new_status", StatusTypeConstant.mpos_transaction_record_deal_status_08);
				param.put("tran_ref_code", StringUtil.getMapValue(mposTrade, "tran_ref_code"));
				num = transactionDataProcessingMapper.updateMposTransactionRecordDealStatus(param);
				if(num != 1){
					throw new Exception("更新失败状态异常");
				}
				return;
			}
			
			/************************************查询分润相关信息记录********************************************/
			//查询系统参数
			Map<String, Object> mpos = transactionDataProcessingMapper.getMposDetailInfo(StringUtil.getMapValue(mposTrade, "sn"));
			if(mpos == null){
				throw new Exception("未查询到POS机参数信息:" + StringUtil.getMapValue(mposTrade, "sn"));
			}
			//查询直属会员
			Map<String, Object> merchant = transactionDataProcessingMapper.getMposMerchantInfo(StringUtil.getMapValue(mposTrade, "sn"));
			if(merchant == null){
				throw new Exception("未查询到POS机直属会员:" + StringUtil.getMapValue(mposTrade, "sn"));
			}
			//查询分润列表
			List<Map<String, Object>> agencyList = transactionDataProcessingMapper.getMposAgencyList(merchant);
			
			/************************************开始处理结算底价收益********************************************/
			this.processingMposSettlePrice(mposTrade, mpos, merchant, agencyList);
			
			/************************************开始处理单笔收益********************************************/
			this.processingMposSingleBenefit(mposTrade, mpos, merchant, agencyList);
			
			/************************************开始处理交易汇总********************************************/
			this.processingMposTradeSummary(mposTrade, mpos, merchant, agencyList);
			
			/************************************将已处理结束交易移到正式表**********************************/
			this.backupMposTransactionRecord(mposTrade);
			
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingMposTrade方法处理异常:" + mposTrade.toString() + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * 处理直属和代理结算分润
	 * @param mposTrade
	 * @param mpos
	 * @param merchant
	 * @param agencyList
	 * @throws Exception
	 */
	public void processingMposSettlePrice(Map<String, Object> mposTrade, Map<String, Object> mpos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//费率
		BigDecimal rate = null;
		//获取结算价
		String settle_price = null;
		//云闪付交易额临界值条件（超过当前数量将使用刷卡费率）
		String cloudFlushCriticalValue = manaSysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cloudFlushCriticalValue);
		//交易金额
		BigDecimal transAmount = new BigDecimal(StringUtil.getMapValue(mposTrade, "trans_amount"));
		//获取分润金额
		if(ZhongFuInterfaceCodeConstant.trans_type_1.equals(StringUtil.getMapValue(mposTrade, "trans_type")) 
				&&  ZhongFuInterfaceCodeConstant.card_type_new_4.equals(StringUtil.getMapValue(mposTrade, "card_type"))){
			if(transAmount.compareTo(new BigDecimal(cloudFlushCriticalValue))>0){
				rate = new BigDecimal(StringUtil.getMapValue(mpos, "credit_card_rate"));
				settle_price = "card_settle_price";
			}else{
				rate = new BigDecimal(StringUtil.getMapValue(mpos, "cloud_flash_rate"));
				settle_price = "cloud_settle_price";
			}
		}else{
			rate = new BigDecimal(StringUtil.getMapValue(mpos, "credit_card_rate"));
			settle_price = "card_settle_price";
		}
		
		//下级结算价
		BigDecimal agency_settle = rate;
		//处理直属会员收益
		if(agency_settle.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)))>0){
			BigDecimal benefit_money = transAmount.multiply(agency_settle.subtract(new BigDecimal(StringUtil.getMapValue(merchant, settle_price)))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
			if(benefit_money.compareTo(new BigDecimal(0))>0){
				this.mposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_1, merchant, mposTrade, mpos);
			}
			agency_settle = new BigDecimal(StringUtil.getMapValue(merchant, settle_price));
		}
		
		//处理代理收益
		if(agencyList != null && agencyList.size() > 0){
			for(Map<String, Object> agency : agencyList){
				if(agency_settle.compareTo(new BigDecimal(StringUtil.getMapValue(agency, settle_price)))>0){
					BigDecimal benefit_money = transAmount.multiply(agency_settle.subtract(new BigDecimal(StringUtil.getMapValue(agency, settle_price)))).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
					if(benefit_money.compareTo(new BigDecimal(0))>0){
						this.mposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_01, BenefitParamConstants.state_type_0, agency, mposTrade, mpos);
					}
					agency_settle = new BigDecimal(StringUtil.getMapValue(agency, settle_price));
				}
			}
		}
		
	}
	
	/**
	 * 处理直属和代理单笔分润
	 * @param mposTrade
	 * @param mpos
	 * @param merchant
	 * @param agencyList
	 */
	public void processingMposSingleBenefit(Map<String, Object> mposTrade, Map<String, Object> mpos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//判断是否有单笔费用
		if("".equals(StringUtil.getMapValue(mposTrade, "mer_withdraw_fee"))) return;
		//单笔分润比例
		BigDecimal agency_single_profit_rate = new BigDecimal(0);
		//单笔费用
		BigDecimal merWithdrawFee = new BigDecimal(StringUtil.getMapValue(mposTrade, "mer_withdraw_fee"));
				
		
		if(merWithdrawFee.compareTo(new BigDecimal(0))>0){
			//处理直属会员收益
			if(agency_single_profit_rate.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate")))<0){
				BigDecimal benefit_money = merWithdrawFee.multiply(new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate")).subtract(agency_single_profit_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.mposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_02, BenefitParamConstants.state_type_1, merchant, mposTrade, mpos);
				}
				agency_single_profit_rate = new BigDecimal(StringUtil.getMapValue(merchant, "single_profit_rate"));
			}
			
			
			//处理代理收益
			if(agencyList != null && agencyList.size() > 0){
				for(Map<String, Object> agency : agencyList){
					if(agency_single_profit_rate.compareTo(new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate")))<0){
						BigDecimal benefit_money = merWithdrawFee.multiply(new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate")).subtract(agency_single_profit_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.mposShareBenefit(benefit_money, BenefitRecordTypeContants.benefit_record_type_02, BenefitParamConstants.state_type_0, agency, mposTrade, mpos);
						}
						agency_single_profit_rate = new BigDecimal(StringUtil.getMapValue(agency, "single_profit_rate"));
					}
				}
			}
		}
	}
	
	/**
	 * 处理单个会员收益及记录
	 * @param benefit_money
	 * @param op_type
	 * @param state_type
	 * @param benefit
	 * @param trade
	 * @param transpos
	 */
	public void mposShareBenefit(BigDecimal benefit_money, String op_type, String state_type, Map<String, Object> benefit, Map<String, Object> trade, Map<String, Object> mpos)throws Exception{
		int num = 0;
		//更新用户收益
		String order_id = StringUtil.getDateTimeAndRandomForID();
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		edit_user.put("money", benefit_money);
		edit_user.put("today_benefit", benefit_money);
		edit_user.put("total_benefit", benefit_money);
		edit_user.put("op_type", op_type);
		edit_user.put("op_order_id", order_id);
		edit_user.put("state_type", state_type);
		edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
		edit_user.put("sn", StringUtil.getMapValue(trade, "sn"));
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		System.out.println("需要查询的SN号：" + StringUtil.getMapValue(trade, "sn"));
		Map<String,Object> map = agentSysMposInfoMapper.getAgentSysMposInfoBySn(StringUtil.getMapValue(trade, "sn"));
		String mer_name = StringUtil.getMapValue(map,"mer_name");
		num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
		if(num != 1){
			throw new Exception("用户收益更新异常");
		}
		
		//记录结算分润
		Map<String, Object> record = new HashMap<>();
		record.put("order_id", order_id);
		record.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		record.put("sn", StringUtil.getMapValue(trade, "sn"));
		record.put("trans_amount", StringUtil.getMapValue(trade, "trans_amount"));
		record.put("trans_type", StringUtil.getMapValue(trade, "trans_type"));
		record.put("card_type", StringUtil.getMapValue(trade, "card_type"));
		record.put("trans_time", StringUtil.getMapValue(trade, "trans_time"));
		record.put("benefit_type", op_type);
		record.put("state_type", state_type);
		record.put("single_amount", StringUtil.getMapValue(trade, "mer_withdraw_fee"));
		record.put("benefit_money", benefit_money);
		record.put("tran_ref_code", StringUtil.getMapValue(trade, "tran_ref_code"));
		record.put("card_settle_price", StringUtil.getMapValue(benefit, "card_settle_price"));
		record.put("cloud_settle_price", StringUtil.getMapValue(benefit, "cloud_settle_price"));
		record.put("single_profit_rate", StringUtil.getMapValue(benefit, "single_profit_rate"));
		record.put("credit_card_rate", StringUtil.getMapValue(mpos, "credit_card_rate"));
		record.put("cloud_flash_rate", StringUtil.getMapValue(mpos, "cloud_flash_rate"));
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		if(mer_name.isEmpty()){
			record.put("mer_name", mer_name);
			System.out.println(mer_name);
		}
		num = transactionDataProcessingMapper.insertUserMposShareBenefitRecord(record);
		if(num != 1){
			throw new Exception("收益日志记录异常");
		}
		
		//记录通知
		Map<String, Object> message = new HashMap<>();
		message.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		message.put("order_id", order_id);
		message.put("op_type", op_type);
		message.put("money", benefit_money);
		message.put("sn", StringUtil.getMapValue(trade, "sn"));
		message.put("pos_type", BenefitParamConstants.pos_type_02);
		message.put("cre_date", TimeUtil.getDayString());
		message.put("cre_time", TimeUtil.getTimeString());
		num = transactionDataProcessingMapper.insertUserMessageInfo(message);
		if(num != 1){
			throw new Exception("通知记录异常");
		}

		//分润推送通知add byqh202003
		Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(benefit, "user_id"));
		if(StringUtil.getMapValue(userMap, "device_type").contains("iOS") &&
				!"".equals(StringUtil.getMapValue(userMap, "device_token"))){
			StringBuffer notice = new StringBuffer();
			notice.append("【中付钱柜】您收到一笔[");
			String tt = StringUtil.getMapValue(trade, "trans_type");
			if("1".equals(tt)){
				notice.append("刷卡");
			}else if("2".equals(tt)){
				notice.append("快捷支付");
			}else if("3".equals(tt)){
				notice.append("微信");
			}else if("4".equals(tt)){
				notice.append("支付宝");
			}else{
				notice.append("云闪付");
			}
			notice.append("]收益").append(benefit_money).append("元");
			IOSPushy.PushyMessage("",notice.toString(),StringUtil.getMapValue(userMap, "device_token"));
		}
	}
	
	/**
	 * 交易额数据汇总
	 * @param transposTrade
	 * @param transpos
	 * @param merchant
	 * @param agencyList
	 */
	public void processingMposTradeSummary(Map<String, Object> mposTrade, Map<String, Object> mpos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		int num = 0;
		int trade_num = 0;
		//判断是否有交易
		if(!"1".equals(StringUtil.getMapValue(merchant, "trade_status"))){
			trade_num = 1;
			merchant.put("up_date", TimeUtil.getDayString());
			merchant.put("up_time", TimeUtil.getTimeString());
			num = transactionDataProcessingMapper.updateMposTradeStatus(merchant);
			if(num != 1){
				throw new Exception("交易状态更新异常");
			}
		}
		
		//汇总参数
		Map<String, Object> param = new HashMap<>();
		param.put("performance", StringUtil.getMapValue(mposTrade, "trans_amount"));
		param.put("sn", StringUtil.getMapValue(mposTrade, "sn"));
		param.put("trade_num", trade_num);
		param.put("up_date", TimeUtil.getDayString());
		param.put("up_time", TimeUtil.getTimeString());
		
		//汇总直属商户交易额
		param.put("user_id", StringUtil.getMapValue(merchant, "user_id"));
		num = transactionDataProcessingMapper.updateSummaryMposMerchantPerformance(param);
		if(num != 1){
			throw new Exception("商户交易额汇总异常");
		}
		
		//汇总直属会员交易额
		num = transactionDataProcessingMapper.updateSummaryMposUserMerchantPerformance(param);
		if(num != 1){
			throw new Exception("用户交易额汇总异常");
		}
		
		//处理代理
		if(agencyList != null && agencyList.size() > 0){
			for(Map<String, Object> agency : agencyList){
				//汇总代理商户交易额
				param.put("user_id", StringUtil.getMapValue(agency, "user_id"));
				num = transactionDataProcessingMapper.updateSummaryMposAgencyPerformance(param);
				if(num != 1){
					throw new Exception("代理商户交易额汇总异常");
				}
				
				//汇总代理会员交易额
				num = transactionDataProcessingMapper.updateSummaryMposUserAgencyPerformance(param);
				if(num != 1){
					throw new Exception("代理用户交易额汇总异常");
				}
			}
		}
		
	}
	
	/**
	 * 将待处理表数据移入正式表
	 * @param transposTrade
	 */
	public void backupMposTransactionRecord(Map<String, Object> mposTrade)throws Exception{
		int num = 0;
//		//移入正式表
//		num = transactionDataProcessingMapper.backupMposTransactionRecord(mposTrade);
//		if(num != 1){
//			throw new Exception("交易数据移入正式表异常");
//		}
		
		//删除待处理表
		num = transactionDataProcessingMapper.deleteMposTransactionRecordDeal(mposTrade);
		if(num != 1){
			throw new Exception("待处理交易数据删除异常");
		}
	}

	/**
	 * 处理机器返现
	 */
	@Override
	public void processingMachineBack() {
		LOGGER.info("------------------处理机器返现分润-START-------------");
		try{
			//查询待处理的传统POS机返现
			List<Map<String, Object>> traposMachineBackList = transactionDataProcessingMapper.getTraposMachineBackList();
			if(traposMachineBackList!=null && traposMachineBackList.size()>0){
				for(Map<String, Object> traposMachineBack : traposMachineBackList){
					SpringUtils.getAopProxy(this).processingTraposMachineBack(traposMachineBack);
				}
			}
			
			//查询待处理的MPOS机返现
			List<Map<String, Object>> mposMachineBackList = transactionDataProcessingMapper.getMposMachineBackList();
			if(mposMachineBackList!=null && mposMachineBackList.size()>0){
				for(Map<String, Object> mposMachineBack : mposMachineBackList){
					SpringUtils.getAopProxy(this).processingMposMachineBack(mposMachineBack);
				}
			}
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingMachineBack方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------处理机器返现分润-END-------------");
	}
	
	/**
	 * 处理单笔传统POS机器返现
	 * @param traposMachineBack
	 */
	@Transactional
	public void processingTraposMachineBack(Map<String, Object> traposMachineBack){
		int num = 0;
		Map<String, Object> param = null;
		try{
			/************************************修改处理状态，开始处理交易********************************************/
			//修改待处理状态
			param = new HashMap<String, Object>();
			param.put("old_status", StatusTypeConstant.trapos_machine_cashback_record_deal_status_00);
			param.put("new_status", StatusTypeConstant.trapos_machine_cashback_record_deal_status_02);
			param.put("sn", StringUtil.getMapValue(traposMachineBack, "sn"));
			num = transactionDataProcessingMapper.updateTraposMachineBackDealStatus(param);
			if(num != 1){
				return;
			}
			
			try{
				//移入正式表
				num = transactionDataProcessingMapper.backupTraposMachineBackRecord(traposMachineBack);
				if(num != 1){
					throw new Exception("交易数据移入正式表异常");
				}
			}catch(Exception e){
				LOGGER.error("移入正式表异常:" + traposMachineBack.toString() + ExceptionUtil.getExceptionAllinformation(e));
				param.put("old_status", StatusTypeConstant.trapos_machine_cashback_record_deal_status_02);
				param.put("new_status", StatusTypeConstant.trapos_machine_cashback_record_deal_status_08);
				param.put("sn", StringUtil.getMapValue(traposMachineBack, "sn"));
				num = transactionDataProcessingMapper.updateTraposMachineBackDealStatus(param);
				if(num != 1){
					throw new Exception("更新失败状态异常");
				}
				return;
			}
			
			/************************************查询分润相关信息记录********************************************/
			//查询系统参数
			Map<String, Object> trapos = transactionDataProcessingMapper.getTraposDetailInfo(StringUtil.getMapValue(traposMachineBack, "sn"));
			if(trapos == null){
				throw new Exception("未查询到POS机参数信息:" + StringUtil.getMapValue(traposMachineBack, "sn"));
			}
			//查询直属会员
			Map<String, Object> merchant = transactionDataProcessingMapper.getTraposMerchantInfo(StringUtil.getMapValue(traposMachineBack, "sn"));
			if(merchant == null){
				throw new Exception("未查询到POS机直属会员:" + StringUtil.getMapValue(traposMachineBack, "sn"));
			}
			//查询分润列表
			List<Map<String, Object>> agencyList = transactionDataProcessingMapper.getTraposAgencyList(merchant);
			
			/************************************开始处理单笔收益********************************************/
			this.processingTraposMachineBackBenefit(traposMachineBack, trapos, merchant, agencyList);
			
			/************************************将已处理结束返现移到正式表**********************************/
			this.backupTraposMachineBack(traposMachineBack);
			
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingTraposMachineBack方法处理异常:" + traposMachineBack.toString() + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * 处理直属和代理机器返现分润
	 * @param traposMachineBack
	 * @param trapos
	 * @param merchant
	 * @param agencyList
	 */
	public void processingTraposMachineBackBenefit(Map<String, Object> traposMachineBack, Map<String, Object> trapos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//返现分润比例
		BigDecimal agency_cash_back_rate = new BigDecimal(0);
		//返现费用
		BigDecimal cash_back_money = null;
		
		if("1".equals(StringUtil.getMapValue(trapos, "cash_back_type"))){
			cash_back_money = new BigDecimal(StringUtil.getMapValue(traposMachineBack, "return_amt"));
		}else{
			if(new BigDecimal(StringUtil.getMapValue(trapos, "cash_back_condition")).compareTo(new BigDecimal(StringUtil.getMapValue(traposMachineBack, "return_amt")))>0){
				cash_back_money = new BigDecimal(StringUtil.getMapValue(traposMachineBack, "return_amt"));
			}else{
				cash_back_money = new BigDecimal(StringUtil.getMapValue(trapos, "cash_back_money"));
			}
		}
		
		//更新返现状态
		trapos.put("cash_back_money", cash_back_money);
		int num = transactionDataProcessingMapper.updateTraposCashBackStatus(trapos);
		if(num != 1){
			throw new Exception("更新返现状态异常");
		}
				
		
		if(cash_back_money.compareTo(new BigDecimal(0))>0){
			//处理直属会员收益
			if(agency_cash_back_rate.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate")))<0){
				BigDecimal benefit_money = cash_back_money.multiply(new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate")).subtract(agency_cash_back_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.traposMachineBackBenefit(cash_back_money, benefit_money, BenefitRecordTypeContants.benefit_record_type_03, BenefitParamConstants.state_type_1, merchant, traposMachineBack, trapos);
				}
				agency_cash_back_rate = new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate"));
			}
			
			
			//处理代理收益
			if(agencyList != null && agencyList.size() > 0){
				for(Map<String, Object> agency : agencyList){
					if(agency_cash_back_rate.compareTo(new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate")))<0){
						BigDecimal benefit_money = cash_back_money.multiply(new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate")).subtract(agency_cash_back_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.traposMachineBackBenefit(cash_back_money, benefit_money, BenefitRecordTypeContants.benefit_record_type_03, BenefitParamConstants.state_type_0, agency, traposMachineBack, trapos);
						}
						agency_cash_back_rate = new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate"));
					}
				}
			}
		}
	}
	
	/**
	 * 处理单个会员返现及记录
	 * @param benefit_money
	 * @param op_type
	 * @param state_type
	 * @param benefit
	 * @param trade
	 * @param transpos
	 */
	public void traposMachineBackBenefit(BigDecimal return_amt, BigDecimal benefit_money, String op_type, String state_type, Map<String, Object> benefit, Map<String, Object> machineBack, Map<String, Object> trapos)throws Exception{
		int num = 0;
		//更新用户收益
		String order_id = StringUtil.getDateTimeAndRandomForID();
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		edit_user.put("money", benefit_money);
		edit_user.put("today_benefit", benefit_money);
		edit_user.put("total_benefit", benefit_money);
		edit_user.put("op_type", op_type);
		edit_user.put("op_order_id", order_id);
		edit_user.put("state_type", state_type);
		edit_user.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(machineBack, "sn"));
		if("epos".equals(pos_type)){
			edit_user.put("pos_type", BenefitParamConstants.pos_type_03);
		}else{
			edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
		}
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
		if(num != 1){
			throw new Exception("用户收益更新异常");
		}
		
		//记录结算分润
		Map<String, Object> record = new HashMap<>();
		record.put("order_id", order_id);
		record.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		record.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		record.put("frozen_time", StringUtil.getMapValue(machineBack, "frozen_time"));
		record.put("tran_ref_code", StringUtil.getMapValue(machineBack, "tran_ref_code"));
		record.put("money", benefit_money);
		record.put("return_amt", return_amt);
		record.put("cash_back_rate", StringUtil.getMapValue(benefit, "cash_back_rate"));
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		if("epos".equals(pos_type)){
			record.put("pos_type","03");
		}
		num = transactionDataProcessingMapper.insertUserTraposMachineBackRecord(record);
		if(num != 1){
			throw new Exception("收益日志记录异常");
		}
		
		//记录通知
		Map<String, Object> message = new HashMap<>();
		message.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		message.put("order_id", order_id);
		message.put("op_type", op_type);
		message.put("money", benefit_money);
		message.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		message.put("pos_type", BenefitParamConstants.pos_type_01);
		message.put("cre_date", TimeUtil.getDayString());
		message.put("cre_time", TimeUtil.getTimeString());
		num = transactionDataProcessingMapper.insertUserMessageInfo(message);
		if(num != 1){
			throw new Exception("通知记录异常");
		}
	}
	
	public void backupTraposMachineBack(Map<String, Object> traposMachineBack)throws Exception{
		int num = 0;
//		//移入正式表
//		num = transactionDataProcessingMapper.backupTraposMachineBackRecord(traposMachineBack);
//		if(num != 1){
//			throw new Exception("交易数据移入正式表异常");
//		}
		
		//删除待处理表
		num = transactionDataProcessingMapper.deleteTraposMachineBackRecordDeal(traposMachineBack);
		if(num != 1){
			throw new Exception("待处理交易数据删除异常");
		}
	}
	
	/**
	 * 处理单笔传统POS机器返现
	 * @param transposTrade
	 */
	@Transactional
	public void processingMposMachineBack(Map<String, Object> mposMachineBack){
		int num = 0;
		Map<String, Object> param = null;
		try{
			/************************************修改处理状态，开始处理交易********************************************/
			//修改待处理状态
			param = new HashMap<String, Object>();
			param.put("old_status", StatusTypeConstant.mpos_machine_cashback_record_deal_status_00);
			param.put("new_status", StatusTypeConstant.mpos_machine_cashback_record_deal_status_02);
			param.put("sn", StringUtil.getMapValue(mposMachineBack, "sn"));
			num = transactionDataProcessingMapper.updateMposMachineBackDealStatus(param);
			if(num != 1){
				return;
			}
			
			try{
				//移入正式表
				num = transactionDataProcessingMapper.backupMposMachineBackRecord(mposMachineBack);
				if(num != 1){
					throw new Exception("交易数据移入正式表异常");
				}
			}catch(Exception e){
				LOGGER.error("移入正式表异常:" + mposMachineBack.toString() + ExceptionUtil.getExceptionAllinformation(e));
				param.put("old_status", StatusTypeConstant.mpos_machine_cashback_record_deal_status_02);
				param.put("new_status", StatusTypeConstant.mpos_machine_cashback_record_deal_status_08);
				param.put("sn", StringUtil.getMapValue(mposMachineBack, "sn"));
				num = transactionDataProcessingMapper.updateMposMachineBackDealStatus(param);
				if(num != 1){
					throw new Exception("更新失败状态异常");
				}
				return;
			}
			/************************************查询分润相关信息记录********************************************/
			//查询系统参数
			Map<String, Object> mpos = transactionDataProcessingMapper.getMposDetailInfo(StringUtil.getMapValue(mposMachineBack, "sn"));
			if(mpos == null){
				throw new Exception("未查询到POS机参数信息:" + StringUtil.getMapValue(mposMachineBack, "sn"));
			}
			//查询直属会员
			Map<String, Object> merchant = transactionDataProcessingMapper.getMposMerchantInfo(StringUtil.getMapValue(mposMachineBack, "sn"));
			if(merchant == null){
				throw new Exception("未查询到POS机直属会员:" + StringUtil.getMapValue(mposMachineBack, "sn"));
			}
			//查询分润列表
			List<Map<String, Object>> agencyList = transactionDataProcessingMapper.getMposAgencyList(merchant);
			
			/************************************开始处理单笔收益********************************************/
			this.processingMposMachineBackBenefit(mposMachineBack, mpos, merchant, agencyList);
			
			/************************************将已处理结束返现移到正式表**********************************/
			this.backupMposMachineBack(mposMachineBack);
			
		}catch(Exception e){
			LOGGER.error("TransactionDataProcessingServiceImpl -- processingMposMachineBack方法处理异常:" + mposMachineBack.toString() + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * 处理直属和代理机器返现分润
	 * @param transposTrade
	 * @param transpos
	 * @param merchant
	 * @param agencyList
	 */
	public void processingMposMachineBackBenefit(Map<String, Object> mposMachineBack, Map<String, Object> mpos, Map<String, Object> merchant, List<Map<String, Object>> agencyList)throws Exception{
		//返现分润比例
		BigDecimal agency_cash_back_rate = new BigDecimal(0);
		//返现费用
		BigDecimal cash_back_money = null;
		
		if("1".equals(StringUtil.getMapValue(mpos, "cash_back_type"))){
			cash_back_money = new BigDecimal(StringUtil.getMapValue(mposMachineBack, "return_amt"));
		}else{
			if(new BigDecimal(StringUtil.getMapValue(mpos, "cash_back_condition")).compareTo(new BigDecimal(StringUtil.getMapValue(mposMachineBack, "return_amt")))>0){
				cash_back_money = new BigDecimal(StringUtil.getMapValue(mposMachineBack, "return_amt"));
			}else{
				cash_back_money = new BigDecimal(StringUtil.getMapValue(mpos, "cash_back_money"));
			}
		}
		
		//更新返现状态
		mpos.put("cash_back_money", cash_back_money);
		int num = transactionDataProcessingMapper.updateMposCashBackStatus(mpos);
		if(num != 1){
			throw new Exception("更新返现状态异常");
		}
				
		
		if(cash_back_money.compareTo(new BigDecimal(0))>0){
			//处理直属会员收益
			if(agency_cash_back_rate.compareTo(new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate")))<0){
				BigDecimal benefit_money = cash_back_money.multiply(new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate")).subtract(agency_cash_back_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
				if(benefit_money.compareTo(new BigDecimal(0))>0){
					this.mposMachineBackBenefit(cash_back_money, benefit_money, BenefitRecordTypeContants.benefit_record_type_03, BenefitParamConstants.state_type_1, merchant, mposMachineBack, mpos);
				}
				agency_cash_back_rate = new BigDecimal(StringUtil.getMapValue(merchant, "cash_back_rate"));
			}
			
			
			//处理代理收益
			if(agencyList != null && agencyList.size() > 0){
				for(Map<String, Object> agency : agencyList){
					if(agency_cash_back_rate.compareTo(new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate")))<0){
						BigDecimal benefit_money = cash_back_money.multiply(new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate")).subtract(agency_cash_back_rate)).divide(new BigDecimal(100)).setScale(SysParamConstant.money_decimal_digits, BigDecimal.ROUND_DOWN);
						if(benefit_money.compareTo(new BigDecimal(0))>0){
							this.mposMachineBackBenefit(cash_back_money, benefit_money, BenefitRecordTypeContants.benefit_record_type_03, BenefitParamConstants.state_type_0, agency, mposMachineBack, mpos);
						}
						agency_cash_back_rate = new BigDecimal(StringUtil.getMapValue(agency, "cash_back_rate"));
					}
				}
			}
		}
	}
	
	/**
	 * 处理单个会员返现及记录
	 * @param benefit_money
	 * @param op_type
	 * @param state_type
	 * @param benefit
	 * @param trade
	 * @param transpos
	 */
	public void mposMachineBackBenefit(BigDecimal return_amt, BigDecimal benefit_money, String op_type, String state_type, Map<String, Object> benefit, Map<String, Object> machineBack, Map<String, Object> mpos)throws Exception{
		int num = 0;
		//更新用户收益
		String order_id = StringUtil.getDateTimeAndRandomForID();
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		edit_user.put("money", benefit_money);
		edit_user.put("today_benefit", benefit_money);
		edit_user.put("total_benefit", benefit_money);
		edit_user.put("op_type", op_type);
		edit_user.put("op_order_id", order_id);
		edit_user.put("state_type", state_type);
		edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
		edit_user.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
		if(num != 1){
			throw new Exception("用户收益更新异常");
		}
		
		//记录结算分润
		Map<String, Object> record = new HashMap<>();
		record.put("order_id", order_id);
		record.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		record.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		record.put("frozen_time", StringUtil.getMapValue(machineBack, "frozen_time"));
		record.put("tran_ref_code", StringUtil.getMapValue(machineBack, "tran_ref_code"));
		record.put("money", benefit_money);
		record.put("return_amt", return_amt);
		record.put("cash_back_rate", StringUtil.getMapValue(benefit, "cash_back_rate"));
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		num = transactionDataProcessingMapper.insertUserMposMachineBackRecord(record);
		if(num != 1){
			throw new Exception("收益日志记录异常");
		}
		
		//记录通知
		Map<String, Object> message = new HashMap<>();
		message.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
		message.put("order_id", order_id);
		message.put("op_type", op_type);
		message.put("money", benefit_money);
		message.put("sn", StringUtil.getMapValue(machineBack, "sn"));
		message.put("pos_type", BenefitParamConstants.pos_type_02);
		message.put("cre_date", TimeUtil.getDayString());
		message.put("cre_time", TimeUtil.getTimeString());
		num = transactionDataProcessingMapper.insertUserMessageInfo(message);
		if(num != 1){
			throw new Exception("通知记录异常");
		}
	}
	
	public void backupMposMachineBack(Map<String, Object> mposMachineBack)throws Exception{
		int num = 0;
//		//移入正式表
//		num = transactionDataProcessingMapper.backupMposMachineBackRecord(mposMachineBack);
//		if(num != 1){
//			throw new Exception("交易数据移入正式表异常");
//		}
		
		//删除待处理表
		num = transactionDataProcessingMapper.deleteMposMachineBackRecordDeal(mposMachineBack);
		if(num != 1){
			throw new Exception("待处理交易数据删除异常");
		}
	}

}
