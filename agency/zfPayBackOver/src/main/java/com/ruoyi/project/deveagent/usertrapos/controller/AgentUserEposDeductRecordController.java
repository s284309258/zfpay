package com.ruoyi.project.deveagent.usertrapos.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposDeductRecord;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEposDeductRecordService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposDeductRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户传统POS未达标扣除记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userEposDeductRecord")
public class AgentUserEposDeductRecordController extends BaseController
{
    private String prefix = "deveagent/userEposDeductRecord";
	
	@Autowired
	private AgentUserEposDeductRecordService agentUserEposDeductRecordService;
	
	
	/**
	 * 跳转用户传统POS信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposDeductRecord:view")
	@GetMapping()
	public String userTraposDeductRecord()
	{
	    return prefix + "/userTraposDeductRecord";
	}
	
	
	/**
	 * 查询用户传统POS未达标扣除记录列表
	 */
	@RequiresPermissions("deveagent:userTraposDeductRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserEposDeductRecordService.getAgentUserTraposDeductRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS未达标扣除记录
	 */
	@Log(title = "传统POS未达标扣除记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraposDeductRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraposDeductRecord> list = agentUserEposDeductRecordService.selectAgentUserTraposDeductRecordList(params);
        ExcelUtil<AgentUserTraposDeductRecord> util = new ExcelUtil<AgentUserTraposDeductRecord>(AgentUserTraposDeductRecord.class);
        return util.exportExcel(list, "传统POS未达标扣除记录数据");
    }
	
}
