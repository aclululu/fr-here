<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/privilege/add?navTabId=/fr-here/rest/sys/privilege/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
	<div class="unit">
		<p>
			<label>权限父菜单：</label>
			<select class="combox" name="pid">
				<option value="0">顶级权限</option>
				<c:forEach items="${privileges}" var="item">
          			<option value="${item.id}">${item.name}</option>
          		</c:forEach>
			</select>
		</p>
		<p>
			<label>权限名：</label>
			<input name="name" maxlength="20" class="required" type="text" size="30"  alt="请输入权限名"/>
		</p>
	</div>
	<div class="unit">
		<p>
			<label>权限url：</label>
			<input maxlength="100" name="url"/>
		</p>
		<p>
			<label>权限说明：</label>
			<input maxlength="100" name="info"/>
		</p>
	</div>
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