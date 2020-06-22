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
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.notice.domain.ManaSysNews;
import com.ruoyi.project.devemana.notice.mapper.ManaSysNewsMapper;
import com.ruoyi.project.devemana.notice.service.ManaSysNewsService;


/**
 * 管理员=====新闻资讯 服务层实现
 * @author Administrator
 *
 */
@Service
public class ManaSysNewsServiceImpl implements ManaSysNewsService
{
	
	@Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private ManaSysNewsMapper manaSysNewsMapper;

    @Autowired
    private ManaSysNoticeMapper sysNoticeMapper;

    
    /**
     * 查询新闻资讯列表
     */
	@Override
	public List<Map<String, Object>> getManaSysNewsList(Map<String, Object> params) {
		return manaSysNewsMapper.getManaSysNewsList(params);
	}

	
	/**
	 * 查询新闻资讯详情
	 */
	@Override
	public ManaSysNews getManaNewsById(String id) {
		return manaSysNewsMapper.getManaNewsById(id);
	}

	
	/**
	 * 新增新闻资讯 update byqh 201912
	 */
	@Override
	public R addManaSysNews(Map<String, Object> params) {
		try {
			//（1）获取当前队列长度
			int size = manaSysNewsMapper.getManaSysNewsSize();
			//（2）新增新闻
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = manaSysNewsMapper.addManaSysNews(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			//（3）清除缓存
			this.delCache(size);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}

	
	/**
	 * 更新系统新闻资讯
	 */
	@Override
	public R editManaSysNews(Map<String, Object> params) {
		try {
			//（1）获取当前队列长度
			int size = manaSysNewsMapper.getManaSysNewsSize();
			//（2）更新新闻
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysNewsMapper.updateManaSysNews(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			//（3）清除缓存
			this.delCache(size);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 批量删除系统新闻资讯
	 */
	@Override
	public R batchRemoveManaSysNews(String ids) {
		//（1）获取当前队列长度
		int size = manaSysNewsMapper.getManaSysNewsSize();
		
		//（2）处理
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] notice_ids = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String notice_id : notice_ids)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysNotice(notice_id);
            if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        
        //（3）清除缓存
		this.delCache(size);
		
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeManaSysNotice(String notice_id) {
		try {
			int i = manaSysNewsMapper.deleteManaSysNews(notice_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "新闻编号"+notice_id+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "新闻编号"+notice_id+"：删除异常");
		} 
	}
	
	
	/**
	 * 清除缓存
	 * @param size
	 */
	private void delCache(int size){
		if(size > 0){
			int page = size/SysParamConstant.page_size + 1;
			for(int i=1; i<=page; i++){
				String key = RedisNameConstants.zfpay_sys_news_info_list + String.valueOf(i);
				redisUtils.remove(key);
			}
		}
	}
	
    
}
