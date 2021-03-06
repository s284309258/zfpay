package com.ruoyi.project.deveagent.user.controller;

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
import com.ruoyi.project.deveagent.user.domain.AgentBenefitRecordMoney;
import com.ruoyi.project.deveagent.user.service.AgentBenefitRecordMoneyService;


/**
 * 代理======用户资金流水操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/benefitRecordMoney")
public class AgentBenefitRecordMoneyController extends BaseController
{
    private String prefix = "deveagent/benefitRecordMoney";
	
	@Autowired
	private AgentBenefitRecordMoneyService agentBenefitRecordMoneyService;
	
	
	/**
	 * 跳转用户资金流水列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:benefitRecordMoney:view")
	@GetMapping()
	public String benefitRecordMoney()
	{
	    return prefix + "/benefitRecordMoney";
	}
	
	
	/**
	 * 查询用户资金流水列表
	 */
	@RequiresPermissions("deveagent:benefitRecordMoney:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentBenefitRecordMoneyService.getAgentBenefitRecordMoneyList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户资金流水
	 */
	@Log(title = "用户资金流水管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:benefitRecordMoney:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentBenefitRecordMoney> list = agentBenefitRecordMoneyService.selectAgentBenefitRecordMoneyList(params);
        ExcelUtil<AgentBenefitRecordMoney> util = new ExcelUtil<AgentBenefitRecordMoney>(AgentBenefitRecordMoney.class);
        return util.exportExcel(list, "用户资金流水数据");
    }
	
}
