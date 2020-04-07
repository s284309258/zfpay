package com.example.longecological.service.benefitcentre.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.BenefitCentreCodeConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.benefitcentre.BenefitCentreMapper;
import com.example.longecological.service.benefitcentre.BenefitCentreService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class BenefitCentreServiceImpl implements BenefitCentreService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BenefitCentreServiceImpl.class);
	
	@Autowired
	private BenefitCentreMapper benefitCentreMapper;

	/**
	 * 收益中心头部信息
	 */
	@Override
	public R getHeaderInformation(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取传统POS信息
			Map<String, Object> headerInfo = benefitCentreMapper.getHeaderInformation(map);
			respondMap.put("headerInfo", headerInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getHeaderInformation方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 收益中心详情（传统POS）
	 */
	@Override
	public R getBenefitCentreTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			/****************************校验参数类型****************************************/
			//校验时间参数
			if("".equals(StringUtil.getMapValue(map, "date"))){
				return R.error(BenefitCentreCodeConstant.MerchantManage_INFO_CODE_992999, BenefitCentreCodeConstant.MerchantManage_INFO_MSG_992999);
			}
			/****************************处理查询****************************************/
			Map<String, Object> respondMap = new HashMap<>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(StringUtil.getMapValue(map,"date")));
			map.put("date",cre_month);
			Map<String, Object> record = benefitCentreMapper.getMonthTraditionalPosBenefitDeatil(map);
			Map<String, Object> traditionalPosBenefitDeatil = new HashMap<>();
			if(record == null){
				traditionalPosBenefitDeatil.put("benefit", "0.00");
				traditionalPosBenefitDeatil.put("merchant_benefit", "0.00");
				traditionalPosBenefitDeatil.put("agency_benefit", "0.00");
				traditionalPosBenefitDeatil.put("share_benefit", "0.00");
				traditionalPosBenefitDeatil.put("return_benefit", "0.00");
				traditionalPosBenefitDeatil.put("activity_benefit", "0.00");
				traditionalPosBenefitDeatil.put("deduct_money", "0.00");
			}else{
				traditionalPosBenefitDeatil.put("benefit", StringUtil.getMapValue(record, "benefit"));
				traditionalPosBenefitDeatil.put("merchant_benefit", StringUtil.getMapValue(record, "merchant_benefit"));
				traditionalPosBenefitDeatil.put("agency_benefit", StringUtil.getMapValue(record, "agency_benefit"));
				traditionalPosBenefitDeatil.put("share_benefit", StringUtil.getMapValue(record, "share_benefit"));
				traditionalPosBenefitDeatil.put("return_benefit", StringUtil.getMapValue(record, "return_benefit"));
				traditionalPosBenefitDeatil.put("activity_benefit", StringUtil.getMapValue(record, "activity_benefit"));
				traditionalPosBenefitDeatil.put("deduct_money", StringUtil.getMapValue(record, "deduct_money"));
			}
			respondMap.put("traditionalPosBenefitDeatil", traditionalPosBenefitDeatil);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getBenefitCentreTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 收益中心详情（传统POS）add byqh202003
	 */
	@Override
	public R getBenefitCentreEposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			/****************************校验参数类型****************************************/
			//校验时间参数
			if("".equals(StringUtil.getMapValue(map, "date"))){
				return R.error(BenefitCentreCodeConstant.MerchantManage_INFO_CODE_992999, BenefitCentreCodeConstant.MerchantManage_INFO_MSG_992999);
			}
			/****************************处理查询****************************************/
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> record = benefitCentreMapper.getMonthEposBenefitDeatil(map);
			Map<String, Object> traditionalPosBenefitDeatil = new HashMap<>();
			if(record == null){
				traditionalPosBenefitDeatil.put("benefit", "0.00");
				traditionalPosBenefitDeatil.put("merchant_benefit", "0.00");
				traditionalPosBenefitDeatil.put("agency_benefit", "0.00");
				traditionalPosBenefitDeatil.put("share_benefit", "0.00");
				traditionalPosBenefitDeatil.put("return_benefit", "0.00");
				traditionalPosBenefitDeatil.put("activity_benefit", "0.00");
				traditionalPosBenefitDeatil.put("deduct_money", "0.00");
			}else{
				traditionalPosBenefitDeatil.put("benefit", StringUtil.getMapValue(record, "benefit"));
				traditionalPosBenefitDeatil.put("merchant_benefit", StringUtil.getMapValue(record, "merchant_benefit"));
				traditionalPosBenefitDeatil.put("agency_benefit", StringUtil.getMapValue(record, "agency_benefit"));
				traditionalPosBenefitDeatil.put("share_benefit", StringUtil.getMapValue(record, "share_benefit"));
				traditionalPosBenefitDeatil.put("return_benefit", StringUtil.getMapValue(record, "return_benefit"));
				traditionalPosBenefitDeatil.put("activity_benefit", StringUtil.getMapValue(record, "activity_benefit"));
				traditionalPosBenefitDeatil.put("deduct_money", StringUtil.getMapValue(record, "deduct_money"));
			}
			respondMap.put("traditionalPosBenefitDeatil", traditionalPosBenefitDeatil);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getBenefitCentreTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 收益中心详情（MPOS）
	 */
	@Override
	public R getBenefitCentreMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			/****************************校验参数类型****************************************/
			//校验时间参数
			if("".equals(StringUtil.getMapValue(map, "date"))){
				return R.error(BenefitCentreCodeConstant.MerchantManage_INFO_CODE_992999, BenefitCentreCodeConstant.MerchantManage_INFO_MSG_992999);
			}
			/****************************处理查询****************************************/
			Map<String, Object> respondMap = new HashMap<>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(StringUtil.getMapValue(map,"date")));
			map.put("date",cre_month);
			Map<String, Object> record = benefitCentreMapper.getMonthMposBenefitDeatil(map);
			Map<String, Object> mposBenefitDeatil = new HashMap<>();
			if(record == null){
				mposBenefitDeatil.put("benefit", "0.00");
				mposBenefitDeatil.put("merchant_benefit", "0.00");
				mposBenefitDeatil.put("agency_benefit", "0.00");
				mposBenefitDeatil.put("share_benefit", "0.00");
				mposBenefitDeatil.put("return_benefit", "0.00");
				mposBenefitDeatil.put("activity_benefit", "0.00");
				mposBenefitDeatil.put("deduct_money", "0.00");
			}else{
				mposBenefitDeatil.put("benefit", StringUtil.getMapValue(record, "benefit"));
				mposBenefitDeatil.put("merchant_benefit", StringUtil.getMapValue(record, "merchant_benefit"));
				mposBenefitDeatil.put("agency_benefit", StringUtil.getMapValue(record, "agency_benefit"));
				mposBenefitDeatil.put("share_benefit", StringUtil.getMapValue(record, "share_benefit"));
				mposBenefitDeatil.put("return_benefit", StringUtil.getMapValue(record, "return_benefit"));
				mposBenefitDeatil.put("activity_benefit", StringUtil.getMapValue(record, "activity_benefit"));
				mposBenefitDeatil.put("deduct_money", StringUtil.getMapValue(record, "deduct_money"));
			}
			respondMap.put("mposBenefitDeatil", mposBenefitDeatil);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getBenefitCentreMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 分润记录列表（传统POS）
	 */
	@Override
	public R getShareBenefitTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> shareBenefitTraditionalPosList = benefitCentreMapper.getShareBenefitTraditionalPosList(map);
			respondMap.put("shareBenefitTraditionalPosList", shareBenefitTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getShareBenefitTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 分润记录列表（传统POS）add byqh202003
	 */
	@Override
	public R getShareBenefitEposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> shareBenefitTraditionalPosList = benefitCentreMapper.getShareBenefitEposList(map);
			respondMap.put("shareBenefitTraditionalPosList", shareBenefitTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getShareBenefitTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 机器返现记录列表（传统POS）
	 */
	@Override
	public R getMachineBackTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> machineBackTraditionalPosList = benefitCentreMapper.getMachineBackTraditionalPosList(map);
			respondMap.put("machineBackTraditionalPosList", machineBackTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getMachineBackTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 机器返现记录列表（传统POS）add byqh202003
	 */
	@Override
	public R getMachineBackEposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> machineBackTraditionalPosList = benefitCentreMapper.getMachineBackEposList(map);
			respondMap.put("machineBackTraditionalPosList", machineBackTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getMachineBackTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 活动奖励记录列表（传统POS）
	 */
	@Override
	public R getActivityRewardTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> activityRewardTraditionalPosList = benefitCentreMapper.getActivityRewardTraditionalPosList(map);
			respondMap.put("activityRewardTraditionalPosList", activityRewardTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getActivityRewardTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 活动奖励记录列表（传统POS）add byqh202003
	 */
	@Override
	public R getActivityRewardEposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> activityRewardTraditionalPosList = benefitCentreMapper.getActivityRewardEposList(map);
			respondMap.put("activityRewardTraditionalPosList", activityRewardTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getActivityRewardTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 扣除列表（传统POS）
	 */
	@Override
	public R getDeductTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> deductTraditionalPosList = benefitCentreMapper.getDeductTraditionalPosList(map);
			respondMap.put("deductTraditionalPosList", deductTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getDeductTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 扣除列表（传统POS）add byqh202003
	 */
	@Override
	public R getDeductEposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> deductTraditionalPosList = benefitCentreMapper.getDeductEposList(map);
			respondMap.put("deductTraditionalPosList", deductTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getDeductTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 分润记录列表（MPOS）
	 */
	@Override
	public R getShareBenefitMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> shareBenefitMposList = benefitCentreMapper.getShareBenefitMposList(map);
			respondMap.put("shareBenefitMposList", shareBenefitMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getShareBenefitMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 机器返现记录列表（MPOS）
	 */
	@Override
	public R getMachineBackMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> machineBackMposList = benefitCentreMapper.getMachineBackMposList(map);
			respondMap.put("machineBackMposList", machineBackMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getMachineBackMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 活动奖励记录列表（MPOS）
	 */
	@Override
	public R getActivityRewardMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> activityRewardMposList = benefitCentreMapper.getActivityRewardMposList(map);
			respondMap.put("activityRewardMposList", activityRewardMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getActivityRewardMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 扣除列表（MPOS）
	 */
	@Override
	public R getDeductMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取分润记录
			map.put("start_date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			map.put("end_date", TimeUtil.getLastDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> deductMposList = benefitCentreMapper.getDeductMposList(map);
			respondMap.put("deductMposList", deductMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("BenefitCentreServiceImpl -- getDeductMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
