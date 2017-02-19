<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>所有记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
function resetAll() {
	document.getElementById("newscolumnid").selectedIndex = 0;
	document.getElementById("newslikename").value = "";
	document.getElementById("newslikeeditor").value = "";
	document.getElementById("newsstartdate").value = "";
	document.getElementById("newsenddate").value = "";
	document.getElementById("newscondstatus").selectedIndex = 0;
	$("#combox_newscolumnid > a").text("不限");
	$("#combox_newscondstatus > a").text("状态");
}
</script>
</head>
<body>

<form id="pagerForm" method="post" action="/fr-here/rest/sys/news/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="/fr-here/rest/sys/news/list" method="post" >
<div class="pageHeader">

	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					所属栏目：</td>
					<td>
					<select class="combox" name="columnid" id="newscolumnid">
						<option value="">不限</option>
						<c:forEach items="${newscolumns}" var="item">
		          			<option value="${item.id}" <c:if test="${item.id == columnid}">selected='selected'</c:if>>${item.name}</option>
		          		</c:forEach>
					</select>
					</td>
				
				<td>
					新闻标题：<input type="text" id="newslikename" name="likename" value="${likename }"/>
				</td>
				<td>
					发布者：<input type="text" id="newslikeeditor" name="likeeditor" value="${likeeditor }"/>
				</td>
				<td>
                	<span>发布日期：</span>
                    <input type="text" name="startdate"  id="newsstartdate" value="${startdate }" size="10" class="date textInput readonly valid" readonly="true"/>
                    <span>至</span>
                    <input name="enddate" type="text" id="newsenddate" value="${enddate }" size="10" class="date textInput readonly valid" readonly="true"/>
                </td>
                <td>
					<select  class="combox"  id="newscondstatus" name="condstatus" >
						<option value="">状态</option>
						
						<option value="1" <c:if test="${condstatus ==1 }">selected='selected'</c:if>>已发布</option>
						<option value="-1" <c:if test="${condstatus ==-1 }">selected='selected'</c:if>>未发布</option>
					</select>
				</td>
			</tr>
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
			<li><a class="add" href="/fr-here/rest/sys/news/toaddorupdate?id=0" target="navTab"><span>添加新闻</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="/fr-here/rest/sys/news/deletes" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" title="确实要发布这些记录吗?" target="selectedTodo" rel="ids" href="/fr-here/rest/sys/news/publishs?status=1"><span>批量发布</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" title="确实要撤销发布这些记录吗?" target="selectedTodo" rel="ids" href="/fr-here/rest/sys/news/publishs?status=-1"><span>撤销发布</span></a></li>
		</ul>
	</div>
	<table class="table" width="1200" layoutH="137">
		<thead>
			<tr>
				<th width="30" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="40" align="center">序号</th>
				<th width="100" align="center" orderField="cid" class="${param.orderField eq 'cid' ? param.orderDirection : ''}" >所属栏目</th>
				<th width="150" align="center">新闻标题</th>
				<th width="150" align="center" orderField="cdate" class="${param.orderField eq 'cdate' ? param.orderDirection : ''}"  >发布日期</th>
				<th width="80" align="center" orderField="status" class="${param.orderField eq 'status' ? param.orderDirection : ''}"  >状态</th>
				<th width="50" align="center" orderField="clicks" class="${param.orderField eq 'clicks' ? param.orderDirection : ''}"  >访问量</th>
				<th width="150" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.list}"  varStatus="u" var="item">
			<tr target="sid_user" rel="20">
				<td align="center"><input type="checkbox" name="ids" value="${item.id}" class="checkboxCtrl"></td>
				<td align="center">${(pageBean.currentPage-1)*pageBean.pageSize+u.count}</td>
				<td align="center">${item.column}</td>
				<td align="center">${item.title}</td>
				<td align="center"><fmt:formatDate type="date" value="${item.cdate }"/></td>
				<td align="center"><c:if test="${item.status == 1}"><div style="color:red;">已发布</div ></c:if>
				<c:if test="${item.status == -1}">未发布</c:if></td>
				<td align="center">${item.clicks}</td>
				<td align="center">
					<a title="确实要删除这条记录吗" target="ajaxTodo" href="/fr-here/rest/sys/news/delete?id=${item.id}" class="btnDel">删除</a>							
					<a title="编辑" target="navTab" href="/fr-here/rest/sys/news/toaddorupdate?id=${item.id}" class="btnEdit">编辑</a>
					<a title="预览" target="_blank" href="/fr-here/rest/app/news/view?id=${item.id}" class="btnLook">预览</a>
					
					<a <c:if test="${item.status == -1}">title="确定要发布这条记录吗"</c:if>  <c:if test="${item.status == 1}">title="确定要撤销发布这条记录吗"</c:if> target="ajaxTodo" href="/fr-here/rest/sys/news/publish?id=${item.id}&status=${item.status}" class="btnAttach">发布</a>
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
