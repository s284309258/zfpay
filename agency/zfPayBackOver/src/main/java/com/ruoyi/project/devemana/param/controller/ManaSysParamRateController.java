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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;
import com.ruoyi.project.devemana.param.service.ManaSysParamRateService;

/**
 * 管理员======系统费率参数操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/sysParamRate")
public class ManaSysParamRateController extends BaseController
{
    private String prefix = "devemana/sysParamRate";
	
	@Autowired
	private ManaSysParamRateService manaSysParamRateServices;
	
	
	/**
	 * 跳转系统费率参数列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:sysParamRate:view")
	@GetMapping()
	public String sysParamRate()
	{
	    return prefix + "/sysParamRate";
	}
	
	
	/**
	 * 查询系统费率参数列表
	 */
	@RequiresPermissions("devemana:sysParamRate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaSysParamRateServices.getManaSysParamRateList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出系统费率参数
	 */
	@Log(title = "系统费率参数管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:sysParamRate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSysParamRate> list = manaSysParamRateServices.selectManaSysParamRateList(params);
        ExcelUtil<ManaSysParamRate> util = new ExcelUtil<ManaSysParamRate>(ManaSysParamRate.class);
        return util.exportExcel(list, "系统费率参数数据");
    }
	
	
    /**
     * 跳转到新增页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_ManaAddSysParamRate;
    	}else {
    		mmap.put("id", id);
            return prefix + "/add";
    	}
    }
    
    
    /**
     * 新增保存系统费率参数
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysParamRate:add")
    @Log(title = "系统费率参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysParamRateServices.addManaSysParamRate(params);
    }
    
	
	
    /**
     * 跳转修改系统费率参数页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_ManaEditSysParamRate;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    

    /**
     * 修改保存系统费率参数
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysParamRate:edit")
    @Log(title = "系统费率参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysParamRateServices.editManaSysParamRate(params);
    }
    
    
    /**
	 * 根据用户id查询系统费率参数详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@PostMapping("/getManaSysParamRateById")
	@ResponseBody
    public Map<String, Object> getManaSysParamRateById(String id)
    {
        return manaSysParamRateServices.getManaSysParamRateById(id);
    }
	
	
	 /**
     * 跳转删除系统费率参数页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_ManaDelSysParamRate;
    	}else {
    		mmap.put("id", id);
            return prefix + "/del";
    	}
    }
    

    /**
     * 批量删除系统费率参数
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysParamRate:remove")
    @Log(title = "系统费率参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return manaSysParamRateServices.batchRemoveManaSysParamRate(params);
    }
    
    
    /**
	 * 根据类型查询参数值列表
	 * @return
	 */
	@PostMapping("/getManaParamRateListByType")
	@ResponseBody
    public List<ManaSysParamRate> getManaParamRateListByType(String type)
    {
        return manaSysParamRateServices.getManaParamRateListByType(type,"");
    }
	
}
