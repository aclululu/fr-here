<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配权限</title>
</head>
<body>

<div class="pageContent">
<form action="/fr-here/rest/sys/role/assignprivilege?navTabId=/fr-here/rest/sys/role/list&callbackType=closeCurrent" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="53">
	<div class="unit">
		<p>
			<label>角色名：</label>
			<input readonly="true" value="${model.name}" type="text" />
		</p>
		<p>
			<label>角色描述：</label>
			<input readonly="true" value="${model.info}" type="text" />
			<input type="hidden" name="roleid" value="${model.id }"/>				
		</p>
	</div>
	<div class="unit">
	<div>
		<label>请选择权限：</label>
		<div style=" float:left; display:block;  overflow:auto; width:400px; height:600px; overflow:auto; border:solid 1px #CCC; line-height:21px; background:#FFF;">
		<ul class="tree treeFolder treeCheck expand" oncheck="kkk">
			<c:forEach items="${tree}" varStatus="u" var="item">
				<c:if test="${item.parent}">
					<li><a >${item.name}</a>
					<ul>
						<c:forEach items="${item.child}" varStatus="u" var="m">
							<li><a tname="name" tvalue="${m.id }" <c:if test="${m.checked}">checked="true"</c:if> >${m.name }</a></li>
						</c:forEach>
						</ul>
					</li>				
				</c:if>
			</c:forEach>
		</ul>
		</div>
	</div>
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