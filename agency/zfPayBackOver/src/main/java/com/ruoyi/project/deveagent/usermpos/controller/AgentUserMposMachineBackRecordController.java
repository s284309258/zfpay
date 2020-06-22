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
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineBackRecord;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposMachineBackRecordService;

/**
 * 代理======MPOS机具返现记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposMachineBackRecord")
public class AgentUserMposMachineBackRecordController extends BaseController
{
    private String prefix = "deveagent/userMposMachineBackRecord";
	
	@Autowired
	private AgentUserMposMachineBackRecordService agentUserMposMachineBackRecordService;
	
	
	/**
	 * 跳转用户MPOS机具返现记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposMachineBackRecord:view")
	@GetMapping()
	public String userMposMachineBackRecord()
	{
	    return prefix + "/userMposMachineBackRecord";
	}
	
	
	/**
	 * 查询用户MPOS机具返现记录列表
	 */
	@RequiresPermissions("deveagent:userMposMachineBackRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposMachineBackRecordService.getAgentUserMposMachineBackRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户MPOS机具返现记录
	 */
	@Log(title = "用户MPOS机具返现记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userMposMachineBackRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserMposMachineBackRecord> list = agentUserMposMachineBackRecordService.selectAgentUserMposMachineBackRecordList(params);
        ExcelUtil<AgentUserMposMachineBackRecord> util = new ExcelUtil<AgentUserMposMachineBackRecord>(AgentUserMposMachineBackRecord.class);
        return util.exportExcel(list, "用户MPOS机具返现记录数据");
    }
	
}
