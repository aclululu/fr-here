<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>添加供需</title>
 <!-- 引入 WeUI -->
 <link rel="stylesheet" href="http://res.wx.qq.com/open/libs/weui/0.4.2/weui.css" type="text/css"/>
 <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</head>
<body>
<form id="supply_add" action="/fr-here/rest/app/supply/add" method="post">
<div class="weui_cells_title">添加供需</div>
<div class="weui_cells weui_cells_form">
    <div class="weui_cell">
        <div class="weui_cell_hd">
            <label class="weui_label">标题</label>
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <input class="weui_input" type="text" name="title"  placeholder="请输入供需标题">
        </div>
    </div>

<div class="weui_cells_title">请选择供或需</div>
<div class="weui_cells weui_cells_radio">
    <label class="weui_cell weui_check_label" for="x11">
        <div class="weui_cell_bd weui_cell_primary">
            <p>供应</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" class="weui_check" name="sio" id="x11" value="1">
            <span class="weui_icon_checked"></span>
        </div>
    </label>
    <label class="weui_cell weui_check_label" for="x12">

        <div class="weui_cell_bd weui_cell_primary">
            <p>需求</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" name="sio" class="weui_check" id="x12" checked="checked" value="-1">
            <span class="weui_icon_checked"></span>
        </div>
    </label>
</div>
<div class="weui_cells_title">请选择供需类型</div>
<div class="weui_cell weui_cell_select">
        <div class="weui_cell_hd ">
            <div class="weui_label" style="padding-left: 15px;">供需类型</div>
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <select class="weui_select" name="cid">
               <c:forEach items="${supplycolumns}" var="item">
          			<option value="${item.id}">${item.name}</option>
          		</c:forEach>
            </select>
        </div>
    </div>


    <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
            <textarea class="weui_textarea" placeholder="请填写供需内容" name="content" rows="6"></textarea>
            <div class="weui_textarea_counter"><span>0</span>/200</div>
        </div>
    </div>



    <div class="weui_cell">
        <div class="weui_cell_hd">
            <label class="weui_label">地址</label>
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <input class="weui_input" type="text" name="address"  placeholder="请输入供需地址">
        </div>
    </div>

    
    <div class="weui_cell">
        <div class="weui_cell_hd">
            <label class="weui_label">电话</label>
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <input class="weui_input" type="tel" name="phone"  placeholder="请输入供需电话">
        </div>
    </div>

</div>
</form>
<div class="weui_btn_area">
    <a class="weui_btn weui_btn_primary" id="showTooltips">确定</a>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        //提交
        $('#showTooltips').click(function(){
            //alert("sfdsfsddfsdfd");
            $('#supply_add').submit();
        });
    })
</script>
</html>

