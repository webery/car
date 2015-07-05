<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>主页</title>
<link rel="shortcut icon" href="/images/title_logo.jpg"
	type="image/x-icon" />
<!-- 
<base href="" />
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<style type="text/css">
 -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/common.css">
</head>
<body>
	<jsp:include page="../common/navbar.jsp"></jsp:include>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- <header> -->
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- left barside -->
			<div class="col-md-2">
				<jsp:include page="../common/sidebar.jsp"></jsp:include>
			</div>
			<!--<div class="col-md-2">  -->
			<div class="col-md-10">
				<div class="container-fluid" id="main-content">
				${sessionScope.user }</div>
			</div>
			<!--<div class="col-md-10">  -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="/js/jquery-1.11.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/home.js"></script>
</body>
</html>