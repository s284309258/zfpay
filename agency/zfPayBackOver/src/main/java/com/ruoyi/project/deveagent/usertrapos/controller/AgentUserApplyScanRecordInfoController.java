package com.ruoyi.project.deveagent.usertrapos.controller;

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
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyScanRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserApplyScanRecordInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户申请扫码支付操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userApplyScanRecordInfo")
public class AgentUserApplyScanRecordInfoController extends BaseController
{
    private String prefix = "deveagent/userApplyScanRecordInfo";
	
	@Autowired
	private AgentUserApplyScanRecordInfoService agentUserApplyScanRecordInfoService;
	
	
	/**
	 * 跳转用户申请扫码支付列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:view")
	@GetMapping()
	public String userApplyScanRecordInfo()
	{
	    return prefix + "/userApplyScanRecordInfo";
	}
	
	
	/**
	 * 查询用户申请扫码支付列表
	 */
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserApplyScanRecordInfoService.getAgentUserApplyScanRecordInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户申请扫码支付
	 */
	@Log(title = "用户申请扫码支付管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserApplyScanRecordInfo> list = agentUserApplyScanRecordInfoService.selectAgentUserApplyScanRecordInfoList(params);
        ExcelUtil<AgentUserApplyScanRecordInfo> util = new ExcelUtil<AgentUserApplyScanRecordInfo>(AgentUserApplyScanRecordInfo.class);
        return util.exportExcel(list, "用户申请扫码支付");
    }
	
	
	/**
	 * 系统批量扫码支付申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserApplyScanRecordInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	
	
	/**
	 * 系统批量扫码支付申请
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:sysAudit")
    @Log(title = "用户申请扫码支付管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserApplyScanRecordInfoService.batchAgentSysAuditUserApplyScanRecordInfo(params);
    }
	
	
	/**
	 * 根据id查询申请扫码支付详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("deveagent:userApplyScanRecordInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userApplyScanRecordInfo", agentUserApplyScanRecordInfoService.getAgentUserApplyScanRecordInfoById(id));
        return prefix + "/detail";
    }
	
}
