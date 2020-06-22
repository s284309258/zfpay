package com.ruoyi.project.deveagent.usertrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTraditionalPosInstallInfo extends BaseEntity
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
	
	/** 审核状态 */
	@Excel(name = "审核状态", readConverterExp = "00=审核成功,01=审核失败")
	private String biz_code;
	
	/** 返回信息 */
	@Excel(name = "返回信息")
	private String biz_msg;
	
	/** 来源 */
	@Excel(name = "来源")
	private String source;
	
	/** 商户名称 */
	@Excel(name = "商户名称")
	private String merchant_name;
	
	/** 商户编号 */
	@Excel(name = "商户编号")
	private String mer_code;
	
	/** 代理账号 */
	@Excel(name = "代理账号")
	private String agent_id;
	
	/** 到账标识 */
	@Excel(name = "到账标识", readConverterExp = "T0=T0到账,T1=T1到账")
	private String settle_flag;
	
	/** 对应SDK的other字段 */
	@Excel(name = "对应SDK的other字段")
	private String sdk_push_key;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	
	/** 创建者 */
	@Excel(name = "创建者")
	private String create_by;

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

	public String getBiz_code() {
		return biz_code;
	}

	public void setBiz_code(String biz_code) {
		this.biz_code = biz_code;
	}

	public String getBiz_msg() {
		return biz_msg;
	}

	public void setBiz_msg(String biz_msg) {
		this.biz_msg = biz_msg;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMer_code() {
		return mer_code;
	}

	public void setMer_code(String mer_code) {
		this.mer_code = mer_code;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public String getSettle_flag() {
		return settle_flag;
	}

	public void setSettle_flag(String settle_flag) {
		this.settle_flag = settle_flag;
	}

	public String getSdk_push_key() {
		return sdk_push_key;
	}

	public void setSdk_push_key(String sdk_push_key) {
		this.sdk_push_key = sdk_push_key;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTraditionalPosInstallInfo [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", biz_code=" + biz_code + ", biz_msg="
				+ biz_msg + ", source=" + source + ", merchant_name=" + merchant_name + ", mer_code=" + mer_code
				+ ", agent_id=" + agent_id + ", settle_flag=" + settle_flag + ", sdk_push_key=" + sdk_push_key
				+ ", cre_date=" + cre_date + ", remark=" + remark + ", create_by=" + create_by + "]";
	}

}
