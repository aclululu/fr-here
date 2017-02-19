<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
 <script src="${ctx}/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>
 
<title>佛软新闻</title>
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
 <p class="weui_cells_title">发布时间:<fmt:formatDate type="date" value="${model.cdate }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${model.author} &nbsp;&nbsp;</p>
 <p class="weui_cells_title">来源：${model.origin}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;浏览量：<span id="clickcount">${model.clicks}</span>&nbsp;&nbsp;</p>
 <p class="weui_cells_title">关键字：<span class="keyword">${model.keywords}</span></p>
 <p class="weui_cells_title">导读：${model.abstract_}</p>
 <article class="weui_article">${model.content}</article>
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
