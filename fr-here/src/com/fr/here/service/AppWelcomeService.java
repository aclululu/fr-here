/**
 * 
 */
package com.fr.here.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.AppWelcomeMapper;
import com.fr.here.model.AppWelcome;
import com.fr.here.util.PageBean;

/**
 * app图片
 * @author shli
 */
@Service
public class AppWelcomeService {

	@Resource
	private AppWelcomeMapper appWelcomeMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public AppWelcome findAppWelcome(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = appWelcomeMapper.findAppWelcomes(cond);
		if (list.size()>0)
			return (AppWelcome) list.get(0);
		else
			return null;
	}
	
	/**
	 * 获取最新的APP图片  即id最大
	 * @param 
	 * @return
	 */
	public AppWelcome findNewAppWelcome() {
		HashMap cond = new HashMap<String, Object>();
		cond.put("sort", "id");
		cond.put("order", "desc");
		List list = appWelcomeMapper.findAppWelcomes(cond);
		if (list.size()>0)
			return (AppWelcome) list.get(0);
		else
			return new AppWelcome();
	}


	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findAppWelcomes(int pageNum,String orderField,String orderDirection,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		pageBean.setTotalCount(appWelcomeMapper.findAppWelcomesCount(cond));//总记录数
		
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
		pageBean.setList(appWelcomeMapper.findAppWelcomes(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteAppWelcome(int id){
		boolean flag = false;
		try {
			appWelcomeMapper.deleteAppWelcome(id);
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
	public boolean deleteAppWelcomes(int[] ids){
		boolean flag = false;
		try {
			appWelcomeMapper.deleteAppWelcomes(ids);
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
	public int addAppWelcome(AppWelcome welcome){
		appWelcomeMapper.addAppWelcome(welcome);
		//sysPrivilege.getId();
		return welcome.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateAppWelcome(AppWelcome welcome){
		boolean flag = false;
		try {
			appWelcomeMapper.updateAppWelcome(welcome);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
}
