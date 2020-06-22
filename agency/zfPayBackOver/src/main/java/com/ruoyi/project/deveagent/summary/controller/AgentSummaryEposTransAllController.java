package com.ruoyi.project.deveagent.summary.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransAll;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryEposTransAllService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransAllService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS商户数据汇总操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/summaryEposTransAll")
public class AgentSummaryEposTransAllController extends BaseController
{
    private String prefix = "deveagent/summaryTraditionalPosTransAll";
	
	@Autowired
	private AgentSummaryEposTransAllService agentSummaryEposTransAllService;
	
	
	/**
	 * 跳转传统POS商户数据汇总列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:summaryTraditionalPosTransAll:view")
	@GetMapping()
	public String summaryTraditionalPosTransAll()
	{
		return prefix + "/summaryEposTransAll";
	}


	@PostMapping("/summaryTraditionalPosTransAllList")
	@ResponseBody
	public Map<String,Object> summaryTraditionalPosTransAllList(@RequestParam Map<String, Object> params){
		return agentSummaryEposTransAllService.summaryTraditionalPosTransAllList(params);
	}
	
	
	/**
	 * 查询传统POS商户数据汇总列表
	 */
	@RequiresPermissions("deveagent:summaryTraditionalPosTransAll:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSummaryEposTransAllService.getAgentSummaryTraditionalPosTransAllList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "传统POS商户数据汇总管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:summaryTraditionalPosTransAll:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSummaryTraditionalPosTransAll> list = agentSummaryEposTransAllService.selectAgentSummaryTraditionalPosTransAllList(params);
        ExcelUtil<AgentSummaryTraditionalPosTransAll> util = new ExcelUtil<AgentSummaryTraditionalPosTransAll>(AgentSummaryTraditionalPosTransAll.class);
        return util.exportExcel(list, "传统POS商户数据汇总数据");
    }
	
}
