package com.ruoyi.project.develop.task.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitParamConstants;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.BenefitRollbackMapper;
import com.ruoyi.project.develop.task.service.BenefitRollbackService;

@Service
public class BenefitRollbackServiceImpl implements BenefitRollbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BenefitRollbackServiceImpl.class);
	
	@Autowired
	private BenefitRollbackMapper benefitRollbackMapper;

	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;
	
	@Override
	public void rollBack1() {
		LOGGER.info("------------------收益数据回滚-START-------------");
		try{
			//查询待回滚数据
			List<Map<String, Object>> dealTraposDataList = benefitRollbackMapper.getDealTraposDataList();
			if(dealTraposDataList!=null && dealTraposDataList.size()>0){
				for(Map<String, Object> traposData : dealTraposDataList){
					SpringUtils.getAopProxy(this).dealTraposData(traposData);
				}
			}
		}catch(Exception e){
			LOGGER.error("BenefitRollbackServiceImpl -- rollBack1方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------收益数据回滚-END-------------");
	}
	
	@Transactional
	public void dealTraposData(Map<String, Object> traposData){
		int num = 0;
		try{
			//查询已获得的收益
			List<Map<String, Object>> traposBenefitList = benefitRollbackMapper.getTraposBenefitList(traposData);
			if(traposBenefitList!=null && traposBenefitList.size()>0){
				for(Map<String, Object> traposBenefit:traposBenefitList){
					//扣除用户收益
					Map<String, Object> edit_user = new HashMap<String, Object>();
					edit_user.put("money", new BigDecimal(StringUtil.getMapValue(traposBenefit, "benefit_money")));
					edit_user.put("user_id", StringUtil.getMapValue(traposBenefit, "user_id"));
					edit_user.put("state_type", StringUtil.getMapValue(traposBenefit, "state_type"));
					edit_user.put("op_order_id", StringUtil.getMapValue(traposBenefit, "order_id"));
					edit_user.put("today_type", TimeUtil.getDayString().equals(StringUtil.getMapValue(traposBenefit, "cre_date"))?"01":"02");
					edit_user.put("op_type", StringUtil.getMapValue(traposBenefit, "benefit_type"));
					edit_user.put("pos_type", BenefitParamConstants.pos_type_01);
					edit_user.put("sn", StringUtil.getMapValue(traposBenefit, "sn"));
					edit_user.put("up_date", StringUtil.getMapValue(traposData, "cre_date"));
					edit_user.put("up_time", StringUtil.getMapValue(traposData, "cre_time"));
					num = benefitRollbackMapper.updateUserBenefit(edit_user);
					if(num != 1){
						throw new Exception("用户资产不足");
					}
					
					//查询流水ID
					Map<String, Object> benefitRecordMap = benefitRollbackMapper.getBenefitRecordMap(edit_user);
					
					//更新流水
					edit_user.put("min", StringUtil.getMapValue(benefitRecordMap, "min"));
					edit_user.put("max", StringUtil.getMapValue(benefitRecordMap, "max"));
					num = benefitRollbackMapper.updateBenefitRecord(edit_user);
					if(num < 2){
						throw new Exception("流水更新异常");
					}
					
					//删除流水
					num = benefitRollbackMapper.delBenefitRecord(edit_user);
					if(num != 2){
						throw new Exception("删除流水异常");
					}
					
					//删除分润记录
					num = benefitRollbackMapper.delTraposBenefit(traposBenefit);
					if(num != 1){
						throw new Exception("删除分润记录异常");
					}
					
				}
				
			}
			
			//查询代理列表
			List<Map<String, Object>> agencyList = benefitRollbackMapper.getAgencyList(traposData);
			if(agencyList!=null && agencyList.size()>0){
				for(Map<String, Object> agency:agencyList){
					if("1".equals(StringUtil.getMapValue(agency, "state_status"))){
						Map<String, Object> param = new HashMap<>();
						param.put("performance", StringUtil.getMapValue(traposData, "trans_amount"));
						param.put("user_id", StringUtil.getMapValue(agency, "user_id"));
						param.put("sn", StringUtil.getMapValue(traposData, "sn"));
						param.put("up_date", StringUtil.getMapValue(traposData, "cre_date"));
						param.put("up_time", StringUtil.getMapValue(traposData, "cre_time"));
						String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(traposData, "sn"));
						if("epos".equals(pos_type)){
							param.put("pos_type","epos");
						}
						num = benefitRollbackMapper.updateMerchantTraposPerformance(param);
						if(num != 1){
							throw new Exception("商户汇总异常");
						}
						
						num = benefitRollbackMapper.updateUserMerchantPerformance(param);
						if(num != 1){
							throw new Exception("用户汇总异常");
						}
					}else{
						Map<String, Object> param = new HashMap<>();
						param.put("performance", StringUtil.getMapValue(traposData, "trans_amount"));
						param.put("user_id", StringUtil.getMapValue(agency, "user_id"));
						param.put("sn", StringUtil.getMapValue(traposData, "sn"));
						param.put("up_date", StringUtil.getMapValue(traposData, "cre_date"));
						param.put("up_time", StringUtil.getMapValue(traposData, "cre_time"));
						String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(traposData, "sn"));
						if("epos".equals(pos_type)){
							param.put("pos_type","epos");
						}
						num = benefitRollbackMapper.updateAgencyTraposPerformance(param);
						if(num != 1){
							throw new Exception("商户汇总异常");
						}
						
						num = benefitRollbackMapper.updateUserAgencyPerformance(param);
						if(num != 1){
							throw new Exception("用户汇总异常");
						}
					}
				}
			}
			
			//更新处理状态
			traposData.put("old_status", "99");
			traposData.put("new_status", "88");
			num = benefitRollbackMapper.updateTraposDataStatus(traposData);
			if(num != 1){
				throw new Exception("更新交易状态异常");
			}
			
		}catch(Exception e){
			LOGGER.error("BenefitRollbackServiceImpl -- dealTraposData方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
