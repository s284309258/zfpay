package com.ruoyi.project.deveagent.summary.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitAll;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserEposBenefitAllService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitAllService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS代理数据汇总操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/summaryUserEposBenefitAll")
public class AgentSummaryUserEposBenefitAllController extends BaseController
{
    private String prefix = "deveagent/summaryUserTraditionalPosBenefitAll";
	
	@Autowired
	private AgentSummaryUserEposBenefitAllService agentSummaryUserEposBenefitAllService;
	
	
	/**
	 * 跳转传统POS代理数据汇总列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitAll:view")
	@GetMapping()
	public String summaryUserTraditionalPosBenefitAll()
	{
	    return prefix + "/summaryUserEposBenefitAll";
	}
	
	
	/**
	 * 查询传统POS代理数据汇总列表
	 */
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitAll:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSummaryUserEposBenefitAllService.getAgentSummaryUserTraditionalPosBenefitAllList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "传统POS代理数据汇总管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:summaryUserTraditionalPosBenefitAll:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSummaryUserTraditionalPosBenefitAll> list = agentSummaryUserEposBenefitAllService.selectAgentSummaryUserTraditionalPosBenefitAllList(params);
        ExcelUtil<AgentSummaryUserTraditionalPosBenefitAll> util = new ExcelUtil<AgentSummaryUserTraditionalPosBenefitAll>(AgentSummaryUserTraditionalPosBenefitAll.class);
        return util.exportExcel(list, "传统POS代理数据汇总数据");
    }
	
}
