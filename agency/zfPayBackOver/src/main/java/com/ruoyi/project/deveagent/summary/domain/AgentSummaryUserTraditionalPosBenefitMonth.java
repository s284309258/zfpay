package com.ruoyi.project.deveagent.summary.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_mpos_trans_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSummaryUserTraditionalPosBenefitMonth extends BaseEntity
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
	
	/** 伞下人数 */
	@Excel(name = "伞下人数")
	private String under_num;
	
	/** 直推人数 */
	@Excel(name = "直推人数")
	private String refer_num;
	
	/** 商户总数量 */
	@Excel(name = "商户总数量")
	private String pos_num;
	
	/** 直营商户数量 */
	@Excel(name = "直营商户数量")
	private String merchant_pos_num;
	
	/** 代理商户数量 */
	@Excel(name = "代理商户数量")
	private String agency_pos_num;
	
	/** 激活数量 */
	@Excel(name = "激活数量")
	private String act_num;
	
	/** 直营激活数量 */
	@Excel(name = "直营激活数量")
	private String merchant_act_num;
	
	/** 代理激活数量 */
	@Excel(name = "代理激活数量")
	private String agency_act_num;
	
	/** 扣除金额 */
	@Excel(name = "扣除金额")
	private String deduct_money;
	
	/** 有交易的POS机总数量 */
	@Excel(name = "有交易的POS机总数量")
	private String trade_num;
	
	/** 直营有交易POS机总数量 */
	@Excel(name = "直营有交易POS机总数量")
	private String merchant_trade_num;
	
	/** 代理有交易POS机总数量  */
	@Excel(name = "代理有交易POS机总数量 ")
	private String agency_trade_num;
	
	/** 创建日期 */
	@Excel(name = "创建日期")
	private String cre_date;

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

	public String getUnder_num() {
		return under_num;
	}

	public void setUnder_num(String under_num) {
		this.under_num = under_num;
	}

	public String getRefer_num() {
		return refer_num;
	}

	public void setRefer_num(String refer_num) {
		this.refer_num = refer_num;
	}

	public String getPos_num() {
		return pos_num;
	}

	public void setPos_num(String pos_num) {
		this.pos_num = pos_num;
	}

	public String getMerchant_pos_num() {
		return merchant_pos_num;
	}

	public void setMerchant_pos_num(String merchant_pos_num) {
		this.merchant_pos_num = merchant_pos_num;
	}

	public String getAgency_pos_num() {
		return agency_pos_num;
	}

	public void setAgency_pos_num(String agency_pos_num) {
		this.agency_pos_num = agency_pos_num;
	}

	public String getAct_num() {
		return act_num;
	}

	public void setAct_num(String act_num) {
		this.act_num = act_num;
	}

	public String getMerchant_act_num() {
		return merchant_act_num;
	}

	public void setMerchant_act_num(String merchant_act_num) {
		this.merchant_act_num = merchant_act_num;
	}

	public String getAgency_act_num() {
		return agency_act_num;
	}

	public void setAgency_act_num(String agency_act_num) {
		this.agency_act_num = agency_act_num;
	}

	public String getDeduct_money() {
		return deduct_money;
	}

	public void setDeduct_money(String deduct_money) {
		this.deduct_money = deduct_money;
	}

	public String getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(String trade_num) {
		this.trade_num = trade_num;
	}

	public String getMerchant_trade_num() {
		return merchant_trade_num;
	}

	public void setMerchant_trade_num(String merchant_trade_num) {
		this.merchant_trade_num = merchant_trade_num;
	}

	public String getAgency_trade_num() {
		return agency_trade_num;
	}

	public void setAgency_trade_num(String agency_trade_num) {
		this.agency_trade_num = agency_trade_num;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentSummaryUserTraditionalPosBenefitMonth [id=" + id + ", manager_id=" + manager_id + ", user_id="
				+ user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", performance=" + performance
				+ ", num=" + num + ", merchant_performance=" + merchant_performance + ", merchant_num=" + merchant_num
				+ ", agency_performance=" + agency_performance + ", agency_num=" + agency_num + ", benefit=" + benefit
				+ ", share_benefit=" + share_benefit + ", single_benefit=" + single_benefit + ", return_benefit="
				+ return_benefit + ", activity_benefit=" + activity_benefit + ", merchant_benefit=" + merchant_benefit
				+ ", merchant_share_benefit=" + merchant_share_benefit + ", merchant_single_benefit="
				+ merchant_single_benefit + ", merchant_return_benefit=" + merchant_return_benefit
				+ ", merchant_activity_benefit=" + merchant_activity_benefit + ", agency_benefit=" + agency_benefit
				+ ", agency_share_benefit=" + agency_share_benefit + ", agency_single_benefit=" + agency_single_benefit
				+ ", agency_return_benefit=" + agency_return_benefit + ", under_num=" + under_num + ", refer_num="
				+ refer_num + ", pos_num=" + pos_num + ", merchant_pos_num=" + merchant_pos_num + ", agency_pos_num="
				+ agency_pos_num + ", act_num=" + act_num + ", merchant_act_num=" + merchant_act_num
				+ ", agency_act_num=" + agency_act_num + ", deduct_money=" + deduct_money + ", trade_num=" + trade_num
				+ ", merchant_trade_num=" + merchant_trade_num + ", agency_trade_num=" + agency_trade_num
				+ ", cre_date=" + cre_date + "]";
	}

}
