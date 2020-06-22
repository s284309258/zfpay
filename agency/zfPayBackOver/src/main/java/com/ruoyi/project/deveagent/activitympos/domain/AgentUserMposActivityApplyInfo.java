package com.ruoyi.project.deveagent.activitympos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_message_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMposActivityApplyInfo extends BaseEntity
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
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String real_name;
	
	/** 活动编号 */
	@Excel(name = "活动编号")
	private String activity_id;
	
	/** 活动订单号 */
	@Excel(name = "活动订单号")
	private String activity_order_id;
	
	/** 活动名称 */
	@Excel(name = "活动名称")
	private String activity_name;
	
	/** 活动奖励类型编号 */
	@Excel(name = "活动奖励类型编号")
	private String activity_reward_id;
	
	/** 库存数量 */
	@Excel(name = "库存数量")
	private String pos_num;
	
	/** 奖励金额 */
	@Excel(name = "奖励金额")
	private String reward_money;
	
	/** 交易额（万） */
	@Excel(name = "交易额（万）")
	private String expenditure;
	
	/** 申请的SN列表 */
	@Excel(name = "申请的SN列表")
	private String sn_list;
	
	/** 审核状态 */
	@Excel(name = "审核状态", readConverterExp = "00=待审核,04=取消活动,08=审核失败,09=审核成功")
	private String status;
	
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

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public String getActivity_order_id() {
		return activity_order_id;
	}

	public void setActivity_order_id(String activity_order_id) {
		this.activity_order_id = activity_order_id;
	}

	public String getActivity_reward_id() {
		return activity_reward_id;
	}

	public void setActivity_reward_id(String activity_reward_id) {
		this.activity_reward_id = activity_reward_id;
	}

	public String getSn_list() {
		return sn_list;
	}

	public void setSn_list(String sn_list) {
		this.sn_list = sn_list;
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

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getPos_num() {
		return pos_num;
	}

	public void setPos_num(String pos_num) {
		this.pos_num = pos_num;
	}

	public String getReward_money() {
		return reward_money;
	}

	public void setReward_money(String reward_money) {
		this.reward_money = reward_money;
	}

	public String getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserMposActivityApplyInfo [id=" + id + ", manager_id=" + manager_id + ", order_id=" + order_id
				+ ", user_id=" + user_id + ", user_tel=" + user_tel + ", real_name=" + real_name + ", activity_id="
				+ activity_id + ", activity_order_id=" + activity_order_id + ", activity_name=" + activity_name
				+ ", activity_reward_id=" + activity_reward_id + ", pos_num=" + pos_num + ", reward_money="
				+ reward_money + ", expenditure=" + expenditure + ", sn_list=" + sn_list + ", status=" + status
				+ ", remark=" + remark + ", cre_date=" + cre_date + ", up_date=" + up_date + ", update_by=" + update_by
				+ "]";
	}

}
