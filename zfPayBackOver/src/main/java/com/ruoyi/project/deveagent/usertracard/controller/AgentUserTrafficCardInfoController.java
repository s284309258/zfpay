package com.ruoyi.project.deveagent.usertracard.controller;

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
import com.ruoyi.project.deveagent.usertracard.domain.AgentSelectUserTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.service.AgentUserTrafficCardInfoService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户流量卡信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTrafficCardInfo")
public class AgentUserTrafficCardInfoController extends BaseController
{
    private String prefix = "deveagent/userTrafficCardInfo";
	
	@Autowired
	private AgentUserTrafficCardInfoService agentUserTrafficCardInfoService;
	
	
	/**
	 * 跳转用户流量卡信息列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTrafficCardInfo:view")
	@GetMapping()
	public String userTrafficCardInfo()
	{
	    return prefix + "/userTrafficCardInfo";
	}
	
	
	/**
	 * 查询用户流量卡信息列表
	 */
	@RequiresPermissions("deveagent:userTrafficCardInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTrafficCardInfoService.getAgentUserTrafficCardInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流量卡信息
	 */
	@Log(title = "用户流量卡信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTrafficCardInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTrafficCardInfo> list = agentUserTrafficCardInfoService.selectAgentUserTrafficCardInfoList(params);
        ExcelUtil<AgentUserTrafficCardInfo> util = new ExcelUtil<AgentUserTrafficCardInfo>(AgentUserTrafficCardInfo.class);
        return util.exportExcel(list, "用户流量卡数据");
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentAddUserTrafficCardInfo;
    	}else {
    		mmap.put("id", id);
            return prefix + "/selectUser";
    	}
    }
    
    
    /**
     * 跳转到新增页面=====》选择系统流量卡机
     * @param mmap
     * @return
     */
    @GetMapping("/selectSysCard/{id}")
    public String selectPos(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("user_id", id);
        return prefix + "/selectSysCard";
    }
    
    
    /**
     * 导出导入数据模板
     * @return
     */
    @RequiresPermissions("deveagent:userTrafficCardInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
    	//Excel相关处理对象
        ExcelUtil<AgentUserTrafficCardInfo> util = new ExcelUtil<AgentUserTrafficCardInfo>(AgentUserTrafficCardInfo.class);
        return util.importTemplateExcel("用户流量卡机数据");
    }
    
    
    /**
     * 导入用户流量卡信息数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "用户流量卡信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("deveagent:userTrafficCardInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public R importData(MultipartFile file, String user_id) throws Exception
    {
    	//Excel相关处理对象
        ExcelUtil<AgentUserTrafficCardInfo> util = new ExcelUtil<AgentUserTrafficCardInfo>(AgentUserTrafficCardInfo.class);
        //对excel表单默认第一个索引名转换成list
        List<AgentUserTrafficCardInfo> agentuserTrafficCardInfoList = util.importExcel(file.getInputStream());
        //导入用户流量卡数据信息
        return agentUserTrafficCardInfoService.importAgentUserTrafficCardInfoList(agentuserTrafficCardInfoList,user_id);
    }
    
    
    /**
	 * 导出可分配的流量卡模板
	 */
	@Log(title = "用户流量卡信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTrafficCardInfo:add")
    @PostMapping("/exportDeal")
    @ResponseBody
    public AjaxResult exportDeal(@RequestParam Map<String, Object> params)
    {
    	List<AgentSelectUserTrafficCardInfo> list = agentUserTrafficCardInfoService.selectAgentNoDisSysTrafficCardInfoList(params);
        ExcelUtil<AgentSelectUserTrafficCardInfo> util = new ExcelUtil<AgentSelectUserTrafficCardInfo>(AgentSelectUserTrafficCardInfo.class);
        return util.exportExcel(list, "可分配的流量卡导入模板");
    }
	
	
	/**
     * 新增保存用户流量卡机信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userTrafficCardInfo:add")
    @Log(title = "用户流量卡信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTrafficCardInfoService.addAgentUserTrafficCardInfo(params);
    }
    
	
}
