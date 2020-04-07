package com.example.longecological.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserCardInfoMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.user.UserCardInfoService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户银行卡相关
 * @author Administrator
 *
 */
@Service
public class UserCardInfoServiceImpl implements UserCardInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCardInfoServiceImpl.class);
	
	@Autowired
	VerifyUserService verifyUserService;
	
	@Autowired
	UserCardInfoMapper userCardInfoMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	

	/**
	 * 查询用户结算卡列表
	 */
	@Override
	public R getUserCardList(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			List<Map<String, Object>> userCardList = userCardInfoMapper.getUserCardList(map);
			respondMap.put("userCardList", userCardList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- getUserCardList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);

		}
	}


	/**
	 * 设置用户结算卡
	 */
	@Override
	@Transactional
	public R updateUserCard(Map<String, Object> map) {
		//（1）解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		//（2）设置结算卡操作
		return this.updateUserCardOper((Map<String, Object>) map.get("data"));
	}


	/**
	 * 设置结算卡操作
	 * @param dataMap
	 * @return
	 */
	private R updateUserCardOper(Map<String, Object> dataMap) {
		dataMap.put("cre_date", TimeUtil.getDayString());//创建日期
		dataMap.put("cre_time", TimeUtil.getTimeString());//创建时间
		//操作类型：新增
		if(OperParamConstant.USER_CARD_OPER_UPDATE_TYPE_ADD.equals(dataMap.get("user_card_oper").toString())) {
			return this.addUserCard(dataMap);
			//操作类型：删除
		}else if(OperParamConstant.USER_CARD_OPER_UPDATE_TYPE_DEL.equals(dataMap.get("user_card_oper").toString())) {
			return this.delUserCard(dataMap);
			//操作类型：设置默认
		}else if(OperParamConstant.USER_CARD_OPER_UPDATE_TYPE_SET.equals(dataMap.get("user_card_oper").toString())) {
			return this.setUserCard(dataMap);
		}else {
			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
		}
	}


	/**
	 * 添加用户结算卡
	 * @param dataMap
	 * @return
	 */
	private R addUserCard(Map<String, Object> dataMap) {
		try {
			int i=0;
			//（1）必备参数表信息
			R checkMustReslut = ParamValidUtil.checkMustParam(dataMap, OperParamConstant.USER_CARD_MUST_PARAM);
			if(!Boolean.valueOf(checkMustReslut.get(R.SUCCESS_TAG).toString())) {
				return checkMustReslut;
			}
			//（2）银行卡号校验
			if(!RegexUtil.isValidBankCard(dataMap.get("account").toString())) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999773, UserInfoCodeConstant.USER_INFO_MSG_999773);
			}
			//（3）银行卡照片校验
			if(dataMap.get("card_photo").toString().split(",").length!=2) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999772, UserInfoCodeConstant.USER_INFO_MSG_999772);
			}
			//（4）查询用户已经添加的银行卡数量
			Integer cardNum = userCardInfoMapper.getUserCardNum(dataMap.get("sys_user_id").toString());
			if(cardNum>=SysParamConstant.user_card_max_num) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999769,UserInfoCodeConstant.USER_INFO_MSG_999769);
			}
			//（5）实名认证和交易密码校验
			R authPayPassCheckReslut = verifyUserService.checkAuthAndPayPass(dataMap);
			if(!Boolean.valueOf(authPayPassCheckReslut.get(R.SUCCESS_TAG).toString())) {
				return authPayPassCheckReslut;
			}
			dataMap.put("account_name", ((Map<String, Object>)authPayPassCheckReslut.get("data")).get("real_name").toString());
			dataMap.put("id_card", ((Map<String, Object>)authPayPassCheckReslut.get("data")).get("id_card").toString());
			//（6）查询用户添加银行卡是否需要审核（与代理有关）
			Map<String, Object> agentMap = userInfoMapper.getUserAgentInfo(dataMap.get("sys_user_id").toString());
			if(agentMap==null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999770,UserInfoCodeConstant.USER_INFO_MSG_999770);
			}
			//不需要审核
			if(TypeStatusConstant.user_card_status_card_isaudit_0.equals(agentMap.get("card_isaudit").toString())) {
				dataMap.put("status", TypeStatusConstant.user_card_status_09);//审核通过
			}else {
				dataMap.put("status", TypeStatusConstant.user_card_status_00);//待审核
			}
			dataMap.put("card_id", null);//银行卡主键ID
			//（7）添加银行卡的相关操作过程
			//1、如果是添加非默认卡（先看用户是否存在银行卡，若不存在则添加默认银行卡，否则直接添加）
			if(TypeStatusConstant.user_card_is_default_0.equals(dataMap.get("is_default").toString())) {
				//（1）查询用户是否存在默认卡，不存在则设置成默认卡，否则非默认卡
				Integer defaultCardNum = userCardInfoMapper.getDefaultCardNum(dataMap.get("sys_user_id").toString());
				if(defaultCardNum==0) {
					dataMap.put("is_default", TypeStatusConstant.user_card_is_default_1);//默认卡
				}
				//（2）添加保存
				i = userCardInfoMapper.addUserCard(dataMap);
				if(i != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999768,UserInfoCodeConstant.USER_INFO_MSG_999768);
				}
			}else {
				//2、否则添加为默认卡
				//（1）先将默认卡设置为非默认卡
				i = userCardInfoMapper.updateCardToNoDefault(dataMap);
				//（2）添加默认银行卡
				i = userCardInfoMapper.addUserCard(dataMap);
				if(i != 1){//进行回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999768,UserInfoCodeConstant.USER_INFO_MSG_999768);
				}
			}
			//返回结果
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("card_id", dataMap.get("card_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- addUserCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
	/**
	 * 设置用户默认卡和非默认卡
	 * @param dataMap
	 * @return
	 */
	private R setUserCard(Map<String, Object> dataMap) {
		try {
			int i=0;
			//如果编辑成默认银行卡（先将所有地址设置成非默认银行卡，再编辑地址），否则直接编辑银行卡：即编辑银行卡是必须操作
			if(TypeStatusConstant.user_card_is_default_1.equals(dataMap.get("is_default").toString())) {
				//（1）将用户所有地址均设置成非默认地址
				i = userCardInfoMapper.updateCardToNoDefault(dataMap);
			}
			//（2）编辑用户银行卡（默认和非默认方法都通用）
			i = userCardInfoMapper.updateUserCard(dataMap);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998, CommonCodeConstant.COMMON_MSG_999998);
			}
			//返回结果
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("card_id", dataMap.get("card_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- setUserCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 删除用户结算卡
	 * @param dataMap
	 * @return
	 */
	private R delUserCard(Map<String, Object> dataMap) {
		try {
			//（1）实名认证和交易密码校验
			R authPayPassCheckReslut = verifyUserService.checkAuthAndPayPass(dataMap);
			if(!Boolean.valueOf(authPayPassCheckReslut.get(R.SUCCESS_TAG).toString())) {
				return authPayPassCheckReslut;
			}
			int i = userCardInfoMapper.delUserCardById(dataMap);
			if(i != 1){//进行回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998,CommonCodeConstant.COMMON_MSG_999998);
			}
			//返回结果
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("card_id", dataMap.get("card_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- delUserCard方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 查询用户有效的结算卡列表
	 */
	@Override
	public R getUserValidCardList(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			map.put("status", TypeStatusConstant.user_card_status_09);//审核通过
			List<Map<String, Object>> userCardList = userCardInfoMapper.getUserCardList(map);
			respondMap.put("userCardList", userCardList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCardInfoServiceImpl -- getUserCardList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);

		}
	}

	
}
