package com.fr.weixin.util;

import java.util.List;
import org.apache.log4j.Logger;
import com.fr.weixin.pojo.Oauth2Token;
import com.fr.weixin.pojo.SNSUserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class OauthUtil {
	
	//private static Logger log = LoggerFactory.getLogger(OauthUtil.class);
	private static Logger log = Logger.getLogger(OauthUtil.class);
	/**获取网页授权凭证**/
	public static Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code){
		
		Oauth2Token wat = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		
		//获取网页授权凭证
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				wat = new Oauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:"+errorCode+" errmsg:{}"+ errorMsg);
			}
		}
		return wat;
	}
	
	/**刷新网页授权凭证**/
	public static Oauth2Token refreshOauth2AccessToken(String appId, String refreshToken){
		
		Oauth2Token wat = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		//刷新网页凭证
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				wat = new Oauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("刷新网页授权凭证失败 errcode:"+errorCode+" errmsg:{}"+ errorMsg);
			}
		}
		return wat;
	}
	
	
	/**通过网页授权获取用户信息**/
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId){
		
		SNSUserInfo snsUserInfo = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		//通过网页授权获取用户信息
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				snsUserInfo = new SNSUserInfo();
				//用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				//昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				//性别
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				//用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				//省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				//城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				//头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				//用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e){
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:"+errorCode+"errmsg:{}"+ errorMsg);
			}
		}
		return snsUserInfo;
	}
	
}
