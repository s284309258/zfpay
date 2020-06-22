package com.ruoyi.project.deveagent.usermpos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_message_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserApplyCardrateMposRecord extends BaseEntity
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
	
	/** 旧刷卡费率 */
	@Excel(name = "旧刷卡费率（%）")
	private String credit_card_rate_old;
	
	/** 新刷卡费率 */
	@Excel(name = "新刷卡费率（%）")
	private String credit_card_rate_new;
	
	/** 刷卡结算底价 */
	@Excel(name = "刷卡结算底价（%）")
	private String card_settle_price;
	
	/** 申请状态 */
	@Excel(name = "申请状态", readConverterExp = "00=待处理,08=拒绝,09=通过")
	private String status;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;

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

	public String getCredit_card_rate_old() {
		return credit_card_rate_old;
	}

	public void setCredit_card_rate_old(String credit_card_rate_old) {
		this.credit_card_rate_old = credit_card_rate_old;
	}

	public String getCredit_card_rate_new() {
		return credit_card_rate_new;
	}

	public void setCredit_card_rate_new(String credit_card_rate_new) {
		this.credit_card_rate_new = credit_card_rate_new;
	}

	public String getCard_settle_price() {
		return card_settle_price;
	}

	public void setCard_settle_price(String card_settle_price) {
		this.card_settle_price = card_settle_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserApplyCardrateMposRecord [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", sn=" + sn + ", credit_card_rate_old="
				+ credit_card_rate_old + ", credit_card_rate_new=" + credit_card_rate_new + ", card_settle_price="
				+ card_settle_price + ", status=" + status + ", remark=" + remark + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + ", update_by=" + update_by + "]";
	}

}
