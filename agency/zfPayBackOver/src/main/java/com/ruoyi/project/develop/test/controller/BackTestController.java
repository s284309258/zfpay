package com.ruoyi.project.develop.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.service.ActivitySettlementService;
import com.ruoyi.project.develop.task.service.BenefitRollbackService;
import com.ruoyi.project.develop.task.service.TransactionDataProcessingService;


/**
 * 用户信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/backTest")
public class BackTestController extends BaseController
{
    private String prefix = "develop/backTest";
    
    @Autowired
    private ActivitySettlementService activitySettlementService;
	
    @Autowired
    private TransactionDataProcessingService transactionDataProcessingService;
    
    @Autowired
    private BenefitRollbackService benefitRollbackService;
    
	
    /**
	 * 选择系统日期
	 * @return
	 */
    /*@GetMapping("/toCeshi1")
    public String toCeshi1()
    {
		return prefix + "/ceshi1";
    }*/
	
	
	/**
	 * 修改系统日期
	 * @param params
	 * @return
	 */
	/*@PostMapping("/ceshi1")
    @ResponseBody
    public R ceshi1(@RequestParam Map<String, Object> params)
    {
		try {
			//通过exec 来运行Linux shell脚本:在这个demo中 setDate.sh 是和 LinuxTimeSetter 在同一个文件夹中 
			String[] command = new String[]{"sudo","/data/setdate.sh",StringUtil.getMapValue(params, "date")}; 
			Process proc = Runtime.getRuntime().exec(command); 
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream())); 
			System.out.println("开始执行:"+StringUtil.getMapValue(params, "date"));
			String text = null; 
			//输出操作结果 
			while ((text = br.readLine()) != null) { 
				System.out.println(text); 
			}
		}catch(Exception e){
			e.printStackTrace();
			return R.error("操作失败");
		}
        return R.ok("操作成功");
    }*/
	
	
	/**
	 * 高返现活动结算-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi1")
	@ResponseBody
	public R zfCeshi1(@RequestParam Map<String, Object> params)
	{
		activitySettlementService.highCashbackActivitySettlement();
		return R.ok("执行成功");
	}*/
	
	
	/**
	 * 交易量活动结算-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi2")
	@ResponseBody
	public R zfCeshi2(@RequestParam Map<String, Object> params)
	{
		activitySettlementService.volumeActivitySettlement();
		return R.ok("执行成功");
	}*/
	
	
	/**
	 * 处理交易数据-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi3")
	@ResponseBody
	public R zfCeshi3(@RequestParam Map<String, Object> params)
	{
		transactionDataProcessingService.processingTrades();
		return R.ok("执行成功");
	}*/
	
	
	/**
	 * 处理机器返现-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi4")
	@ResponseBody
	public R zfCeshi4(@RequestParam Map<String, Object> params)
	{
		transactionDataProcessingService.processingMachineBack();
		return R.ok("执行成功");
	}*/
	
	/**
	 * 达标任务处理MPOS-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi5")
	@ResponseBody
	public R zfCeshi5(@RequestParam Map<String, Object> params)
	{
		activitySettlementService.mposAssessTaskSettlement(StringUtil.getMapValue(params, "assess_id"));
		return R.ok("执行成功");
	}*/
	
	/**
	 * 达标任务处理传统POS-测试
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi6")
	@ResponseBody
	public R zfCeshi6(@RequestParam Map<String, Object> params)
	{
		activitySettlementService.traposAssessTaskSettlement(StringUtil.getMapValue(params, "assess_id"));
		return R.ok("执行成功");
	}*/
	
    
	/**
	 * 传统POS交易数据回滚
	 * @param params
	 * @return
	 */
	@PostMapping("/traPosTransCallBack")
	@ResponseBody
	public R traPosTransCallBack(@RequestParam Map<String, Object> params)
	{
		benefitRollbackService.rollBack1();
		return R.ok("执行成功");
	}
	
	/**
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi8")
	@ResponseBody
	public R zfCeshi8(@RequestParam Map<String, Object> params)
	{
		return R.ok("执行成功");
	}*/
	
	/**
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi9")
	@ResponseBody
	public R zfCeshi9(@RequestParam Map<String, Object> params)
	{
		return R.ok("执行成功");
	}*/
	
	/**
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi10")
	@ResponseBody
	public R zfCeshi10(@RequestParam Map<String, Object> params)
	{
		return R.ok("执行成功");
	}*/
	
	/**
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi11")
	@ResponseBody
	public R zfCeshi11(@RequestParam Map<String, Object> params)
	{
		return R.ok("执行成功");
	}*/
	
	/**
	 * @param params
	 * @return
	 */
	/*@PostMapping("/zfCeshi12")
	@ResponseBody
	public R zfCeshi12(@RequestParam Map<String, Object> params)
	{
		return R.ok("执行成功");
	}*/
}
