package com.ruoyi.project.devemana.notice.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 管理员=====钱柜学院t_sys_money_locker_college_info
 * 
 * @author ruoyi
 */
public class ManaSysMoneyLockerCollege extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 钱柜编号 */
    private String id;
    
    /** 钱柜标题 */
    private String money_locker_title;
    
    /** 钱柜封面图 */
    private String money_locker_cover;
    
    /** 钱柜导航图或视频 */
    private String money_locker_nav;
    
    /** 钱柜详情图 */
    private String money_locker_content;
    
    /** 备注 */
    private String remark;
    
    /** 创建时间 */
    private String cre_date;
    
    /** 更新时间 */
    private String up_date;
    
    /** 创建人 */
    private String create_by;
    
    /** 更新人 */
    private String update_by;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoney_locker_title() {
		return money_locker_title;
	}

	public void setMoney_locker_title(String money_locker_title) {
		this.money_locker_title = money_locker_title;
	}

	public String getMoney_locker_cover() {
		return money_locker_cover;
	}

	public void setMoney_locker_cover(String money_locker_cover) {
		this.money_locker_cover = money_locker_cover;
	}

	public String getMoney_locker_nav() {
		return money_locker_nav;
	}

	public void setMoney_locker_nav(String money_locker_nav) {
		this.money_locker_nav = money_locker_nav;
	}

	public String getMoney_locker_content() {
		return money_locker_content;
	}

	public void setMoney_locker_content(String money_locker_content) {
		this.money_locker_content = money_locker_content;
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
		return "ManaSysMoneyLockerCollege [id=" + id + ", money_locker_title=" + money_locker_title
				+ ", money_locker_cover=" + money_locker_cover + ", money_locker_nav=" + money_locker_nav
				+ ", money_locker_content=" + money_locker_content + ", remark=" + remark + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}

}
