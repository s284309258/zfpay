package com.ruoyi.project.deveagent.datatrapos.controller;

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
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposTransactionRecordDeal;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposTransactionRecordDealService;

/**
 * 代理======交易记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/dataTraposTransactionRecordDeal")
public class AgentDataTraposTransactionRecordDealController extends BaseController
{
    private String prefix = "deveagent/dataTraposTransactionRecordDeal";
	
	@Autowired
	private AgentDataTraposTransactionRecordDealService agentDataTraposTransactionRecordDealService;
	
	
	/**
	 * 跳转传统POS交易记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:dataTraposTransactionRecordDeal:view")
	@GetMapping()
	public String dataTraposTransactionRecordDeal()
	{
	    return prefix + "/dataTraposTransactionRecordDeal";
	}
	
	
	/**
	 * 查询传统POS交易记录列表
	 */
	@RequiresPermissions("deveagent:dataTraposTransactionRecordDeal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentDataTraposTransactionRecordDealService.getAgentDataTraposTransactionRecordDealList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出传统POS交易记录记录
	 */
	@Log(title = "传统POS交易记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:dataTraposTransactionRecordDeal:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentDataTraposTransactionRecordDeal> list = agentDataTraposTransactionRecordDealService.selectAgentDataTraposTransactionRecordDealList(params);
        ExcelUtil<AgentDataTraposTransactionRecordDeal> util = new ExcelUtil<AgentDataTraposTransactionRecordDeal>(AgentDataTraposTransactionRecordDeal.class);
        return util.exportExcel(list, "传统POS交易记录数据");
    }
	
}
