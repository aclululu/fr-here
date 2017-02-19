package com.fr.here.model;

import java.io.Serializable;
import java.util.Date;

/**
  * @Description:供需 
  * @Author: shli
  * @Create Date: 2016-6-29下午03:20:16
 */
public class Supply implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7293534281653548349L;
	private Integer id;
	private Integer cid;
	private Integer sio;
	private String title;
	private String content;
	private Date cdate;
	private Integer status;
	private String address;
	private String phone;
	private String suname;
	private Date overdate;
	private String column;
	private String openid;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * @return the sio
	 */
	public Integer getSio() {
		return sio;
	}
	/**
	 * @param sio the sio to set
	 */
	public void setSio(Integer sio) {
		this.sio = sio;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the cdate
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * @param cdate the cdate to set
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the suname
	 */
	public String getSuname() {
		return suname;
	}
	/**
	 * @param suname the suname to set
	 */
	public void setSuname(String suname) {
		this.suname = suname;
	}
	/**
	 * @return the overdate
	 */
	public Date getOverdate() {
		return overdate;
	}
	/**
	 * @param overdate the overdate to set
	 */
	public void setOverdate(Date overdate) {
		this.overdate = overdate;
	}
	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
}
