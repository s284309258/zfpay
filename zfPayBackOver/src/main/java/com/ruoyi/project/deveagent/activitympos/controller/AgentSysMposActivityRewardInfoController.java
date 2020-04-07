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
import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityRewardInfo;
import com.ruoyi.project.deveagent.activitympos.service.AgentSysMposActivityRewardInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======线上活动(MPOS)奖励类型信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysMposActivityRewardInfo")
public class AgentSysMposActivityRewardInfoController extends BaseController
{
    private String prefix = "deveagent/sysMposActivityRewardInfo";
	
	@Autowired
	private AgentSysMposActivityRewardInfoService agentSysMposActivityRewardInfoService;
	
	
	/**
	 * 跳转线上活动(MPOS)奖励类型信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysMposActivityRewardInfo:view")
	@GetMapping()
	public String sysMposActivityRewardInfo()
	{
	    return prefix + "/sysMposActivityRewardInfo";
	}
	
	
	/**
	 * 查询线上活动(MPOS)奖励类型信息列表
	 */
	@RequiresPermissions("deveagent:sysMposActivityRewardInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysMposActivityRewardInfoService.getAgentSysMposActivityRewardInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出线上活动(MPOS)奖励类型信息
	 */
	@Log(title = "线上活动(MPOS)奖励类型管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysMposActivityRewardInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSysMposActivityRewardInfo> list = agentSysMposActivityRewardInfoService.selectAgentSysMposActivityRewardInfoList(params);
        ExcelUtil<AgentSysMposActivityRewardInfo> util = new ExcelUtil<AgentSysMposActivityRewardInfo>(AgentSysMposActivityRewardInfo.class);
        return util.exportExcel(list, "线上活动奖励类型");
    }
	
	
	/**
     * 跳转新增活动奖励类型信息页面
     * @param roleId
     * @param mmap
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:add")
    @GetMapping("/addReward/{id}")
    public String authUser(@PathVariable("id") String id, String operParam,  ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddSysMposActivityRewardInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/addReward";
    	}
    }
	
    
    /**
     * 新增保存活动奖励类型信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:add")
    @Log(title = "线上活动(MPOS)管理奖励类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysMposActivityRewardInfoService.addAgentSysMposActivityRewardInfos(params);
    }
    
    
    /**
     * 跳转修改活动奖励类型信息页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentEditSysMposActivityRewardInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
     * 修改保存活动信息（只能待发布未删除的状态才能编辑）
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:edit")
    @Log(title = "线上活动(MPOS)奖励类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return agentSysMposActivityRewardInfoService.editAgentSysMposActivityRewardInfo(params);
    }
    
    
    /**
     * 根据id查看线上活动(MPOS)奖励类型详情
     * @param id
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:list")
    @PostMapping("/getAgentSysMposActivityRewardInfoById")
    @ResponseBody
    public Map<String, Object> getAgentSysMposActivityRewardInfoById(String id)
    {
        return agentSysMposActivityRewardInfoService.getAgentSysMposActivityRewardInfoById(id);
    }
    
    
    /**
	 * 跳转到批量线上活动(MPOS)奖励类型页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysMposActivityRewardInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysMposActivityRewardInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除线上活动(MPOS)奖励类型====>只能删除未发布且未删除的活动
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysMposActivityRewardInfo:remove")
    @Log(title = "线上活动(MPOS)奖励类型管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysMposActivityRewardInfoService.batchRemoveAgentSysMposActivityRewardInfo(params);
    }
	
}
