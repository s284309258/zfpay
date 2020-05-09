package com.ruoyi.project.deveagent.user.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_cash_record
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserCashRecordExcel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号  */
	private Integer id;
	
	/** 用户名（中付开出的代理账户）  */
	@Excel(name = "用户名（中付开出的代理账户）")
	private String sub_agent_account;

	/** 手机号  */
	@Excel(name = "子级代理账号")
	private String user_tel;

	/** 机构号 */
	@Excel(name = "机构号", readConverterExp = "25=847000000000015,26=847000000000018,27=847000000000017")
	private String org_no;
	
	/** 机构名 */
	@Excel(name = "机构名", readConverterExp = "25=湖南金盛和,26=顺付联,27=中昱科技")
	private String org_name;
	
	/** 结算账户名 */
	@Excel(name = "结算账户名")
	private String account_name;
	
	/** 结算账号（银行卡号） */
	@Excel(name = "结算账号")
	private String account;
	
	/** 账户类型@Excel(name = "账户类型", readConverterExp = "11=对私,12=对公") */
	@Excel(name = "账户类型")
	private String account_type;
	
	/** 银行代码 */
	@Excel(name = "银行代码")
	private String bank_code;
	
	/** 银行名称 */
	@Excel(name = "银行名称")
	private String bank_name;
	
	@Excel(name = "结算金额（元）")
	private String cash_actual_money;
	
	/** 身份证号 */
	@Excel(name = "身份证号")
	private String id_card;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 批次号 */
	@Excel(name = "批次号")
	private String batch_no;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;

	public String getSub_agent_account() {
		return sub_agent_account;
	}

	public void setSub_agent_account(String sub_agent_account) {
		this.sub_agent_account = sub_agent_account;
	}

	public String getUser_tel() { return user_tel; }

	public void setUser_tel(String user_tel) { this.user_tel = user_tel; }

	public String getOrg_no() {
		return org_no;
	}

	public void setOrg_no(String org_no) {
		this.org_no = org_no;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCash_actual_money() {
		return cash_actual_money;
	}

	public void setCash_actual_money(String cash_actual_money) {
		this.cash_actual_money = cash_actual_money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AgentUserCashRecordExcel [id=" + id + ", sub_agent_account=" + sub_agent_account + ",user_tel="+ user_tel
				+ ", org_no=" + org_no + ", org_name=" + org_name + ", account_name=" + account_name +
				", account=" + account + ", account_type=" + account_type + ", bank_code=" + bank_code +
				", bank_name=" + bank_name + ", cash_actual_money=" + cash_actual_money + ", id_card=" + id_card +
				", remark=" + remark + ", batch_no=" + batch_no + ", order_id=" + order_id + "]";
	}

}
