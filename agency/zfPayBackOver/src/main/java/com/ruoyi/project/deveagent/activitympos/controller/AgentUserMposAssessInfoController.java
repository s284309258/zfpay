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
import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposAssessInfo;
import com.ruoyi.project.deveagent.activitympos.service.AgentUserMposAssessInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户MPOS系统考核操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposAssessInfo")
public class AgentUserMposAssessInfoController extends BaseController
{
    private String prefix = "deveagent/userMposAssessInfo";
	
	@Autowired
	private AgentUserMposAssessInfoService agentUserMposAssessInfoService;
	
	
	/**
	 * 跳转用户MPOS系统考核列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposAssessInfo:view")
	@GetMapping()
	public String userMposAssessInfo()
	{
	    return prefix + "/userMposAssessInfo";
	}
	
	
	/**
	 * 查询用户MPOS系统考核列表
	 */
	@RequiresPermissions("deveagent:userMposAssessInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposAssessInfoService.getAgentUserMposAssessInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户MPOS系统考核
	 */
	@Log(title = "用户MPOS系统考核管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userMposAssessInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserMposAssessInfo> list = agentUserMposAssessInfoService.selectAgentUserMposAssessInfoList(params);
        ExcelUtil<AgentUserMposAssessInfo> util = new ExcelUtil<AgentUserMposAssessInfo>(AgentUserMposAssessInfo.class);
        return util.exportExcel(list, "用户MPOS系统考核");
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddUserMposAssessInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/selectUser";
    	}
    }
    
    
    /**
     * 跳转到新增页面=====》选择用户MPOS机
     * @param mmap
     * @return
     */
    @GetMapping("/selectPos/{id}")
    public String selectPos(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("user_id", id);
        return prefix + "/selectPos";
    }
    
    
    /**
     * 新增保存活动信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userMposAssessInfo:add")
    @Log(title = "MPOS系统考核管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserMposAssessInfoService.addAgentUserMposAssessInfo(params);
    }
    
    
    /**
	 * 跳转到批量删除MPOS系统考核页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:userMposAssessInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelUserMposAssessInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除MPOS系统考核
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userMposAssessInfo:remove")
    @Log(title = "MPOS系统考核管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentUserMposAssessInfoService.batchRemoveAgentUserMposAssessInfo(params);
    }
    
    
    /**
	 * 根据id查询考核活动详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposAssessInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("assessInfo", agentUserMposAssessInfoService.getAgentUserMposAssessInfoById(id));
        return prefix + "/detail";
    }
	
}
