package com.fr.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fr.here.model.News;
import com.fr.here.service.NewsService;
import com.fr.weixin.main.MenuManager;
import com.fr.weixin.message.resp.TextMessage;
import com.fr.weixin.util.MessageUtil;

public class CoreService {
	private static Logger log = Logger.getLogger(CoreService.class);
	private static MessageService messageService;
	private static EventService eventService;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respXml = null;  
		if(messageService==null) messageService = new MessageService(request);
		if(eventService==null) eventService = new EventService(request);
		// 默认返回的文本消息内容  
        try { 
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");
            
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
            	// 处理文本消息  
            	respXml = messageService.processMessageRequest(requestMap);
            }  else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {	
                //处理事件推送
            	respXml = eventService.processEventRequest(requestMap);
            }
            
            //其他类型的消息暂不自动处理   需要人工处理
        	// 回复文本消息  
            if(respXml==null||respXml.equals("")){
                TextMessage textMessage = new TextMessage();  
                textMessage.setToUserName(fromUserName);  
                textMessage.setFromUserName(toUserName);  
                textMessage.setCreateTime(new Date().getTime());  
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
            	textMessage.setContent("您好！很高兴为您服务，需要咨询请留言，我们客服人员会尽快给您回复，或者输入“？”使用菜单自助服务，谢谢！");
            	respXml = MessageUtil.textMessageToXml(textMessage);	
            }
        } catch (Exception e) {  
        	log.error("微信请求解析出现异常");
            e.printStackTrace();  
        }  
  
        return respXml;  
    }  
}
