package com.ruoyi.project.deveagent.usermpos.controller;

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
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserApplyCardrateMposRecord;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserApplyCardrateMposRecordService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户费率申请记录(MPOS)操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userApplyCardrateMposRecord")
public class AgentUserApplyCardrateMposRecordController extends BaseController
{
    private String prefix = "deveagent/userApplyCardrateMposRecord";
	
	@Autowired
	private AgentUserApplyCardrateMposRecordService agentUserApplyCardrateMposRecordService;
	
	
	/**
	 * 跳转用户费率申请记录(MPOS)列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userApplyCardrateMposRecord:view")
	@GetMapping()
	public String userApplyCardrateMposRecord()
	{
	    return prefix + "/userApplyCardrateMposRecord";
	}
	
	
	/**
	 * 查询用户费率申请记录(MPOS)列表
	 */
	@RequiresPermissions("deveagent:userApplyCardrateMposRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserApplyCardrateMposRecordService.getAgentUserApplyCardrateMposRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户费率申请记录(MPOS)
	 */
	@Log(title = "用户费率申请记录(MPOS)管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userApplyCardrateMposRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserApplyCardrateMposRecord> list = agentUserApplyCardrateMposRecordService.selectAgentUserApplyCardrateMposRecordList(params);
        ExcelUtil<AgentUserApplyCardrateMposRecord> util = new ExcelUtil<AgentUserApplyCardrateMposRecord>(AgentUserApplyCardrateMposRecord.class);
        return util.exportExcel(list, "用户费率申请记录MPOS");
    }
	
	
	/**
	 * 系统批量刷卡费率(MPOS)申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userApplyCardrateMposRecord:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserApplyCardrateMposRecord;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	
	
	/**
	 * 系统批量刷卡费率(MPOS)申请
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userApplyCardrateMposRecord:sysAudit")
    @Log(title = "用户费率申请记录(MPOS)管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserApplyCardrateMposRecordService.batchAgentSysAuditUserApplyCardrateMposRecord(params);
    }
	
}
