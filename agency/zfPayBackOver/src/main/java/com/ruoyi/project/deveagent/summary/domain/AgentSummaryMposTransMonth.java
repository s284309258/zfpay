package com.ruoyi.project.deveagent.summary.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_mpos_trans_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSummaryMposTransMonth extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 总业绩 */
	@Excel(name = "总业绩")
	private String performance;
	
	/** 总笔数 */
	@Excel(name = "总笔数")
	private String num;
	
	/** 直营总业绩 */
	@Excel(name = "直营总业绩")
	private String merchant_performance;
	
	/** 直营总笔数 */
	@Excel(name = "直营总笔数")
	private String merchant_num;
	
	/** 代理总业绩 */
	@Excel(name = "代理总业绩")
	private String agency_performance;
	
	/** 代理总笔数 */
	@Excel(name = "代理总笔数")
	private String agency_num;
	
	/** 总收入 */
	@Excel(name = "总收入")
	private String benefit;
	
	/** 分润收入 */
	@Excel(name = "分润收入")
	private String share_benefit;
	
	/** 单笔收入 */
	@Excel(name = "单笔收入")
	private String single_benefit;
	
	/** 机具返现收入 */
	@Excel(name = "机具返现收入")
	private String return_benefit;
	
	/** 活动收入 */
	@Excel(name = "活动收入")
	private String activity_benefit;
	
	/** 直营总收入 */
	@Excel(name = "直营总收入")
	private String merchant_benefit;
	
	/** 直营分润收入 */
	@Excel(name = "直营分润收入")
	private String merchant_share_benefit;
	
	/** 直营单笔收入 */
	@Excel(name = "直营单笔收入")
	private String merchant_single_benefit;
	
	/** 直营机具返现收入 */
	@Excel(name = "直营机具返现收入")
	private String merchant_return_benefit;
	
	/** 直营活动收入 */
	@Excel(name = "直营活动收入")
	private String merchant_activity_benefit;
	
	/** 代理总收入 */
	@Excel(name = "代理总收入")
	private String agency_benefit;
	
	/** 代理分润收入 */
	@Excel(name = "代理分润收入")
	private String agency_share_benefit;
	
	/** 代理单笔收入 */
	@Excel(name = "代理单笔收入")
	private String agency_single_benefit;
	
	/** 代理机具返现收入 */
	@Excel(name = "代理机具返现收入")
	private String agency_return_benefit;
	
	/** 创建月份 */
	@Excel(name = "创建月份")
	private String cre_month;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMerchant_performance() {
		return merchant_performance;
	}

	public void setMerchant_performance(String merchant_performance) {
		this.merchant_performance = merchant_performance;
	}

	public String getMerchant_num() {
		return merchant_num;
	}

	public void setMerchant_num(String merchant_num) {
		this.merchant_num = merchant_num;
	}

	public String getAgency_performance() {
		return agency_performance;
	}

	public void setAgency_performance(String agency_performance) {
		this.agency_performance = agency_performance;
	}

	public String getAgency_num() {
		return agency_num;
	}

	public void setAgency_num(String agency_num) {
		this.agency_num = agency_num;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getShare_benefit() {
		return share_benefit;
	}

	public void setShare_benefit(String share_benefit) {
		this.share_benefit = share_benefit;
	}

	public String getSingle_benefit() {
		return single_benefit;
	}

	public void setSingle_benefit(String single_benefit) {
		this.single_benefit = single_benefit;
	}

	public String getReturn_benefit() {
		return return_benefit;
	}

	public void setReturn_benefit(String return_benefit) {
		this.return_benefit = return_benefit;
	}

	public String getActivity_benefit() {
		return activity_benefit;
	}

	public void setActivity_benefit(String activity_benefit) {
		this.activity_benefit = activity_benefit;
	}

	public String getMerchant_benefit() {
		return merchant_benefit;
	}

	public void setMerchant_benefit(String merchant_benefit) {
		this.merchant_benefit = merchant_benefit;
	}

	public String getMerchant_share_benefit() {
		return merchant_share_benefit;
	}

	public void setMerchant_share_benefit(String merchant_share_benefit) {
		this.merchant_share_benefit = merchant_share_benefit;
	}

	public String getMerchant_single_benefit() {
		return merchant_single_benefit;
	}

	public void setMerchant_single_benefit(String merchant_single_benefit) {
		this.merchant_single_benefit = merchant_single_benefit;
	}

	public String getMerchant_return_benefit() {
		return merchant_return_benefit;
	}

	public void setMerchant_return_benefit(String merchant_return_benefit) {
		this.merchant_return_benefit = merchant_return_benefit;
	}

	public String getMerchant_activity_benefit() {
		return merchant_activity_benefit;
	}

	public void setMerchant_activity_benefit(String merchant_activity_benefit) {
		this.merchant_activity_benefit = merchant_activity_benefit;
	}

	public String getAgency_benefit() {
		return agency_benefit;
	}

	public void setAgency_benefit(String agency_benefit) {
		this.agency_benefit = agency_benefit;
	}

	public String getAgency_share_benefit() {
		return agency_share_benefit;
	}

	public void setAgency_share_benefit(String agency_share_benefit) {
		this.agency_share_benefit = agency_share_benefit;
	}

	public String getAgency_single_benefit() {
		return agency_single_benefit;
	}

	public void setAgency_single_benefit(String agency_single_benefit) {
		this.agency_single_benefit = agency_single_benefit;
	}

	public String getAgency_return_benefit() {
		return agency_return_benefit;
	}

	public void setAgency_return_benefit(String agency_return_benefit) {
		this.agency_return_benefit = agency_return_benefit;
	}

	public String getCre_month() {
		return cre_month;
	}

	public void setCre_month(String cre_month) {
		this.cre_month = cre_month;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentSummaryMposTransMonth [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", sn=" + sn + ", performance=" + performance
				+ ", num=" + num + ", merchant_performance=" + merchant_performance + ", merchant_num=" + merchant_num
				+ ", agency_performance=" + agency_performance + ", agency_num=" + agency_num + ", benefit=" + benefit
				+ ", share_benefit=" + share_benefit + ", single_benefit=" + single_benefit + ", return_benefit="
				+ return_benefit + ", activity_benefit=" + activity_benefit + ", merchant_benefit=" + merchant_benefit
				+ ", merchant_share_benefit=" + merchant_share_benefit + ", merchant_single_benefit="
				+ merchant_single_benefit + ", merchant_return_benefit=" + merchant_return_benefit
				+ ", merchant_activity_benefit=" + merchant_activity_benefit + ", agency_benefit=" + agency_benefit
				+ ", agency_share_benefit=" + agency_share_benefit + ", agency_single_benefit=" + agency_single_benefit
				+ ", agency_return_benefit=" + agency_return_benefit + ", cre_month=" + cre_month + "]";
	}
	
}
