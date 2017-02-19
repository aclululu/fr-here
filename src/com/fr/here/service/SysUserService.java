/**
 * 
 */
package com.fr.here.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.SysUserMapper;
import com.fr.here.model.SysUser;
import com.fr.here.util.PageBean;

/**
 * 用户
 * @author shli
 */
@Service
public class SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public SysUser findSysUser(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = sysUserMapper.findSysUsers(cond);
		if (list.size()>0)
			return (SysUser) list.get(0);
		else
			return null;
	}
	
	/**
	 *查询
	 * @param name
	 * @return
	 */
	public SysUser findSysUser(String name) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("account", name);
		List list = sysUserMapper.findSysUsers(cond);
		if (list.size()>0)
			return (SysUser) list.get(0);
		else
			return null;
	}
	
	/**
	 * 判断数据库是否存在权限名字
	 * @param name
	 * @return
	 */
	public boolean isExist(String name){
		return findSysUser(name)!=null;
	}
	
	
	/**
	 *检查角色是否有被关联
	 * @param name
	 * @return
	 */
	public int findCountByRoleIDs(int[] roleids){
		return sysUserMapper.findCountByRoleIDs(roleids);
	}
	

	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findSysUsers(int constatus,String likename,int pageNum,String orderField,String orderDirection,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		cond.put("likename", likename);
		if(constatus!=0){
			cond.put("status", constatus);
		}
		pageBean.setTotalCount(sysUserMapper.findSysUsersCount(cond));//总记录数
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
		pageBean.setList(sysUserMapper.findSysUsers(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteSysUser(int id){
		boolean flag = false;
		try {
			sysUserMapper.deleteSysUser(id);
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
	public boolean deleteSysUsers(int[] ids){
		boolean flag = false;
		try {
			sysUserMapper.deleteSysUsers(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 新增一条记录  返回记录的id  如果为0 则为失败
	 * @param sysPrivilege
	 * @return
	 */
	public int addSysUser(SysUser role){
		sysUserMapper.addSysUser(role);
		//sysPrivilege.getId();
		return role.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateSysUser(SysUser role){
		boolean flag = false;
		try {
			sysUserMapper.updateSysUser(role);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
}
