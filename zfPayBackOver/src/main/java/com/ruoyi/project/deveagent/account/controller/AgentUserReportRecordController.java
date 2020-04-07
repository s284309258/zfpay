package com.ruoyi.project.deveagent.account.controller;

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
import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecord;
import com.ruoyi.project.deveagent.account.service.AgentUserReportRecordService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======代理报备操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userReportRecord")
public class AgentUserReportRecordController extends BaseController
{
    private String prefix = "deveagent/userReportRecord";
	
	@Autowired
	private AgentUserReportRecordService agentUserReportRecordService;
	
	
	/**
	 * 跳转代理报备列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userReportRecord:view")
	@GetMapping()
	public String userReportRecord()
	{
	    return prefix + "/userReportRecord";
	}
	
	
	/**
	 * 查询代理报备列表
	 */
	@RequiresPermissions("deveagent:userReportRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserReportRecordService.getAgentUserReportRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出代理报备
	 */
	@Log(title = "代理报备管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userReportRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserReportRecord> list = agentUserReportRecordService.selectAgentUserReportRecordList(params);
        ExcelUtil<AgentUserReportRecord> util = new ExcelUtil<AgentUserReportRecord>(AgentUserReportRecord.class);
        return util.exportExcel(list, "代理报备数据");
    }
	
	
	/**
	 * 代理报备
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userReportRecord:add")
    @Log(title = "代理报备管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysReport")
    @ResponseBody
    public R sysReport(@RequestParam Map<String, Object> params)
    {
        return agentUserReportRecordService.sysReport(params);
    }

	
}
