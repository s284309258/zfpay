package com.ruoyi.project.deveagent.datatrapos.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposPolicyRecord;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposPolicyRecordService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.service.ZhongFuDataAcquireService;

/**
 * 代理======账号政策记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/dataTraposPolicyRecord")
public class AgentDataTraposPolicyRecordController extends BaseController
{
    private String prefix = "deveagent/dataTraposPolicyRecord";
	
	@Autowired
	private AgentDataTraposPolicyRecordService agentDataTraposPolicyRecordService;
	@Autowired
	private ZhongFuDataAcquireService zhongFuDataAcquireService;
	
	
	/**
	 * 跳转账号政策记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:dataTraposPolicyRecord:view")
	@GetMapping()
	public String dataTraposPolicyRecord()
	{
	    return prefix + "/dataTraposPolicyRecord";
	}
	
	
	/**
	 * 查询账号政策记录列表
	 */
	@RequiresPermissions("deveagent:dataTraposPolicyRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentDataTraposPolicyRecordService.getAgentDataTraposPolicyRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出账号政策记录
	 */
	@Log(title = "账号政策记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:dataTraposPolicyRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentDataTraposPolicyRecord> list = agentDataTraposPolicyRecordService.selectAgentDataTraposPolicyRecordList(params);
        ExcelUtil<AgentDataTraposPolicyRecord> util = new ExcelUtil<AgentDataTraposPolicyRecord>(AgentDataTraposPolicyRecord.class);
        return util.exportExcel(list, "账号政策记录数据");
    }
	
	
	/**
     * 跳转到新增页面=====》选择账号
     * @param mmap
     * @return
     */
    @GetMapping("/selectAccount")
    public String selectAccount(ModelMap mmap)
    {
    	return prefix + "/selectAccount";
    }
	
    
    /**
     * 更新账号政策记录
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:dataTraposPolicyRecord:edit")
    @Log(title = "账号政策记录管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/{id}")
    @ResponseBody
    public R editSave(@PathVariable("id") String id, ModelMap mmap)
    {
        return zhongFuDataAcquireService.getTransposDataPolicyRecord(id);
    }
	
}
