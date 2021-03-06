package com.ruoyi.project.deveagent.summary.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitEveryDay;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserEposBenefitEveryDayService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitEveryDayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS代理数据汇总（每日）操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/summaryUserEposBenefitEveryDay")
public class AgentSummaryUserEposBenefitEveryDayController extends BaseController
{
    private String prefix = "deveagent/summaryUserTraditionalPosBenefitEveryDay";
	
	@Autowired
	private AgentSummaryUserEposBenefitEveryDayService agentSummaryUserEposBenefitEveryDayService;
	
	
	/**
	 * 跳转传统POS代理数据汇总（每日）列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitEveryDay:view")
	@GetMapping()
	public String summaryUserTraditionalPosBenefitEveryDay()
	{
	    return prefix + "/summaryUserEposBenefitEveryDay";
	}
	
	
	/**
	 * 查询传统POS代理数据汇总（每日）列表
	 */
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitEveryDay:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSummaryUserEposBenefitEveryDayService.getAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "传统POS代理数据汇总（每日）管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitEveryDay:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSummaryUserTraditionalPosBenefitEveryDay> list = agentSummaryUserEposBenefitEveryDayService.selectAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
        ExcelUtil<AgentSummaryUserTraditionalPosBenefitEveryDay> util = new ExcelUtil<AgentSummaryUserTraditionalPosBenefitEveryDay>(AgentSummaryUserTraditionalPosBenefitEveryDay.class);
        return util.exportExcel(list, "传统POS代理数据汇总每日数据");
    }
	
}
