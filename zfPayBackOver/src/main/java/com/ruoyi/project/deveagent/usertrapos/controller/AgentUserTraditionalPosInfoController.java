package com.ruoyi.project.deveagent.usertrapos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
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
import com.ruoyi.project.deveagent.usertrapos.domain.AgentSelectUserTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraditionalPosInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraditionalPosInfo")
public class AgentUserTraditionalPosInfoController extends BaseController
{
    private String prefix = "deveagent/userTraditionalPosInfo";
	
	@Autowired
	private AgentUserTraditionalPosInfoService agentUserTraditionalPosInfoService;

	@Autowired
	private SysPosPolicyServiceImpl sysPosPolicyService;
	
	
	/**
	 * 跳转用户传统POS信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInfo:view")
	@GetMapping()
	public String userTraditionalPosInfo()
	{
	    return prefix + "/userTraditionalPosInfo";
	}
	
	
	/**
	 * 查询用户传统POS信息列表
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraditionalPosInfoService.getAgentUserTraditionalPosInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS信息
	 */
	@Log(title = "用户传统POS信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraditionalPosInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraditionalPosInfo> list = agentUserTraditionalPosInfoService.selectAgentUserTraditionalPosInfoList(params);
        ExcelUtil<AgentUserTraditionalPosInfo> util = new ExcelUtil<AgentUserTraditionalPosInfo>(AgentUserTraditionalPosInfo.class);
        return util.exportExcel(list, "用户传统POS数据");
    }
	
	
	/**
     * 跳转到新增页面=====》选择用户
     * @param mmap
     * @return
     */
    @GetMapping("/selectUser")
    public String selectUser(String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddUserTraditionalPosInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/selectUser";
    	}
    }

    /**
     * 跳转到新增页面=====》选择用户byqh
     * @param mmap
     * @return
     *
     */
    @GetMapping("/selectAgentUser")
    public String selectAgentUser(String id, String operParam, ModelMap mmap)
    {
        if(!ShiroUtils.getSysUser().isAuth()) {
            return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddUserAgentTraditionalPosInfo;
        }else {
            mmap.put("id", id);
            return prefix + "/selectAgentUser";
        }
    }

    /**
     * 跳转到新增页面=====》选择系统传统POS机byqh
     * @param mmap
     * @return
     */
    @GetMapping("/selectAgentSysPos/{id}")
    public String selectAgentSysPos(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("user_id", id);
        List<Map<String,Object>> dropDownList = agentUserTraditionalPosInfoService.selectSubAgentInfo(id);

        mmap.put("subAgent",dropDownList);
        return prefix + "/selectAgentSysPos";
    }
    
    
    /**
     * 跳转到新增页面=====》选择系统传统POS机 update byqh 201912
     * @param mmap
     * @return
     */
    @GetMapping("/selectSysPos/{id}")
    public String selectPos(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("user_id", id);
        List<Map<String,Object>> list = sysPosPolicyService.selectSysPosPolicyList(new HashMap<>());
        mmap.addAttribute("policyList",list);
        return prefix + "/selectSysPos";
    }
    
    
    /**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<AgentUserTraditionalPosInfo> util = new ExcelUtil<AgentUserTraditionalPosInfo>(AgentUserTraditionalPosInfo.class);
        return util.importTemplateExcel("用户传统POS机数据");
    }
    
    
    /**
     * 导入用户传统POS信息数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "用户传统POS信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("deveagent:userTraditionalPosInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public R importData(MultipartFile file, String user_id) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<AgentUserTraditionalPosInfo> util = new ExcelUtil<AgentUserTraditionalPosInfo>(AgentUserTraditionalPosInfo.class);
        //对excel表单默认第一个索引名转换成list
        List<AgentUserTraditionalPosInfo> agentuserTraditionalPosInfoList = util.importExcel(file.getInputStream());
        //导入用户传统POS数据信息
        return agentUserTraditionalPosInfoService.importAgentUserTraditionalPosInfoList(agentuserTraditionalPosInfoList,user_id);
    }
    
    
    /**
	 * 导出可分配的传统POS模板
	 */
	@Log(title = "用户传统POS信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraditionalPosInfo:add")
    @PostMapping("/exportDeal")
    @ResponseBody
    public AjaxResult exportDeal(@RequestParam Map<String, Object> params)
    {
    	List<AgentSelectUserTraditionalPosInfo> list = agentUserTraditionalPosInfoService.selectAgentNoDisSysTraditionalPosInfoList(params);
        ExcelUtil<AgentSelectUserTraditionalPosInfo> util = new ExcelUtil<AgentSelectUserTraditionalPosInfo>(AgentSelectUserTraditionalPosInfo.class);
        return util.exportExcel(list, "可分配的传统POS导入模板");
    }
	
	
	/**
     * 新增保存用户传统POS机信息
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInfo:add")
    @Log(title = "用户传统POS信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTraditionalPosInfoService.addAgentUserTraditionalPosInfo(params);
    }

    /**
     * 代理的MPOS分配给二级代理保存操作byqh
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:userMposInfo:add")
    @Log(title = "用户MPOS信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/subAdd")
    @ResponseBody
    public R subAdd(@RequestParam Map<String, Object> params){
        return agentUserTraditionalPosInfoService.addSubAgentUserTraditionalPosInfo(params);
    }
    
    
    /**
     * 跳转修改用户传统POS页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditUserTraditionalPosInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
     * 根据id查看用户传统POS详情
     * @param id
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInfo:list")
    @PostMapping("/getAgentUserTraditionalPosInfoById")
    @ResponseBody
    public Map<String, Object> getAgentUserTraditionalPosInfoById(String id)
    {
        return agentUserTraditionalPosInfoService.getAgentUserTraditionalPosInfoById(id);
    }
    

    /**
     * 修改保存用户传统POS信息（只能待发布未删除的状态才能编辑）
     * @param params
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInfo:edit")
    @Log(title = "用户传统POS信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentUserTraditionalPosInfoService.editAgentUserTraditionalPosInfo(params);
    }
	
}
