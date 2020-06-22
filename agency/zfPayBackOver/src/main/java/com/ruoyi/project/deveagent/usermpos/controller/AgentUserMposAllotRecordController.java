package com.ruoyi.project.deveagent.usermpos.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposAllotRecord;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import com.ruoyi.project.deveagent.usermpos.service.impl.AgentUserMposAllotRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deveagent/userMposAllotRecord")
public class AgentUserMposAllotRecordController  extends BaseController {

    private String prefix = "deveagent/userMposAllotRecord";

    @Autowired
    private AgentUserMposAllotRecordServiceImpl agentUserMposAllotRecordService;

    /**
     * 跳转用户MPOS信息列表页面
     * @return
     */
    @GetMapping()
    public String userMposDeductRecord()
    {
        return prefix + "/userMposAllotRecord";
    }

    /**
     * 查询用户MPOS未达标扣除记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
        //此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserMposAllotRecordService.getAgentUserMposAllotRecordList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }


    /**
     * 导出用户MPOS未达标扣除记录
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
        List<AgentUserMposAllotRecord> list = agentUserMposAllotRecordService.selectAgentUserMposAllotRecordList(params);
        ExcelUtil<AgentUserMposAllotRecord> util = new ExcelUtil<AgentUserMposAllotRecord>(AgentUserMposAllotRecord.class);
        return util.exportExcel(list, "MPOS下拨记录数据");
    }

}
