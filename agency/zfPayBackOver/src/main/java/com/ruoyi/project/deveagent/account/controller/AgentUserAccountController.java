package com.ruoyi.project.deveagent.account.controller;

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
import com.ruoyi.project.deveagent.account.domain.AgentUserAccount;
import com.ruoyi.project.deveagent.account.service.AgentUserAccountService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======中付账号操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userAccount")
public class AgentUserAccountController extends BaseController
{
    private String prefix = "deveagent/userAccount";
	
	@Autowired
	private AgentUserAccountService agentUserAccountService;
	
	
	/**
	 * 跳转中付账号列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userAccount:view")
	@GetMapping()
	public String userAccount()
	{
	    return prefix + "/userAccount";
	}
	
	
	/**
	 * 查询中付账号列表
	 */
	@RequiresPermissions("deveagent:userAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserAccountService.getAgentUserAccountList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出中付账号
	 */
	@Log(title = "中付账号管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserAccount> list = agentUserAccountService.selectAgentUserAccountList(params);
        ExcelUtil<AgentUserAccount> util = new ExcelUtil<AgentUserAccount>(AgentUserAccount.class);
        return util.exportExcel(list, "中付账号数据");
    }
	
	
    /**
     * 跳转到新增页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddUserAccount;
    	}else {
    		mmap.put("id", id);
            return prefix + "/add";
    	}
    }
    
    
    /**
     * 新增保存中付账号
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userAccount:add")
    @Log(title = "中付账号管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserAccountService.addAgentUserAccount(params);
    }
    
	
	
    /**
     * 跳转修改活动信息页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditUserAccount;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    

    /**
     * 修改保存中付账号
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userAccount:edit")
    @Log(title = "中付账号管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentUserAccountService.editAgentUserAccount(params);
    }
    
    
    /**
	 * 根据用户id查询中付账号详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@PostMapping("/getAgentUserAccountById")
	@ResponseBody
    public Map<String, Object> getAgentUserAccountById(String id)
    {
        return agentUserAccountService.getAgentUserAccountById(id);
    }
	
	
	 /**
     * 跳转删除中付账号页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelUserAccount;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
    

    /**
     * 批量删除中付账号
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userAccount:remove")
    @Log(title = "中付账号管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentUserAccountService.batchRemoveAgentUserAccount(params);
    }
    
    
    /**
	 * 批量开启关闭中付账号
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysOpen/{id}")
	@RequiresPermissions("deveagent:userAccount:sysOpen")
    public String sysOpen(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysOpenUserAccount;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysOpen";
    	}
    }
		
		
	/**
	 * 批量开启和关闭中付账号
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userInfo:sysFreeze")
    @Log(title = "中付账号管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysOpen")
    @ResponseBody
    public R sysFreeze(@RequestParam Map<String, Object> params)
    {
        return agentUserAccountService.batchSysOpenAgentUserAccount(params);
    }
	
	
	/**
	 * 查询代理有效的中付账号列表
	 * @return
	 */
	@PostMapping("/getAgentValidUserAccountList")
	@ResponseBody
    public List<AgentUserAccount> getAgentValidUserAccountList()
    {
        return agentUserAccountService.getAgentValidUserAccountList();
    }
		
	
}
