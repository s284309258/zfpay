package com.ruoyi.project.devemana.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.notice.domain.ManaSysMoneyLockerCollege;
import com.ruoyi.project.devemana.notice.mapper.ManaSysMoneyLockerCollegeMapper;
import com.ruoyi.project.devemana.notice.service.ManaSysMoneyLockerCollegeService;


/**
 * 管理员=====钱柜学院 服务层实现
 * @author Administrator
 *
 */
@Service
public class ManaSysMoneyLockerCollegeServiceImpl implements ManaSysMoneyLockerCollegeService
{
	@Autowired
    private RedisUtils redisUtils;
	
	@Autowired
    private ManaSysMoneyLockerCollegeMapper manaSysMoneyLockerCollegeMapper;

	@Autowired
	private ManaSysNoticeMapper sysNoticeMapper;

    
    /**
     * 查询钱柜学院 列表
     */
	@Override
	public List<Map<String, Object>> getManaSysMoneyLockerCollegeList(Map<String, Object> params) {
		return manaSysMoneyLockerCollegeMapper.getManaSysMoneyLockerCollegeList(params);
	}

	
	/**
	 * 查询钱柜学院 详情
	 */
	@Override
	public ManaSysMoneyLockerCollege getManaSysMoneyLockerCollegeById(String id) {
		return manaSysMoneyLockerCollegeMapper.getManaSysMoneyLockerCollegeById(id);
	}

	
	/**
	 * 新增钱柜学院 
	 */
	@Override
	public R addManaSysMoneyLockerCollege(Map<String, Object> params) {
		try {
			//（1）新增新闻
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = manaSysMoneyLockerCollegeMapper.addManaSysMoneyLockerCollege(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			//（2）清除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_money_locker_college_list+"0");
			//更新小红点 add begin byqh 201912
			Map<String,Object> mmm = new HashMap<>();
			mmm.put("read_flag","0");
			mmm.put("manager_id",ShiroUtils.getUserId().toString());
			mmm.put("news_type","collegeFlag");
			sysNoticeMapper.updateNewsReadFlagManagerID(mmm);
			//更新小红点 add end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除钱柜学院 
	 */
	@Override
	public R batchRemoveManaSysMoneyLockerCollege(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] college_ids = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String college_id : college_ids)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysMoneyLockerCollege(college_id);
            if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        //清除缓存
        redisUtils.remove(RedisNameConstants.zfpay_sys_money_locker_college_list+"0");
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeManaSysMoneyLockerCollege(String college_id) {
		try {
			int i = manaSysMoneyLockerCollegeMapper.deleteManaSysMoneyLockerCollege(college_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "新闻编号"+college_id+"：删除失败");
			}
			//清除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_money_locker_college+college_id);
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "新闻编号"+college_id+"：删除异常");
		} 
	}
	
    
}
