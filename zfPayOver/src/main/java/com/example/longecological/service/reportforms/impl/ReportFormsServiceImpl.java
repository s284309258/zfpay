package com.example.longecological.service.reportforms.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.reportforms.ReportFormsMapper;
import com.example.longecological.service.reportforms.ReportFormsService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class ReportFormsServiceImpl implements ReportFormsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportFormsServiceImpl.class);
	
	@Autowired
	private ReportFormsMapper reportFormsMapper;

	/**
	 * 报表中心首页记录
	 */
	@Override
	public R getHomePageInfo(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//当前月
			map.put("month", TimeUtil.getDayFormat10());
			//获取传统POS信息
			Map<String, Object> traposDetail = reportFormsMapper.getHomePageInfoByTraditionalPos(map);
			if(traposDetail==null){
				traposDetail = new HashMap<>();
				traposDetail.put("pos_avg","0");
				traposDetail.put("pos_num","0");
				traposDetail.put("performance","0");
				traposDetail.put("act_num","0");
			}
			traposDetail.put("act_num_day",reportFormsMapper.getHomePageInfoTraditionalActNumDay(String.valueOf(map.get("sys_user_id"))));
			respondMap.put("traposDetail", traposDetail);
			//获取MPOS信息
			Map<String, Object> mposDetail = reportFormsMapper.getHomePageInfoByMpos(map);
			if(mposDetail==null){
				mposDetail = new HashMap<>();
				mposDetail.put("pos_avg","0");
				mposDetail.put("pos_num","0");
				mposDetail.put("performance","0");
				mposDetail.put("act_num","0");
			}
			mposDetail.put("act_num_day",reportFormsMapper.getHomePageInfoMPOSActNumDay(String.valueOf(map.get("sys_user_id"))));
			respondMap.put("mposDetail", mposDetail);
			//获取EPOS信息
			Map<String, Object> eposDetail = reportFormsMapper.getHomePageInfoByEpos(map);
			if(eposDetail==null){
				eposDetail = new HashMap<>();
				eposDetail.put("pos_avg","0");
				eposDetail.put("pos_num","0");
				eposDetail.put("performance","0");
				eposDetail.put("act_num","0");
			}
			String act_num_day = reportFormsMapper.getHomePageInfoEposActNumDay(String.valueOf(map.get("sys_user_id")));
			if(act_num_day==null){act_num_day="0";}
			eposDetail.put("act_num_day",act_num_day);
			respondMap.put("eposDetail", eposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getHomePageInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每天详情（传统POS）
	 */
	@Override
	public R getDayAgencyTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			StringBuffer cre_month = new StringBuffer(String.valueOf(map.get("date")));
			if(cre_month.length()==6){
				cre_month.insert(4,"0");
				cre_month.insert(6,"0");
			}else if (cre_month.length()==7){
				cre_month.insert(4,"0");
			}
			map.put("date",cre_month.toString());
			Map<String, Object> dayAgencyTraditionalPosDetail = reportFormsMapper.getDayAgencyTraditionalPosDetail(map);
			if(dayAgencyTraditionalPosDetail == null){
				dayAgencyTraditionalPosDetail = new HashMap<>();
				dayAgencyTraditionalPosDetail.put("performance", "0.00");
				dayAgencyTraditionalPosDetail.put("user_num", "0");
				dayAgencyTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("dayAgencyTraditionalPosDetail", dayAgencyTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayAgencyTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 代理每天详情（传统POS）add byqh202003
	 */
	@Override
	public R getDayAgencyEposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> dayAgencyTraditionalPosDetail = reportFormsMapper.getDayAgencyEposDetail(map);
			if(dayAgencyTraditionalPosDetail == null){
				dayAgencyTraditionalPosDetail = new HashMap<>();
				dayAgencyTraditionalPosDetail.put("performance", "0.00");
				dayAgencyTraditionalPosDetail.put("user_num", "0");
				dayAgencyTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("dayAgencyTraditionalPosDetail", dayAgencyTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayAgencyTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每月详情（传统POS）
	 */
	@Override
	public R getMonthAgencyTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("date"))));
			map.put("date",cre_month);
			Map<String, Object> monthAgencyTraditionalPosDetail = reportFormsMapper.getMonthAgencyTraditionalPosDetail(map);
			if(monthAgencyTraditionalPosDetail == null){
				monthAgencyTraditionalPosDetail = new HashMap<>();
				monthAgencyTraditionalPosDetail.put("performance", "0.00");
				monthAgencyTraditionalPosDetail.put("user_num", "0");
				monthAgencyTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("monthAgencyTraditionalPosDetail", monthAgencyTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthAgencyTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 代理每月详情（传统POS）add byqh20200310
	 */
	@Override
	public R getMonthAgencyEposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> monthAgencyTraditionalPosDetail = reportFormsMapper.getMonthAgencyEposDetail(map);
			if(monthAgencyTraditionalPosDetail == null){
				monthAgencyTraditionalPosDetail = new HashMap<>();
				monthAgencyTraditionalPosDetail.put("performance", "0.00");
				monthAgencyTraditionalPosDetail.put("user_num", "0");
				monthAgencyTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("monthAgencyTraditionalPosDetail", monthAgencyTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthAgencyTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每天详情（传统POS）
	 */
	@Override
	public R getDayMerchantTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			StringBuffer cre_month = new StringBuffer(String.valueOf(map.get("date")));
			if(cre_month.length()==6){
				cre_month.insert(4,"0");
				cre_month.insert(6,"0");
			}else if (cre_month.length()==7){
				cre_month.insert(4,"0");
			}
			map.put("date",cre_month.toString());
			Map<String, Object> dayMerchantTraditionalPosDetail = reportFormsMapper.getDayMerchantTraditionalPosDetail(map);
			if(dayMerchantTraditionalPosDetail == null){
				dayMerchantTraditionalPosDetail = new HashMap<>();
				dayMerchantTraditionalPosDetail.put("performance", "0.00");
				dayMerchantTraditionalPosDetail.put("user_num", "0");
				dayMerchantTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("dayMerchantTraditionalPosDetail", dayMerchantTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayMerchantTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 商户每天详情（传统POS）add byqh202003
	 */
	@Override
	public R getDayMerchantEposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> dayMerchantTraditionalPosDetail = reportFormsMapper.getDayMerchantEposDetail(map);
			if(dayMerchantTraditionalPosDetail == null){
				dayMerchantTraditionalPosDetail = new HashMap<>();
				dayMerchantTraditionalPosDetail.put("performance", "0.00");
				dayMerchantTraditionalPosDetail.put("user_num", "0");
				dayMerchantTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("dayMerchantTraditionalPosDetail", dayMerchantTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayMerchantTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每月详情（传统POS）
	 */
	@Override
	public R getMonthMerchantTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("date"))));
			map.put("date",cre_month);
			Map<String, Object> monthMerchantTraditionalPosDetail = reportFormsMapper.getMonthMerchantTraditionalPosDetail(map);
			if(monthMerchantTraditionalPosDetail == null){
				monthMerchantTraditionalPosDetail = new HashMap<>();
				monthMerchantTraditionalPosDetail.put("performance", "0.00");
				monthMerchantTraditionalPosDetail.put("user_num", "0");
				monthMerchantTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("monthMerchantTraditionalPosDetail", monthMerchantTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthMerchantTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 商户每月详情（传统POS）add byqh202003
	 */
	@Override
	public R getMonthMerchantEposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> monthMerchantTraditionalPosDetail = reportFormsMapper.getMonthMerchantEposDetail(map);
			if(monthMerchantTraditionalPosDetail == null){
				monthMerchantTraditionalPosDetail = new HashMap<>();
				monthMerchantTraditionalPosDetail.put("performance", "0.00");
				monthMerchantTraditionalPosDetail.put("user_num", "0");
				monthMerchantTraditionalPosDetail.put("act_num", "0");
			}
			respondMap.put("monthMerchantTraditionalPosDetail", monthMerchantTraditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthMerchantTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每天业绩走势（传统POS）
	 */
	@Override
	public R getDayAgencyTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> dayAgencyTraditionalPosList = reportFormsMapper.getDayAgencyTraditionalPosList(map);
			respondMap.put("dayAgencyTraditionalPosList", dayAgencyTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayAgencyTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每月业绩走势（传统POS）
	 */
	@Override
	public R getMonthAgencyTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			map.put("date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> monthAgencyTraditionalPosList = reportFormsMapper.getMonthAgencyTraditionalPosList(map);
			respondMap.put("monthAgencyTraditionalPosList", monthAgencyTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthAgencyTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每天业绩走势（传统POS）
	 */
	@Override
	public R getDayMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> dayMerchantTraditionalPosList = reportFormsMapper.getDayMerchantTraditionalPosList(map);
			respondMap.put("dayMerchantTraditionalPosList", dayMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每月业绩走势（传统POS）
	 */
	@Override
	public R getMonthMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			map.put("date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> monthMerchantTraditionalPosList = reportFormsMapper.getMonthMerchantTraditionalPosList(map);
			respondMap.put("monthMerchantTraditionalPosList", monthMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 代理每天详情（MPOS）
	 */
	@Override
	public R getDayAgencyMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			StringBuffer cre_month = new StringBuffer(String.valueOf(map.get("date")));
			if(cre_month.length()==6){
				cre_month.insert(4,"0");
				cre_month.insert(6,"0");
			}else if (cre_month.length()==7){
				cre_month.insert(4,"0");
			}
			map.put("date",cre_month.toString());
			Map<String, Object> dayAgencyMposDetail = reportFormsMapper.getDayAgencyMposDetail(map);
			if(dayAgencyMposDetail == null){
				dayAgencyMposDetail = new HashMap<>();
				dayAgencyMposDetail.put("performance", "0.00");
				dayAgencyMposDetail.put("user_num", "0");
				dayAgencyMposDetail.put("act_num", "0");
			}
			respondMap.put("dayAgencyMposDetail", dayAgencyMposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayAgencyMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每月详情（MPOS）
	 */
	@Override
	public R getMonthAgencyMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("date"))));
			map.put("date",cre_month);
			Map<String, Object> monthAgencyMposDetail = reportFormsMapper.getMonthAgencyMposDetail(map);
			if(monthAgencyMposDetail == null){
				monthAgencyMposDetail = new HashMap<>();
				monthAgencyMposDetail.put("performance", "0.00");
				monthAgencyMposDetail.put("user_num", "0");
				monthAgencyMposDetail.put("act_num", "0");
			}
			respondMap.put("monthAgencyMposDetail", monthAgencyMposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthAgencyMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每天详情（MPOS）
	 */
	@Override
	public R getDayMerchantMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			StringBuffer cre_month = new StringBuffer(String.valueOf(map.get("date")));
			if(cre_month.length()==6){
				cre_month.insert(4,"0");
				cre_month.insert(6,"0");
			}else if (cre_month.length()==7){
				cre_month.insert(4,"0");
			}
			map.put("date",cre_month.toString());
			Map<String, Object> dayMerchantMposDetail = reportFormsMapper.getDayMerchantMposDetail(map);
			if(dayMerchantMposDetail == null){
				dayMerchantMposDetail = new HashMap<>();
				dayMerchantMposDetail.put("performance", "0.00");
				dayMerchantMposDetail.put("user_num", "0");
				dayMerchantMposDetail.put("act_num", "0");
			}
			respondMap.put("dayMerchantMposDetail", dayMerchantMposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayMerchantMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每月详情（MPOS）
	 */
	@Override
	public R getMonthMerchantMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("date"))));
			map.put("date",cre_month);
			Map<String, Object> monthMerchantMposDetail = reportFormsMapper.getMonthMerchantMposDetail(map);
			if(monthMerchantMposDetail == null){
				monthMerchantMposDetail = new HashMap<>();
				monthMerchantMposDetail.put("performance", "0.00");
				monthMerchantMposDetail.put("user_num", "0");
				monthMerchantMposDetail.put("act_num", "0");
			}
			respondMap.put("monthMerchantMposDetail", monthMerchantMposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthMerchantMposDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每天业绩走势（MPOS）
	 */
	@Override
	public R getDayAgencyMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> dayAgencyMposList = reportFormsMapper.getDayAgencyMposList(map);
			respondMap.put("dayAgencyMposList", dayAgencyMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayAgencyMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 代理每月业绩走势（MPOS）
	 */
	@Override
	public R getMonthAgencyMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			map.put("date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> monthAgencyMposList = reportFormsMapper.getMonthAgencyMposList(map);
			respondMap.put("monthAgencyMposList", monthAgencyMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthAgencyMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每天业绩走势（MPOS）
	 */
	@Override
	public R getDayMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> dayMerchantMposList = reportFormsMapper.getDayMerchantMposList(map);
			respondMap.put("dayMerchantMposList", dayMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getDayMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户每月业绩走势（MPOS）
	 */
	@Override
	public R getMonthMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			map.put("date", TimeUtil.getFirstDayOfMonth(StringUtil.getMapValue(map, "date")));
			List<Map<String, Object>> monthMerchantMposList = reportFormsMapper.getMonthMerchantMposList(map);
			respondMap.put("monthMerchantMposList", monthMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("ReportFormsServiceImpl -- getMonthMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
}
