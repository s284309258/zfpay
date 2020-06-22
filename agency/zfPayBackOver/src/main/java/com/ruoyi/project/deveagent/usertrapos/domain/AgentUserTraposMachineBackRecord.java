package com.ruoyi.project.deveagent.usertrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTraposMachineBackRecord extends BaseEntity
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
	
	/** 冻结时间 */
	@Excel(name = "冻结时间")
	private String frozen_time;

	/** 返现金额 */
	@Excel(name = "返现金额")
	private String money;
	
	/** 参考号 */
	@Excel(name = "参考号")
	private String tran_ref_code;
	
	/** 返现金额 */
	@Excel(name = "返现金额")
	private String return_amt;
	
	/** 返现比例 */
	@Excel(name = "返现比例（%）")
	private String cash_back_rate;
	
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getFrozen_time() {
		return frozen_time;
	}

	public void setFrozen_time(String frozen_time) {
		this.frozen_time = frozen_time;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getTran_ref_code() {
		return tran_ref_code;
	}

	public void setTran_ref_code(String tran_ref_code) {
		this.tran_ref_code = tran_ref_code;
	}

	public String getReturn_amt() {
		return return_amt;
	}

	public void setReturn_amt(String return_amt) {
		this.return_amt = return_amt;
	}

	public String getCash_back_rate() {
		return cash_back_rate;
	}

	public void setCash_back_rate(String cash_back_rate) {
		this.cash_back_rate = cash_back_rate;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTraposMachineBackRecord [id=" + id + ", order_id=" + order_id + ", manager_id=" + manager_id
				+ ", user_id=" + user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", sn=" + sn
				+ ", frozen_time=" + frozen_time + ", money=" + money + ", tran_ref_code=" + tran_ref_code
				+ ", return_amt=" + return_amt + ", cash_back_rate=" + cash_back_rate + ", cre_date=" + cre_date + "]";
	}

}
