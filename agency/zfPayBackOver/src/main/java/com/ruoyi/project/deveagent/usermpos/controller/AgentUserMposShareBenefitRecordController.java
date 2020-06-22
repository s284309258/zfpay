package com.ruoyi.project.deveagent.usermpos.controller;

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
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposShareBenefitRecord;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposShareBenefitRecordService;

/**
 * 代理======用户Mpos分润记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposShareBenefitRecord")
public class AgentUserMposShareBenefitRecordController extends BaseController
{
    private String prefix = "deveagent/userMposShareBenefitRecord";
	
	@Autowired
	private AgentUserMposShareBenefitRecordService agentUserMposShareBenefitRecordService;
	
	
	/**
	 * 跳转用户Mpos分润记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposShareBenefitRecord:view")
	@GetMapping()
	public String userMposShareBenefitRecord()
	{
	    return prefix + "/userMposShareBenefitRecord";
	}
	
	
	/**
	 * 查询用户Mpos分润记录列表
	 */
	@RequiresPermissions("deveagent:userMposShareBenefitRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposShareBenefitRecordService.getAgentUserMposShareBenefitRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户Mpos分润记录
	 */
	@Log(title = "用户Mpos分润记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userMposShareBenefitRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserMposShareBenefitRecord> list = agentUserMposShareBenefitRecordService.selectAgentUserMposShareBenefitRecordList(params);
        ExcelUtil<AgentUserMposShareBenefitRecord> util = new ExcelUtil<AgentUserMposShareBenefitRecord>(AgentUserMposShareBenefitRecord.class);
        return util.exportExcel(list, "用户Mpos分润记录数据");
    }
	
}
