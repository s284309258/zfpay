package com.ruoyi.project.deveagent.usertracard.controller;

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
import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardRecallRecordInfo;
import com.ruoyi.project.deveagent.usertracard.service.AgentUserTrafficCardRecallRecordInfoService;


/**
 * 代理======用户流量卡召回记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTrafficCardRecallRecordInfo")
public class AgentUserTrafficCardRecallRecordInfoController extends BaseController
{
    private String prefix = "deveagent/userTrafficCardRecallRecordInfo";
	
	@Autowired
	private AgentUserTrafficCardRecallRecordInfoService agentUserTrafficCardRecallRecordInfoService;
	
	
	/**
	 * 跳转用户流量卡召回记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTrafficCardRecallRecordInfo:view")
	@GetMapping()
	public String userTrafficCardRecallRecordInfo()
	{
	    return prefix + "/userTrafficCardRecallRecordInfo";
	}
	
	
	/**
	 * 查询用户流量卡召回记录列表
	 */
	@RequiresPermissions("deveagent:userTrafficCardRecallRecordInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTrafficCardRecallRecordInfoService.getAgentUserTrafficCardRecallRecordInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流量卡召回记录信息
	 */
	@Log(title = "用户流量卡召回记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTrafficCardRecallRecordInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTrafficCardRecallRecordInfo> list = agentUserTrafficCardRecallRecordInfoService.selectAgentUserTrafficCardRecallRecordInfoList(params);
        ExcelUtil<AgentUserTrafficCardRecallRecordInfo> util = new ExcelUtil<AgentUserTrafficCardRecallRecordInfo>(AgentUserTrafficCardRecallRecordInfo.class);
        return util.exportExcel(list, "用户流量卡召回记录数据");
    }
	
}
