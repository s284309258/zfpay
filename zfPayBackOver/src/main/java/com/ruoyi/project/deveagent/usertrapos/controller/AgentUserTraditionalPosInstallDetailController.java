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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallDetail;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraditionalPosInstallDetailService;
import com.ruoyi.project.develop.common.domain.R;


/**
 * 代理======用户传统PO商户进件详情操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraditionalPosInstallDetail")
public class AgentUserTraditionalPosInstallDetailController extends BaseController
{
    private String prefix = "deveagent/userTraditionalPosInstallDetail";
	
	@Autowired
	private AgentUserTraditionalPosInstallDetailService agentUserTraditionalPosInstallDetailService;
	
	
	/**
	 * 跳转用户传统POS商户进件详情列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInstallDetail:view")
	@GetMapping()
	public String userTraditionalPosInstallDetail()
	{
	    return prefix + "/userTraditionalPosInstallDetail";
	}
	
	
	/**
	 * 查询用户传统POS商户进件详情列表
	 */
	@RequiresPermissions("deveagent:userTraditionalPosInstallDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraditionalPosInstallDetailService.getAgentUserTraditionalPosInstallDetailList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户传统POS商户进件信息
	 */
	@Log(title = "用户传统POS商户进件详情管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraditionalPosInstallDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraditionalPosInstallDetail> list = agentUserTraditionalPosInstallDetailService.selectAgentUserTraditionalPosInstallDetailList(params);
        ExcelUtil<AgentUserTraditionalPosInstallDetail> util = new ExcelUtil<AgentUserTraditionalPosInstallDetail>(AgentUserTraditionalPosInstallDetail.class);
        return util.exportExcel(list, "用户传统POS商户进件详情数据");
    }
	
	
	/**
     * 跳转添加进件详细信息页面
     * @param id
     * @param operParam
     * @param mmap
     * @return
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("install_id", id);
        return prefix + "/add";
    }
    
    
    /**
     * 新增保存进件信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallDetail:add")
    @Log(title = "用户传统POS商户进件详情管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTraditionalPosInstallDetailService.addAgentUserTraditionalPosInstallDetail(params);
    }
    
    
   /**
    * 跳转修改进件信息页面
    * @param id
    * @param operParam
    * @param mmap
    * @return
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
		mmap.put("installDetail", agentUserTraditionalPosInstallDetailService.getAgentUserTraditionalPosInstallDetailById(id));
        return prefix + "/edit";
    }
    
    
    /**
     * 修改保存进件信息
     * @param user
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallDetail:edit")
    @Log(title = "用户传统POS商户进件详情管理", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
    	return agentUserTraditionalPosInstallDetailService.editAgentUserTraditionalPosInstallDetail(params);
    }
    
    
    /**
     * 批量删除商户进件信息
     * @param ids
     * @return
     */
    @RequiresPermissions("deveagent:userTraditionalPosInstallDetail:remove")
    @Log(title = "用户传统POS商户进件详情管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return agentUserTraditionalPosInstallDetailService.batchRemoveAgentUserTraditionalPosInstallDetail(ids);
    }
	
}
