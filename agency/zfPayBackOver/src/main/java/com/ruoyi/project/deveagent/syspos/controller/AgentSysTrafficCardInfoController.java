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
import com.ruoyi.project.deveagent.syspos.domain.AgentSysTrafficCardInfo;
import com.ruoyi.project.deveagent.syspos.service.AgentSysTrafficCardInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======系统流量卡信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/sysTrafficCardInfo")
public class AgentSysTrafficCardInfoController extends BaseController
{
    private String prefix = "deveagent/sysTrafficCardInfo";
	
	@Autowired
	private AgentSysTrafficCardInfoService agentSysTrafficCardInfoService;
	
	
	/**
	 * 跳转系统流量卡信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:sysTrafficCardInfo:view")
	@GetMapping()
	public String sysTrafficCardInfo()
	{
	    return prefix + "/sysTrafficCardInfo";
	}
	
	
	/**
	 * 查询系统流量卡信息列表
	 */
	@RequiresPermissions("deveagent:sysTrafficCardInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentSysTrafficCardInfoService.getAgentSysTrafficCardInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出系统流量卡信息
	 */
	@Log(title = "系统流量卡信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:sysTrafficCardInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentSysTrafficCardInfo> list = agentSysTrafficCardInfoService.selectAgentSysTrafficCardInfoList(params);
        ExcelUtil<AgentSysTrafficCardInfo> util = new ExcelUtil<AgentSysTrafficCardInfo>(AgentSysTrafficCardInfo.class);
        return util.exportExcel(list, "系统流量卡数据");
    }
	
	
	/**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("deveagent:sysTrafficCardInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysTrafficCardInfo> util = new ExcelUtil<AgentSysTrafficCardInfo>(AgentSysTrafficCardInfo.class);
        return util.importTemplateExcel("系统流量卡数据");
    }
    
    
    /**
     * 导入系统流量卡信息数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "系统流量卡信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("deveagent:sysTrafficCardInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public R importData(MultipartFile file, boolean updateSupport) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<AgentSysTrafficCardInfo> util = new ExcelUtil<AgentSysTrafficCardInfo>(AgentSysTrafficCardInfo.class);
        //对excel表单默认第一个索引名转换成list
        List<AgentSysTrafficCardInfo> agentsysTrafficCardInfoList = util.importExcel(file.getInputStream());
        //导入系统流量卡数据信息
        return agentSysTrafficCardInfoService.importAgentSysTrafficCardInfoList(agentsysTrafficCardInfoList);
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
     * 新增保存系统流量卡
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysTrafficCardInfo:add")
    @Log(title = "系统流量卡信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentSysTrafficCardInfoService.addAgentSysTrafficCardInfo(params);
    }

    
    /**
	 * 跳转到批量删除系统流量卡页面
	 * @param id
	 * @param mmap
	 * @return
	 */
    @GetMapping("/del/{id}")
	@RequiresPermissions("deveagent:sysTrafficCardInfo:remove")
    public String toSysAudit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentDelSysTrafficCardInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
	
	
	/**
     * 批量删除系统流量卡====>只能删除未分配的流量卡
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:sysTrafficCardInfo:remove")
    @Log(title = "系统流量卡信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return agentSysTrafficCardInfoService.batchRemoveAgentSysTrafficCardInfo(params);
    }
    
    
    /**
	 * 根据卡号查询详情
	 * @param id
	 * @param mmap
	 * @return
	 */
//    @RequiresPermissions("deveagent:sysTrafficCardInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("sysTrafficCardInfo", agentSysTrafficCardInfoService.getAgentSysTrafficCardInfoDetailByCardNo(id));
        return prefix + "/detail";
    }
	
}
