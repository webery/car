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
		<h3 class="panel-title">车辆信息绑定(拖车和货柜托架搭配)</h3>
	</div>
	<div class="panel-body">
		<form id="bind-trailer" class="form-horizontal">
			<div class="form-group">
				<label for="trailer" class="col-sm-2 control-label">拖车</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="trailer"
						id="trailer-value" maxlength="10" placeholder="" data-id=""
						required readonly>
				</div>
				<div class="col-sm-1">
					<a id="s-trailer" class="btn btn-info" aria-hidden="true"
						data-toggle="modal" data-target="#trailerModal">选择</a>
				</div>
				<div class="col-sm-1">
					<a id="c-trailer" class="btn btn-info">取消</a>
				</div>
			</div>
			<div class="form-group">
				<label for="container" class="col-sm-2 control-label">货柜</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="container"
						id="container-value" maxlength="10" placeholder="" data-id=""
						required readonly>
				</div>
				<div class="col-sm-1">
					<a id="s-container" class="btn btn-info" aria-hidden="true"
						data-toggle="modal" data-target="#containerModal">选择</a>
				</div>
				<div class="col-sm-1">
					<a id="c-container" class="btn btn-info">取消</a>
				</div>
			</div>
			<div class="form-group">
				<label for="bracket" class="col-sm-2 control-label">托架</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="bracket"
						id="bracket-value" maxlength="10" placeholder="" data-id=""
						required readonly>
				</div>
				<div class="col-sm-1">
					<a id="s-bracket" class="btn btn-info" aria-hidden="true"
						data-toggle="modal" data-target="#bracketModal">选择</a>
				</div>
				<div class="col-sm-1">
					<a id="c-bracket" class="btn btn-info">取消</a>
				</div>
			</div>
			<div class="form-group">
				<label for="employee" class="col-sm-2 control-label">司机</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="employee"
						id="employee-value" maxlength="10" placeholder="" data-id=""
						required readonly>
				</div>
				<div class="col-sm-1">
					<a id="s-employee" class="btn btn-info" aria-hidden="true"
						data-toggle="modal" data-target="#employeeModal">选择</a>
				</div>
				<div class="col-sm-1">
					<a id="c-employee" class="btn btn-info" aria-hidden="true">取消</a>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a id="submit" class="btn btn-primary">保存</a>
				</div>
			</div>
		</form>
	</div>
	<div class="panel-footer"></div>
</div>
<div id="employee-detail">
	<div class="modal fade" id="employeeModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">员工详情</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<select id="status" name="status" class="form-control">
										<option value="-1" selected="selected">全部</option>
										<option value="0">在职</option>
										<option value="1">休假</option>
										<option value="2">离职</option>
									</select>
								</div>
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="name" id="name" placeholder="姓名/工号">
								</div>
								<div class="form-group">
									<a type="button" id="q-employee" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>工号</th>
										<th>姓名</th>
										<th>性别</th>
										<th>出生</th>
										<th>职位</th>
										<th>入职时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="employee-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="employee-page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!--  -->
<div id="trailer-detail">
	<div class="modal fade" id="trailerModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">拖车</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<select id="status" name="status" class="form-control">
										<option value="-1" selected="selected">全部</option>
										<option value="0">使用中</option>
										<option value="1">转让</option>
										<option value="2">报废</option>
									</select>
								</div>
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="searchKeyWord" id="searchKeyWord"
										placeholder="车牌/车编号">
								</div>
								<div class="form-group">
									<a type="button" id="q-trailer" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>车牌号</th>
										<th>车名称</th>
										<th>购买日期</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="trailer-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="trailer-page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
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
					<h4 class="modal-title">选择托架</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<select id="status" name="status" class="form-control">
										<option value="-1" selected="selected">全部</option>
										<option value="0">使用中</option>
										<option value="1">转让</option>
										<option value="2">报废</option>
									</select>
								</div>
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="searchKeyWord" id="searchKeyWord"
										placeholder="车牌/货柜编号">
								</div>
								<div class="form-group">
									<a type="button" id="q-bracket" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>车牌号</th>
										<th>车名称</th>
										<th>购买日期</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="bracket-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="bracket-page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!--  -->

<!--  -->
<div id="container-detail">
	<div class="modal fade" id="containerModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择货柜</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<select id="status" name="status" class="form-control">
										<option value="-1" selected="selected">全部</option>
										<option value="0">使用中</option>
										<option value="1">转让</option>
										<option value="2">报废</option>
									</select>
								</div>
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="searchKeyWord" id="searchKeyWord"
										placeholder="车牌/货柜编号">
								</div>
								<div class="form-group">
									<a type="button" id="q-container" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>货柜编号</th>
										<th>货柜名称</th>
										<th>类型</th>
										<th>购买日期</th>
										<th>详情</th>
									</tr>
								</thead>
								<tbody id="container-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="container-page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>