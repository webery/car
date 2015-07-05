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
					placeholder="单号/车编号">
			</div>
			<div class="form-group">
				<a type="button" id="query" class="btn btn-primary">查詢</a>
			</div>
		</form>
	</div>
	<div class="panel-body"></div>
	<div class="table-responsive">
		<table id="current-trip-id" class="table table-bordered table-hover"
			data-id="">
			<!-- 当前trip的id -->
			<thead>
				<tr>
					<th>编号</th>
					<th>司机</th>
					<th>车辆</th>
					<th>出单日期</th>
					<th>完成日期</th>
					<th>路线</th>
					<th>油卡</th>
					<th>维修</th>
					<th>违章</th>
					<th>状态</th>
					<th>详情</th>
				</tr>
			</thead>
			<tbody id="trip-content">
			</tbody>
		</table>
	</div>
	<div class="panel-footer">
		<nav>
			<ul id="Trip-page" class="pagination">
			</ul>
		</nav>
	</div>
</div>
<!--  -->
<div id="trip-detail">
	<div class="modal fade" id="tripModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单详情</h4>
				</div>
				<div class="modal-body">
					<form id="trip-form" class="form-horizontal">
						<div class="form-group">
							<div class="col-sm-2" style="display: none;">
								<input type="text" class="form-control span2" id="trip-id"
									name="id" readonly>
							</div>
							<label for="startDate" class="col-sm-2 control-label">出单时间</label>
							<div class="col-sm-2">
								<input type="text"
									pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
									class="form-control span2" id="startDate" name="startDate"
									maxlength="10" placeholder="xxxx-xx-xx" required>
							</div>
							<label for="finishDate" class="col-sm-2 control-label">完成时间</label>
							<div class="col-sm-2">
								<input type="text"
									pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
									class="form-control span2" id="finishDate" name="finishDate"
									maxlength="10" placeholder="xxxx-xx-xx" required>
							</div>
						</div>
						<div class="form-group">
							<label for="employee-show" class="col-sm-2 control-label">司机</label>
							<div class="col-sm-2">
								<input type="text" style="display: none;" id="employee-id"
									name="employee.id"> <input type="text"
									class="form-control" id="employee-name" name="employee.name"
									readonly>
							</div>
							<label for="salary" class="col-sm-2 control-label">工资</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="salary"
									name="salary" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="请输入整数0-1000000000" value="2500" required>
							</div>
							<label for="cash" class="col-sm-2 control-label">出车费</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="cash" name="cash"
									maxlength="8" pattern="^[1-9]\d*|0$"
									placeholder="请输入整数0-1000000000" required>
							</div>

						</div>
						<div class="form-group">
							<label for="trailer-plateNum" class="col-sm-2 control-label">车辆</label>
							<div class="col-sm-2">
								<input type="text" style="display: none;" id="trailer-id"
									name="trailer.id"> <input type="text"
									class="form-control" id="trailer-plateNum"
									name="trailer.plateNum" placeholder="车辆" required readonly>
							</div>
							<label for="container-code" class="col-sm-2 control-label">货柜</label>
							<div class="col-sm-2">
								<input type="text" style="display: none;" id="container-id"
									name="container.id"> <input type="text"
									class="form-control" id="container-code" name="container.code"
									placeholder="货柜(必选)" required readonly>
							</div>
							<label for="bracket-plateNum" class="col-sm-2 control-label">托架</label>
							<div class="col-sm-2">
								<input type="text" style="display: none;" id="bracket-id"
									name="bracket.id"> <input type="text"
									class="form-control" id="bracket-plateNum"
									name="bracket.plateNum" placeholder="托架(必选)" required readonly>
							</div>
						</div>
						<!--  -->
						<div class="form-group">
							<label for="oilcard-code" class="col-sm-2 control-label">油卡</label>
							<div class="col-sm-2">
								<input type="text" style="display: none;" id="oilcard-id"
									name="oilcard.id"> <input type="text"
									class="form-control" id="oilcard-code" name="oilcard.code"
									placeholder="油卡(必选)" required readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="oilBalance" class="col-sm-2 control-label">油卡余额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="oilBalance"
									name="oilBalance" maxlength="8" value="0"
									pattern="^[1-9]\d*|0$" placeholder="请输入整数0-1000000000" required>
							</div>
							<label for="oilPayment" class="col-sm-2 control-label">油卡总充值</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="oilPayment"
									name="oilPayment" maxlength="8" value="0"
									pattern="^[1-9]\d*|0$" readonly>
							</div>
							<label for="oilMoney" class="col-sm-2 control-label">油耗</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="oilMoney"
									name="oilMoney" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000">
							</div>
						</div>
						<div class="form-group">
							<label for="earning" class="col-sm-2 control-label">总货款</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="earning"
									name="earning" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									readonly>
							</div>
							<label for="payment" class="col-sm-2 control-label">总已经支付</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="payment"
									name="payment" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000" required readonly>
							</div>
							<label for="profit" class="col-sm-2 control-label">总利润</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="profit"
									name="profit" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000" required>
							</div>
						</div>
						<div class="form-group">
							<label for="roadToll" class="col-sm-2 control-label">过路费</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="roadToll"
									name="roadToll" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000">
							</div>
							<label for="maintenanceCosts" class="col-sm-2 control-label">维修费用</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="maintenanceCosts"
									name="maintenanceCosts" maxlength="8" value="0"
									pattern="^[1-9]\d*|0$" placeholder="0-1000000000" readonly>
							</div>
							<label for="trafficTicket" class="col-sm-2 control-label">违章罚款</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="trafficTicket"
									name="trafficTicket" maxlength="8" value="0"
									pattern="^[1-9]\d*|0$" placeholder="0-1000000000" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="allowance" class="col-sm-2 control-label">补贴</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="allowance"
									name="allowance" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000">
							</div>
							<label for="reward" class="col-sm-2 control-label">奖金</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="reward"
									name="reward" maxlength="8" value="0" pattern="^[1-9]\d*|0$"
									placeholder="0-1000000000">
							</div>
							<label for="deductMoney" class="col-sm-2 control-label">扣钱</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="deductMoney"
									name="deductMoney" maxlength="8" value="0"
									pattern="^[1-9]\d*|0$" placeholder="0-1000000000">
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
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="query_tripRoute.jsp"></jsp:include>
<jsp:include page="query_tripOilcard.jsp"></jsp:include>
<jsp:include page="query_tripMaintenance.jsp"></jsp:include>
<jsp:include page="query_tripTraffic.jsp"></jsp:include>
<jsp:include page="tripOilcard.jsp"></jsp:include>