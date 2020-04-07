package com.ruoyi.project.deveagent.summary.controller;

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
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransAll;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransAllService;

/**
 * 代理======MPOS商户数据汇总操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/summaryMposTransAll")
public class AgentSummaryMposTransAllController extends BaseController
{
    private String prefix = "deveagent/summaryMposTransAll";
	
	@Autowired
	private AgentSummaryMposTransAllService agentSummaryMposTransAllService;
	
	
	/**
	 * 跳转MPOS商户数据汇总列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:summaryMposTransAll:view")
	@GetMapping()
	public String summaryMposTransAll()
	{
	    return prefix + "/summaryMposTransAll";
	}
	
	
	/**
	 * 查询MPOS商户数据汇总列表
	 */
	@RequiresPermissions("deveagent:summaryMposTransAll:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSummaryMposTransAllService.getAgentSummaryMposTransAllList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "MPOS商户数据汇总管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:summaryMposTransAll:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSummaryMposTransAll> list = agentSummaryMposTransAllService.selectAgentSummaryMposTransAllList(params);
        ExcelUtil<AgentSummaryMposTransAll> util = new ExcelUtil<AgentSummaryMposTransAll>(AgentSummaryMposTransAll.class);
        return util.exportExcel(list, "MPOS商户数据汇总数据");
    }
	
}
