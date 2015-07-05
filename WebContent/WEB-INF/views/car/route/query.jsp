<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<form class="form-inline">
			<div class="form-group">
				<label class="sr-only" for="name"></label> <input type="text"
					class="form-control" name="searchKeyWord" id="searchKeyWord"
					placeholder="城市名称(0-5个字)">
			</div>
			<button type="button" id="query" class="btn btn-primary">查詢</button>
		</form>
	</div>
	<!-- panel-heading  -->
	<div class="panel-body">
		<div class="row-fluid"></div>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>出发到</th>
					<th>终点</th>
					<th>路线名称</th>
				</tr>
			</thead>
			<tbody id="route-content">
			</tbody>
		</table>
	</div>
	<!-- table-responsive  -->
	<!-- panel-body  -->
	<div class="panel-footer">
		<nav>
			<ul id="page" class="pagination">
			</ul>
		</nav>
	</div>
	<!-- panel-footer  -->
	<!-- panel  -->
</div>