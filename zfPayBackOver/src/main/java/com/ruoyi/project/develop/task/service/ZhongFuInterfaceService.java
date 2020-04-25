package com.ruoyi.project.develop.task.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.develop.common.domain.R;

import java.util.Map;

/**
 * 中付接口对接
 * @author i
 *
 */
public interface ZhongFuInterfaceService {

	/**
	 * 代理报备接口 7001
	 * @param agentAccount 代理账号
	 * @param jsonArray 报备代理信息
	 * @param appKey 加签秘钥
	 * @return
	 */
	public R requestType7001(String agentAccount, net.sf.json.JSONArray jsonArray, String appKey);
	
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
	public R requestType7002(String agentAccount, String transType, String deviceType, String startTime, String endTime, String pageNum, String appKey);
	
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
	public R requestType7003(String agentAccount, String SN, String merId, String tranRefCode, String orderDate, String appKey);
	
	/**
	 * 代理信息查询政策信息接口 7004
	 * @param agentAccount 代理账号
	 * @param machineType 设备类型 02-传统POS 01-MPOS
	 * @param pageNum 页数
	 * @param appKey 加签秘钥
	 * @return
	 */
	public R requestType7004(String agentAccount, String machineType, String pageNum, String appKey);
	
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
	public R requestType7005(String agentAccount, String queryDate, String merCode, String policyCode, String machineType, String pageNum, String appKey);
	
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
	public R requestType7006(String agentAccount, String SN, String startTime, String endTime, String machineType, String pageNum, String appKey);


	public R requestType7007(String agentAccount, JSONObject requestData, String appKey);

	public R requestType7008(String agentAccount, JSONObject requestData, String appKey);
}
