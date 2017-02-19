<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限菜单管理</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/privilege/update?navTabId=/fr-here/rest/sys/privilege/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
	<div class="unit">
		<p>
			<label>权限父菜单：</label>
			<select class="combox required"   name="pid">
				<option value="0">顶级权限</option>
				<c:forEach items="${privileges}" var="item">
          			<option value="${item.id}"<c:if test="${model.pid == item.id}">selected='selected'</c:if>>${item.name}</option>
          		</c:forEach>
			</select>	
		</p>
		<p>
			<label>权限名：</label>
			<input name="name"  maxlength="20" value="${model.name}" class="required" type="text" size="30"/>
		</p>
		</div>
		<div class="unit">
		<p>
			<label>权限url：</label>
			<input name="url"  maxlength="100" value="${model.url}" type="text" size="30"/>
		</p>
		
		<p>
			<label>权限说明：</label>
			<input name="info"  maxlength="100" value="${model.info}" type="text" size="30"/>
			<input type="hidden" name="id" value="${model.id}"/>
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