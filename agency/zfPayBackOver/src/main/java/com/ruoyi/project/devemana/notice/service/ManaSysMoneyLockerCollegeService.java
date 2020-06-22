package com.ruoyi.project.devemana.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.notice.domain.ManaSysMoneyLockerCollege;


/**
 * 管理员====钱柜学院 服务层
 * @author Administrator
 *
 */
public interface ManaSysMoneyLockerCollegeService
{
   
    /**
     * 查询钱柜学院列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getManaSysMoneyLockerCollegeList(Map<String, Object> params);
    
    
    /**
     * 根据公告id查询钱柜学院
     * @param id
     * @return
     */
    public ManaSysMoneyLockerCollege getManaSysMoneyLockerCollegeById(String id);


    /**
     * 添加钱柜学院
     * @param params
     * @return
     */
	public R addManaSysMoneyLockerCollege(Map<String, Object> params);


	/**
	 * 批量删除钱柜学院
	 * @param ids
	 * @return
	 */
	public R batchRemoveManaSysMoneyLockerCollege(String ids);
}
