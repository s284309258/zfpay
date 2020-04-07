package com.example.longecological.entity.cash;

import java.io.Serializable;
import java.util.List;

/**
 * 取现记录
 * @author Administrator
 *
 */
public class CashRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//提现编号
	private String cash_id; 
	
	//订单号
	private String order_id; 
	
	//用户编号
	private String user_id;
	
	//银行卡号
	private String account; 
	
	//提现总额
	private String cash_money; 
	
	//实际到账
	private String cash_actual_money; 
	
	//所得税比例
	private String feet_rate; 
	
	//所得税金额
	private String rate_feet_money; 
	
	//单笔提现费
	private String single_feet_money; 
	
	//考核未达标
	private String deduct_money; 
	
	//提现状态
	private String status; 
	
	//提现时间
	private String cre_date; 
	
	//提现记录详情列表
	private List<CashRecordDetail> cashRecordDetailList;

	public String getCash_id() {
		return cash_id;
	}

	public void setCash_id(String cash_id) {
		this.cash_id = cash_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public List<CashRecordDetail> getCashRecordDetailList() {
		return cashRecordDetailList;
	}

	public void setCashRecordDetailList(List<CashRecordDetail> cashRecordDetailList) {
		this.cashRecordDetailList = cashRecordDetailList;
	}

	@Override
	public String toString() {
		return "CashRecord [cash_id=" + cash_id + ", order_id=" + order_id + ", user_id=" + user_id + ", account="
				+ account + ", cash_money=" + cash_money + ", cash_actual_money=" + cash_actual_money + ", feet_rate="
				+ feet_rate + ", rate_feet_money=" + rate_feet_money + ", single_feet_money=" + single_feet_money
				+ ", deduct_money=" + deduct_money + ", status=" + status + ", cre_date=" + cre_date
				+ ", cashRecordDetailList=" + cashRecordDetailList + "]";
	}
	
}