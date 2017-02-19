<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>更新供需栏目</title>
</head>
<body>
<div class="pageContent">
<form action="/fr-here/rest/sys/supplycolumn/update?navTabId=/fr-here/rest/sys/supplycolumn/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
		<p>
			<label>供需栏目名：</label>
			<input name="name" maxlength="20" class="required" type="text" size="30" value="${model.name}"/>
			<input type="hidden" name="id" value="${model.id}"/>
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