package com.ruoyi.project.deveagent.usertrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTraposShareBenefitRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	
	/** 代理编号 */
	@Excel(name = "代理编号")
	private String manager_id;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 交易金额 */
	@Excel(name = "交易金额")
	private String trans_amount;

	/** 交易类型 */
	@Excel(name = "交易类型", readConverterExp = "1=刷卡,2=快捷支付,3=微信,4=支付宝,5=银联二维码")
	private String trans_type;
	
	/** 卡类型 */
	@Excel(name = "卡类型", readConverterExp = "0=贷记卡,1=借记卡,2=境外卡,3=云闪付")
	private String card_type;
	
	/** 交易时间 */
	@Excel(name = "交易时间")
	private String trans_time;
	
	/** 收益类型 */
	@Excel(name = "收益类型", readConverterExp = "01=结算价,02=单笔")
	private String benefit_type;
	
	/** 归属类型  */
	@Excel(name = "归属类型", readConverterExp = "0=上级,1=直属")
	private String state_type;
	
	/** 单笔金额 */
	@Excel(name = "单笔金额")
	private String single_amount;
	
	/** 收益金额 */
	@Excel(name = "收益金额")
	private String benefit_money;
	
	/** 参考号 */
	@Excel(name = "参考号")
	private String tran_ref_code;
	
	/** 刷卡结算底价 */
	@Excel(name = "刷卡结算底价")
	private String card_settle_price;
	
	/** 微信结算底价 */
	@Excel(name = "微信结算底价（%）")
	private String weixin_settle_price;
	
	/** 支付宝结算底价 */
	@Excel(name = "支付宝结算底价（%）")
	private String zhifubao_settle_price;
	
	/** 云闪付结算底价 */
	@Excel(name = "云闪付结算底价（%）")
	private String cloud_settle_price;
	
	/** 单笔分润比例 */
	@Excel(name = "单笔分润比例（%）")
	private String single_profit_rate;
	
	/** 封顶费 */
	@Excel(name = "封顶费")
	private String mer_cap_fee;
	
	/** 刷卡费率 */
	@Excel(name = "刷卡费率")
	private String credit_card_rate;
	
	/** 云闪付费率 */
	@Excel(name = "云闪付费率")
	private String cloud_flash_rate;

	/** 微信费率 */
	@Excel(name = "微信费率")
	private String weixin_rate;
	
	/** 支付宝费率 */
	@Excel(name = "支付宝费率")
	private String zhifubao_rate;
	
	/** 交易封顶费 */
	@Excel(name = "交易封顶费")
	private String trade_mer_cap_fee;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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

	public String getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getBenefit_type() {
		return benefit_type;
	}

	public void setBenefit_type(String benefit_type) {
		this.benefit_type = benefit_type;
	}

	public String getState_type() {
		return state_type;
	}

	public void setState_type(String state_type) {
		this.state_type = state_type;
	}

	public String getSingle_amount() {
		return single_amount;
	}

	public void setSingle_amount(String single_amount) {
		this.single_amount = single_amount;
	}

	public String getBenefit_money() {
		return benefit_money;
	}

	public void setBenefit_money(String benefit_money) {
		this.benefit_money = benefit_money;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getTran_ref_code() {
		return tran_ref_code;
	}

	public void setTran_ref_code(String tran_ref_code) {
		this.tran_ref_code = tran_ref_code;
	}

	public String getCard_settle_price() {
		return card_settle_price;
	}

	public void setCard_settle_price(String card_settle_price) {
		this.card_settle_price = card_settle_price;
	}

	public String getWeixin_settle_price() {
		return weixin_settle_price;
	}

	public void setWeixin_settle_price(String weixin_settle_price) {
		this.weixin_settle_price = weixin_settle_price;
	}

	public String getZhifubao_settle_price() {
		return zhifubao_settle_price;
	}

	public void setZhifubao_settle_price(String zhifubao_settle_price) {
		this.zhifubao_settle_price = zhifubao_settle_price;
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

	public String getMer_cap_fee() {
		return mer_cap_fee;
	}

	public void setMer_cap_fee(String mer_cap_fee) {
		this.mer_cap_fee = mer_cap_fee;
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

	public String getWeixin_rate() {
		return weixin_rate;
	}

	public void setWeixin_rate(String weixin_rate) {
		this.weixin_rate = weixin_rate;
	}

	public String getZhifubao_rate() {
		return zhifubao_rate;
	}

	public void setZhifubao_rate(String zhifubao_rate) {
		this.zhifubao_rate = zhifubao_rate;
	}

	public String getTrade_mer_cap_fee() {
		return trade_mer_cap_fee;
	}

	public void setTrade_mer_cap_fee(String trade_mer_cap_fee) {
		this.trade_mer_cap_fee = trade_mer_cap_fee;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTraposShareBenefitRecord [id=" + id + ", order_id=" + order_id + ", manager_id=" + manager_id
				+ ", user_id=" + user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", sn=" + sn
				+ ", trans_amount=" + trans_amount + ", trans_type=" + trans_type + ", card_type=" + card_type
				+ ", trans_time=" + trans_time + ", benefit_type=" + benefit_type + ", state_type=" + state_type
				+ ", single_amount=" + single_amount + ", benefit_money=" + benefit_money + ", tran_ref_code="
				+ tran_ref_code + ", card_settle_price=" + card_settle_price + ", weixin_settle_price="
				+ weixin_settle_price + ", zhifubao_settle_price=" + zhifubao_settle_price + ", cloud_settle_price="
				+ cloud_settle_price + ", single_profit_rate=" + single_profit_rate + ", mer_cap_fee=" + mer_cap_fee
				+ ", credit_card_rate=" + credit_card_rate + ", cloud_flash_rate=" + cloud_flash_rate + ", weixin_rate="
				+ weixin_rate + ", zhifubao_rate=" + zhifubao_rate + ", trade_mer_cap_fee=" + trade_mer_cap_fee
				+ ", cre_date=" + cre_date + "]";
	}
	
}
