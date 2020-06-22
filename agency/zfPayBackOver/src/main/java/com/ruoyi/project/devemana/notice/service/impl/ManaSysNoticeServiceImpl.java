package com.ruoyi.project.devemana.notice.service.impl;

import java.util.List;
import java.util.Map;

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
import com.ruoyi.project.devemana.notice.domain.ManaSysNotice;
import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import com.ruoyi.project.devemana.notice.service.ManaSysNoticeService;


/**
 * 管理员====系统公告 服务层实现
 * @author Administrator
 *
 */
@Service
public class ManaSysNoticeServiceImpl implements ManaSysNoticeService
{
	
	@Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private ManaSysNoticeMapper manaSysNoticeMapper;

    
    /**
     * 查询公告列表
     */
	@Override
	public List<Map<String, Object>> getManaSysNoticeList(Map<String, Object> params) {
		return manaSysNoticeMapper.getManaSysNoticeList(params);
	}

	
	/**
	 * 查询公告详情
	 */
	@Override
	public ManaSysNotice getManaNoticeById(String id) {
		return manaSysNoticeMapper.getManaNoticeById(id);
	}

	
	/**
	 * 新增公告
	 */
	@Override
	public R addManaSysNotice(Map<String, Object> params) {
		try {
			//（1）获取当前队列长度
			int size = manaSysNoticeMapper.getManaSysNoticeSize();
			//（2）新增公告
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = manaSysNoticeMapper.addManaSysNotice(params);
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
	 * 清除缓存
	 * @param size
	 */
	private void delCache(int size) {
		//删除最新公告的缓存
		redisUtils.remove(RedisNameConstants.zfpay_sys_notice_info_new);
		//删除公告列表的缓存
		if(size > 0){
			int page = size/SysParamConstant.page_size + 1;
			for(int i=1; i<=page; i++){
				String key = RedisNameConstants.zfpay_sys_notice_info_list + String.valueOf(i);
				redisUtils.remove(key);
			}
		}
	}


	/**
	 * 更新系统公告
	 */
	@Override
	public R editManaSysNotice(Map<String, Object> params) {
		try {
			//（1）获取当前队列长度
			int size = manaSysNoticeMapper.getManaSysNoticeSize();
			//（2）更新公告
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysNoticeMapper.updateManaSysNotice(params);
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
	 * 批量删除系统公告
	 */
	@Override
	public R batchRemoveManaSysNotice(String ids) {
		//（1）获取当前队列长度
		int size = manaSysNoticeMapper.getManaSysNoticeSize();
		
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
			int i = manaSysNoticeMapper.deleteManaSysNotice(notice_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "公告编号"+notice_id+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "公告编号"+notice_id+"：删除异常");
		} 
	}
	
    
}
