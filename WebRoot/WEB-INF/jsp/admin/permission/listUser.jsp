<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员管理</title>

<script type="text/javascript">
function resetAll() {
	document.getElementById("likeusername").value = "";
	document.getElementById("conduserstatus").selectedIndex = 0;
	$("#combox_conduserstatus > a").text("状态");
}
</script>
</head>
<body>
<form id="pagerForm" method="post" action="/fr-here/rest/sys/user/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>

<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="/fr-here/rest/sys/user/list" method="post" >
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					管理员：<input type="text" id="likeusername" name="likename" value="${likename}"/>
				</td>
				<td>
					<select id="conduserstatus" name="condstatus" class ="combox">
						<option value="0">状态</option>
						<option value="1" <c:if test="${condstatus == 1}">selected='selected'</c:if>>正常</option>
						<option value="-1" <c:if test="${condstatus == -1}">selected='selected'</c:if>>锁定</option>
					</select>
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
			<li><a class="add" href="/fr-here/rest/sys/user/toadd" target="navTab"><span>添加管理员</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="/fr-here/rest/sys/user/deletes" class="delete"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="1000" layoutH="137">
		<thead>
			<tr>
				<th width="30" align="center" ><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="40" align="center"  >序号</th>
				<th width="100" align="center" >管理员名</th>
				<th width="80" align="center" >状态</th>
				<th width="100" align="center">角色</th>
				<th width="150" align="center" >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.list}"  varStatus="u" var="item">
			<tr target="sid_user" rel="20">
				<td align="center"><input type="checkbox" name="ids" value="${item.id}" class="checkboxCtrl"></td>
				<td align="center">${(pageBean.currentPage-1)*pageBean.pageSize+u.count}</td>
				<td align="center">${item.account}</td>
				<td align="center">
				<c:if test="${item.status > 0}">
				     正常
				</c:if>
				<c:if test="${item.status < 0}">
				    锁定
				</c:if>
				</td>
				<td align="center">${item.stringrole}</td>
				<td align="center">
					<a title="确实要删除这条记录吗" target="ajaxTodo" href="/fr-here/rest/sys/user/delete?id=${item.id}" class="btnDel">删除</a>							
					<a title="编辑" target="navTab" href="/fr-here/rest/sys/user/toupdate?id=${item.id}" class="btnEdit">编辑</a>
				    <a title="分配角色" target="navTab" href="/fr-here/rest/sys/user/toassignrole?id=${item.id}" class="btnAssign">分配角色</a>
				</td>										
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach begin="10" end="40" step="10" varStatus="s">
					<option value="${s.index}" ${numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
				</c:forEach>
			</select>
			<span>条&nbsp;&nbsp;共${pageBean.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pageBean.totalCount}"  numPerPage="${numPerPage}"  pageNumShown="${pageBean.totalPage}" currentPage="${pageBean.currentPage}"></div>
	</div>
</div>
</body>
</html>
