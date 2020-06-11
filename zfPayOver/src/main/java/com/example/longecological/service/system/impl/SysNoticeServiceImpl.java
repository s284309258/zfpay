package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.longecological.mapper.ratesmanage.CreditCardRatesApplyMapper;
import com.example.longecological.service.user.UserInfoCacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysNoticeMapper;
import com.example.longecological.service.system.SysNoticeCacheService;
import com.example.longecological.service.system.SysNoticeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 系统公告相关
 * @author Administrator
 *
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
	
	@Autowired
	SysNoticeCacheService sysNoticeCacheService;
	
	@Autowired
	SysNoticeMapper sysNoticeMapper;

	@Autowired
	CreditCardRatesApplyMapper creditCardRatesApplyMapper;

	@Autowired
	private UserInfoCacheService userInfoCacheService;

	/**
	 * 得到未读消息小红点add byqh 201912
	 * @param map
	 * @return
	 */
	public R getUnReadNews(Map<String, Object> map){
		try{
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}

			Map<String, Object> respondMap = new HashMap<>();


			Map<String, Object> user = userInfoCacheService.getUserInfoCacheById(StringUtil.getMapValue(map, "sys_user_id"));
			String manager_id = StringUtil.getMapValue(user, "manager_id");
//			List<Map<String, Object>> tposrecord = creditCardRatesApplyMapper.getApplyRateTraditionalPosRecordList(map);
//			List<Map<String, Object>> mposrecord = creditCardRatesApplyMapper.getApplyRateMposRecordList(map);
//			long cnt1 = tposrecord.stream().filter(f->{
//				if("08".equals(f.get("status")) || "09".equals(f.get("status"))){
//					return true;
//				}
//				return false;
//			}).count();
//			long cnt2 = mposrecord.stream().filter(f->{
//				if("08".equals(f.get("status")) || "09".equals(f.get("status"))){
//					return true;
//				}
//				return false;
//			}).count();


			int rateApplyCnt = sysNoticeMapper.selectUnReadRateApply(map);
			String applyRateFlag = "0";
			map.put("news_type","applyRateFlag");
			List<Map<String,Object>> listNewsRead = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"applyRateFlag",0,rateApplyCnt,manager_id);
			}else{
				Integer unread_num = (Integer) listNewsRead.get(0).get("unread_num");
				String read_flag = String.valueOf(listNewsRead.get(0).get("read_flag"));
				sysNoticeMapper.updateNewsReadState(unread_num,rateApplyCnt,StringUtil.getMapValue(map,"sys_user_id"),"applyRateFlag");
				applyRateFlag = read_flag;
			}
			respondMap.put("applyRateFlag",applyRateFlag);

			int recallCnt = sysNoticeMapper.selectUnReadRecall(map);
			String recallFlag = "0";
			map.put("news_type","recallFlag");
			List<Map<String,Object>> listNewsRead2 = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead2.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"recallFlag",0,recallCnt,manager_id);
			}else{
				Integer unread_num = (Integer) listNewsRead2.get(0).get("unread_num");
				String read_flag = String.valueOf(listNewsRead2.get(0).get("read_flag"));
				sysNoticeMapper.updateNewsReadState(unread_num,recallCnt,StringUtil.getMapValue(map,"sys_user_id"),"recallFlag");
				recallFlag = read_flag;
			}
			respondMap.put("recallFlag",recallFlag);

			Integer collegeCnt = sysNoticeMapper.selectCollegeInfo();
			String collegeFlag = "0";
			map.put("news_type","collegeFlag");
			List<Map<String,Object>> listNewsRead3 = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead3.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"collegeFlag",0,collegeCnt,manager_id);
			}else{
				Integer unread_num = (Integer) listNewsRead3.get(0).get("unread_num");
				String read_flag = String.valueOf(listNewsRead3.get(0).get("read_flag"));
				sysNoticeMapper.updateNewsReadState(unread_num,collegeCnt,StringUtil.getMapValue(map,"sys_user_id"),"collegeFlag");
				collegeFlag = read_flag;
			}
			respondMap.put("collegeFlag",collegeFlag);

			int cardCnt = sysNoticeMapper.selectUnReadRateApply(map);
			String cardFlag = "0";
			map.put("news_type","cardFlag");
			List<Map<String,Object>> listNewsRead4 = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead4.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"cardFlag",0,cardCnt,manager_id);
			}else{
				Integer unread_num = (Integer) listNewsRead4.get(0).get("unread_num");
				String read_flag = String.valueOf(listNewsRead4.get(0).get("read_flag"));
				sysNoticeMapper.updateNewsReadState(unread_num,cardCnt,StringUtil.getMapValue(map,"sys_user_id"),"cardFlag");
				cardFlag = read_flag;
			}
			respondMap.put("cardFlag",cardFlag);

			String appImgFlag = "0";
			map.put("news_type","appImgFlag");
			List<Map<String,Object>> listNewsRead5 = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead5.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"appImgFlag",0,0,manager_id);
			}else{
				String read_flag = String.valueOf(listNewsRead5.get(0).get("read_flag"));
				appImgFlag = read_flag;
			}
			respondMap.put("appImgFlag",appImgFlag);



			String cashFlag = "0";
			map.put("news_type","cashFlag");
			List<Map<String,Object>> listNewsRead6 = sysNoticeMapper.selectNewsReadState(map);
			if(listNewsRead6.size()<1){
				sysNoticeMapper.insertNewsReadState(StringUtil.getMapValue(map,"sys_user_id"),"appImgFlag",0,0,manager_id);
			}else{
				String read_flag = String.valueOf(listNewsRead6.get(0).get("read_flag"));
				cashFlag = read_flag;
			}
			respondMap.put("cashFlag",cashFlag);


			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		}catch (Exception e){
			LOGGER.error("SysNoticeServiceImpl -- getUnReadNews方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/***
	 * 更新已读未读标志 add byqh 2010912
	 * @param map
	 * @return
	 */
	public R updateNewsReadFlag(Map<String, Object> map){
		sysNoticeMapper.updateNewsReadFlag(map);
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_CODE_999999);
	}

	
	/**
	 * 首页最新两条公告信息
	 */
	@Override
	public R getNewNotice(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> noticeInfoList = sysNoticeMapper.getNewNotice(map);
			respondMap.put("noticeInfoList", noticeInfoList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNoticeServiceImpl -- getNewNotice方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	
	/**
	 * 查询系统公告列表
	 */
	@Override
	public R getNoticeList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//默认查询第一页
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "pageNum"))) {
				map.put("pageNum", "1");
			}
			map.put("start", (Integer.parseInt(StringUtil.getMapValue(map, "pageNum"))-1) * SysParamConstant.page_size);
			map.put("end", SysParamConstant.page_size);
			List<Map<String, Object>> noticeInfoList = sysNoticeMapper.getNoticeList(map);
			respondMap.put("noticeInfoList", noticeInfoList);
			respondMap.put("pageNum", map.get("pageNum"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNoticeServiceImpl -- getNoticeList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询系统公告详情
	 */
	@Override
	public R getNoticeDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//默认查询第一页
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "pageNum"))) {
				map.put("pageNum", "1");
			}
			Map<String, Object> noticeInfo = sysNoticeMapper.getNoticeDetail(map);
			//判断是否已读
			if("0".equals(StringUtil.getMapValue(noticeInfo, "is_read"))){
				map.put("cre_date", TimeUtil.getDayString());
				map.put("cre_time", TimeUtil.getTimeString());
				//保存读取记录
				sysNoticeMapper.addUserNoticeReadInfo(map);
			}
			respondMap.put("noticeInfo", noticeInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNoticeServiceImpl -- getNoticeDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}
