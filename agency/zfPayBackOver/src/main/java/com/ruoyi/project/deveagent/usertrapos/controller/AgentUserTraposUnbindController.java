package com.ruoyi.project.deveagent.usertrapos.controller;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposUnbindRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindRecordInfoService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindService;
import com.ruoyi.project.develop.common.domain.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 代理======用户传统POS解绑记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userTraposUnbind")
public class AgentUserTraposUnbindController extends BaseController
{
    private String prefix = "deveagent/userTraposUnbind";
	
	@Autowired
	private AgentUserTraposUnbindService agentUserTraposUnbindService;
	
	
	/**
	 * 跳转用户传统POS解绑记录列表页面
	 * @return
	 */
	@GetMapping()
	public String userTraposUnbindRecordInfo()
	{
	    return prefix + "/userTraposUnbind";
	}
	
	
	/**
	 * 查询用户传统POS解绑记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserTraposUnbindService.getAgentUserTraposUnbindList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}

	/**
	 * 批量解绑
	 * @param params
	 * @return
	 */
	@PostMapping("/remove")
	@ResponseBody
	public R remove(@RequestParam Map<String, Object> params)
	{
		return agentUserTraposUnbindService.removePosBind(params);
	}
}
