package com.example.longecological.mapper.merchant;

import java.util.List;
import java.util.Map;

import com.example.longecological.entity.R;
import org.apache.ibatis.annotations.*;

public interface MerchantManageMapper {

	/**
	 * 查询所有商户数量（传统POS）
	 * @param map
	 * @return
	 */
	int getAllMerchantTraditionalPosNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询优质商户数量（传统POS）
	 * @param map
	 * @return
	 */
	int getExcellentMerchantTraditionalPosNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活跃商户数量（传统POS）
	 * @param map
	 * @return
	 */
	int getActiveMerchantTraditionalPosNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询休眠商户数量（传统POS）
	 * @param map
	 * @return
	 */
	int getDormantMerchantTraditionalPosNum(@Param("map") Map<String, Object> map);

	/***
	 * 查询达标商户数量(传统POS) add byqh 201912
	 * @param map
	 * @return
	 */
	@Select("<script>" +
			"select count(distinct mer_id) as cnt from t_sys_pos_policy3_record where user_id=#{map.sys_user_id} " +
			"<choose>" +
			"<when test='map.pos_type!=null'>" +
			" and pos_type='03'"+
			"</when>"+
			"<otherwise>"+
			" and pos_type='01'"+
			"</otherwise>"+
			"</choose>"+
			"</script>")
	@ResultType(Integer.class)
	int getStandardMerchantTraditionalPosNum(@Param("map") Map<String,Object> map);
	
	/**
	 * 查询所有商户数量（MPOS）
	 * @param map
	 * @return
	 */
	int getAllMerchantMposNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询优质商户数量（MPOS）
	 * @param map
	 * @return
	 */
	int getExcellentMerchantMposNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活跃商户数量（MPOS）
	 * @param map
	 * @return
	 */
	int getActiveMerchantMposNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询休眠商户数量（MPOS）
	 * @param map
	 * @return
	 */
	int getDormantMerchantMposNum(@Param("map") Map<String, Object> map);


	/***
	 * 查询达标商户数量(MPOS) add byqh 201912
	 * @param map
	 * @return
	 */
	@Select("select count(distinct mer_id) as cnt from t_sys_pos_policy3_record where user_id=#{map.sys_user_id} and pos_type='02'")
	@ResultType(Integer.class)
	int getStandardMerchantMPosNum(@Param("map") Map<String,Object> map);
	
	/**
	 * 全部商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAllMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 优质商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getExcellentMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 活跃商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getActiveMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 休眠商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDormantMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 全部商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAllMerchantMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 优质商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getExcellentMerchantMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 活跃商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getActiveMerchantMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 休眠商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDormantMerchantMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询商户详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosDetail(@Param("map") Map<String, Object> map);

	/**
	 * 查询商户详情（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosTradeDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询商户详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询直推代理
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getReferAgencyList(@Param("map") Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getCompletePolicyMerchantTraditionalPos(@Param("map") Map<String,Object> map);

	/***
	 * 添加激活剩余天数add byqh 201912
	 * @param sn
	 * @return
	 */
	@Select("select (case when TIMESTAMPDIFF(day,CURRENT_DATE,str_to_date(module1_end_date,'%Y%m%d'))>=0 then TIMESTAMPDIFF(day,CURRENT_DATE,str_to_date(module1_end_date,'%Y%m%d')) else 0 end)  as expire_day " +
			" from t_sys_pos_policy_info where sn=#{sn} and isuse=1 and module_type=1")
	@ResultType(String.class)
	String getExpireDay1(@Param("sn") String sn);

	/***
	 * add byqh 201912
	 * 添加政策名称
	 * @param sn
	 * @return
	 */
	@Select("select GROUP_CONCAT(policy_name) as policy_name from t_sys_pos_policy_info where sn=#{sn}")
	@ResultType(String.class)
	String getPolicyName(@Param("sn") String sn);

	/***
	 * add byqh202003
	 * @param sn
	 * @return
	 */
	@Select("select GROUP_CONCAT(policy_name) as policy_name from t_sys_pos_policy_info where sn=#{sn} and module_type=3 and isuse=1")
	@ResultType(String.class)
	String getPolicy3Name(@Param("sn") String sn);

	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getCompletePolicyMerchantMPos(@Param("map") Map<String,Object> map);
	
	/**
	 * 查询代理头部信息（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getReferAgencyHeadTraditionalPosInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询代理商户列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getReferAgencyTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询代理头部信息（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getReferAgencyHeadMposInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询代理商户列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getReferAgencyMposList(@Param("map") Map<String, Object> map);


	/***
	 * 查询代理总MPOS机具数量,未激活数量,已激活数量
	 * @param user_id
	 * @return
	 */
	Map<String,Object> getMPOSAllocateCase(@Param("user_id") String user_id);

	/***
	 * 查询代理总传统POS机具数量,未激活数量,已激活数量
	 * @param user_id
	 * @return
	 */
	Map<String,Object> getTraditionalPosAllocateCase(@Param("user_id") String user_id);

