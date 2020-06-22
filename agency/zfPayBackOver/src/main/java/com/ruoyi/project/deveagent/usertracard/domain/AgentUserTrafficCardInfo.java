package com.ruoyi.project.deveagent.usertracard.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTrafficCardInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号", type = Type.EXPORT)
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号", type = Type.EXPORT)
	private String manager_id;
	
	/** 用户编号 */
	@Excel(name = "用户编号", type = Type.EXPORT)
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号", type = Type.EXPORT)
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名", type = Type.EXPORT)
	private String real_name;
	
	/** 卡号 */
	@Excel(name = "流量卡号")
	private String card_no;
	
	/** 归属状态 */
	@Excel(name = "归属状态", readConverterExp = "0=上级,1=直属", type = Type.EXPORT)
	private String state_status;
	
	/** 删除状态 */
	@Excel(name = "删除状态", readConverterExp = "0=未删除,1=已删除", type = Type.EXPORT)
	private String del;
	
	/** 创建时间 */
	@Excel(name = "创建时间", type = Type.EXPORT)
	private String cre_date;
	
	private String cre_time;
	
	/** 更新时间 */
	@Excel(name = "更新时间", type = Type.EXPORT)
	private String up_date;
	
	private String up_time;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 创建者 */
	@Excel(name = "创建者", type = Type.EXPORT)
	private String create_by;
	
	/** 创建者 */
	@Excel(name = "更新者", type = Type.EXPORT)
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

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getState_status() {
		return state_status;
	}

	public void setState_status(String state_status) {
		this.state_status = state_status;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getCre_time() {
		return cre_time;
	}

	public void setCre_time(String cre_time) {
		this.cre_time = cre_time;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	public String getUp_time() {
		return up_time;
	}

	public void setUp_time(String up_time) {
		this.up_time = up_time;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTrafficCardInfo [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", card_no=" + card_no + ", state_status="
				+ state_status + ", del=" + del + ", cre_date=" + cre_date + ", cre_time=" + cre_time + ", up_date="
				+ up_date + ", up_time=" + up_time + ", remark=" + remark + ", create_by=" + create_by + ", update_by="
				+ update_by + "]";
	}
	
}
