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
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.service.ZhongFuDataAcquireService;
import com.ruoyi.project.devemana.user.domain.ManaUserInfo;
import com.ruoyi.project.devemana.user.service.ManaUserInfoService;

/**
 * 管理员======用户信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/userInfo")
public class ManaUserInfoController extends BaseController
{
    private String prefix = "devemana/userInfo";
	
	@Autowired
	private ManaUserInfoService manaUserInfoService;
	@Autowired
	private ZhongFuDataAcquireService zhongFuDataAcquireService;
	
	
	
	
	/**
	 * 跳转用户信息列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:userInfo:view")
	@GetMapping()
	public String userInfo()
	{
	    return prefix + "/userInfo";
	}
	
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("devemana:userInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaUserInfoService.getManaUserInfoList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("devemana:userInfo:list")
	@PostMapping("/summaryManaUserInfoList")
	@ResponseBody
	public Map<String, Object> summaryManaUserInfoList(@RequestParam Map<String, Object> params)
	{
		return manaUserInfoService.summaryManaUserInfoList(params);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "用户信息管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:userInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaUserInfo> list = manaUserInfoService.selectManaUserInfoList(params);
        ExcelUtil<ManaUserInfo> util = new ExcelUtil<ManaUserInfo>(ManaUserInfo.class);
        return util.exportExcel(list, "用户信息数据");
    }
	
	
	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("devemana:userInfo:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userInfo", manaUserInfoService.getManaUserInfoById(id));
        return prefix + "/detail";
    }
	
	
	/**
	 * 选择日期
	 * @return
	 */
	@RequiresPermissions("devemana:userInfo:selectDate")
	@GetMapping("/selectDate")
    public String selectDate()
    {
		return prefix + "/selectDate";
    }
	
	
	/**
	 * 获取中付返现数据，插入到待处理表中
	 * @param params
	 * @return
	 */
	@RequiresPermissions("devemana:userInfo:selectDate")
	@PostMapping("/getDataMachineCashbackRecordDeal")
    @ResponseBody
    public R getDataMachineCashbackRecordDeal(@RequestParam Map<String, Object> params)
    {
		zhongFuDataAcquireService.getDataMachineCashbackRecordDeal(params.get("date").toString());
        return R.ok("操作成功");
    }
	
}
