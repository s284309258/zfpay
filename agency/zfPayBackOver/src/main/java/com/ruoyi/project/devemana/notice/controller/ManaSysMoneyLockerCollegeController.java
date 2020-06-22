package com.ruoyi.project.devemana.notice.controller;

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

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.notice.service.ManaSysMoneyLockerCollegeService;

/**
 * 管理员=====钱柜学院操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/devemana/sysMoneyLockerCollege")
public class ManaSysMoneyLockerCollegeController extends BaseController
{
    private String prefix = "devemana/sysMoneyLockerCollege";

    @Autowired
    private ManaSysMoneyLockerCollegeService manaSysMoneyLockerCollegeService;

    
    /**
     * 跳转钱柜学院列表页面
     * @return
     */
    @RequiresPermissions("devemana:sysMoneyLockerCollege:view")
    @GetMapping()
    public String sysMoneyLockerCollege()
    {
        return prefix + "/sysMoneyLockerCollege";
    }

   
    /**
     * 查询钱柜学院列表
     * @param notice
     * @return
     */
    @RequiresPermissions("devemana:sysMoneyLockerCollege:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询公告列表
        List<Map<String, Object>> list = manaSysMoneyLockerCollegeService.getManaSysMoneyLockerCollegeList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 跳转新增钱柜学院页面
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存钱柜学院
     * @param params
     * @return
     */
    @RequiresPermissions("devemana:sysMoneyLockerCollege:add")
    @Log(title = "钱柜学院", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysMoneyLockerCollegeService.addManaSysMoneyLockerCollege(params);
    }

    
    /**
     * 批量删除系统钱柜学院
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysMoneyLockerCollege:remove")
    @Log(title = "钱柜学院管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysMoneyLockerCollegeService.batchRemoveManaSysMoneyLockerCollege(ids);
    }
    
    
    /**
	 * 根据id查询钱柜学院详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("devemana:sysMoneyLockerCollege:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("sysMoneyLockerCollege", manaSysMoneyLockerCollegeService.getManaSysMoneyLockerCollegeById(id));
        return prefix + "/detail";
    }
   
}
