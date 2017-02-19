<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>佛软一体化信息服务平台后台管理</title>
<meta http-equiv="Contet-Type" content="text/html; charset=UTF-8">
<%@ include file="/dwz/dwz.jsp"%>
<script type="text/javascript" src="${ctx}/ckeditor/ckeditor.js"></script>
</head>
<body>
<div id="layout">
	<div id="header">
		<div class="headerNav">
			<ul class="nav">
				<li><a href="/fr-here/rest/sys/logout">退出</a></li>
			</ul>
			
			<ul class="themeList" id="themeList">
				<li theme="default"><div class="selected">蓝色</div></li>
				<li theme="green"><div>绿色</div></li>
				<!--<li theme="red"><div>红色</div></li>-->
				<li theme="purple"><div>紫色</div></li>
				<li theme="silver"><div>银色</div></li>
				<li theme="azure"><div>天蓝</div></li>
			</ul>
		</div>
		<!-- navMenu -->
	</div>
	<div id="leftside">
		<div id="sidebar_s">
			<div class="collapse">
				<div class="toggleCollapse"><div></div></div>
			</div>
		</div>
		<div id="sidebar">
			<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
			<div class="accordion" fillSpace="sidebar">
				<div class="accordionHeader">
					<h2><span>Folder</span>系统管理</h2>
				</div>
				<div class="accordionContent">
					<ul class="tree treeFolder">
					
						<c:forEach items="${tree}" varStatus="u" var="item">
							<c:if test="${item.parent}">
								<li><a>${item.name }</a>
									<ul>
									<c:forEach items="${item.child}" varStatus="u" var="m">
										<li><a href="${m.url }" target="navTab" rel="${m.url }" >${m.name }</a></li>
									</c:forEach>
								    </ul>
								</li>		
							</c:if>
					    </c:forEach>
					</ul>
				</div>
				<div class="accordionHeader">
					<h2><span>Folder</span>业务管理</h2>
				</div>
				<div class="accordionContent">
					<ul class="tree treeFolder">
						<li>
						<a>新闻管理</a>
								<ul>
									<li><a href="/fr-here/rest/sys/newscolumn/list" target="navTab" rel="/fr-here/rest/sys/newscolumn/list">新闻类别管理</a></li>
									<li><a href="/fr-here/rest/sys/news/list" target="navTab" rel="/fr-here/rest/sys/news/list">新闻内容管理</a></li>
								</ul>
						</li>
							
						<li>
						<a>供需管理</a>
								<ul>
									<li><a href="/fr-here/rest/sys/supplycolumn/list" target="navTab" rel="/fr-here/rest/sys/supplycolumn/list">供需类别管理</a></li>
									<li><a href="/fr-here/rest/sys/supply/list" target="navTab" rel="/fr-here/rest/sys/supply/list">供需内容管理</a></li>
								</ul>
						</li>	
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="container">
		<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
					<ul class="navTab-tab">
						<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
				<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
				<div class="tabsMore">more</div>
			</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页
					 }
					</a></li>
				</ul>
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
					<div class="accountInfo">
					</div>
					<div class="pageFormContent" layoutH="80" style="margin-right:230px">
					</div>
						
						<!-- 侧边弹框 
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src=""></iframe>
						</div>
						 -->
				</div>					
			</div>
		</div>
	</div>
</div>
</body>
</html>