package com.fr.weixin.main;


import org.apache.log4j.Logger;

import com.fr.weixin.menu.Button;
import com.fr.weixin.menu.ClickButton;
import com.fr.weixin.menu.ComplexButton;
import com.fr.weixin.menu.Menu;
import com.fr.weixin.menu.Token;
import com.fr.weixin.menu.ViewButton;
import com.fr.weixin.util.C;
import com.fr.weixin.util.CommonUtil;
import com.fr.weixin.util.MenuUtil;


/*
 * 菜单管理器类
 * */
public class MenuManager {

	//private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	private static Logger log = Logger.getLogger(MenuManager.class);
	//测试号:wx4c3f97ddfce3a676		2d4ba187dc80f9746244c6a33743d945
	//wx51ace996bab5978a		eb3161e653fdbd3bf088febec577e94a
	
	//fr 测试号 wx7dd2e6617e10575a     c43b8959d97dddb2e48873d57ffcb70c
	//wxea67481e5fda716d               eb91a85e333d9224828dd947f377dd95 
	
	
	
	/*
	 * 定义菜单结构
	 * */
	private static Menu getMenu(){
		
		ClickButton btn11 = new ClickButton();
		btn11.setName("重点业务");
		btn11.setType("click");
		btn11.setKey("business");
//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx51ace996bab5978a&redirect_uri=http%3A%2F%2Fwww.chishatoday.com%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
		//http://mp.weixin.qq.com/s?__biz=MzAwMTM5MjIwOQ==&mid=207326934&idx=4&sn=6b088d982e632893dc1a23345692cd2e#rd
		ClickButton btn12 = new ClickButton();
		btn12.setName("技术资源");
		btn12.setType("click");
		btn12.setKey("source");
		
		
		ClickButton btn13 = new ClickButton();
		btn13.setName("员工活动");
		btn13.setType("click");
		btn13.setKey("active");
		
		ClickButton btn21 = new ClickButton();
		btn21.setName("最新供应");
		btn21.setType("click");
		btn21.setKey("supply");
		
		ClickButton btn22 = new ClickButton();
		btn22.setName("最新需求");
		btn22.setType("click");
		btn22.setKey("demand");
		
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("我有供需");
		btn23.setType("view");
		btn23.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7dd2e6617e10575a&redirect_uri=http%3A%2F%2Faclululu.ngrok.cc%2Ffr-here%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		ClickButton btn24 = new ClickButton();
		btn24.setName("我的供应");
		btn24.setType("click");
		btn24.setKey("isupply");
		
		ClickButton btn25 = new ClickButton();
		btn25.setName("我的需求");
		btn25.setType("click");
		btn25.setKey("idemand");

		
		ViewButton btn31 = new ViewButton();
		btn31.setName("操作指引");
		btn31.setType("view");
		btn31.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313023&idx=1&sn=1b4f803b93e2f1b2ff612f40c8b9db08#rd");
		//http://aclululu.ngrok.cc/fr-here/rest/app/supply/view?id=8
		ViewButton btn32 = new ViewButton();
		btn32.setName("关于我们");
		btn32.setType("view");
		btn32.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313025&idx=1&sn=7aa0fc7be0b328297875af0d657f26c9#rd");
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("加入我们");
		btn33.setType("view");
		btn33.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxODcxMTgzNQ==&mid=308313026&idx=1&sn=b7fac510b39fe3de2c01aab4e0b47abc#rd");
		
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("佛软新闻");
		mainBtn1.setSub_button(new Button[] {btn11,btn12,btn13});
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("供需平台");
		mainBtn2.setSub_button(new Button[] {btn21,btn22,btn23,btn24,btn25});
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("特色服务");
		mainBtn3.setSub_button(new Button[] {btn31,btn32,btn33});
		
		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBtn1,mainBtn2,mainBtn3});
		
		return menu;
	}
	
	public static void main(String args[]){
	
		//调用接口获取凭证
		Token token = CommonUtil.getToken(C.APPID, C.APPSECRET);
		
		if(null != token){
			//创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
			//判断菜单创建结果
			if(result)
				log.info("菜单创建成功");
			else
				log.info("菜单创建失败");
		}
		
		
	}
}
