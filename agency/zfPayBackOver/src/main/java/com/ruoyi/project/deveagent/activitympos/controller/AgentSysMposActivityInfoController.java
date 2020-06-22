package com.ruoyi.project.deveagent.activitympos.controller;

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
import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityInfo;
import com.ruoyi.project.deveagent.activitympos.service.AgentSysMposActivityInfoService;
import com.ruoyi.project.deveagent.user.domain.AgentBenefitRecordMoney;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======线上活动(MPOS)操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysMposActivityInfo")
public class AgentSysMposActivityInfoController extends BaseController
{
    private String prefix = "deveagent/sysMposActivityInfo";
	
	@Autowired
	private AgentSysMposActivityInfoService agentSysMposActivityInfoService;
	
	
	/**
	 * 跳转线上活动(MPOS)列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysMposActivityInfo:view")
	@GetMapping()
	public String sysMposActivityInfo()
	{
	    return prefix + "/sysMposActivityInfo";
	}
	
	
	/**
	 * 查询线上活动(MPOS)列表
	 */
	@RequiresPermissions("deveagent:sysMposActivityInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysMposActivityInfoService.getAgentSysMposActivityInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出线上活动(MPOS)
	 */
	@Log(title = "线上活动(MPOS)管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysMposActivityInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		List<AgentSysMposActivityInfo> list = agentSysMposActivityInfoService.selectAgentSysMposActivityInfoList(params);
        ExcelUtil<AgentSysMposActivityInfo> util = new ExcelUtil<AgentSysMposActivityInfo>(AgentSysMposActivityInfo.class);
        return util.exportExcel(list, "线上活动MPOS");
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddSysMposActivityInfo;
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
    @RequiresPermissions("deveagent:sysMposActivityInfo:add")
    @Log(title = "线上活动(MPOS)管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysMposActivityInfoService.addAgentSysMposActivityInfo(params);
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditSysMposActivityInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
	 * 查看线上活动(MPOS)详情
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("deveagent:sysMposActivityInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("id", id);
        return prefix + "/detail";
    }
    
    
    /**
     * 根据id查看线上活动(MPOS)详情
     * @param id
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityInfo:list")
    @PostMapping("/getAgentSysMposActivityInfoById")
    @ResponseBody
    public Map<String, Object> getAgentSysMposActivityInfoById(String id)
    {
        return agentSysMposActivityInfoService.getAgentSysMposActivityInfoById(id);
    }

   
    /**
     * 修改保存活动信息（只能待发布未删除的状态才能编辑）
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityInfo:edit")
    @Log(title = "线上活动(MPOS)管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentSysMposActivityInfoService.editAgentSysMposActivityInfo(params);
    }
    
    
    /**
	 * 跳转到批量删除活动信息页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysMposActivityInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysMposActivityInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除线上活动（MPOS）====>只能删除未发布且未删除的活动
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityInfo:remove")
    @Log(title = "线上活动(MPOS)管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysMposActivityInfoService.batchRemoveAgentSysMposActivityInfo(params);
    }
    
    
    /**
	 * 系统批量发布和取消系统活动
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysRelease/{id}")
	@RequiresPermissions("deveagent:sysMposActivityInfo:sysRelease")
    public String sysRelease(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysReleaseSysMposActivityInfo;
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
	@RequiresPermissions("deveagent:sysMposActivityInfo:sysRelease")
    @Log(title = "线上活动(MPOS)管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysRelease")
    @ResponseBody
    public R sysRelease(@RequestParam Map<String, Object> params)
    {
        return agentSysMposActivityInfoService.batchSysReleaseAgentSysMposActivityInfo(params);
    }
	
	
	/**
     * 跳转查看奖励类型页面
     * @param roleId
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:add")
    @GetMapping("/listReward/{id}")
    public String listReward(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("activity_id", id);
        return prefix + "/listReward";
    }
	
}
