package com.ruoyi.project.deveagent.usertrapos.controller;

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
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposShareBenefitRecord;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposShareBenefitRecordService;

/**
 * 代理======用户传统POS分润记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraposShareBenefitRecord")
public class AgentUserTraposShareBenefitRecordController extends BaseController
{
    private String prefix = "deveagent/userTraposShareBenefitRecord";
	
	@Autowired
	private AgentUserTraposShareBenefitRecordService agentUserTraposShareBenefitRecordService;
	
	
	/**
	 * 跳转用户传统POS分润记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposShareBenefitRecord:view")
	@GetMapping()
	public String userTraposShareBenefitRecord()
	{
	    return prefix + "/userTraposShareBenefitRecord";
	}
	
	
	/**
	 * 查询用户传统POS分润记录列表
	 */
	@RequiresPermissions("deveagent:userTraposShareBenefitRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraposShareBenefitRecordService.getAgentUserTraposShareBenefitRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS分润记录
	 */
	@Log(title = "用户传统POS分润记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraposShareBenefitRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraposShareBenefitRecord> list = agentUserTraposShareBenefitRecordService.selectAgentUserTraposShareBenefitRecordList(params);
        ExcelUtil<AgentUserTraposShareBenefitRecord> util = new ExcelUtil<AgentUserTraposShareBenefitRecord>(AgentUserTraposShareBenefitRecord.class);
        return util.exportExcel(list, "用户传统POS分润记录数据");
    }
	
}
