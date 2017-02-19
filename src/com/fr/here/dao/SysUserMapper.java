/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.SysPrivilege;
import com.fr.here.model.SysRole;
import com.fr.here.model.SysUser;

/**
 * @author shli
 *2016-07-01
 */
public interface SysUserMapper {

	/**
	 * 根据条件 查询 获取管理员列表
	 * @param  name likename  id roleid
	 * @return
	 */
	public List<SysUser>findSysUsers(HashMap<String,Object> cond);
	
	
	public int findSysUsersCount(HashMap<String,Object> cond);
	
	/**
	 * 添加管理员
	 * @param SysUser
	 */
	public void addSysUser(SysUser sysUser);

	/**
	 * 编辑管理员
	 * @param SysUser
	 */
	public void updateSysUser(SysUser sysUser);
	
	/**
	 * 删除管理员
	 * @param id
	 */
	public void deleteSysUser(@Param("id") int id);
	
	/**
	 * 批量删除gualiyu7an
	 */
	public void deleteSysUsers(int[] ids);

	/**
	 * 统计将要被删除的roleids 有多少个管理员在引用
	 * @param roleids
	 * @return
	 */
	public int findCountByRoleIDs(int[] roleids);
	
}
