/**
 * 
 */
package com.fr.here.service;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.NewsMapper;
import com.fr.here.model.News;
import com.fr.here.util.PageBean;
import com.fr.here.util.PageQueryUtil;

/**
 * 新闻
 * @author shli
 */
@Service
public class NewsService {

	@Resource
	private NewsMapper newsMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public News findNews(int id) {
		HashMap<String, Object> cond = new HashMap<String, Object>();
		cond.put("id", id);
		List<News> list = newsMapper.findNewss(cond);
		if (list.size()>0)
			return (News) list.get(0);
		else
			return null;
	}
	
	/**
	 *根据标题获取
	 * @param name
	 * @return
	 */
	public News findNews(String title) {
		HashMap<String, Object> cond = new HashMap<String, Object>();
		cond.put("title", title);
		List<News> list = newsMapper.findNewss(cond);
		if (list.size()>0)
			return (News) list.get(0);
		else
			return null;
	}
	
	/**
	 *获取
	 * @param name
	 * @return
	 */
	public List<News> findNewss(HashMap<String, Object> cond) {
		return newsMapper.findNewss(cond);
	}
	
	
	/**
	 * 查看传入的新闻栏目是否有新闻
	 * @param cid  栏目id
	 */
	public int columnHasNews(int cid){
		HashMap<String, Object> cond = new HashMap<String, Object>();
		cond.put("cid", cid);
		List<News> list = newsMapper.findNewss(cond);
		return list.size();
	}

	/**
	 * @param cond    查询条件
	 * @param pageNum      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @param pageBean   分页信息
	 * @return
	 */
	public PageBean findNews(HashMap<String,Object> cond,int pageNum,PageBean pageBean){
		pageBean.setTotalCount(newsMapper.findNewsCount(cond));//总记录数
		
		int totalPage = PageBean.counttotalPage(pageBean.getPageSize(), pageBean.getTotalCount());//总页数
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(pageNum);
		int offset = PageBean.countOffset(pageBean.getPageSize(), pageNum);	//当前开始记录
		cond.put("length",pageBean.getPageSize());
		cond.put("offset", offset);
		pageBean.setList(newsMapper.findNewss(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteNews(int id){
		boolean flag = false;
		try {
			newsMapper.deleteNews(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 批量删除     利用id
	 * @param ids
	 * @return
	 */
	public boolean deleteNews(int[] ids){
		boolean flag = false;
		try {
			newsMapper.deleteNewss(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 新增一条记录  返回记录的id  如果为0 则为失败
	 * @param sysPrivilege
	 * @return
	 */
	public int addNews(News news){
		newsMapper.addNews(news);
		//sysPrivilege.getId();
		return news.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateNews(News news){
		boolean flag = false;
		try {
			newsMapper.updateNews(news);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	/**
	 * 批量发布 or 撤销发布
	 * @param ids
	 * @param status
	 * @return
	 */
	public boolean updateNewsStatus(int[] ids,int status){
		boolean flag = false;
		HashMap<String,Object> cond = new HashMap<String, Object>();
		cond.put("ids", ids);
		cond.put("status", status);
		try{
			newsMapper.updateNewss(cond);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 发布 or 撤销
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateNewsStatus(int id,int status){
		return updateNewsStatus(new int[]{id},status); 
	}
	
	/**
	 * 微信公众号接口
	 * 6 重点业务  7技术资源 8员工活动
	 * @return
	 */
	public List<News> getWxPushNews(int cid){
		return newsMapper.getWxPushNews(cid);
	}
	
	/**
	 * 微信公众号接口
	 * 按照新闻的标题模糊
	 * @param likename
	 * @return
	 */
	public List<News> getWxPushNews(String likename){
		HashMap<String, Object> cond = new HashMap<String, Object>();
		cond.put("likename", likename);
		cond.put("order", "desc");
		cond.put("sort", "id");
		cond.put("status", 1);
		cond.put("length", 5);
		cond.put("offset", 0);
		return  newsMapper.findNewss(cond);
	}
	
	/**
	 * 获取4条用于banner的图片新闻   根据 日期和点击量 获取
	 * @return
	 */
	public List<News> getBannerNews(){
		HashMap<String, Object> cond = new  HashMap<String, Object>();
		cond.put("bannersort", "sort");
		cond.put("length", 4);
		cond.put("offset", 0);
		cond.put("status", 1);
		cond.put("picnews", 1);
		return newsMapper.findNewss(cond);
		
	}
	/**
	 * 用于app查询的新闻列表
	 * 条件     picnews 1图片新闻  -1文本新闻   0全部   length offset
	 * 排序呢    id 倒序
	 */
	public List<News> findNews(int pageNum,int pageSize,int picnews){
		
		HashMap<String,Object> cond = new HashMap<String, Object>();
		//分页
		int offset = PageBean.countOffset(pageSize, pageNum);	//当前开始记录
		cond.put("length",pageSize);
		cond.put("offset", offset);
		cond.put("status", 1);
		if(picnews==1||picnews==-1){
			cond.put("picnews", picnews);
		}
		//排序
		cond.put("sort", "id");
		cond.put("order", "desc");
		return newsMapper.findNewss(cond);
	}
	
	
}
