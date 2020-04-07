package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

/**
 * 系统公告管理
 * @author Administrator
 *
 */
public interface SysNoticeMapper {

	/***
	 * 得到未读消息add byqh 201912
	 * @param user_id
	 * @param news_type
	 * @param pre_unread_num
	 * @param unread_num
	 * @return
	 */
	@Insert("insert into t_news_read_state(user_id,news_type,pre_unread_num,unread_num,manager_id) values(#{user_id},#{news_type},#{pre_unread_num},#{unread_num},#{manager_id})")
	int insertNewsReadState(@Param("user_id") String user_id,@Param("news_type") String news_type,@Param("pre_unread_num") int pre_unread_num,@Param("unread_num") int unread_num,@Param("manager_id") String manager_id);

	/***
	 * 更新未读消息 add byqh 201912
	 * @param pre_unread_num
	 * @param unread_num
	 * @return
	 */
	@Update("update t_news_read_state set pre_unread_num=#{pre_unread_num},unread_num=#{unread_num} where user_id=#{user_id} and news_type=#{news_type}")
	int updateNewsReadState(@Param("pre_unread_num") int pre_unread_num,@Param("unread_num") int unread_num,@Param("user_id") String user_id,@Param("news_type") String news_type);

	/***
	 * 更新已读未读标志 add byqh 2010912
	 * @param map
	 * @return
	 */
	@Update("update t_news_read_state set unread_num=0,read_flag=#{map.read_flag} where user_id=#{map.sys_user_id} and news_type=#{map.news_type}")
	int updateNewsReadFlag(@Param("map") Map<String,Object> map);

	/***
	 * 更新已读未读标志0 add byqh 2010912
	 * @param list
	 * @param read_flag
	 * @param news_type
	 * @return
	 */
	int updateNewsReadFlagBatch(@Param("list") List<Map<String,Object>> list,@Param("read_flag") String read_flag,@Param("news_type") String news_type);


	/***
	 * 查询未读消息列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@Select("select * from t_news_read_state where news_type=#{map.news_type} and user_id=#{map.sys_user_id}")
	List<Map<String,Object>> selectNewsReadState(@Param("map") Map<String,Object> map);

	/***
	 * 查询费率申请列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@Select("select sum(cnt) from (select count(*) as cnt from t_user_apply_cardrate_trapos_record_info where user_id = #{map.sys_user_id} and status in('08','09') union " +
			"select count(*) as cnt from t_user_apply_cardrate_mpos_record_info where user_id = #{map.sys_user_id} and status in('08','09')) t")
	@ResultType(Integer.class)
	int selectUnReadRateApply(@Param("map") Map<String,Object> map);

	/***
	 * 钱柜学院列表 add byqh 201912
	 * @return
	 */
	@Select("select count(*) as cnt from t_sys_money_locker_college_info")
	@ResultType(Integer.class)
	int selectCollegeInfo();

	/***
	 * 查询待召回列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@Select("select sum(cnt) from (SELECT count(*) as cnt FROM t_user_trapos_recall_record_info WHERE acce_user_id = #{map.sys_user_id} AND status = '00' union" +
			" SELECT count(*) as cnt FROM t_user_mpos_recall_record_info WHERE acce_user_id = #{map.sys_user_id} AND status = '00') t")
	@ResultType(Integer.class)
	int selectUnReadRecall(@Param("map") Map<String,Object> map);

	
	/**
	 * 查询系统最新版本
	 * @param dataMap
	 * @return
	 */
	List<Map<String, Object>> getNewNotice(@Param("map") Map<String, Object> dataMap);

	
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNoticeList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getNoticeDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增读取记录
	 * @param map
	 * @return
	 */
	int addUserNoticeReadInfo(@Param("map") Map<String, Object> map);

	
}
