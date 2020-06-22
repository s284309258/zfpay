package com.ruoyi.project.deveagent.account.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 sys_user_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserReportRecordDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 编号 */
	@Excel(name = "记录编号")
	private String record_id;
	
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
	
	/** 代理账号 */
	@Excel(name = "代理账号")
	private String agent_account;
	
	/** 子级代理账户 */
	@Excel(name = "子级代理账户")
	private String sub_agent_account;
	
	/** 子级代理名称 */
	@Excel(name = "子级代理名称")
	private String sub_agent_name;
	
	/** 子级代理身份证号 */
	@Excel(name = "子级代理身份证号")
	private String sub_agent_id_num;
	
	/** 子级代理身份证图片Base64 */
	@Excel(name = "子级代理身份证图片Base64")
	private String sub_agent_id_img;
	
	/** 子级代理结算账号 */
	@Excel(name = "子级代理结算账号")
	private String sub_agent_sett_account;
	
	/** 报备信息标识 */
	@Excel(name = "报备信息标识")
	private String is_report;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 创建人 */
	@Excel(name = "创建人")
	private String create_by;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getAgent_account() {
		return agent_account;
	}

	public void setAgent_account(String agent_account) {
		this.agent_account = agent_account;
	}

	public String getSub_agent_account() {
		return sub_agent_account;
	}

	public void setSub_agent_account(String sub_agent_account) {
		this.sub_agent_account = sub_agent_account;
	}

	public String getSub_agent_name() {
		return sub_agent_name;
	}

	public void setSub_agent_name(String sub_agent_name) {
		this.sub_agent_name = sub_agent_name;
	}

	public String getSub_agent_id_num() {
		return sub_agent_id_num;
	}

	public void setSub_agent_id_num(String sub_agent_id_num) {
		this.sub_agent_id_num = sub_agent_id_num;
	}

	public String getSub_agent_sett_account() {
		return sub_agent_sett_account;
	}

	public void setSub_agent_sett_account(String sub_agent_sett_account) {
		this.sub_agent_sett_account = sub_agent_sett_account;
	}

	public String getIs_report() {
		return is_report;
	}

	public void setIs_report(String is_report) {
		this.is_report = is_report;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSub_agent_id_img() {
		return sub_agent_id_img;
	}

	public void setSub_agent_id_img(String sub_agent_id_img) {
		this.sub_agent_id_img = sub_agent_id_img;
	}

	@Override
	public String toString() {
		return "AgentUserReportRecordDetail [id=" + id + ", record_id=" + record_id + ", manager_id=" + manager_id
				+ ", user_id=" + user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", agent_account="
				+ agent_account + ", sub_agent_account=" + sub_agent_account + ", sub_agent_name=" + sub_agent_name
				+ ", sub_agent_id_num=" + sub_agent_id_num + ", sub_agent_id_img=" + sub_agent_id_img
				+ ", sub_agent_sett_account=" + sub_agent_sett_account + ", is_report=" + is_report + ", cre_date="
				+ cre_date + ", create_by=" + create_by + ", remark=" + remark + "]";
	}
	
}
