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
import com.ruoyi.project.devemana.param.domain.ManaSysBankInfo;
import com.ruoyi.project.devemana.param.service.ManaSysBankInfoService;

/**
 * 管理员====银行基础信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/sysBankInfo")
public class ManaSysBankInfoController extends BaseController
{
    private String prefix = "devemana/sysBankInfo";
	
	@Autowired
	private ManaSysBankInfoService manaSysBankInfoService;
	
	
	/**
	 * 跳转银行基础信息列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:sysBankInfo:view")
	@GetMapping()
	public String sysBankInfo()
	{
	    return prefix + "/sysBankInfo";
	}
	
	
	/**
	 * 查询银行基础信息列表
	 */
	@RequiresPermissions("devemana:sysBankInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaSysBankInfoService.getManaSysBankInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出银行基础信息列表
	 */
	@Log(title = "银行基础信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:sysBankInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSysBankInfo> list = manaSysBankInfoService.selectManaSysBankInfoList(params);
        ExcelUtil<ManaSysBankInfo> util = new ExcelUtil<ManaSysBankInfo>(ManaSysBankInfo.class);
        return util.exportExcel(list, "银行基础信息数据");
    }
	
    
    /**
     * 跳转修改银行基础信息页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("bankInfo", manaSysBankInfoService.getManaSysBankInfoById(id));
    	return prefix + "/edit";
    }
    
   
    /**
     * 修改银行基础信息
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysBankInfo:edit")
    @Log(title = "银行基础信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysBankInfoService.editManaSysBankInfo(params);
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
     * 新增保存银行基础信息
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysBankInfo:add")
    @Log(title = "银行基础信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysBankInfoService.addManaSysBankInfo(params);
    }
    
    
    /**
     * 批量删除银行基础信息
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysBankInfo:remove")
    @Log(title = "银行基础信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysBankInfoService.batchRemoveManaSysBankInfo(ids);
    }
	
}
