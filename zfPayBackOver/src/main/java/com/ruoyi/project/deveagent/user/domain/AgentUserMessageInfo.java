package com.ruoyi.project.deveagent.user.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_message_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMessageInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 收益订单号 */
	@Excel(name = "收益订单号")
	private String order_id;
	
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
	
	/** 收益类型 */
	@Excel(name = "收益类型", readConverterExp = "01=结算分润,02=单笔分润,03=机器返现,04=活动奖励")
	private String op_type;
	
	/** 收益金额 */
	@Excel(name = "收益金额")
	private String money;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 读取状态 */
	@Excel(name = "读取状态", readConverterExp = "0=未读,1=已读")
	private String read_status;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;

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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getRead_status() {
		return read_status;
	}

	public void setRead_status(String read_status) {
		this.read_status = read_status;
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
		return "AgentUserMessageInfo [id=" + id + ", order_id=" + order_id + ", manager_id=" + manager_id + ", user_id="
				+ user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", op_type=" + op_type + ", money="
				+ money + ", sn=" + sn + ", read_status=" + read_status + ", cre_date=" + cre_date + "]";
	}
	
}
