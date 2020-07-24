package com.example.longecological.service.ratesmanage.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.CreditCardRatesApplyCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.ratesmanage.CreditCardRatesApplyMapper;
import com.example.longecological.service.common.SysParamRateService;
import com.example.longecological.service.ratesmanage.CreditCardRatesApplyService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class CreditCardRatesApplyServiceImpl implements CreditCardRatesApplyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardRatesApplyServiceImpl.class);
	
	@Autowired
	private CreditCardRatesApplyMapper creditCardRatesApplyMapper;
	
	@Autowired
	private SysParamRateService sysParamRateService;
	
	/**
	 * 查询申请费率的POS机列表（传统POS）
	 */
	@Override
	public R getApplyRateTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> applyRateTraditionalPosList = creditCardRatesApplyMapper.getApplyRateTraditionalPosList(map);;
			respondMap.put("applyRateTraditionalPosList", applyRateTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- getApplyRateTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询申请费率的POS机列表（MPOS）
	 */
	@Override
	public R getApplyRateMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> applyRateMposList = creditCardRatesApplyMapper.getApplyRateMposList(map);;
			respondMap.put("applyRateMposList", applyRateMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- getApplyRateMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询刷卡费率列表
	 */
	@Override
	public R getCreditCardRateList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> creditCardRateList = sysParamRateService.getRateListByType("1",String.valueOf(map.get("pos_type")));
			respondMap.put("creditCardRateList", creditCardRateList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- getCreditCardRateList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 提交费率申请记录（传统POS）
	 */
	@Override
	@Transactional
	public R addApplyRateTraditionalPos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAddApplyRateTraditionalPos = this.checkAddApplyRateTraditionalPos(map);
			if(!Boolean.valueOf(checkAddApplyRateTraditionalPos.get(R.SUCCESS_TAG).toString())) {
			    return checkAddApplyRateTraditionalPos;
			}
			/********************开始处理申请记录操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int cnt = creditCardRatesApplyMapper.getApplyRateRecord(map);
			if(cnt>0){
				return R.error("994996","有未处理的费率申请,不能重复申请");
			}
			int num1 = creditCardRatesApplyMapper.addApplyRateTraditionalPos(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994998, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994998);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- addApplyRateTraditionalPos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAddApplyRateTraditionalPos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要分配的POS机
		if(sn_list.length < 1){
			return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994999, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994999);
		}
		if(new BigDecimal(StringUtil.getMapValue(map, "credit_card_rate")).compareTo(new BigDecimal(0)) <= 0){
			return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994997, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994997);
		}
		
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}
	
	/**
	 * 提交费率申请记录（MPOS）
	 */
	@Override
	@Transactional
	public R addApplyRateMpos(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAddApplyRateMpos = this.checkAddApplyRateMpos(map);
			if(!Boolean.valueOf(checkAddApplyRateMpos.get(R.SUCCESS_TAG).toString())) {
			    return checkAddApplyRateMpos;
			}
			/********************开始处理申请记录操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int cnt = creditCardRatesApplyMapper.getApplyRateRecord(map);
			if(cnt>0){
				return R.error("994996","有未处理的费率申请,不能重复申请");
			}
			int num1 = creditCardRatesApplyMapper.addApplyRateMpos(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994998, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994998);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- addApplyRateMpos方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAddApplyRateMpos(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要分配的POS机
		if(sn_list.length < 1){
			return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994999, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994999);
		}
		if(new BigDecimal(StringUtil.getMapValue(map, "credit_card_rate")).compareTo(new BigDecimal(0)) <= 0){
			return R.error(CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_CODE_994997, CreditCardRatesApplyCodeConstant.CreditCardRatesApply_INFO_MSG_994997);
		}
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/**
	 * 查询费率申请记录（传统POS）
	 */
	@Override
	public R getApplyRateTraditionalPosRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> applyRateTraditionalPosRecordList = creditCardRatesApplyMapper.getApplyRateTraditionalPosRecordList(map);;
			respondMap.put("applyRateTraditionalPosRecordList", applyRateTraditionalPosRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- getApplyRateTraditionalPosRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询费率申请记录（MPOS）
	 */
	@Override
	public R getApplyRateMposRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> applyRateMposRecordList = creditCardRatesApplyMapper.getApplyRateMposRecordList(map);;
			respondMap.put("applyRateMposRecordList", applyRateMposRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("CreditCardRatesApplyServiceImpl -- getApplyRateMposRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
}
