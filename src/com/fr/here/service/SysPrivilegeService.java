/**
 * 
 */
package com.fr.here.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.SysPrivilegeMapper;
import com.fr.here.model.SysPrivilege;
import com.fr.here.util.PageBean;

/**
 * 权限
 * @author shli
 */
@Service
public class SysPrivilegeService {

	@Resource
	private SysPrivilegeMapper privilegeMapper;

	/**
	 * 根据id获取唯一 权限
	 * @param id
	 * @return
	 */
	public SysPrivilege findSysPrivilege(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = privilegeMapper.findSysPrivileges(cond);
		if (list.size()>0)
			return (SysPrivilege) list.get(0);
		else
			return null;
	}
	
	/**
	 * 根据权限名获取权限
	 * @param name
	 * @return
	 */
	public SysPrivilege findSysPrivilege(String name) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("name", name);
		List list = privilegeMapper.findSysPrivileges(cond);
		if (list.size()>0)
			return (SysPrivilege) list.get(0);
		else
			return null;
	}
	
	
	/**
	 * 根据pid获取所有所属权限
	 * @param id
	 * @return
	 */
	public List<SysPrivilege> findChildSysPrivilege(int pid) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("pid", pid);
		return privilegeMapper.findSysPrivileges(cond);
	}
	
	/**
	 * 获取所有的顶级权限
	 * @return
	 */
	public List<SysPrivilege> findBossSysPrivilege() {
		return privilegeMapper.findBossSysPrivilegeList();
	}
	
	/**
	 * 判断数据库是否存在权限名字
	 * @param name
	 * @return
	 */
	public boolean isExist(String name){
		return findSysPrivilege(name)!=null;
	}

	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findSysPrivileges(String likename,int pageNum,String orderField,String orderDirection,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		cond.put("likename", likename);
		pageBean.setTotalCount(privilegeMapper.findSysPrivilegesCount(cond));//总记录数
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
		pageBean.setList(privilegeMapper.findSysPrivileges(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteSysprivilege(int id){
		boolean flag = false;
		try {
			privilegeMapper.deleteSysPrivileger(id);
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
	public boolean deleteSysprivileges(int[] ids){
		boolean flag = false;
		try {
			privilegeMapper.deleteSysPrivilegers(ids);
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
	public int addSysPrivilege(SysPrivilege sysPrivilege){
		privilegeMapper.addSysPrivilege(sysPrivilege);
		//sysPrivilege.getId();
		return sysPrivilege.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateSysPrivilege(SysPrivilege sysPrivilege){
		boolean flag = false;
		try {
			privilegeMapper.updateSysPrivileger(sysPrivilege);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	

	/**
	 * 当分配权限的时候  privileges 为空
	 * 当获取用户权限的时候 privileges为所有子权限
	 * @param privileges
	 * @return
	 */
	public List<SysPrivilege> makePrivilegeTree(List<SysPrivilege> privileges,List<SysPrivilege> checks){
		//获得所有的顶级权限
		List<SysPrivilege> list = this.findBossSysPrivilege();
		List checkids = new ArrayList();
		List<SysPrivilege> removelist = new ArrayList<SysPrivilege>();
		if(privileges==null){
			privileges = privilegeMapper.findSysPrivileges(new HashMap());
			for (SysPrivilege check : checks) {
				checkids.add(check.getId());
			}
		}
		
		/**
		 * 完成菜单树的构建
		 */
		for (SysPrivilege parent : list) {
			//List<SysPrivilege> childs = new ArrayList<SysPrivilege>();
			for (SysPrivilege child : privileges) {
				if(child.getPid()==parent.getId()){
					if(checkids.contains(child.getId()))  child.setChecked(true);
					parent.getChild().add(child);
				}
			}
			//parent.setChild(childs);
			parent.setParent(true);
			//childs.clear();
			if(parent.getChild().size()==0){
				//list.remove(parent);
				removelist.add(parent);
			}
		}
		list.removeAll(removelist);
		return list;
	}
	
	
	
	
}
