package com.ruoyi.project.devemana.notice.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 管理员=====新闻资讯表t_sys_news_info
 * 
 * @author ruoyi
 */
public class ManaSysNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻编号 */
    private String id;
    
    /** 新闻标题 */
    private String news_title;
    
    /** 新闻封面 */
    private String news_cover;
    
    /** 新闻导航 */
    private String news_nav;
    
    /** 新闻内容 */
    private String news_content;
    
    /** 浏览次数 */
    private String browse_num;
    
    /** 新闻状态（0正常 1关闭） */
    private String status;
    
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

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_cover() {
		return news_cover;
	}

	public void setNews_cover(String news_cover) {
		this.news_cover = news_cover;
	}

	public String getNews_nav() {
		return news_nav;
	}

	public void setNews_nav(String news_nav) {
		this.news_nav = news_nav;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getBrowse_num() {
		return browse_num;
	}

	public void setBrowse_num(String browse_num) {
		this.browse_num = browse_num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "ManaSysNews [id=" + id + ", news_title=" + news_title + ", news_cover=" + news_cover + ", news_nav="
				+ news_nav + ", news_content=" + news_content + ", browse_num=" + browse_num + ", status=" + status
				+ ", remark=" + remark + ", cre_date=" + cre_date + ", up_date=" + up_date + ", create_by=" + create_by
				+ ", update_by=" + update_by + "]";
	}
    
}
