<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增视频</title> 
</head>
<body>
<form action="/fr-here/rest/app/movie/addorupdate?navTabId=/fr-here/rest/app/movie/list&callbackType=closeCurrent" class="pageForm required-validate" method="post"  onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
	<div class="pageFormContent" layoutH="53">
	
	<div class="unit">
				<label>视频标题</label>
				<input name="title" maxlength="50" class="required " type="text" size="30"  alt="请输入视频标题"
				<c:if test="${model.title != null}">value="${model.title }"</c:if>
				/>
			
		</div>
		
	
		<div class="unit">
				<label>说明</label>
				<input name="abstract_" maxlength="200" class="required " type="text" size="200"  alt="请输入视频说明"
				<c:if test="${model.abstract_ != null}">value="${model.abstract_ }"</c:if>/>
				<input type="hidden" name="id" value="${model.id}"/>
				
		</div>
		
		<div class="unit">
		<label>选择视频封面</label>  
		<input  type="file" name="pic" size="10" contentEditable="false" onChange="checkFile(this.value)" />
		</div>
		
		<div class="unit">
		<label>选择视频</label>  
		<input  type="file" name="movie" size="10" contentEditable="false" onChange="checkFile(this.value)" />
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