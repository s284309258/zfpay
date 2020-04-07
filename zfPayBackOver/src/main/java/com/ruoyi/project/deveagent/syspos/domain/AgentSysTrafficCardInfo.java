package com.ruoyi.project.deveagent.syspos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSysTrafficCardInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号", type = Type.EXPORT)
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号", type = Type.EXPORT)
	private String manager_id;
	
	/** 流量卡号 */
	@Excel(name = "流量卡号")
	private String card_no;
	
	/** 分配状态 */
	@Excel(name = "分配状态", readConverterExp = "0=未分配,1=已分配", type = Type.EXPORT)
	private String dis_status;
	
	/** 创建时间 */
	@Excel(name = "创建时间", type = Type.EXPORT)
	private String cre_date;
	
	/** 创建时间 */
	private String cre_time;
	
	/** 更新时间 */
	@Excel(name = "更新时间", type = Type.EXPORT)
	private String up_date;
	
	/** 创建时间 */
	private String up_time;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 创建者 */
	@Excel(name = "创建者", type = Type.EXPORT)
	private String create_by;
	
	/** 更新者 */
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

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getDis_status() {
		return dis_status;
	}

	public void setDis_status(String dis_status) {
		this.dis_status = dis_status;
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

	@Override
	public String toString() {
		return "AgentSysTrafficCardInfo [id=" + id + ", manager_id=" + manager_id + ", card_no=" + card_no
				+ ", dis_status=" + dis_status + ", cre_date=" + cre_date + ", cre_time=" + cre_time + ", up_date="
				+ up_date + ", up_time=" + up_time + ", remark=" + remark + ", create_by=" + create_by + ", update_by="
				+ update_by + "]";
	}
	
	
}
