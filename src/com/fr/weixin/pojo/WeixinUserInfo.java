package com.fr.weixin.pojo;


import org.apache.log4j.Logger;

import com.fr.weixin.util.CommonUtil;

import net.sf.json.JSONObject;


public class WeixinUserInfo {
  
	//private static Logger log = LoggerFactory.getLogger(WeixinUserInfo.class);
	private static Logger log = Logger.getLogger(WeixinUserInfo.class);
	//用户的标识
	private String openId;
	
	//关注状态(1是关注,0是未关注)，未关注时获取不到其余信息
	private int subscribe;
	
	//用户关注时间，为时间戳。如果用户曾多次关注，则取最后一次关注的时间
	private String subscribe_time;
	
	//昵称
	private String nickname;
	
	//用户性别（1是男性，2是女性，0是未知）
	private int sex;
	
	//用户所在国家
	private String country;
	
	//用户所在省份
	private String province;
	
	//用户所在城市
	private String city;
	
	//用户的语言
	private String language;
	
	//用户头像
	private String headImgUrl;
	
	private String unionid;
	
	private String remark;
	
	private String groupid;

	private String appid;
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	public String getAppid() {
		return "gh_da714df8c3f8";
	}


	/**获取用户信息**/
	public static WeixinUserInfo getUserInfo(String accessToken, String openId){
		
		WeixinUserInfo weixinUserInfo = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		//获取用户信息
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		
		if(null != jsonObject){
			try{
				weixinUserInfo = new WeixinUserInfo();
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e){
				if (0 == weixinUserInfo.getSubscribe()){
					log.error("用户"+weixinUserInfo.getOpenId()+"已取消关注");
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败  errcode:"+errorCode+" errmsg:{}"+errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}
}
