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
import com.ruoyi.project.devemana.user.domain.ManaUserCard;
import com.ruoyi.project.devemana.user.service.ManaUserCardService;

/**
 * 管理员======用户结算卡操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/devemana/userCard")
public class ManaUserCardController extends BaseController
{
    private String prefix = "devemana/userCard";
	
	@Autowired
	private ManaUserCardService manaUserCardService;
	
	
	/**
	 * 跳转用户结算卡列表页面
	 * @return
	 */
	@RequiresPermissions("devemana:userCard:view")
	@GetMapping()
	public String userCard()
	{
	    return prefix + "/userCard";
	}
	
	
	/**
	 * 查询用户结算卡列表
	 */
	@RequiresPermissions("devemana:userCard:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = manaUserCardService.getManaUserCardList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "用户结算卡管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("devemana:userCard:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<ManaUserCard> list = manaUserCardService.selectManaUserCardList(params);
        ExcelUtil<ManaUserCard> util = new ExcelUtil<ManaUserCard>(ManaUserCard.class);
        return util.exportExcel(list, "用户结算卡数据");
    }
	
	
	/**
	 * 根据用户id查询用户结算卡详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("devemana:userCard:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userCard", manaUserCardService.getManaUserCardById(id));
        return prefix + "/detail";
    }
	
}
