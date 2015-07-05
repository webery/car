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
<title>打印派单</title>
<link rel="shortcut icon" href="/images/title_logo.jpg"
	type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/common.css">
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
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul id="print-tab" class="nav nav-tabs" role="tablist">
								<li class="active"><a href="#allEmp-trip" role="tab"
									data-toggle="tab">油卡充值记录</a></li>
								<li><a href="#singleEmp-trip" role="tab" data-toggle="tab">导出单个员工派单</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<div id="print-tab" class="tab-content">
								<div class="tab-pane active" id="allEmp-trip">
									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label" for="yearMonth-all">请输入导出的年月</label>
											<div class="col-sm-2">
												<input type="text" class="form-control" name="yearMonth-all"
													id="yearMonth-all" maxlength="7"
													placeholder="yyyy-mm(例如：2011-01)">
											</div>
											<div class="col-sm-2">
												<a type="button" id="btn-query-payment"
													class="btn btn-primary">查询</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="yearMonth-all">点击图标下载</label>
											<div id="btn-all-print" style="display: none;">
												<a class="glyphicon glyphicon-print btn btn-info" href=""></a>
											</div>
										</div>
									</form>
								</div>
								<div class="tab-pane" id="singleEmp-trip"></div>
							</div>
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>油卡编号</th>
											<th>充值对象</th>
											<th>充值金额</th>
											<th>充值时间</th>
											<th>单号</th>
											<th>充值路线</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="oilCardPayment-content">
									</tbody>
								</table>
							</div>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="oilCardPayment-page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!--<div class="col-md-10">  -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="/js/jquery-1.11.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/car_home.js"></script>
	<script src="/js/print_payment.js"></script>
</body>
</html>