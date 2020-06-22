package com.ruoyi.project.deveagent.usertrapos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserTraditionalPosInstallDetail extends BaseEntity
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
	
	/** 进件编号 */
	@Excel(name = "进件编号")
	private String install_id;
	
	/** 终端号 */
	@Excel(name = "终端号")
	private String terminal;
	
	/** 机具编号(设备号) */
	@Excel(name = "机具编号(设备号)")
	private String machine_id;
	
	/** 卡号 */
	@Excel(name = "卡号")
	private String sim_card;
	
	/** 携机入网标识 */
	@Excel(name = "携机入网标识", readConverterExp = "0=中付机,1=携机入网 ")
	private String is_take_machi;
	
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

	public String getInstall_id() {
		return install_id;
	}

	public void setInstall_id(String install_id) {
		this.install_id = install_id;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

	public String getSim_card() {
		return sim_card;
	}

	public void setSim_card(String sim_card) {
		this.sim_card = sim_card;
	}

	public String getIs_take_machi() {
		return is_take_machi;
	}

	public void setIs_take_machi(String is_take_machi) {
		this.is_take_machi = is_take_machi;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "AgentUserTraditionalPosInstallDetail [id=" + id + ", manager_id=" + manager_id + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", real_name=" + real_name + ", install_id=" + install_id + ", terminal="
				+ terminal + ", machine_id=" + machine_id + ", sim_card=" + sim_card + ", is_take_machi="
				+ is_take_machi + "]";
	}
	
}
