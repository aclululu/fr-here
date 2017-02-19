/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.SysRole;
/**
 * @author shli
 *2016-07-01
 */
public interface SysRoleMapper {

	/**
	 * 根据条件 查询 获取角色列表
	 * @param  name likename  id 
	 * @return
	 */
	public List<SysRole>findSysRoles(HashMap<String,Object> cond);
	
	
	public int findSysRolesCount(HashMap<String,Object> cond);
	
	/**
	 * 添加角色
	 * @param sysRole
	 */
	public void addSysRole(SysRole sysRole);

	/**
	 * 编辑角色
	 * @param sysRole
	 */
	public void updateSysRole(SysRole sysRole);
	
	/**
	 * 删除角色
	 * @param id
	 */
	public void deleteSysRole(@Param("id") int id);
	
	/**
	 * 批量删除角色
	 */
	public void deleteSysRoles(int[] ids);
	
	
	
	

}
