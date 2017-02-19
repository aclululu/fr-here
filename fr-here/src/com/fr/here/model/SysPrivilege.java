package com.fr.here.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
  * @Description: 权限 
  * @Author: shli
  * @Create Date: 2016-6-29下午03:01:12
 */
public class SysPrivilege implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1888257381254219731L;
	private int id;
	private int pid;
	private String name;
	private String url;
	private String info;
	
	private List<SysPrivilege> child = new ArrayList<SysPrivilege>();
	private boolean parent;
	private boolean checked;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the child
	 */
	public List<SysPrivilege> getChild() {
		return child;
	}
	/**
	 * @param child the child to set
	 */
	public void setChild(List<SysPrivilege> child) {
		this.child = child;
	}
	/**
	 * @return the parent
	 */
	public boolean isParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(boolean parent) {
		this.parent = parent;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
