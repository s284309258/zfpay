package com.ruoyi.project.develop.task.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.develop.task.mapper.ActivitySettlementMapper;
import com.ruoyi.project.devemana.param.service.ManaSysParamService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.ZhongFuInterfaceCodeConstant;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.mapper.ZhongFuDataAcquireMapper;
import com.ruoyi.project.develop.task.service.ZhongFuDataAcquireService;
import com.ruoyi.project.develop.task.service.ZhongFuInterfaceService;

@Service
public class ZhongFuDataAcquireServiceImpl implements ZhongFuDataAcquireService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhongFuDataAcquireServiceImpl.class);

	@Autowired
	private ZhongFuDataAcquireMapper zhongFuDataAcquireMapper;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private ZhongFuInterfaceService zhongFuInterfaceService;

	@Autowired
	private ManaSysParamService manaSysParamService;

	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;

	@Autowired
	private ActivitySettlementMapper activitySettlementMapper;

	/**
	 * 获取中付交易数据，插入到待处理表中
	 */
	@Override
	public void getDataTransactionRecordDeal() {
		LOGGER.info("------------------执行中付交易数据获取接口-START-------------");
		try{
			List<Map<String, Object>> account_list = zhongFuDataAcquireMapper.getManagerAccountList();
			if(account_list != null && account_list.size() > 0){
				for(Map<String, Object> account : account_list){
					try{
						//当前时间
						String endTime = TimeUtil.getDayFormat4();
						//开始时间
						String startTime = StringUtil.getMapValue(account, "tran_time");
						//前推10分钟
						String beforeTenMinuteStartTime = TimeUtil.getBeforeTenMinute(startTime);
						//获取开始时间的跨天截断时间点
						String cutStartTime = TimeUtil.getBankCutDate(startTime);
						//获取结束时间的跨天截断时间点
						String cutEndTime = TimeUtil.getBankCutDate(endTime);
						//获取昨日的跨天截断时间点
						String cutYesterdayTime = TimeUtil.getYesterdayBankCutDate(endTime);
						LOGGER.info("执行时间-endTime:"+endTime+",startTime:"+startTime+",cutStartTime:"+cutStartTime+",cutEndTime:"+cutEndTime+",cutYesterdayTime:"+cutYesterdayTime);
						//判断是否两个开始日期跨天
						if(!(beforeTenMinuteStartTime.compareTo(cutStartTime)<0 && startTime.compareTo(cutStartTime)>=0)){
							startTime = beforeTenMinuteStartTime;
						}
						//判断获取数据的时间段
						if(cutStartTime.compareTo(cutEndTime)==0 && startTime.compareTo(cutStartTime)<0 && endTime.compareTo(cutStartTime)>0){
							endTime = cutStartTime;
						}else if(cutStartTime.compareTo(cutEndTime)!=0 && endTime.compareTo(cutEndTime)<0 && startTime.compareTo(cutYesterdayTime)<0){
							endTime = cutYesterdayTime;
						}else if(cutStartTime.compareTo(cutEndTime)!=0 && endTime.compareTo(cutEndTime)>=0){
							endTime = cutEndTime;
						}
						LOGGER.info("更新时间-endTime:"+endTime+",startTime:"+startTime+",cutStartTime:"+cutStartTime+",cutEndTime:"+cutEndTime+",cutYesterdayTime:"+cutYesterdayTime);
						LOGGER.info("执行账号："+account.toString());
						//处理中付交易接口
						boolean status = this.getDataTransactinRecord(account, startTime, endTime);
//						if(status){
						//更细处理时间
						account.put("new_tran_time", endTime);
						zhongFuDataAcquireMapper.updateSysUserAccountTranTime(account);
//						}
					}catch(Exception e){
						LOGGER.error("账号"+ account.toString() +"处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataTransactionRecordDeal方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------执行中付交易数据获取接口-END--------------");
	}

	/**
	 * 获取中付前一天交易数据
	 */
	@Override
	public void getDataTransactionRecordYesterday() {
		LOGGER.info("------------------执行中付前天交易数据接口-START-------------");
		try{
			List<Map<String, Object>> account_list = zhongFuDataAcquireMapper.getManagerAccountList();
			if(account_list != null && account_list.size() > 0){
				String endTime = TimeUtil.getDayFormat4();
				String previous_day = manaSysParamService.getParamByCode("transData7002");
				String startTime = TimeUtil.getBeforeTwoDayBankCutDate(endTime,Integer.parseInt(previous_day));
				for(Map<String, Object> account : account_list){
					try{
						//当前时间
//						String time = TimeUtil.getDayFormat4();
						//处理中付交易接口
//						this.getDataTransactinRecord(account, TimeUtil.getBeforeTwoDayBankCutDate(time), TimeUtil.getYesterdayBankCutDate(time));

						//查询前两天至当前时间数据
						this.getDataTransactinRecord(account, startTime, endTime,0);
					}catch(Exception e){
						LOGGER.error("账号"+ account.toString() +"处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataTransactionRecordYesterday方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------执行中付前天交易数据接口-END--------------");
	}


	/***
	 * 根据查询时间获取交易数据add byqh20200103
	 * @param startTime
	 * @param endTime
	 */
	@Override
	public void getDataTransactionRecordHHMMSS(String startTime,String endTime) {
		LOGGER.info("------------------执行中付前天交易数据接口-START-------------");
		try{
			List<Map<String, Object>> account_list = zhongFuDataAcquireMapper.getManagerAccountList();
			if(account_list != null && account_list.size() > 0){
				for(Map<String, Object> account : account_list){
					try{
						//查询指定时间交易数据
						this.getDataTransactinRecord(account, startTime, endTime,0);
					}catch(Exception e){
						LOGGER.error("账号"+ account.toString() +"处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataTransactionRecordYesterday方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------执行中付前天交易数据接口-END--------------");
	}

	/**
	 * 处理单个账号交易数据获取
	 * @param account
	 * @param startTime
	 * @param endTime
	 */
	@SuppressWarnings("unchecked")
	private boolean getDataTransactinRecord(Map<String, Object> account, String startTime, String endTime,int xx){
		try{
			//循环查询交易类型
			for(int transType=1; transType<=5; transType++){
				//初始页数
				int transposPageNum = 1;
				//获取数据
				while(true){
					//通过7002接口取交易数据
					R transposData = zhongFuInterfaceService.requestType7002(StringUtil.getMapValue(account, "app_id"), String.valueOf(transType), ZhongFuInterfaceCodeConstant.device_type_1, startTime, endTime, String.valueOf(transposPageNum), StringUtil.getMapValue(account, "app_key"));
					//获取异常
					if(!R.Type.SUCCESS.value.equals(transposData.get("code").toString())) {
						return false;
					}
					//获取内容
					JSONObject data = (JSONObject) transposData.get("data");
					//内容为空，跳出循环
					if(data == null || data.get("details") == null){
						break;
					}else{
						//获取交易数组
						JSONArray list = data.getJSONArray("details");
						if(list==null || list.size()<1) break;
						for(Object object : list){
							Map<String, Object> bean = (Map<String, Object>)object;
							LOGGER.info("处理交易数据："+bean.toString());
							//判断缓存是否有当前数据
							if(redisUtils.exists(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"))){
								continue;
							}else{
								//存入相关参数
								bean.put("account_id", StringUtil.getMapValue(account, "id"));
								bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
								bean.put("cre_date", TimeUtil.getDayString());
								bean.put("cre_time", TimeUtil.getTimeString());
								//判断系统是否导入该机型
								///////增加被扫入库byqh begin 201912
								if("3".equals(bean.get("transType")) || "4".equals(bean.get("transType")) || "5".equals(bean.get("transType"))){
									String sn = zhongFuDataAcquireMapper.getTranscationPosSnByMerID(String.valueOf(bean.get("merId")));
									LOGGER.info("transType4-3 sn："+sn);
									bean.put("sN",sn);
								}
								///////增加被扫入库byqh end 201912
								if(zhongFuDataAcquireMapper.checkTransactionPosExists(bean)>0){
									try{
										//将该记录保存到待处理表中
										int num = zhongFuDataAcquireMapper.insertDataTraposTransactionRecordDeal(bean);
										if(num != 1){
											throw new Exception();
										}else{
											redisUtils.set(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"), bean, SysParamConstant.two_day_seconds);
										}
									}catch(Exception e){
										LOGGER.error("当前交易数据保存异常："+bean.toString()+ExceptionUtil.getExceptionAllinformation(e));
									}
								}
							}
						}
					}
					//页数自增
					transposPageNum++;
				}

				//初始页数
				int mposPageNum = 1;
				//获取数据
				while(true){
					//通过7002接口取交易数据
					R mposData = zhongFuInterfaceService.requestType7002(StringUtil.getMapValue(account, "app_id"), String.valueOf(transType), ZhongFuInterfaceCodeConstant.device_type_0, startTime, endTime, String.valueOf(mposPageNum), StringUtil.getMapValue(account, "app_key"));
					//获取异常
					if(!R.Type.SUCCESS.value.equals(mposData.get("code").toString())) {
						return false;
					}
					//获取内容
					JSONObject data = (JSONObject) mposData.get("data");
					//内容为空，跳出循环
					if(data == null || data.get("details") == null){
						break;
					}else{
						//获取交易数组
						JSONArray list = data.getJSONArray("details");
						if(list==null || list.size()<1) break;
						for(Object object : list){
							Map<String, Object> bean = (Map<String, Object>)object;
							//只取出款状态未已出款的交易数据 add if("1".equals(bean.get("settStatus"))) begin byqh
//							if("1".equals(bean.get("settStatus"))){
							//判断缓存是否有当前数据
							if(redisUtils.exists(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"))){
								continue;
							}else{
								//存入相关参数
								bean.put("account_id", StringUtil.getMapValue(account, "id"));
								bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
								bean.put("cre_date", TimeUtil.getDayString());
								bean.put("cre_time", TimeUtil.getTimeString());
								///////增加被扫入库byqh begin 201912
								if("5".equals(bean.get("transType"))){
									bean.put("sN",bean.get("merName"));
								}
								///////增加被扫入库byqh end 201912
								//判断系统是否导入该机型
								if(zhongFuDataAcquireMapper.checkMposExists(bean)>0){
									try{
										//将该记录保存到待处理表中
										int num = zhongFuDataAcquireMapper.insertDataMposTransactionRecordDeal(bean);
										if(num != 1){
											throw new Exception();
										}else{
											redisUtils.set(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"), bean, SysParamConstant.two_day_seconds);
										}
									}catch(Exception e){
										LOGGER.error("当前交易数据保存异常："+bean.toString()+ ExceptionUtil.getExceptionAllinformation(e));
									}
								}
							}
//							}
							//只取出款状态未已出款的交易数据end byqh
						}
					}
					//页数自增
					mposPageNum++;
				}

			}
			return true;
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataTransactinRecord方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return false;
		}
	}

	/**
	 * 处理单个账号交易数据获取
	 * @param account
	 * @param startTime
	 * @param endTime
	 */
	@SuppressWarnings("unchecked")
	private boolean getDataTransactinRecord(Map<String, Object> account, String startTime, String endTime){
		try{
			//循环查询交易类型
			for(int transType=1; transType<=5; transType++){
				//初始页数
				int transposPageNum = 1;
				//获取数据
				while(true){
					//通过7002接口取交易数据
					R transposData = zhongFuInterfaceService.requestType7002(StringUtil.getMapValue(account, "app_id"), String.valueOf(transType), ZhongFuInterfaceCodeConstant.device_type_1, startTime, endTime, String.valueOf(transposPageNum), StringUtil.getMapValue(account, "app_key"));
					//获取异常
					if(!R.Type.SUCCESS.value.equals(transposData.get("code").toString())) {
						return false;
					}
					//获取内容
					JSONObject data = (JSONObject) transposData.get("data");
					//内容为空，跳出循环
					if(data == null || data.get("details") == null){
						break;
					}else{
						//获取交易数组
						JSONArray list = data.getJSONArray("details");
						if(list==null || list.size()<1) break;
						for(Object object : list){
							Map<String, Object> bean = (Map<String, Object>)object;
							LOGGER.info("处理交易数据："+bean.toString());
							//判断缓存是否有当前数据
							if(redisUtils.exists(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"))){
								continue;
							}else{
								//存入相关参数
								bean.put("account_id", StringUtil.getMapValue(account, "id"));
								bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
								bean.put("cre_date", TimeUtil.getDayString());
								bean.put("cre_time", TimeUtil.getTimeString());
								///////增加被扫入库byqh begin 201912
								if("3".equals(bean.get("transType")) || "4".equals(bean.get("transType")) || "5".equals(bean.get("transType"))){
									String sn = zhongFuDataAcquireMapper.getTranscationPosSnByMerID(String.valueOf(bean.get("merId")));
									LOGGER.info("transType4-3 sn："+sn);
									bean.put("sN",sn);
								}
								///////增加被扫入库byqh end 201912
								//判断系统是否导入该机型
								if(zhongFuDataAcquireMapper.checkTransactionPosExists(bean)>0){
									try{
										//将该记录保存到待处理表中
										int num = zhongFuDataAcquireMapper.insertDataTraposTransactionRecordDeal(bean);
										if(num != 1){
											throw new Exception();
										}else{
											redisUtils.set(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"), bean, SysParamConstant.two_day_seconds);
										}
									}catch(Exception e){
										LOGGER.error("当前交易数据保存异常："+bean.toString()+ExceptionUtil.getExceptionAllinformation(e));
									}
								}
							}
						}
					}
					//页数自增
					transposPageNum++;
				}

				//初始页数
				int mposPageNum = 1;
				//获取数据
				while(true){
					//通过7002接口取交易数据
					R mposData = zhongFuInterfaceService.requestType7002(StringUtil.getMapValue(account, "app_id"), String.valueOf(transType), ZhongFuInterfaceCodeConstant.device_type_0, startTime, endTime, String.valueOf(mposPageNum), StringUtil.getMapValue(account, "app_key"));
					//获取异常
					if(!R.Type.SUCCESS.value.equals(mposData.get("code").toString())) {
						return false;
					}
					//获取内容
					JSONObject data = (JSONObject) mposData.get("data");
					//内容为空，跳出循环
					if(data == null || data.get("details") == null){
						break;
					}else{
						//获取交易数组
						JSONArray list = data.getJSONArray("details");
						if(list==null || list.size()<1) break;
						for(Object object : list){
							Map<String, Object> bean = (Map<String, Object>)object;
							//只取出款状态未已出款的交易数据 add if("1".equals(bean.get("settStatus"))) begin byqh
							if("1".equals(bean.get("settStatus"))){
								//判断缓存是否有当前数据
								if(redisUtils.exists(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"))){
									continue;
								}else{
									//存入相关参数
									bean.put("account_id", StringUtil.getMapValue(account, "id"));
									bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
									bean.put("cre_date", TimeUtil.getDayString());
									bean.put("cre_time", TimeUtil.getTimeString());
									///////增加被扫入库byqh begin 201912
									if("5".equals(bean.get("transType"))){
										bean.put("sN",bean.get("merName"));
									}
									///////增加被扫入库byqh end 201912
									//判断系统是否导入该机型
									if(zhongFuDataAcquireMapper.checkMposExists(bean)>0){
										try{
											//将该记录保存到待处理表中
											int num = zhongFuDataAcquireMapper.insertDataMposTransactionRecordDeal(bean);
											if(num != 1){
												throw new Exception();
											}else{
												redisUtils.set(RedisNameConstants.zfpay_transaction_record+StringUtil.getMapValue(bean, "tranRefCode"), bean, SysParamConstant.two_day_seconds);
											}
										}catch(Exception e){
											LOGGER.error("当前交易数据保存异常："+bean.toString()+ ExceptionUtil.getExceptionAllinformation(e));
										}
									}
								}
							}
							//只取出款状态未已出款的交易数据end byqh
						}
					}
					//页数自增
					mposPageNum++;
				}

			}
			return true;
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataTransactinRecord方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return false;
		}
	}

	/**
	 * 获取中付返现数据，插入到待处理表中
	 */
	@Override
	public void getDataMachineCashbackRecordDeal(String query_date) {
		LOGGER.info("------------------执行中付返现数据获取接口-START-------------");
		try{
			//处理获取时间
			if(query_date == null){
				query_date = TimeUtil.getYesterdayFormat3();
			}
			//获取传统POS政策信息列表
			List<Map<String, Object>> traposPolicyList = zhongFuDataAcquireMapper.getTraposPolicyList();
			if(traposPolicyList!=null && traposPolicyList.size()>0){
				for(Map<String, Object> traposPolicy : traposPolicyList){
					getDataMachineCashbackRecord(traposPolicy, ZhongFuInterfaceCodeConstant.machine_type_02, query_date);
				}
			}
			//获取MPOS政策信息列表
			List<Map<String, Object>> mposPolicyList =zhongFuDataAcquireMapper.getMposPolicyList();
			if(mposPolicyList!=null && mposPolicyList.size()>0){
				for(Map<String, Object> mposPolicy : mposPolicyList){
					getDataMachineCashbackRecord(mposPolicy, ZhongFuInterfaceCodeConstant.machine_type_01, query_date);
				}
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataMachineCashbackRecordDeal方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------执行中付返现数据获取接口-END--------------");
	}

	/**
	 * 获取单个政策的返现记录
	 */
	private void getDataMachineCashbackRecord(Map<String, Object> policy, String machine_type, String query_date){
		try{
			//初始页数
			int pageNum = 1;
			//获取数据
			while(true){
				//通过7002接口取交易数据
				R policyData = zhongFuInterfaceService.requestType7005(StringUtil.getMapValue(policy, "app_id"), query_date, null, StringUtil.getMapValue(policy, "policy_code"), machine_type, String.valueOf(pageNum), StringUtil.getMapValue(policy, "app_key"));
				//获取异常
				if(!R.Type.SUCCESS.value.equals(policyData.get("code").toString())) {
					return;
				}
				//获取内容
				JSONObject data = (JSONObject) policyData.get("data");
				//内容为空，跳出循环
				if(data == null || data.get("details") == null){
					break;
				}else{
					//获取交易数组
					JSONArray list = data.getJSONArray("details");
					if(list==null || list.size()<1) break;
					for(Object object : list){
						Map<String, Object> bean = (Map<String, Object>)object;
						//判断缓存是否有当前数据
						if(redisUtils.exists(RedisNameConstants.zfpay_cashback_record+StringUtil.getMapValue(bean, "sN"))){
							continue;
						}
						//存入相关参数
						bean.put("account_id", StringUtil.getMapValue(policy, "account_id"));
						bean.put("manager_id", StringUtil.getMapValue(policy, "manager_id"));
						bean.put("cre_date", TimeUtil.getDayString());
						bean.put("cre_time", TimeUtil.getTimeString());
						if(this.checkDataMachineCashbackRecord(bean, machine_type)>0){
							try{
								//保存返现信息信息
								int num = this.insertDataMachineCashbackRecordDeal(bean, machine_type);
								if(num != 1){
									throw new Exception();
								}else{
									redisUtils.set(RedisNameConstants.zfpay_cashback_record+StringUtil.getMapValue(bean, "sN"), bean, SysParamConstant.two_day_seconds);
								}
							}catch(Exception e){
								LOGGER.error("返现数据保存异常："+bean.toString()+ ExceptionUtil.getExceptionAllinformation(e));
							}
						}

					}
				}
				//页数自增
				pageNum++;
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataMachineCashbackRecord方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
	}

	/***
	 * 传统POS处理激活返现收益5555555555
	 * @param bean
	 * @return
	 */
	private int mposPolicy5RecordHandle(Map<String, Object> bean){
		List<Map<String, Object>> arrayList = zhongFuDataAcquireMapper.getPolicy5UserInfo(bean);
		if(arrayList.size()>0) {
			Map<String, Object> map = arrayList.get(0);
			Double reward = Double.parseDouble(map.get("module5_reward").toString());
			HashMap<Integer, Integer> mmm = new HashMap();

			Map<String, Object> map_param = new HashMap<>();
			//StringUtil.getMapValue(bean, "SN")
			map_param.put("sn", StringUtil.getMapValue(bean, "SN"));
			List<Map<String, Object>> arrayList2 = zhongFuDataAcquireMapper.getMposUserList(map_param);
			String zhishudaili = "0";
			for (Map<String, Object> mp : arrayList2) {
				if ("1".equals(mp.get("state_status").toString())) {
					zhishudaili = mp.get("user_id").toString();
				}
			}
			activitySettlementMapper.update_t_sys_pos_policy_info(zhishudaili,StringUtil.getMapValue(map, "id"));
			if (arrayList2.size() == 1) {
				if (reward == 22){mmm.put(0,22);}
				if (reward == 21){mmm.put(0,21);}

				if (reward == 20) {mmm.put(0, 20);}
				if (reward == 18) {mmm.put(0, 18);}
				if (reward == 15) {mmm.put(0, 15);}
				if (reward == 12) {mmm.put(0, 12);}
				if (reward == 10) {mmm.put(0, 10);}
				if (reward == 8) {mmm.put(0, 8);}
				if (reward == 5) {mmm.put(0, 5);}
				if(reward>=5){
					if(reward==5){
						Integer cnt = zhongFuDataAcquireMapper.selectPolicy5Reward5(zhishudaili);
						if(cnt==0){
							HashMap<String, Object> params = new HashMap<>();
							params.put("sn", StringUtil.getMapValue(bean, "SN"));
							params.put("user_id", zhishudaili);
							params.put("policy_id", map.get("policy_id"));
							params.put("money", mmm.get(0));
							zhongFuDataAcquireMapper.insertPolicy5Record(params);
						}
					}else{
						HashMap<String, Object> params = new HashMap<>();
						params.put("sn", StringUtil.getMapValue(bean, "SN"));
						params.put("user_id", zhishudaili);
						params.put("policy_id", map.get("policy_id"));
						params.put("money", mmm.get(0));
						zhongFuDataAcquireMapper.insertPolicy5Record(params);
					}
				}
			}
			if (arrayList2.size() == 2) {
				if(reward==22){mmm.put(0,1);mmm.put(1,21);}
				if(reward==21){mmm.put(0,1);mmm.put(1,20);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 18);}
				if (reward == 18) {mmm.put(0, 3);mmm.put(1, 15);}
				if (reward == 15) {mmm.put(0, 3);mmm.put(1, 12);}
				if (reward == 12) {mmm.put(0, 2);mmm.put(1, 10);}
				if (reward == 10) {mmm.put(0, 2);mmm.put(1, 8);}
				if (reward == 8) {mmm.put(0, 3);mmm.put(1, 5);}
				if(reward>=8){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if (arrayList2.size() == 3) {
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,20);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,18);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 15);}
				if (reward == 18) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 12);}
				if (reward == 15) {mmm.put(0, 3);mmm.put(1, 2);mmm.put(2, 10);}
				if (reward == 12) {mmm.put(0, 2);mmm.put(1, 2);mmm.put(2, 8);}
				if (reward == 10) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 5);}
				if(reward>=10){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili2);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if (arrayList2.size() == 4) {
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,18);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,15);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 3);mmm.put(3, 12);}
				if (reward == 18) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 2);mmm.put(3, 10);}
				if (reward == 15) {mmm.put(0, 3);mmm.put(1, 2);mmm.put(2, 2);mmm.put(3, 8);}
				if (reward == 12) {mmm.put(0, 2);mmm.put(1, 2);mmm.put(2, 3);mmm.put(3, 5);}
				if(reward>=12){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili2);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili3);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if (arrayList2.size() == 5) {
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,15);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,12);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 3);mmm.put(3, 2);mmm.put(4, 10);}
				if (reward == 18) {mmm.put(0, 3);mmm.put(1, 3);mmm.put(2, 2);mmm.put(3, 2);mmm.put(4, 8);}
				if (reward == 15) {mmm.put(0, 3);mmm.put(1, 2);mmm.put(2, 2);mmm.put(3, 2);mmm.put(4, 5);}
				if(reward>=15){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili2);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili3);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili4);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}

			if (arrayList2.size() == 6) {
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,12);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,10);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 3);mmm.put(3, 2);mmm.put(4, 2);mmm.put(5, 8);}
				if (reward == 18) {mmm.put(0, 3);mmm.put(1, 3);mmm.put(2, 2);mmm.put(3, 2);mmm.put(4, 3);mmm.put(5, 5);}
				if(reward>=18){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili2);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili3);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili4);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili5);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if (arrayList2.size() == 7) {
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,10);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,2);mmm.put(6,8);}

				if (reward == 20) {mmm.put(0, 2);mmm.put(1, 3);mmm.put(2, 3);mmm.put(3, 2);mmm.put(4, 2);mmm.put(5, 3);mmm.put(6, 5);}
				if(reward>=20){
					HashMap<String, Object> params = new HashMap<>();
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", zhishudaili);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili1);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili2);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili3);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili4);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili5);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn", StringUtil.getMapValue(bean, "SN"));
					params.put("user_id", shangjidaili6);
					params.put("policy_id", map.get("policy_id"));
					params.put("money", mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==8){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,2);mmm.put(7,8);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,2);mmm.put(6,3);mmm.put(7,5);}
				if(reward>=21){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(7));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili6);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili7 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili6);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili7);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==9){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,2);mmm.put(7,3);mmm.put(8,5);}
				if(reward>=22){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(8));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(7));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili6);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili7 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili6);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili7);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili8 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili7);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili8);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
		}
		return 0;
	}

	/***
	 * 传统POS处理激活返现收益5555555555
	 * @param bean
	 * @return
	 */
	private int traposPolicy5RecordHandle(Map<String, Object> bean){
		List<Map<String, Object>> arrayList = zhongFuDataAcquireMapper.getPolicy5UserInfo(bean);
		if(arrayList.size()>0){
			Map<String, Object> map = arrayList.get(0);
			Double reward = Double.parseDouble(map.get("module5_reward").toString());
			HashMap<Integer,Integer> mmm = new HashMap();

			Map<String,Object> map_param = new HashMap<>();
			map_param.put("sn",StringUtil.getMapValue(bean, "SN"));
			List<Map<String,Object>> arrayList2 = zhongFuDataAcquireMapper.getTraposUserList(map_param);
			String zhishudaili = "0";
			for(Map<String,Object> mp : arrayList2){
				if("1".equals(mp.get("state_status").toString())){
					zhishudaili = mp.get("user_id").toString();
				}
			}
			activitySettlementMapper.update_t_sys_pos_policy_info(zhishudaili,StringUtil.getMapValue(map, "id"));
			if(arrayList2.size()==1){
				if(reward==22){mmm.put(0,22);}
				if(reward==21){mmm.put(0,21);}
				if(reward==20){mmm.put(0,20);}
				if(reward==18){mmm.put(0,18);}
				if(reward==15){mmm.put(0,15);}
				if(reward==12){mmm.put(0,12);}
				if(reward==10){mmm.put(0,10);}
				if(reward==8){mmm.put(0,8);}
				if(reward==5){mmm.put(0,5);}
				if(reward>=5){
					if(reward==5){
						Integer cnt = zhongFuDataAcquireMapper.selectPolicy5Reward5(zhishudaili);
						if(cnt==0){
							HashMap<String,Object> params = new HashMap<>();
							params.put("sn",StringUtil.getMapValue(bean, "SN"));
							params.put("user_id",zhishudaili);
							params.put("policy_id",map.get("policy_id"));
							params.put("money",mmm.get(0));
							zhongFuDataAcquireMapper.insertPolicy5Record(params);
						}
					}else{
						HashMap<String,Object> params = new HashMap<>();
						params.put("sn",StringUtil.getMapValue(bean, "SN"));
						params.put("user_id",zhishudaili);
						params.put("policy_id",map.get("policy_id"));
						params.put("money",mmm.get(0));
						zhongFuDataAcquireMapper.insertPolicy5Record(params);
					}
				}
			}
			if(arrayList2.size()==2){
				if(reward==22){mmm.put(0,1);mmm.put(1,21);}
				if(reward==21){mmm.put(0,1);mmm.put(1,20);}

				if(reward==20){mmm.put(0,2);mmm.put(1,18);}
				if(reward==18){mmm.put(0,3);mmm.put(1,15);}
				if(reward==15){mmm.put(0,3);mmm.put(1,12);}
				if(reward==12){mmm.put(0,2);mmm.put(1,10);}
				if(reward==10){mmm.put(0,2);mmm.put(1,8);}
				if(reward==8){mmm.put(0,3);mmm.put(1,5);}
				if(reward>=8){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==3){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,20);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,18);}

				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,15);}
				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,15);}
				if(reward==18){mmm.put(0,2);mmm.put(1,3);mmm.put(2,12);}
				if(reward==15){mmm.put(0,3);mmm.put(1,2);mmm.put(2,10);}
				if(reward==12){mmm.put(0,2);mmm.put(1,2);mmm.put(2,8);}
				if(reward==10){mmm.put(0,2);mmm.put(1,3);mmm.put(2,5);}
				if(reward>=10){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==4){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,18);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,15);}

				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,3);mmm.put(3,12);}
				if(reward==18){mmm.put(0,2);mmm.put(1,3);mmm.put(2,2);mmm.put(3,10);}
				if(reward==15){mmm.put(0,3);mmm.put(1,2);mmm.put(2,2);mmm.put(3,8);}
				if(reward==12){mmm.put(0,2);mmm.put(1,2);mmm.put(2,3);mmm.put(3,5);}
				if(reward>=12){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==5){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,15);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,12);}

				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,3);mmm.put(3,2);mmm.put(4,10);}
				if(reward==18){mmm.put(0,3);mmm.put(1,3);mmm.put(2,2);mmm.put(3,2);mmm.put(4,8);}
				if(reward==15){mmm.put(0,3);mmm.put(1,2);mmm.put(2,2);mmm.put(3,2);mmm.put(4,5);}
				if(reward>=15){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==6){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,12);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,10);}

				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,3);mmm.put(3,2);mmm.put(4,2);mmm.put(5,8);}
				if(reward==18){mmm.put(0,3);mmm.put(1,3);mmm.put(2,2);mmm.put(3,2);mmm.put(4,3);mmm.put(5,5);}
				if(reward>=18){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==7){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,10);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,2);mmm.put(6,8);}
				if(reward==20){mmm.put(0,2);mmm.put(1,3);mmm.put(2,3);mmm.put(3,2);mmm.put(4,2);mmm.put(5,3);mmm.put(6,5);}
				if(reward>=20){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili6);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==8){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,2);mmm.put(7,8);}
				if(reward==21){mmm.put(0,1);mmm.put(1,2);mmm.put(2,3);mmm.put(3,3);mmm.put(4,2);mmm.put(5,2);mmm.put(6,3);mmm.put(7,5);}
				if(reward>=21){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(7));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili6);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili7 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili6);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili7);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
			if(arrayList2.size()==9){
				if(reward==22){mmm.put(0,1);mmm.put(1,1);mmm.put(2,2);mmm.put(3,3);mmm.put(4,3);mmm.put(5,2);mmm.put(6,2);mmm.put(7,3);mmm.put(8,5);}
				if(reward>=22){
					HashMap<String,Object> params = new HashMap<>();
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",zhishudaili);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(8));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
					String shangjidaili1 = zhongFuDataAcquireMapper.getUserRefererID(zhishudaili);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili1);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(7));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili2 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili1);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili2);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(6));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili3 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili2);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili3);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(5));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili4 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili3);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili4);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(4));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili5 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili4);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili5);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(3));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);


					String shangjidaili6 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili5);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili6);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(2));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili7 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili6);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili7);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(1));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);

					String shangjidaili8 = zhongFuDataAcquireMapper.getUserRefererID(shangjidaili7);
					params.put("sn",StringUtil.getMapValue(bean, "SN"));
					params.put("user_id",shangjidaili8);
					params.put("policy_id",map.get("policy_id"));
					params.put("money",mmm.get(0));
					zhongFuDataAcquireMapper.insertPolicy5Record(params);
				}
			}
		}
		return 0;
	}

	/**
	 * 校验返现商户
	 * @return
	 */
	private int checkDataMachineCashbackRecord(Map<String, Object> bean, String machine_type){
		int num = 0;
		if(ZhongFuInterfaceCodeConstant.machine_type_02.equals(machine_type)){
			num = zhongFuDataAcquireMapper.checkTransactionPosExists(bean);
		}else if(ZhongFuInterfaceCodeConstant.machine_type_01.equals(machine_type)){
			num = zhongFuDataAcquireMapper.checkMposExists(bean);
		}
		return num;
	}

	/**
	 * 保存返现信息信息
	 * @param bean
	 * @param machine_type
	 * @return
	 */
	private int insertDataMachineCashbackRecordDeal(Map<String, Object> bean, String machine_type){
		int num = 0;
		if(ZhongFuInterfaceCodeConstant.machine_type_02.equals(machine_type)){
			num = zhongFuDataAcquireMapper.insertTransposDataMachineCashbackRecordDeal(bean);
		}else if(ZhongFuInterfaceCodeConstant.machine_type_01.equals(machine_type)){
			num = zhongFuDataAcquireMapper.insertMposDataMachineCashbackRecordDeal(bean);
		}
		return num;
	}

	/**
	 * 获取中付政策信息，插入到系统政策信息表中(传统POS)
	 */
	@Override
	public R getTransposDataPolicyRecord(String account_id) {
		try{
			//查询账号详情
			Map<String, Object> account = zhongFuDataAcquireMapper.getManagerAccountDetail(account_id);
			//删除当前账号政策信息
			zhongFuDataAcquireMapper.deleteDataTraposPolicyRecord(account_id);
			//查询当前账号的最新政策信息
			//初始页数
			int pageNum = 1;
			//获取数据
			while(true){
				//通过7002接口取交易数据
				R transposData = zhongFuInterfaceService.requestType7004(StringUtil.getMapValue(account, "app_id"), ZhongFuInterfaceCodeConstant.machine_type_02, String.valueOf(pageNum), StringUtil.getMapValue(account, "app_key"));
				//获取异常
				if(!R.Type.SUCCESS.value.equals(transposData.get("code").toString())) {
					return R.error("政策信息获取异常");
				}
				//获取内容
				JSONObject data = (JSONObject) transposData.get("data");
				//内容为空，跳出循环
				if(data == null || data.get("details") == null){
					break;
				}else{
					//获取交易数组
					JSONArray list = data.getJSONArray("details");
					if(list==null || list.size()<1) break;
					for(Object object : list){
						Map<String, Object> bean = (Map<String, Object>)object;
						//存入相关参数
						bean.put("account_id", StringUtil.getMapValue(account, "id"));
						bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
						bean.put("cre_date", TimeUtil.getDayString());
						bean.put("cre_time", TimeUtil.getTimeString());
						try{
							//保存政策信息
							int num = zhongFuDataAcquireMapper.insertDataTraposPolicyRecord(bean);
							if(num != 1){
								throw new Exception();
							}
						}catch(Exception e){
							LOGGER.error("政策信息保存异常："+bean.toString()+ ExceptionUtil.getExceptionAllinformation(e));
						}
					}
				}
				//页数自增
				pageNum++;
			}
			return R.ok("政策信息获取成功");
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataPolicyRecord方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error("政策信息获取异常");
		}
	}

	/**
	 * 获取中付政策信息，插入到系统政策信息表中(MPOS)
	 */
	@Override
	public R getMposDataPolicyRecord(String account_id) {
		try{
			//查询账号详情
			Map<String, Object> account = zhongFuDataAcquireMapper.getManagerAccountDetail(account_id);
			//删除当前账号政策信息
			zhongFuDataAcquireMapper.deleteDataMposPolicyRecord(account_id);
			//查询当前账号的最新政策信息
			//初始页数
			int pageNum = 1;
			//获取数据
			while(true){
				//通过7002接口取交易数据
				R transposData = zhongFuInterfaceService.requestType7004(StringUtil.getMapValue(account, "app_id"), ZhongFuInterfaceCodeConstant.machine_type_01, String.valueOf(pageNum), StringUtil.getMapValue(account, "app_key"));
				//获取异常
				if(!R.Type.SUCCESS.value.equals(transposData.get("code").toString())) {
					return R.error("政策信息获取异常");
				}
				//获取内容
				JSONObject data = (JSONObject) transposData.get("data");
				//内容为空，跳出循环
				if(data == null || data.get("details") == null){
					break;
				}else{
					//获取交易数组
					JSONArray list = data.getJSONArray("details");
					if(list==null || list.size()<1) break;
					for(Object object : list){
						Map<String, Object> bean = (Map<String, Object>)object;
						//存入相关参数
						bean.put("account_id", StringUtil.getMapValue(account, "id"));
						bean.put("manager_id", StringUtil.getMapValue(account, "manager_id"));
						bean.put("cre_date", TimeUtil.getDayString());
						bean.put("cre_time", TimeUtil.getTimeString());
						try{
							//保存政策信息
							int num = zhongFuDataAcquireMapper.insertDataMposPolicyRecord(bean);
							if(num != 1){
								throw new Exception();
							}
						}catch(Exception e){
							LOGGER.error("政策信息保存异常："+bean.toString()+ ExceptionUtil.getExceptionAllinformation(e));
						}
					}
				}
				//页数自增
				pageNum++;
			}
			return R.ok("政策信息获取成功");
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataPolicyRecord方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error("政策信息获取异常");
		}
	}

	/**
	 * 获取中付激活信息
	 */
	@Override
	public void getDataActivatedState() {
		LOGGER.info("------------------中付激活信息接口-START-------------");
		try{
			//获取传统POS未激活列表
			List<Map<String, Object>> traposList = zhongFuDataAcquireMapper.getTraposUnactivatedStateList();
			if(traposList!=null && traposList.size()>0){
				for(Map<String, Object> trapos : traposList){
					SpringUtils.getAopProxy(this).getDataActivatedStateRecord(trapos, ZhongFuInterfaceCodeConstant.machine_type_02);
				}
			}
			//获取MPOS政策信息列表
			List<Map<String, Object>> mposList =zhongFuDataAcquireMapper.getMposUnactivatedStateList();
			if(mposList!=null && mposList.size()>0){
				for(Map<String, Object> mpos : mposList){
					SpringUtils.getAopProxy(this).getDataActivatedStateRecord(mpos, ZhongFuInterfaceCodeConstant.machine_type_01);
				}
			}
		}catch(Exception e){
			LOGGER.error("ZhongFuDataAcquireServiceImpl -- getDataActivatedState方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
		}
		LOGGER.info("------------------中付激活信息接口-END--------------");

	}

	/**
	 * 查询单个POS机激活状态
	 * @param map
	 * @param machine_type
	 */
	@Transactional
	public void getDataActivatedStateRecord(Map<String, Object> map, String machine_type){
		int num = 0;
		try{
			String startTime = "20180101000000";
			String endTime = TimeUtil.getDayFormat4();
			//通过7002接口取交易数据
			R transposData = zhongFuInterfaceService.requestType7006(StringUtil.getMapValue(map, "app_id"), StringUtil.getMapValue(map, "sn"), startTime, endTime, machine_type, String.valueOf(1), StringUtil.getMapValue(map, "app_key"));
			//获取异常
			if(!R.Type.SUCCESS.value.equals(transposData.get("code").toString())) {
				return;
			}
			//获取内容
			JSONObject data = (JSONObject) transposData.get("data");
			//内容为空，跳出循环
			if(data == null || data.get("details") == null){
				return;
			}else{
				//获取交易数组
				JSONArray list = data.getJSONArray("details");
				if(list==null || list.size()<1) return;
				Map<String, Object> bean = (Map<String, Object>)list.get(0);
				//判断是否已激活
				if("1".equals(StringUtil.getMapValue(bean, "activateStatus"))){
					//更新激活状态
					map.put("act_date", TimeUtil.getDayString());
					map.put("act_time", TimeUtil.getTimeString());
					if(ZhongFuInterfaceCodeConstant.machine_type_02.equals(machine_type)){
						//更新激活状态
						num = zhongFuDataAcquireMapper.updateTraposActivatedState(map);
						if(num != 1){
							throw new Exception("激活状态更新异常");
						}
						traposPolicy5RecordHandle(bean);

						//查询跟当前POS机相关的用户集合
						List<Map<String, Object>> traposUserList = zhongFuDataAcquireMapper.getTraposUserList(map);
						if(traposUserList!=null && traposUserList.size()>0){
							for(Map<String, Object> traposUser : traposUserList){
								traposUser.put("up_date", TimeUtil.getDayString());
								traposUser.put("up_time", TimeUtil.getTimeString());
								if("1".equals(StringUtil.getMapValue(traposUser, "state_status"))){
									//更新商户激活数量(传统POS)
									String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(traposUser, "sn"));
									if("epos".equals(pos_type)){
										num = zhongFuDataAcquireMapper.updateSummaryUserEposMerchantActNum(traposUser);
										if(num != 1){
											throw new Exception("汇总异常："+traposUser.toString());
										}
									}else{
										num = zhongFuDataAcquireMapper.updateSummaryUserTraditionalPosMerchantActNum(traposUser);
										if(num != 1){
											throw new Exception("汇总异常："+traposUser.toString());
										}
									}

								}else{
									//更新代理激活数量(传统POS)
									String pos_type = agentSysTraditionalPosInfoMapper.getAgentSysEposInfoBySn(StringUtil.getMapValue(traposUser, "sn"));
									if("epos".equals(pos_type)){
										num = zhongFuDataAcquireMapper.updateSummaryUserEposAgencyActNum(traposUser);
										if(num != 1){
											throw new Exception("汇总异常："+traposUser.toString());
										}
									}else{
										num = zhongFuDataAcquireMapper.updateSummaryUserTraditionalPosAgencyActNum(traposUser);
										if(num != 1){
											throw new Exception("汇总异常："+traposUser.toString());
										}
									}

								}
							}
						}

					}else{
						//更新激活状态
						num = zhongFuDataAcquireMapper.updateMposActivatedState(map);
						if(num != 1){
							throw new Exception("激活状态更新异常");
						}
						//处理激活返现收益5555555555
						mposPolicy5RecordHandle(bean);
						//查询跟当前POS机相关的用户集合
						List<Map<String, Object>> mposUserList = zhongFuDataAcquireMapper.getMposUserList(map);
						if(mposUserList!=null && mposUserList.size()>0){
							for(Map<String, Object> mposUser : mposUserList){
								mposUser.put("up_date", TimeUtil.getDayString());
								mposUser.put("up_time", TimeUtil.getTimeString());
								if("1".equals(StringUtil.getMapValue(mposUser, "state_status"))){
									//更新商户激活数量(MPOS)
									num = zhongFuDataAcquireMapper.updateSummaryUserMposMerchantActNum(mposUser);
									if(num != 1){
										throw new Exception("汇总异常："+mposUser.toString());
									}
								}else{
									//更新代理激活数量(MPOS)
									num = zhongFuDataAcquireMapper.updateSummaryUserMposAgencyActNum(mposUser);
									if(num != 1){
										throw new Exception("汇总异常："+mposUser.toString());
									}
								}
							}
						}
					}
				}
			}

		}catch(Exception e){
			LOGGER.error("当前POS机处理异常:" + map.toString() + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public static void main(String args[]){
		try{
			//开始时间
			String startTime = "20190901231234";
			//当前时间
			String endTime = "20190903235012";
			//获取开始时间的跨天截断时间点
			String cutStartTime = TimeUtil.getBankCutDate(startTime);
			//获取结束时间的跨天截断时间点
			String cutEndTime = TimeUtil.getBankCutDate(endTime);
			//获取昨日的跨天截断时间点
			String cutYesterdayTime = TimeUtil.getYesterdayBankCutDate(endTime);
			//判断获取数据的时间段
			if(cutStartTime.compareTo(cutEndTime)==0 && startTime.compareTo(cutStartTime)<0 && endTime.compareTo(cutStartTime)>0){
				endTime = cutStartTime;
			}else if(cutStartTime.compareTo(cutEndTime)!=0 && endTime.compareTo(cutEndTime)<0 && startTime.compareTo(cutYesterdayTime)<0){
				endTime = cutYesterdayTime;
			}else if(cutStartTime.compareTo(cutEndTime)!=0 && endTime.compareTo(cutEndTime)>=0){
				endTime = cutEndTime;
			}
			System.out.println("cutStartTime:"+cutStartTime);
			System.out.println("cutEndTime:"+cutEndTime);
			System.out.println("cutYesterdayTime:"+cutYesterdayTime);
			System.out.println("startTime:"+startTime);
			System.out.println("endTime:"+endTime);

		}catch(Exception e){

		}

	}

}
