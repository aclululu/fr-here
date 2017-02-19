/**
 * 
 */
package com.fr.here.service;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.SupplyColumnMapper;
import com.fr.here.model.SupplyColumn;
import com.fr.here.util.PageBean;

/**
 *供需栏目
 * @author shli
 */
@Service
public class SupplyColumnService {

	@Resource
	private SupplyColumnMapper supplyColumnMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public SupplyColumn findSupplyColumn(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = supplyColumnMapper.findSupplyColumns(cond);
		if (list.size()>0)
			return (SupplyColumn) list.get(0);
		else
			return null;
	}
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public List<SupplyColumn> findSupplyColumns(HashMap cond) {
		return supplyColumnMapper.findSupplyColumns(cond);
	}

	/**
	 * @param likename  查询条件
	 * @param page      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @return
	 */
	public PageBean findSupplyColumns(String likename,int pageNum,PageBean pageBean){
		//PageBean pageBean = new PageBean();
		HashMap cond = new HashMap<String,Object>();
		cond.put("likename", likename);
		pageBean.setTotalCount(supplyColumnMapper.findSupplyColumnsCount(cond));//总记录数
		int totalPage = PageBean.counttotalPage(pageBean.getPageSize(), pageBean.getTotalCount());//总页数
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(pageNum);
		
		int offset = PageBean.countOffset(pageBean.getPageSize(), pageNum);	//当前开始记录
		cond.put("length",pageBean.getPageSize());
		cond.put("offset", offset);
		cond.put("sort", "id");
		cond.put("order", "desc");
		pageBean.setList(supplyColumnMapper.findSupplyColumns(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的栏目
	 * @param id
	 * @return
	 */
	public boolean deleteSupplyColumn(int id){
		boolean flag = false;
		try {
			supplyColumnMapper.deleteSupplyColumn(id);
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
	public int addSupplyColumn(SupplyColumn column){
		supplyColumnMapper.addSupplyColumn(column);
		//sysPrivilege.getId();
		return column.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateSupplyColumn(SupplyColumn column){
		boolean flag = false;
		try {
			supplyColumnMapper.updateSupplyColumn(column);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
}
