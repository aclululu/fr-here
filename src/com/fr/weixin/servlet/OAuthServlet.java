package com.fr.weixin.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.weixin.pojo.Oauth2Token;
import com.fr.weixin.pojo.SNSUserInfo;
import com.fr.weixin.util.C;
import com.fr.weixin.util.OauthUtil;


/**授权后的回调请求处理**/
@SuppressWarnings("serial")
public class OAuthServlet  extends HttpServlet{
	protected Map<String, Object> session = null;
	//fr 测试号 wx7dd2e6617e10575a     c43b8959d97dddb2e48873d57ffcb70c
	//wxea67481e5fda716d               eb91a85e333d9224828dd947f377dd95 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//用户同意授权后，能获取到code
		String code = request.getParameter("code");
		//用户同意授权
		if(!"authdeny".equals(code)){
			//获取网页授权access_token
			Oauth2Token oauth2Token = OauthUtil.getOauth2AccessToken(C.APPID, C.APPSECRET, code);
			//网页授权接口访问凭证
			String accessToken = oauth2Token.getAccessToken();
			//用户标识
			String openId = oauth2Token.getOpenId();
			//获取用户信息
			SNSUserInfo snsUserInfo = OauthUtil.getSNSUserInfo(accessToken, openId);
			System.out.println(snsUserInfo.toString());
			//设置要传递的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		//跳转到index.jsp
		request.getRequestDispatcher(C.SUPPLYTOADDURL).forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
}
