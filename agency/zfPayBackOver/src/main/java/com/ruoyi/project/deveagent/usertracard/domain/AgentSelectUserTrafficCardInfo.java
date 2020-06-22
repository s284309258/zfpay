package com.ruoyi.project.deveagent.usertracard.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSelectUserTrafficCardInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 流量卡号 */
	@Excel(name = "流量卡号")
	private String card_no;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AgentSelectUserTrafficCardInfo [card_no=" + card_no + ", remark=" + remark + "]";
	}
	
}
