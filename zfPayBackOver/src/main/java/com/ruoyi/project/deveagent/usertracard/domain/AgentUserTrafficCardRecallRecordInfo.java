package com.ruoyi.project.deveagent.usertracard.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTrafficCardRecallRecordInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 发起召回用户编号 */
	@Excel(name = "发起召回用户编号")
	private String send_user_id;
	
	/** 发起召回用户手机号 */
	@Excel(name = "发起召回用户手机号")
	private String send_user_tel;
	
	/** 发起召回用户姓名 */
	@Excel(name = "发起召回用户姓名")
	private String send_real_name;
	
	/** 接受召回用户编号 */
	@Excel(name = "接受召回用户编号")
	private String acce_user_id;
	
	/** 接受召回用户姓名 */
	@Excel(name = "接受召回用户姓名")
	private String acce_real_name;
	
	/** 接受召回用户手机号 */
	@Excel(name = "接受召回用户手机号")
	private String acce_user_tel;
	
	/** 流量卡号 */
	@Excel(name = "流量卡号")
	private String card_no;
	
	/** 召回状态 */
	@Excel(name = "召回状态", readConverterExp = "00=待处理,08=拒绝,09=同意")
	private String status;
	
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

	public String getSend_user_id() {
		return send_user_id;
	}

	public void setSend_user_id(String send_user_id) {
		this.send_user_id = send_user_id;
	}

	public String getSend_user_tel() {
		return send_user_tel;
	}

	public void setSend_user_tel(String send_user_tel) {
		this.send_user_tel = send_user_tel;
	}

	public String getAcce_user_id() {
		return acce_user_id;
	}

	public void setAcce_user_id(String acce_user_id) {
		this.acce_user_id = acce_user_id;
	}

	public String getAcce_user_tel() {
		return acce_user_tel;
	}

	public void setAcce_user_tel(String acce_user_tel) {
		this.acce_user_tel = acce_user_tel;
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

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getSend_real_name() {
		return send_real_name;
	}

	public void setSend_real_name(String send_real_name) {
		this.send_real_name = send_real_name;
	}

	public String getAcce_real_name() {
		return acce_real_name;
	}

	public void setAcce_real_name(String acce_real_name) {
		this.acce_real_name = acce_real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTrafficCardRecallRecordInfo [id=" + id + ", manager_id=" + manager_id + ", send_user_id="
				+ send_user_id + ", send_user_tel=" + send_user_tel + ", send_real_name=" + send_real_name
				+ ", acce_user_id=" + acce_user_id + ", acce_real_name=" + acce_real_name + ", acce_user_tel="
				+ acce_user_tel + ", card_no=" + card_no + ", status=" + status + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + "]";
	}

}
