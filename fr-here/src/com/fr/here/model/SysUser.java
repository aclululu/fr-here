package com.fr.here.model;

import java.io.Serializable;

/**
  * @Description: 管理员实体
  * @Author: shli
  * @Create Date: 2016-6-29下午02:27:52
 */
public class SysUser implements Serializable {
	
	public SysUser(){
		super();
	}

	
	public SysUser(int id, String account, String password, int status,
			int roleid) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.status = status;//status
		this.roleid = roleid;
	}


	public SysUser(String account, String password, int status, int roleid) {
		super();
		this.account = account;
		this.password = password;
		this.status = status;
		this.roleid = roleid;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6123625822500908366L;
	
	private int id;
	private String account;
	private String password;
	private int status;
	private int roleid;
	private String stringrole;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return the stringrole
	 */
	public String getStringrole() {
		return stringrole;
	}


	/**
	 * @param stringrole the stringrole to set
	 */
	public void setStringrole(String stringrole) {
		this.stringrole = stringrole;
	}


	
	
	

}
