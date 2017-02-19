/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.fr.here.model.Supply;

/**
 * @author shli
 *2016-07-01
 */
public interface SupplyMapper {

	/**
	 * 根据条件  新闻
	 * @param  
	 * @return
	 */
	public List<Supply>findSupplys(HashMap<String,Object> cond);
	
	
	/**
	 * 分页  总条数
	 * @param cond
	 * @return
	 */
	public int findSupplyCount(HashMap<String,Object> cond);
	
	/**
	 * 添加
	 * @param supply
	 */
	public void addSupply(Supply supply);

	/**
	 * 编辑
	 * @param supply
	 */
	public void updateSupply(Supply supply);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteSupply(@Param("id") int id);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteSupplys(@Param("ids")int[] ids);
	
	
	/**
	 * 批量发布or撤销发布
	 * HashMap<String,Object> cond
	 * @Param("ids")int[] ids ,@Param("status") int status
	 */
	public void updateSupplys(HashMap<String,Object> cond);
	
	
	/**
	 * 微信专用接口
	 * 推送5条供需   按供or需    日期
	 * sio 1为供应  -1为需求
	 * @return
	 */
	public List<Supply> getWxPushSupply(@Param("sio") int sio);
}
