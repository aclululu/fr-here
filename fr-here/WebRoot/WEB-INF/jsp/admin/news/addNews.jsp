<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增新闻</title>
<script type="text/javascript">
	if (CKEDITOR.instances['content']) {
		CKEDITOR.remove(CKEDITOR.instances['content']);
	}
	var editor = CKEDITOR.replace('content',{height : 600});
	//var content = CKEDITOR.instances.content.getData();
</script>	 
</head>
<body>
<form action="/fr-here/rest/sys/news/addorupdate?navTabId=/fr-here/rest/sys/news/list&callbackType=closeCurrent" class="pageForm required-validate" method="post"  onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
	<div class="pageFormContent" layoutH="53">
		<div class="unit">
			<p>
				<label>新闻标题</label>
				<input name="title" maxlength="50" class="required " type="text" size="30"  alt="请输入新闻标题"
				<c:if test="${model.title != null}">value="${model.title }"</c:if>
				/>
			</p>
			<p>
				<label>点击数</label>
				<input name="clicks" maxlength="11"  type="text" size="30" class="required digits" value="${model.clicks }"
				/>
			</p>
		</div>
		<div class="unit">
			<p>
				<label>所属栏目</label>
				<select class="combox" name="cid">
				<c:forEach items="${newscolumns}" var="item">
          			<option value="${item.id}" <c:if test="${model.cid == item.id}">selected='selected'</c:if>>${item.name}</option>
          		</c:forEach>
			</select>
			</p>
			<p>
				<label>选择图片</label>  
				<input  type="file" name="file" size="10" contentEditable="false" onChange="checkFile(this.value)" />
			</p>
		</div>
		<div class="unit">
			<p>
				<label>来源</label>  	
		    	<input name="origin" maxlength="12"  type="text" size="30"  
		    	<c:if test="${model.origin != null}">value="${model.origin }"</c:if>
		    	<c:if test="${model.origin == null}">value="不详"</c:if>/>
			</p>
			<p>
				<label>作者</label>
				<input name="author" maxlength="12"  type="text" size="30"  
				<c:if test="${model.author == null}">value="不详"</c:if>
				<c:if test="${model.author != null}">value="${model.author }"</c:if>/>
			</p>
			<p>
				<label>优先值</label>
				<input name="priority" maxlength="11"  type="text" size="30" class="required digits" value="${model.priority }"/>
			</p>
			<p>
				<label>关键词</label>
				<input name="keywords" maxlength="200"  type="text" size="30"
				<c:if test="${model.keywords != null}">value="${model.keywords }"</c:if> />
				<input type="hidden" name="id" value="${model.id}"/>
			</p>
		</div>
		<div class="unit">
				<label>新闻摘要</label>
				<textarea rows="3" cols="75"  class="required" name="abstract_">
				<c:if test="${model.abstract_ != null}">${model.abstract_ }</c:if>
				</textarea>
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