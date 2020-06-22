package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentUserCashRecordExcel;
import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;



/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
public interface AgentUserCashRecordMapper {


	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCashRecordList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 汇总取现记录数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserCashRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户取现记录列表
	 * @param params
	 * @return
	 */
	List<ManaUserCashRecord> selectAgentUserCashRecordList(@Param("map") Map<String, Object> params);


	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCashRecordDetailList(@Param("map") Map<String, Object> params);


	/**
	 * 查询用户待处理提现记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserCashRecordExcel> selectManaWaitUserCashRecordList(@Param("map") Map<String, Object> params);


	/**
	 * 更新提现状态
	 * @param cashDealMap
	 * @return
	 */
	int updateAgentUserCashRecordStatus(@Param("map") Map<String, Object> cashDealMap);


	/**
	 * 记录提现记录详情
	 * @param cashDealMap
	 * @return
	 */
	int addAgentUserCashRecordDetail(@Param("map") Map<String, Object> cashDealMap);


	/**
	 * 根据提现id查询提现详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserCashRecordById(@Param("cash_id") String cash_id);
	
}
