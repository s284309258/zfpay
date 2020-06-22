package com.ruoyi.project.devemana.user.controller;

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
import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;
import com.ruoyi.project.devemana.user.service.ManaUserCashRecordService;

/**
 * 管理员======用户取现记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/userCashRecord")
public class ManaUserCashRecordController extends BaseController
{
    private String prefix = "devemana/userCashRecord";
	
	@Autowired
	private ManaUserCashRecordService manaUserCashRecordService;
	
	
	/**
	 * 跳转用户取现记录列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:userCashRecord:view")
	@GetMapping()
	public String userCashRecord()
	{
	    return prefix + "/userCashRecord";
	}
	
	
	/**
	 * 查询用户取现记录列表
	 */
	@RequiresPermissions("devemana:userCashRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaUserCashRecordService.getManaUserCashRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计取现信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("devemana:userCashRecord:list")
	@PostMapping("/summaryManaUserCashRecordList")
	@ResponseBody
	public Map<String, Object> summaryManaUserCashRecordList(@RequestParam Map<String, Object> params)
	{
		return manaUserCashRecordService.summaryManaUserCashRecordList(params);
	}
	
	
	/**
	 * 导出用户取现记录
	 */
	@Log(title = "用户取现记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:userCashRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaUserCashRecord> list = manaUserCashRecordService.selectManaUserCashRecordList(params);
        ExcelUtil<ManaUserCashRecord> util = new ExcelUtil<ManaUserCashRecord>(ManaUserCashRecord.class);
        return util.exportExcel(list, "用户取现记录数据");
    }
	
	
	/**
     * 跳转跳转用户取现记录详情列表页面
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("devemana:userCashRecord:detail")
    @GetMapping("/detailList/{id}")
    public String detailList(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("cash_id", id);
        return prefix + "/detailList";
    }
	
	
	/**
	 * 查询用户取现记录详情列表
	 */
	@RequiresPermissions("devemana:userCashRecord:detail")
	@PostMapping("/detailList")
	@ResponseBody
	public TableDataInfo detailList(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaUserCashRecordService.getManaUserCashRecordDetailList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
}
