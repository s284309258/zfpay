package com.ruoyi.project.deveagent.activitytrapos.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityRewardRecord;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserEposActivityRewardRecordService;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserTraposActivityRewardRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户线上活动(传统POS)奖励记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userEposActivityRewardRecord")
public class AgentUserEposActivityRewardRecordController extends BaseController
{
    private String prefix = "deveagent/userEposActivityRewardRecord";
	
	@Autowired
	private AgentUserEposActivityRewardRecordService agentUserEposActivityRewardRecordService;
	
	
	/**
	 * 跳转用户线上活动(传统POS)奖励记录列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userTraposActivityRewardRecord:view")
	@GetMapping()
	public String userTraposActivityRewardRecord()
	{
	    return prefix + "/userTraposActivityRewardRecord";
	}
	
	
	/**
	 * 查询用户线上活动(传统POS)奖励记录列表
	 */
	@RequiresPermissions("deveagent:userTraposActivityRewardRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserEposActivityRewardRecordService.getAgentUserTraposActivityRewardRecordList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户线上活动(传统POS)奖励记录
	 */
	@Log(title = "用户线上活动(传统POS)奖励记录管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userTraposActivityRewardRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserTraposActivityRewardRecord> list = agentUserEposActivityRewardRecordService.selectAgentUserTraposActivityRewardRecordList(params);
        ExcelUtil<AgentUserTraposActivityRewardRecord> util = new ExcelUtil<AgentUserTraposActivityRewardRecord>(AgentUserTraposActivityRewardRecord.class);
        return util.exportExcel(list, "用户线上活动传统POS奖励记录");
    }
	
}
