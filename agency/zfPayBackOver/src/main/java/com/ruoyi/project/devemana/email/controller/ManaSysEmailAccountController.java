package com.ruoyi.project.devemana.email.controller;

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
import com.ruoyi.project.devemana.email.domain.ManaSysEmailAccount;
import com.ruoyi.project.devemana.email.service.ManaSysEmailAccountService;

/**
 * 邮箱账号操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/sysEmailAccount")
public class ManaSysEmailAccountController extends BaseController
{
    private String prefix = "devemana/sysEmailAccount";
	
	@Autowired
	private ManaSysEmailAccountService manaSysEmailAccountService;
	
	
	/**
	 * 跳转邮箱账号列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:sysEmailAccount:view")
	@GetMapping()
	public String sysEmailAccount()
	{
	    return prefix + "/sysEmailAccount";
	}
	
	
	/**
	 * 查询邮箱账号列表
	 */
	@RequiresPermissions("devemana:sysEmailAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaSysEmailAccountService.getManaSysEmailAccountList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出邮箱账号列表
	 */
	@Log(title = "邮箱账号管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:sysEmailAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSysEmailAccount> list = manaSysEmailAccountService.selectManaSysEmailAccountList(params);
        ExcelUtil<ManaSysEmailAccount> util = new ExcelUtil<ManaSysEmailAccount>(ManaSysEmailAccount.class);
        return util.exportExcel(list, "邮箱账号数据");
    }
	
    
    /**
     * 跳转修改邮箱账号页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("sysEmailAccount", manaSysEmailAccountService.getManaSysEmailAccountById(id));
    	return prefix + "/edit";
    }
    
   
    /**
     * 修改邮箱账号
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysEmailAccount:edit")
    @Log(title = "邮箱账号管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysEmailAccountService.editManaSysEmailAccount(params);
    }

    
    /**
     * 跳转到新增邮箱账号页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    
    /**
     * 新增保存邮箱账号
     * @param user
     * @return
     */
    @RequiresPermissions("devemana:sysEmailAccount:add")
    @Log(title = "邮箱账号管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysEmailAccountService.addManaSysEmailAccount(params);
    }
    
    
    /**
     * 批量删除邮箱账号
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysEmailAccount:remove")
    @Log(title = "邮箱账号管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysEmailAccountService.batchRemoveManaSysEmailAccount(ids);
    }
	
}
