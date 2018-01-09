/**
 * Project Name:adminlte
 * File Name:Record.java
 * Package Name:cn.bdqn.lesson.model
 * Date:2017年8月21日下午3:47:38
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 */

package cn.bdqn.datacockpit.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午3:47:38 <br/>
 * 
 * @author junwen.bao@airintelli.com
 * @version
 * @see
 */

public class Userinfo1 implements Serializable{

	private String realname;

    private String username;

    private String password;

    private String job;

    private String email;

    private Integer state;

    private Integer roleId;

    private String approval;
    
	private List<String> roles;

    private Set<String> permission;
    
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPermission() {
		return permission;
	}

	public void setPermission(Set<String> permission) {
		this.permission = permission;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "Userinfo1 [realname=" + realname + ", username=" + username + ", password=" + password + ", job=" + job
				+ ", email=" + email + ", state=" + state + ", roleId=" + roleId + ", approval=" + approval + "]";
	}

    

}
