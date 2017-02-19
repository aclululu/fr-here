/**
 * 
 */
package com.fr.here.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.MovieMapper;
import com.fr.here.model.Movie;
import com.fr.here.util.PageBean;

/**
 * app视频
 * @author shli
 */
@Service
public class MovieService {

	@Resource
	private MovieMapper movieMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Movie findMovie(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = movieMapper.findMovies(cond);
		if (list.size()>0)
			return (Movie) list.get(0);
		else
			return null;
	}
	
	/**
	 * 获取最新的APP视频  即id最大
	 * @param 
	 * @return
	 */
	public Movie findNewMovie() {
		HashMap cond = new HashMap<String, Object>();
		cond.put("sort", "id");
		cond.put("order", "desc");
		List list = movieMapper.findMovies(cond);
		if (list.size()>0)
			return (Movie) list.get(0);
		else
			return new Movie();
	}


	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findMovies(int pageNum,String orderField,String orderDirection,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		pageBean.setTotalCount(movieMapper.findMoviesCount(cond));//总记录数
		
		int totalPage = PageBean.counttotalPage(pageBean.getPageSize(), pageBean.getTotalCount());//总页数
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(pageNum);
		
		int offset = PageBean.countOffset(pageBean.getPageSize(), pageNum);	//当前开始记录
		cond.put("length",pageBean.getPageSize());
		cond.put("offset", offset);
		orderField = orderField == null ?"id":orderDirection;
		orderDirection = orderDirection == null ?  "desc":orderField;
		cond.put("sort", orderField);
		cond.put("order", orderDirection);
		pageBean.setList(movieMapper.findMovies(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteMovie(int id){
		boolean flag = false;
		try {
			movieMapper.deleteMovie(id);
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
	public boolean deleteMovies(int[] ids){
		boolean flag = false;
		try {
			movieMapper.deleteMovies(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 新增一条记录  返回记录的id  如果为0 则为失败
	 * @param movie
	 * @return
	 */
	public int addMovie(Movie movie){
		movieMapper.addMovie(movie);
		//movie.getId();
		return movie.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param movie
	 * @return
	 */
	public boolean updateMovie(Movie movie){
		boolean flag = false;
		try {
			movieMapper.updateMovie(movie);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 用于app查询的视频列表
	 * 排序    id 倒序
	 */
	public List<Movie> findMovie(int pageNum,int pageSize){
		
		HashMap<String,Object> cond = new HashMap<String, Object>();
		//分页
		int offset = PageBean.countOffset(pageSize, pageNum);	//当前开始记录
		cond.put("length",pageSize);
		cond.put("offset", offset);
		//排序
		cond.put("sort", "id");
		cond.put("order", "desc");
		return movieMapper.findMovies(cond);
	}
	
	
	
}
