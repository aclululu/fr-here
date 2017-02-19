package com.fr.here.dao.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fr.here.dao.SysUserMapper;
import com.fr.here.model.SysUser;
@RunWith(value = SpringJUnit4ClassRunner.class)
///fr-here/WebRoot/WEB-INF/config/applicationContext.xml
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestSysUserDao {
/*	 @Autowired 
	  private SysUserMapper sysUserMapper;  
	 //@Test
	 @Transactional 
	  public void findUserByIDTest(){
	        SysUser entity = sysUserMapper.findUserByID(1);  
	        System.out.println("" + entity.getAccount());  
	 } 
	 
	 @Transactional 
	// @Test
	  public void getSysUserListTest(){
	        List<SysUser> list = sysUserMapper.getSysUserList();  
	        for (SysUser sysUser : list) {
	        	System.out.println("" + sysUser.getAccount()+"--"+sysUser.getStatus());  
			}
	 } 
	 
	 @Transactional 
	 //@Test
	  public void addSysUserTest(){
	      SysUser user = new SysUser("j-ui", "123", 0, 1);
	      sysUserMapper.addSysUser(user);
	      System.out.println(user.getId());
	 } 
	 
	 
	 @Transactional 
	 @Test
	  public void updateSysUserTest(){
	      SysUser user = new SysUser(7,"test", "123", 0, 1);
	      sysUserMapper.updateUser(user);
	      System.out.println(user.getId());
	 } 
	 */
	 
	 
	 
}  
