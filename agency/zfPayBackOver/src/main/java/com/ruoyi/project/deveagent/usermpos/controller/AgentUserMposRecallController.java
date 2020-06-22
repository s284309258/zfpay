package com.ruoyi.project.deveagent.usermpos.controller;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
import com.ruoyi.project.deveagent.usermpos.domain.AgentSelectUserMposInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposInfo;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposInfoService;
import com.ruoyi.project.develop.common.domain.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理======用户MPOS信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposRecall")
public class AgentUserMposRecallController extends BaseController
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
	    return prefix + "/userMposRecall";
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
        List<Map<String, Object>> list = agentUserMposInfoService.getAgentUserMposRecallList(params);
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
}
