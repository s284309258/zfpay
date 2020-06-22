package com.ruoyi.project.deveagent.usermpos.domain;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class AgentUserMposAllotRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 订单号 */
	@Excel(name = "设备号（机器编号）")
	private String sn;
	
	/** 达标编号 */
	@Excel(name = "分配给谁ID")
	private String user_id;
	
	/** 代理编号 */
	@Excel(name = "分配给谁")
	private String real_name;
	
	/** 用户编号 */
	@Excel(name = "机具类型")
	private String pos_type;
	
	/** 手机号 */
	@Excel(name = "由谁分配")
	private String allocate_by;
	
	/** 真实姓名 */
	@Excel(name = "分配时间")
	private String allocate_date;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getPos_type() {
		return pos_type;
	}

	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}

	public String getAllocate_by() {
		return allocate_by;
	}

	public void setAllocate_by(String allocate_by) {
		this.allocate_by = allocate_by;
	}

	public String getAllocate_date() {
		return allocate_date;
	}

	public void setAllocate_date(String allocate_date) {
		this.allocate_date = allocate_date;
	}
}
