package com.example.longecological.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.longecological.mapper.merchant.MerchantManageMapper;
import com.example.longecological.mapper.mpos.MposMapper;
import com.example.longecological.mapper.traditionalpos.TraditionalPosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.common.SysParamRateMapper;
import com.example.longecological.service.common.SysParamRateService;
import com.example.longecological.utils.string.StringUtil;

@Service
public class SysParamRateServiceImpl implements SysParamRateService {
	
	@Autowired
	private SysParamRateMapper sysParamRateMapper;

	@Autowired
	private MposMapper mposMapper;

	@Autowired
	private TraditionalPosMapper traditionalPosMapper;

	@Autowired
	private MerchantManageMapper merchantManageMapper;

	/**
	 * 费率列表
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_param_rate_list,
//			fieldKey="#type", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getRateListByType(String type,String pos_type) {
		return sysParamRateMapper.getRateListByType(type,pos_type);
	}

	/**
	 * 传统POS-系统分配参数
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_param_rate_traditionalpos,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysParamRateByTraditionalPos() {
		List<Map<String, Object>> rateList = sysParamRateMapper.getSysParamRateAll();
		List<String> card_settle_price_list = new ArrayList<String>();
		List<String> weixin_settle_price_list = new ArrayList<String>();
		List<String> zhifubao_settle_price_list = new ArrayList<String>();
		List<String> cloud_settle_price_list = new ArrayList<String>();
		List<String> single_profit_rate_list = new ArrayList<String>();
		List<String> cash_back_rate_list = new ArrayList<String>();
		List<String> mer_cap_fee_list = new ArrayList<String>();
		Map<String, Object> sysParamRateMap = new HashMap<String, Object>();
		if(rateList != null && rateList.size() > 0){
			for(Map<String, Object> bean : rateList){
				if("2".equals(StringUtil.getMapValue(bean, "type"))){
					card_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("5".equals(StringUtil.getMapValue(bean, "type"))){
					weixin_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("7".equals(StringUtil.getMapValue(bean, "type"))){
					zhifubao_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("3".equals(StringUtil.getMapValue(bean, "type"))){
					cloud_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("9".equals(StringUtil.getMapValue(bean, "type"))){
					single_profit_rate_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("10".equals(StringUtil.getMapValue(bean, "type"))){
					cash_back_rate_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("11".equals(StringUtil.getMapValue(bean, "type"))){
					mer_cap_fee_list.add(StringUtil.getMapValue(bean, "rate"));
				}
			}
		}
		sysParamRateMap.put("card_settle_price_list", card_settle_price_list);//刷卡结算底价
		sysParamRateMap.put("weixin_settle_price_list", weixin_settle_price_list);//微信结算底价
		sysParamRateMap.put("zhifubao_settle_price_list", zhifubao_settle_price_list);//支付宝结算底价
		sysParamRateMap.put("cloud_settle_price_list", cloud_settle_price_list);//云闪付结算底价
		sysParamRateMap.put("single_profit_rate_list", single_profit_rate_list);//单笔分润比例
		sysParamRateMap.put("cash_back_rate_list", cash_back_rate_list);//返现比例
		sysParamRateMap.put("mer_cap_fee_list", mer_cap_fee_list);//封顶费
		return sysParamRateMap;
	}

	/**
	 * 传统POS-系统分配参数 byqh update byqh 201912
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 */
	@Override
	public Map<String, Object> getSysParamRateByTraditionalPosAgent(Map<String,Object> map) {
		map.put("sn",map.get("sn").toString().split(",")[0]);
		Map<String,Object> tposMap = traditionalPosMapper.getUserTraditionalPosInfoAll(map);

		List<Map<String, Object>> rateList = sysParamRateMapper.getSysParamRateAll();
		List<String> card_settle_price_list = new ArrayList<String>();
		List<String> weixin_settle_price_list = new ArrayList<String>();
		List<String> zhifubao_settle_price_list = new ArrayList<String>();
		List<String> cloud_settle_price_list = new ArrayList<String>();


		List<String> card_settle_price_vip_list = new ArrayList<String>();
		List<String> weixin_settle_price_vip_list = new ArrayList<String>();
		List<String> zhifubao_settle_price_vip_list = new ArrayList<String>();
		List<String> cloud_settle_price_vip_list = new ArrayList<String>();


		List<String> single_profit_rate_list = new ArrayList<String>();
		List<String> cash_back_rate_list = new ArrayList<String>();
		List<String> mer_cap_fee_list = new ArrayList<String>();
		Map<String, Object> sysParamRateMap = new HashMap<String, Object>();
		String policy3Name = merchantManageMapper.getPolicy3Name(StringUtil.getMapValue(map,"sn"));
		if(policy3Name!=null){
			policy3Name = StringUtil.getMapValue(tposMap,"is_reward");
			sysParamRateMap.put("is_reward",policy3Name);
		}
		if(rateList != null && rateList.size() > 0){
			for(Map<String, Object> bean : rateList){
				if("2".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("card_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price<=rate){
						card_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}

					Double settle_price_vip = Double.parseDouble(tposMap.get("card_settle_price_vip").toString());
					Double rate_vip = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price_vip<=rate_vip){
						card_settle_price_vip_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}else if("5".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("weixin_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price<=rate){
						weixin_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}

//					Double settle_price_vip = Double.parseDouble(tposMap.get("weixin_settle_price_vip").toString());
//					Double rate_vip = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
//					if(settle_price_vip<=rate_vip){
//						weixin_settle_price_vip_list.add(StringUtil.getMapValue(bean, "rate"));
//					}
				}else if("7".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("zhifubao_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price<=rate){
						zhifubao_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}

//					Double settle_price_vip = Double.parseDouble(tposMap.get("zhifubao_settle_price_vip").toString());
//					Double rate_vip = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
//					if(settle_price_vip<=rate_vip){
//						zhifubao_settle_price_vip_list.add(StringUtil.getMapValue(bean, "rate"));
//					}
				}else if("3".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("cloud_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price<=rate){
						cloud_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}

//					Double settle_price_vip = Double.parseDouble(tposMap.get("cloud_settle_price_vip").toString());
//					Double rate_vip = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
//					if(settle_price_vip<=rate_vip){
//						cloud_settle_price_vip_list.add(StringUtil.getMapValue(bean, "rate"));
//					}
				}else if("9".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("single_profit_rate").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price>=rate){
						single_profit_rate_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}else if("10".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("cash_back_rate").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price>=rate){
						cash_back_rate_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}else if("11".equals(StringUtil.getMapValue(bean, "type"))){
					Double settle_price = Double.parseDouble(tposMap.get("mer_cap_fee").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(settle_price<=rate){
						mer_cap_fee_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}
			}
		}
		sysParamRateMap.put("card_settle_price_list", card_settle_price_list);//刷卡结算底价
		sysParamRateMap.put("weixin_settle_price_list", weixin_settle_price_list);//微信结算底价
		sysParamRateMap.put("zhifubao_settle_price_list", zhifubao_settle_price_list);//支付宝结算底价
		sysParamRateMap.put("cloud_settle_price_list", cloud_settle_price_list);//云闪付结算底价


		sysParamRateMap.put("card_settle_price_vip_list", card_settle_price_vip_list);//刷卡结算底价
		sysParamRateMap.put("weixin_settle_price_vip_list", weixin_settle_price_vip_list);//微信结算底价
		sysParamRateMap.put("zhifubao_settle_price_vip_list", zhifubao_settle_price_vip_list);//支付宝结算底价
		sysParamRateMap.put("cloud_settle_price_vip_list", cloud_settle_price_vip_list);//云闪付结算底价


		sysParamRateMap.put("single_profit_rate_list", single_profit_rate_list);//单笔分润比例
		sysParamRateMap.put("cash_back_rate_list", cash_back_rate_list);//返现比例
		sysParamRateMap.put("mer_cap_fee_list", mer_cap_fee_list);//封顶费
		return sysParamRateMap;
	}

	/**
	 * Mpos-系统分配参数
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_param_rate_mpos,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysParamRateByMpos() {
		List<Map<String, Object>> rateList = sysParamRateMapper.getSysParamRateAll();
		List<String> card_settle_price_list = new ArrayList<String>();
		List<String> cloud_settle_price_list = new ArrayList<String>();
		List<String> single_profit_rate_list = new ArrayList<String>();
		List<String> cash_back_rate_list = new ArrayList<String>();
		Map<String, Object> sysParamRateMap = new HashMap<String, Object>();
		if(rateList != null && rateList.size() > 0){
			for(Map<String, Object> bean : rateList){
				if("2".equals(StringUtil.getMapValue(bean, "type"))){
					card_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("3".equals(StringUtil.getMapValue(bean, "type"))){
					cloud_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("9".equals(StringUtil.getMapValue(bean, "type"))){
					single_profit_rate_list.add(StringUtil.getMapValue(bean, "rate"));
				}else if("10".equals(StringUtil.getMapValue(bean, "type"))){
					cash_back_rate_list.add(StringUtil.getMapValue(bean, "rate"));
				}
			}
		}
		sysParamRateMap.put("card_settle_price_list", card_settle_price_list);//刷卡结算底价
		sysParamRateMap.put("cloud_settle_price_list", cloud_settle_price_list);//云闪付结算底价
		sysParamRateMap.put("single_profit_rate_list", single_profit_rate_list);//单笔分润比例
		sysParamRateMap.put("cash_back_rate_list", cash_back_rate_list);//返现比例
		return sysParamRateMap;
	}

	/**
	 * Mpos-系统分配参数 byqh
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 */
	@Override
	public Map<String, Object> getSysParamRateByMposAgent(Map<String,Object> map) {
		String sys_user_id = StringUtil.getMapValue(map, "sys_user_id");
		map.put("sn",map.get("sn").toString().split(",")[0]);
		Map<String,Object> mposMap = mposMapper.getUserMposInfoAll(map);

		List<Map<String, Object>> rateList = sysParamRateMapper.getSysParamRateAll();
		List<String> card_settle_price_list = new ArrayList<String>();
		List<String> cloud_settle_price_list = new ArrayList<String>();
		List<String> single_profit_rate_list = new ArrayList<String>();
		List<String> cash_back_rate_list = new ArrayList<String>();
		Map<String, Object> sysParamRateMap = new HashMap<String, Object>();
		String policy3Name = merchantManageMapper.getPolicy3Name(StringUtil.getMapValue(map,"sn"));
		if(policy3Name!=null){
			policy3Name = StringUtil.getMapValue(mposMap,"is_reward");
			sysParamRateMap.put("is_reward",policy3Name);
		}
		if(rateList != null && rateList.size() > 0){
			for(Map<String, Object> bean : rateList){
				if("2".equals(StringUtil.getMapValue(bean, "type"))){
					Double card_settle_price = Double.parseDouble(mposMap.get("card_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(card_settle_price<=rate){
						card_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}else if("3".equals(StringUtil.getMapValue(bean, "type"))){
					Double cloud_settle_price = Double.parseDouble(mposMap.get("cloud_settle_price").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(cloud_settle_price<=rate){
						cloud_settle_price_list.add(StringUtil.getMapValue(bean, "rate"));
					}

				}else if("9".equals(StringUtil.getMapValue(bean, "type"))){
					Double single_profit_rate = Double.parseDouble(mposMap.get("single_profit_rate").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(single_profit_rate>=rate){
						single_profit_rate_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}else if("10".equals(StringUtil.getMapValue(bean, "type"))){
					Double cash_back_rate = Double.parseDouble(mposMap.get("cash_back_rate").toString());
					Double rate = Double.parseDouble(StringUtil.getMapValue(bean, "rate"));
					if(cash_back_rate>=rate){
						cash_back_rate_list.add(StringUtil.getMapValue(bean, "rate"));
					}
				}
			}
		}
		sysParamRateMap.put("card_settle_price_list", card_settle_price_list);//刷卡结算底价
		sysParamRateMap.put("cloud_settle_price_list", cloud_settle_price_list);//云闪付结算底价
		sysParamRateMap.put("single_profit_rate_list", single_profit_rate_list);//单笔分润比例
		sysParamRateMap.put("cash_back_rate_list", cash_back_rate_list);//返现比例
		return sysParamRateMap;
	}

}
