package com.example.longecological.service.machines.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.longecological.mapper.merchant.MerchantManageMapper;
import com.example.longecological.mapper.mpos.MposMapper;
import com.example.longecological.mapper.system.SysNoticeMapper;
import com.example.longecological.mapper.traditionalpos.TraditionalPosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MachinesManageCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.machines.MachinesManageMapper;
import com.example.longecological.service.common.SysParamRateService;
import com.example.longecological.service.machines.MachinesManageService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class MachinesManageServiceImpl implements MachinesManageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MachinesManageServiceImpl.class);
	
	@Autowired
	private MachinesManageMapper machinesManageMapper;
	
	@Autowired
	private UserInfoCacheService userInfoCacheService;
	
	@Autowired
	private SysParamRateService sysParamRateService;

	@Autowired
	private MposMapper mposMapper;

	@Autowired
	private TraditionalPosMapper traditionalPosMapper;

	@Autowired
	private SysNoticeMapper sysNoticeMapper;

	@Autowired
	private MerchantManageMapper merchantManageMapper;

	/**
	 * 获取待分配列表（传统POS）
	 */
	@Override
	public R getTraditionalPosAllocationList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosAllocationList = machinesManageMapper.getTraditionalPosAllocationList(map);
			respondMap.put("traditionalPosAllocationList", traditionalPosAllocationList);

			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTraditionalPosAllocationList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 获取待分配列表（MPOS）
	 */
	@Override
	public R getMposAllocationList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposAllocationList = machinesManageMapper.getMposAllocationList(map);
			respondMap.put("mposAllocationList", mposAllocationList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getMposAllocationList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 获取待分配列表（流量卡）
	 */
	@Override
	public R getTrafficCardAllocationList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> trafficCardAllocationList = machinesManageMapper.getTrafficCardAllocationList(map);
			respondMap.put("trafficCardAllocationList", trafficCardAllocationList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTrafficCardAllocationList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 获取待召回列表（传统POS） update byqh 201912
	 */
	@Override
	public R getTraditionalPosRecallList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosRecallList = machinesManageMapper.getTraditionalPosRecallList(map);
			respondMap.put("traditionalPosRecallList", traditionalPosRecallList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTraditionalPosRecallList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 获取待召回列表（MPOS）
	 */
	@Override
	public R getMposRecallList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposRecallList = machinesManageMapper.getMposRecallList(map);
			respondMap.put("mposRecallList", mposRecallList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getMposRecallList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 选择奖励 add byqh 201912
	 */
	@Override
	public R chooseAward(Map<String,Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			List<Map<String,Object>> list = machinesManageMapper.selectPolicy3RecordAll(map);
			if(list.size()>0){
				for(Map<String,Object> machinesMap : list){
					if(map.get("id").equals(machinesMap.get("policy_id").toString())){
						String order_id = StringUtil.getDateTimeAndRandomForID();
						String up_date = TimeUtil.getDayString();
						String up_time = TimeUtil.getTimeString();
						machinesMap.put("op_order_id",order_id);
						machinesMap.put("up_date",up_date);
						machinesMap.put("up_time",up_time);
						int num = 0;

						if("01".equals(machinesMap.get("pos_type"))){
							num = machinesManageMapper.updateUserMoneyBenefit(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}

							machinesMap.put("cre_date",up_date);
							machinesMap.put("cre_time",up_time);
							num = machinesManageMapper.insertUserTraposActivityRewardRecord(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}
						}else if("02".equals(machinesMap.get("pos_type"))){
							num = machinesManageMapper.updateUserMoneyBenefit(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}

							machinesMap.put("cre_date",up_date);
							machinesMap.put("cre_time",up_time);
							num = machinesManageMapper.insertUserMposActivityRewardRecord(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}
						}
						machinesManageMapper.updatePolicy3RecordChooseField(map);
						break;
					}
				}
			}



			List<Map<String,Object>> list5 = machinesManageMapper.selectPolicy5RecordAll(map);
			if(list5.size()>0){
				for(Map<String,Object> machinesMap : list){
					if(map.get("id").equals(machinesMap.get("policy_id").toString())){
						String order_id = StringUtil.getDateTimeAndRandomForID();
						String up_date = TimeUtil.getDayString();
						String up_time = TimeUtil.getTimeString();
						machinesMap.put("op_order_id",order_id);
						machinesMap.put("up_date",up_date);
						machinesMap.put("up_time",up_time);
						machinesMap.put("op_type","04");
						machinesMap.put("today_benefit",machinesMap.get("money"));
						int num = 0;

						if("01".equals(machinesMap.get("pos_type"))){
							num = machinesManageMapper.updateUserMoneyBenefit(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}

							machinesMap.put("cre_date",up_date);
							machinesMap.put("cre_time",up_time);
							machinesMap.put("begin_date",up_date);
							num = machinesManageMapper.insertUserTraposActivityRewardRecord(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}
						}else if("02".equals(machinesMap.get("pos_type"))){
							num = machinesManageMapper.updateUserMoneyBenefit(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}

							machinesMap.put("cre_date",up_date);
							machinesMap.put("cre_time",up_time);
							machinesMap.put("begin_date",up_date);
							num = machinesManageMapper.insertUserMposActivityRewardRecord(machinesMap);
							if(num!=1){
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
							}
						}
						machinesManageMapper.updatePolicy5RecordChooseField(map);
						break;
					}
				}
			}


			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);

		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}

	/***
	 * 查询政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R selectPolicy3List(Map<String,Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "sys_user_id"));
			map.put("manager_id", StringUtil.getMapValue(user, "manager_id"));
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> policy3List = machinesManageMapper.selectPolicy3List(map);
			respondMap.put("policy3List",policy3List);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			LOGGER.error("MachinesManageServiceImpl -- selectPolicy3List方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 查询达标政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R selectPolicy3Record(Map<String,Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();

			Map<String, Object> user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "sys_user_id"));
			map.put("manager_id", StringUtil.getMapValue(user, "manager_id"));

			List<Map<String, Object>> machinesPolicy3Record = machinesManageMapper.selectPolicy3Record(map);
			List<Map<String, Object>> machinesPolicy5Record = machinesManageMapper.selectPolicy5Record(map);

			for(Map<String,Object> subMap : machinesPolicy3Record){
				subMap.put("policy_type","3");
				map.put("sn",subMap.get("sn"));
				List<Map<String, Object>> policy3List = machinesManageMapper.selectPolicy3List(map);
				subMap.put("policy3List",policy3List);
			}

			for(Map<String,Object> subMap : machinesPolicy5Record){
				subMap.put("policy_type","5");
				subMap.put("end_date", TimeUtil.getDayString());
				subMap.put("begin_date", TimeUtil.getDayString());
				subMap.put("trade_amount","300");
				List<Map<String, Object>> policy5List = new ArrayList<>();
				Map<String,Object> subMap5 = new HashMap<>();
				subMap5.put("policy_quantity","300");
				subMap5.put("id",subMap.get("policy_id"));
				subMap5.put("policy_amount",subMap.get("money"));
				policy5List.add(subMap5);
				subMap.put("policy3List",policy5List);
			}
			machinesPolicy3Record.addAll(machinesPolicy5Record);
			respondMap.put("machinesPolicy3Record",machinesPolicy3Record);



			//byqh202006 达标商户5
//			List<Map<String, Object>> machinesPolicy5Record = machinesManageMapper.selectPolicy5Record(map);
//			for(Map map5 : machinesPolicy5Record){
//				map5.put("end_date", TimeUtil.getDayString());
//				map5.put("begin_date", TimeUtil.getDayString());
//				map5.put("trade_amount","300");
//				List<Map<String, Object>> policy5List = new ArrayList<>();
//				Map<String,Object> subMap5 = new HashMap<>();
//				subMap5.put("policy_quantity","300");
//				subMap5.put("id",map5.get("policy_id"));
//				subMap5.put("policy_amount",map5.get("money"));
//				policy5List.add(subMap5);
//				map5.put("policy5List",policy5List);
//			}
//			respondMap.put("machinesPolicy5Record",machinesPolicy5Record);
//			//byqh202006 达标商户5
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			LOGGER.error("MachinesManageServiceImpl -- selectPolicy3Record方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R selectUnbindTraditionalPos(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosUnbindList = machinesManageMapper.selectUnbindTraditionalPos(map);
			respondMap.put("traditionalPosUnbindList",traditionalPosUnbindList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			LOGGER.error("MachinesManageServiceImpl -- selectUnbindTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R selectUnbindMpos(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposUnbindList =  machinesManageMapper.selectUnbindMpos(map);
			respondMap.put("mposUnbindList",mposUnbindList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			LOGGER.error("MachinesManageServiceImpl -- selectUnbindMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 获取待召回列表（流量卡） update byqh 201912
	 */
	@Override
	public R getTrafficCardRecallList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> trafficCardRecallList = machinesManageMapper.getTrafficCardRecallList(map);
			respondMap.put("trafficCardRecallList", trafficCardRecallList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTrafficCardRecallList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 分配POS机（传统POS） update byqh 201912
	 */
	@Override
	@Transactional
	public R allocationTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAllocationTraditionalPos = this.checkAllocationTraditionalPos(map);
			if(!Boolean.valueOf(checkAllocationTraditionalPos.get(R.SUCCESS_TAG).toString())) {
			    return checkAllocationTraditionalPos;
			}

			//add byqh202003 begin
			int is_reward = machinesManageMapper.checkIsReward(map);
			if(is_reward>1){
				return R.error("code_999988","交易量达标返现领取有多种设置，不能同时下拨");
			}
			String policy3Name = machinesManageMapper.checkIsPolicy3(map);
			if(policy3Name!=null){
				if("1".equals(StringUtil.getMapValue(map, "is_reward"))){
					int is_repeat = machinesManageMapper.checkRepeatSetReward(map);
					if(is_repeat>1){
						return R.error("code_999888","交易量达标返现领取，不能重新开启");
					}
				}
			}else{
				map.put("is_reward","1");
			}
			//add byqh202003 end

			/********************开始处理分配操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addAllocationTraditionalPos(map);
			//更新旧的分配记录
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num2 = machinesManageMapper.updateOldAllocationTraditionalPos(map);
			if((num1 + num2) != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995994, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995994);
			}
			
			//更新当前用户归属状态
			int num3 = machinesManageMapper.updateTraditionalPosStateStatus(map);
			if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995993, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995993);
			}
			//新增批次每批量分配一次新增一个批次，用于按批次修改 begin add byqh
			Map<String,Object> batchMap = new HashMap<>();
			String batch_no = map.get("sys_user_id")+"_"+TimeUtil.getDate();
			batchMap.put("batch_no",batch_no);
			batchMap.put("sn",map.get("sn_list"));
			batchMap.put("user_id",map.get("acce_user_id"));
			batchMap.put("pos_type",map.get("pos_type")==null?"TraditionalPOS":map.get("pos_type"));
			batchMap.put("allocate_date",TimeUtil.getDayFormat5());
			batchMap.put("allocate_by",map.get("sys_user_id"));
			machinesManageMapper.insertPosBatchAllocate(batchMap);
			//新增批次每批量分配一次新增一个批次，用于按批次修改 end add byqh
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- allocationTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAllocationTraditionalPos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要分配的POS机
		if(sn_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995999, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995999);
		}
		
		//校验分配对象是否为下级代理
		Map<String, Object> acce_user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "acce_user_id"));
		if(acce_user == null){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995998, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995998);
		}
		if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(acce_user, "referer_id"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995997, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995997);
		}
		if(!"09".equals(StringUtil.getMapValue(acce_user, "auth_status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995978, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995978);
		}
		
		//校验分配的参数是否符合
		//刷卡结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "card_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}

		//刷卡结算底价vip
		if(new BigDecimal(StringUtil.getMapValue(map, "card_settle_price_vip")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//微信结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "weixin_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}

		//支付宝结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "zhifubao_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//云闪付结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "cloud_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//单笔分润比例
		if(new BigDecimal(StringUtil.getMapValue(map, "single_profit_rate")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//返现比例
		if(new BigDecimal(StringUtil.getMapValue(map, "cash_back_rate")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//封顶费
		if(new BigDecimal(StringUtil.getMapValue(map, "mer_cap_fee")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}

		//校验当前参数与所选POS机是否合规
		int num = machinesManageMapper.checkAllocationTraditionalPos(map);
		if(num != sn_list.length){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995995, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995995);
		}
				
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 分配POS机（MPOS）
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R allocationMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAllocationMpos = this.checkAllocationMpos(map);
			if(!Boolean.valueOf(checkAllocationMpos.get(R.SUCCESS_TAG).toString())) {
			    return checkAllocationMpos;
			}

			//add byqh202003 begin
			String is_reward = machinesManageMapper.checkIsRewardMpos(map);
			if(is_reward!=null && Integer.parseInt(is_reward)>1){
				return R.error("code_999988","交易量达标返现领取有多种设置，不能同时下拨");
			}
			String policy3Name = machinesManageMapper.checkIsPolicy3(map);
			if(policy3Name!=null){
				if("1".equals(StringUtil.getMapValue(map, "is_reward"))){
					int is_repeat = machinesManageMapper.checkRepeatSetRewardMpos(map);
					if(is_repeat>1){
						return R.error("code_999888","交易量达标返现领取，不能重新开启");
					}
				}
			}
			//add byqh202003 end
			/********************开始处理分配操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addAllocationMpos(map);
			//更新旧的分配记录
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num2 = machinesManageMapper.updateOldAllocationMpos(map);
			if((num1 + num2) != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995994, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995994);
			}
			
			//更新当前用户归属状态
			int num3 = machinesManageMapper.updateMposStateStatus(map);
			if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995993, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995993);
			}

			//新增批次每批量分配一次新增一个批次，用于按批次修改 begin add byqh
			Map<String,Object> batchMap = new HashMap<>();
			String batch_no = map.get("sys_user_id")+"_"+TimeUtil.getDate();
			batchMap.put("batch_no",batch_no);
			batchMap.put("sn",map.get("sn_list"));
			batchMap.put("user_id",map.get("acce_user_id"));
			batchMap.put("pos_type","MPOS");
			batchMap.put("allocate_date",TimeUtil.getDayFormat5());
			batchMap.put("allocate_by",map.get("sys_user_id"));
			machinesManageMapper.insertPosBatchAllocate(batchMap);
			//新增批次每批量分配一次新增一个批次，用于按批次修改 end add byqh
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- allocationMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAllocationMpos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要分配的POS机
		if(sn_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995999, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995999);
		}
		
		//校验分配对象是否为下级代理
		Map<String, Object> acce_user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "acce_user_id"));
		if(acce_user == null){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995998, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995998);
		}
		if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(acce_user, "referer_id"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995997, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995997);
		}
		if(!"09".equals(StringUtil.getMapValue(acce_user, "auth_status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995978, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995978);
		}
		
		//校验分配的参数是否符合
		//刷卡结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "card_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//云闪付结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "cloud_settle_price")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//单笔分润比例
		if(new BigDecimal(StringUtil.getMapValue(map, "single_profit_rate")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//返现比例
		if(new BigDecimal(StringUtil.getMapValue(map, "cash_back_rate")).compareTo(new BigDecimal(0))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}

		//校验当前参数与所选POS机是否合规
		int num = machinesManageMapper.checkAllocationMpos(map);
		if(num != sn_list.length){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995995, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995995);
		}
				
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 分配流量卡
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R allocationTrafficCard(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAllocationTrafficCard = this.checkAllocationTrafficCard(map);
			if(!Boolean.valueOf(checkAllocationTrafficCard.get(R.SUCCESS_TAG).toString())) {
			    return checkAllocationTrafficCard;
			}
			/********************开始处理分配操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addAllocationTrafficCard(map);
			//更新旧的分配记录
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num2 = machinesManageMapper.updateOldAllocationTrafficCard(map);
			if((num1 + num2) != Integer.parseInt(StringUtil.getMapValue(map, "card_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995994, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995994);
			}
			
			//更新当前用户归属状态
			int num3 = machinesManageMapper.updateTrafficCardStateStatus(map);
			if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "card_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995992, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995992);
			}
			//新增批次每批量分配一次新增一个批次，用于按批次修改 begin add byqh
			Map<String,Object> batchMap = new HashMap<>();
			String batch_no = map.get("sys_user_id")+"_"+TimeUtil.getDate();
			batchMap.put("batch_no",batch_no);
			batchMap.put("sn",map.get("card_list"));
			batchMap.put("user_id",map.get("acce_user_id"));
			batchMap.put("pos_type","TrafficCard");
			batchMap.put("allocate_date",TimeUtil.getDayFormat5());
			batchMap.put("allocate_by",map.get("sys_user_id"));
			machinesManageMapper.insertPosBatchAllocate(batchMap);
			//新增批次每批量分配一次新增一个批次，用于按批次修改 end add byqh
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- allocationTrafficCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAllocationTrafficCard(Map<String, Object> map){
		String[] card_list = StringUtil.getMapValue(map, "card_list").split(",");
		//校验是否上传需要分配的POS机
		if(card_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995999, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995999);
		}
				
		//校验分配对象是否为下级代理
		Map<String, Object> acce_user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "acce_user_id"));
		if(acce_user == null){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995998, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995998);
		}
		if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(acce_user, "referer_id"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995997, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995997);
		}
		if(!"09".equals(StringUtil.getMapValue(acce_user, "auth_status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995978, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995978);
		}
		
		//上传数量
		map.put("card_length", card_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}


	@Override
	@Transactional
	public R recallTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkRecallTraditionalPos = this.checkRecallTraditionalPos(map);
			if(!Boolean.valueOf(checkRecallTraditionalPos.get(R.SUCCESS_TAG).toString())) {
			    return checkRecallTraditionalPos;
			}

			//add byqh 201912
//			int check = machinesManageMapper.checkPosPolicy(String.valueOf(map.get("sn_list")));
//			if(check>0){
//				return R.error(CommonCodeConstant.COMMON_CODE_999966, CommonCodeConstant.COMMON_MSG_999966);
//			}
			/********************开始处理召回操作***************************/
			//插入最新的召回记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addRecallTraditionalPos(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995990, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995990);
			}

			//add byqh 201912
			String sn_list = String.valueOf(map.get("sn_list"));
			List<Map<String,Object>> list = traditionalPosMapper.getUserTraditionalPosBelongBySN(sn_list);
			sysNoticeMapper.updateNewsReadFlagBatch(list,"0","recallFlag");

			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- recallTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkRecallTraditionalPos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要召回的POS机
		if(sn_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995991, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995991);
		}
		//校验分配的参数是否符合
		
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 召回POS机（MPOS）
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R recallMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkRecallMpos = this.checkRecallMpos(map);
			if(!Boolean.valueOf(checkRecallMpos.get(R.SUCCESS_TAG).toString())) {
			    return checkRecallMpos;
			}

			//add byqh 201912
//			int check = machinesManageMapper.checkPosPolicy(String.valueOf(map.get("sn_list")));
//			if(check>0){
//				return R.error(CommonCodeConstant.COMMON_CODE_999966, CommonCodeConstant.COMMON_MSG_999966);
//			}
			/********************开始处理召回操作***************************/
			//插入最新的召回记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addRecallMpos(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995990, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995990);
			}

			//add byqh 201912
			String sn_list = String.valueOf(map.get("sn_list"));
			List<Map<String,Object>> list = mposMapper.getUserMposBelongBySN(sn_list);
			sysNoticeMapper.updateNewsReadFlagBatch(list,"0","recallFlag");

			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- recallMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkRecallMpos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要召回的POS机
		if(sn_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995991, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995991);
		}
		//校验分配的参数是否符合
		
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 召回流量卡
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R recallTrafficCard(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkRecallTrafficCard = this.checkRecallTrafficCard(map);
			if(!Boolean.valueOf(checkRecallTrafficCard.get(R.SUCCESS_TAG).toString())) {
			    return checkRecallTrafficCard;
			}

			//add byqh 201912
//			int check = machinesManageMapper.checkPosPolicy(String.valueOf(map.get("card_list")));
//			if(check>0){
//				return R.error(CommonCodeConstant.COMMON_CODE_999966, CommonCodeConstant.COMMON_MSG_999966);
//			}
			/********************开始处理召回操作***************************/
			//插入最新的召回记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addRecallTrafficCard(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "card_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995988, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995988);
			}

			//add byqh 201912
			String card_list = String.valueOf(map.get("card_list"));
			List<Map<String,Object>> list = machinesManageMapper.getUserTrafficBelongBySN(card_list);
			sysNoticeMapper.updateNewsReadFlagBatch(list,"0","recallFlag");
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- recallTrafficCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkRecallTrafficCard(Map<String, Object> map){
		String[] card_list = StringUtil.getMapValue(map, "card_list").split(",");
		//校验是否上传需要召回的POS机
		if(card_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995989, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995989);
		}
		//校验分配的参数是否符合
		
		//上传数量
		map.put("card_length", card_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/**
	 * 解绑POS机（传统POS）
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R unbindTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
//			R checkUnbindTraditionalPos = this.checkUnbindTraditionalPos(map);
//			if(!Boolean.valueOf(checkUnbindTraditionalPos.get(R.SUCCESS_TAG).toString())) {
//			    return checkUnbindTraditionalPos;
//			}
			/********************开始处理召回操作***************************/
			//插入最新的召回记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addUnbindTraditionalPos(map);
			if(num1 < 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995986, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995986);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- unbindTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkUnbindTraditionalPos(Map<String, Object> map){
		//校验解绑的POS机
		int num = 0;
		num = machinesManageMapper.checkUnbindTraditionalPos(map);
		if(num < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995987, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995987);
		}
		
		num = machinesManageMapper.checkExistsUnbindTraditionalPos(map);
		if(num > 0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995977, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995977);
		}
		
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/**
	 * 解绑POS机（MPOS）update byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R unbindMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
//			R checkUnbindMpos = this.checkUnbindMpos(map);
//			if(!Boolean.valueOf(checkUnbindMpos.get(R.SUCCESS_TAG).toString())) {
//			    return checkUnbindMpos;
//			}
			/********************开始处理召回操作***************************/
			//插入最新的解绑记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.addUnbindMpos(map);
			if(num1 < 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995986, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995986);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- unbindMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkUnbindMpos(Map<String, Object> map){
		int num = 0;
		//校验解绑的POS机
		num = machinesManageMapper.checkUnbindMpos(map);
		if(num < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995987, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995987);
		}
		
		num = machinesManageMapper.checkExistsUnbindMpos(map);
		if(num > 0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995977, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995977);
		}
		
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/**
	 * 查询直推代理
	 */
	@Override
	public R getRefererAgency(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> refererAgencyList = machinesManageMapper.getRefererAgency(map);
			respondMap.put("refererAgencyList", refererAgencyList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getRefererAgency方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询系统费率参数(传统POS)
	 */
	@Override
	public R getTraditionalPosSysParamRateList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//新增查询代理商的低价方法，分配POS查询费率的时候不会查出比代理商低价低的费率 add byqh
			if(null==map.get("sn") || "".equals(map.get("sn"))){
				Map<String, Object> traditionalPosSysParamRate = sysParamRateService.getSysParamRateByTraditionalPos();
				respondMap.put("traditionalPosSysParamRate", traditionalPosSysParamRate);
			}else{
				Map<String, Object> traditionalPosSysParamRate = sysParamRateService.getSysParamRateByTraditionalPosAgent(map);
				respondMap.put("traditionalPosSysParamRate", traditionalPosSysParamRate);

				//增加交易达标，分期达标标识 add byqh202006
				List<Map<String, Object>> policy2 = machinesManageMapper.getPolicy2BySN(map);
				respondMap.put("policy2",policy2);
//				List<Map<String, Object>> policy3 = machinesManageMapper.getPolicy3BySN(String.valueOf(map.get("sn")));
//				respondMap.put("policy3",policy3);
				//增加交易达标，分期达标标识 add byqh202006
			}

			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTraditionalPosSysParamRateList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	@Override
	public R policy2OnOff(Map<String, Object> map) {
		//验签成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		int cnt = machinesManageMapper.policy2OnOff(map);
		if(cnt>0){
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		}
		return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
	}

	/**
	 * 查询系统费率参数(MPOS)
	 */
	@Override
	public R getMposSysParamRateList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//新增查询代理商的低价方法，分配POS查询费率的时候不会查出比代理商低价低的费率 add byqh
			if(null==map.get("sn") || "".equals(map.get("sn"))){
				Map<String, Object> mposSysParamRate = sysParamRateService.getSysParamRateByMpos();
				respondMap.put("mposSysParamRate", mposSysParamRate);
			}else{
				Map<String, Object> mposSysParamRate = sysParamRateService.getSysParamRateByMposAgent(map);
				respondMap.put("mposSysParamRate", mposSysParamRate);

				//增加交易达标，分期达标标识 add byqh202006
				List<Map<String, Object>> policy2 = machinesManageMapper.getPolicy2BySN(map);
				respondMap.put("policy2",policy2);
				List<Map<String, Object>> policy3 = machinesManageMapper.getPolicy3BySN(String.valueOf(map.get("sn")));
				respondMap.put("policy3",policy3);
				//增加交易达标，分期达标标识 add byqh202006
			}

			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getMposSysParamRateList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询解绑记录（传统POS）
	 */
	@Override
	public R getTraditionalPosUnbindRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosUnbindRecordList =  machinesManageMapper.getUnbindRecordTraditionalPosList(map);
			respondMap.put("traditionalPosUnbindRecordList", traditionalPosUnbindRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTraditionalPosUnbindRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询解绑记录（MPOS）
	 */
	@Override
	public R getMposUnbindRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposUnbindRecordList =  machinesManageMapper.getUnbindRecordMposList(map);
			respondMap.put("mposUnbindRecordList", mposUnbindRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getTraditionalPosUnbindRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询传统POS机分配记录
	 */
	@Override
	public R getAllocationTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> allocationTraditionalPosList = machinesManageMapper.getAllocationTraditionalPosList(map);
			respondMap.put("allocationTraditionalPosList", allocationTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getAllocationTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询MPOS机分配记录
	 */
	@Override
	public R getAllocationMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> allocationMposList = machinesManageMapper.getAllocationMposList(map);
			respondMap.put("allocationMposList", allocationMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getAllocationMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询传统POS机分配详情
	 */
	@Override
	public R getAllocationTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> allocationTraditionalPos = machinesManageMapper.getAllocationTraditionalPosDetail(map);
			respondMap.put("allocationTraditionalPos", allocationTraditionalPos);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getAllocationTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询MPOS机分配详情
	 */
	@Override
	public R getAllocationMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> allocationMpos = machinesManageMapper.getAllocationMposDetail(map);
			respondMap.put("allocationMpos", allocationMpos);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getAllocationMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 修改传统POS机分配记录
	 */
	@Override
	@Transactional
	public R editAllocationTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkEditAllocationTraditionalPos = this.checkEditAllocationTraditionalPos(map);
			if(!Boolean.valueOf(checkEditAllocationTraditionalPos.get(R.SUCCESS_TAG).toString())) {
			    return checkEditAllocationTraditionalPos;
			}
			/********************开始处理分配操作***************************/
			//更新分配记录
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num = machinesManageMapper.updateAllocationTraditionalPos(map);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995983, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995983);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- editAllocationTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkEditAllocationTraditionalPos(Map<String, Object> map){
		//查询需要修改的记录
		Map<String, Object> allocationRecord = machinesManageMapper.getCheckEditAllocationTraditionalPosDetail(map);
		if(allocationRecord == null){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995985, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995985);
		}
		
		//分配对象
		Map<String, Object> allocationUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(allocationRecord, "user_id"));
		if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(allocationUser, "referer_id"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995984, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995984);
		}
		
		//刷卡结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "card_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "card_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//微信结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "weixin_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "weixin_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}

		//支付宝结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "zhifubao_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "zhifubao_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
				
		//云闪付结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "cloud_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "cloud_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
				
		//单笔分润比例
		if(new BigDecimal(StringUtil.getMapValue(map, "single_profit_rate")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "single_profit_rate")))>0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		
		//返现比例
		if(new BigDecimal(StringUtil.getMapValue(map, "cash_back_rate")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "cash_back_rate")))>0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}	
		
		//封顶费
		if(new BigDecimal(StringUtil.getMapValue(map, "mer_cap_fee")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "mer_cap_fee")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 修改MPOS机分配记录
	 */
	@Override
	@Transactional
	public R editAllocationMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkEditAllocationMpos = this.checkEditAllocationMpos(map);
			if(!Boolean.valueOf(checkEditAllocationMpos.get(R.SUCCESS_TAG).toString())) {
			    return checkEditAllocationMpos;
			}
			/********************开始处理分配操作***************************/
			//更新分配记录
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num = machinesManageMapper.updateAllocationMpos(map);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995983, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995983);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- editAllocationMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkEditAllocationMpos(Map<String, Object> map){
		//查询需要修改的记录
		Map<String, Object> allocationRecord = machinesManageMapper.getCheckEditAllocationMposDetail(map);
		if(allocationRecord == null){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995985, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995985);
		}
				
		//分配对象
		Map<String, Object> allocationUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(allocationRecord, "user_id"));
		if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(allocationUser, "referer_id"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995984, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995984);
		}
				
		//刷卡结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "card_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "card_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
				
		//云闪付结算底价
		if(new BigDecimal(StringUtil.getMapValue(map, "cloud_settle_price")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "cloud_settle_price")))<0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
						
		//单笔分润比例
		if(new BigDecimal(StringUtil.getMapValue(map, "single_profit_rate")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "single_profit_rate")))>0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}
						
		//返现比例
		if(new BigDecimal(StringUtil.getMapValue(map, "cash_back_rate")).compareTo(new BigDecimal(StringUtil.getMapValue(allocationRecord, "cash_back_rate")))>0){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995996, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995996);
		}		
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 查询流量卡分配记录
	 */
	@Override
	public R getAllocationTrafficCardList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> allocationTrafficCardList = machinesManageMapper.getAllocationTrafficCardList(map);
			respondMap.put("allocationTrafficCardList", allocationTrafficCardList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getAllocationTrafficCardList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询召回记录（传统POS）
	 */
	@Override
	public R getRecallTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> recallTraditionalPosList = machinesManageMapper.getRecallTraditionalPosList(map);
			respondMap.put("recallTraditionalPosList", recallTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getRecallTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询召回记录（传统POS）add byqh202003
	 */
	@Override
	public R getRecallEposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> recallTraditionalPosList = machinesManageMapper.getRecallEposList(map);
			respondMap.put("recallTraditionalPosList", recallTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getRecallTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 处理召回（传统POS）
	 */
	@Override
	@Transactional
	public R dealRecallTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkDealRecallTraditionalPos = this.checkDealRecallTraditionalPos(map);
			if(!Boolean.valueOf(checkDealRecallTraditionalPos.get(R.SUCCESS_TAG).toString())) {
			    return checkDealRecallTraditionalPos;
			}
			/********************开始处理分配操作***************************/
			//更新召回状态
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.updateRecallTraditionalPosStatus(map);

			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995980, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995980);
			}
			
			if("09".equals(StringUtil.getMapValue(map, "status"))){
				//更新上级用户POS机归属
				int num2 = machinesManageMapper.updateRecallTraditionalPosSendState(map);
				if(num2 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}
				
				//更新当前用户POS机归属
				int num3 = machinesManageMapper.updateRecallTraditionalPosAcceState(map);
				if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}

				//删除分配记录 byqh202002
				machinesManageMapper.updateBatchAllocate(String.valueOf(map.get("ids_list")),StringUtil.getMapValue(map, "sys_user_id"));
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- dealRecallTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkDealRecallTraditionalPos(Map<String, Object> map){
		String[] ids_list = StringUtil.getMapValue(map, "ids_list").split(",");
		//校验是否上传需要召回的POS机
		if(ids_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995982, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995982);
		}
		//校验分配的参数是否符合
		if(!"08".equals(StringUtil.getMapValue(map, "status")) && !"09".equals(StringUtil.getMapValue(map, "status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995981, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995981);
		}
		
		//上传数量
		map.put("ids_length", ids_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 查询召回记录（流量卡）
	 */
	@Override
	public R getRecallTrafficCardList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> recallTrafficCardList = machinesManageMapper.getRecallTrafficCardList(map);
			respondMap.put("recallTrafficCardList", recallTrafficCardList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getRecallTrafficCardList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 处理召回（流量卡）
	 */
	@Override
	@Transactional
	public R dealRecallTrafficCard(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkDealRecallTrafficCard = this.checkDealRecallTrafficCard(map);
			if(!Boolean.valueOf(checkDealRecallTrafficCard.get(R.SUCCESS_TAG).toString())) {
			    return checkDealRecallTrafficCard;
			}
			/********************开始处理分配操作***************************/
			//更新召回状态
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.updateRecallTrafficCardStatus(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995980, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995980);
			}
			
			if("09".equals(StringUtil.getMapValue(map, "status"))){
				//更新当前用户POS机归属
				int num2 = machinesManageMapper.updateRecallTrafficCardSendState(map);
				if(num2 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}
				
				//更新上级用户POS机归属
				int num3 = machinesManageMapper.updateRecallTrafficCardAcceState(map);
				if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- dealRecallTrafficCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}

	private R checkDealRecallTrafficCard(Map<String, Object> map){
		String[] ids_list = StringUtil.getMapValue(map, "ids_list").split(",");
		//校验是否上传需要召回的POS机
		if(ids_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995982, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995982);
		}
		//校验分配的参数是否符合
		if(!"08".equals(StringUtil.getMapValue(map, "status")) && !"09".equals(StringUtil.getMapValue(map, "status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995981, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995981);
		}
		
		//上传数量
		map.put("ids_length", ids_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 查询召回记录（MPOS）
	 */
	@Override
	public R getRecallMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> recallMposList = machinesManageMapper.getRecallMposList(map);
			respondMap.put("recallMposList", recallMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- getRecallMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 处理召回（MPOS）
	 */
	@Override
	@Transactional
	public R dealRecallMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkDealRecallMpos = this.checkDealRecallMpos(map);
			if(!Boolean.valueOf(checkDealRecallMpos.get(R.SUCCESS_TAG).toString())) {
			    return checkDealRecallMpos;
			}
			/********************开始处理分配操作***************************/
			//更新召回状态
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			int num1 = machinesManageMapper.updateRecallMposStatus(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995980, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995980);
			}
			
			if("09".equals(StringUtil.getMapValue(map, "status"))){
				//更新当前用户POS机归属
				int num2 = machinesManageMapper.updateRecallMposSendState(map);
				if(num2 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}
				
				//更新上级用户POS机归属
				int num3 = machinesManageMapper.updateRecallMposAcceState(map);
				if(num3 != Integer.parseInt(StringUtil.getMapValue(map, "ids_length"))){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995979, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995979);
				}
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MachinesManageServiceImpl -- dealRecallMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkDealRecallMpos(Map<String, Object> map){
		String[] ids_list = StringUtil.getMapValue(map, "ids_list").split(",");
		//校验是否上传需要召回的POS机
		if(ids_list.length < 1){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995982, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995982);
		}
		//校验分配的参数是否符合
		if(!"08".equals(StringUtil.getMapValue(map, "status")) && !"09".equals(StringUtil.getMapValue(map, "status"))){
			return R.error(MachinesManageCodeConstant.MachinesManage_INFO_CODE_995981, MachinesManageCodeConstant.MachinesManage_INFO_MSG_995981);
		}
		
		//上传数量
		map.put("ids_length", ids_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/***
	 * 根据批次修改POS信息 add byqh
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R editAllocationMPosBatch(Map<String, Object> map) {
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			List<Map<String,Object>> allocateList = machinesManageMapper.selectPosBatchAllocateDetail(map);
			for(Map<String,Object> allocateMap : allocateList){
				String sn = allocateMap.get("sn").toString();
				String user_id = String.valueOf(allocateMap.get("user_id"));
				Map<String,Object> posMap = new HashMap<>();
				posMap.put("sys_user_id",user_id);
				posMap.put("sn",sn);
				Map<String, Object> userMpos = mposMapper.getUserMposInfoAll(posMap);
				Object userMposId = userMpos.get("id");
				map.put("allocation_id",userMposId);
				R result = editAllocationMpos(map);
				Boolean boo =(Boolean) result.get(R.SUCCESS_TAG);
				if(!boo){
					return result;
				}
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		}catch(Exception e){
			LOGGER.error("MachinesManageServiceImpl -- editAllocationMPosBatch方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}

	}

	/***
	 * 根据批次修改POS信息 add byqh
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public R editAllocationTraditionalPosBatch(Map<String, Object> map) {
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			List<Map<String,Object>> allocateList = machinesManageMapper.selectPosBatchAllocateDetail(map);
			for(Map<String,Object> allocateMap : allocateList){
				String sn = allocateMap.get("sn").toString();
				String user_id = String.valueOf(allocateMap.get("user_id"));
				Map<String,Object> posMap = new HashMap<>();
				posMap.put("sys_user_id",user_id);
				posMap.put("sn",sn);
				Map<String, Object> userMpos = traditionalPosMapper.getUserTraditionalPosInfoAll(posMap);
				Object userMposId = userMpos.get("id");
				map.put("allocation_id",userMposId);
				R result = editAllocationTraditionalPos(map);
				Boolean boo =(Boolean) result.get(R.SUCCESS_TAG);
				if(!boo){
					return result;
				}
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		}catch (Exception e){
			LOGGER.error("MachinesManageServiceImpl -- editAllocationTraditionalPosBatch方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}

	}

	/***
	 * 查询分配的批次 add byqh
	 * @param map
	 * @return
	 */
	@Override
	public R selectPosBatchAllocate(Map<String, Object> map) {
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String,Object> respondMap = new HashMap<>();
			List<Map<String,Object>> allocateList = machinesManageMapper.selectPosBatchAllocate(map);
			respondMap.put("allocateList",allocateList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch(Exception e){
			LOGGER.error("MachinesManageServiceImpl -- dealRecallTrafficCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 得到代理的机器结算价格byqh
	 * @param map
	 * @return
	 */
	public R selectPosSettlePriceBySN(Map<String, Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String,Object> respondMap = new HashMap<>();
			if("MPOS".equals(map.get("pos_type"))){
				map.put("sys_user_id",map.get("user_id").toString());
				Map<String,Object> settleInfo = mposMapper.getUserMposInfoAll(map);
				String policy3Name = merchantManageMapper.getPolicy3Name(StringUtil.getMapValue(map,"sn"));
				if(policy3Name==null){
					settleInfo.put("is_reward",null);
				}
				Map<String,Object> snMap = machinesManageMapper.selectPosBatchSNList(map);
				settleInfo.putAll(snMap);
				Map<String,Object> usermap = userInfoCacheService.getUserInfoCacheById(map.get("user_id").toString());
				settleInfo.put("real_name",usermap.get("real_name"));
				String policy_name = machinesManageMapper.selectAllocatePolicyName(String.valueOf(map.get("batch_no")));
				settleInfo.put("policy_name",policy_name);
				respondMap.put("allocationPos",settleInfo);
			}else if("TraditionalPOS".equals(map.get("pos_type"))){
				map.put("sys_user_id",map.get("user_id").toString());
				Map<String,Object> settleInfo = traditionalPosMapper.getUserTraditionalPosInfoAll(map);
				String policy3Name = merchantManageMapper.getPolicy3Name(StringUtil.getMapValue(map,"sn"));
				if(policy3Name==null){
					settleInfo.put("is_reward",null);
				}
				Map<String,Object> snMap = machinesManageMapper.selectPosBatchSNList(map);
				settleInfo.putAll(snMap);
				Map<String,Object> usermap = userInfoCacheService.getUserInfoCacheById(map.get("user_id").toString());
				settleInfo.put("real_name",usermap.get("real_name"));
				String policy_name = machinesManageMapper.selectAllocatePolicyName(String.valueOf(map.get("batch_no")));
				settleInfo.put("policy_name",policy_name);
				respondMap.put("allocationPos",settleInfo);
			}

			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
