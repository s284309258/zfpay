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
import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecordDetail;
import com.ruoyi.project.deveagent.account.service.AgentUserReportRecordDetailService;

/**
 * 代理======代理报备详情操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userReportRecordDetail")
public class AgentUserReportRecordDetailController extends BaseController
{
    private String prefix = "deveagent/userReportRecordDetail";
	
	@Autowired
	private AgentUserReportRecordDetailService agentUserReportRecordDetailService;
	
	
	/**
	 * 跳转代理报备列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userReportRecordDetail:view")
	@GetMapping()
	public String userReportRecordDetail()
	{
	    return prefix + "/userReportRecordDetail";
	}
	
	
	/**
	 * 查询代理报备列表
	 */
	@RequiresPermissions("deveagent:userReportRecordDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserReportRecordDetailService.getAgentUserReportRecordDetailList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出代理报备详情
	 */
	@Log(title = "代理报备详情管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userReportRecordDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserReportRecordDetail> list = agentUserReportRecordDetailService.selectAgentUserReportRecordDetailList(params);
        ExcelUtil<AgentUserReportRecordDetail> util = new ExcelUtil<AgentUserReportRecordDetail>(AgentUserReportRecordDetail.class);
        return util.exportExcel(list, "代理报备数据");
    }

	
}
