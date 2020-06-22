package com.ruoyi.project.devemana.user.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class ManaUserCard extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 代理账号 */
	@Excel(name = "代理账号")
	private String manager_login_name;
	
	/** 代理用户名  */
	@Excel(name = "代理用户名")
	private String manager_user_name;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 结算账号 */
	@Excel(name = "结算账号")
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
	
	/** 银行卡照片 */
	@Excel(name = "银行卡照片")
	private String card_photo;
	
	/** 状态  */
	@Excel(name = "状态", readConverterExp = "00=待审核,08=审核失败,09=审核成功")
	private String status;
	
	/** 是否默认卡 */
	@Excel(name = "是否默认卡", readConverterExp = "0=非默认卡,1=默认卡")
	private String is_default;
	
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

	public String getCard_photo() {
		return card_photo;
	}

	public void setCard_photo(String card_photo) {
		this.card_photo = card_photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "ManaUserCard [id=" + id + ", manager_id=" + manager_id + ", manager_login_name=" + manager_login_name
				+ ", manager_user_name=" + manager_user_name + ", user_id=" + user_id + ", user_tel=" + user_tel
				+ ", real_name=" + real_name + ", account=" + account + ", account_name=" + account_name + ", id_card="
				+ id_card + ", bank_code=" + bank_code + ", bank_name=" + bank_name + ", card_photo=" + card_photo
				+ ", status=" + status + ", is_default=" + is_default + ", cre_date=" + cre_date + ", up_date="
				+ up_date + ", update_by=" + update_by + ", remark=" + remark + "]";
	}
	
}
