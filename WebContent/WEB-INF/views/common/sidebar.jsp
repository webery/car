<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-book" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#trip">派单管理
				</a>
			</h4>
		</div>
		<div id="trip" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/trip/query" class="list-group-item">查询派单信息</a> <a
						href="/view/trip/create" class="list-group-item">录入派单信息</a> <a
						href="/view/trip/going" class="list-group-item">未完成的派单</a> <a
						href="/view/trip/print" class="list-group-item">打印完成的派单</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class=" glyphicon glyphicon-star" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#client">客户管理
				</a>
			</h4>
		</div>
		<div id="client" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/client/query" class="list-group-item">查询客户信息</a> <a
						href="/view/client/create" class="list-group-item">录入客户信息</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-home" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#oilcard">油卡管理
				</a>
			</h4>
		</div>
		<div id="oilcard" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/oilcard/query" class="list-group-item">查询油卡信息</a> <a
						href="/view/oilcard/create" class="list-group-item">录入油卡信息</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-flag" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#trailer">车辆管理
				</a>
			</h4>
		</div>
		<div id="trailer" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/trailer/query" class="list-group-item">查询车辆信息</a> <a
						href="/view/trailer/create" class="list-group-item">录入车辆信息</a> <a
						href="/view/trailer/bind" class="list-group-item">车辆信息绑定</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#employee">人员管理
				</a>
			</h4>
		</div>
		<div id="employee" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/employee/query" class="list-group-item">查询人员信息</a> <a
						href="/view/employee/create" class="list-group-item">录入人员信息</a>
					<!--  <a
						href="/view/employee/update" class="list-group-item">人员状态修改</a>
						-->
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-hdd" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#container">集装箱管理
				</a>
			</h4>
		</div>
		<div id="container" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/container/query" class="list-group-item">查询集装箱信息</a>
					<a href="/view/container/create" class="list-group-item">录入集装箱信息</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-road" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#bracket">托架管理
				</a>
			</h4>
		</div>
		<div id="bracket" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/bracket/query" class="list-group-item">查询托架信息</a> <a
						href="/view/bracket/create" class="list-group-item">录入托架信息</a>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class=" glyphicon glyphicon-plane" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion" href="#route">路线管理
				</a>
			</h4>
		</div>
		<div id="route" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/route/query" class="list-group-item">查询路线信息</a> <a
						href="/view/route/create" class="list-group-item">录入路线信息</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion" href="#repair">维修管理</a>
			</h4>
		</div>
		<div id="repair" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/repair/query" class="list-group-item">查询维修信息</a>
					<a href="/view/repair/create" class="list-group-item">录入维修信息</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion" href="#insurance">车险管理</a>
			</h4>
		</div>
		<div id="insurance" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/insurance/query"
						class="list-group-item">查询车险信息</a> <a
						href="/view/insurance/create" class="list-group-item">录入车险信息</a>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion"
					href="#inspection">年检管理</a>
			</h4>
		</div>
		<div id="inspection" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/view/inspection/query"
						class="list-group-item">年检信息查询</a> <a
						href="/view/inspection/create"
						class="list-group-item">录入年检信息</a>
				</div>
			</div>
		</div>
	</div>
	 -->
</div>
