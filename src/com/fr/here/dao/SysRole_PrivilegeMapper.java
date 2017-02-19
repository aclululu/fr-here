/**
 * 
 */
package com.fr.here.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fr.here.model.SysPrivilege;
/**
 * 这里面仅仅关联子权限
 * @author shli 
 *2016-07-01
 */
public interface SysRole_PrivilegeMapper {
	
	/**
	 * 获取角色对应的所有权限
	 * @param roleid
	 */
	public List<SysPrivilege> findPrivilegesByRoleID(@Param("roleid") int roleid);
	
	/**
	 * 删除角色对应的所有权限
	 */
	public void deletePrivilegesByRoleIDS(int[] roleids);
	
	/**
	 * 删除权限  如果是顶级权限，则先删除子权限
	 * 然后清空 和角色的关联
	 */
	public void deletePrivilegesByProvilgerIDs(int[] ids);
	
	/**
	 * 添加关联  应先全部清除  在全部添加
	 */
	public void addRolePrivilege(List<HashMap> list);
	
}