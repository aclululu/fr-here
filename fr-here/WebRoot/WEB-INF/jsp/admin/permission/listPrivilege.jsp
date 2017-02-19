<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限菜单管理</title>
<script type="text/javascript">
function resetAll() {
	document.getElementById("likeprivilegename").value = "";
}
</script>
</head>
<body>
<form id="pagerForm" method="post" action="/fr-here/rest/sys/privilege/list">
	<input type="hidden" name="pageNum" value="1" />
</form>

<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="/fr-here/rest/sys/privilege/list" method="post" >
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					权限名：<input type="text" id="likeprivilegename" name="likename" value="${likename}"/>
				</td>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="resetAll();">重置</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>	
</div>
</form> 


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/fr-here/rest/sys/privilege/toadd" target="navTab"><span>添加权限</span></a></li>
		</ul>
	</div>
	<table class="table" width="980" layoutH="137">
		<thead>
			<tr>
				<th align="center" width="40"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="40" >序号</th>
				<th align="center" width="150">权限名</th>
				<th align="center" width="200">权限url</th>
				<th align="center" width="400">权限说明</th>
				<th align="center" width="150">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.list}"  varStatus="u" var="item">
			<tr target="sid_user" rel="20">
				<td align="center"><input type="checkbox" name="ids" value="${item.id}" class="checkboxCtrl"></td>
				<td align="center">${(pageBean.currentPage-1)*pageBean.pageSize+u.count}</td>
				<td align="center">${item.name}</td>
				<td align="center">${item.url}</td>
				<td align="center">${item.info}</td>
				<td align="center">
					<a title="确实要删除这条记录吗，如果是顶级权限，那么所有子权限将被删除!" target="ajaxTodo" href="/fr-here/rest/sys/privilege/delete?id=${item.id}" class="btnDel">删除</a>							
					<a title="编辑" target="navTab" href="/fr-here/rest/sys/privilege/toupdate?id=${item.id}" class="btnEdit">编辑</a>
				</td>										
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页${pageBean.pageSize}条&nbsp;&nbsp;</span>
			<span>共${pageBean.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pageBean.totalCount}" numPerPage="${pageBean.pageSize}" pageNumShown="${pageBean.totalPage}" currentPage="${pageBean.currentPage}"></div>
	</div>
</div>
</body>
</html>