package com.ruoyi.project.deveagent.account.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 sys_user_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserAccount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** app_id */
	@Excel(name = "app_id")
	private String app_id;
	
	/** app_key */
	@Excel(name = "app_key")
	private String app_key;
	
	/** app名称 */
	@Excel(name = "app名称")
	private String app_name;
	
	/** 状态  */
	@Excel(name = "启用状态", readConverterExp = "0=启用,1=停用")
	private String start;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 创建人 */
	@Excel(name = "创建人")
	private String create_by;
	
	/** 更新人 */
	@Excel(name = "更新人")
	private String update_by;
	
	/** 交易日期 */
	@Excel(name = "交易日期")
	private String tran_time;
	
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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

	public String getTran_time() {
		return tran_time;
	}

	public void setTran_time(String tran_time) {
		this.tran_time = tran_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	@Override
	public String toString() {
		return "AgentUserAccount [id=" + id + ", manager_id=" + manager_id + ", app_id=" + app_id + ", app_key="
				+ app_key + ", app_name=" + app_name + ", start=" + start + ", cre_date=" + cre_date + ", up_date="
				+ up_date + ", create_by=" + create_by + ", update_by=" + update_by + ", tran_time=" + tran_time
				+ ", remark=" + remark + "]";
	}
	
}
