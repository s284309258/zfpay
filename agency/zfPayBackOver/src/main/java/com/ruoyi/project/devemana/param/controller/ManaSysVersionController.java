package com.ruoyi.project.devemana.param.controller;

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
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysVersion;
import com.ruoyi.project.devemana.param.service.ManaSysVersionService;

/**
 * 系统版本操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/sysVersion")
public class ManaSysVersionController extends BaseController
{
    private String prefix = "devemana/sysVersion";
	
	@Autowired
	private ManaSysVersionService manaSysVersionService;
	
	
	/**
	 * 跳转版本列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:sysVersion:view")
	@GetMapping()
	public String sysVersion()
	{
	    return prefix + "/sysVersion";
	}
	
	
	/**
	 * 查询版本列表
	 */
	@RequiresPermissions("devemana:sysVersion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaSysVersionService.getManaSysVersionList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出版本列表
	 */
	@Log(title = "系统版本列表管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:sysVersion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSysVersion> list = manaSysVersionService.selectManaSysVersiontList(params);
        ExcelUtil<ManaSysVersion> util = new ExcelUtil<ManaSysVersion>(ManaSysVersion.class);
        return util.exportExcel(list, "系统套餐信息数据");
    }
	
    
    /**
     * 跳转修改版本页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		mmap.put("sysVersion", manaSysVersionService.getManaSysVersionById(id));
        return prefix + "/edit";
    }
    
    
    /**
     * 根据参数id查询版本详情
     * @param id
     * @return
     */
    @RequiresPermissions("devemana:sysVersion:list")
    @PostMapping("/getSysVersionById")
    @ResponseBody
    public ManaSysVersion getManaSysVersionById(String id)
    {
        return manaSysVersionService.getManaSysVersionById(id);
    }

   
    /**
     * 修改保存系统版本
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysVersion:edit")
    @Log(title = "系统版本信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysVersionService.editManaSysVersion(params);
    }
    
    
    /**
     * 跳转新增版本页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(String id, String operParam, ModelMap mmap)
    {
		mmap.put("id", id);
        return prefix + "/add";
    }
    
    
    /**
     * 新增保存系统版本
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysVersion:edit")
    @Log(title = "系统版本信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
        return manaSysVersionService.addManaSysVersion(params);
    }
    
    
    /**
     * 批量删除系统版本
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysVersion:remove")
    @Log(title = "系统版本信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysVersionService.batchRemoveManaSysVersion(ids);
    }
    
}
