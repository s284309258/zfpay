package com.ruoyi.project.deveagent.usermpos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSelectUserMposInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
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
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AgentSelectUserMposInfo [sn=" + sn + ", card_settle_price=" + card_settle_price
				+ ", cloud_settle_price=" + cloud_settle_price + ", single_profit_rate=" + single_profit_rate
				+ ", cash_back_rate=" + cash_back_rate + ", remark=" + remark + "]";
	}
	
	
}
