package com.example.longecological.entity.cash;

import java.io.Serializable;

/**
 * 取现记录详情
 * @author Administrator
 *
 */
public class CashRecordDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//提现详情编号
	private String cash_detail_id; 
	
	//提现编号
	private String cash_id; 
	
	//提现状态
	private String cash_status;
	
	//备注
	private String note; 
	
	//提现时间
	private String cre_date;

	public String getCash_detail_id() {
		return cash_detail_id;
	}

	public void setCash_detail_id(String cash_detail_id) {
		this.cash_detail_id = cash_detail_id;
	}

	public String getCash_id() {
		return cash_id;
	}

	public void setCash_id(String cash_id) {
		this.cash_id = cash_id;
	}

	public String getCash_status() {
		return cash_status;
	}

	public void setCash_status(String cash_status) {
		this.cash_status = cash_status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	} 
	
}