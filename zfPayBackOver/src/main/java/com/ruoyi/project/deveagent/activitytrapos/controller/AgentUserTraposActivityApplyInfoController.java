package com.ruoyi.project.deveagent.activitytrapos.controller;

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
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityApplyInfo;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserTraposActivityApplyInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户线上活动(传统POS)申请记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraposActivityApplyInfo")
public class AgentUserTraposActivityApplyInfoController extends BaseController
{
    private String prefix = "deveagent/userTraposActivityApplyInfo";
	
	@Autowired
	private AgentUserTraposActivityApplyInfoService agentUserTraposActivityApplyInfoService;
	
	
	/**
	 * 跳转用户线上活动(传统POS)申请记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:view")
	@GetMapping()
	public String userTraposActivityApplyInfo()
	{
	    return prefix + "/userTraposActivityApplyInfo";
	}
	
	
	/**
	 * 查询用户线上活动(传统POS)申请记录列表
	 */
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraposActivityApplyInfoService.getAgentUserTraposActivityApplyInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户线上活动(传统POS)申请记录
	 */
	@Log(title = "用户线上活动(传统POS)申请记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraposActivityApplyInfo> list = agentUserTraposActivityApplyInfoService.selectAgentUserTraposActivityApplyInfoList(params);
        ExcelUtil<AgentUserTraposActivityApplyInfo> util = new ExcelUtil<AgentUserTraposActivityApplyInfo>(AgentUserTraposActivityApplyInfo.class);
        return util.exportExcel(list, "用户线上活动传统POS申请记录");
    }
	
	
	/**
	 * 根据用户id查询活动申请详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("applyInfo", agentUserTraposActivityApplyInfoService.getAgentUserTraposActivityApplyInfoDetailById(id));
        return prefix + "/detail";
    }
	
	
	/**
	 * 系统批量审核线上活动(传统POS)申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserTraposActivityApplyInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	
	
	/**
	 * 系统批量审核线上活动(传统POS)申请
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposActivityApplyInfo:sysAudit")
    @Log(title = "用户线上活动(传统POS)申请记录管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserTraposActivityApplyInfoService.batchSysAuditUserTraposActivityApplyInfo(params);
    }
	
	
}
