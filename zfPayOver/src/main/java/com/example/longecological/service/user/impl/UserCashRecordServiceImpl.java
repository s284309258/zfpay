package com.example.longecological.service.user.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.TradeCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.entity.cash.CashRecord;
import com.example.longecological.mapper.user.UserCardInfoMapper;
import com.example.longecological.mapper.user.UserCashRecordMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.user.UserCashRecordService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户提现相关
 * @author Administrator
 *
 */
@Service
public class UserCashRecordServiceImpl implements UserCashRecordService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCashRecordServiceImpl.class);
	

	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	SysParamService sysParamService;
	@Autowired
	VerifyUserService verifyUserService;
	
	
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserCardInfoMapper userCardInfoMapper;
	@Autowired
	UserCashRecordMapper userCashRecordMapper;
	

	/**
	 * 查询提现信息
	 */
	@Override
	public R getCashInfo(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//（1）提现单笔固定手续费
			String cashSingleFeet = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cashSingleFeet);
			//（2）最低提现额度
			String cashMinNum = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cashMinNum);
			//（3）提现手续费率
			String cashFeetRate = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cashFeetRate);
			//（4）查询用户账户实时余额信息
			Map<String, Object> userPurseMap = userInfoMapper.getUserPurseById(map.get("sys_user_id").toString());
			
			//（5）返回提现信息
			Map<String, Object> cashInfoMap=new HashMap<>();
			cashInfoMap.put("cashSingleFeet", cashSingleFeet);
			cashInfoMap.put("cashMinNum", cashMinNum);
			cashInfoMap.put("cashFeetRate", cashFeetRate);
			cashInfoMap.put("deduct_money", userPurseMap.get("deduct_money").toString());
