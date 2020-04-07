package com.example.longecological.service.traditionalpos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.constant.msgcode.TraditionalPosCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.traditionalpos.TraditionalPosMapper;
import com.example.longecological.service.traditionalpos.TraditionalPosService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class TraditionalPosServiceImpl implements TraditionalPosService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TraditionalPosServiceImpl.class);
	
	@Autowired
	private TraditionalPosMapper traditionalPosMapper;

	/**
	 * 查询需要申请扫码支付的传统POS机列表
	 */
	@Override
	public R getScanTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> scanTraditionalPosList = traditionalPosMapper.getScanTraditionalPosList(map);
			respondMap.put("scanTraditionalPosList", scanTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("TraditionalPosServiceImpl -- getScanTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 提交申请扫码支付记录
	 */
	@Override
	@Transactional
	public R addApplyScanRecord(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//校验上传的参数信息
			R checkAddApplyScanRecord = this.checkAddApplyScanRecord(map);
			if(!Boolean.valueOf(checkAddApplyScanRecord.get(R.SUCCESS_TAG).toString())) {
			    return checkAddApplyScanRecord;
			}
			/********************开始处理申请记录操作***************************/
			//插入最新的分配记录
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			int num1 = traditionalPosMapper.addApplyScanRecord(map);
			if(num1 != Integer.parseInt(StringUtil.getMapValue(map, "sn_length"))){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996994, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996994);
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("TraditionalPosServiceImpl -- addApplyScanRecord方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	private R checkAddApplyScanRecord(Map<String, Object> map){
		String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
		//校验是否上传需要分配的POS机
		if(sn_list.length < 1){
			return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996999, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996999);
		}
		//校验营业执照
		if("".equals(StringUtil.getMapValue(map, "business_license"))){
			return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996998, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996998);
		}
		//校验店铺内景
		if("".equals(StringUtil.getMapValue(map, "store_interior"))){
			return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996997, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996997);
		}
		//校验店铺门头
		if("".equals(StringUtil.getMapValue(map, "shop_head"))){
			return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996996, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996996);
		}
		//校验收银台
		if("".equals(StringUtil.getMapValue(map, "cashier_desk"))){
			return R.error(TraditionalPosCodeConstant.TraditionalPos_INFO_CODE_996995, TraditionalPosCodeConstant.TraditionalPos_INFO_MSG_996995);
		}
		
		//上传数量
		map.put("sn_length", sn_list.length);
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

	/**
	 * 查询扫码支付申请记录
	 */
	@Override
	public R getApplyScanRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> applyScanRecordList = traditionalPosMapper.getApplyScanRecordList(map);
			respondMap.put("applyScanRecordList", applyScanRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("TraditionalPosServiceImpl -- getApplyScanRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
