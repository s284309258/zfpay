package com.ruoyi.project.deveagent.usermpos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMposMachineActiveRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	@Excel(name = "商户号")
	private String mer_id;
	
	@Excel(name = "商户名称")
	private String mer_name;
	
	@Excel(name = "联系人")
	private String name;
	
	@Excel(name = "联系电话")
	private String tel;
	
	@Excel(name = "返现返现状态")
	private String cash_back_status;


	@Excel(name = "返现金额")
	private String cash_back_money;

	@Excel(name = "实际返现金额")
	private String real_cash_back_money;

	@Excel(name = "刷卡费率")
	private String credit_card_rate;

	@Excel(name = "云闪付费率")
	private String cloud_flash_rate;

	@Excel(name = "激活状态")
	private String act_status;

	@Excel(name = "激活日期")
	private String act_date;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMer_name() {
		return mer_name;
	}

	public void setMer_name(String mer_name) {
		this.mer_name = mer_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCash_back_status() {
		return cash_back_status;
	}

	public void setCash_back_status(String cash_back_status) {
		this.cash_back_status = cash_back_status;
	}

	public String getCash_back_money() {
		return cash_back_money;
	}

	public void setCash_back_money(String cash_back_money) {
		this.cash_back_money = cash_back_money;
	}

	public String getReal_cash_back_money() {
		return real_cash_back_money;
	}

	public void setReal_cash_back_money(String real_cash_back_money) {
		this.real_cash_back_money = real_cash_back_money;
	}

	public String getCredit_card_rate() {
		return credit_card_rate;
	}

	public void setCredit_card_rate(String credit_card_rate) {
		this.credit_card_rate = credit_card_rate;
	}

	public String getCloud_flash_rate() {
		return cloud_flash_rate;
	}

	public void setCloud_flash_rate(String cloud_flash_rate) {
		this.cloud_flash_rate = cloud_flash_rate;
	}

	public String getAct_status() {
		return act_status;
	}

	public void setAct_status(String act_status) {
		this.act_status = act_status;
	}

	public String getAct_date() {
		return act_date;
	}

	public void setAct_date(String act_date) {
		this.act_date = act_date;
	}
}
