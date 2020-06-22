package com.example.longecological.service.machines;

import java.util.Map;

import com.example.longecological.entity.R;

public interface MachinesManageService {
	
	/**
	 * 获取待分配列表（传统POS）
	 * @param map
	 * @return
	 */
	R getTraditionalPosAllocationList(Map<String, Object> map);
	
	/**
	 * 获取待分配列表（MPOS）
	 * @param map
	 * @return
	 */
	R getMposAllocationList(Map<String, Object> map);
	
	/**
	 * 获取待分配列表（流量卡）
	 * @param map
	 * @return
	 */
	R getTrafficCardAllocationList(Map<String, Object> map);
	
	/**
	 * 获取待召回列表（传统POS）
	 * @param map
	 * @return
	 */
	R getTraditionalPosRecallList(Map<String, Object> map);
	
	/**
	 * 获取待召回列表（MPOS）
	 * @param map
	 * @return
	 */
	R getMposRecallList(Map<String, Object> map);
	
	/**
	 * 获取待召回列表（流量卡）
	 * @param map
	 * @return
	 */
	R getTrafficCardRecallList(Map<String, Object> map);

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	R selectUnbindTraditionalPos(Map<String, Object> map);

	/***
	 * 查询待解绑pos add byqh 201912
	 * @param map
	 * @return
	 */
	R selectUnbindMpos(Map<String, Object> map);

	/***
	 * 查询政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	R selectPolicy3List(Map<String,Object> map);

	/***
	 * 查询达标政策3列表 add byqh 201912
	 * @param map
	 * @return
	 */
	R selectPolicy3Record(Map<String,Object> map);

	/***
	 * 选择奖励
	 * @param map
	 * @return
	 */
	R chooseAward(Map<String,Object> map);
	
	/**
	 * 查询系统费率参数(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosSysParamRateList(Map<String, Object> map);


	R policy2OnOff(Map<String, Object> map);
	
	/**
	 * 查询系统费率参数(MPOS)
	 * @param map
	 * @return
	 */
	R getMposSysParamRateList(Map<String, Object> map);
	
	/**
	 * 分配传统POS机
	 * @param map
	 * @return
	 */
	R allocationTraditionalPos(Map<String, Object> map);
	
	/**
	 * 召回传统POS机
	 * @param map
	 * @return
	 */
	R recallTraditionalPos(Map<String, Object> map);
	
	/**
	 * 解绑传统POS机
	 * @param map
	 * @return
	 */
	R unbindTraditionalPos(Map<String, Object> map);
	
	/**
	 * 分配MPOS机
	 * @param map
	 * @return
	 */
	R allocationMpos(Map<String, Object> map);
	
	/**
	 * 召回MPOS机
	 * @param map
	 * @return
	 */
	R recallMpos(Map<String, Object> map);
	
	/**
	 * 解绑MPOS机
	 * @param map
	 * @return
	 */
	R unbindMpos(Map<String, Object> map);
	
	/**
	 * 分配流量卡
	 * @param map
	 * @return
	 */
	R allocationTrafficCard(Map<String, Object> map);
	
	/**
	 * 召回流量卡
	 * @param map
	 * @return
	 */
	R recallTrafficCard(Map<String, Object> map);
	
	/**
	 * 查询直推代理人
	 * @param map
	 * @return
	 */
	R getRefererAgency(Map<String, Object> map);
	
	/**
	 * 查询解绑记录（传统POS）
	 * @param map
	 * @return
	 */
	R getTraditionalPosUnbindRecordList(Map<String, Object> map);
	
	/**
	 * 查询解绑记录（传统POS）
	 * @param map
	 * @return
	 */
	R getMposUnbindRecordList(Map<String, Object> map);
	
	/**
	 * 查询传统POS机分配记录
	 * @param map
	 * @return
	 */
	R getAllocationTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 查询传统POS机分配详情
	 * @param map
	 * @return
	 */
	R getAllocationTraditionalPosDetail(Map<String, Object> map);
	
	/**
	 * 修改传统POS机分配记录
	 * @param map
	 * @return
	 */
	R editAllocationTraditionalPos(Map<String, Object> map);
	
	/**
	 * 查询MPOS机分配记录
	 * @param map
	 * @return
	 */
	R getAllocationMposList(Map<String, Object> map);
	
	/**
	 * 查询MPOS机分配详情
	 * @param map
	 * @return
	 */
	R getAllocationMposDetail(Map<String, Object> map);
	
	/**
	 * 修改MPOS机分配记录
	 * @param map
	 * @return
	 */
	R editAllocationMpos(Map<String, Object> map);
	
	/**
	 * 查询流量卡分配记录
	 * @param map
	 * @return
	 */
	R getAllocationTrafficCardList(Map<String, Object> map);
	
	/**
	 * 查询召回记录（传统POS）
	 * @param map
	 * @return
	 */
	R getRecallTraditionalPosList(Map<String, Object> map);

	/**
	 * 查询召回记录（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getRecallEposList(Map<String, Object> map);
	
	/**
	 * 处理召回（传统POS）
	 * @param map
	 * @return
	 */
	R dealRecallTraditionalPos(Map<String, Object> map);
	
	/**
	 * 查询召回记录（MPOS）
	 * @param map
	 * @return
	 */
	R getRecallMposList(Map<String, Object> map);
	
	/**
	 * 处理召回（MPOS）
	 * @param map
	 * @return
	 */
	R dealRecallMpos(Map<String, Object> map);
	
	/**
	 * 查询召回记录（流量卡）
	 * @param map
	 * @return
	 */
	R getRecallTrafficCardList(Map<String, Object> map);
	
	/**
	 * 处理召回（流量卡）
	 * @param map
	 * @return
	 */
	R dealRecallTrafficCard(Map<String, Object> map);

	/***
	 * 查询分配的批次 add byqh
	 * @param map
	 * @return
	 */
	R selectPosBatchAllocate(Map<String,Object> map);

	/***
	 * 根据批次修改MPOS信息 add byqh
	 * @param map
	 * @return
	 */
	R editAllocationMPosBatch(Map<String,Object> map);

	/***
	 * 根据批次修改传统POS信息 add byqh
	 * @param map
	 * @return
	 */
	R editAllocationTraditionalPosBatch(Map<String, Object> map);

	/***
	 * 得到代理的机器结算价格byqh
	 * @param map
	 * @return
	 */
	R selectPosSettlePriceBySN(Map<String, Object> map);

}
