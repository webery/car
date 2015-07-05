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
		<h3 class="panel-title">录入货柜信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-11">
				<form id="container-form" class="form-horizontal">
					<!-- 货柜编号，车牌，货柜名称，购买时间，备注 -->
					<div class="form-group">
						<label for="orderNum" class="col-sm-2 control-label">货柜编号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="code" id="code"
								pattern="[A-Za-z0-9]{5,10}" maxlength="10"
								placeholder="5-10个数字和字母组合" required>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">货柜名称</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="name" id="name"
								maxlength="15" placeholder="名称长度1-15" required>
						</div>
					</div>
					<div class="form-group">
						<label for="buyDate" class="col-sm-2 control-label">购买日期</label>
						<div class="col-sm-3">
							<input type="text"
								pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
								class="form-control span2" id="buyDate" name="buyDate" size="10" maxlength="10"
								placeholder="xxxx-xx-xx" required>
						</div>
						<label for="price" class="col-sm-2 control-label">价格</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="price" id="price"
								size="8" maxlength="8" pattern="^[1-9]\d*|0$" placeholder="整数0-10000000" required>
						</div>
					</div>
					<div class="form-group">
						<label for="size" class="col-sm-2 control-label">方数</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="size" id="size"
								size="3" maxlength="3" pattern="^[1-9]\d*|0$" placeholder="整数0-300" required>
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="col-sm-2 control-label">备注</label>
						<div class="col-sm-offset-2 col-sm-10">
							<textarea class="form-control" id="remark" name="remark"
								placeholder="0-150个字" maxlength="150" rows="3"
								style="resize: none"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="submit" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div>
			<!--<div class="col-md-10.2">  -->
			<div class="col-md-1">
			</div>
			<!--<div class="col-md-4">  -->
		</div>
		<!--rw2  -->
	</div>
	<div class="panel-footer"></div>
</div>