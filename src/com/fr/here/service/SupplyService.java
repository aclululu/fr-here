/**
 * 
 */
package com.fr.here.service;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fr.here.dao.SupplyMapper;
import com.fr.here.model.News;
import com.fr.here.model.Supply;
import com.fr.here.util.PageBean;

/**
 * 供需
 * @author shli
 */
@Service
public class SupplyService {

	@Resource
	private SupplyMapper supplyMapper;

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Supply findSupply(int id) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("id", id);
		List list = supplyMapper.findSupplys(cond);
		if (list.size()>0)
			return (Supply) list.get(0);
		else
			return null;
	}
	
	/**
	 *根据标题获取
	 * @param name
	 * @return
	 */
	public Supply findSupply(String title) {
		HashMap cond = new HashMap<String, Object>();
		cond.put("title", title);
		List list = supplyMapper.findSupplys(cond);
		if (list.size()>0)
			return (Supply) list.get(0);
		else
			return null;
	}
	
	/**
	 *获取
	 * @param name
	 * @return
	 */
	public List<Supply> findSupplys(HashMap<String, Object> cond) {
		return supplyMapper.findSupplys(cond);
	}
	
	
	/**
	 * 查看传入的供需栏目是否有供需
	 * @param cid  栏目id
	 */
	public int columnHasSupply(int cid){
		HashMap cond = new HashMap<String, Object>();
		cond.put("cid", cid);
		List list = supplyMapper.findSupplys(cond);
		return list.size();
	}

	/**
	 * @param cond    查询条件
	 * @param pageNum      第几页
	 * @param orderField    排序字段
	 * @param orderDirection  排序规则
	 * @param pageBean   分页信息
	 * @return
	 */
	public PageBean findSupply(HashMap<String,Object> cond,int pageNum,PageBean pageBean){
		pageBean.setTotalCount(supplyMapper.findSupplyCount(cond));//总记录数
		
		int totalPage = PageBean.counttotalPage(pageBean.getPageSize(), pageBean.getTotalCount());//总页数
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(pageNum);
		int offset = PageBean.countOffset(pageBean.getPageSize(), pageNum);	//当前开始记录
		cond.put("length",pageBean.getPageSize());
		cond.put("offset", offset);
		pageBean.setList(supplyMapper.findSupplys(cond));
		return pageBean;
	}
	
	/**
	 * 删除对应id 的 权限
	 * @param id
	 * @return
	 */
	public boolean deleteSupply(int id){
		boolean flag = false;
		try {
			supplyMapper.deleteSupply(id);
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
	public boolean deleteSupply(int[] ids){
		boolean flag = false;
		try {
			supplyMapper.deleteSupplys(ids);
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
	public int addSupply(Supply supply){
		supplyMapper.addSupply(supply);
		//sysPrivilege.getId();
		return supply.getId();
	}
	
	/**
	 * 更新一条记录
	 * @param sysPrivilege
	 * @return
	 */
	public boolean updateSupply(Supply supply){
		boolean flag = false;
		try {
			supplyMapper.updateSupply(supply);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	/**
	 * 批量发布 or 撤销发布
	 * @param ids
	 * @param status
	 * @return
	 */
	public boolean updateSupplyStatus(int[] ids,int status){
		boolean flag = false;
		HashMap<String,Object> cond = new HashMap<String, Object>();
		cond.put("ids", ids);
		cond.put("status", status);
		try{
			supplyMapper.updateSupplys(cond);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 发布 or 撤销
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateSupplyStatus(int id,int status){
		return updateSupplyStatus(new int[]{id},status); 
	}
	
	/**
	 * 微信公众号接口
	 * sio 1 供应   -1 需求
	 * @return
	 */
	public List<Supply> getWxPushSupply(int sio){
		return supplyMapper.getWxPushSupply(sio);
	}
	
	/**
	 * 微信公众号接口
	 * 按照供需的标题模糊搜索
	 * @param likename   
	 * @param sio 1 供应   -1 需求
	 * @return
	 */
	public List<Supply> getWxPushSupply(String likename,int sio){
		HashMap cond = new HashMap<String, Object>();
		cond.put("likename", likename);
		cond.put("order", "desc");
		cond.put("status", 1);
		cond.put("sio", sio);
		cond.put("sort", "id");
		cond.put("length", 5);
		cond.put("offset", 0);
		return supplyMapper.findSupplys(cond);
	}
	
	/**
	 * 查询我的供需  
	 * @param sio 1 供应   -1 需求
	 * @return
	 */
	public List<Supply> getWxPushSupply(int sio,String openid){
		HashMap cond = new HashMap<String, Object>();
		cond.put("openid", openid);
		cond.put("order", "desc");
		cond.put("sio", sio);
		cond.put("sort", "id");
		cond.put("length", 5);
		cond.put("offset", 0);
		return supplyMapper.findSupplys(cond);
	}
}
