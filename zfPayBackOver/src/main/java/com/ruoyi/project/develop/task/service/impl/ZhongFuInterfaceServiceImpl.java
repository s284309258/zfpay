package com.ruoyi.project.develop.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.ZhongFuInterfaceCodeConstant;
import com.ruoyi.common.utils.encryption.MD5Utils;
import com.ruoyi.common.utils.exception.ExceptionUtil;
import com.ruoyi.common.utils.interfaces.HttpUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.service.ZhongFuInterfaceService;

import net.sf.json.JSONArray;


@Service
public class ZhongFuInterfaceServiceImpl implements ZhongFuInterfaceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZhongFuInterfaceServiceImpl.class);

	/**
	 * 代理报备接口 7001
	 * agentAccount 代理账号
	 * details 报备代理信息
	 */
	@Override
	public R requestType7001(String agentAccount, JSONArray details, String appKey) {
		 try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 requestData.put("details", details);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7001);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7001 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("代理报备异常");
		}
	}

	/**
	 * 交易状态查询接口 7002
	 * @param agentAccount 代理账号
	 * @param transType 交易类型 1刷卡，2快捷支付，3微信，4支付宝，5银联二维码
	 * @param deviceType 设备类型 1 传统pos，0 MPOS
	 * @param startTime 查询开始时间 yyyyMMddHHmmss
	 * @param endTime 查询结束时间 yyyyMMddHHmmss
	 * @param pageNum 页数
	 * @param appKey 加签秘钥
	 * @return
	 */
	@Override
	public R requestType7002(String agentAccount, String transType, String deviceType, String startTime, String endTime,
			String pageNum, String appKey) {
		try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 requestData.put("transType", transType);
			 requestData.put("deviceType", deviceType);
			 requestData.put("startTime", startTime);
			 requestData.put("endTime", endTime);
			 requestData.put("pageNum", pageNum);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7002);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7002 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("获取信息异常");
		}
	}

	/**
	 * 代理信息参考号查询结算状态接口 7003
	 * @param agentAccount 代理账号
	 * @param SN 设备号
	 * @param merId 商户号
	 * @param tranRefCode 参考号
	 * @param orderDate 订单日期 yyyy-MM-dd
	 * @param appKey 加签秘钥
	 * @return
	 */
	@Override
	public R requestType7003(String agentAccount, String SN, String merId, String tranRefCode, String orderDate,
			String appKey) {
		try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 if(SN != null)
				 requestData.put("SN", SN);
			 requestData.put("merId", merId);
			 requestData.put("tranRefCode", tranRefCode);
			 requestData.put("orderDate", orderDate);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7003);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7003 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("获取信息异常");
		}
	}

	/**
	 * 代理信息查询政策信息接口 7004
	 * @param agentAccount 代理账号
	 * @param machineType 设备类型 02-传统POS 01-MPOS
	 * @param pageNum 页数
	 * @param appKey 加签秘钥
	 * @return
	 */
	@Override
	public R requestType7004(String agentAccount, String machineType, String pageNum, String appKey) {
		try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 requestData.put("machineType", machineType);
			 requestData.put("pageNum", pageNum);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7004);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7004 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("获取信息异常");
		}
	}

	/**
	 * 代理信息查询返现信息接口 7005
	 * @param agentAccount 代理账号
	 * @param queryDate 查询日期 yyyy-MM-dd
	 * @param merCode 商户号
	 * @param policyCode 政策编码
	 * @param machineType 设备类型 02-传统POS 01-MPOS
	 * @param pageNum 页数
	 * @param appKey 加签秘钥
	 * @return
	 */
	@Override
	public R requestType7005(String agentAccount, String queryDate, String merCode, String policyCode,
			String machineType, String pageNum, String appKey) {
		try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 requestData.put("queryDate", queryDate);
			 if(merCode != null)
				 requestData.put("merCode", merCode);
			 requestData.put("policyCode", policyCode);
			 requestData.put("machineType", machineType);
			 requestData.put("pageNum", pageNum);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7005);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7005 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("获取信息异常");
		}
	}

	/**
	 * 代理信息查询设备激活信息接口 7006
	 * @param agentAccount 代理账号
	 * @param SN 设备号
	 * @param startTime 查询开始时间 yyyyMMddHHmmss
	 * @param endTime 查询结束时间 yyyyMMddHHmmss
	 * @param machineType 设备类型 02-传统POS 01-MPOS
	 * @param pageNum 页数
	 * @param appKey 加签秘钥
	 * @return
	 */
	@Override
	public R requestType7006(String agentAccount, String SN, String startTime, String endTime, String machineType,
			String pageNum, String appKey) {
		try {
			 JSONObject requestData = new JSONObject();
			 requestData.put("agentAccount", agentAccount);
			 if(SN != null)
				 requestData.put("SN", SN);
			 requestData.put("startTime", startTime);
			 requestData.put("endTime", endTime);
			 requestData.put("machineType", machineType);
			 requestData.put("pageNum", pageNum);
			 String dataSign = MD5Utils.MD5Encode(appKey+requestData.toJSONString());
			 JSONObject param = new JSONObject();
			 param.put("appId", agentAccount);
			 param.put("requestType", ZhongFuInterfaceCodeConstant.request_type_7006);
			 param.put("requestData", requestData);
			 param.put("dataSign", dataSign);
			 String result = HttpUtils.sendHttpPostRequestJson(ZhongFuInterfaceCodeConstant.request_url, param, false);
			 LOGGER.info("参数信息：" + param.toJSONString());
			 LOGGER.info("接口返回信息：" + result);
			 JSONObject resultJson = JSONObject.parseObject(result);
			 if("2000".equals(resultJson.getString("code"))){
				 return R.ok(resultJson.getString("msg"), resultJson.get("data"));
			 }else{
				 return R.error(resultJson.getString("code"), resultJson.getString("msg"));
			 }
		} catch (Exception e) {
			LOGGER.error("zhongFuInterfaceServiceImpl -- requestType7006 接口信息获取异常：" + ExceptionUtil.getExceptionMessage(e));
			return R.error("获取信息异常");
		}
	}

}
