package com.example.longecological.controller.machines;

import java.util.Map;

import com.example.longecological.annotations.RSAOnly;
import com.example.longecological.annotations.RSAToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.machines.MachinesManageService;

/**
 * 首页机具管理 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/machinesmanage")
public class MachinesManageController {

	@Autowired
	private MachinesManageService machinesManageService;
	
	/**
	 * 获取待分配列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosAllocationList")
	public R getTraditionalPosAllocationList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTraditionalPosAllocationList(map);
	}
	
	/**
	 * 获取待分配列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposAllocationList")
	public R getMposAllocationList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getMposAllocationList(map);
	}
	
	/**
	 * 获取待分配列表（流量卡）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTrafficCardAllocationList")
	public R getTrafficCardAllocationList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTrafficCardAllocationList(map);
	}
	
	/**
	 * 获取待召回列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosRecallList")
	public R getTraditionalPosRecallList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTraditionalPosRecallList(map);
	}
	
	/**
	 * 获取待召回列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposRecallList")
	public R getMposRecallList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getMposRecallList(map);
	}
	
	/**
	 * 获取待召回列表（流量卡）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTrafficCardRecallList")
	public R getTrafficCardRecallList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTrafficCardRecallList(map);
	}

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectUnbindTraditionalPos")
	public R selectUnbindTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectUnbindTraditionalPos(map);
	}

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectUnbindMpos")
	public R selectUnbindMpos(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectUnbindMpos(map);
	}

	/***
	 * 查询政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectPolicy3List")
	public R selectPolicy3List(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectPolicy3List(map);
	}

	/***
	 * 查询达标政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectPolicy3Record")
	public R selectPolicy3Record(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectPolicy3Record(map);
	}


	/***
	 * 选择奖励 add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/chooseAward")
	public R chooseAward(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.chooseAward(map);
	}
	
	/**
	 * 查询直推代理======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRefererAgency")
	public R getRefererAgency(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getRefererAgency(map);
	}
	
	/**
	 * 查询系统结算价配置参数(传统POS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosSysParamRateList")
	public R getTraditionalPosSysParamRateList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTraditionalPosSysParamRateList(map);
	}

	@ResponseBody
	@RequestMapping("/policy2OnOff")
	public R policy2OnOff(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.policy2OnOff(map);
	}
	
	/**
	 * 查询系统结算价配置参数(MPOS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposSysParamRateList")
	public R getMposSysParamRateList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getMposSysParamRateList(map);
	}
	
	/**
	 * 分配传统POS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allocationTraditionalPos")
	public R allocationTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.allocationTraditionalPos(map);
	}
	
	/**
	 * 召回传统POS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recallTraditionalPos")
	public R recallTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.recallTraditionalPos(map);
	}
	
	/**
	 * 解绑传统POS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unbindTraditionalPos")
	public R unbindTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.unbindTraditionalPos(map);
	}
	
	/**
	 * 分配MPOS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allocationMpos")
	public R allocationMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.allocationMpos(map);
	}
	
	/**
	 * 召回MPOS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recallMpos")
	public R recallMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.recallMpos(map);
	}
	
	/**
	 * 解绑MPOS机======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unbindMpos")
	public R unbindMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.unbindMpos(map);
	}
	
	/**
	 * 分配流量卡======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allocationTrafficCard")
	public R allocationTrafficCard(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.allocationTrafficCard(map);
	}
	
	/**
	 * 召回流量卡======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recallTrafficCard")
	public R recallTrafficCard(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.recallTrafficCard(map);
	}
	
	/**
	 * 查询POS机解绑记录(传统POS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosUnbindRecordList")
	public R getTraditionalPosUnbindRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getTraditionalPosUnbindRecordList(map);
	}
	
	/**
	 * 查询POS机解绑记录(MPOS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposUnbindRecordList")
	public R getMposUnbindRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getMposUnbindRecordList(map);
	}
	
	/**
	 * 查询传统POS机分配记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllocationTraditionalPosList")
	public R getAllocationTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getAllocationTraditionalPosList(map);
	}
	
	/**
	 * 查询传统POS机分配详情======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllocationTraditionalPosDetail")
	public R getAllocationTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getAllocationTraditionalPosDetail(map);
	}
	
	/**
	 * 修改传统POS机分配记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editAllocationTraditionalPos")
	public R editAllocationTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.editAllocationTraditionalPos(map);
	}
	
	/**
	 * 查询MPOS机分配记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllocationMposList")
	public R getAllocationMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getAllocationMposList(map);
	}
	
	/**
	 * 查询MPOS机分配详情======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllocationMposDetail")
	public R getAllocationMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getAllocationMposDetail(map);
	}
	
	/**
	 * 修改MPOS机分配记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editAllocationMpos")
	public R editAllocationMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.editAllocationMpos(map);
	}
	
	/**
	 * 查询流量卡分配记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllocationTrafficCardList")
	public R getAllocationTrafficCardList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getAllocationTrafficCardList(map);
	}
	
	/**
	 * 查询传统POS机召回记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRecallTraditionalPosList")
	public R getRecallTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getRecallTraditionalPosList(map);
	}

	/**
	 * 查询传统POS机召回记录======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRecallEposList")
	public R getRecallEposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getRecallEposList(map);
	}
	
	/**
	 * 处理传统POS机召回======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dealRecallTraditionalPos")
	public R dealRecallTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.dealRecallTraditionalPos(map);
	}
	
	/**
	 * 查询MPOS机召回记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRecallMposList")
	public R getRecallMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getRecallMposList(map);
	}
	
	/**
	 * 处理MPOS机召回======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dealRecallMpos")
	public R dealRecallMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.dealRecallMpos(map);
	}
	
	/**
	 * 查询流量卡召回记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRecallTrafficCardList")
	public R getRecallTrafficCardList(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.getRecallTrafficCardList(map);
	}
	
	/**
	 * 处理流量卡召回======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dealRecallTrafficCard")
	public R dealRecallTrafficCard(@SIGNToken @RequestBody Map<String, Object> map) {
		return machinesManageService.dealRecallTrafficCard(map);
	}

	/***
	 * 查询分配的批次 add byqh
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectPosBatchAllocate")
	public R selectPosBatchAllocate(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectPosBatchAllocate(map);
	}

	/***
	 * 批次修改分配的mpos add byqh
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editAllocationMPosBatch")
	public R editAllocationMPosBatch(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.editAllocationMPosBatch(map);
	}

	/***
	 * 批次修改分配的传统pos add byqh
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editAllocationTraditionalPosBatch")
	public R editAllocationTraditionalPosBatch(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.editAllocationTraditionalPosBatch(map);
	}

	/***
	 * 得到代理的机器结算价格byqh
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectPosSettlePriceBySN")
	public R selectPosSettlePriceBySN(@SIGNToken @RequestBody Map<String, Object> map){
		return machinesManageService.selectPosSettlePriceBySN(map);
	}
}
