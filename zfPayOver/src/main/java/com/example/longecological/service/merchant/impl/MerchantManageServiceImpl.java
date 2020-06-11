package com.example.longecological.service.merchant.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.longecological.mapper.machines.MachinesManageMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MerchantManageCodeConstant;
import com.example.longecological.constant.msgcode.OnlineActivityCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.merchant.MerchantManageMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.machines.impl.MachinesManageServiceImpl;
import com.example.longecological.service.merchant.MerchantManageService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class MerchantManageServiceImpl implements MerchantManageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantManageServiceImpl.class);
	
	@Autowired
	private MerchantManageMapper merchantManageMapper;
	
	@Autowired
	private UserInfoCacheService userInfoCacheService;

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 商户管理汇总列表(传统POS)
	 */
	@Override
	public R getSummaryTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//所有商户
			int all_merchant = merchantManageMapper.getAllMerchantTraditionalPosNum(map);
			//优质商户
			map.put("month", TimeUtil.getDayFormat10());
			int excellent_merchant = merchantManageMapper.getExcellentMerchantTraditionalPosNum(map);
			//活跃商户
			map.put("start_date", TimeUtil.getDateAddByDate(-30));
			map.put("end_date", TimeUtil.getDayString());
			int active_merchant = merchantManageMapper.getActiveMerchantTraditionalPosNum(map);
			//休眠商户
			map.put("start_date", TimeUtil.getDateAddByDate(-60));
			map.put("end_date", TimeUtil.getDayString());
			int dormant_merchant = merchantManageMapper.getDormantMerchantTraditionalPosNum(map);
			//达标商户数量
			int standard_merchant = merchantManageMapper.getStandardMerchantTraditionalPosNum(map);
			respondMap.put("all_merchant", all_merchant);
			respondMap.put("excellent_merchant", excellent_merchant);
			respondMap.put("active_merchant", active_merchant);
			respondMap.put("dormant_merchant", dormant_merchant);
			respondMap.put("standard_merchant",standard_merchant);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getSummaryTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 商户管理汇总列表(MPOS)
	 */
	@Override
	public R getSummaryMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//所有商户
			int all_merchant = merchantManageMapper.getAllMerchantMposNum(map);
			//优质商户
			map.put("month", TimeUtil.getDayFormat10());
			int excellent_merchant = merchantManageMapper.getExcellentMerchantMposNum(map);
			//活跃商户
			map.put("start_date", TimeUtil.getDateAddByDate(-30));
			map.put("end_date", TimeUtil.getDayString());
			int active_merchant = merchantManageMapper.getActiveMerchantMposNum(map);
			//休眠商户
			map.put("start_date", TimeUtil.getDateAddByDate(-60));
			map.put("end_date", TimeUtil.getDayString());
			int dormant_merchant = merchantManageMapper.getDormantMerchantMposNum(map);
			//达标商户数量
			int standard_merchant = merchantManageMapper.getStandardMerchantMPosNum(map);
			respondMap.put("all_merchant", all_merchant);
			respondMap.put("excellent_merchant", excellent_merchant);
			respondMap.put("active_merchant", active_merchant);
			respondMap.put("dormant_merchant", dormant_merchant);
			respondMap.put("standard_merchant", standard_merchant);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getSummaryMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 全部商户列表查询（传统POS）
	 */
	@Override
	public R getAllMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//所有商户
			List<Map<String, Object>> allMerchantTraditionalPosList = merchantManageMapper.getAllMerchantTraditionalPosList(map);
			respondMap.put("allMerchantTraditionalPosList", allMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getAllMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 更新商户姓名和电话 add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R updateMerchantNameAndTel(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			merchantManageMapper.updateMerchantNameAndTelTPOS(map);
            merchantManageMapper.updateMerchantNameAndTelMPOS(map);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- updateMerchantNameAndTel方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 优质商户列表查询（传统POS）
	 */
	@Override
	public R getExcellentMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//优质商户
			map.put("month", TimeUtil.getDayFormat10());
			List<Map<String, Object>> excellentMerchantTraditionalPosList = merchantManageMapper.getExcellentMerchantTraditionalPosList(map);
			respondMap.put("excellentMerchantTraditionalPosList", excellentMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getExcellentMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 活跃商户列表查询（传统POS）
	 */
	@Override
	public R getActiveMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//活跃商户
			map.put("start_date", TimeUtil.getDateAddByDate(-30));
			map.put("end_date", TimeUtil.getDayString());
			List<Map<String, Object>> activeMerchantTraditionalPosList = merchantManageMapper.getActiveMerchantTraditionalPosList(map);
			respondMap.put("activeMerchantTraditionalPosList", activeMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getActiveMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 休眠商户列表查询（传统POS）
	 */
	@Override
	public R getDormantMerchantTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//休眠商户
			map.put("start_date", TimeUtil.getDateAddByDate(-60));
			map.put("end_date", TimeUtil.getDayString());
			List<Map<String, Object>> dormantMerchantTraditionalPosList = merchantManageMapper.getDormantMerchantTraditionalPosList(map);
			respondMap.put("dormantMerchantTraditionalPosList", dormantMerchantTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getDormantMerchantTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 全部商户列表查询（MPOS）
	 */
	@Override
	public R getAllMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//所有商户
			List<Map<String, Object>> allMerchantMposList = merchantManageMapper.getAllMerchantMposList(map);
			respondMap.put("allMerchantMposList", allMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getAllMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 优质商户列表查询（MPOS）
	 */
	@Override
	public R getExcellentMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//优质商户
			map.put("month", TimeUtil.getDayFormat10());
			List<Map<String, Object>> excellentMerchantMposList = merchantManageMapper.getExcellentMerchantMposList(map);
			respondMap.put("excellentMerchantMposList", excellentMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getExcellentMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 活跃商户列表查询（MPOS）
	 */
	@Override
	public R getActiveMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//活跃商户
			map.put("start_date", TimeUtil.getDateAddByDate(-30));
			map.put("end_date", TimeUtil.getDayString());
			List<Map<String, Object>> activeMerchantMposList = merchantManageMapper.getActiveMerchantMposList(map);
			respondMap.put("activeMerchantMposList", activeMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getActiveMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 休眠商户列表查询（MPOS）
	 */
	@Override
	public R getDormantMerchantMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//休眠商户
			map.put("start_date", TimeUtil.getDateAddByDate(-60));
			map.put("end_date", TimeUtil.getDayString());
			List<Map<String, Object>> dormantMerchantMposList = merchantManageMapper.getDormantMerchantMposList(map);
			respondMap.put("dormantMerchantMposList", dormantMerchantMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getDormantMerchantMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询商户详情（传统POS）
	 */
	@Override
	public R getTraditionalPosDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> traditionalPosDetail = merchantManageMapper.getTraditionalPosDetail(map);
			//新增政策1激活未达标扣款 byqh 201912
			String expire_day = merchantManageMapper.getExpireDay1(String.valueOf(map.get("sn")));
			traditionalPosDetail.put("expire_day",expire_day);
			String policy_name = merchantManageMapper.getPolicyName(String.valueOf(map.get("sn")));
			traditionalPosDetail.put("policy_name",policy_name);
			//新增代理直营商户交易总必输,交易总金额字段begin byqh
			String pos_type =merchantManageMapper.getAgentSysEposInfoBySn(String.valueOf(map.get("sn")));
			if("epos".equals(pos_type)){
				map.put("pos_type","epos");
			}
			Map<String, Object> traposStatistical = merchantManageMapper.getTraditionalPOSTradeStatistical(map);
			traditionalPosDetail.putAll(traposStatistical);

			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("traditionalPosDetail", traditionalPosDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	//查询商户排名10 byqh202006
	public R getTradeVolumeRankByMonth(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getMposTradeVolumeRankByMonth(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	public R getTradeVolumeRankByDay(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getMposTradeVolumeRankByMonth(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}







	public R getMposTradeVolumeRankByMonth(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getMposTradeVolumeRankByMonth(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	public R getMposTradeVolumeRankByDay(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getMposTradeVolumeRankByDay(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	public R getTraposTradeVolumeRankByMonth(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getTraposTradeVolumeRankByMonth(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	public R getTraposTradeVolumeRankByDay(Map<String,Object> map){
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> tradeVolumeRank = merchantManageMapper.getTraposTradeVolumeRankByDay(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("tradeVolumeRank", tradeVolumeRank);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	//查询商户排名10 byqh202006


	/**
	 * 查询商户详情（传统POS） add byqh202003
	 */
	@Override
	public R getTraditionalPosTradeDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosTradeDetail = merchantManageMapper.getTraditionalPosTradeDetail(map);
			//新增代理直营商户交易总必输,交易总金额字段end byqh
			respondMap.put("traditionalPosTradeDetail", traditionalPosTradeDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询商户详情（MPOS）
	 */
	@Override
	public R getMposDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> mposDetail = merchantManageMapper.getMposDetail(map);
			//新增政策1激活未达标扣款 byqh 201912
			String expire_day = merchantManageMapper.getExpireDay1(String.valueOf(map.get("sn")));
			mposDetail.put("expire_day",expire_day);
			String policy_name = merchantManageMapper.getPolicyName(String.valueOf(map.get("sn")));
			mposDetail.put("policy_name",policy_name);
			//新增代理直营商户交易总笔数,交易总金额字段begin byqh
			Map<String, Object> mposStatistical = merchantManageMapper.getMPOSTradeStatistical(map);
			mposDetail.putAll(mposStatistical);
			//新增代理直营商户交易总笔数,交易总金额字段end byqh
			respondMap.put("mposDetail", mposDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getTraditionalPosDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询直推人数
	 */
	@Override
	public R getReferAgencyNum(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			String user_id = StringUtil.getMapValue(map, "sys_user_id");
			Map<String, Object> user = userInfoMapper.getUserInfoById(user_id);
			respondMap.put("referer_num", StringUtil.getMapValue(user, "referer_num"));
			//添加代理总MPOS机具数量,未激活数量,已激活数量 byqh
			Map<String,Object> mposMap = merchantManageMapper.getMPOSAllocateCase(user_id);
            if(mposMap==null){
                mposMap = new HashMap<>();
                mposMap.put("m_pos_num","0");
                mposMap.put("m_act_num","0");
                mposMap.put("m_inact_num","0");
            }
			//添加代理总传统POS机具数量,未激活数量,已激活数量 byqh
			Map<String,Object> traMap = merchantManageMapper.getTraditionalPosAllocateCase(user_id);
            if(traMap==null){
                traMap = new HashMap<>();
                traMap.put("tra_pos_num","0");
                traMap.put("tra_act_num","0");
                traMap.put("tra_inact_num","0");
            }
			//添加代理总EPOS机具数量,未激活数量,已激活数量 byqh202003
			Map<String,Object> eposMap = merchantManageMapper.getEposAllocateCase(user_id);
			if(eposMap==null){
				eposMap = new HashMap<>();
				eposMap.put("e_pos_num","0");
				eposMap.put("e_act_num","0");
				eposMap.put("e_inact_num","0");
			}
			respondMap.putAll(mposMap);
			respondMap.putAll(traMap);
			respondMap.putAll(eposMap);

			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyNum方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R getReferAgencyTraditionalPosTradeAmountAvg(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("cre_month"))));
			String pos_type = map.get("pos_type")==null?null:String.valueOf(map.get("pos_type"));
			Map<String, Object> tradeAmountAvg = merchantManageMapper.getReferAgencyTraditionalPosTradeAmountAvg(String.valueOf(map.get("user_id")),cre_month,pos_type);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, tradeAmountAvg);
		}catch (Exception e){
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyTraditionalPosTradeAmountAvg方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912 add byqh202003
	 * @param map
	 * @return
	 */
	@Override
	public R getReferAgencyEposTradeAmountAvg(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("cre_month"))));
			Map<String, Object> eposAmountAvg = merchantManageMapper.getReferAgencyEposTradeAmountAvg(String.valueOf(map.get("user_id")),cre_month);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, eposAmountAvg);
		}catch (Exception e){
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyTraditionalPosTradeAmountAvg方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 得到MPOS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	@Override
	public R getReferAgencyMPosTradeAmountAvg(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//兼容yyyy-M,转换成yyyy-MM byqh202002
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String cre_month =  formatter.format(formatter.parse(String.valueOf(map.get("cre_month"))));
			Map<String, Object> tradeAmountAvg = merchantManageMapper.getReferAgencyMPosTradeAmountAvg(String.valueOf(map.get("user_id")),cre_month);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, tradeAmountAvg);
		}catch (Exception e){
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyMPosTradeAmountAvg方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询直推代理
	 */
	@Override
	public R getReferAgencyList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> referAgencyList = merchantManageMapper.getReferAgencyList(map);
			respondMap.put("referAgencyList", referAgencyList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	public R getCompletePolicyMerchantTraditionalPos(Map<String, Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String,Object>> merchantList = merchantManageMapper.getCompletePolicyMerchantTraditionalPos(map);
			respondMap.put("merchantList", merchantList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	public R getCompletePolicyMerchantMPos(Map<String, Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String,Object>> merchantList = merchantManageMapper.getCompletePolicyMerchantMPos(map);
			respondMap.put("merchantList", merchantList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询代理头部信息（传统POS）
	 */
	@Override
	public R getReferAgencyHeadTraditionalPosInfo(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//判断用户ID是否为代理
			Map<String, Object> agencyUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "user_id"));
			if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(agencyUser, "referer_id"))){
				return R.error(MerchantManageCodeConstant.MerchantManage_INFO_CODE_993999, MerchantManageCodeConstant.MerchantManage_INFO_MSG_993999);
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> referAgencyHeadTraditionalPosInfo = merchantManageMapper.getReferAgencyHeadTraditionalPosInfo(map);
			if(referAgencyHeadTraditionalPosInfo!=null){
				//添加代理总传统POS机具数量,未激活数量,已激活数量 begin byqh
				if(map.get("pos_type")==null){
					Map<String,Object> traposMap = merchantManageMapper.getTraditionalPosAllocateCase(StringUtil.getMapValue(map, "user_id"));
					referAgencyHeadTraditionalPosInfo.putAll(traposMap);
					referAgencyHeadTraditionalPosInfo.put("real_name",agencyUser.get("real_name").toString());
				}else{
					Map<String,Object> traposMap = merchantManageMapper.getEposAllocateCase(StringUtil.getMapValue(map, "user_id"));
					referAgencyHeadTraditionalPosInfo.putAll(traposMap);
					referAgencyHeadTraditionalPosInfo.put("real_name",agencyUser.get("real_name").toString());
				}
				//添加代理总传统POS机具数量,未激活数量,已激活数量 end byqh

				//总交易额 begin add byqh 201912
				String tradeAmountAll = merchantManageMapper.getTraditionalTransAmountByAll(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "pos_type"));
				referAgencyHeadTraditionalPosInfo.put("tradeAmountAll",tradeAmountAll);
				//总交易额 end add byqh 201912
			}else{
                referAgencyHeadTraditionalPosInfo = new HashMap<>();
				referAgencyHeadTraditionalPosInfo.put("trade_num","0");
				referAgencyHeadTraditionalPosInfo.put("performance","0");
				referAgencyHeadTraditionalPosInfo.put("pos_num","0");
				referAgencyHeadTraditionalPosInfo.put("tra_pos_num","0");
				referAgencyHeadTraditionalPosInfo.put("tra_inact_num","0");
				referAgencyHeadTraditionalPosInfo.put("tra_act_num","0");
				referAgencyHeadTraditionalPosInfo.put("real_name",agencyUser.get("real_name").toString());
				referAgencyHeadTraditionalPosInfo.put("tradeAmountAll","0");
			}
			respondMap.put("referAgencyHeadTraditionalPosInfo", referAgencyHeadTraditionalPosInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyHeadTraditionalPosInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询代理商户列表（传统POS）
	 */
	@Override
	public R getReferAgencyTraditionalPosList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//判断用户ID是否为代理
			Map<String, Object> agencyUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "user_id"));
			if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(agencyUser, "referer_id"))){
				return R.error(MerchantManageCodeConstant.MerchantManage_INFO_CODE_993999, MerchantManageCodeConstant.MerchantManage_INFO_MSG_993999);
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> referAgencyTraditionalPosList = merchantManageMapper.getReferAgencyTraditionalPosList(map);
			respondMap.put("referAgencyTraditionalPosList", referAgencyTraditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyTraditionalPosList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询代理头部信息（MPOS）
	 */
	@Override
	public R getReferAgencyHeadMposInfo(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//判断用户ID是否为代理
			Map<String, Object> agencyUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "user_id"));
			if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(agencyUser, "referer_id"))){
				return R.error(MerchantManageCodeConstant.MerchantManage_INFO_CODE_993999, MerchantManageCodeConstant.MerchantManage_INFO_MSG_993999);
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> referAgencyHeadMposInfo = merchantManageMapper.getReferAgencyHeadMposInfo(map);
			//添加代理总MPOS机具数量,未激活数量,已激活数量 begin byqh
			Map<String, Object> mposMap = merchantManageMapper.getMPOSAllocateCase(StringUtil.getMapValue(map, "user_id"));
			referAgencyHeadMposInfo.putAll(mposMap);
			referAgencyHeadMposInfo.put("real_name",agencyUser.get("real_name").toString());
			//添加代理总MPOS机具数量,未激活数量,已激活数量 end byqh

			//总交易额 begin add byqh 201912
			String tradeAmountAll = merchantManageMapper.getMPOSTransAmountByAll(StringUtil.getMapValue(map, "user_id"));
			referAgencyHeadMposInfo.put("tradeAmountAll",tradeAmountAll);
			//总交易额 end add byqh 201912

			respondMap.put("referAgencyHeadMposInfo", referAgencyHeadMposInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyHeadMposInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询代理头部信息（MPOS）
	 */
	@Override
	public R getReferAgencyMposList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//判断用户ID是否为代理
			Map<String, Object> agencyUser = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "user_id"));
			if(!StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(agencyUser, "referer_id"))){
				return R.error(MerchantManageCodeConstant.MerchantManage_INFO_CODE_993999, MerchantManageCodeConstant.MerchantManage_INFO_MSG_993999);
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> referAgencyMposList = merchantManageMapper.getReferAgencyMposList(map);
			respondMap.put("referAgencyMposList", referAgencyMposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MerchantManageServiceImpl -- getReferAgencyMposList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
