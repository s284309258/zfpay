package com.ruoyi.project.deveagent.usertrapos.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEposUnbindService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindService;
import com.ruoyi.project.develop.common.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/deveagent/userEposUnbind")
public class AgentUserEposUnbindController extends BaseController
{
    private String prefix = "deveagent/userEposUnbind";
	
	@Autowired
	private AgentUserEposUnbindService agentUserEposUnbindService;
	
	
	/**
	 * 跳转用户传统POS解绑记录列表页面
	 * @return
	 */
	@GetMapping()
	public String userTraposUnbindRecordInfo()
	{
	    return prefix + "/userEposUnbind";
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
        List<Map<String, Object>> list = agentUserEposUnbindService.getAgentUserTraposUnbindList(params);
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
		return agentUserEposUnbindService.removePosBind(params);
	}
}
