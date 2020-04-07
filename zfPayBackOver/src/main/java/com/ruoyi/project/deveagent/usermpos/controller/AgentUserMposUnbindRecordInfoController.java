package com.ruoyi.project.deveagent.usermpos.controller;

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
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposUnbindRecordInfo;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposUnbindRecordInfoService;
import com.ruoyi.project.develop.common.domain.R;


/**
 * 代理======用户MPOS解绑记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userMposUnbindRecordInfo")
public class AgentUserMposUnbindRecordInfoController extends BaseController
{
    private String prefix = "deveagent/userMposUnbindRecordInfo";
	
	@Autowired
	private AgentUserMposUnbindRecordInfoService agentUserMposUnbindRecordInfoService;
	
	
	/**
	 * 跳转用户MPOS解绑记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userMposUnbindRecordInfo:view")
	@GetMapping()
	public String userMposUnbindRecordInfo()
	{
	    return prefix + "/userMposUnbindRecordInfo";
	}
	
	
	/**
	 * 查询用户MPOS解绑记录列表
	 */
	@RequiresPermissions("deveagent:userMposUnbindRecordInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposUnbindRecordInfoService.getAgentUserMposUnbindRecordInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户MPOS解绑记录信息
	 */
	@Log(title = "用户MPOS解绑记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userMposUnbindRecordInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserMposUnbindRecordInfo> list = agentUserMposUnbindRecordInfoService.selectAgentUserMposUnbindRecordInfoList(params);
        ExcelUtil<AgentUserMposUnbindRecordInfo> util = new ExcelUtil<AgentUserMposUnbindRecordInfo>(AgentUserMposUnbindRecordInfo.class);
        return util.exportExcel(list, "用户MPOS解绑记录数据");
    }
	
	
	/**
	 * 系统批量审核解绑申请
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("deveagent:userMposUnbindRecordInfo:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditUserMposUnbindRecordInfo;
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
	@RequiresPermissions("deveagent:userMposUnbindRecordInfo:sysAudit")
    @Log(title = "用户MPOS解绑记录管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserMposUnbindRecordInfoService.batchAgentSysAuditUserMposUnbindRecordInfo(params);
    }
	
}
