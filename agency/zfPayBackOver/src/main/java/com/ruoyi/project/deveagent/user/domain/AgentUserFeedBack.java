package com.ruoyi.project.deveagent.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_feedback
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserFeedBack extends BaseEntity
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
	
	/** 意见反馈标题 */
	@Excel(name = "意见反馈标题")
	private String feedback_title;

	/** 意见反馈内容 */
	@Excel(name = "意见反馈内容")
	private String feedback_content;
	
	/** 意见反馈图片 */
	@Excel(name = "意见反馈图片")
	private String feedback_img;
	
	/** 联系方式 */
	@Excel(name = "联系方式")
	private String contact_way;
	
	/** 反馈回复 */
	@Excel(name = "反馈回复")
	private String feedback_answer;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getFeedback_title() {
		return feedback_title;
	}

	public void setFeedback_title(String feedback_title) {
		this.feedback_title = feedback_title;
	}

	public String getFeedback_content() {
		return feedback_content;
	}

	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}

	public String getFeedback_img() {
		return feedback_img;
	}

	public void setFeedback_img(String feedback_img) {
		this.feedback_img = feedback_img;
	}

	public String getContact_way() {
		return contact_way;
	}

	public void setContact_way(String contact_way) {
		this.contact_way = contact_way;
	}

	public String getFeedback_answer() {
		return feedback_answer;
	}

	public void setFeedback_answer(String feedback_answer) {
		this.feedback_answer = feedback_answer;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserFeedBack [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id + ", user_tel="
				+ user_tel + ", real_name=" + real_name + ", feedback_title=" + feedback_title + ", feedback_content="
				+ feedback_content + ", feedback_img=" + feedback_img + ", contact_way=" + contact_way
				+ ", feedback_answer=" + feedback_answer + ", cre_date=" + cre_date + "]";
	}

}
