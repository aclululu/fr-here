/**
 * 
 */
package com.fr.here.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.SysRoleMapper;
import com.fr.here.dao.SysRole_PrivilegeMapper;
import com.fr.here.model.SysPrivilege;
import com.fr.here.model.SysRole;
import com.fr.here.util.PageBean;

/**
 * 角色
 * @author shli
 */
@Service
public class SysRoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysRole_PrivilegeMapper sysRPMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public SysRole findSysRole(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = sysRoleMapper.findSysRoles(cond);
		if (list.size()>0)
			return (SysRole) list.get(0);
		else
			return null;
	}
	
	/**
	 * 列表
	 */
	public List<SysRole> findSysRolesByCond(HashMap cond){
		return sysRoleMapper.findSysRoles(cond);
	}
	
	
	/**
	 *查询
	 * @param name
	 * @return
	 */
	public SysRole findSysRole(String name) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("name", name);
		List list = sysRoleMapper.findSysRoles(cond);
		if (list.size()>0)
			return (SysRole) list.get(0);
		else
			return null;
	}
	
	/**
	 * 判断数据库是否存在权限名字
	 * @param name
	 * @return
	 */
	public boolean isExist(String name){
		return findSysRole(name)!=null;
	}

	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findSysRoles(String likename,int pageNum,String orderField,String orderDirection,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		cond.put("likename", likename);
		pageBean.setTotalCount(sysRoleMapper.findSysRolesCount(cond));//总记录数
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
		pageBean.setList(sysRoleMapper.findSysRoles(cond));
		return pageBean;
	}
	
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteSysRole(int id){
		boolean flag = false;
		try {
			sysRoleMapper.deleteSysRole(id);
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
	public boolean deleteSysRoles(int[] ids){
		boolean flag = false;
		try {
			sysRoleMapper.deleteSysRoles(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 新增一条记录  返回记录的id  如果为0 则为失败
	 * @param sysRole
	 * @return
	 */
	public int addSysRole(SysRole role){
		sysRoleMapper.addSysRole(role);
		//sysRole.getId();
		return role.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysRole
	 * @return
	 */
	public boolean updateSysRole(SysRole role){
		boolean flag = false;
		try {
			sysRoleMapper.updateSysRole(role);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 添加权限和角色的关联
	 * @param sysRole
	 * @return
	 */
	public boolean addSysRole_Privileges(int roleid,int[] names){
		boolean flag = false;
		List list = new ArrayList();
		try {
			for(int i=0;i<names.length;i++){
				Map<String,Integer> map = new HashMap<String, Integer>();
				map.put("roleid", roleid);
				map.put("privilegeid", names[i]);
				list.add(map);
			}
			if(list.size()==0){
				flag = true;
			}else{
				sysRPMapper.addRolePrivilege(list);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除所有角色对应的权限
	 * @param ids
	 * @return
	 */
	public boolean deletePrivilegesByRoleIDS(int[] roleids){
		boolean flag = false;
		try {
			sysRPMapper.deletePrivilegesByRoleIDS(roleids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 角色对应的所有权限列表
	 */
	public List<SysPrivilege> findPrivilegesByRoleID(int roleid){
		return sysRPMapper.findPrivilegesByRoleID(roleid);
	}
	
	
	
	/**
	 * 清空权限相关项
	 * @param ids
	 * @return
	 */
	public boolean deletePrivilegesByProvilgerIDs(int[] ids){
		boolean flag = false;
		try {
			sysRPMapper.deletePrivilegesByProvilgerIDs(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
}
