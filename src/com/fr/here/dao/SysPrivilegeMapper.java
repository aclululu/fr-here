/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.SysPrivilege;
/**
 * @author shli
 *2016-07-01
 */
public interface SysPrivilegeMapper {

	/**
	 * 根据条件 查询 获取权限列表
	 * @param  name likename  id  pid
	 * @return
	 */
	public List<SysPrivilege>findSysPrivileges(HashMap<String,Object> cond);
	
	
	public int findSysPrivilegesCount(HashMap<String,Object> cond);
	/**
	 * 获取所有一级权限
	 * @return
	 */
	public List<SysPrivilege> findBossSysPrivilegeList();
	
	/**
	 * 添加权限
	 * @param sysPrivilege
	 */
	public void addSysPrivilege(SysPrivilege sysPrivilege);

	/**
	 * 编辑权限
	 * @param sysPrivilege
	 */
	public void updateSysPrivileger(SysPrivilege sysPrivilege);
	
	/**
	 * 删除权限
	 * @param id
	 */
	public void deleteSysPrivileger(@Param("id") int id);
	
	/**
	 * 批量删除权限  不做了 草
	 */
	public void deleteSysPrivilegers(int[] ids);
	
	

}
