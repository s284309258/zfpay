package com.ruoyi.project.develop.chart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.devemana.user.service.ManaUserInfoService;

/**
 * 定存钱包流水操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/chartUser")
public class ChartUserInfoController extends BaseController
{
	
	@Autowired
	private ManaUserInfoService manaUserInfoService;
	
	
	/**
	 * 用户每日注册量统计
	 * @return
	 */
	@ResponseBody
	@PostMapping("/userRegister")
	public List<Map<String, Object>> userRegister() {
		return manaUserInfoService.getManaUserRegisterList();
	}
	
}