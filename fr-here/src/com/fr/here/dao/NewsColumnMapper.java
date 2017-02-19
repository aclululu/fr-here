/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.NewsColumn;
import com.fr.here.model.SysPrivilege;
import com.fr.here.model.SysRole;
import com.fr.here.model.SysUser;

/**
 * @author shli
 *2016-07-01
 */
public interface NewsColumnMapper {

	/**
	 * 根据条件 查询 新闻栏目
	 * @param  
	 * @return
	 */
	public List<NewsColumn>findNewsColumns(HashMap<String,Object> cond);
	
	
	/**
	 * 分页  总条数
	 * @param cond
	 * @return
	 */
	public int findNewsColumnsCount(HashMap<String,Object> cond);
	
	/**
	 * 添加新闻栏目
	 * @param newsColumn
	 */
	public void addNewsColumn(NewsColumn newsColumn);

	/**
	 * 编辑新闻栏目
	 * @param newsColumn
	 */
	public void updateNewsColumn(NewsColumn newsColumn);
	
	/**
	 * 删除新闻栏目
	 * @param id
	 */
	public void deleteNewsColumn(@Param("id") int id);
	
}