//			cashInfoMap.put("can_cash_money", new BigDecimal(userPurseMap.get("money").toString()).subtract(new BigDecimal(userPurseMap.get("today_benefit").toString())));
			cashInfoMap.put("can_cash_money", new BigDecimal(userPurseMap.get("money").toString()));
		
			//（6）设置缓存
			redisUtils.set(RedisNameConstants.zfpay_user_can_cash_info+map.get("sys_user_id").toString(), cashInfoMap, SysParamConstant.cash_past_seconds);
			
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("cashInfo", cashInfoMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- getCashInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);

		}
	}


	
	/**
	 * 用户申请提现
	 */
	@Override
	@Transactional
	public R applyCash(Map<String, Object> map) {
		//（1）解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		//（2）用户申请提现操作
		return this.applyCashOper((Map<String, Object>) map.get("data"));
	}


	
	/**
	 * 用户申请提现操作
	 * @param map
	 * @return
	 */
	private R applyCashOper(Map<String, Object> dataMap) {
		try {
			int i=0;
			//（1）实名认证和交易密码信息校验（且获得身份证号信息）
			R authPayPassCheckReslut = verifyUserService.checkAuthAndPayPass(dataMap);
			if(!Boolean.valueOf(authPayPassCheckReslut.get(R.SUCCESS_TAG).toString())) {
				return authPayPassCheckReslut;
			}
			//（2）取现数量校验
			if(!RegexUtil.isVaildTradeNum(dataMap.get("cash_money").toString())) {
				return R.error(TradeCodeConstant.TRADE_CODE_999699,TradeCodeConstant.TRADE_MSG_999699);
			}
			//（3）取出用户查到的关于取现的缓存信息
			Map<String, Object> cashInfoMap = (Map<String, Object>) redisUtils.get(RedisNameConstants.zfpay_user_can_cash_info+dataMap.get("sys_user_id").toString());
			BigDecimal cashMinNum = new BigDecimal(cashInfoMap.get("cashMinNum").toString());//提现最小金额
			BigDecimal cashSingleFeet = new BigDecimal(cashInfoMap.get("cashSingleFeet").toString());//提现单笔规定手续费
			BigDecimal cashFeetRate = new BigDecimal(cashInfoMap.get("cashFeetRate").toString());//提现单笔手续费率
			BigDecimal deduct_money = new BigDecimal(cashInfoMap.get("deduct_money").toString());//未达标扣除金额
			BigDecimal can_cash_money = new BigDecimal(cashInfoMap.get("can_cash_money").toString());//可提现金额
			BigDecimal cash_money = new BigDecimal(dataMap.get("cash_money").toString());//用户申请提现金额
			//（4）提现金额不得大于可提现金额
			if(cash_money.compareTo(can_cash_money)==1) {
				//申请提现的金额大于了可以提现的金额
				return R.error(TradeCodeConstant.TRADE_CODE_999698,TradeCodeConstant.TRADE_MSG_999698);
			}
			//（5）提现金额必须大于未达标扣除金额
			if(!(cash_money.compareTo(deduct_money)==1)) {
				//申请提现的金额小于等于了未达标扣除金额
				return R.error(TradeCodeConstant.TRADE_CODE_999697,TradeCodeConstant.TRADE_MSG_999697);
			}
			//（6）提现金额需大于最小提现金额
			if((cash_money.subtract(deduct_money)).compareTo(cashMinNum)==-1) {
				//申请提现的金额小于了最小提现金额
				return R.error(TradeCodeConstant.TRADE_CODE_999696,TradeCodeConstant.TRADE_MSG_999696);
			}
			//（7）计算各项数据信息
			BigDecimal rate_feet_money = (cash_money.subtract(deduct_money)).multiply(cashFeetRate) ;//比例手续费金额
			BigDecimal cash_actual_money = (cash_money.subtract(deduct_money)).subtract(rate_feet_money).subtract(cashSingleFeet);//实际到账金额
			dataMap.put("cash_actual_money", cash_actual_money);//实际到账金额
			dataMap.put("feet_rate", cashFeetRate);//所得税比例
			dataMap.put("rate_feet_money", rate_feet_money);//比例手续费
			dataMap.put("single_feet_money", cashSingleFeet);//单笔手续费
			dataMap.put("deduct_money", deduct_money);//考核未达标扣除金额
			//（8）结算卡信息校验
			Map<String, Object> userCardMap = userCardInfoMapper.getUserCardInfo(dataMap);
			if(userCardMap==null) {
				return R.error(TradeCodeConstant.TRADE_CODE_999695,TradeCodeConstant.TRADE_MSG_999695);
			}
			dataMap.put("account", userCardMap.get("account"));//结算账号
			dataMap.put("account_name", userCardMap.get("account_name"));//结算账户名
			dataMap.put("bank_code", userCardMap.get("bank_code"));//银行代码
			dataMap.put("bank_name", userCardMap.get("bank_name"));//银行名称
			dataMap.put("account", userCardMap.get("account"));//结算账号
			dataMap.put("id_card", userCardMap.get("id_card"));//身份证号
			dataMap.put("status", TypeStatusConstant.user_cash_record_detail_cash_status_00);//提现状态：待处理
			dataMap.put("order_id", StringUtil.getDateTimeAndRandomForID_20());//订单号
			dataMap.put("batch_no", StringUtil.getDateTimeAndRandomForID_20());//批次号
			dataMap.put("cre_date", TimeUtil.getDayString());//创建日期
			dataMap.put("cre_time", TimeUtil.getTimeString());//创建时间
			//（9）更新用户账户余额
			dataMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_06);//用户申请提现
			i = userInfoMapper.updateUserByApplyCash(dataMap);
			if(i != 1){//进行回滚
				return R.error(TradeCodeConstant.TRADE_CODE_999694,TradeCodeConstant.TRADE_MSG_999694);
			}
			//（10）记录提现订单记录
			dataMap.put("cash_id", null);
			i = userCashRecordMapper.addUserCashRecord(dataMap);
			if(i != 1){//进行回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeCodeConstant.TRADE_CODE_999693,TradeCodeConstant.TRADE_MSG_999693);
			}
			//（11）记录提现记录详情
			dataMap.put("cash_status", TypeStatusConstant.user_cash_record_detail_cash_status_00);//提现状态：待处理
			dataMap.put("note", "申请提现");
			i = userCashRecordMapper.addUserCashRecordDetail(dataMap);
			if(i != 1){//进行回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeCodeConstant.TRADE_CODE_999693,TradeCodeConstant.TRADE_MSG_999693);
			}
			//（12）清除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_can_cash_info+dataMap.get("sys_user_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- applyCashOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);

		}
	}


	
	/**
	 * 查询提现记录列表
	 */
	@Override
	public R getCashRecordList(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			map.put("pageSize", SysParamConstant.page_size);
			List<CashRecord> cashRecordList = userCashRecordMapper.getCashRecordList(map);
			respondMap.put("cashRecordList", cashRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- getUserCardList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);

		}
	}
	
}
