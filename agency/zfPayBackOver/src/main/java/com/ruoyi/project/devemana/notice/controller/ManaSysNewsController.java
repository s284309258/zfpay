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
import com.ruoyi.project.devemana.notice.service.ManaSysNewsService;

/**
 * 管理员=====新闻资讯操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/devemana/sysNews")
public class ManaSysNewsController extends BaseController
{
    private String prefix = "devemana/sysNews";

    @Autowired
    private ManaSysNewsService manaSysNewsService;

    
    /**
     * 跳转新闻资讯列表页面
     * @return
     */
    @RequiresPermissions("devemana:sysNews:view")
    @GetMapping()
    public String sysNews()
    {
        return prefix + "/sysNews";
    }

   
    /**
     * 查询新闻资讯列表
     * @param notice
     * @return
     */
    @RequiresPermissions("devemana:sysNews:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询公告列表
        List<Map<String, Object>> list = manaSysNewsService.getManaSysNewsList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 跳转新增新闻资讯页面
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存新闻资讯
     * @param params
     * @return
     */
    @RequiresPermissions("devemana:sysNews:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return manaSysNewsService.addManaSysNews(params);
    }

    
    /**
     * 跳转修改新闻资讯页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	//根据公告id查询新闻资讯详情
        mmap.put("sysNews", manaSysNewsService.getManaNewsById(id));
        return prefix + "/edit";
    }

   
    /**
     * 修改保存新闻资讯
     * @param notice
     * @return
     */
    @RequiresPermissions("devemana:sysNews:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return manaSysNewsService.editManaSysNews(params);
    }
    
    
    /**
     * 批量删除系统新闻资讯
     * @param ids
     * @return
     */
    @RequiresPermissions("devemana:sysNews:remove")
    @Log(title = "新闻资讯管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return manaSysNewsService.batchRemoveManaSysNews(ids);
    }
    
    
    /**
	 * 根据id查询新闻详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("devemana:sysNews:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("sysNews", manaSysNewsService.getManaNewsById(id));
        return prefix + "/detail";
    }
   
}
