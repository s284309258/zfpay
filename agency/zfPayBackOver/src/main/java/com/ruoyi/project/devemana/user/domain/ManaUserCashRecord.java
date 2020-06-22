package com.ruoyi.project.devemana.user.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_cash_record
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class ManaUserCashRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 代理账号 */
	@Excel(name = "代理账号")
	private String manager_login_name;
	
	/** 代理用户名  */
	@Excel(name = "代理用户名")
	private String manager_user_name;
	
	/** 中付提现账号  */
	@Excel(name = "中付提现账号")
	private String sub_agent_account;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 机构号 */
	@Excel(name = "机构号")
	private String org_no;
	
	/** 机构名 */
	@Excel(name = "机构名")
	private String org_name;
	
	/** 账户类型 */
	@Excel(name = "账户类型", readConverterExp = "11=对私,12=对公")
	private String account_type;
	
	/** 提现总额 */
	@Excel(name = "提现总额")
	private String cash_money;
	
	/** 实际到账金额 */
	@Excel(name = "实际到账金额")
	private String cash_actual_money;
	
	/** 所得税比例 */
	@Excel(name = "所得税比例")
	private String feet_rate;
	
	/** 比例手续费 */
	@Excel(name = "比例手续费")
	private String rate_feet_money;
	
	/** 单笔手续费 */
	@Excel(name = "单笔手续费")
	private String single_feet_money;
	
	/** 考核未达标扣除金额 */
	@Excel(name = "考核未达标扣除金额")
	private String deduct_money;
	
	/** 结算账号（银行卡号） */
	@Excel(name = "结算账号（银行卡号）")
	private String account;
	
	/** 结算账户名 */
	@Excel(name = "结算账户名")
	private String account_name;
	
	/** 身份证号 */
	@Excel(name = "身份证号")
	private String id_card;
	
	/** 银行代码 */
	@Excel(name = "银行代码")
	private String bank_code;
	
	/** 银行名称 */
	@Excel(name = "银行名称")
	private String bank_name;
	
	/** 批次号 */
	@Excel(name = "批次号")
	private String batch_no;
	
	/** 状态  */
	@Excel(name = "状态", readConverterExp = "00=待处理,02=处理中,04=已撤销,08=处理失败,09=处理成功")
	private String status;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_login_name() {
		return manager_login_name;
	}

	public void setManager_login_name(String manager_login_name) {
		this.manager_login_name = manager_login_name;
	}

	public String getManager_user_name() {
		return manager_user_name;
	}

	public void setManager_user_name(String manager_user_name) {
		this.manager_user_name = manager_user_name;
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

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getCash_money() {
		return cash_money;
	}

	public void setCash_money(String cash_money) {
		this.cash_money = cash_money;
	}

	public String getCash_actual_money() {
		return cash_actual_money;
	}

	public void setCash_actual_money(String cash_actual_money) {
		this.cash_actual_money = cash_actual_money;
	}

	public String getFeet_rate() {
		return feet_rate;
	}

	public void setFeet_rate(String feet_rate) {
		this.feet_rate = feet_rate;
	}

	public String getRate_feet_money() {
		return rate_feet_money;
	}

	public void setRate_feet_money(String rate_feet_money) {
		this.rate_feet_money = rate_feet_money;
	}

	public String getSingle_feet_money() {
		return single_feet_money;
	}

	public void setSingle_feet_money(String single_feet_money) {
		this.single_feet_money = single_feet_money;
	}

	public String getDeduct_money() {
		return deduct_money;
	}

	public void setDeduct_money(String deduct_money) {
		this.deduct_money = deduct_money;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
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

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
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

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSub_agent_account() {
		return sub_agent_account;
	}

	public void setSub_agent_account(String sub_agent_account) {
		this.sub_agent_account = sub_agent_account;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "ManaUserCashRecord [id=" + id + ", order_id=" + order_id + ", manager_id=" + manager_id
				+ ", manager_login_name=" + manager_login_name + ", manager_user_name=" + manager_user_name
				+ ", sub_agent_account=" + sub_agent_account + ", user_id=" + user_id + ", user_tel=" + user_tel
				+ ", real_name=" + real_name + ", org_no=" + org_no + ", org_name=" + org_name + ", account_type="
				+ account_type + ", cash_money=" + cash_money + ", cash_actual_money=" + cash_actual_money
				+ ", feet_rate=" + feet_rate + ", rate_feet_money=" + rate_feet_money + ", single_feet_money="
				+ single_feet_money + ", deduct_money=" + deduct_money + ", account=" + account + ", account_name="
				+ account_name + ", id_card=" + id_card + ", bank_code=" + bank_code + ", bank_name=" + bank_name
				+ ", batch_no=" + batch_no + ", status=" + status + ", cre_date=" + cre_date + ", up_date=" + up_date
				+ ", update_by=" + update_by + ", remark=" + remark + "]";
	}

}
