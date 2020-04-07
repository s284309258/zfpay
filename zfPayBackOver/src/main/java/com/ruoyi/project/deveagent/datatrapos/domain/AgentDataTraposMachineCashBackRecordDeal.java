package com.ruoyi.project.deveagent.datatrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_mpos_trans_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentDataTraposMachineCashBackRecordDeal extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 商户号 */
	@Excel(name = "商户号")
	private String mer_id;
	
	/** 冻结时间 */
	@Excel(name = "冻结时间")
	private String frozen_time;
	
	/** 返现金额 */
	@Excel(name = "返现金额")
	private String return_amt;
	
	/** 政策名称 */
	@Excel(name = "政策名称")
	private String policy_name;
	
	/** 政策编码 */
	@Excel(name = "政策编码")
	private String policy_code;
	
	/** 代理总笔数 */
	@Excel(name = "是否首次激活 ", readConverterExp = "0=非首次,1=首次")
	private String is_first_activate;
	
	/** 处理状态  */
	@Excel(name = "处理状态 ", readConverterExp = "00=待处理,02=处理中,08=处理异常,09=已处理")
	private String status;
	
	/** 参考号 */
	@Excel(name = "参考号")
	private String tran_ref_code;
	
	/** 账号ID */
	@Excel(name = "账号ID")
	private String account_id;
	
	/** app_id */
	@Excel(name = "app_id")
	private String app_id;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getMer_id() {
		return mer_id;
	}

	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public String getFrozen_time() {
		return frozen_time;
	}

	public void setFrozen_time(String frozen_time) {
		this.frozen_time = frozen_time;
	}

	public String getReturn_amt() {
		return return_amt;
	}

	public void setReturn_amt(String return_amt) {
		this.return_amt = return_amt;
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

	public String getIs_first_activate() {
		return is_first_activate;
	}

	public void setIs_first_activate(String is_first_activate) {
		this.is_first_activate = is_first_activate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTran_ref_code() {
		return tran_ref_code;
	}

	public void setTran_ref_code(String tran_ref_code) {
		this.tran_ref_code = tran_ref_code;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
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

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	@Override
	public String toString() {
		return "AgentDataMposMachineCashBackRecordDeal [id=" + id + ", manager_id=" + manager_id + ", sn=" + sn
				+ ", mer_id=" + mer_id + ", frozen_time=" + frozen_time + ", return_amt=" + return_amt
				+ ", policy_name=" + policy_name + ", policy_code=" + policy_code + ", is_first_activate="
				+ is_first_activate + ", status=" + status + ", tran_ref_code=" + tran_ref_code + ", account_id="
				+ account_id + ", app_id=" + app_id + ", cre_date=" + cre_date + ", up_date=" + up_date + "]";
	}

}
