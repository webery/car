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
		<h3 class="panel-title">录入单信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-10">
				<form id="trip-form" class="form-horizontal">
					<div class="form-group">
						<label for="startDate" class="col-sm-2 control-label">出单时间</label>
						<div class="col-sm-3">
							<input type="text"
								pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
								class="form-control span2" id="startDate" name="startDate"
								maxlength="10" placeholder="xxxx-xx-xx" required> <input
								type="text"
								pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
								style="display: none;" id="finishDate" name="finishDate"
								value="2011-11-11" size="10">
						</div>
						<label for="employee-show" class="col-sm-2 control-label">司机</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" id="employee-id"
								name="employee.id"> <input type="text"
								class="form-control" id="employee-show" name="employee-show"
								placeholder="司机(必选)" required readonly>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-info" id="btn-start"
								data-toggle="modal" data-target="#employeeModal">选择</button>
						</div>
					</div>

					<div class="form-group">
						<label for="oilcard-show" class="col-sm-2 control-label">油卡</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" id="oilcard-id"
								name="oilcard.id"> <input type="text"
								class="form-control" id="oilcard-show" name="oilcard-show"
								placeholder="油卡(必选)" required readonly>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-info" id="btn-start"
								data-toggle="modal" data-target="#oilcardModal">选择</button>
						</div>
						<!--  -->
						<label for="trailer-show" class="col-sm-2 control-label">车辆</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" id="trailer-id"
								name="trailer.id"> <input type="text"
								class="form-control" id="trailer-show" name="trailer-show"
								placeholder="车辆" required readonly>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-info" id="btn-start"
								data-toggle="modal" data-target="#trailerModal">选择</button>
						</div>
					</div>

					<div class="form-group">
						<label for="container-show" class="col-sm-2 control-label">货柜</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" id="container-id"
								name="container.id"> <input type="text"
								class="form-control" id="container-show" name="container-show"
								placeholder="货柜(必选)" required readonly>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-info" id="btn-start"
								data-toggle="modal" data-target="#containerModal">选择</button>
						</div>
						<!--  -->
						<label for="bracket-show" class="col-sm-2 control-label">托架</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" id="bracket-id"
								name="bracket.id"> <input type="text"
								class="form-control" id="bracket-show" name="bracket-show"
								placeholder="托架(必选)" required readonly>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-info" id="btn-start"
								data-toggle="modal" data-target="#bracketModal">选择</button>
						</div>
					</div>
					<!--  -->
					<div class="form-group">
						<label for="salary" class="col-sm-2 control-label">工资</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="salary" name="salary"
								maxlength="8" value="0" pattern="^[1-9]\d*|0$"
								placeholder="请输入整数0-1000000000" value="2500" required>
						</div>
						<label for="cash" class="col-sm-2 control-label">出车费</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="cash" name="cash"
								maxlength="8" pattern="^[1-9]\d*|0$"
								placeholder="请输入整数0-1000000000" required>
						</div>
					</div>
					<div class="form-group">
						<label for="oilBalance" class="col-sm-2 control-label">下单油卡余额</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="oilBalance"
								name="oilBalance" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
								placeholder="请输入整数0-1000000000" required>
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

					<div class="form-group" style="display: none;">
						<input type="text" class="form-control" id="profit" name="profit"
							maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="earning" name="earning" maxlength="8"
							value="0" pattern="^[1-9]\d*|0$" placeholder="0-1000000000">
						<input type="text" class="form-control" id="payment"
							name="payment" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="roadToll" name="roadToll" maxlength="8"
							value="0" pattern="^[1-9]\d*|0$" placeholder="0-1000000000">
						<input type="text" class="form-control" id="roadToll"
							name="roadToll" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="oilPayment" name="oilPayment"
							maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="oilMoney" name="oilMoney" maxlength="8"
							value="0" pattern="^[1-9]\d*|0$" placeholder="0-1000000000">
						<input type="text" class="form-control" id="maintenanceCosts"
							name="maintenanceCosts" maxlength="8" value="0"
							pattern="^[1-9]\d*|0$" placeholder="0-1000000000"> <input
							type="text" class="form-control" id="trafficTicket"
							name="trafficTicket" maxlength="8" value="0"
							pattern="^[1-9]\d*|0$" placeholder="0-1000000000"> <input
							type="text" class="form-control" id="allowance" name="allowance"
							maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="deductMoney" name="deductMoney"
							maxlength="8" value="0" pattern="^[1-9]\d*|0$"
							placeholder="0-1000000000"> <input type="text"
							class="form-control" id="reward" name="reward" maxlength="8"
							value="0" pattern="^[1-9]\d*|0$" placeholder="0-1000000000">
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="submit" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div>
			<!--<div class="col-md-10.2">  -->
			<div class="col-md-2"></div>
			<!--<div class="col-md-4">  -->
		</div>
		<!--rw2  -->
	</div>
	<div class="panel-footer"></div>
</div>
<!-- 司机选择弹框 -->
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
					<h4 class="modal-title">司机选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="search-emp"></label> <input
										type="text" class="form-control" name="search-emp"
										id="search-emp" placeholder="姓名">
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
										<th>职位</th>
										<th>选择</th>
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
<!-- 车辆选择种弹窗 -->
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
					<h4 class="modal-title">车辆选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="search-emp"></label> <input
										type="text" class="form-control" name="search-trailer"
										id="search-trailer" placeholder="车牌">
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
										<th>车牌</th>
										<th>车辆名称</th>
										<th>选择</th>
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
<!-- 货柜选择种弹窗 -->
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
					<h4 class="modal-title">货柜选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="search-emp"></label> <input
										type="text" class="form-control" name="search-container"
										id="search-container" placeholder="货柜编号">
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
										<th>选择</th>
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
<!-- 托架选择种弹窗 -->
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
					<h4 class="modal-title">拖架选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="search-emp"></label> <input
										type="text" class="form-control" name="search-bracket"
										id="search-bracket" placeholder="托架车牌">
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
										<th>车牌</th>
										<th>拖架名称</th>
										<th>选择</th>
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
<!-- 油卡选择种弹窗 -->
<div id="oilcard-detail">
	<div class="modal fade" id="oilcardModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">油卡选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="search-emp"></label> <input
										type="text" class="form-control" name="search-oilcard"
										id="search-oilcard" placeholder="油卡名称">
								</div>
								<div class="form-group">
									<a type="button" id="q-oilcard" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>油卡编号</th>
										<th>油卡名称</th>
										<th>选择</th>
									</tr>
								</thead>
								<tbody id="oilcard-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="oilcard-page" class="pagination">
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
<!-- 弹窗选择结束 -->