package com.ruoyi.project.deveagent.user.domain;


import java.math.BigDecimal;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 代数 */
	@Excel(name = "代数")
	private String rank;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 身份证号 */
	@Excel(name = "身份证号")
	private String id_card;
	
	/** 证件照 */
	@Excel(name = "证件照")
	private String card_photo;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;

	/** 账户余额 */
	@Excel(name = "账户余额")
	private BigDecimal money;
	
	/** 到账金额 */
	@Excel(name = "到账金额")
	private BigDecimal settle_money;
	
	/** 扣除金额 */
	@Excel(name = "扣除金额")
	private BigDecimal deduct_money;
	
	/** 今日收益 */
	@Excel(name = "今日收益")
	private BigDecimal today_benefit;
	
	/** 累计收益 */
	@Excel(name = "累计收益")
	private BigDecimal total_benefit;
	
	/** 结算单笔手续费 */
	@Excel(name = "结算单笔手续费")
	private BigDecimal settle_single_feet_money;
	
	/** 结算比例手续费 */
	@Excel(name = "结算比例手续费")
	private BigDecimal single_rate_feet_money;

	/** 头像 */
	@Excel(name = "头像")
	private String head_photo;
	
	/** 推荐人编号 */
	@Excel(name = "推荐人编号")
	private String referer_id;
	
	/** 推荐人手机号 */
	@Excel(name = "推荐人手机号")
	private String referer_user_tel;
	
	/** 推荐人姓名 */
	@Excel(name = "推荐人姓名")
	private String referer_real_name;
	
	/** 推荐人头像 */
	@Excel(name = "推荐人头像")
	private String referer_head_photo;
	
	/** 状态  */
	@Excel(name = "状态", readConverterExp = "0=可用,1=冻结")
	private String status;
	
	/** 认证状态  */
	@Excel(name = "认证状态", readConverterExp = "00=待认证,04=待审核,08=认证失败,09=认证成功")
	private String auth_status;
	
	/** 报备状态  */
	@Excel(name = "报备状态", readConverterExp = "0=未报备,1=已报备")
	private String report_status;
	
	/** 直推人数 */
	@Excel(name = "直推人数")
	private String referer_num;
	
	/** 团队人数 */
	@Excel(name = "团队人数")
	private String under_num;
	
	/** 代数 */
	@Excel(name = "代数")
	private String algebra;
	
	/** 父级链 */
	@Excel(name = "父级链")
	private String parent_chain;
	
	/** 设备类型  */
	@Excel(name = "设备类型")
	private String device_type;
	
	/** 设备号 */
	@Excel(name = "设备号")
	private String device_no;
	
	/** 版本号 */
	@Excel(name = "版本号")
	private String version_no;
	
	/** 注册时间 */
	@Excel(name = "注册时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 最后登录ip */
	@Excel(name = "最后登录ip")
	private String last_login_ip;
	
	/** 最后登录时间 */
	@Excel(name = "最后登录时间")
	private String last_login_date;

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

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getCard_photo() {
		return card_photo;
	}

	public void setCard_photo(String card_photo) {
		this.card_photo = card_photo;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getSettle_money() {
		return settle_money;
	}

	public void setSettle_money(BigDecimal settle_money) {
		this.settle_money = settle_money;
	}

	public BigDecimal getDeduct_money() {
		return deduct_money;
	}

	public void setDeduct_money(BigDecimal deduct_money) {
		this.deduct_money = deduct_money;
	}

	public BigDecimal getToday_benefit() {
		return today_benefit;
	}

	public void setToday_benefit(BigDecimal today_benefit) {
		this.today_benefit = today_benefit;
	}

	public BigDecimal getTotal_benefit() {
		return total_benefit;
	}

	public void setTotal_benefit(BigDecimal total_benefit) {
		this.total_benefit = total_benefit;
	}

	public BigDecimal getSettle_single_feet_money() {
		return settle_single_feet_money;
	}

	public void setSettle_single_feet_money(BigDecimal settle_single_feet_money) {
		this.settle_single_feet_money = settle_single_feet_money;
	}

	public BigDecimal getSingle_rate_feet_money() {
		return single_rate_feet_money;
	}

	public void setSingle_rate_feet_money(BigDecimal single_rate_feet_money) {
		this.single_rate_feet_money = single_rate_feet_money;
	}

	public String getHead_photo() {
		return head_photo;
	}

	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}

	public String getReferer_id() {
		return referer_id;
	}

	public void setReferer_id(String referer_id) {
		this.referer_id = referer_id;
	}

	public String getReferer_user_tel() {
		return referer_user_tel;
	}

	public void setReferer_user_tel(String referer_user_tel) {
		this.referer_user_tel = referer_user_tel;
	}

	public String getReferer_real_name() {
		return referer_real_name;
	}

	public void setReferer_real_name(String referer_real_name) {
		this.referer_real_name = referer_real_name;
	}

	public String getReferer_head_photo() {
		return referer_head_photo;
	}

	public void setReferer_head_photo(String referer_head_photo) {
		this.referer_head_photo = referer_head_photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}

	public String getReferer_num() {
		return referer_num;
	}

	public void setReferer_num(String referer_num) {
		this.referer_num = referer_num;
	}

	public String getUnder_num() {
		return under_num;
	}

	public void setUnder_num(String under_num) {
		this.under_num = under_num;
	}

	public String getAlgebra() {
		return algebra;
	}

	public void setAlgebra(String algebra) {
		this.algebra = algebra;
	}

	public String getParent_chain() {
		return parent_chain;
	}

	public void setParent_chain(String parent_chain) {
		this.parent_chain = parent_chain;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
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

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getReport_status() {
		return report_status;
	}

	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}

	@Override
	public String toString() {
		return "AgentUserInfo [id=" + id + ", manager_id=" + manager_id + ", rank=" + rank + ", user_tel=" + user_tel
				+ ", id_card=" + id_card + ", card_photo=" + card_photo + ", real_name=" + real_name + ", money="
				+ money + ", settle_money=" + settle_money + ", deduct_money=" + deduct_money + ", today_benefit="
				+ today_benefit + ", total_benefit=" + total_benefit + ", settle_single_feet_money="
				+ settle_single_feet_money + ", single_rate_feet_money=" + single_rate_feet_money + ", head_photo="
				+ head_photo + ", referer_id=" + referer_id + ", referer_user_tel=" + referer_user_tel
				+ ", referer_real_name=" + referer_real_name + ", referer_head_photo=" + referer_head_photo
				+ ", status=" + status + ", auth_status=" + auth_status + ", report_status=" + report_status
				+ ", referer_num=" + referer_num + ", under_num=" + under_num + ", algebra=" + algebra
				+ ", parent_chain=" + parent_chain + ", device_type=" + device_type + ", device_no=" + device_no
				+ ", version_no=" + version_no + ", cre_date=" + cre_date + ", up_date=" + up_date + ", last_login_ip="
				+ last_login_ip + ", last_login_date=" + last_login_date + "]";
	}

}
