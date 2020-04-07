package com.ruoyi.project.deveagent.user.controller;

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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.user.domain.AgentUserCashRecord;
import com.ruoyi.project.deveagent.user.domain.AgentUserCashRecordExcel;
import com.ruoyi.project.deveagent.user.service.AgentUserCashRecordService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;

/**
 * 管理员======用户取现记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userCashRecord")
public class AgentUserCashRecordController extends BaseController
{
    private String prefix = "deveagent/userCashRecord";
	
	@Autowired
	private AgentUserCashRecordService agentUserCashRecordService;
	
	
	/**
	 * 跳转用户取现记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userCashRecord:view")
	@GetMapping()
	public String userCashRecord()
	{
	    return prefix + "/userCashRecord";
	}
	
	
	/**
	 * 查询用户取现记录列表
	 */
	@RequiresPermissions("deveagent:userCashRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserCashRecordService.getAgentUserCashRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计取现信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userCashRecord:list")
	@PostMapping("/summaryAgentUserCashRecordList")
	@ResponseBody
	public Map<String, Object> summaryAgentUserCashRecordList(@RequestParam Map<String, Object> params)
	{
		return agentUserCashRecordService.summaryAgentUserCashRecordList(params);
	}
	
	
	/**
	 * 导出用户取现记录
	 */
	@Log(title = "用户取现记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userCashRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaUserCashRecord> list = agentUserCashRecordService.selectAgentUserCashRecordList(params);
        ExcelUtil<ManaUserCashRecord> util = new ExcelUtil<ManaUserCashRecord>(ManaUserCashRecord.class);
        return util.exportExcel(list, "用户取现记录数据");
    }
	
	
	/**
     * 跳转跳转用户取现记录详情列表页面
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:userCashRecord:detail")
    @GetMapping("/detailList/{id}")
    public String detailList(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("cash_id", id);
        return prefix + "/detailList";
    }
	
	
	/**
	 * 查询用户取现记录详情列表
	 */
	@RequiresPermissions("deveagent:userCashRecord:detail")
	@PostMapping("/detailList")
	@ResponseBody
	public TableDataInfo detailList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserCashRecordService.getAgentUserCashRecordDetailList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户待处理取现记录
	 */
	@Log(title = "用户待处理取现记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userCashRecord:export")
    @PostMapping("/exportDeal")
    @ResponseBody
    public AjaxResult exportDeal(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserCashRecordExcel> list = agentUserCashRecordService.selectManaWaitUserCashRecordList(params);
        ExcelUtil<AgentUserCashRecordExcel> util = new ExcelUtil<AgentUserCashRecordExcel>(AgentUserCashRecordExcel.class);
        return util.exportExcel(list, "待处理取现记录报表");
    }
	
	
	/**
	 * 批量处理处理中的提现申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userCashRecord:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserCash;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	
	
	/**
	 * 批量处理处理中的提现申请
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userCashRecord:sysAudit")
    @Log(title = "处理处理中的提现记录", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserCashRecordService.batchSysAuditUserCashRecord(params);
    }
	
}
