package com.ruoyi.project.deveagent.datampos.controller;

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
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposMachineCashBackRecord;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposMachineCashBackRecordService;

/**
 * 代理======机具返现记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/dataMposMachineCashBackRecord")
public class AgentDataMposMachineCashBackRecordController extends BaseController
{
    private String prefix = "deveagent/dataMposMachineCashBackRecord";
	
	@Autowired
	private AgentDataMposMachineCashBackRecordService agentDataMposMachineCashBackRecordService;
	
	
	/**
	 * 跳转机具返现记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:dataMposMachineCashBackRecord:view")
	@GetMapping()
	public String dataMposMachineCashBackRecord()
	{
	    return prefix + "/dataMposMachineCashBackRecord";
	}
	
	
	/**
	 * 查询机具返现记录表列表
	 */
	@RequiresPermissions("deveagent:dataMposMachineCashBackRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentDataMposMachineCashBackRecordService.getAgentDataMposMachineCashBackRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机具返现记录
	 */
	@Log(title = "机具返现记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:dataMposMachineCashBackRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentDataMposMachineCashBackRecord> list = agentDataMposMachineCashBackRecordService.selectAgentDataMposMachineCashBackRecordList(params);
        ExcelUtil<AgentDataMposMachineCashBackRecord> util = new ExcelUtil<AgentDataMposMachineCashBackRecord>(AgentDataMposMachineCashBackRecord.class);
        return util.exportExcel(list, "机具返现记录数据");
    }
	
}
