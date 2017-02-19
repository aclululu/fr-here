<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配角色</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/user/update?navTabId=/fr-here/rest/sys/user/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
		<p>
			<label>管理员名：</label>
			<input readonly="true" name="account" value="${model.account}" type="text" />
		</p>
		<p>
			<label>分配角色：</label>	
			<select class="combox required"  name="roleid">
				<option value="">请选择</option>
				<c:forEach items="${roles}" var="item">
          			<option value="${item.id}"<c:if test="${model.roleid == item.id}">selected='selected'</c:if>>${item.name}</option>
          		</c:forEach>
			</select>
			
			<input type="hidden" name="id" value="${model.id }"/>
		</p>
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