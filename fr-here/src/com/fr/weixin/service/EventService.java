package com.fr.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fr.here.model.News;
import com.fr.here.model.Supply;
import com.fr.here.service.NewsService;
import com.fr.here.service.SupplyService;
import com.fr.weixin.message.resp.Article;
import com.fr.weixin.message.resp.NewsMessage;
import com.fr.weixin.message.resp.TextMessage;
import com.fr.weixin.util.C;
import com.fr.weixin.util.MessageUtil;

/**
  * @Description:处理微信发来的事件请求 
  * @Author: shli
  * @Create Date: 2016-7-19上午08:41:03
 */
public class EventService {
	private NewsService newsService;
	private SupplyService supplyService;
	
	public EventService(HttpServletRequest request) {
    	ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
    	newsService = (NewsService) ctx.getBean("newsService");
    	supplyService = (SupplyService) ctx.getBean("supplyService");
	}
	
	public String processEventRequest(Map<String, String> requestMap) {
		String respXml = null;
	        // 发送方帐号（open_id）  
	    String fromUserName = requestMap.get("FromUserName");  
	        // 公众帐号  
	    String toUserName = requestMap.get("ToUserName"); 
	    // 事件类型  
        String eventType = requestMap.get("Event");    
	    
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
        // 订阅  
        if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
        	textMessage.setContent("欢迎关注佛软公众号！\ue056 \n\n专注于政府系软件解决方案！爱编程，更爱生活！\n\n回复“？”调出菜单，快快体验吧，么么哒");
        	respXml = MessageUtil.textMessageToXml(textMessage);
        	//取消订阅
        }else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
        	return "success";
        	//菜单点击事件
        }else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
        	// 事件KEY值，与创建菜单时的key值对应
        	List<News> newss = null;
			String eventKey = requestMap.get("EventKey");
			if("business".equals(eventKey)){
				List<News> news = newsService.getWxPushNews(6);
				respXml = installNews(textMessage, newsMessage, newsService.getWxPushNews(6));
			}else if("source".equals(eventKey)){
				respXml = installNews(textMessage, newsMessage, newsService.getWxPushNews(7));
			}else if("active".equals(eventKey)){
				respXml = installNews(textMessage, newsMessage, newsService.getWxPushNews(8));
			}else if("supply".equals(eventKey)){
				respXml = installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(1));
			}else if("demand".equals(eventKey)){
				respXml = installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(-1));
			}else if("isupply".equals(eventKey)){
				respXml = installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(1, fromUserName));
			}else if("idemand".equals(eventKey)){
				respXml = installSupply(textMessage, newsMessage, supplyService.getWxPushSupply(-1, fromUserName));
			}
        }
		return respXml;
	}
	
	/**
	 * 根据查询出的新闻生成新闻图文
	 * @param newsMessage
	 * @param newss
	 * @return
	 */
	public static String installNews(TextMessage textMessage,NewsMessage newsMessage,List<News> newss){
		if(newss==null||newss.size()==0){
			textMessage.setContent("对不起，木有找到您查询的新闻信息");
			return MessageUtil.textMessageToXml(textMessage);
		} 
		List<Article> articleList = new ArrayList<Article>();
		Iterator<News> iter = newss.iterator();
		while(iter.hasNext()){
			News news = iter.next();
			Article artic = new Article();
			artic.setTitle(news.getTitle());
			artic.setDescription(news.getAbstract_());
			artic.setPicUrl(C.HOST+"/"+news.getPicture());
			artic.setUrl(C.HOST+C.NEWVIEWURL+news.getId());
			articleList.add(artic);
		}
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}
	
	
	/**
	 * 根据查询出的供需生成供需图文
	 * @param newsMessage
	 * @param newss
	 * @return
	 */
	public static String installSupply(TextMessage textMessage,NewsMessage newsMessage,List<Supply> supplys){
		if(supplys==null||supplys.size()==0){
			textMessage.setContent("对不起，木有找到您查询的供需信息");
			return MessageUtil.textMessageToXml(textMessage);
		}
		List<Article> articleList = new ArrayList<Article>();
		Iterator<Supply> iter = supplys.iterator();
		while(iter.hasNext()){
			Supply supply = iter.next();
			Article artic = new Article();
			artic.setTitle(supply.getTitle());
			artic.setDescription(supply.getAddress());
			artic.setPicUrl("http://www.foshan.com.cn/Company_Web/index/Image/beijing2.jpg");
			artic.setUrl(C.HOST+C.SUPPLYVIEWURL+supply.getId());
			articleList.add(artic);
		}
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}
	
	
	
	
	
}
