package com.ruoyi.project.deveagent.usertrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserApplyScanRecordInfo extends BaseEntity
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
	
	/** 用户手机号 */
	@Excel(name = "用户手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 营业执照 */
	@Excel(name = "营业执照")
	private String business_license;
	
	/** 店铺内景 */
	@Excel(name = "店铺内景")
	private String store_interior;
	
	/** 店铺门头 */
	@Excel(name = "店铺门头")
	private String shop_head;
	
	/** 收银台 */
	@Excel(name = "收银台")
	private String cashier_desk;
	
	/** 申请状态 */
	@Excel(name = "申请状态", readConverterExp = "00=待处理,08=不通过,09=通过")
	private String status;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
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

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	public String getStore_interior() {
		return store_interior;
	}

	public void setStore_interior(String store_interior) {
		this.store_interior = store_interior;
	}

	public String getShop_head() {
		return shop_head;
	}

	public void setShop_head(String shop_head) {
		this.shop_head = shop_head;
	}

	public String getCashier_desk() {
		return cashier_desk;
	}

	public void setCashier_desk(String cashier_desk) {
		this.cashier_desk = cashier_desk;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "AgentUserApplyScanRecordInfo [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", sn=" + sn + ", business_license="
				+ business_license + ", store_interior=" + store_interior + ", shop_head=" + shop_head
				+ ", cashier_desk=" + cashier_desk + ", status=" + status + ", cre_date=" + cre_date + ", up_date="
				+ up_date + ", remark=" + remark + ", update_by=" + update_by + "]";
	}
	
}