	/***
	 * 查询代理总传统POS机具数量,未激活数量,已激活数量
	 * @param user_id
	 * @return
	 */
	Map<String,Object> getEposAllocateCase(@Param("user_id") String user_id);

	/***
	 * 查询直营MPOS总交易笔数,总交易金额
	 * @param map
	 * @return
	 */
	Map<String,Object> getMPOSTradeStatistical(@Param("map") Map<String,Object> map);

	/***
	 * 查询直营传统POS总交易笔数,总交易金额 byqh
	 * @param map
	 * @return
	 */
	Map<String,Object> getTraditionalPOSTradeStatistical(@Param("map") Map<String,Object> map);

	/**
	 * 根据设备号（机器编号）查询POS机详情
	 * @param sn
	 * @return
	 */
	@Select("select pos_type from t_sys_traditional_pos_info where sn=#{sn}")
	String getAgentSysEposInfoBySn(@Param("sn") String sn);

	/***
	 * 添加总交易额传统POS add byqh 201912
	 * @param user_id
	 * @return
	 */
	@Select("<script>" +
			"select IFNULL(cast(sum(performance) as char),'0') as amount from t_summary_traditional_pos_trans_all " +
			"where user_id=${user_id}"+
			"<choose>" +
			"<when test='pos_type!=null'>" +
			" and pos_type='epos'"+
			"</when>" +
			"<otherwise>" +
			" and pos_type is null"+
			"</otherwise>"+
			"</choose>"+
			"</script>")
	@ResultType(String.class)
	String getTraditionalTransAmountByAll(@Param("user_id") String user_id,@Param("pos_type") String pos_type);


	/***
	 * 添加总交易额MPOS add byqh 201912
	 * @param user_id
	 * @return
	 */
	@Select("select IFNULL(cast(sum(performance) as char),'0') as amount from t_summary_mpos_trans_all where user_id=${user_id}")
	@ResultType(String.class)
	String getMPOSTransAmountByAll(@Param("user_id") String user_id);

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912
	 * @param user_id,cre_month
	 * @return
	 */
	@Select("<script>" +
			"select IFNULL(cast(sum(performance) as char),'0') as merchant_performance,IFNULL(cast(TRUNCATE(sum(performance)/(select trade_num from t_summary_user_traditional_pos_benefit_all where user_id=${user_id} <choose><when test='pos_type!=null'> and pos_type='epos'</when><otherwise> and pos_type is null</otherwise></choose>),2) as char),'0') as avg_performance " +
			" from t_summary_traditional_pos_trans_month where user_id=${user_id} and cre_month=${cre_month} " +
			"<choose>"+
			"<when test='pos_type!=null'>" +
			" and pos_type='epos'"+
			"</when>" +
			"<otherwise>" +
			" and pos_type is null"+
			"</otherwise>"+
			"</choose>"+
			"</script>")
	Map<String,Object> getReferAgencyTraditionalPosTradeAmountAvg(@Param("user_id") String user_id,@Param("cre_month") String cre_month,@Param("pos_type") String pos_type);

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912 add byqh202003
	 * @param user_id,cre_month
	 * @return
	 */
	@Select("select IFNULL(cast(sum(performance) as char),'0') as merchant_performance,IFNULL(cast(TRUNCATE(sum(performance)/(select trade_num from t_summary_user_traditional_pos_benefit_all where user_id=${user_id} ),2) as char),'0') as avg_performance " +
			" from t_summary_traditional_pos_trans_month where user_id=${user_id} and cre_month=${cre_month} and pos_type='epos'")
	Map<String,Object> getReferAgencyEposTradeAmountAvg(@Param("user_id") String user_id,@Param("cre_month") String cre_month);

	/***
	 * 得到MPOS交易额和月均交易额 add byqh 201912
	 * @param user_id,cre_month
	 * @return
	 */
	@Select("select IFNULL(cast(sum(performance) as char),'0') as merchant_performance,IFNULL(cast(TRUNCATE(sum(performance)/(select trade_num from t_summary_user_mpos_benefit_all where user_id=${user_id} ),2) as char),'0') as avg_performance " +
			" from t_summary_mpos_trans_month where user_id=${user_id} and cre_month=${cre_month}")
	Map<String,Object> getReferAgencyMPosTradeAmountAvg(@Param("user_id") String user_id,@Param("cre_month") String cre_month);

	/***
	 * 更新商户姓名和电话传统POS add byqh 201912
	 * @param map
	 * @return
	 */
	@Update("update t_sys_traditional_pos_info set name=#{map.name},tel=#{map.tel} where sn=#{map.sn}")
	@ResultType(Integer.class)
	int updateMerchantNameAndTelTPOS(@Param("map") Map<String,Object> map);


    /***
     * 更新商户姓名和电话传统POS add byqh 201912
     * @param map
     * @return
     */
    @Update("update t_sys_mpos_info set name=#{map.name},tel=#{map.tel} where sn=#{map.sn}")
    @ResultType(Integer.class)
    int updateMerchantNameAndTelMPOS(@Param("map") Map<String,Object> map);
}
