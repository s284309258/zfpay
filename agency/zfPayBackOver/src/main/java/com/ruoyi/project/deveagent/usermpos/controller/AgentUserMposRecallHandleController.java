package com.ruoyi.project.deveagent.usermpos.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户MPOS信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposRecallHandle")
public class AgentUserMposRecallHandleController extends BaseController
{
    private String prefix = "deveagent/userMposInfo";
	
	@Autowired
	private AgentUserMposInfoService agentUserMposInfoService;

	/**
	 * 跳转用户MPOS信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposInfo:view")
	@GetMapping()
	public String userMposInfo()
	{
	    return prefix + "/userMposRecallHandle";
	}
	
	
	/**
	 * 查询用户MPOS信息列表
	 */
	@RequiresPermissions("deveagent:userMposInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposInfoService.getAgentUserMposRecallHandleList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}

	@PostMapping("/recallUserPos")
	@ResponseBody
	public String recallUserPos(@RequestParam Map<String, Object> params){
		int cnt = agentUserMposInfoService.recallUserPos(params);
		if(cnt>0){
			return "success";
		}
		return "fail";
	}

	@PostMapping("/agreeRecallUserPos")
	@ResponseBody
	public String agreeRecallUserPos(@RequestParam Map<String, Object> params){
		int cnt = agentUserMposInfoService.agreeRecallUserPos(params);
		if(cnt>0){
			return "success";
		}
		return "fail";
	}

	@PostMapping("/refuseRecallUserPos")
	@ResponseBody
	public String refuseRecallUserPos(@RequestParam Map<String, Object> params){
		int cnt = agentUserMposInfoService.refuseRecallUserPos(params);
		if(cnt>0){
			return "success";
		}
		return "fail";
	}
}
