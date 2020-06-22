package com.ruoyi.project.deveagent.syspospolicy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.deveagent.syspospolicy.domain.SysPosPolicy;
import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.test.IOSPushy;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/deveagent/sysPosPolicy")
public class SysPosPolicyController {
    private String prefix = "deveagent/sysPosPolicy";

    @Autowired
    private SysPosPolicyServiceImpl sysPosPolicyService;

    /**
     * 跳转系统POS机政策信息列表页面
     * @return
     */
    @GetMapping()
    public String sysPosPolicy()
    {
//        try {
//            IOSPushy.PushyMessage("分润收入到账","传统POS刷卡入账1.5元","a3bb9dcf47d465541920a3abd9f98cf8ce852515222f34c0c878e1e215543910");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return prefix + "/sysPosPolicyInfo";
    }

    /**
     * 跳转到新增页面
     * @param mmap
     * @return
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap)
    {
        return prefix + "/add"+id;
    }


    /**
     * 跳转修改系统MPOS页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",id);
        Map<String,Object> result = sysPosPolicyService.selectSysPosPolicyInfo(hashMap);
        String policy_type = result.get("policy_type").toString();
        mmap.addAttribute("policy",result);
        return prefix + "/edit"+policy_type;
    }

    @PostMapping("/edit")
    @ResponseBody
    public R edit(@RequestParam Map<String, Object> params){
        return sysPosPolicyService.editPolicyInfo(params);
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
        List<Map<String,Object>> list = sysPosPolicyService.selectSysPosPolicyList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        //获得封装分页对象
        PageDomain pageDomain = TableSupport.buildPageRequest();
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

    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
        return sysPosPolicyService.addPolicyInfo(params);
    }


    @PostMapping("/del")
    @ResponseBody
    public R remove(@RequestParam Map<String,Object> params){
        return sysPosPolicyService.removePolicyInfo(params);
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
