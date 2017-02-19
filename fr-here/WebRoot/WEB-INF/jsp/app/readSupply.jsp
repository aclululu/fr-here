<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>供需服务平台</title>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
 <!-- 引入 WeUI -->
 <link rel="stylesheet" href="http://res.wx.qq.com/open/libs/weui/0.4.2/weui.css" type="text/css"/>
</head>

<body>
<div class="wrap_box">
<div class="mobile_head product_head">
<a href="javascript:history.go(-1);" class="add_posl">
<span class="icon_l">
</span>
</a>
<h1 class="product_title">${model.column}</h1>
<a href="javascript:history.go(1);" class="add_posr">
<span class="icon_r">
</span>
</a>
</div>
<div class="clear"></div>
<div class="mobile_product_box mobile_news_con_box">
 <h1 class="mobile_news_con_title">${model.title }</h1>
 <p class="weui_cells_title">发布时间:<fmt:formatDate type="date" value="${model.cdate }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布者：${model.suname} &nbsp;&nbsp;</p>
 <p class="weui_cells_title">供或需：<c:if test="${model.sio==-1}">需求</c:if><c:if test="${model.sio==1}">供应</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：<span id="clickcount"><c:if test="${model.status==-1}">未发布</c:if><c:if test="${model.status==2}">交易结束</c:if><c:if test="${model.status==1}">可交易</c:if></span>&nbsp;&nbsp;</p>
 <article class="weui_article">${model.content}</article>
 <div class="weui_cells_title">联系他</div>
 <div class="weui_cells">
    <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
  			<p>地址：</p>
 		 </div>
 		 <div class="weui_cell_ft">${model.address}</div>
  	</div>
  <div class="weui_cell">
	  <div class="weui_cell_bd weui_cell_primary">
	   <p>联系方式：</p>
	   </div>
  	 <div class="weui_cell_ft">${model.phone}</div>
  </div>
</div>
<div class="noused3"></div>
<div class="clear"></div>
 <div class="mobile_footer2">
  	<ul>
    	<li><a href="#" class="mobile_home">主页</a></li>
        <li><a href="http://www.foshan.com.cn" class="mobile_contact_us">联系我们</a></li>
    </ul>
  </div>
 </div>
</body>
</html>
