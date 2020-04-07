package com.ruoyi.project.deveagent.datampos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_mpos_trans_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentDataMposPolicyRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 冻结时间 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 政策名称 */
	@Excel(name = "政策名称")
	private String policy_name;
	
	/** 政策编码 */
	@Excel(name = "政策编码")
	private String policy_code;
	
	/** 账号编号 */
	@Excel(name = "账号编号")
	private String account_id;
	
	/** app_id */
	@Excel(name = "app_id")
	private String app_id;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;

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

	public String getPolicy_name() {
		return policy_name;
	}

	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}

	public String getPolicy_code() {
		return policy_code;
	}

	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	@Override
	public String toString() {
		return "AgentDataMposPolicyRecord [id=" + id + ", manager_id=" + manager_id + ", policy_name=" + policy_name
				+ ", policy_code=" + policy_code + ", account_id=" + account_id + ", app_id=" + app_id + ", cre_date="
				+ cre_date + "]";
	}
	
}
