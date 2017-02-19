<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>佛软一体化后台登录系统</title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/adminLogin.css"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>
<body id="userlogin_body">
<form action="/fr-here/rest/sys/login" method="post" onsubmit="return CheckForm ()">
	<div></div>
	<div id="user_login">
		<dl>
			<dd id="user_top">
				<ul>
					<li class="user_top_l"></li>
					<li class="user_top_c"></li>
					<li class="user_top_r"></li>
				</ul>
			</dd>
			<dd id="user_main">
		  		<ul>
		    		<li class="user_main_l"></li>
					<li class="user_main_c">
						<div class="user_main_box">
							<ul>
								<li class="user_main_text">用户名： </li>
								<li class="user_main_input">
									<input class="txtUserNameCssClass" id="name" name="name" maxlength="20"/>
									<i style="vertical-align:middle;display:inline-block;height:100%;width:0;overflow:hidden"></i>
								</li>
							</ul>
					    <ul>
					      <li class="user_main_text">密　码： </li>
					      <li class="user_main_input">
					      	<input type="password" class="txtPasswordCssClass" id="password" name="password"/>
					      	<i style="vertical-align:middle;display:inline-block;height:100%;width:0;overflow:hidden"></i>
					      </li>
							</ul>
						</div>
					</li>
		    		<li class="user_main_r">
		    			<input type="image" class="iBtnEnterCssClass" id="ibtnenter" name="ibtnenter" style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px" src="${ctx}/images/user_botton.gif"/>
		    		</li>
		    	</ul>
		    </dd>
  			<dd id="user_bottom">
	  			<ul>
				    <li class="user_bottom_l"></li>
				    <li class="user_bottom_c"></li>
				    <li class="user_bottom_r"></li>
				</ul>
			</dd>
		</dl>
	</div>
	<div></div>
</form>
</body>
</html>
<script language="javascript">
document.getElementById("name").focus();
function CheckForm ()
{
	var name = document.getElementById("name");
	if (name.value=="")
	{
		alert ("请输入用户名!");
		name.focus();
		return false;
	}
	
	var pass = document.getElementById("password");
	if (pass.value=="")
	{
		alert ("请输入密码！");
		pass.focus();
		return false;
	}
}
var msg="${requestScope.tipMessage}";
if(msg!=""){
	alert(msg);
}
</script>
