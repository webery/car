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
					class="form-control" name="search-Word" id="search-Word"
					placeholder="车牌">
			</div>
			<div class="form-group">
				<a id="query" class="btn btn-primary">查詢</a>
			</div>
		</form>
	</div>
	<div class="panel-body"></div>
	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>车牌号</th>
					<th>托架名称</th>
					<th>尺寸(尺)</th>
					<th>购买日期</th>
					<th>详情</th>
				</tr>
			</thead>
			<tbody id="bracket-content">
			</tbody>
		</table>
	</div>
	<div class="panel-footer">
		<nav>
			<ul id="page" class="pagination">
			</ul>
		</nav>
	</div>
</div>
<!--  -->
<div id="bracket-detail">
	<div class="modal fade" id="bracketModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">托架详情</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-11">
							<form id="bracket-form" data-id="" class="form-horizontal">
								<div class="form-group">
									<label for="plateNum" class="col-sm-2 control-label">托架牌</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="plateNum"
											id="plateNum" maxlength="10"
											pattern="^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$"
											placeholder="车牌号" required>
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">托架名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="name" id="name"
											maxlength="15" placeholder="名称长度1-15" required>
									</div>
								</div>
								<div class="form-group">
									<label for="size" class="col-sm-2 control-label">尺寸(尺)</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" name="size" id="size"
											pattern="^[1-9]\d*|0$" maxlength="3" placeholder="整数0-100"
											required>
									</div>
								</div>
								<div class="form-group">
									<label for="buyDate" class="col-sm-2 control-label">购买日期</label>
									<div class="col-sm-3">
										<input type="text"
											pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
											class="form-control span2" id="buyDate" name="buyDate"
											size="10" placeholder="xxxx-xx-xx" required>
									</div>
									<label for="price" class="col-sm-2 control-label">价格</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" name="price"
											id="price" maxlength="8" pattern="^[1-9]\d*|0$"
											placeholder="请输入整数0-10000000" required>
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
										<button type="submit" id="btn-submit" class="btn btn-primary">修改</button>
									</div>
								</div>
							</form>
						</div>
						<!--<div class="col-md-10.2">  -->
						<div class="col-md-1"></div>
						<!--<div class="col-md-4">  -->
					</div>
					<!--rw2  -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>