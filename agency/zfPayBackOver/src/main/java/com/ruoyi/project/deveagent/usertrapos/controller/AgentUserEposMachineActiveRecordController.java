package com.ruoyi.project.deveagent.usertrapos.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineActiveRecord;
import com.ruoyi.project.deveagent.usertrapos.service.impl.AgentUserEposMachineActiveRecordServiceImpl;
import com.ruoyi.project.deveagent.usertrapos.service.impl.AgentUserTraposMachineActiveRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deveagent/userEposMachineActiveRecord")
public class AgentUserEposMachineActiveRecordController extends BaseController {

    private String prefix = "deveagent/userEposMachineActiveRecord";

    @Autowired
    private AgentUserEposMachineActiveRecordServiceImpl agentUserEposRecordService;

    /**
     * 跳转用户MPOS信息列表页面
     * @return
     */
    @GetMapping()
    public String userMposDeductRecord()
    {
        return prefix + "/userMachineActiveRecord";
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
        List<Map<String, Object>> list = agentUserEposRecordService.getAgentUserTroposRecordList(params);
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
        List<AgentUserMposMachineActiveRecord> list = agentUserEposRecordService.selectAgentUserTroposRecordList(params);
        ExcelUtil<AgentUserMposMachineActiveRecord> util = new ExcelUtil<AgentUserMposMachineActiveRecord>(AgentUserMposMachineActiveRecord.class);
        return util.exportExcel(list, "EPOS激活记录数据");
    }

}
