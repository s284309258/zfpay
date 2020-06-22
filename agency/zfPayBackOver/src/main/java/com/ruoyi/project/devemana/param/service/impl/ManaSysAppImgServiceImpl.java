package com.ruoyi.project.devemana.param.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.domain.ManaSysAppImg;
import com.ruoyi.project.devemana.param.mapper.ManaSysAppImgMapper;
import com.ruoyi.project.devemana.param.service.ManaSysAppImgService;

/**
 * APP图片 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaSysAppImgServiceImpl implements ManaSysAppImgService 
{
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ManaSysAppImgMapper manaSysAppImgMapper;
	@Autowired
	private ManaSysNoticeMapper manaSysNoticeMapper;

	
	/**
	 * 查询APP图片列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysAppImgList(Map<String, Object> params) {
		return manaSysAppImgMapper.getManaSysAppImgList(params);
	}

	
	/**
	 * 导出APP图片列表
	 */
	@Override
	public List<ManaSysAppImg> selectManaSysAppImgList(Map<String, Object> params) {
		return manaSysAppImgMapper.selectManaSysAppImgList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getManaSysAppImgById(String id) {
		return manaSysAppImgMapper.getManaSysAppImgById(id);
	}


	/**
	 * 编辑APP图片
	 */
	@Override
	public R editManaSysAppImg(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysAppImgMapper.updateManaSysAppImg(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			redisUtils.remove(RedisNameConstants.zfpay_sys_app_img+params.get("img_type"));
			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("news_type","appImgFlag");
			hashMap.put("manager_id",ShiroUtils.getUserId());
			manaSysNoticeMapper.updateNewsReadFlagManagerID(hashMap);
			//add 小红点消息置为未读 end byqh 201912

			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 新增APP图片
	 */
	@Override
	public R addManaSysAppImg(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			
			int i = manaSysAppImgMapper.addManaSysAppImg(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			redisUtils.remove(RedisNameConstants.zfpay_sys_app_img+params.get("img_type"));

			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("news_type","appImgFlag");
			hashMap.put("manager_id",ShiroUtils.getUserId());
			manaSysNoticeMapper.updateNewsReadFlagManagerID(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除APP图片
	 */
	@Override
	public R batchRemoveManaSysAppImg(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] appImgIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String appImgId : appImgIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysAppImg(appImgId);
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
        return R.ok(failure_msg);
	}
	
	
	/**
	 * 删除单个数据
	 * @param appImgId
	 * @return
	 */
	@Transactional
	public R removeManaSysAppImg(String appImgId) {
		try {
			//（1）根据图片编号查询图片信息
			Map<String, Object> appImgMap = manaSysAppImgMapper.getManaSysAppImgById(appImgId);
			//（2）删除图片信息
			int i = manaSysAppImgMapper.deleteManaSysAppImg(appImgId);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "图片编号"+appImgId+"：删除失败");
			}
			//（3）清除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_app_img+appImgMap.get("img_type").toString());
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "图片编号"+appImgId+"：删除异常");
		} 
	}
	
}
