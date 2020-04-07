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
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransMonth;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransMonthService;

/**
 * 代理======MPOS商户数据汇总(每月)操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/summaryMposTransMonth")
public class AgentSummaryMposTransMonthController extends BaseController
{
    private String prefix = "deveagent/summaryMposTransMonth";
	
	@Autowired
	private AgentSummaryMposTransMonthService agentSummaryMposTransMonthService;
	
	
	/**
	 * 跳转MPOS商户数据汇总(每日)列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:summaryMposTransMonth:view")
	@GetMapping()
	public String summaryMposTransMonth()
	{
	    return prefix + "/summaryMposTransMonth";
	}
	
	
	/**
	 * 查询MPOS商户数据汇总(每月)列表
	 */
	@RequiresPermissions("deveagent:summaryMposTransMonth:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSummaryMposTransMonthService.getAgentSummaryMposTransMonthList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "MPOS商户数据汇总(每月)管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:summaryMposTransEveryDay:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSummaryMposTransMonth> list = agentSummaryMposTransMonthService.selectAgentSummaryMposTransMonthList(params);
        ExcelUtil<AgentSummaryMposTransMonth> util = new ExcelUtil<AgentSummaryMposTransMonth>(AgentSummaryMposTransMonth.class);
        return util.exportExcel(list, "MPOS商户数据汇总每月数据");
    }
	
}
