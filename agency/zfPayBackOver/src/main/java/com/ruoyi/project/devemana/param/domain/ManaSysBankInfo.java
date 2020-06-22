package com.ruoyi.project.devemana.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 银行基础信息表： t_sys_bank_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class ManaSysBankInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 银行代码 */
	@Excel(name = "银行代码")
	private String bank_code;
	
	/** 银行名称 */
	@Excel(name = "银行名称")
	private String bank_name;
	
	/** 状态 */
	@Excel(name = "状态", readConverterExp = "0=不支持,1=支持")
	private String status;

	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 创建者 */
	@Excel(name = "创建者")
	private String create_by;
	
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
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

	@Override
	public String toString() {
		return "ManaSysBankInfo [id=" + id + ", bank_code=" + bank_code + ", bank_name=" + bank_name + ", status="
				+ status + ", cre_date=" + cre_date + ", up_date=" + up_date + ", create_by=" + create_by
				+ ", update_by=" + update_by + "]";
	}
	
}
