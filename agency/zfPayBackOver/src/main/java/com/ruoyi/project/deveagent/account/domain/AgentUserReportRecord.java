package com.ruoyi.project.deveagent.account.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 sys_user_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserReportRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 代理账号id */
	@Excel(name = "代理账号id")
	private String account_id;
	
	/** 代理账号 */
	@Excel(name = "代理账号")
	private String agent_account;
	
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

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAgent_account() {
		return agent_account;
	}

	public void setAgent_account(String agent_account) {
		this.agent_account = agent_account;
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

	@Override
	public String toString() {
		return "AgentUserReportRecord [id=" + id + ", manager_id=" + manager_id + ", account_id=" + account_id
				+ ", agent_account=" + agent_account + ", cre_date=" + cre_date + ", create_by=" + create_by
				+ ", remark=" + remark + "]";
	}

}
