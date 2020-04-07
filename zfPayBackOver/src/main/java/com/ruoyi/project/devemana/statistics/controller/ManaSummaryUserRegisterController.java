package com.ruoyi.project.devemana.statistics.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ruoyi.project.devemana.statistics.domain.ManaSummaryUserRegisterEvery;
import com.ruoyi.project.devemana.user.service.ManaUserInfoService;

/**
 * 用户信息实时统计
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/summaryUserRegister")
public class ManaSummaryUserRegisterController extends BaseController
{
    private String prefix = "devemana/summaryUserRegister";
	
	@Autowired
	private ManaUserInfoService manaUserInfoService;
	
	
	/**
	 * 跳转用户实时注册量列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:summaryUserRegister:view")
	@GetMapping()
	public String summaryUserRegister()
	{
	    return prefix + "/summaryUserRegister";
	}
	
	
	/**
	 * 查询用户流水类性汇总列表
	 */
	@RequiresPermissions("devemana:summaryUserRegister:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaUserInfoService.getManaSummaryUserRegisterList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流水类每日汇总列表
	 */
	@Log(title = "用户注册量实时统计", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:summaryUserRegister:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaSummaryUserRegisterEvery> list = manaUserInfoService.selectManaSummaryUserRegisterList(params);
        ExcelUtil<ManaSummaryUserRegisterEvery> util = new ExcelUtil<ManaSummaryUserRegisterEvery>(ManaSummaryUserRegisterEvery.class);
        return util.exportExcel(list, "用户注册量实时统计数据");
    }

}
