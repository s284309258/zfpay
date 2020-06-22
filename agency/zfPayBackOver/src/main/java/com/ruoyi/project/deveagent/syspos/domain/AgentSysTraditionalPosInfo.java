package com.ruoyi.project.deveagent.syspos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentSysTraditionalPosInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号", type = Type.EXPORT)
	private Integer id;
	
	/** 代理编号 */
	@Excel(name = "代理编号", type = Type.EXPORT)
	private String manager_id;
	
	/** 代理账号编号 */
	@Excel(name = "代理账号编号", type = Type.EXPORT)
	private String account_id;
	
	/** app_id */
	@Excel(name = "app_id", type = Type.EXPORT)
	private String app_id;
	
	/** 设备号（机器编号） */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 商户号 */
	@Excel(name = "商户号")
	private String mer_id;
	
	/** 商户名称 */
	@Excel(name = "商户名称")
	private String mer_name;
	
	/** 商户姓名 */
	@Excel(name = "商户姓名", type = Type.EXPORT)
	private String name;
	
	/** 商户手机号 */
	@Excel(name = "商户手机号", type = Type.EXPORT)
	private String tel;
	
	/** 返现获取类型 */
	@Excel(name = "返现获取类型(0=设置,1=中付)", readConverterExp = "0=设置,1=中付")
	private String cash_back_type;
	
	/** 返现条件 */
	@Excel(name = "返现条件")
	private String cash_back_condition;
	
	/** 返现金额 */
	@Excel(name = "返现金额")
	private String cash_back_money;
	
	/** 实际返现金额  */
	@Excel(name = "实际返现金额", type = Type.EXPORT)
	private String real_cash_back_money;
	
	/** 返现状态 */
	@Excel(name = "返现状态", readConverterExp = "0=未返现,1=已返现", type = Type.EXPORT)
	private String cash_back_status;
	
	/** 刷卡费率（%） */
	@Excel(name = "刷卡费率（%）")
	private String credit_card_rate;
	
	/** 云闪付费率（%） */
	@Excel(name = "云闪付费率（%）")
	private String cloud_flash_rate;
	
	/** 微信费率（%） */
	@Excel(name = "微信费率（%）")
	private String weixin_rate;
	
	/** 支付宝费率（%） */
	@Excel(name = "支付宝费率（%）")
	private String zhifubao_rate;

	//add begin byqh 201912
	/** VIP秒到(商圈)刷卡费率（%） */
	@Excel(name = "VIP秒到(商圈)刷卡费率（%）")
	private String credit_card_rate_vip;

	/** VIP秒到(商圈)云闪付费率（%） */
	@Excel(name = "VIP秒到(商圈)云闪付费率（%）")
	private String cloud_flash_rate_vip;

	/** VIP秒到(商圈)微信费率（%） */
	@Excel(name = "VIP秒到(商圈)微信费率（%）")
	private String weixin_rate_vip;

	/** VIP秒到(商圈)支付宝费率（%） */
	@Excel(name = "VIP秒到(商圈)支付宝费率（%）")
	private String zhifubao_rate_vip;
	//add end byqh 201912

	
	/** 是否申请扫码支付 */
	@Excel(name = "是否申请扫码支付", readConverterExp = "0=否,1=是", type = Type.EXPORT)
	private String scan_status;
	
	/** 激活状态 */
	@Excel(name = "激活状态", readConverterExp = "0=未激活,1=已激活", type = Type.EXPORT)
	private String act_status;
	
	/** 分配状态 */
	@Excel(name = "分配状态", readConverterExp = "0=未分配,1=已分配", type = Type.EXPORT)
	private String dis_status;
	
	/** 激活时所属用户  */
	@Excel(name = "激活时所属用户", type = Type.EXPORT)
	private String act_user_id;
	
	/** 激活时间 */
	@Excel(name = "激活时间", type = Type.EXPORT)
	private String act_date;
	
	/** 创建时间 */
	@Excel(name = "创建时间", type = Type.EXPORT)
	private String cre_date;
	
	/** 创建时间 */
	private String cre_time;
	
	/** 更新时间 */
	@Excel(name = "更新时间", type = Type.EXPORT)
	private String up_date;
	
	/** 更新时间 */
	private String up_time;
	
	/** 备注 */
	@Excel(name = "备注", type = Type.EXPORT)
	private String remark;
	
	/** 创建者 */
	@Excel(name = "创建者", type = Type.EXPORT)
	private String create_by;
	
	/** 更新者 */
	@Excel(name = "更新者", type = Type.EXPORT)
	private String update_by;

	public String getCredit_card_rate_vip() {
		return credit_card_rate_vip;
	}

	public void setCredit_card_rate_vip(String credit_card_rate_vip) {
		this.credit_card_rate_vip = credit_card_rate_vip;
	}

	public String getCloud_flash_rate_vip() {
		return cloud_flash_rate_vip;
	}

	public void setCloud_flash_rate_vip(String cloud_flash_rate_vip) {
		this.cloud_flash_rate_vip = cloud_flash_rate_vip;
	}

	public String getWeixin_rate_vip() {
		return weixin_rate_vip;
	}

	public void setWeixin_rate_vip(String weixin_rate_vip) {
		this.weixin_rate_vip = weixin_rate_vip;
	}

	public String getZhifubao_rate_vip() {
		return zhifubao_rate_vip;
	}

	public void setZhifubao_rate_vip(String zhifubao_rate_vip) {
		this.zhifubao_rate_vip = zhifubao_rate_vip;
	}

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public String getScan_status() {
		return scan_status;
	}

	public void setScan_status(String scan_status) {
		this.scan_status = scan_status;
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

	public String getDis_status() {
		return dis_status;
	}

	public void setDis_status(String dis_status) {
		this.dis_status = dis_status;
	}

	public String getUp_time() {
		return up_time;
	}

	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getReal_cash_back_money() {
		return real_cash_back_money;
	}

	public void setReal_cash_back_money(String real_cash_back_money) {
		this.real_cash_back_money = real_cash_back_money;
	}

	@Override
	public String toString() {
		return "AgentSysTraditionalPosInfo [id=" + id + ", manager_id=" + manager_id + ", account_id=" + account_id
				+ ", app_id=" + app_id + ", sn=" + sn + ", mer_id=" + mer_id + ", mer_name=" + mer_name + ", name="
				+ name + ", tel=" + tel + ", cash_back_type=" + cash_back_type + ", cash_back_condition="
				+ cash_back_condition + ", cash_back_money=" + cash_back_money + ", real_cash_back_money="
				+ real_cash_back_money + ", cash_back_status=" + cash_back_status + ", credit_card_rate="
				+ credit_card_rate + ", cloud_flash_rate=" + cloud_flash_rate + ", weixin_rate=" + weixin_rate
				+ ", zhifubao_rate=" + zhifubao_rate + ", scan_status=" + scan_status + ", act_status=" + act_status
				+ ", dis_status=" + dis_status + ", act_user_id=" + act_user_id + ", act_date=" + act_date
				+ ", cre_date=" + cre_date + ", cre_time=" + cre_time + ", up_date=" + up_date + ", up_time=" + up_time
				+ ", remark=" + remark + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}

}
