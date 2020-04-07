package com.ruoyi.project.deveagent.usermpos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMposInfo extends BaseEntity
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
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 刷卡结算底价 */
	@Excel(name = "刷卡结算底价（%）")
	private String card_settle_price;
	
	/** 云闪付结算底价 */
	@Excel(name = "云闪付结算底价（%）")
	private String cloud_settle_price;
	
	/** 单笔分润比例 */
	@Excel(name = "单笔分润比例（%）")
	private String single_profit_rate;
	
	/** 返现比例 */
	@Excel(name = "返现比例（%）")
	private String cash_back_rate;
	
	/** 归属状态 */
	@Excel(name = "归属状态", readConverterExp = "0=上级,1=直属", type = Type.EXPORT)
	private String state_status;
	
	/** 是否参与线上活动  */
	@Excel(name = "是否参与线上活动", readConverterExp = "0=否,1=是", type = Type.EXPORT)
	private String activity_status;
	
	/** 删除状态 */
	@Excel(name = "删除状态", readConverterExp = "0=未删除,1=已删除", type = Type.EXPORT)
	private String del;
	
	/** 是否有交易 */
	@Excel(name = "是否有交易", readConverterExp = "0=否,1=是", type = Type.EXPORT)
	private String trade_status;
	
	/** POS机编号 */
	@Excel(name = "POS机编号", type = Type.EXPORT)
	private String pos_id;
	
	/** 商户号 */
	@Excel(name = "商户号", type = Type.EXPORT)
	private String mer_id;
	
	/** 商户名称 */
	@Excel(name = "商户名称", type = Type.EXPORT)
	private String mer_name;
	
	/** 商户姓名 */
	@Excel(name = "商户姓名", type = Type.EXPORT)
	private String name;
	
	/** 商户手机号 */
	@Excel(name = "商户手机号", type = Type.EXPORT)
	private String tel;
	
	/** 返现获取类型 */
	@Excel(name = "返现获取类型", readConverterExp = "0=设置,1=中付", type = Type.EXPORT)
	private String cash_back_type;
	
	/** 返现条件 */
	@Excel(name = "返现条件", type = Type.EXPORT)
	private String cash_back_condition;
	
	/** 返现金额 */
	@Excel(name = "返现金额", type = Type.EXPORT)
	private String cash_back_money;
	
	/** 实际返现金额 */
	@Excel(name = "实际返现金额", type = Type.EXPORT)
	private String real_cash_back_money;
	
	/** 返现状态 */
	@Excel(name = "返现状态", readConverterExp = "0=未返现,1=已返现", type = Type.EXPORT)
	private String cash_back_status;
	
	/** 刷卡费率（%） */
	@Excel(name = "刷卡费率（%）", type = Type.EXPORT)
	private String credit_card_rate;
	
	/** 云闪付费率（%） */
	@Excel(name = "云闪付费率（%）", type = Type.EXPORT)
	private String cloud_flash_rate;
	
	/** 激活状态 */
	@Excel(name = "激活状态", readConverterExp = "0=未激活,1=已激活", type = Type.EXPORT)
	private String act_status;
	
	/** 激活时所属用户  */
	@Excel(name = "激活时所属用户", type = Type.EXPORT)
	private String act_user_id;
	
	/** 激活时间 */
	@Excel(name = "激活时间", type = Type.EXPORT)
	private String act_date;
	
	/** 交易时间 */
	@Excel(name = "交易时间", type = Type.EXPORT)
	private String trade_date;
	
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCard_settle_price() {
		return card_settle_price;
	}

	public void setCard_settle_price(String card_settle_price) {
		this.card_settle_price = card_settle_price;
	}

	public String getCloud_settle_price() {
		return cloud_settle_price;
	}

	public void setCloud_settle_price(String cloud_settle_price) {
		this.cloud_settle_price = cloud_settle_price;
	}

	public String getSingle_profit_rate() {
		return single_profit_rate;
	}

	public void setSingle_profit_rate(String single_profit_rate) {
		this.single_profit_rate = single_profit_rate;
	}

	public String getCash_back_rate() {
		return cash_back_rate;
	}

	public void setCash_back_rate(String cash_back_rate) {
		this.cash_back_rate = cash_back_rate;
	}

	public String getState_status() {
		return state_status;
	}

	public void setState_status(String state_status) {
		this.state_status = state_status;
	}

	public String getActivity_status() {
		return activity_status;
	}

	public void setActivity_status(String activity_status) {
		this.activity_status = activity_status;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getPos_id() {
		return pos_id;
	}

	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}

	public String getMer_id() {
		return mer_id;
	}

	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public String getMer_name() {
		return mer_name;
	}

	public void setMer_name(String mer_name) {
		this.mer_name = mer_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCash_back_type() {
		return cash_back_type;
	}

	public void setCash_back_type(String cash_back_type) {
		this.cash_back_type = cash_back_type;
	}

	public String getCash_back_condition() {
		return cash_back_condition;
	}

	public void setCash_back_condition(String cash_back_condition) {
		this.cash_back_condition = cash_back_condition;
	}

	public String getCash_back_money() {
		return cash_back_money;
	}

	public void setCash_back_money(String cash_back_money) {
		this.cash_back_money = cash_back_money;
	}

	public String getCash_back_status() {
		return cash_back_status;
	}

	public void setCash_back_status(String cash_back_status) {
		this.cash_back_status = cash_back_status;
	}

	public String getCredit_card_rate() {
		return credit_card_rate;
	}

	public void setCredit_card_rate(String credit_card_rate) {
		this.credit_card_rate = credit_card_rate;
	}

	public String getCloud_flash_rate() {
		return cloud_flash_rate;
	}

	public void setCloud_flash_rate(String cloud_flash_rate) {
		this.cloud_flash_rate = cloud_flash_rate;
	}

	public String getAct_status() {
		return act_status;
	}

	public void setAct_status(String act_status) {
		this.act_status = act_status;
	}

	public String getAct_user_id() {
		return act_user_id;
	}

	public void setAct_user_id(String act_user_id) {
		this.act_user_id = act_user_id;
	}

	public String getAct_date() {
		return act_date;
	}

	public void setAct_date(String act_date) {
		this.act_date = act_date;
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

	public String getCre_time() {
		return cre_time;
	}

	public void setCre_time(String cre_time) {
		this.cre_time = cre_time;
	}

	public String getUp_time() {
		return up_time;
	}

	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}

	public String getTrade_date() {
		return trade_date;
	}

	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}

	public String getReal_cash_back_money() {
		return real_cash_back_money;
	}

	public void setReal_cash_back_money(String real_cash_back_money) {
		this.real_cash_back_money = real_cash_back_money;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserMposInfo [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id + ", user_tel="
				+ user_tel + ", real_name=" + real_name + ", sn=" + sn + ", card_settle_price=" + card_settle_price
				+ ", cloud_settle_price=" + cloud_settle_price + ", single_profit_rate=" + single_profit_rate
				+ ", cash_back_rate=" + cash_back_rate + ", state_status=" + state_status + ", activity_status="
				+ activity_status + ", del=" + del + ", trade_status=" + trade_status + ", pos_id=" + pos_id
				+ ", mer_id=" + mer_id + ", mer_name=" + mer_name + ", name=" + name + ", tel=" + tel
				+ ", cash_back_type=" + cash_back_type + ", cash_back_condition=" + cash_back_condition
				+ ", cash_back_money=" + cash_back_money + ", real_cash_back_money=" + real_cash_back_money
				+ ", cash_back_status=" + cash_back_status + ", credit_card_rate=" + credit_card_rate
				+ ", cloud_flash_rate=" + cloud_flash_rate + ", act_status=" + act_status + ", act_user_id="
				+ act_user_id + ", act_date=" + act_date + ", trade_date=" + trade_date + ", cre_date=" + cre_date
				+ ", cre_time=" + cre_time + ", up_date=" + up_date + ", up_time=" + up_time + ", remark=" + remark
				+ ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}

}
