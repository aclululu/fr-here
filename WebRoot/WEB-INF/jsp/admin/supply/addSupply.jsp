<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增供需</title>
<script type="text/javascript">
	if (CKEDITOR.instances['content']) {
		CKEDITOR.remove(CKEDITOR.instances['content']);
	}
	var editor = CKEDITOR.replace('content',{height : 600});
	//var content = CKEDITOR.instances.content.getData();
</script>	 
</head>
<body>
<form action="/fr-here/rest/sys/supply/addorupdate?navTabId=/fr-here/rest/sys/supply/list&callbackType=closeCurrent" class="pageForm required-validate" method="post"  onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
	<div class="pageFormContent" layoutH="53">
		<div class="unit">
				<label>供需标题</label>
				<input name="title" maxlength="100" class="required " type="text" size="100"  alt="请输入供需标题"
				<c:if test="${model.title != null}">value="${model.title }"</c:if>
				/>
		</div>
		<div class="unit">
			<p>
				<label>所属栏目</label>
				<select class="combox" name="cid">
				<c:forEach items="${supplycolumns}" var="item">
          			<option value="${item.id}" <c:if test="${model.cid == item.id}">selected='selected'</c:if>>${item.name}</option>
          		</c:forEach>
			</select>
			</p>
			<p>
				<label>供或需</label>
				<select class="combox" name="sio">
				<option value="-1" <c:if test="${model.sio==-1}">selected='selected'</c:if> <c:if test="${model.sio==null}">selected='selected'</c:if>>需求</option>
				<option value="1" <c:if test="${model.sio==1}">selected='selected'</c:if>>供应</option>
				</select>
			</p>
		</div>
		<div class="unit">
			<p>
				<label>地址</label>  	
		    	<input name="address" maxlength="200"  type="text" size="30"  class="required" 
		    	<c:if test="${model.address != null}">value="${model.address }"</c:if>/>
		    	<input type="hidden" name="id" value="${model.id}"/>
			</p>
			<p>
				<label>电话</label>
				<input name="phone" maxlength="12"  type="text" size="20"  class="required phone" 
				<c:if test="${model.phone != null}">value="${model.phone }"</c:if>/>
			</p>
		</div>

		<div class="unit">
			<textarea cols="70" id="content" name="content" rows="10">
			<c:if test="${model.content != null}">${model.content }</c:if></textarea>
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