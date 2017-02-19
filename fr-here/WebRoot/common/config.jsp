<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String basePath;
if (request.getServerPort() == 80) {
	basePath = request.getScheme()+"://"+request.getServerName()+request.getContextPath();
} else {
	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
}
pageContext.setAttribute("ctx",basePath);
%>