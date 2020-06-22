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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposUnbindRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindRecordInfoService;
import com.ruoyi.project.develop.common.domain.R;


/**
 * 代理======用户传统POS解绑记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraposUnbindRecordInfo")
public class AgentUserTraposUnbindRecordInfoController extends BaseController
{
    private String prefix = "deveagent/userTraposUnbindRecordInfo";
	
	@Autowired
	private AgentUserTraposUnbindRecordInfoService agentUserTraposUnbindRecordInfoService;
	
	
	/**
	 * 跳转用户传统POS解绑记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposUnbindRecordInfo:view")
	@GetMapping()
	public String userTraposUnbindRecordInfo()
	{
	    return prefix + "/userTraposUnbindRecordInfo";
	}
	
	
	/**
	 * 查询用户传统POS解绑记录列表
	 */
	@RequiresPermissions("deveagent:userTraposUnbindRecordInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraposUnbindRecordInfoService.getAgentUserTraposUnbindRecordInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS解绑记录信息
	 */
	@Log(title = "用户传统POS解绑记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraposUnbindRecordInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraposUnbindRecordInfo> list = agentUserTraposUnbindRecordInfoService.selectAgentUserTraposUnbindRecordInfoList(params);
        ExcelUtil<AgentUserTraposUnbindRecordInfo> util = new ExcelUtil<AgentUserTraposUnbindRecordInfo>(AgentUserTraposUnbindRecordInfo.class);
        return util.exportExcel(list, "用户传统POS解绑记录数据");
    }
	
	

	/**
	 * 系统批量审核解绑申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userTraposUnbindRecordInfo:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserTraposUnbindRecordInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	
	
	/**
	 * 系统批量审核解绑申请
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposUnbindRecordInfo:sysAudit")
    @Log(title = "用户传统POS解绑记录管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserTraposUnbindRecordInfoService.batchAgentSysAuditUserTraposUnbindRecordInfo(params);
    }
}
