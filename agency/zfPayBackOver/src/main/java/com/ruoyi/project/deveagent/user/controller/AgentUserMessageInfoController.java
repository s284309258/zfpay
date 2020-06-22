package com.ruoyi.project.deveagent.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.user.domain.AgentUserMessageInfo;
import com.ruoyi.project.deveagent.user.service.AgentUserMessageInfoService;

/**
 * 代理======用户通知操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMessageInfo")
public class AgentUserMessageInfoController extends BaseController
{
    private String prefix = "deveagent/userMessageInfo";
	
	@Autowired
	private AgentUserMessageInfoService agentUserMessageInfoService;
	
	
	/**
	 * 跳转用户通知列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMessageInfo:view")
	@GetMapping()
	public String userMessageInfo()
	{
	    return prefix + "/userMessageInfo";
	}
	
	
	/**
	 * 查询用户通知列表
	 */
	@RequiresPermissions("deveagent:userMessageInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMessageInfoService.getAgentUserMessageInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	

	/**
	 * 统计数据
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userMessageInfo:list")
	@PostMapping("/summaryAgentUserMessageInfoList")
	@ResponseBody
	public Map<String, Object> summaryAgentUserMessageInfoList(@RequestParam Map<String, Object> params)
	{
		return agentUserMessageInfoService.summaryAgentUserMessageInfoList(params);
	}
	
	
	/**
	 * 导出用户通知
	 */
	@Log(title = "用户通知管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userMessageInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserMessageInfo> list = agentUserMessageInfoService.selectAgentUserMessageInfoList(params);
        ExcelUtil<AgentUserMessageInfo> util = new ExcelUtil<AgentUserMessageInfo>(AgentUserMessageInfo.class);
        return util.exportExcel(list, "用户通知数据");
    }
	
}
