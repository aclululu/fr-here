/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.SupplyColumn;
/**
 * @author shli
 *2016-07-01
 */
public interface SupplyColumnMapper {

	/**
	 * 根据条件 查询栏目
	 * @param  
	 * @return
	 */
	public List<SupplyColumn>findSupplyColumns(HashMap<String,Object> cond);
	
	
	/**
	 * 分页  总条数
	 * @param cond
	 * @return
	 */
	public int findSupplyColumnsCount(HashMap<String,Object> cond);
	
	/**
	 * 添加栏目
	 * @param supplyColumn
	 */
	public void addSupplyColumn(SupplyColumn supplyColumn);

	/**
	 * 编辑栏目
	 * @param supplyColumn
	 */
	public void updateSupplyColumn(SupplyColumn supplyColumn);
	
	/**
	 * 删除栏目
	 * @param id
	 */
	public void deleteSupplyColumn(@Param("id") int id);
	
}
