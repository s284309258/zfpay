package com.example.longecological.service.activity.impl;

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
import com.example.longecological.entity.R;
import com.example.longecological.mapper.activity.OnlineActivityMapper;
import com.example.longecological.service.activity.OnlineActivityCacheService;
import com.example.longecological.service.activity.OnlineActivityService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class OnlineActivityServiceImpl implements OnlineActivityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineActivityServiceImpl.class);
	
	@Autowired
	private OnlineActivityCacheService onlineActivityCacheService;	
	
	@Autowired
	private OnlineActivityMapper onlineActivityMapper;
	
	@Autowired
	private UserInfoCacheService userInfoCacheService;

	/**
	 * 线上活动列表（传统POS）
	 */
	@Override
	public R getTraditionalPosOnlineActivityList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "sys_user_id"));
			map.put("date", TimeUtil.getDayString());
			map.put("manager_id", StringUtil.getMapValue(user, "manager_id"));
			map.put("key", StringUtil.getMapValue(user, "manager_id")+"_"+TimeUtil.getDayString());
			List<Map<String, Object>> traditionalPosOnlineActivityList = onlineActivityCacheService.getTraditionalPosOnlineActivityList(map);
			respondMap.put("traditionalPosOnlineActivityList", traditionalPosOnlineActivityList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosOnlineActivityList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策
	 * @param map
	 * @return
	 */
	@Override
	public R getPosOnlinePolicyList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> posOnlinePolicyList = onlineActivityMapper.getPosOnlinePolicyList(map);
			respondMap.put("posOnlinePolicyList", posOnlinePolicyList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosOnlineActivityList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策
	 * @param map
	 * @return
	 */
	@Override
	public R joinPosOnlinePolicy(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> list = onlineActivityMapper.getPosOnlinePolicyByID(map);
			if(list.size()>0){
				Map<String,Object> params = list.get(0);
				String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
				for(String sn : sn_list){
					params.put("sn",sn);
					params.put("create_by",StringUtil.getMapValue(map, "sys_user_id"));
					onlineActivityMapper.joinPosOnlinePolicy(params);
				}
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosOnlineActivityList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}

	/**
	 * 线上活动列表（MPOS）
	 */
	@Override
	public R getMposOnlineActivityList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "sys_user_id"));
			map.put("date", TimeUtil.getDayString());
			map.put("manager_id", StringUtil.getMapValue(user, "manager_id"));
			map.put("key", StringUtil.getMapValue(user, "manager_id")+"_"+TimeUtil.getDayString());
			List<Map<String, Object>> mposOnlineActivityList = onlineActivityCacheService.getMposOnlineActivityList(map);
			respondMap.put("mposOnlineActivityList", mposOnlineActivityList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getMposOnlineActivityList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询活动奖励记录列表(传统POS)
	 */
	@Override
	public R getTraditionalPosRewardRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosRewardRecordList = onlineActivityMapper.getTraditionalPosRewardRecordList(map);
			respondMap.put("traditionalPosRewardRecordList", traditionalPosRewardRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosRewardRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询活动奖励记录列表(MPOS)
	 */
	@Override
	public R getMposRewardRecordList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposRewardRecordList = onlineActivityMapper.getMposRewardRecordList(map);
			respondMap.put("mposRewardRecordList", mposRewardRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getMposRewardRecordList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询活动申请列表(传统POS)
	 */
	@Override
	public R getTraditionalPosActivityApplyList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosActivityApplyList = onlineActivityMapper.getTraditionalPosActivityApplyList(map);
			respondMap.put("traditionalPosActivityApplyList", traditionalPosActivityApplyList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosActivityApplyList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询活动申请列表(MPOS)
	 */
	@Override
	public R getMposActivityApplyList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposActivityApplyList = onlineActivityMapper.getMposActivityApplyList(map);
			respondMap.put("mposActivityApplyList", mposActivityApplyList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getMposActivityApplyList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询活动申请详情(传统POS)
	 */
	@Override
	public R getTraditionalPosActivityApplyDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> traditionalPosActivityApply = onlineActivityMapper.getTraditionalPosActivityApplyDetail(map);
			respondMap.put("traditionalPosActivityApply", traditionalPosActivityApply);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosActivityApplyDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询活动申请详情(MPOS)
	 */
	@Override
	public R getMposActivityApplyDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> mposActivityApply = onlineActivityMapper.getMposActivityApplyDetail(map);
			respondMap.put("mposActivityApply", mposActivityApply);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getMposActivityApplyDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询活动详情(传统POS)
	 */
	@Override
	public R getTraditionalPosOnlineActivityDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> traditionalPosOnlineActivity = onlineActivityCacheService.getTraditionalPosOnlineActivityDetail(map);
			respondMap.put("traditionalPosOnlineActivity", traditionalPosOnlineActivity);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosOnlineActivityDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询活动详情(MPOS)
	 */
	@Override
	public R getMposOnlineActivityDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> mposOnlineActivity = onlineActivityCacheService.getMposOnlineActivityDetail(map);
			respondMap.put("mposOnlineActivity", mposOnlineActivity);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getMposOnlineActivityDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询参与活动信息(传统POS)
	 */
	@Override
	public R getTraditionalPosPartActivityInfo(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> traditionalPosOnlineActivity = onlineActivityCacheService.getTraditionalPosOnlineActivityDetail(map);
			if(traditionalPosOnlineActivity == null){
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			//查询参与活动信息
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> traditionalPosActivityRewardList = onlineActivityCacheService.getTraditionalPosActivityRewardList(map);
			respondMap.put("traditionalPosActivityRewardList", traditionalPosActivityRewardList);
			List<Map<String, Object>> traditionalPosList = onlineActivityMapper.getUserTraditionalPosList(map);
			respondMap.put("traditionalPosList", traditionalPosList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosPartActivityInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 查询参与活动信息(MPOS)
	 */
	@Override
	public R getMposPartActivityInfo(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询活动
			Map<String, Object> mposOnlineActivity = onlineActivityCacheService.getMposOnlineActivityDetail(map);
			if(mposOnlineActivity == null){
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			//查询参与活动信息
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> mposActivityRewardList = onlineActivityCacheService.getMposActivityRewardList(map);
			respondMap.put("mposActivityRewardList", mposActivityRewardList);
			List<Map<String, Object>> mposList = onlineActivityMapper.getUserMposList(map);
			respondMap.put("mposList", mposList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- getTraditionalPosPartActivityInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 提交活动申请信息(传统POS)
	 */
	@Override
	@Transactional
	public R submitTraditionalPosActivityApply(Map<String, Object> map) {
		int num = 0;
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询奖励详情
			Map<String, Object> traditionalPosActivityReward = onlineActivityCacheService.getTraditionalPosActivityRewardDetail(map);
			if(traditionalPosActivityReward == null){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997998, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997998);
			}
			//判断提交的POS机数量是否符合
			String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
			if(Integer.parseInt(StringUtil.getMapValue(traditionalPosActivityReward, "pos_num")) > sn_list.length){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997997, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997997); 
			}
			//查询是否存在正在参与或审核的活动
//			map.put("activity_id", StringUtil.getMapValue(traditionalPosActivityReward, "activity_id"));
//			if(onlineActivityMapper.getTraditionalPosProcessingActivityApply(map) > 0){
//				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997996, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997996); 
//			}
			//保存申请记录
			Map<String, Object> record = new HashMap<>();
			record.put("apply_id", null);
			record.put("order_id", StringUtil.getDateTimeAndRandomForID());
			record.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
			record.put("activity_id", StringUtil.getMapValue(traditionalPosActivityReward, "activity_id"));
			record.put("activity_reward_id", StringUtil.getMapValue(map, "activity_reward_id"));
			record.put("sn_list", StringUtil.getMapValue(map, "sn_list"));
			record.put("cre_date", TimeUtil.getDayString());
			record.put("cre_time", TimeUtil.getTimeString());
			num = onlineActivityMapper.addTraditionalPosActivityApplyInfo(record);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997995, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997995); 
			}
			
			//修改POS机参与活动状态
			num = onlineActivityMapper.updateUserTraditionalPosActivityStatus(map);
			if(num != sn_list.length){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997994, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997994); 
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- submitTraditionalPosActivityApply方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	/**
	 * 提交活动申请信息(MPOS)
	 */
	@Override
	@Transactional
	public R submitMposActivityApply(Map<String, Object> map) {
		int num = 0;
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询奖励详情
			Map<String, Object> mposActivityReward = onlineActivityCacheService.getMposActivityRewardDetail(map);
			if(mposActivityReward == null){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997998, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997998);
			}
			//判断提交的POS机数量是否符合
			String[] sn_list = StringUtil.getMapValue(map, "sn_list").split(",");
			if(Integer.parseInt(StringUtil.getMapValue(mposActivityReward, "pos_num")) > sn_list.length){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997997, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997997); 
			}
			//查询是否存在正在参与或审核的活动
//			map.put("activity_id", StringUtil.getMapValue(mposActivityReward, "activity_id"));
//			if(onlineActivityMapper.getMposProcessingActivityApply(map) > 0){
//				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997996, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997996); 
//			}
			//保存申请记录
			Map<String, Object> record = new HashMap<>();
			record.put("apply_id", null);
			record.put("order_id", StringUtil.getDateTimeAndRandomForID());
			record.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
			record.put("activity_id", StringUtil.getMapValue(mposActivityReward, "activity_id"));
			record.put("activity_reward_id", StringUtil.getMapValue(map, "activity_reward_id"));
			record.put("sn_list", StringUtil.getMapValue(map, "sn_list"));
			record.put("cre_date", TimeUtil.getDayString());
			record.put("cre_time", TimeUtil.getTimeString());
			num = onlineActivityMapper.addMposActivityApplyInfo(record);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997995, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997995); 
			}
			
			//修改POS机参与活动状态
			num = onlineActivityMapper.updateUserMposActivityStatus(map);
			if(num != sn_list.length){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997994, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997994); 
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- submitMposActivityApply方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	/**
	 * 取消活动申请(传统POS)
	 */
	@Override
	@Transactional
	public R cancelTraditionalPosActivityApply(Map<String, Object> map) {
		int num = 0;
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询申请详情
			Map<String, Object> traditionalPosActivityApply = onlineActivityMapper.getTraditionalPosActivityApply(map);
			if(traditionalPosActivityApply == null){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997993, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997993); 
			}
			
			//修改申请状态
			map.put("new_status", "04");
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			num = onlineActivityMapper.updateTraditionalPosActivityApplyStatus(map);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997992, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997992); 
			}
			
			//修改POS机参与活动状态
			map.put("sn_list", StringUtil.getMapValue(traditionalPosActivityApply, "sn_list"));
			num = onlineActivityMapper.updateUserTraditionalPosCancelActivityStatus(map);
			if(num != StringUtil.getMapValue(map, "sn_list").split(",").length){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997991, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997991); 
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- cancelTraditionalPosActivityApply方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	/**
	 * 取消活动申请(MPOS)
	 */
	@Override
	@Transactional
	public R cancelMposActivityApply(Map<String, Object> map) {
		int num = 0;
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询申请详情
			Map<String, Object> mposActivityApply = onlineActivityMapper.getMposActivityApply(map);
			if(mposActivityApply == null){
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997993, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997993); 
			}
			
			//修改申请状态
			map.put("new_status", "04");
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			num = onlineActivityMapper.updateMposActivityApplyStatus(map);
			if(num != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997992, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997992); 
			}
			
			//修改POS机参与活动状态
			map.put("sn_list", StringUtil.getMapValue(mposActivityApply, "sn_list"));
			num = onlineActivityMapper.updateUserMposCancelActivityStatus(map);
			if(num != StringUtil.getMapValue(map, "sn_list").split(",").length){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OnlineActivityCodeConstant.OnlineActivity_INFO_CODE_997991, OnlineActivityCodeConstant.OnlineActivity_INFO_MSG_997991); 
			}
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OnlineActivityServiceImpl -- cancelMposActivityApply方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}

}
