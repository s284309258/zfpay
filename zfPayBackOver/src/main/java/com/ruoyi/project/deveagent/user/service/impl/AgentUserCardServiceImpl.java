package com.ruoyi.project.deveagent.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.user.domain.AgentUserCard;
import com.ruoyi.project.deveagent.user.mapper.AgentUserCardMapper;
import com.ruoyi.project.deveagent.user.service.AgentUserCardService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户结算卡管理
 * @author Administrator
 *
 */
@Service
public class AgentUserCardServiceImpl implements AgentUserCardService {
	
	
	@Autowired
	private AgentUserCardMapper agentUserCardMapper;

	@Autowired
	private ManaSysNoticeMapper sysNoticeMapper;


	
	/**
	 * 查询用户结算卡列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserCardList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCardMapper.getAgentUserCardList(params);
	}
	
	
	/**
	 * 导出用户结算卡列表
	 */
	@Override
	public List<AgentUserCard> selectAgentUserCardList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCardMapper.selectAgentUserCardList(params);
	}


	/**
	 * 根据id查询用户结算卡详情
	 */
	@Override
	public AgentUserCard getAgentUserCardById(String id) {
		return agentUserCardMapper.getAgentUserCardById(id);
	}


	/**
	 * 批量审核用户结算卡
	 */
	@Override
	public R batchSysAuditAgentUserCard(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_card_info_status_08.equals(StringUtil.getMapValue(params, "status")) || TypeStatusConstant.user_card_info_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	return this.batchSysAuditAgentUserCardOper(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}


	/**
	 * 批量审核用户结算卡操作
	 * @param params
	 * @return
	 */
	private R batchSysAuditAgentUserCardOper(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] card_ids = Convert.toStrArray(StringUtil.getMapValue(params, "card_ids"));
        for(int i=0;i<card_ids.length;i++) {
        	Map<String, Object> cardMap = new HashMap<>();
        	cardMap.put("remark", params.get("remark"));
        	cardMap.put("card_id", card_ids[i]);
        	cardMap.put("new_status", params.get("status").toString());
        	//依次审核每一个
        	R result = SpringUtils.getAopProxy(this).sysAuditAgentUserCard(cardMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 依次审核每一个结算卡
	 * @param cardMap
	 * @return
	 */
	@Transactional
	public R sysAuditAgentUserCard(Map<String, Object> cardMap) {
		try {
			int i=0;
			//（1）更新结算卡状态
			cardMap.put("old_status", TypeStatusConstant.user_card_info_status_00);
			cardMap.put("up_date", TimeUtil.getDayString());
			cardMap.put("up_time", TimeUtil.getTimeString());
			cardMap.put("update_by", ShiroUtils.getLoginName());
			System.out.println(cardMap);
			i = agentUserCardMapper.updateAgentUserCardStatus(cardMap);
			if(i != 1) {
				return R.error(Type.WARN, "卡编号"+cardMap.get("card_id").toString()+"：状态更新失败");
			}
			//add 小红点消息置为未读 begin byqh 201912
			String user_id = agentUserCardMapper.selectCardUserIDByCardID(String.valueOf(cardMap.get("card_id")));
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("sys_user_id",user_id);
			hashMap.put("news_type","cardFlag");
			sysNoticeMapper.updateNewsReadFlag(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "卡编号"+cardMap.get("card_id").toString()+"：审核异常");
		}
	}
	
}
