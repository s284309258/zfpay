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
import com.ruoyi.project.devemana.param.domain.ManaSysAppImg;
import com.ruoyi.project.devemana.param.service.ManaSysAppImgService;

/**
 * APP图片操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/sysAppImg")
public class ManaSysAppImgController extends BaseController
{
    private String prefix = "devemana/sysAppImg";
	
	@Autowired
	private ManaSysAppImgService manaSysAppImgService;
	
	
	/**
	 * 跳转图片列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:sysAppImg:view")
	@GetMapping()
	public String sysAppImg()
	{
	    return prefix + "/sysAppImg";
	}
	
	
	/**
	 * 查询图片列表
	 */
	@RequiresPermissions("devemana:sysAppImg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaSysAppImgService.getManaSysAppImgList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出图片列表
	 */
	@Log(title = "APP图片管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:sysAppImg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSysAppImg> list = manaSysAppImgService.selectManaSysAppImgList(params);
        ExcelUtil<ManaSysAppImg> util = new ExcelUtil<ManaSysAppImg>(ManaSysAppImg.class);
        return util.exportExcel(list, "APP图片数据");
    }
	
    
    /**
     * 跳转修改图片页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("appImg", manaSysAppImgService.getManaSysAppImgById(id));
    	return prefix + "/edit";
    }
    
   
    /**
     * 修改APP图片
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysAppImg:edit")
    @Log(title = "APP图片管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysAppImgService.editManaSysAppImg(params);
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
     * 新增保存APP图片
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysAppImg:add")
    @Log(title = "APP图片管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysAppImgService.addManaSysAppImg(params);
    }
    
    
    /**
     * 批量删除APP图片
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysAppImg:remove")
    @Log(title = "APP图片管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysAppImgService.batchRemoveManaSysAppImg(ids);
    }
	
}
