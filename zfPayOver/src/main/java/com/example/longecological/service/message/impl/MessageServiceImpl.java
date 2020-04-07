package com.example.longecological.service.message.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.message.MessageMapper;
import com.example.longecological.service.message.MessageService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;

@Service
public class MessageServiceImpl implements MessageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private MessageMapper messageMapper;
	
	/**
	 * 用户通知记录列表
	 */
	@Override
	public R getMessageRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取传统POS信息
			List<Map<String, Object>> messageRecordList = messageMapper.getMessageRecordList(map);
			respondMap.put("messageRecordList", messageRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MessageServiceImpl -- getMessageRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 用户通知记录详情
	 */
	@Override
	public R getMessageRecordDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//获取传统POS信息
			Map<String, Object> messageRecord = messageMapper.getMessageRecordDetail(map);
			respondMap.put("messageRecord", messageRecord);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MessageServiceImpl -- getMessageRecordDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
