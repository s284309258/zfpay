package com.ruoyi.project.deveagent.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.deveagent.user.domain.AgentUserCard;
import com.ruoyi.project.deveagent.user.service.AgentUserCardService;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户结算卡操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/deveagent/userCard")
public class AgentUserCardController extends BaseController
{
    private String prefix = "deveagent/userCard";
	
	@Autowired
	private AgentUserCardService agentUserCardService;
	
	
	/**
	 * 跳转用户结算卡列表页面
	 * @return
	 */
	@RequiresPermissions("deveagent:userCard:view")
	@GetMapping()
	public String userCard()
	{
	    return prefix + "/userCard";
	}
	
	
	/**
	 * 查询用户结算卡列表
	 */
	@RequiresPermissions("deveagent:userCard:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = agentUserCardService.getAgentUserCardList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@Log(title = "用户结算卡管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("deveagent:userCard:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<AgentUserCard> list = agentUserCardService.selectAgentUserCardList(params);
        ExcelUtil<AgentUserCard> util = new ExcelUtil<AgentUserCard>(AgentUserCard.class);
        return util.exportExcel(list, "用户结算卡数据");
    }
	
	
	/**
	 * 根据用户id查询用户结算卡详情
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("deveagent:userCard:detail")
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("userCard", agentUserCardService.getAgentUserCardById(id));
        return prefix + "/detail";
    }
	
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("deveagent:userCard:detail")
	@PostMapping("/getAgentUserCardById")
	@ResponseBody
	public AgentUserCard getAgentUserCardById(String id)
	{
		return agentUserCardService.getAgentUserCardById(id);
	}
	
	
	/**
	 * 单个审核银行卡
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysCardAudit/{id}")
	@RequiresPermissions("deveagent:userCard:sysCardAudit")
    public String sysCardAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentSysAuditCardInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysCardAudit";
    	}
    }
	
	
	/**
	 * 批量审核银行卡
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/batchSysCardAudit/{id}")
	@RequiresPermissions("deveagent:userCard:sysCardAudit")
    public String batchSysCardAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AgentBatchSysAuditCardInfo;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/batchSysCardAudit";
    	}
    }
		
		
	/**
	 * 批量审核银行卡
	 * @param params
	 * @return
	 */
	@RequiresPermissions("deveagent:userCard:sysCardAudit")
    @Log(title = "用户结算卡管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysCardAudit")
    @ResponseBody
    public R sysCardAudit(@RequestParam Map<String, Object> params)
    {
        return agentUserCardService.batchSysAuditAgentUserCard(params);
    }
	
}
