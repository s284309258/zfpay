package com.ruoyi.project.deveagent.activitympos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_message_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMposAssessInfo extends BaseEntity
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
	
	/** 活动名称 */
	@Excel(name = "活动名称")
	private String assess_name;
	
	/** 活动类型 */
	@Excel(name = "活动类型", readConverterExp = "01=活动A 高返现,02=活动B 交易量")
	private String assess_type;
	
	/** 状态 */
	@Excel(name = "状态", readConverterExp = "00=待处理,09=已处理")
	private String status;
	
	/** 申请的SN列表 */
	@Excel(name = "申请的SN列表")
	private String sn_list;
	
	/** 扣除金额 */
	@Excel(name = "扣除金额")
	private String deduct_money;
	
	/** 交易额（万） */
	@Excel(name = "交易额（万）")
	private String expenditure;
	
	/** 旧刷卡费率 */
	@Excel(name = "开始日期")
	private String start_date;
	
	/** 新刷卡费率 */
	@Excel(name = "结束日期")
	private String end_date;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;
	
	/** 更新者 */
	@Excel(name = "创建者")
	private String create_by;

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

	public String getAssess_name() {
		return assess_name;
	}

	public void setAssess_name(String assess_name) {
		this.assess_name = assess_name;
	}

	public String getAssess_type() {
		return assess_type;
	}

	public void setAssess_type(String assess_type) {
		this.assess_type = assess_type;
	}

	public String getSn_list() {
		return sn_list;
	}

	public void setSn_list(String sn_list) {
		this.sn_list = sn_list;
	}

	public String getDeduct_money() {
		return deduct_money;
	}

	public void setDeduct_money(String deduct_money) {
		this.deduct_money = deduct_money;
	}

	public String getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserMposAssessInfo [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", assess_name=" + assess_name
				+ ", assess_type=" + assess_type + ", status=" + status + ", sn_list=" + sn_list + ", deduct_money="
				+ deduct_money + ", expenditure=" + expenditure + ", start_date=" + start_date + ", end_date="
				+ end_date + ", remark=" + remark + ", cre_date=" + cre_date + ", up_date=" + up_date + ", update_by="
				+ update_by + ", create_by=" + create_by + "]";
	}

}
