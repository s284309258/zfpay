package com.ruoyi.project.develop.task.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.time.DateUtils;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitParamConstants;
import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.ActivitySettlementMapper;
import com.ruoyi.project.develop.task.service.ActivitySettlementService;
import com.ruoyi.project.devemana.user.mapper.ManaUserInfoMapper;

/**
 * 活动结算业务层 byqh 201912
 * @author i
 *
 */
@Service
public class ActivitySettlementServiceImpl implements ActivitySettlementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivitySettlementServiceImpl.class);
	
	@Autowired
	private ActivitySettlementMapper activitySettlementMapper;
	
	@Autowired
	private ManaUserInfoMapper manaUserInfoMapper;

	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;

	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;


	/**
	 * 高返现活动结算(传统POS)
	 */
	@Override
	public void highCashbackActivitySettlement() {
		LOGGER.info("------------------高返现活动结算任务-START-------------");
		
		try{
			//查询传统POS活动
			String date = TimeUtil.getDayString();
			List<Map<String, Object>> traposActivityList = activitySettlementMapper.getTraposHighCashbackActivity(date);
			if(traposActivityList!=null && traposActivityList.size()>0){
				for(Map<String, Object> activity : traposActivityList){
					SpringUtils.getAopProxy(this).dealTraposHighCashbackActivitySettlement(activity, date);
				}
			}
			
			//查询MPOS活动
			List<Map<String, Object>> mposActivityList = activitySettlementMapper.getMposHighCashbackActivity(date);
			if(mposActivityList!=null && mposActivityList.size()>0){
				for(Map<String, Object> activity : mposActivityList){
					SpringUtils.getAopProxy(this).dealMposHighCashbackActivitySettlement(activity, date);
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- highCashbackActivitySettlement方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------高返现活动结算任务-END-------------");
		
	}
	
	/**
	 * 处理单个高返现活动(传统POS)
	 * @param activity
	 * @param date
	 */
	@Transactional
	public void dealTraposHighCashbackActivitySettlement(Map<String, Object> activity, String date){
		int num = 0;
		try{
			//查询需要处理的活动申请
			List<Map<String, Object>> traposApplyList = activitySettlementMapper.getTraposApplyHighCashbackActivityList(StringUtil.getMapValue(activity, "id"));
			if(traposApplyList!=null && traposApplyList.size()>0){
				for(Map<String, Object> traposApply : traposApplyList){
					//查询满足活动条件的POS机
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
					param.put("sn_list", StringUtil.getMapValue(traposApply, "sn_list"));
					param.put("expenditure", StringUtil.getMapValue(traposApply, "expenditure"));
					param.put("apply_id", StringUtil.getMapValue(traposApply, "apply_id"));
					param.put("start_date", StringUtil.getMapValue(activity, "start_date"));
					param.put("end_date", StringUtil.getMapValue(activity, "end_date"));
					List<Map<String, Object>> traposList = activitySettlementMapper.getTraposHighCashbackActivityRewardList(param);
					if(traposList!=null && traposList.size()>0){
						for(Map<String, Object> trapos : traposList){
							//判断交易额数量
							if("".equals(StringUtil.getMapValue(trapos, "expenditure"))){
								continue;
							}
							if(new BigDecimal(StringUtil.getMapValue(traposApply, "expenditure")).multiply(new BigDecimal(10000)).compareTo(new BigDecimal(StringUtil.getMapValue(trapos, "expenditure")))>0){
								continue;
							}
							
							//判断返现记录
							trapos.put("start_date", StringUtil.getMapValue(activity, "start_date"));
							trapos.put("end_date", StringUtil.getMapValue(activity, "end_date"));
							Map<String, Object> cashBack = activitySettlementMapper.getTraposCashBackRecord(trapos);
							if(cashBack==null){
								continue;
							}
							
							//更新用户活动奖励
							String order_id = StringUtil.getDateTimeAndRandomForID();
							Map<String, Object> edit_user = new HashMap<>();
							BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(traposApply, "reward_money"));
							edit_user.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
							edit_user.put("money", reward_money);
							edit_user.put("today_benefit", reward_money);
							edit_user.put("total_benefit", reward_money);
							edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
							edit_user.put("op_order_id", order_id);
							edit_user.put("state_type", BenefitParamConstants.state_type_1);
							edit_user.put("sn", StringUtil.getMapValue(trapos, "sn"));
							String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(trapos, "sn"));
							if("epos".equals(pos_type)){
								edit_user.put("pos_type", BenefitParamConstants.pos_type_03);
							}else{
								edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
							}
							edit_user.put("up_date", TimeUtil.getDayString());
							edit_user.put("up_time", TimeUtil.getTimeString());
							num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
							if(num != 1){
								throw new Exception("用户收益更新异常:"+edit_user.toString());
							}
							
							//记录活动奖励
							Map<String, Object> record = new HashMap<>();
							record.put("order_id", order_id);
							record.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
							record.put("activity_id", StringUtil.getMapValue(traposApply, "activity_id"));
							record.put("activity_reward_id", StringUtil.getMapValue(traposApply, "activity_reward_id"));
							record.put("apply_id", StringUtil.getMapValue(traposApply, "apply_id"));
							record.put("sn", StringUtil.getMapValue(trapos, "sn"));
							record.put("money", reward_money);
							record.put("expenditure", StringUtil.getMapValue(trapos, "expenditure"));
							record.put("cre_date", TimeUtil.getDayString());
							record.put("cre_time", TimeUtil.getTimeString());
							if("epos".equals(pos_type)){
								record.put("pos_type",BenefitParamConstants.pos_type_03);
							}
							num = activitySettlementMapper.insertUserTraposActivityRewardRecord(record);
							if(num != 1){
								throw new Exception("活动奖励记录异常:"+record.toString());
							}
							
							//更细活动状态
							if(StringUtil.getMapValue(activity, "end_date").compareTo(date)<0){
								num = activitySettlementMapper.updateUserTraposActivityStatus(traposApply);
								if(num != StringUtil.getMapValue(traposApply, "sn_list").split(",").length){
									throw new Exception("当前用户POS活动状态更新异常："+traposApply.toString());
								}
							}
						}
					}
				}
			}
			
			//更新活动结束状态
			if(StringUtil.getMapValue(activity, "end_date").compareTo(date)<0){
				num = activitySettlementMapper.updateTraposActivityEndStatus(StringUtil.getMapValue(activity, "id"));
				if(num != 1){
					throw new Exception("活动结束状态更新异常");
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- dealTraposHighCashbackActivitySettlement方法处理异常:" + activity.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * 处理单个高返现活动(MPOS)
	 * @param activity
	 * @param date
	 */
	@Transactional
	public void dealMposHighCashbackActivitySettlement(Map<String, Object> activity, String date){
		int num = 0;
		try{
			//查询需要处理的活动申请
			List<Map<String, Object>> mposApplyList = activitySettlementMapper.getMposApplyHighCashbackActivityList(StringUtil.getMapValue(activity, "id"));
			if(mposApplyList!=null && mposApplyList.size()>0){
				for(Map<String, Object> mposApply : mposApplyList){
					//查询满足活动条件的POS机
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
					param.put("sn_list", StringUtil.getMapValue(mposApply, "sn_list"));
					param.put("expenditure", StringUtil.getMapValue(mposApply, "expenditure"));
					param.put("apply_id", StringUtil.getMapValue(mposApply, "apply_id"));
					param.put("start_date", StringUtil.getMapValue(activity, "start_date"));
					param.put("end_date", StringUtil.getMapValue(activity, "end_date"));
					List<Map<String, Object>> mposList = activitySettlementMapper.getMposHighCashbackActivityRewardList(param);
					if(mposList!=null && mposList.size()>0){
						for(Map<String, Object> mpos : mposList){
							//判断交易额数量
							if("".equals(StringUtil.getMapValue(mpos, "expenditure"))){
								continue;
							}
							if(new BigDecimal(StringUtil.getMapValue(mposApply, "expenditure")).multiply(new BigDecimal(10000)).compareTo(new BigDecimal(StringUtil.getMapValue(mpos, "expenditure")))>0){
								continue;
							}
							
							//判断返现记录
							mpos.put("start_date", StringUtil.getMapValue(activity, "start_date"));
							mpos.put("end_date", StringUtil.getMapValue(activity, "end_date"));
							Map<String, Object> cashBack = activitySettlementMapper.getMposCashBackRecord(mpos);
							if(cashBack==null){
								continue;
							}
							
							//更新用户活动奖励
							String order_id = StringUtil.getDateTimeAndRandomForID();
							Map<String, Object> edit_user = new HashMap<>();
							BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(mposApply, "reward_money"));
							edit_user.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
							edit_user.put("money", reward_money);
							edit_user.put("today_benefit", reward_money);
							edit_user.put("total_benefit", reward_money);
							edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
							edit_user.put("op_order_id", order_id);
							edit_user.put("state_type", BenefitParamConstants.state_type_1);
							edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
							edit_user.put("sn", StringUtil.getMapValue(mpos, "sn"));
							edit_user.put("up_date", TimeUtil.getDayString());
							edit_user.put("up_time", TimeUtil.getTimeString());
							num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
							if(num != 1){
								throw new Exception("用户收益更新异常:"+edit_user.toString());
							}
							
							//记录活动奖励
							Map<String, Object> record = new HashMap<>();
							record.put("order_id", order_id);
							record.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
							record.put("activity_id", StringUtil.getMapValue(mposApply, "activity_id"));
							record.put("activity_reward_id", StringUtil.getMapValue(mposApply, "activity_reward_id"));
							record.put("apply_id", StringUtil.getMapValue(mposApply, "apply_id"));
							record.put("sn", StringUtil.getMapValue(mpos, "sn"));
							record.put("money", reward_money);
							record.put("expenditure", StringUtil.getMapValue(mpos, "expenditure"));
							record.put("cre_date", TimeUtil.getDayString());
							record.put("cre_time", TimeUtil.getTimeString());
							num = activitySettlementMapper.insertUserMposActivityRewardRecord(record);
							if(num != 1){
								throw new Exception("活动奖励记录异常:"+record.toString());
							}
							
							//更细活动状态
							if(StringUtil.getMapValue(activity, "end_date").compareTo(date)<0){
								num = activitySettlementMapper.updateUserMposActivityStatus(mposApply);
								if(num != StringUtil.getMapValue(mposApply, "sn_list").split(",").length){
									throw new Exception("当前用户POS活动状态更新异常："+mposApply.toString());
								}
							}
						}
					}
				}
			}
			
			//更新活动结束状态
			if(StringUtil.getMapValue(activity, "end_date").compareTo(date)<0){
				num = activitySettlementMapper.updateMposActivityEndStatus(StringUtil.getMapValue(activity, "id"));
				if(num != 1){
					throw new Exception("活动结束状态更新异常");
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- dealMposHighCashbackActivitySettlement方法处理异常:" + activity.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/**
	 * 交易量活动
	 */
	@Override
	public void volumeActivitySettlement() {
		LOGGER.info("------------------交易量活动结算任务-START-------------");
		
		try{
			//查询传统POS活动
			String start_date = TimeUtil.getPreMonthFirstDay();
			List<Map<String, Object>> traposActivityList = activitySettlementMapper.getTraposVolumeActivity(start_date);
			if(traposActivityList!=null && traposActivityList.size()>0){
				for(Map<String, Object> activity : traposActivityList){
					SpringUtils.getAopProxy(this).dealTraposVolumeActivitySettlement(activity, start_date);
				}
			}
			
			//查询MPOS活动
			List<Map<String, Object>> mposActivityList = activitySettlementMapper.getMposVolumeActivity(start_date);
			if(mposActivityList!=null && mposActivityList.size()>0){
				for(Map<String, Object> activity : mposActivityList){
					SpringUtils.getAopProxy(this).dealMposVolumeActivitySettlement(activity, start_date);
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- volumeActivitySettlement方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------交易量活动结算任务-END-------------");
		
	}

	/***
	 * 单个交易量活动结算(传统POS)
	 * @param activity
	 * @param start_date
	 */
	public void dealTraposVolumeActivitySettlement(Map<String, Object> activity, String start_date){
		int num = 0;
		try{
			//查询需要处理的活动申请
			List<Map<String, Object>> traposApplyList = activitySettlementMapper.getTraposApplyVolumeActivityList(StringUtil.getMapValue(activity, "id"));
			if(traposApplyList!=null && traposApplyList.size()>0){
				for(Map<String, Object> traposApply : traposApplyList){
					//查询满足活动条件的POS机
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
					param.put("sn_list", StringUtil.getMapValue(traposApply, "sn_list"));
					param.put("expenditure", StringUtil.getMapValue(traposApply, "expenditure"));
					param.put("month", start_date.substring(0, 6));
					List<Map<String, Object>> traposList = activitySettlementMapper.getTraposVolumeActivityRewardList(param);
					if(traposList!=null && traposList.size()>0){
						for(Map<String, Object> trapos : traposList){
							//判断交易额数量
							if("".equals(StringUtil.getMapValue(trapos, "expenditure"))){
								continue;
							}
							if(new BigDecimal(StringUtil.getMapValue(traposApply, "expenditure")).multiply(new BigDecimal(10000)).compareTo(new BigDecimal(StringUtil.getMapValue(trapos, "expenditure")))>0){
								continue;
							}
							
							//更新用户活动奖励
							String order_id = StringUtil.getDateTimeAndRandomForID();
							Map<String, Object> edit_user = new HashMap<>();
							BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(traposApply, "reward_money"));
							edit_user.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
							edit_user.put("money", reward_money);
							edit_user.put("today_benefit", reward_money);
							edit_user.put("total_benefit", reward_money);
							edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
							edit_user.put("op_order_id", order_id);
							edit_user.put("state_type", BenefitParamConstants.state_type_1);
							edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
							edit_user.put("sn", StringUtil.getMapValue(trapos, "sn"));
							edit_user.put("up_date", TimeUtil.getDayString());
							edit_user.put("up_time", TimeUtil.getTimeString());
							num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
							if(num != 1){
								throw new Exception("用户收益更新异常:"+edit_user.toString());
							}
							
							//记录活动奖励
							Map<String, Object> record = new HashMap<>();
							record.put("order_id", order_id);
							record.put("user_id", StringUtil.getMapValue(traposApply, "user_id"));
							record.put("activity_id", StringUtil.getMapValue(traposApply, "activity_id"));
							record.put("activity_reward_id", StringUtil.getMapValue(traposApply, "activity_reward_id"));
							record.put("apply_id", StringUtil.getMapValue(traposApply, "apply_id"));
							record.put("sn", StringUtil.getMapValue(trapos, "sn"));
							record.put("money", reward_money);
							record.put("expenditure", StringUtil.getMapValue(trapos, "expenditure"));
							record.put("cre_date", TimeUtil.getDayString());
							record.put("cre_time", TimeUtil.getTimeString());
							num = activitySettlementMapper.insertUserTraposActivityRewardRecord(record);
							if(num != 1){
								throw new Exception("活动奖励记录异常:"+record.toString());
							}
							
							//更细活动状态
							if(StringUtil.getMapValue(activity, "end_date").compareTo(TimeUtil.getDayString())<0){
								num = activitySettlementMapper.updateUserTraposActivityStatus(traposApply);
								if(num != StringUtil.getMapValue(traposApply, "sn_list").split(",").length){
									throw new Exception("当前用户POS活动状态更新异常："+traposApply.toString());
								}
							}
						}
					}
				}
			}
			
			//更新活动结束状态
			if(StringUtil.getMapValue(activity, "end_date").compareTo(TimeUtil.getDayString())<0){
				num = activitySettlementMapper.updateTraposActivityEndStatus(StringUtil.getMapValue(activity, "id"));
				if(num != 1){
					throw new Exception("活动结束状态更新异常");
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- dealTraposHighCashbackActivitySettlement方法处理异常:" + activity.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/***
	 * 单个交易量活动结算(MPOS)
	 * @param activity
	 * @param start_date
	 */
	public void dealMposVolumeActivitySettlement(Map<String, Object> activity, String start_date){
		int num = 0;
		try{
			//查询需要处理的活动申请
			List<Map<String, Object>> mposApplyList = activitySettlementMapper.getMposApplyVolumeActivityList(StringUtil.getMapValue(activity, "id"));
			if(mposApplyList!=null && mposApplyList.size()>0){
				for(Map<String, Object> mposApply : mposApplyList){
					//查询满足活动条件的POS机
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
					param.put("sn_list", StringUtil.getMapValue(mposApply, "sn_list"));
					param.put("expenditure", StringUtil.getMapValue(mposApply, "expenditure"));
					param.put("month", start_date.substring(0, 6));
					List<Map<String, Object>> mposList = activitySettlementMapper.getMposVolumeActivityRewardList(param);
					if(mposList!=null && mposList.size()>0){
						for(Map<String, Object> mpos : mposList){
							//判断交易额数量
							if("".equals(StringUtil.getMapValue(mpos, "expenditure"))){
								continue;
							}
							if(new BigDecimal(StringUtil.getMapValue(mposApply, "expenditure")).multiply(new BigDecimal(10000)).compareTo(new BigDecimal(StringUtil.getMapValue(mpos, "expenditure")))>0){
								continue;
							}
							
							//更新用户活动奖励
							String order_id = StringUtil.getDateTimeAndRandomForID();
							Map<String, Object> edit_user = new HashMap<>();
							BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(mposApply, "reward_money"));
							edit_user.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
							edit_user.put("money", reward_money);
							edit_user.put("today_benefit", reward_money);
							edit_user.put("total_benefit", reward_money);
							edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
							edit_user.put("op_order_id", order_id);
							edit_user.put("state_type", BenefitParamConstants.state_type_1);
							edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
							edit_user.put("sn", StringUtil.getMapValue(mpos, "sn"));
							edit_user.put("up_date", TimeUtil.getDayString());
							edit_user.put("up_time", TimeUtil.getTimeString());
							num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
							if(num != 1){
								throw new Exception("用户收益更新异常:"+edit_user.toString());
							}
							
							//记录活动奖励
							Map<String, Object> record = new HashMap<>();
							record.put("order_id", order_id);
							record.put("user_id", StringUtil.getMapValue(mposApply, "user_id"));
							record.put("activity_id", StringUtil.getMapValue(mposApply, "activity_id"));
							record.put("activity_reward_id", StringUtil.getMapValue(mposApply, "activity_reward_id"));
							record.put("apply_id", StringUtil.getMapValue(mposApply, "apply_id"));
							record.put("sn", StringUtil.getMapValue(mpos, "sn"));
							record.put("money", reward_money);
							record.put("expenditure", StringUtil.getMapValue(mpos, "expenditure"));
							record.put("cre_date", TimeUtil.getDayString());
							record.put("cre_time", TimeUtil.getTimeString());
							num = activitySettlementMapper.insertUserMposActivityRewardRecord(record);
							if(num != 1){
								throw new Exception("活动奖励记录异常:"+record.toString());
							}
							
							//更细活动状态
							if(StringUtil.getMapValue(activity, "end_date").compareTo(TimeUtil.getDayString())<0){
								num = activitySettlementMapper.updateUserMposActivityStatus(mposApply);
								if(num != StringUtil.getMapValue(mposApply, "sn_list").split(",").length){
									throw new Exception("当前用户POS活动状态更新异常："+mposApply.toString());
								}
							}
						}
					}
				}
			}
			
			//更新活动结束状态
			if(StringUtil.getMapValue(activity, "end_date").compareTo(TimeUtil.getDayString())<0){
				num = activitySettlementMapper.updateMposActivityEndStatus(StringUtil.getMapValue(activity, "id"));
				if(num != 1){
					throw new Exception("活动结束状态更新异常");
				}
			}
			
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- dealMposHighCashbackActivitySettlement方法处理异常:" + activity.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void traposAssessTaskSettlement(String assess_id) {
		LOGGER.info("------------------传统POS达标任务处理-START-------------");
		try{
			//查询达标任务详情
			Map<String, Object> assessTask = activitySettlementMapper.getTraposAssessTask(assess_id);
			if(assessTask != null){
				if("01".equals(StringUtil.getMapValue(assessTask, "assess_type"))){//激活量
					this.traposActivateAssessTaskSettlement(assessTask);
				}else if("02".equals(StringUtil.getMapValue(assessTask, "assess_type"))){//交易量
					this.traposVolumeAssessTaskSettlement(assessTask);
				}
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- traposAssessTaskSettlement方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------传统POS达标任务处理-END-------------");
		
	}
	
	@Transactional
	public void traposActivateAssessTaskSettlement(Map<String, Object> assessTask){
		int num = 0;
		try{
			//查询不满足任务的POS机
			List<Map<String, Object>> traposList = activitySettlementMapper.getTraposActivateAssessTaskList(assessTask);
			if(traposList!=null && traposList.size()>0){
				for(Map<String, Object> trapos : traposList){
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(assessTask, "deduct_money"));
					edit_user.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
					edit_user.put("sn", StringUtil.getMapValue(trapos, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}
					
					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					record.put("assess_id", StringUtil.getMapValue(assessTask, "id"));
					record.put("sn", StringUtil.getMapValue(trapos, "sn"));
					String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(trapos, "sn"));
					if("epos".equals(pos_type)){
						record.put("pos_type",BenefitParamConstants.pos_type_03);
					}
					record.put("money", deduct_money);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					num = activitySettlementMapper.insertUserTraposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
				}
			}
			
			//更新活动结束状态
			assessTask.put("up_date", TimeUtil.getDayString());
			assessTask.put("up_time", TimeUtil.getTimeString());
			num = activitySettlementMapper.updateTraposAssessTaskEndStatus(assessTask);
			if(num != 1){
				throw new Exception("达标任务状态更新异常");
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- traposActivateAssessTaskSettlement方法处理异常:" + assessTask.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	@Transactional
	public void traposVolumeAssessTaskSettlement(Map<String, Object> assessTask){
		int num = 0;
		try{
			//查询不满足任务的POS机
			List<Map<String, Object>> traposList = activitySettlementMapper.getTraposVolumeAssessTaskList(assessTask);
			if(traposList!=null && traposList.size()>0){
				for(Map<String, Object> trapos : traposList){
					//判断交易额数量
					if(!"".equals(StringUtil.getMapValue(trapos, "expenditure")) 
							&& new BigDecimal(StringUtil.getMapValue(trapos, "expenditure")).compareTo(new BigDecimal(StringUtil.getMapValue(assessTask, "expenditure")).multiply(new BigDecimal(10000)))>=0){
						continue;
					}
					
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(assessTask, "deduct_money"));
					edit_user.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
					edit_user.put("sn", StringUtil.getMapValue(trapos, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}
					
					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					record.put("assess_id", StringUtil.getMapValue(assessTask, "id"));
					record.put("sn", StringUtil.getMapValue(trapos, "sn"));
					record.put("money", deduct_money);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					num = activitySettlementMapper.insertUserTraposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
				}
			}
			
			//更新活动结束状态
			assessTask.put("up_date", TimeUtil.getDayString());
			assessTask.put("up_time", TimeUtil.getTimeString());
			num = activitySettlementMapper.updateTraposAssessTaskEndStatus(assessTask);
			if(num != 1){
				throw new Exception("达标任务状态更新异常");
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- traposActivateAssessTaskSettlement方法处理异常:" + assessTask.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void mposAssessTaskSettlement(String assess_id) {
		LOGGER.info("------------------MPOS达标任务处理-START-------------");
		try{
			//查询达标任务详情
			Map<String, Object> assessTask = activitySettlementMapper.getMposAssessTask(assess_id);
			if(assessTask != null){
				if("01".equals(StringUtil.getMapValue(assessTask, "assess_type"))){//激活量
					this.mposActivateAssessTaskSettlement(assessTask);
				}else if("02".equals(StringUtil.getMapValue(assessTask, "assess_type"))){//交易量
					this.mposVolumeAssessTaskSettlement(assessTask);
				}
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- mposAssessTaskSettlement方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------MPOS达标任务处理-END-------------");
	}

	/***
	 * pos政策每天执行一次,政策跟机器走
	 * add byqh 201912
	 */
	@Override
	public void posPolicyTaskSettlement() {
		try{
			/*******************************执行政策模块1 激活期考核*****************************/
			//mpos政策考核
			List<Map<String, Object>> list1 = activitySettlementMapper.getModule1Mpos();
			policy_module1Mpos(list1);
			//传统pos政策考核
			List<Map<String, Object>> list2 = activitySettlementMapper.getModule1Trapos();
			policy_module1Trapos(list2);

			/*********************************执行政策模块2 分期达标返现*********************************/
			//mpos政策考核
			List<Map<String, Object>> list3 = activitySettlementMapper.getModule2Mpos();
			policy_module2Mpos(list3);
			//传统pos政策考核
			List<Map<String, Object>> list4 = activitySettlementMapper.getModule2Trapos();
			policy_module2Trapos(list4);

			/***********************************交易达标返现********************************************/
			//mpos政策考核
			List<Map<String, Object>> list5 = activitySettlementMapper.getModule3Mpos();
			policy_module3Mpos(list5);
			//传统pos政策考核
			List<Map<String, Object>> list6 = activitySettlementMapper.getModule3Trapos();
			policy_module3Trapos(list6);

			/********************************伪激活扣款************************************************/
			//mpos政策考核
			List<Map<String,Object>> list7 = activitySettlementMapper.getModule4Mpos();
			policy_module4Mpos(list7);
			//传统pos政策考核
			List<Map<String,Object>> list8 = activitySettlementMapper.getModule4Trapos();
			policy_module4Trapos(list8);

		}catch (Exception e){
			LOGGER.error("ActivitySettlementServiceImpl.posPolicyTaskSettlement方法处理异常:" +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Transactional
	public void mposActivateAssessTaskSettlement(Map<String, Object> assessTask){
		int num = 0;
		try{
			//查询不满足任务的POS机
			List<Map<String, Object>> mposList = activitySettlementMapper.getMposActivateAssessTaskList(assessTask);
			if(mposList!=null && mposList.size()>0){
				for(Map<String, Object> mpos : mposList){
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(assessTask, "deduct_money"));
					edit_user.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
					edit_user.put("sn", StringUtil.getMapValue(mpos, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}
					
					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					record.put("assess_id", StringUtil.getMapValue(assessTask, "id"));
					record.put("sn", StringUtil.getMapValue(mpos, "sn"));
					record.put("money", deduct_money);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					num = activitySettlementMapper.insertUserMposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
				}
			}
			
			//更新活动结束状态
			assessTask.put("up_date", TimeUtil.getDayString());
			assessTask.put("up_time", TimeUtil.getTimeString());
			num = activitySettlementMapper.updateMposAssessTaskEndStatus(assessTask);
			if(num != 1){
				throw new Exception("达标任务状态更新异常");
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- mposActivateAssessTaskSettlement方法处理异常:" + assessTask.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Transactional
	public void mposVolumeAssessTaskSettlement(Map<String, Object> assessTask){
		int num = 0;
		try{
			//查询不满足任务的POS机
			List<Map<String, Object>> mposList = activitySettlementMapper.getMposVolumeAssessTaskList(assessTask);
			if(mposList!=null && mposList.size()>0){
				for(Map<String, Object> mpos : mposList){
					//判断交易额数量
					if(!"".equals(StringUtil.getMapValue(mpos, "expenditure")) 
							&& new BigDecimal(StringUtil.getMapValue(mpos, "expenditure")).compareTo(new BigDecimal(StringUtil.getMapValue(assessTask, "expenditure")).multiply(new BigDecimal(10000)))>=0){
						continue;
					}
					
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(assessTask, "deduct_money"));
					edit_user.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
					edit_user.put("sn", StringUtil.getMapValue(mpos, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}
					
					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(assessTask, "user_id"));
					record.put("assess_id", StringUtil.getMapValue(assessTask, "id"));
					record.put("sn", StringUtil.getMapValue(mpos, "sn"));
					record.put("money", deduct_money);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					num = activitySettlementMapper.insertUserMposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
				}
			}
			
			//更新活动结束状态
			assessTask.put("up_date", TimeUtil.getDayString());
			assessTask.put("up_time", TimeUtil.getTimeString());
			num = activitySettlementMapper.updateMposAssessTaskEndStatus(assessTask);
			if(num != 1){
				throw new Exception("达标任务状态更新异常");
			}
		}catch(Exception e){
			LOGGER.error("ActivitySettlementServiceImpl -- mposActivateAssessTaskSettlement方法处理异常:" + assessTask.toString() +ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void policy_module1Trapos(List<Map<String,Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map :list1){
				//更新用户扣除金额
				String order_id = StringUtil.getDateTimeAndRandomForID();
				Map<String, Object> edit_user = new HashMap<>();
				BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(map, "module1_deduct"));
				edit_user.put("user_id", StringUtil.getMapValue(map, "user_id"));
				edit_user.put("deduct_money", deduct_money);
				edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
				String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(map, "sn"));
				if("epos".equals(pos_type)){
					edit_user.put("pos_type", "03");
				}else{
					edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
				}
				edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
				edit_user.put("up_date", TimeUtil.getDayString());
				edit_user.put("up_time", TimeUtil.getTimeString());
				num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
				if(num != 1){
					throw new Exception("用户收益更新异常:"+edit_user.toString());
				}

				//记录活动奖励
				Map<String, Object> record = new HashMap<>();
				record.put("order_id", order_id);
				record.put("user_id", StringUtil.getMapValue(map, "user_id"));
				record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
				record.put("sn", StringUtil.getMapValue(map, "sn"));
				record.put("money", deduct_money);
				record.put("begin_date",StringUtil.getMapValue(map,"module1_pickup_date"));
				record.put("end_date",StringUtil.getMapValue(map,"module1_end_date"));
				record.put("cre_date", TimeUtil.getDayString());
				record.put("cre_time", TimeUtil.getTimeString());
				if("epos".equals(pos_type)){
					record.put("pos_type", BenefitParamConstants.pos_type_03);
				}else{
					record.put("pos_type", BenefitParamConstants.pos_type_01);
				}
				num = activitySettlementMapper.insertUserTraposDeductRecord(record);
				if(num != 1){
					throw new Exception("扣除记录异常:"+record.toString());
				}
				//完成考核更新标记
				activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
			}
		}
	}


	/**
	 * mpos政策1(激活期考核) add byqh 201912
	 * @param list1
	 */
	public void policy_module1Mpos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map : list1){
				//更新用户扣除金额
				String order_id = StringUtil.getDateTimeAndRandomForID();
				Map<String, Object> edit_user = new HashMap<>();
				BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(map, "module1_deduct"));
				edit_user.put("user_id", StringUtil.getMapValue(map, "user_id"));
				edit_user.put("deduct_money", deduct_money);
				edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
				edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
				edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
				edit_user.put("up_date", TimeUtil.getDayString());
				edit_user.put("up_time", TimeUtil.getTimeString());
				num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
				if(num != 1){
					throw new Exception("用户收益更新异常:"+edit_user.toString());
				}

				//记录活动奖励
				Map<String, Object> record = new HashMap<>();
				record.put("order_id", order_id);
				record.put("user_id", StringUtil.getMapValue(map, "user_id"));
				record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
				record.put("sn", StringUtil.getMapValue(map, "sn"));
				record.put("money", deduct_money);
				record.put("begin_date",StringUtil.getMapValue(map,"module1_pickup_date"));
				record.put("end_date",StringUtil.getMapValue(map,"module1_end_date"));
				record.put("cre_date", TimeUtil.getDayString());
				record.put("cre_time", TimeUtil.getTimeString());
				record.put("pos_type", BenefitParamConstants.pos_type_02);
				num = activitySettlementMapper.insertUserMposDeductRecord(record);
				if(num != 1){
					throw new Exception("扣除记录异常:"+record.toString());
				}
				//完成考核更新标记
				activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
			}
		}
	}
	/**
	 * mpos政策2 add byqh 201912
	 * @param list1
	 */
	public void policy_module2Mpos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map : list1){
				String act_date = map.get("act_date").toString();
				int module2_active_begin_day = Integer.parseInt(map.get("module2_active_begin_day").toString());
				int module2_active_end_day = Integer.parseInt(map.get("module2_active_end_day").toString());

				Date act_date_date =  DateUtils.parseDate(act_date);

				String begindate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module2_active_begin_day));
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module2_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCard2Mpos(StringUtil.getMapValue(map, "sn"),begindate,enddate);
				double amount_quantity = Double.parseDouble(map.get("module2_quantity").toString());

				if(amount>=amount_quantity){
					//更新用户活动奖励
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(map, "module2_reward"));
					String user_id = "";
					if(!"".equals(StringUtil.getMapValue(map, "module2_reward_flag"))){
						user_id = StringUtil.getMapValue(map, "module2_reward_flag");
//						Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(map, "user_id"));
//						if(null!=userMap.get("referer_id")){
//							user_id = StringUtil.getMapValue(userMap, "referer_id");
//						}
					}else{
						user_id = StringUtil.getMapValue(map, "user_id");
					}
					edit_user.put("user_id", user_id);
					edit_user.put("money", reward_money);
					edit_user.put("today_benefit", reward_money);
					edit_user.put("total_benefit", reward_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
					edit_user.put("op_order_id", order_id);
					edit_user.put("state_type", BenefitParamConstants.state_type_1);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
					edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}

					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", user_id);
					record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
//				record.put("activity_reward_id", StringUtil.getMapValue(map, "activity_reward_id"));
//				record.put("apply_id", StringUtil.getMapValue(mposApply, "apply_id"));
					record.put("sn", StringUtil.getMapValue(map, "sn"));
					record.put("money", reward_money);
//				record.put("expenditure", StringUtil.getMapValue(mpos, "expenditure"));
					record.put("begin_date",act_date);
					record.put("end_date",enddate);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					num = activitySettlementMapper.insertUserMposActivityRewardRecord(record);
					if(num != 1){
						throw new Exception("活动奖励记录异常:"+record.toString());
					}
					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
				}
			}
		}
	}

	/**
	 * mpos政策3 add byqh 201912
	 * @param list1
	 */
	public void policy_module2Trapos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map : list1){
				String act_date = map.get("act_date").toString();
				int module2_active_begin_day = Integer.parseInt(map.get("module2_active_begin_day").toString());
				int module2_active_end_day = Integer.parseInt(map.get("module2_active_end_day").toString());
				String dateTime = DateUtils.dateTime();

				Date act_date_date =  DateUtils.parseDate(act_date);

				String begindate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module2_active_begin_day));
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module2_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCard2Trapos(StringUtil.getMapValue(map, "sn"),begindate,enddate);
				double amount_quantity = Double.parseDouble(map.get("module2_quantity").toString());

				if(amount>=amount_quantity){
					//更新用户活动奖励
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal reward_money = new BigDecimal(StringUtil.getMapValue(map, "module2_reward"));
					String user_id = "";
					if(!"".equals(StringUtil.getMapValue(map, "module2_reward_flag"))){
						user_id = StringUtil.getMapValue(map, "module2_reward_flag");
//						Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(map, "user_id"));
//						if(null!=userMap.get("referer_id")){
//							user_id = StringUtil.getMapValue(userMap, "referer_id");
//						}
					}else{
						user_id = StringUtil.getMapValue(map, "user_id");
					}
					edit_user.put("user_id", user_id);
					edit_user.put("money", reward_money);
					edit_user.put("today_benefit", reward_money);
					edit_user.put("total_benefit", reward_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);
					edit_user.put("op_order_id", order_id);
					edit_user.put("state_type", BenefitParamConstants.state_type_1);
					String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(map, "sn"));
					if("epos".equals(pos_type)){
						edit_user.put("pos_type","03");
					}else{
						edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
					}
					edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserMoneyBenefit(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}

					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", user_id);
					record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
					record.put("sn", StringUtil.getMapValue(map, "sn"));
					record.put("money", reward_money);
					record.put("begin_date",act_date);
					record.put("end_date",enddate);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					if("epos".equals(pos_type)){
						record.put("pos_type", BenefitParamConstants.pos_type_03);
					}else{
						record.put("pos_type", BenefitParamConstants.pos_type_01);
					}
					num = activitySettlementMapper.insertUserTraposActivityRewardRecord(record);
					if(num != 1){
						throw new Exception("活动奖励记录异常:"+record.toString());
					}

					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
				}
			}
		}
	}
	/**
	 * mpos政策4 add byqh 201912
	 * @param list1
	 */
	public void policy_module3Mpos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0) {
			for (Map<String, Object> map : list1) {
				String act_date = map.get("act_date").toString();
				Date act_date_date =  DateUtils.parseDate(act_date);

				int module3_active_end_day = Integer.parseInt(map.get("module3_active_end_day").toString());
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module3_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCard2Mpos(StringUtil.getMapValue(map, "sn"),act_date,enddate);
				double amount_quantity = Double.parseDouble(map.get("module3_quantity").toString());

				if(amount>=amount_quantity){

					Map<String,Object> policy3map = new HashMap<>();
					policy3map.put("money",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("today_benefit",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("total_benefit",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("op_type",BenefitRecordTypeContants.benefit_record_type_04);
					policy3map.put("state_type",BenefitParamConstants.state_type_1);
					policy3map.put("pos_type",BenefitParamConstants.pos_type_02);
					policy3map.put("sn",StringUtil.getMapValue(map, "sn"));
					if("0".equals(StringUtil.getMapValue(map, "is_reward"))){
						Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(map, "user_id"));
						if(null!=userMap.get("referer_id")){
							policy3map.put("user_id",StringUtil.getMapValue(userMap, "referer_id"));
						}
					}else{
						policy3map.put("user_id",StringUtil.getMapValue(map, "user_id"));
					}
					policy3map.put("policy_id",StringUtil.getMapValue(map, "policy_id"));
					policy3map.put("begin_date",act_date);
					policy3map.put("end_date",enddate);
					policy3map.put("mer_id",StringUtil.getMapValue(map, "mer_id"));
					policy3map.put("mer_name",StringUtil.getMapValue(map, "mer_name"));
					policy3map.put("trade_quantity",amount_quantity);
					policy3map.put("trade_amount",amount);
					activitySettlementMapper.insertPolicy3Record(policy3map);
					//已完成该政策，执行下一个政策
					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));

				}
			}
		}
	}
	/**
	 * mpos政策4 add byqh 201912
	 * @param list1
	 */
	public void policy_module3Trapos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0) {
			for (Map<String, Object> map : list1) {
				String act_date = map.get("act_date").toString();
				Date act_date_date =  DateUtils.parseDate(act_date);

				int module3_active_end_day = Integer.parseInt(map.get("module3_active_end_day").toString());
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module3_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCard2Trapos(StringUtil.getMapValue(map, "sn"),act_date,enddate);
				double amount_quantity = Double.parseDouble(map.get("module3_quantity").toString());
//				activitySettlementMapper.updatePolicy3RecodeAmount(StringUtil.getMapValue(map, "sn"),amount);
				if(amount>=amount_quantity){

					Map<String,Object> policy3map = new HashMap<>();
					policy3map.put("money",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("today_benefit",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("total_benefit",StringUtil.getMapValue(map, "module3_reward"));
					policy3map.put("op_type",BenefitRecordTypeContants.benefit_record_type_04);
					policy3map.put("state_type",BenefitParamConstants.state_type_1);
					String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(map, "sn"));
					if("epos".equals(pos_type)){
						policy3map.put("pos_type", BenefitParamConstants.pos_type_03);
					}else{
						policy3map.put("pos_type", BenefitParamConstants.pos_type_01);
					}
					if("0".equals(StringUtil.getMapValue(map, "is_reward"))){
						Map<String,Object> userMap = agentUserInfoMapper.getAgentUserMapById(StringUtil.getMapValue(map, "user_id"));
						if(null!=userMap.get("referer_id")){
							policy3map.put("user_id",StringUtil.getMapValue(userMap, "referer_id"));
						}
					}else{
						policy3map.put("user_id",StringUtil.getMapValue(map, "user_id"));
					}
					policy3map.put("sn",StringUtil.getMapValue(map, "sn"));
					policy3map.put("policy_id",StringUtil.getMapValue(map, "policy_id"));
					policy3map.put("begin_date",act_date);
					policy3map.put("end_date",enddate);
					policy3map.put("mer_id",StringUtil.getMapValue(map, "mer_id"));
					policy3map.put("mer_name",StringUtil.getMapValue(map, "mer_name"));
					policy3map.put("trade_quantity",amount_quantity);
					policy3map.put("trade_amount",amount);
					int cnt = activitySettlementMapper.selectChooseReWard(map);
					if(cnt==0){
						activitySettlementMapper.insertPolicy3Record(policy3map);
					}
					//已完成该政策，执行下一个政策
					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));

				}
			}
		}
	}

	/**
	 * mpos政策4 add byqh 201912
	 * @param list1
	 */
	public void policy_module4Mpos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map : list1){

				String act_date = map.get("act_date").toString();
				Date act_date_date =  DateUtils.parseDate(act_date);

				int module4_active_end_day = Integer.parseInt(map.get("module4_active_end_day").toString());
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module4_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCardAllMpos(StringUtil.getMapValue(map, "sn"),act_date,enddate);
				double amount_quantity = Double.parseDouble(map.get("module4_quantity").toString());

				if(amount<amount_quantity){
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(map, "module4_deduct"));
					edit_user.put("user_id", StringUtil.getMapValue(map, "user_id"));
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					edit_user.put("pos_type", BenefitParamConstants.pos_type_02);
					edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}

					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(map, "user_id"));
					record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
					record.put("sn", StringUtil.getMapValue(map, "sn"));
					record.put("money", deduct_money);
					record.put("begin_date",act_date);
					record.put("end_date",enddate);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					record.put("pos_type",BenefitParamConstants.pos_type_02);
					num = activitySettlementMapper.insertUserMposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
					//完成考核更新标记
					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
				}
			}
		}
	}

	/**
	 * mpos政策4 add byqh 201912
	 * @param list1
	 */
	public void policy_module4Trapos(List<Map<String, Object>> list1) throws Exception{
		int num = 0;
		if(list1!=null && list1.size()>0){
			for(Map<String,Object> map : list1){

				String act_date = map.get("act_date").toString();
				Date act_date_date =  DateUtils.parseDate(act_date);

				int module4_active_end_day = Integer.parseInt(map.get("module4_active_end_day").toString());
				String enddate = DateUtils.parseDateToStr("yyyyMMdd",DateUtils.addDays(act_date_date,module4_active_end_day));

				double amount = activitySettlementMapper.getSumAmountCardAllTrapos(StringUtil.getMapValue(map, "sn"),act_date,enddate);
				double amount_quantity = Double.parseDouble(map.get("module4_quantity").toString());

				if(amount<amount_quantity){
					//更新用户扣除金额
					String order_id = StringUtil.getDateTimeAndRandomForID();
					Map<String, Object> edit_user = new HashMap<>();
					BigDecimal deduct_money = new BigDecimal(StringUtil.getMapValue(map, "module4_deduct"));
					edit_user.put("user_id", StringUtil.getMapValue(map, "user_id"));
					edit_user.put("deduct_money", deduct_money);
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_05);
					String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(map, "sn"));
					if("epos".equals(pos_type)){
						edit_user.put("pos_type", "03");
					}else{
						edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
					}
					edit_user.put("sn", StringUtil.getMapValue(map, "sn"));
					edit_user.put("up_date", TimeUtil.getDayString());
					edit_user.put("up_time", TimeUtil.getTimeString());
					num = manaUserInfoMapper.updateUserDeductMoney(edit_user);
					if(num != 1){
						throw new Exception("用户收益更新异常:"+edit_user.toString());
					}

					//记录活动奖励
					Map<String, Object> record = new HashMap<>();
					record.put("order_id", order_id);
					record.put("user_id", StringUtil.getMapValue(map, "user_id"));
					record.put("policy_id", StringUtil.getMapValue(map, "policy_id"));
					record.put("sn", StringUtil.getMapValue(map, "sn"));
					record.put("money", deduct_money);
					record.put("begin_date",act_date);
					record.put("end_date",enddate);
					record.put("cre_date", TimeUtil.getDayString());
					record.put("cre_time", TimeUtil.getTimeString());
					if("epos".equals(pos_type)){
						record.put("pos_type", BenefitParamConstants.pos_type_03);
					}else{
						record.put("pos_type", BenefitParamConstants.pos_type_01);
					}
					num = activitySettlementMapper.insertUserTraposDeductRecord(record);
					if(num != 1){
						throw new Exception("扣除记录异常:"+record.toString());
					}
					//完成考核更新标记
					activitySettlementMapper.update_t_sys_pos_policy_info(StringUtil.getMapValue(map, "user_id"),StringUtil.getMapValue(map, "id"));
				}
			}
		}
	}
	
}
