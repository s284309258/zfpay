package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;
import org.apache.ibatis.annotations.Select;


/**
 * 管理员======》系统费率参数管理
 * @author Administrator
 *
 */
public interface ManaSysParamRateMapper {


	/**
	 * 查询系统费率参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamRateList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统费率参数列表
	 * @param params
	 * @return
	 */
	List<ManaSysParamRate> selectManaSysParamRateList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询系统费率参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysParamRateById(@Param("param_rate_id") String param_rate_id);


	/**
	 * 更新保存系统费率参数
	 * @param params
	 * @return
	 */
	int updateManaSysParamRate(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 新增保存系统费率参数
	 * @param map
	 * @return
	 */
	int addManaSysParamRate(@Param("map") Map<String, Object> params);


	/**
	 * 根据id删除系统费率参数
	 * @param string
	 * @return
	 */
	int delManaSysParamRateById(@Param("map") Map<String, Object> params);


	/**
	 * 查询指定类型的费率是否存在
	 * @param sysParamRateType1
	 * @param credit_card_rate
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamRateIsValid(@Param("type") String type, @Param("rate") String rate);


	/**
	 * 根据类型查询参数值列表
	 * @param type
	 * @return
	 */
	List<ManaSysParamRate> getManaParamRateListByType(@Param("type") String type);
	@Select("select min(card_settle_price) as card_settle_price,min(cloud_settle_price) as cloud_settle_price,max(single_profit_rate) as single_profit_rate," +
			" max(cash_back_rate) as cash_back_rate from t_user_mpos_info where user_id=#{user_id} and del=0")
	Map<String,Object> getMinRateByMpos(@Param("user_id") Integer user_id);


	@Select("select min(card_settle_price) as card_settle_price,min(card_settle_price_vip) as card_settle_price_vip,min(cloud_settle_price) as cloud_settle_price," +
			" min(weixin_settle_price) as weixin_settle_price,min(zhifubao_settle_price) as zhifubao_settle_price,max(single_profit_rate) as single_profit_rate," +
			" max(cash_back_rate) as cash_back_rate,min(if(mer_cap_fee='',50,mer_cap_fee)) AS mer_cap_fee " +
			" from t_user_traditional_pos_info where user_id=#{user_id} and del=0 and pos_type is null")
	Map<String,Object> getMinRateByTraspos(@Param("user_id") Integer user_id);

	@Select("select min(card_settle_price) as card_settle_price,min(card_settle_price_vip) as card_settle_price_vip,min(cloud_settle_price) as cloud_settle_price," +
			" min(weixin_settle_price) as weixin_settle_price,min(zhifubao_settle_price) as zhifubao_settle_price,max(single_profit_rate) as single_profit_rate," +
			" max(cash_back_rate) as cash_back_rate,min(if(mer_cap_fee='',50,mer_cap_fee)) AS mer_cap_fee " +
			" from t_user_traditional_pos_info where user_id=#{user_id} and del=0 and pos_type='epos'")
	Map<String,Object> getMinRateByEpos(@Param("user_id") Integer user_id);

}
