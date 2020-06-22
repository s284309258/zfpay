package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface ActivitySettlementMapper {

	/**
	 * 高返现活动列表(传统POS)
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getTraposHighCashbackActivity(@Param("date") String date);
	
	/**
	 * 高返现用户申请列表(传统POS)
	 * @param activity_id
	 * @return
	 */
	List<Map<String, Object>> getTraposApplyHighCashbackActivityList(@Param("activity_id") String activity_id);
	
	/**
	 * 查询高返现活动POS机列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposHighCashbackActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存活动奖励记录（传统POS）
	 * @param map
	 * @return
	 */
	int insertUserTraposActivityRewardRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新POS参与活动状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateUserTraposActivityStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新活动结束状态(传统POS)
	 * @param activity_id
	 * @return
	 */
	int updateTraposActivityEndStatus(@Param("activity_id") String activity_id);
	
	/**
	 * 交易量活动列表(传统POS)
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getTraposVolumeActivity(@Param("date") String date);
	
	/**
	 * 交易量用户申请列表(传统POS)
	 * @param activity_id
	 * @return
	 */
	List<Map<String, Object>> getTraposApplyVolumeActivityList(@Param("activity_id") String activity_id);
	
	/**
	 * 查询交易量活动POS机列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposVolumeActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 高返现活动列表(MPOS)
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getMposHighCashbackActivity(@Param("date") String date);
	
	/**
	 * 高返现用户申请列表(MPOS)
	 * @param activity_id
	 * @return
	 */
	List<Map<String, Object>> getMposApplyHighCashbackActivityList(@Param("activity_id") String activity_id);
	
	/**
	 * 查询高返现活动POS机列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposHighCashbackActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存活动奖励记录（MPOS）
	 * @param map
	 * @return
	 */
	int insertUserMposActivityRewardRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新POS参与活动状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateUserMposActivityStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新活动结束状态(传统POS)
	 * @param activity_id
	 * @return
	 */
	int updateMposActivityEndStatus(@Param("activity_id") String activity_id);
	
	/**
	 * 交易量活动列表(MPOS)
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getMposVolumeActivity(@Param("date") String date);
	
	/**
	 * 交易量用户申请列表(MPOS)
	 * @param activity_id
	 * @return
	 */
	List<Map<String, Object>> getMposApplyVolumeActivityList(@Param("activity_id") String activity_id);
	
	/**
	 * 查询交易量活动POS机列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposVolumeActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未达标任务详情(传统POS)
	 * @param assess_id
	 * @return
	 */
	Map<String, Object> getTraposAssessTask(@Param("assess_id") String assess_id);
	
	/**
	 * 查询未达标激活量任务（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposActivateAssessTaskList(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存未达标记录(传统POS)
	 * @param map
	 * @return
	 */
	int insertUserTraposDeductRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新任务结束状态(传统POS)
	 * @param map
	 * @return
	 */
	int updateTraposAssessTaskEndStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未达标交易量任务（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposVolumeAssessTaskList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未达标任务详情(MPOS)
	 * @param assess_id
	 * @return
	 */
	Map<String, Object> getMposAssessTask(@Param("assess_id") String assess_id);
	
	/**
	 * 查询未达标激活量任务（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposActivateAssessTaskList(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存未达标记录(MPOS)
	 * @param map
	 * @return
	 */
	int insertUserMposDeductRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新任务结束状态(MPOS)
	 * @param map
	 * @return
	 */
	int updateMposAssessTaskEndStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未达标交易量任务（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposVolumeAssessTaskList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询是否有返现记录（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraposCashBackRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询是否有返现记录（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposCashBackRecord(@Param("map") Map<String, Object> map);



	/***
	 * 激活期考核List,包括mpos机的直属人信息 byqh 201912
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id " +
			" from t_sys_pos_policy_info t,t_user_mpos_info tt,t_sys_mpos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.iscomplete=0 and t.module_type=1 and t.isuse=1 " +
			" and tt.state_status=1 and t.iscomplete=0 and ttt.act_status=0 and t.module1_end_date<CURRENT_DATE")
	List<Map<String,Object>> getModule1Mpos();

	/***
	 * 激活期考核List,包括传统pos机的直属人信息 byqh 201912
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id " +
			" from t_sys_pos_policy_info t,t_user_traditional_pos_info tt,t_sys_traditional_pos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.iscomplete=0 and t.module_type=1 " +
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=0 and t.module1_end_date<CURRENT_DATE")
	List<Map<String,Object>> getModule1Trapos();

	/***
	 * 分期达标返现List,包括mpos机的直属人信息 byqh 201912 update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select(" select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id,date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date " +
			" from t_sys_pos_policy_info t,t_user_mpos_info tt,t_sys_mpos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=2"+
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1"+
			" and CURRENT_DATE between date_add(ttt.act_date, interval t.module2_active_begin_day day) and date_add(ttt.act_date, interval t.module2_active_end_day day)")
	List<Map<String,Object>> getModule2Mpos();

	/***
	 * 分期达标返现List,包括传统pos机的直属人信息 byqh 201912 update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select(" select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day," +
			" t.module4_quantity,t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id,date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date " +
			" from t_sys_pos_policy_info t,t_user_traditional_pos_info tt,t_sys_traditional_pos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=2"+
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1"+
			" and CURRENT_DATE between date_add(ttt.act_date, interval t.module2_active_begin_day day) and date_add(ttt.act_date, interval t.module2_active_end_day day)")
	List<Map<String,Object>> getModule2Trapos();

	/***
	 *交易达标返现byqh 201912 update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity,tt.is_reward," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id," +
			" date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date,ttt.name as mer_name,ttt.sn as mer_id " +
			" from t_sys_pos_policy_info t,t_user_mpos_info tt,t_sys_mpos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=3" +
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1 " +
			" and CURRENT_DATE<=date_add(ttt.act_date, interval t.module3_active_end_day day)")
	List<Map<String,Object>> getModule3Mpos();

	/***
	 * 交易达标返现byqh 201912 update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity,tt.is_reward,ttt.pos_type," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id," +
			" date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date,mer_id,mer_name " +
			" from t_sys_pos_policy_info t,t_user_traditional_pos_info tt,t_sys_traditional_pos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=3" +
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1 " +
			" and CURRENT_DATE<=date_add(ttt.act_date, interval t.module3_active_end_day day)")
	List<Map<String,Object>> getModule3Trapos();

	/***
	 * 交易量未达到xx则扣款update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id,date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date " +
			" from t_sys_pos_policy_info t,t_user_mpos_info tt,t_sys_mpos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=4" +
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1 " +
			" and CURRENT_DATE>date_add(ttt.act_date, interval t.module4_active_end_day day)")
	List<Map<String,Object>> getModule4Mpos();

	/***
	 * 交易量未达到xx则扣款update byqh202003 date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date
	 * @return
	 */
	@Select("select t.id,t.policy_name,t.sn,t.module1_pickup_date,t.module1_end_date,t.module1_deduct,t.module2_active_begin_day,t.module2_quantity," +
			" t.module2_active_end_day,t.module2_reward,t.module3_active_end_day,t.module3_quantity,t.module3_reward,t.module4_active_end_day,t.module4_quantity," +
			" t.module4_deduct,t.module_type,t.isuse,t.iscomplete,t.remark,t.create_by,t.create_date,t.policy_id,t.complete_date,tt.user_id,date_format(date_sub(ttt.act_date,interval 1 day),'%Y%m%d') as act_date " +
			" from t_sys_pos_policy_info t,t_user_traditional_pos_info tt,t_sys_traditional_pos_info ttt where t.sn=tt.sn and ttt.sn=tt.sn and t.module_type=4" +
			" and t.isuse=1 and t.iscomplete=0 and tt.state_status=1 and ttt.act_status=1 " +
			" and CURRENT_DATE>date_add(ttt.act_date, interval t.module4_active_end_day day)")
	List<Map<String,Object>> getModule4Trapos();


	/***
	 * 插入政策3记录表,提供app端领取奖励add byqh 201912
	 * @param map
	 * @return
	 */
	@Insert("insert into t_sys_pos_policy3_record(money,today_benefit,op_type,state_type,pos_type,sn,user_id,policy_id,begin_date,end_date,mer_id,mer_name,trade_quantity,trade_amount)" +
			" values(#{map.money},#{map.today_benefit},#{map.op_type},#{map.state_type},#{map.pos_type},#{map.sn},#{map.user_id},#{map.policy_id},#{map.begin_date},#{map.end_date}," +
			" #{map.mer_id},#{map.mer_name},#{map.trade_quantity},#{map.trade_amount})")
	int insertPolicy3Record(@Param("map") Map<String,Object> map);

	@Select("select count(1) as cnt from t_sys_pos_policy3_record where sn=#{map.sn} and choose>0")
	@ResultType(Integer.class)
	int selectChooseReWard(@Param("map") Map<String,Object> map);

	/***
	 * add byqh 202003
	 * @return
	 */
	@Update("update t_sys_pos_policy3_record set trade_amount=#{amount} where sn=#{sn}")
	int updatePolicy3RecodeAmount(@Param("sn") String sn,@Param("amount") Double amount);

	/***
	 * 查询Mpos贷记卡交易总额 add byqh 201912
	 * @param sn
	 * @param begin
	 * @param end
	 * @return
	 */
	@Select("select coalesce(sum(trans_amount),0) as amount from t_data_mpos_transaction_record where sn=#{sn} and card_type in(2,4) and cre_date between #{begin} and #{end}")
	@ResultType(Double.class)
	double getSumAmountCard2Mpos(@Param("sn") String sn,@Param("begin") String begin,@Param("end") String end);

	/***
	 * 查询传统pos贷记卡交易总额add byqh 201912
	 * @param sn
	 * @param begin
	 * @param end
	 * @return
	 */
	@Select("select coalesce(sum(trans_amount),0) as amount from t_data_trapos_transaction_record where sn=#{sn} and card_type in(2,4) and cre_date between #{begin} and #{end}")
	@ResultType(Double.class)
	double getSumAmountCard2Trapos(@Param("sn") String sn,@Param("begin") String begin,@Param("end") String end);

	/***
	 * 查询mpos所有卡交易总额add byqh 201912
	 * @param sn
	 * @param begin
	 * @param end
	 * @return
	 */
	@Select("select coalesce(sum(trans_amount),0) as amount from t_data_mpos_transaction_record where sn=#{sn} and cre_date between #{begin} and #{end}")
	@ResultType(Double.class)
	double getSumAmountCardAllMpos(@Param("sn") String sn,@Param("begin") String begin,@Param("end") String end);

	/***
	 * 查询传统pos所有卡交易总额add byqh 201912
	 * @param sn
	 * @param begin
	 * @param end
	 * @return
	 */
	@Select("select coalesce(sum(trans_amount),0) as amount from t_data_trapos_transaction_record where sn=#{sn} and cre_date between #{begin} and #{end}")
	@ResultType(Double.class)
	double getSumAmountCardAllTrapos(@Param("sn") String sn,@Param("begin") String begin,@Param("end") String end);



	/***
	 * 更新政策考核状态add byqh 201912
	 * @param id
	 * @return
	 */
	@Update("update t_sys_pos_policy_info set iscomplete=1,complete_date=CURRENT_DATE,user_id=#{user_id} where id=#{id}")
	int update_t_sys_pos_policy_info(@Param("user_id") String user_id,@Param("id") String id);

}
