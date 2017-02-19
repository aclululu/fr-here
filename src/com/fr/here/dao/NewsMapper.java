/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.fr.here.model.News;

/**
 * @author shli
 *2016-07-01
 */
public interface NewsMapper {

	/**
	 * 根据条件 查询 新闻
	 * @param  
	 * @return
	 */
	public List<News>findNewss(HashMap<String,Object> cond);
	
	
	/**
	 * 分页  总条数
	 * @param cond
	 * @return
	 */
	public int findNewsCount(HashMap<String,Object> cond);
	
	/**
	 * 添加新闻
	 * @param news
	 */
	public void addNews(News news);

	/**
	 * 编辑新闻
	 * @param news
	 */
	public void updateNews(News news);
	
	/**
	 * 删除新闻
	 * @param id
	 */
	public void deleteNews(@Param("id") int id);
	
	/**
	 * 批量删除新闻
	 * @param ids
	 */
	public void deleteNewss(int[] ids);
	
	
	/**
	 * 批量发布or撤销发布
	 * HashMap<String,Object> cond
	 * @Param("ids")int[] ids ,@Param("status") int status
	 */
	public void updateNewss(HashMap<String,Object> cond);
	
	
	/**
	 * 微信专用接口
	 * 推送5条新闻 按类别  和 优先级  
	 * @param cid
	 * @return
	 */
	public List<News> getWxPushNews(@Param("cid") int cid);
}
