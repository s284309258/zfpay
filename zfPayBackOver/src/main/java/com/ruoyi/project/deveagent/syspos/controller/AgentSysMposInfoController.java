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
import com.ruoyi.project.deveagent.syspos.domain.AgentSysMposInfo;
import com.ruoyi.project.deveagent.syspos.service.AgentSysMposInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======系统MPOS信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysMposInfo")
public class AgentSysMposInfoController extends BaseController
{
    private String prefix = "deveagent/sysMposInfo";
	
	@Autowired
	private AgentSysMposInfoService agentSysMposInfoService;
	
	
	/**
	 * 跳转系统MPOS信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysMposInfo:view")
	@GetMapping()
	public String sysMposInfo()
	{
	    return prefix + "/sysMposInfo";
	}
	
	
	/**
	 * 查询系统MPOS信息列表
	 */
	@RequiresPermissions("deveagent:sysMposInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysMposInfoService.getAgentSysMposInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}

    /***
     * 查询代理名下所有MPOS
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:list")
    @PostMapping("/OneAgentList")
    @ResponseBody
    public TableDataInfo OneAgentList(@RequestParam Map<String, Object> params)
    {
        //此方法配合前端完成自动分页
//        startPage();
        //根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysMposInfoService.getOneAgentPosList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }


    /**
     * 查询系统一级代理MPOS信息列表byqh
     */
    @RequiresPermissions("deveagent:sysMposInfo:list")
    @PostMapping("/subList")
    @ResponseBody
    public TableDataInfo subList(@RequestParam Map<String, Object> params)
    {
        //此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysMposInfoService.getSubAgentSysMposInfoList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }
	
	
	/**
	 * 导出系统MPOS信息
	 */
	@Log(title = "系统MPOS信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysMposInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSysMposInfo> list = agentSysMposInfoService.selectAgentSysMposInfoList(params);
        ExcelUtil<AgentSysMposInfo> util = new ExcelUtil<AgentSysMposInfo>(AgentSysMposInfo.class);
        return util.exportExcel(list, "系统MPOS数据");
    }
	
	
	/**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysMposInfo> util = new ExcelUtil<AgentSysMposInfo>(AgentSysMposInfo.class);
        return util.importTemplateExcel("系统MPOS数据");
    }
    
    
    /**
     * 导入系统MPOS信息数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "系统MPOS信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("deveagent:sysMposInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public R importData(MultipartFile file, String account_id) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysMposInfo> util = new ExcelUtil<AgentSysMposInfo>(AgentSysMposInfo.class);
        //对excel表单默认第一个索引名转换成list
        List<AgentSysMposInfo> agentSysMposInfoList = util.importExcel(file.getInputStream());
        //导入系统MPOS数据信息
        return agentSysMposInfoService.importAgentSysMposInfoList(agentSysMposInfoList, account_id);
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
     * 新增保存系统MPOS
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:add")
    @Log(title = "系统MPOS信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysMposInfoService.addAgentSysMposInfo(params);
    }

    
    /**
     * 跳转修改系统MPOS页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditSysMposInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
     * 根据id查看系统MPOS详情
     * @param id
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:list")
    @PostMapping("/getAgentSysMposInfoById")
    @ResponseBody
    public Map<String, Object> getAgentSysMposInfoById(String id)
    {
        return agentSysMposInfoService.getAgentSysMposInfoById(id);
    }
    

    /**
     * 修改保存系统MPOS信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:edit")
    @Log(title = "系统MPOS信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentSysMposInfoService.editAgentSysMposInfo(params);
    }
    
    
    /**
	 * 跳转到批量删除系统MPOS页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysMposInfo:remove")
    public String remove(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysMposInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除系统MPOS====>只能删除未分配的MPOS
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposInfo:remove")
    @Log(title = "系统MPOS信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysMposInfoService.batchRemoveAgentSysMposInfo(params);
    }
    

	/**
	 * 根据设备号（机器编号）查询详情
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("deveagent:sysMposInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("sysMposInfo", agentSysMposInfoService.getAgentSysMposInfoDetailBySn(id));
        return prefix + "/detail";
    }
	
}
