package com.fr.here.model;

import java.io.Serializable;
import java.util.Date;

/**
  * @Description:新闻 
  * @Author: shli
  * @Create Date: 2016-6-29下午03:10:13
 */
public class News implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3700100160565541697L;
	private Integer id;
	private Integer cid;
	private String title;
	private String content;
	private String abstract_;
	private String keywords;
	private Integer picnews;
	private String picture;
	private String origin;
	private Date cdate;
	private String author;
	private String editor;
	private Integer clicks;
	private Integer status;
	private Integer priority;
	
	private String column;

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
	 * @return the abstract_
	 */
	public String getAbstract_() {
		return abstract_;
	}

	/**
	 * @param abstract1 the abstract_ to set
	 */
	public void setAbstract_(String abstract1) {
		abstract_ = abstract1;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the picnews
	 */
	public Integer getPicnews() {
		return picnews;
	}

	/**
	 * @param picnews the picnews to set
	 */
	public void setPicnews(Integer picnews) {
		this.picnews = picnews;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the clicks
	 */
	public Integer getClicks() {
		return clicks;
	}

	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
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
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	
	
	
}
