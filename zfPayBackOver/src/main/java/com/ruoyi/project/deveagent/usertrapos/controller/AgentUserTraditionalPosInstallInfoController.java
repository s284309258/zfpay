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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraditionalPosInstallInfoService;
import com.ruoyi.project.develop.common.domain.R;


/**
 * 代理======用户传统POS商户进件操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraditionalPosInstallInfo")
public class AgentUserTraditionalPosInstallInfoController extends BaseController
{
    private String prefix = "deveagent/userTraditionalPosInstallInfo";
	
	@Autowired
	private AgentUserTraditionalPosInstallInfoService agentUserTraditionalPosInstallInfoService;
	
	
	/**
	 * 跳转用户传统POS商户进件列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInstallInfo:view")
	@GetMapping()
	public String userTraditionalPosInstallInfo()
	{
	    return prefix + "/userTraditionalPosInstallInfo";
	}
	
	
	/**
	 * 查询用户传统POS商户进件列表
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInstallInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraditionalPosInstallInfoService.getAgentUserTraditionalPosInstallInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS商户进件信息
	 */
	@Log(title = "用户传统POS商户进件管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraditionalPosInstallInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraditionalPosInstallInfo> list = agentUserTraditionalPosInstallInfoService.selectAgentUserTraditionalPosInstallInfoList(params);
        ExcelUtil<AgentUserTraditionalPosInstallInfo> util = new ExcelUtil<AgentUserTraditionalPosInstallInfo>(AgentUserTraditionalPosInstallInfo.class);
        return util.exportExcel(list, "用户传统POS商户进件数据");
    }
	
	
	/**
	 * 跳转查询进件明细页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("deveagent:userTraditionalPosInstallInfo:list")
    @GetMapping("/listDetail/{id}")
    public String listDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("install_id", id);
        return prefix + "/listDetail";
    }
	
    
    /**
     * 跳转到新增页面=====》选择用户
     * @param mmap
     * @return
     */
    @GetMapping("/selectUser")
    public String selectUser(String id, String operParam, ModelMap mmap)
    {
        return prefix + "/selectUser";
    }
    
    
    /**
     * 跳转添加进件信息页面
     * @param id
     * @param operParam
     * @param mmap
     * @return
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("user_id", id);
        return prefix + "/add";
    }
    
    
    /**
     * 新增保存进件信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallInfo:add")
    @Log(title = "用户传统POS商户进件管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTraditionalPosInstallInfoService.addAgentUserTraditionalPosInstallInfo(params);
    }
    
    
   /**
    * 跳转修改进件信息页面
    * @param id
    * @param operParam
    * @param mmap
    * @return
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("installInfo", agentUserTraditionalPosInstallInfoService.getAgentUserTraditionalPosInstallInfoById(id));
        return prefix + "/edit";
    }
    
    
    /**
     * 修改保存进件信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallInfo:edit")
    @Log(title = "用户传统POS商户进件管理", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTraditionalPosInstallInfoService.editAgentUserTraditionalPosInstallInfo(params);
    }
    
    
    /**
     * 批量删除商户进件信息
     * @param ids
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallInfo:remove")
    @Log(title = "用户传统POS商户进件管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return agentUserTraditionalPosInstallInfoService.batchRemoveAgentUserTraditionalPosInstallInfo(ids);
    }
}
