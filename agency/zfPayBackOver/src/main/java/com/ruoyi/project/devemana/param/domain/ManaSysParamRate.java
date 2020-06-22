package com.ruoyi.project.devemana.param.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 sys_user_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class ManaSysParamRate extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 类型  */
	@Excel(name = "类型", readConverterExp = "1=刷卡费率,2=刷卡结算价,3=云闪付结算价,4=云闪付费率,5=微信结算价,6=微信费率,7=支付宝结算价,8=支付宝费率,9=单笔分润,10=机器返现")
	private String type;
	
	/** 费率（单位%） */
	@Excel(name = "费率（单位%）")
	private String rate;
	
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
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ManaSysParamRate [id=" + id + ", type=" + type + ", rate=" + rate + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + ", create_by=" + create_by + ", update_by=" + update_by + ", remark="
				+ remark + "]";
	}
	
}
