package com.ruoyi.project.deveagent.user.domain;

import java.math.BigDecimal;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 流水详情对象
 * @author Administrator
 *
 */
public class AgentBenefitRecordMoney {
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 流水类型编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 用户手机号 */
	@Excel(name = "用户手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 业务类型 */
	@Excel(name = "业务类型")
	private String op_type;
	
	/** 业务类型名称 */
	@Excel(name = "业务类型名称")
	private String op_name;
	
	/** 业务订单号" */
	@Excel(name = "业务订单号")
	private String op_order_id;
	
	/** 归属类型" */
	@Excel(name = "归属类型", readConverterExp = "0=上级,1=直营")
	private String state_type;

	/** POS机类型" */
	@Excel(name = "POS机类型", readConverterExp = "01=传统POS,02=MPOS")
	private String pos_type;
	
	/** 变动前金额 */
	@Excel(name = "变动前金额")
	private BigDecimal before_money;
	
	/** 变动金额 */
	@Excel(name = "变动金额")
	private String money;
	
	/** 变动后金额 */
	@Excel(name = "变动后金额")
	private String after_money;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public String getOp_order_id() {
		return op_order_id;
	}

	public void setOp_order_id(String op_order_id) {
		this.op_order_id = op_order_id;
	}

	public String getState_type() {
		return state_type;
	}

	public void setState_type(String state_type) {
		this.state_type = state_type;
	}

	public String getPos_type() {
		return pos_type;
	}

	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}

	public BigDecimal getBefore_money() {
		return before_money;
	}

	public void setBefore_money(BigDecimal before_money) {
		this.before_money = before_money;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getAfter_money() {
		return after_money;
	}

	public void setAfter_money(String after_money) {
		this.after_money = after_money;
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
		return "AgentBenefitRecordMoney [id=" + id + ", user_id=" + user_id + ", user_tel=" + user_tel + ", real_name="
				+ real_name + ", op_type=" + op_type + ", op_name=" + op_name + ", op_order_id=" + op_order_id
				+ ", state_type=" + state_type + ", pos_type=" + pos_type + ", before_money=" + before_money
				+ ", money=" + money + ", after_money=" + after_money + ", cre_date=" + cre_date + "]";
	}

	
}
