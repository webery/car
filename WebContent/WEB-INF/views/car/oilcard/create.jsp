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
		<h3 class="panel-title">录入油卡</h3>
	</div>
	<!-- panel-heading  -->
	<div class="panel-body">
		<div class="row-fluid">
			<form id="oilcard-form" class="form-horizontal">
				<div class="form-group">
					<label for="code" class="col-sm-2 control-label">卡号</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="code" id="code"
							pattern="[A-Za-z0-9]{5,10}" maxlength="10"
							placeholder="5-10个数字和字母组合" required>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">名称</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="name" id="name"
							maxlength="15" placeholder="名称长度1-15" required>
					</div>
				</div>
				<div class="form-group">
					<label for="money" class="col-sm-2 control-label">卡余额</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="money" id="money"
							pattern="^[1-9]\d*|0$" placeholder="整数(0-10000000)" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id="submit" class="btn btn-primary">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- panel-body  -->
	<div class="panel-footer"></div>
	<!-- panel-footer  -->
	<!-- panel  -->
</div>