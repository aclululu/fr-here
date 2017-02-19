<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员管理</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/user/add?navTabId=/fr-here/rest/sys/user/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
		
		<p>
			<label>管理员名：</label>
			<input name="account" maxlength="20" class="required" type="text" size="30"  alt="请输入管理员名"/>
		</p>
	
		<p>
			<label>密码：</label>
			<input name="password" type="password" size="30" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
		</p>
		<input type="hidden" name="status" value="1" />
		
		
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
		</ul>
	</div>
</form>
</div>	
</body>
</html>