package com.example.longecological.service.zfback.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.zfback.ZhongFuBackMapper;
import com.example.longecological.service.zfback.ZhongFuBackService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class ZhongFuBackServiceImpl implements ZhongFuBackService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZhongFuBackServiceImpl.class);
	
	@Autowired
	private ZhongFuBackMapper zhongFuBackMapper;

	@Override
	@Transactional
	public String merchantIntoCallback(Map<String, Object> map) {
		int num = 0;
		try{
			LOGGER.info("开始处理中付进件回调："+map.toString());
			List<Map<String, Object>> terminals = (List<Map<String, Object>>) JSONArray.parse(map.get("terminals").toString());
			if(terminals!=null && terminals.size()>0){
				String user_id = zhongFuBackMapper.getMachineIdUser(StringUtil.getMapValue(terminals.get(0), "machineID"));
				//保存审核记录
				Map<String, Object> record = new HashMap<>();
				record.put("install_id", "");
				record.put("user_id", user_id);
				record.put("biz_code", StringUtil.getMapValue(map, "bizCode"));
				record.put("biz_msg", StringUtil.getMapValue(map, "bizMsg"));
				record.put("merchant_name", StringUtil.getMapValue(map, "merchantName"));
				record.put("mer_code", StringUtil.getMapValue(map, "merCode"));
				record.put("agent_id", StringUtil.getMapValue(map, "agentID"));
				record.put("settle_flag", StringUtil.getMapValue(map, "settleFlag"));
				record.put("source", StringUtil.getMapValue(map, "source"));
				record.put("sdk_push_key", StringUtil.getMapValue(map, "SDKPushKey"));
				record.put("cre_date", TimeUtil.getDayString());
				record.put("cre_time", TimeUtil.getTimeString());
				num = zhongFuBackMapper.insertUserTraditionalPosInstall(record);
				if(num != 1){
					throw new Exception("装机记录保存异常");
				}
				
				//保存设备信息
				for(Map<String, Object> terminal : terminals){
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("install_id", StringUtil.getMapValue(record, "install_id"));
					param.put("terminal", StringUtil.getMapValue(terminal, "terminal"));
					param.put("machine_id", StringUtil.getMapValue(terminal, "machineID"));
					param.put("sim_card", StringUtil.getMapValue(terminal, "simCard"));
					param.put("is_take_machi", StringUtil.getMapValue(terminal, "isTakeMachi"));
					num = zhongFuBackMapper.insertUserTraditionalPosInstallDetail(param);
					if(num != 1){
						throw new Exception("装机设备记录保存异常");
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("中付回调处理异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "fail";
		}
		return "success";
	}

	@Override
	public R getTraditionalPosInstallList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosInstallList = zhongFuBackMapper.getTraditionalPosInstallList(map);
			respondMap.put("traditionalPosInstallList", traditionalPosInstallList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosInstallList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	@Override
	public R getTraditionalPosInstallDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> traditionalPosInstallDetail = zhongFuBackMapper.getTraditionalPosInstallDetail(map);
			respondMap.put("traditionalPosInstallDetail", traditionalPosInstallDetail);
			List<Map<String, Object>> terminalList = zhongFuBackMapper.getTerminalList(map);
			respondMap.put("terminalList", terminalList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosInstallList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
