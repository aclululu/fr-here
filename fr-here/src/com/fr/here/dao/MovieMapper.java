package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.fr.here.model.Movie;
public interface MovieMapper {
	
	/**
	 * 根据条件 查询 获取视频列表
	 * @param  
	 * @return
	 */
	public List<Movie>findMovies(HashMap<String,Object> cond);
	
	
	public int findMoviesCount(HashMap<String,Object> cond);
	
	/**
	 * 添加视频
	 * @param Movie
	 */
	public void addMovie(Movie movie);

	/**
	 * 编辑视频
	 * @param Movie
	 */
	public void updateMovie(Movie movie);
	
	/**
	 * 删除视频
	 * @param id
	 */
	public void deleteMovie(@Param("id") int id);
	
	/**
	 * 批量删除gualiyu7an
	 */
	public void deleteMovies(int[] ids);
}