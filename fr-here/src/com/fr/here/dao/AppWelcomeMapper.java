package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.AppWelcome;

public interface AppWelcomeMapper {
	/**
	 * 根据条件 查询 获取图片列表
	 * @param  
	 * @return
	 */
	public List<AppWelcome>findAppWelcomes(HashMap<String,Object> cond);
	
	
	public int findAppWelcomesCount(HashMap<String,Object> cond);
	
	/**
	 * 添加图片
	 * @param AppWelcome
	 */
	public void addAppWelcome(AppWelcome appWelcome);

	/**
	 * 编辑图片
	 * @param AppWelcome
	 */
	public void updateAppWelcome(AppWelcome appWelcome);
	
	/**
	 * 删除图片
	 * @param id
	 */
	public void deleteAppWelcome(@Param("id") int id);
	
	/**
	 * 批量删除gualiyu7an
	 */
	public void deleteAppWelcomes(int[] ids);

}