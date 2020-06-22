package com.ruoyi.project.deveagent.activitytrapos.controller;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityInfo;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentSysEposActivityInfoService;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentSysTraposActivityInfoService;
import com.ruoyi.project.develop.common.domain.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======线上活动(传统POS)操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysEposActivityInfo")
public class AgentSysEposActivityInfoController extends BaseController
{
    private String prefix = "deveagent/sysEposActivityInfo";
	
	@Autowired
	private AgentSysEposActivityInfoService agentSysEposActivityInfoService;
	
	
	/**
	 * 跳转线上活动(传统POS)列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysTraposActivityInfo:view")
	@GetMapping()
	public String sysTraposActivityInfo()
	{
	    return prefix + "/sysTraposActivityInfo";
	}
	
	
	/**
	 * 查询线上活动(传统POS)列表
	 */
	@RequiresPermissions("deveagent:sysTraposActivityInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysEposActivityInfoService.getAgentSysTraposActivityInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出线上活动(传统POS)
	 */
	@Log(title = "线上活动(传统POS)管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysTraposActivityInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSysTraposActivityInfo> list = agentSysEposActivityInfoService.selectAgentSysTraposActivityInfoList(params);
        ExcelUtil<AgentSysTraposActivityInfo> util = new ExcelUtil<AgentSysTraposActivityInfo>(AgentSysTraposActivityInfo.class);
        return util.exportExcel(list, "线上活动传统POS");
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddSysEposActivityInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/add";
    	}
    }

    
    /**
     * 新增保存活动信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysTraposActivityInfo:add")
    @Log(title = "线上活动(传统POS)管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysEposActivityInfoService.addAgentSysTraposActivityInfo(params);
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditSysEposActivityInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
	 * 查看线上活动(传统POS)详情
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("deveagent:sysTraposActivityInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("id", id);
        return prefix + "/detail";
    }
    
    
    /**
     * 根据id查看线上活动(传统POS)详情
     * @param id
     * @return
     */
    @RequiresPermissions("deveagent:sysTraposActivityInfo:list")
    @PostMapping("/getAgentSysTraposActivityInfoById")
    @ResponseBody
    public Map<String, Object> getAgentSysTraposActivityInfoById(String id)
    {
        return agentSysEposActivityInfoService.getAgentSysTraposActivityInfoById(id);
    }

   
    /**
     * 修改保存活动信息（只能待发布未删除的状态才能编辑）
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysTraposActivityInfo:edit")
    @Log(title = "线上活动(传统POS)管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentSysEposActivityInfoService.editAgentSysTraposActivityInfo(params);
    }
    
    
    /**
	 * 跳转到批量删除活动信息页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysTraposActivityInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysEposActivityInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除线上活动（传统POS）====>只能删除未发布且未删除的活动
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysTraposActivityInfo:remove")
    @Log(title = "线上活动(传统POS)管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysEposActivityInfoService.batchRemoveAgentSysTraposActivityInfo(params);
    }
    
    
    /**
	 * 系统批量发布和取消系统活动
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysRelease/{id}")
	@RequiresPermissions("deveagent:sysTraposActivityInfo:sysRelease")
    public String sysRelease(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysReleaseSysEposActivityInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysRelease";
    	}
    }
	
	
	/**
	 * 系统批量发布和取消系统活动
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:sysTraposActivityInfo:sysRelease")
    @Log(title = "线上活动(传统POS)管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysRelease")
    @ResponseBody
    public R sysRelease(@RequestParam Map<String, Object> params)
    {
        return agentSysEposActivityInfoService.batchSysReleaseAgentSysTraposActivityInfo(params);
    }
	
	
	/**
     * 跳转查看奖励类型页面
     * @param roleId
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:sysTraposActivityRewardInfo:add")
    @GetMapping("/listReward/{id}")
    public String authUser(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("activity_id", id);
        return prefix + "/listReward";
    }
	
}
