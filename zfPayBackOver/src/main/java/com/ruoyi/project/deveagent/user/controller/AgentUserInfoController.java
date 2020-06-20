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
import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;
import com.ruoyi.project.deveagent.user.service.AgentUserInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userInfo")
public class AgentUserInfoController extends BaseController
{
    private String prefix = "deveagent/userInfo";
	
	@Autowired
	private AgentUserInfoService agentUserInfoService;
	
	
	/**
	 * 跳转用户信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:view")
	@GetMapping()
	public String userInfo()
	{
	    return prefix + "/userInfo";
	}
	
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("deveagent:userInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserInfoService.getAgentUserInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:list")
	@PostMapping("/summaryAgentUserInfoList")
	@ResponseBody
	public Map<String, Object> summaryAgentUserInfoList(@RequestParam Map<String, Object> params)
	{
		return agentUserInfoService.summaryAgentUserInfoList(params);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "用户信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserInfo> list = agentUserInfoService.selectAgentUserInfoList(params);
        ExcelUtil<AgentUserInfo> util = new ExcelUtil<AgentUserInfo>(AgentUserInfo.class);
        return util.exportExcel(list, "代理用户信息数据");
    }
	
	
	
	/**
	 * 冻结解冻用户账号
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/toSysFreeze/{id}")
	@RequiresPermissions("deveagent:userInfo:sysFreeze")
    public String toSysFreeze(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysFreezeUserInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysFreeze";
    	}
    }
	
	
	/**
	 * 批量冻结和解冻用户账号
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:sysFreeze")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysFreeze")
    @ResponseBody
    public R sysFreeze(@RequestParam Map<String, Object> params)
    {
        return agentUserInfoService.batchSysFreezeAgentUser(params);
    }
		
		
	/**
	 * 单个实名认证审核
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAuthAudit/{id}")
	@RequiresPermissions("deveagent:userInfo:sysAuthAudit")
    public String sysAuthAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuthAuditUserInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAuthAudit";
    	}
    }
	
	
	/**
	 * 批量实名认证审核
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/batchSysAuthAudit/{id}")
	@RequiresPermissions("deveagent:userInfo:sysAuthAudit")
    public String batchSysAuthAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentBathSysAuthAuditUserInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/batchSysAuthAudit";
    	}
    }

		
	/**
	 * 批量实名认证审核用户账号
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:sysAuthAudit")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAuthAudit")
    @ResponseBody
    public R sysAuthAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserInfoService.batchSysAuthAuditAgentUser(params);
    }

	/**
	 * 跳转修改报备状态页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/batchsysBatchFiling/{id}")
	@RequiresPermissions("deveagent:userInfo:sysBatchFiling")
	public String batchsysBatchFiling(@PathVariable("id") String id,String operParam, ModelMap mmap)
	{
		if(!ShiroUtils.getSysUser().isAuth()) {
			return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentbatchsysBatchFilingUserInfo;
		}else {
			mmap.put("id", id);
			mmap.put("operParam", operParam);
			return prefix + "/batchsysBatchFiling";
		}
	}

	/**
	 * 批量修改报备状态用户账号
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:sysBatchFiling")
	@Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
	@PostMapping("/batchsysBatchFiling")
	@ResponseBody
	public R batchsysBatchFiling(@RequestParam Map<String, Object> params)
	{
		return agentUserInfoService.batchsysBatchFiling(params);
	}
	
	/**
	 * 跳转编辑用户信息页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditUserInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
	
    
    /**
     * 修改保存用户信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userInfo:edit")
    @Log(title = "用户列表管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentUserInfoService.editAgentUserInfo(params);
    }
    
    
    /**
     * 查询父级团队列表
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:userInfo:list")
    @GetMapping("/parentUser/{id}")
    public String parentUser(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("user_id", id);
        return prefix + "/parentUser";
    }
    
    
    /**
	 * 查询父级团队成员列表
	 */
	@RequiresPermissions("deveagent:userInfo:list")
	@PostMapping("/parentList")
	@ResponseBody
	public TableDataInfo parentList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserInfoService.getParentAgentUserInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出父级成员列表
	 */
	@Log(title = "用户父级信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userInfo:export")
    @PostMapping("/parentExport")
    @ResponseBody
    public AjaxResult parentExport(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserInfo> list = agentUserInfoService.selectParentAgentUserInfoList(params);
        ExcelUtil<AgentUserInfo> util = new ExcelUtil<AgentUserInfo>(AgentUserInfo.class);
        return util.exportExcel(list, "用户父级成员信息数据");
    }
	
	
	/**
     * 查询子级团队列表
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:userInfo:list")
    @GetMapping("/childrenUser/{id}")
    public String childrenUser(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("user_id", id);
        return prefix + "/childrenUser";
    }
	
	
	/**
	 * 查询子级（伞下）团队成员列表
	 */
	@RequiresPermissions("deveagent:userInfo:list")
	@PostMapping("/childrenList")
	@ResponseBody
	public TableDataInfo childrenList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserInfoService.getChildrenAgentUserList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出子级成员列表
	 */
	@Log(title = "用户子级信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userInfo:export")
    @PostMapping("/childrenExport")
    @ResponseBody
    public AjaxResult childrenExport(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserInfo> list = agentUserInfoService.selectChildrenAgentUserInfoList(params);
        ExcelUtil<AgentUserInfo> util = new ExcelUtil<AgentUserInfo>(AgentUserInfo.class);
        return util.exportExcel(list, "用户伞下成员信息数据");
    }
	
	
	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userInfo", agentUserInfoService.getAgentUserInfoById(id));
        return prefix + "/detail";
    }
	
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:list")
	@PostMapping("/getAgentUserInfoById")
	@ResponseBody
	public AgentUserInfo getAgentUserInfoById(String id)
	{
		return agentUserInfoService.getAgentUserInfoById(id);
	}
	
	
	
	 /**
	 * 跳转到批量删除用户信息页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:userInfo:remove")
	public String remove(@PathVariable("id") String id, String operParam, ModelMap mmap)
	{
		if(!ShiroUtils.getSysUser().isAuth()) {
			return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelUserInfo;
		}else {
			mmap.put("id", id);
	        return prefix + "/del";
		}
	}
	
	
	/**
	 * 批量删除用户信息====>只能删除未实名的并且下面没有人的
	 * @param user
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:remove")
	@Log(title = "用户列表管理", businessType = BusinessType.DELETE)
	@PostMapping("/del")
	@ResponseBody
	public R remove(@RequestParam Map<String, Object> params)
	{
	    return agentUserInfoService.batchRemoveAgentUserInfo(params);
	}
	
	
	/**
	 * 代理报备
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysReport/{id}")
	@RequiresPermissions("deveagent:userReportRecord:add")
    public String sysReport(@PathVariable("id") String id,ModelMap mmap)
    {
    	//根据编号查询用户信息
		AgentUserInfo agentUserInfo = agentUserInfoService.getAgentUserInfoById(id);
		mmap.put("agentUserInfo", agentUserInfo);
		return prefix + "/sysReport";
    }
	
	
}
