package com.ruoyi.project.deveagent.activitytrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_message_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSysTraposActivityInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	
	/** 活动名称 */
	@Excel(name = "活动名称")
	private String activity_name;
	
	/** 活动类型 */
	@Excel(name = "活动类型", readConverterExp = "01=活动A 高返现,02=活动B 交易量")
	private String activity_type;
	
	/** 旧刷卡费率 */
	@Excel(name = "开始日期")
	private String start_date;
	
	/** 新刷卡费率 */
	@Excel(name = "结束日期")
	private String end_date;
	
	/** 封面图 */
	@Excel(name = "封面图")
	private String cover_url;
	
	/** 详情图 */
	@Excel(name = "详情图")
	private String detail_url;
	
	/** 状态 */
	@Excel(name = "状态", readConverterExp = "00=待发布,09=已发布（进行中）,09=活动已结束")
	private String status;
	
	/** 删除状态 */
	@Excel(name = "删除状态", readConverterExp = "0=未删除,1=已删除")
	private String del;
	
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

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
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

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
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

	@Override
	public String toString() {
		return "AgentSysMposActivityInfo [id=" + id + ", manager_id=" + manager_id + ", order_id=" + order_id
				+ ", activity_name=" + activity_name + ", activity_type=" + activity_type + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", cover_url=" + cover_url + ", detail_url=" + detail_url + ", status="
				+ status + ", del=" + del + ", remark=" + remark + ", cre_date=" + cre_date + ", up_date=" + up_date
				+ ", update_by=" + update_by + "]";
	}
	
}
