package com.ruoyi.project.deveagent.datampos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_mpos_trans_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentDataMposTransactionRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 冻结时间 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 账号编号 */
	@Excel(name = "账号编号")
	private String account_id;
	
	/** app_id */
	@Excel(name = "app_id")
	private String app_id;
	
	/** 归属代理账号 */
	@Excel(name = "归属代理账号")
	private String affiliation_agent_account;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 商户号 */
	@Excel(name = "商户号")
	private String mer_id;
	
	/** 终端号 */
	@Excel(name = "终端号")
	private String trml_id;
	
	/** 交易金额 */
	@Excel(name = "交易金额")
	private String trans_amount;
	
	/** 交易时间 */
	@Excel(name = "交易时间")
	private String trans_time;
	
	/** 参考号 */
	@Excel(name = "参考号")
	private String tran_ref_code;
	
	/** 交易类型 */
	@Excel(name = "交易类型", readConverterExp = "1=刷卡,2=快捷支付,3=微信,4=支付宝,5=银联二维码")
	private String trans_type;

	/** 交易产品 */
	@Excel(name = "交易产品", readConverterExp = "1=付款秒到(非商圈),2=VIP秒到(商圈),3=星券秒到")
	private String trans_product;

	/** 卡类型 */
	@Excel(name = "卡类型", readConverterExp = "0=贷记卡,1=借记卡,2=境外卡,3=云闪付")
	private String card_type;
	
	/** 商户结算费率 */
	@Excel(name = "商户结算费率")
	private String mer_sett_rate;
	
	/** 商户提现费 */
	@Excel(name = "商户提现费")
	private String mer_withdraw_fee;

	/** 商户封顶费 */
	@Excel(name = "商户封顶费")
	private String mer_cap_fee;
	
	/** 代理成本费率 */
	@Excel(name = "代理成本费率")
	private String agent_cost_rate;
	
	/** 代理封顶成本费 */
	@Excel(name = "代理封顶成本费")
	private String agent_cap_cost_fee;
	
	/** 出款状态 */
	@Excel(name = "出款状态", readConverterExp = "0=未出款,1=已出款")
	private String sett_status;
	
	/** 交易卡号 */
	@Excel(name = "交易卡号")
	private String card_no;
	
	/** 冲正标识 */
	@Excel(name = "冲正标识", readConverterExp = "0=正常,1=已冲正,3=已退货")
	private String process_flag;
	
	/** 商户名称 */
	@Excel(name = "商户名称")
	private String mer_name;

	/** 挥卡交易认证标识 */
	@Excel(name = "挥卡交易认证标识", readConverterExp = "0=未认证,1=已认证")
	private String is_authentication;
	
	/** 处理状态  */
	@Excel(name = "处理状态 ", readConverterExp = "00=待处理,02=处理中,08=处理异常,09=已处理")
	private String status;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;

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

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getAffiliation_agent_account() {
		return affiliation_agent_account;
	}

	public void setAffiliation_agent_account(String affiliation_agent_account) {
		this.affiliation_agent_account = affiliation_agent_account;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getMer_id() {
		return mer_id;
	}

	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public String getTrml_id() {
		return trml_id;
	}

	public void setTrml_id(String trml_id) {
		this.trml_id = trml_id;
	}

	public String getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getTran_ref_code() {
		return tran_ref_code;
	}

	public void setTran_ref_code(String tran_ref_code) {
		this.tran_ref_code = tran_ref_code;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getTrans_product() {
		return trans_product;
	}

	public void setTrans_product(String trans_product) {
		this.trans_product = trans_product;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getMer_sett_rate() {
		return mer_sett_rate;
	}

	public void setMer_sett_rate(String mer_sett_rate) {
		this.mer_sett_rate = mer_sett_rate;
	}

	public String getMer_withdraw_fee() {
		return mer_withdraw_fee;
	}

	public void setMer_withdraw_fee(String mer_withdraw_fee) {
		this.mer_withdraw_fee = mer_withdraw_fee;
	}

	public String getMer_cap_fee() {
		return mer_cap_fee;
	}

	public void setMer_cap_fee(String mer_cap_fee) {
		this.mer_cap_fee = mer_cap_fee;
	}

	public String getAgent_cost_rate() {
		return agent_cost_rate;
	}

	public void setAgent_cost_rate(String agent_cost_rate) {
		this.agent_cost_rate = agent_cost_rate;
	}

	public String getAgent_cap_cost_fee() {
		return agent_cap_cost_fee;
	}

	public void setAgent_cap_cost_fee(String agent_cap_cost_fee) {
		this.agent_cap_cost_fee = agent_cap_cost_fee;
	}

	public String getSett_status() {
		return sett_status;
	}

	public void setSett_status(String sett_status) {
		this.sett_status = sett_status;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getProcess_flag() {
		return process_flag;
	}

	public void setProcess_flag(String process_flag) {
		this.process_flag = process_flag;
	}

	public String getMer_name() {
		return mer_name;
	}

	public void setMer_name(String mer_name) {
		this.mer_name = mer_name;
	}

	public String getIs_authentication() {
		return is_authentication;
	}

	public void setIs_authentication(String is_authentication) {
		this.is_authentication = is_authentication;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	@Override
	public String toString() {
		return "AgentDataMposTransactionRecord [id=" + id + ", manager_id=" + manager_id + ", account_id=" + account_id
				+ ", app_id=" + app_id + ", affiliation_agent_account=" + affiliation_agent_account + ", sn=" + sn
				+ ", mer_id=" + mer_id + ", trml_id=" + trml_id + ", trans_amount=" + trans_amount + ", trans_time="
				+ trans_time + ", tran_ref_code=" + tran_ref_code + ", trans_type=" + trans_type + ", trans_product="
				+ trans_product + ", card_type=" + card_type + ", mer_sett_rate=" + mer_sett_rate
				+ ", mer_withdraw_fee=" + mer_withdraw_fee + ", mer_cap_fee=" + mer_cap_fee + ", agent_cost_rate="
				+ agent_cost_rate + ", agent_cap_cost_fee=" + agent_cap_cost_fee + ", sett_status=" + sett_status
				+ ", card_no=" + card_no + ", process_flag=" + process_flag + ", mer_name=" + mer_name
				+ ", is_authentication=" + is_authentication + ", status=" + status + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + "]";
	}
	
}
