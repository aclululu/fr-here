<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员管理</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/user/update?navTabId=/fr-here/rest/sys/user/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
		<p>
			<label>管理员名：</label>
			<input name="account" maxlength="20" class="required" type="text" size="30" value="${model.account}"/>
		</p>
		<p>
			<label>密码：</label>
			<input name="password" value="${model.password}" type="password" size="30" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
			
			<input type="hidden" name="id" value="${model.id}"/>
		</p>
		<p>
			<label>状态：</label>
			<select class="combox"  class="required"  max="1" min="0" name="status">
				<option value="1" <c:if test="${model.status == 1}">selected='selected'</c:if>>正常</option>
				<option value="-1" <c:if test="${model.status == -1}">selected='selected'</c:if>>锁定</option>
			</select>
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