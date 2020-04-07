package com.ruoyi.project.devemana.email.controller;

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
import com.ruoyi.project.devemana.email.domain.ManaVerifyCode;
import com.ruoyi.project.devemana.email.service.ManaVerifyCodeService;

/**
 * 验证码发送   操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/verifyCode")
public class ManaVerifyCodeController extends BaseController
{
    private String prefix = "devemana/verifyCode";
	
    @Autowired
	private ManaVerifyCodeService manaVerifyCodeService;
	
	
	/**
	 * 跳转验证码发送列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:verifyCode:view")
	@GetMapping()
	public String verifyCode()
	{
	    return prefix + "/verifyCode";
	}
	
	
	/**
	 * 查询验证码发送列表
	 */
	@RequiresPermissions("devemana:verifyCode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaVerifyCodeService.getManaVerifyCodeList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出验证码发送数据
	 */
	@Log(title = "验证码发送管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:verifyCode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaVerifyCode> list = manaVerifyCodeService.selectManaVerifyCodeList(params);
        ExcelUtil<ManaVerifyCode> util = new ExcelUtil<ManaVerifyCode>(ManaVerifyCode.class);
        return util.exportExcel(list, "验证码发送数据");
    }
    
}