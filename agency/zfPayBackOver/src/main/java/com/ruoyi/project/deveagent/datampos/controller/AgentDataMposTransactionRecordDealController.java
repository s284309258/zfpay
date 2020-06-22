package com.ruoyi.project.deveagent.datampos.controller;

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
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecordDeal;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposTransactionRecordDealService;

/**
 * 代理======交易记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/dataMposTransactionRecordDeal")
public class AgentDataMposTransactionRecordDealController extends BaseController
{
    private String prefix = "deveagent/dataMposTransactionRecordDeal";
	
	@Autowired
	private AgentDataMposTransactionRecordDealService agentDataMposTransactionRecordDealService;
	
	
	/**
	 * 跳转MPOS交易记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:dataMposTransactionRecordDeal:view")
	@GetMapping()
	public String dataMposTransactionRecordDeal()
	{
	    return prefix + "/dataMposTransactionRecordDeal";
	}
	
	
	/**
	 * 查询MPOS交易记录列表
	 */
	@RequiresPermissions("deveagent:dataMposTransactionRecordDeal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentDataMposTransactionRecordDealService.getAgentDataMposTransactionRecordDealList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出MPOS交易记录记录
	 */
	@Log(title = "MPOS交易记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:dataMposTransactionRecordDeal:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentDataMposTransactionRecordDeal> list = agentDataMposTransactionRecordDealService.selectAgentDataMposTransactionRecordDealList(params);
        ExcelUtil<AgentDataMposTransactionRecordDeal> util = new ExcelUtil<AgentDataMposTransactionRecordDeal>(AgentDataMposTransactionRecordDeal.class);
        return util.exportExcel(list, "MPOS交易记录数据");
    }
	
}
