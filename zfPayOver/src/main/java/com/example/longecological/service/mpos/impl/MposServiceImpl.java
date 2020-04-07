package com.example.longecological.service.mpos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MposCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.mpos.MposMapper;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.mpos.MposService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class MposServiceImpl implements MposService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MposServiceImpl.class);

	@Autowired
	private MposMapper mposMapper;
	
	@Autowired
	private VerifyUserService verifyUserService;
	
	/**
	 * 查询MPOS机列表
	 */
	@Override
	public R getMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposList = mposMapper.getMposList(map);
			respondMap.put("mposList", mposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MposServiceImpl -- getMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 修改商户信息
	 */
	@Override
	public R editMposMerInfo(Map<String, Object> map) {
		try {
			//解密成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			/********************校验上传参数************************/
			R checkEditMposMerInfo = this.checkEditMposMerInfo(map);
			if(!Boolean.valueOf(checkEditMposMerInfo.get(R.SUCCESS_TAG).toString())) {
			    return checkEditMposMerInfo;
			}
			
			/********************修改商户信息************************/
			int num = mposMapper.editMposMerInfo(map);
			if(num != 1){
				return R.error(MposCodeConstant.Mpos_INFO_CODE_998996, MposCodeConstant.Mpos_INFO_MSG_998996);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MposServiceImpl -- editMposMerInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	public R checkEditMposMerInfo(Map<String, Object> map){
		//校验MPOS SN
		Map<String, Object> userMpos = mposMapper.getUserMpos(map);
		if(userMpos == null){
			return R.error(MposCodeConstant.Mpos_INFO_CODE_998999, MposCodeConstant.Mpos_INFO_MSG_998999);
		}
		if(!"1".equals(StringUtil.getMapValue(userMpos, "state_status"))){
			return R.error(MposCodeConstant.Mpos_INFO_CODE_998998, MposCodeConstant.Mpos_INFO_MSG_998998);
		}
		//校验修改信息
		if(StringUtil.getMapValue(userMpos, "name").equals(StringUtil.getMapValue(map, "name")) 
				&& StringUtil.getMapValue(userMpos, "tel").equals(StringUtil.getMapValue(map, "tel"))){
			return R.error(MposCodeConstant.Mpos_INFO_CODE_998997, MposCodeConstant.Mpos_INFO_MSG_998997);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}

}
