package com.ruoyi.project.deveagent.syspospolicy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
import com.ruoyi.project.develop.common.domain.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 系统pos政策管理 add byqh 201912
 */
@Controller
@RequestMapping("/deveagent/agentPosPolicy")
public class AgentPosPolicyController {
    private String prefix = "deveagent/sysPosPolicy";

    @Autowired
    private SysPosPolicyServiceImpl sysPosPolicyService;

    /**
     * 跳转系统POS机政策信息列表页面
     * @return
     */
    @GetMapping()
    public String sysPosPolicy(ModelMap mmap)
    {
        List<Map<String,Object>> list = sysPosPolicyService.selectSysPosPolicyList(new HashMap<>());
        mmap.addAttribute("policyList",list);
        return prefix + "/agentPosPolicy";
    }

    /**
     * 查询系统MPOS信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
        //此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询用户列表
        List<Map<String,Object>> list = sysPosPolicyService.selectAgentPosPolicyList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 批量删除中付账号
     * @param params
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestParam Map<String, Object> params)
    {
        return sysPosPolicyService.removePolicySNInfo(params);
    }



    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        //获得封装分页对象
        PageDomain pageDomain = TableSupport.buildPageRequest();
        pageDomain.setOrderByColumn("t.id");
        //当前记录起始索引
        Integer pageNum = pageDomain.getPageNum();
        //每页显示记录数
        Integer pageSize = pageDomain.getPageSize();
        //当前记录起始索引和记录条数均非空的情况下
        if (StringUtil.isNotNull(pageNum) && StringUtil.isNotNull(pageSize))
        {
            //得到排序对象，注意防止sql注入
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            //分页并排序
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        //表格分页数据对象
        TableDataInfo rspData = new TableDataInfo();
        //消息状态码
        rspData.setCode(0);
        //列表数据
        rspData.setRows(list);
        //总记录数
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
