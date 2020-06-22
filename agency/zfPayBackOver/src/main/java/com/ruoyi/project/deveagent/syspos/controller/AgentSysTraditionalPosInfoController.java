package com.ruoyi.project.deveagent.syspos.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.syspos.domain.AgentSysTraditionalPosInfo;
import com.ruoyi.project.deveagent.syspos.service.AgentSysTraditionalPosInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======系统传统POS信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysTraditionalPosInfo")
public class AgentSysTraditionalPosInfoController extends BaseController
{
    private String prefix = "deveagent/sysTraditionalPosInfo";
	
	@Autowired
	private AgentSysTraditionalPosInfoService agentSysTraditionalPosInfoService;
	
	
	/**
	 * 跳转系统传统POS信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysTraditionalPosInfo:view")
	@GetMapping()
	public String sysTraditionalPosInfo()
	{
	    return prefix + "/sysTraditionalPosInfo";
	}
	
	
	/**
	 * 查询系统传统POS信息列表
	 */
//	@RequiresPermissions("deveagent:sysTraditionalPosInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysTraditionalPosInfoService.getAgentSysTraditionalPosInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}


    /**
     * 查询系统传统POS信息列表byqh
     */
//    @RequiresPermissions("deveagent:sysTraditionalPosInfo:list")
    @PostMapping("/subList")
    @ResponseBody
    public TableDataInfo subList(@RequestParam Map<String, Object> params)
    {
        //此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysTraditionalPosInfoService.getSubAgentSysTraditionalPosInfoList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }
	
	
	/**
	 * 导出系统传统POS信息
	 */
	@Log(title = "系统传统POS信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysTraditionalPosInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSysTraditionalPosInfo> list = agentSysTraditionalPosInfoService.selectAgentSysTraditionalPosInfoList(params);
        ExcelUtil<AgentSysTraditionalPosInfo> util = new ExcelUtil<AgentSysTraditionalPosInfo>(AgentSysTraditionalPosInfo.class);
        return util.exportExcel(list, "系统传统POS数据");
    }
	
	
	/**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("deveagent:sysTraditionalPosInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysTraditionalPosInfo> util = new ExcelUtil<AgentSysTraditionalPosInfo>(AgentSysTraditionalPosInfo.class);
        return util.importTemplateExcel("系统传统POS数据");
    }
    
    
    /**
     * 导入系统传统POS信息数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "系统传统POS信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("deveagent:sysTraditionalPosInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public R importData(MultipartFile file, String account_id) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysTraditionalPosInfo> util = new ExcelUtil<AgentSysTraditionalPosInfo>(AgentSysTraditionalPosInfo.class);
        //对excel表单默认第一个索引名转换成list
        List<AgentSysTraditionalPosInfo> agentSysTraditionalPosInfoList = util.importExcel(file.getInputStream());
        //导入系统传统POS数据信息
        return agentSysTraditionalPosInfoService.importAgentSysTraditionalPosInfoList(agentSysTraditionalPosInfoList,account_id);
    }
    
    
    /**
     * 跳转到新增页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	return prefix + "/add";
    }

    
    /**
     * 新增保存系统传统POS
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:sysTraditionalPosInfo:add")
    @Log(title = "系统传统POS信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysTraditionalPosInfoService.addAgentSysTraditionalPosInfo(params);
    }

    
    /**
     * 跳转修改系统传统POS页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditSysTraditionalPosInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
     * 根据id查看系统传统POS详情
     * @param id
     * @return
     */
//    @RequiresPermissions("deveagent:sysTraditionalPosInfo:list")
    @PostMapping("/getAgentSysTraditionalPosInfoById")
    @ResponseBody
    public Map<String, Object> getAgentSysTraditionalPosInfoById(String id)
    {
        return agentSysTraditionalPosInfoService.getAgentSysTraditionalPosInfoById(id);
    }
    

    /**
     * 修改保存系统传统POS信息
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:sysTraditionalPosInfo:edit")
    @Log(title = "系统传统POS信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentSysTraditionalPosInfoService.editAgentSysTraditionalPosInfo(params);
    }
    
    
    /**
	 * 跳转到批量删除系统传统POS页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysTraditionalPosInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysTraditionalPosInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除系统传统POS====>只能删除未分配的传统POS
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:sysTraditionalPosInfo:remove")
    @Log(title = "系统传统POS信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysTraditionalPosInfoService.batchRemoveAgentSysTraditionalPosInfo(params);
    }
    
    
    /**
	 * 根据设备号（机器编号）查询详情
	 * @param id
	 * @param mmap
	 * @return
	 */
//    @RequiresPermissions("deveagent:sysTraditionalPosInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("sysTraditionalPosInfo", agentSysTraditionalPosInfoService.getAgentSysTraditionalPosInfoDetailBySn(id));
        return prefix + "/detail";
    }
	
	
}
