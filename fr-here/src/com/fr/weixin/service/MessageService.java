package com.fr.weixin.service;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fr.here.service.NewsService;
import com.fr.here.service.SupplyService;
import com.fr.weixin.message.resp.NewsMessage;
import com.fr.weixin.message.resp.TextMessage;
import com.fr.weixin.util.MessageUtil;
import com.fr.weixin.util.tuling.TuringApiProcess;

/**
  * @Description:处理微信发来的事件请求 
  * @Author: shli
  * @Create Date: 2016-7-19上午08:41:03
 */
public class MessageService {
	private NewsService newsService;
	private SupplyService supplyService;
	
	public MessageService(HttpServletRequest request) {
    	ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
    	newsService = (NewsService) ctx.getBean("newsService");
    	supplyService = (SupplyService) ctx.getBean("supplyService");
	}
	public String processMessageRequest(Map<String, String> requestMap) {
		String respXml = null;
	        // 发送方帐号（open_id）  
	    String fromUserName = requestMap.get("FromUserName");  
	        // 公众帐号  
	    String toUserName = requestMap.get("ToUserName"); 
	    
	  //文本消息
        TextMessage textMessage = new TextMessage();  
        textMessage.setToUserName(fromUserName);  
        textMessage.setFromUserName(toUserName);  
        textMessage.setCreateTime(new Date().getTime());  
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
        
        //图文消息
        NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		
		String con = requestMap.get("Content");
		/**
		 * 您好，请回复以下数字选择服务类型：\n1 佛软新闻\n2 供应服务\n3 需求服务\n4 了解我们\n5 加入我们\n\n点击查看  <a href="http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313023&idx=1&sn=1b4f803b93e2f1b2ff612f40c8b9db08#rd">帮助手册</a> 
		 */
		if(con!=null&&!con.equals("")){
			if("1".equals(con)){
				textMessage.setContent("佛软新闻\n\n回复：新闻+标题关键字\n例如：新闻党建\n\n回复“?”显示主菜单");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if("2".equals(con)){
				textMessage.setContent("供应服务\n\n回复：供应+标题关键字\n例如：供应水杯\n\n回复“?”显示主菜单");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if("3".equals(con)){
				textMessage.setContent("需求服务\n\n回复：需求+标题关键字\n例如：需求水杯\n\n回复“?”显示主菜单");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if("4".equals(con)){
				textMessage.setContent("点击进入  <a href=\"http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313025&idx=1&sn=7aa0fc7be0b328297875af0d657f26c9#rd\">了解我们</a>");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if("5".equals(con)){
				textMessage.setContent("点击进入  <a href=\"http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313026&idx=1&sn=b7fac510b39fe3de2c01aab4e0b47abc#rd\">加入我们</a>");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if("?".equals(con)||"？".equals(con)){
				textMessage.setContent("您好，请回复以下数字选择服务类型：\n1 佛软新闻\n2 供应服务\n3 需求服务\n4 了解我们\n5 加入我们\n\n点击查看  <a href=\"http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313023&idx=1&sn=1b4f803b93e2f1b2ff612f40c8b9db08#rd\">帮助手册</a>");
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}else if(con.length()>2){
				String con2 = con.substring(0, 2);
				String con22 = con.substring(2, con.length());
				if(con2.equals("新闻")){
					respXml = EventService.installNews(textMessage, newsMessage, newsService.getWxPushNews(con22)) ;
				}else if(con2.equals("供应")){
					respXml = EventService.installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(con22, 1)) ;
				}else if(con2.equals("需求")){
					respXml = EventService.installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(con22, -1)) ;
				}
			}
			if(respXml==null||respXml.equals("")){
				//交给小水处理吧
				textMessage.setContent(TuringApiProcess.getTulingResult(con));
	        	respXml = MessageUtil.textMessageToXml(textMessage);
			}
		}else{
        	textMessage.setContent("您好！很高兴为您服务，需要咨询请留言，我们客服人员会尽快给您回复，或者输入“？”使用菜单自助服务，谢谢！");
        	respXml = MessageUtil.textMessageToXml(textMessage);
		}
		return respXml;
	}
	
}
