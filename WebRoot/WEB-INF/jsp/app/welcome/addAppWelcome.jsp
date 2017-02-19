<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增欢迎图片</title> 
</head>
<body>
<form action="/fr-here/rest/app/welcome/addorupdate?navTabId=/fr-here/rest/app/welcome/list&callbackType=closeCurrent" class="pageForm required-validate" method="post"  onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
	<div class="pageFormContent" layoutH="53">
		<div class="unit">
				<label>说明</label>
				<input name="info" maxlength="100" class="required " type="text" size="100"  alt="请输入图片说明"
				<c:if test="${model.info != null}">value="${model.info }"</c:if>/>
				<input type="hidden" name="id" value="${model.id}"/>
				
		</div>
		
		<div class="unit">
		<label>选择图片</label>  
		<input  type="file" name="file" size="10" contentEditable="false" onChange="checkFile(this.value)" />
		</div>
	</div>
	
	
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
		</ul>
	</div>
</form>		
</body>
</html>