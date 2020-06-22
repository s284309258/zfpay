package com.example.longecological.mapper.machines;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MachinesManageMapper {

	/**
	 * 获取待分配列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosAllocationList(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取待分配列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposAllocationList(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取待分配列表（流量卡）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTrafficCardAllocationList(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取待召回列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosRecallList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询召回MPOS机列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposRecallList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询召回流量卡列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTrafficCardRecallList(@Param("map") Map<String, Object> map);

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectUnbindTraditionalPos(@Param("map") Map<String,Object> map);

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectUnbindMpos(@Param("map") Map<String,Object> map);

	/***
	 * 查询政策3达标交易量列表 add byqh 201912
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPolicy3Record(@Param("map") Map<String,Object> map);


	List<Map<String,Object>> selectPolicy5Record(@Param("map") Map<String,Object> map);

	/***
	 * 查询政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPolicy3List(@Param("map") Map<String,Object> map);

	/***
	 * 查询政策3列表所有字段 add byqh 201912
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPolicy3RecordAll(@Param("map") Map<String,Object> map);


	List<Map<String,Object>> selectPolicy5RecordAll(@Param("map") Map<String,Object> map);

	/***
	 * 查询流量卡属组 add byqh 201912
	 * @param card_list
	 * @return
	 */
	List<Map<String,Object>> getUserTrafficBelongBySN(@Param("card_list") String card_list);

	/***
	 * 查询分配政策的名称 add byqh 201912
	 * @param batch_no
	 * @return
	 */
	String selectAllocatePolicyName(@Param("batch_no") String batch_no);

	/***
	 * 更新用户收益 add byqh 201912
	 * @param map
	 * @return
	 */
	int updateUserMoneyBenefit(@Param("map") Map<String,Object> map);

	/***
	 * 保存活动奖励记录（传统POS） add byqh 201912
	 * @param map
	 * @return
	 */
	int insertUserTraposActivityRewardRecord(@Param("map") Map<String,Object> map);

	/***
	 * 保存活动奖励记录（MPOS） add byqh 201912
	 * @param map
	 * @return
	 */
	int insertUserMposActivityRewardRecord(@Param("map") Map<String,Object> map);

	/***
	 * 更新商户达标选择的奖励 add byqh 201912
	 * @param map
	 * @return
	 */
	@Update("update t_sys_pos_policy3_record set choose=#{map.id} where mer_id=#{map.mer_id}")
	int updatePolicy3RecordChooseField(@Param("map") Map<String,Object> map);

	@Update("update t_sys_pos_policy5_record set choose=#{map.id} where mer_id=#{map.mer_id}")
	int updatePolicy5RecordChooseField(@Param("map") Map<String,Object> map);
	
	/**
	 * 添加新的分配记录（传统POS）
	 * @param map
	 * @return
	 */
	int addAllocationTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改旧的分配记录及归属（传统POS）
	 * @param map
	 * @return
	 */
	int updateOldAllocationTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改归属状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateTraditionalPosStateStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加召回记录（传统POS）
	 * @param map
	 * @return
	 */
	int addRecallTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加解绑记录（传统POS）
	 * @param map
	 * @return
	 */
	int addUnbindTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加新的分配记录（MPOS）
	 * @param map
	 * @return
	 */
	int addAllocationMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改旧的分配记录及归属（MPOS）
	 * @param map
	 * @return
	 */
	int updateOldAllocationMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改归属状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateMposStateStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加召回记录（MPOS）
	 * @param map
	 * @return
	 */
	int addRecallMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加解绑记录（MPOS）
	 * @param map
	 * @return
	 */
	int addUnbindMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加新的分配记录（流量卡）
	 * @param map
	 * @return
	 */
	int addAllocationTrafficCard(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改旧的分配记录及归属（流量卡）
	 * @param map
	 * @return
	 */
	int updateOldAllocationTrafficCard(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改归属状态（流量卡）
	 * @param map
	 * @return
	 */
	int updateTrafficCardStateStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 添加召回记录（流量卡）
	 * @param map
	 * @return
	 */
	int addRecallTrafficCard(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询直推代理
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRefererAgency(@Param("map") Map<String, Object> map);

	/***
	 * add byqh 202006
	 * @param sn
	 * @return
	 */
	@Select("select policy_id,policy_name from t_sys_pos_policy_info where sn=#{sn} and module_type=2")
	List<Map<String, Object>> getPolicy2BySN(@Param("sn") String sn);

	/***
	 * add byqh 202006
	 * @param sn
	 * @return
	 */
	@Select("select policy_id,policy_name from t_sys_pos_policy_info where sn=#{sn} and module_type=3")
	List<Map<String, Object>> getPolicy3BySN(@Param("sn") String sn);
	
	/**
	 * 查询解绑记录（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUnbindRecordTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询解绑记录（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUnbindRecordMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询分配记录（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAllocationTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询分配详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getAllocationTraditionalPosDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改分配记录（传统POS）
	 * @param map
	 * @return
	 */
	int updateAllocationTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询分配记录（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAllocationMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询分配详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getAllocationMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改分配记录（MPOS）
	 * @param map
	 * @return
	 */
	int updateAllocationMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询分配记录（流量卡）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAllocationTrafficCardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询召回记录（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRecallTraditionalPosList(@Param("map") Map<String, Object> map);

	/**
	 * 查询召回记录（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRecallEposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateRecallTraditionalPosStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回人的归属状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateRecallTraditionalPosSendState(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新接收人的归属状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateRecallTraditionalPosAcceState(@Param("map") Map<String, Object> map);

	/***
	 * 更新分配记录，删除召回的分配记录 byqh202002
	 * @param ids
	 * @param sys_user_id
	 * @return
	 */
	@Update("delete from t_user_pos_batch_allocate where sn " +
			" in(select sn from t_user_trapos_recall_record_info where FIND_IN_SET(id,#{ids_list}) ) and user_id=#{sys_user_id}")
	int updateBatchAllocate(@Param("ids_list") String ids,@Param("sys_user_id") String sys_user_id);
	
	/**
	 * 查询召回记录（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRecallMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateRecallMposStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回人的归属状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateRecallMposSendState(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新接收人的归属状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateRecallMposAcceState(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询召回记录（流量卡）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRecallTrafficCardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回状态（流量卡）
	 * @param map
	 * @return
	 */
	int updateRecallTrafficCardStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新召回人的归属状态（流量卡）
	 * @param map
	 * @return
	 */
	int updateRecallTrafficCardSendState(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新接收人的归属状态（流量卡）
	 * @param map
	 * @return
	 */
	int updateRecallTrafficCardAcceState(@Param("map") Map<String, Object> map);
	
	/**
	 * 校验分配参数（传统POS）
	 * @param map
	 * @return
	 */
	int checkAllocationTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 校验分配参数（MPOS）
	 * @param map
	 * @return
	 */
	int checkAllocationMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 校验解绑参数（传统POS）
	 * @param map
	 * @return
	 */
	int checkUnbindTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 校验解绑参数（MPOS）
	 * @param map
	 * @return
	 */
	int checkUnbindMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询修改分配详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getCheckEditAllocationTraditionalPosDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询修改分配详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getCheckEditAllocationMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询是否有解绑审核记录（传统POS）
	 * @param map
	 * @return
	 */
	int checkExistsUnbindTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询是否有解绑审核记录（MPOS）
	 * @param map
	 * @return
	 */
	int checkExistsUnbindMpos(@Param("map") Map<String, Object> map);

	/***
	 * add byqh202003
	 * @param map
	 * @return
	 */
	int checkIsReward(@Param("map") Map<String, Object> map);

	/***
	 * add byqh202003
	 * @param map
	 * @return
	 */
	int checkRepeatSetReward(@Param("map") Map<String, Object> map);

	/***
	 * add byqh202003
	 * @param map
	 * @return
	 */
	String checkIsRewardMpos(@Param("map") Map<String, Object> map);

	/***
	 * add byqh202003
	 * @param map
	 * @return
	 */
	String checkIsPolicy3(@Param("map") Map<String, Object> map);


	@Update("update t_user_traditional_pos_info set is_reward=#{map.is_reward} where sn=#{map.sn} and user_id=#{map.user_id}")
	int policy2OnOff(@Param("map") Map<String, Object> map);

	/***
	 * add byqh202003
	 * @param map
	 * @return
	 */
	int checkRepeatSetRewardMpos(@Param("map") Map<String, Object> map);

	/***
	 * 分配pos时插入批次表，记录分配属于哪个批次方便按批次修改结算价格 add byqh
	 * @param map
	 * @return
	 */
	int insertPosBatchAllocate(@Param("map") Map<String,Object> map);

	/***
	 * 查询分配的批次 add byqh
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPosBatchAllocate(@Param("map") Map<String,Object> map);

	/***
	 * 查询批次详情的方法 add byqh
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectPosBatchAllocateDetail(@Param("map") Map<String,Object> map);

	/***
	 * 新增查询批次SN列表的方法 add byqh
	 * @param map
	 * @return
	 */
	Map<String,Object> selectPosBatchSNList(@Param("map") Map<String,Object> map);

	/***
	 * 检查是否机器带有未完成的政策，有则不可以召回 add byqh 201912
	 * @param sn_list
	 * @return
	 */
	int checkPosPolicy(@Param("sn_list") String sn_list);
	
}
