<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div id="tripRoute-detail">
	<div class="modal fade" id="tripRouteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单路线详情</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul id="tripRoute-tab" class="nav nav-tabs" role="tablist">
								<li class="active" id="btn-query-tripRoute"><a
									href="#tr-query-tab" role="tab" data-toggle="tab">单路线详情</a></li>
								<li id="btn-create-tripRoute"><a href="#tr-create-tab"
									role="tab" data-toggle="tab">录入单路线</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<div id="tripRoute-tab" class="tab-content">
								<div class="tab-pane active" id="tr-query-tab">
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>路线</th>
													<th>客户</th>
													<th>开始</th>
													<th>完成</th>
													<th>收益</th>
													<th>详情</th>
												</tr>
											</thead>
											<tbody id="tripRoute-content">
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="tr-create-tab"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 单个路线详情 -->
<div id="tripRouteGoing-detail">
	<div class="modal fade" id="tripRouteGoingModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">派单路线详情</h4>
				</div>
				<div class="modal-body">
					<form id="tripRouteGoing-form" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="rtu-route-show">路线</label>
							<div class="col-sm-3">
								<input type="text" style="display: none;" name="id"
									id="rtu-tripRoute-id"> <input type="text"
									style="display: none;" name="trip.id" id="rtu-trip-id">
								<input type="text" style="display: none;" name="route.id"
									id="rtu-route-id"> <input type="text"
									style="display: none;" name="client.id" id="rtu-client-id">
								<input type="text" class="form-control" name="route-show"
									id="rtu-route-show" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="rtu-client-show">客户</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="client-show"
									id="rtu-client-show" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="rtu-earning" class="col-sm-2 control-label">货款</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="rtu-earning"
									name="earning" pattern="^[1-9]\d*|0$" maxlength="8"
									placeholder="0-10000000" required>
							</div>
							<label for="rtu-payment" class="col-sm-2 control-label">已支付</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="rtu-payment"
									name="payment" pattern="^[1-9]\d*|0$" maxlength="8"
									placeholder="0-10000000" required>
							</div>
							<label for="rtu-arrearage" class="col-sm-2 control-label">未支付</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="rtu-arrearage"
									name="arrearage" pattern="^[1-9]\d*|0$" maxlength="8"
									placeholder="0-10000000" required>
							</div>
						</div>
						<div class="form-group">
							<label for="rtu-startDate" class="col-sm-2 control-label">开始时间</label>
							<div class="col-sm-3">
								<input type="text"
									pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
									class="form-control" id="rtu-startDate" maxlength="10"
									name="startDate" size="10" placeholder="xxxx-xx-xx" required>
							</div>
							<label for="rtu-finishDate" class="col-sm-2 control-label">完成时间</label>
							<div class="col-sm-3">
								<input type="text"
									pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
									class="form-control" id="rtu-finishDate" maxlength="10"
									name="finishDate" size="10" placeholder="xxxx-xx-xx" required>
							</div>
						</div>
						<div class="form-group" style="display: none;">
							<input type="text" class="form-control" id="rt-salary"
								name="salary" pattern="^[1-9]\d*|0$" maxlength="8" value="0"
								placeholder="请输入整数0-10000000"> <input type="text"
								class="form-control" id="rt-oilPayment" name="oilPayment"
								pattern="^[1-9]\d*|0$" maxlength="8" value="0"
								placeholder="请输入整数0-10000000"> <input type="text"
								class="form-control" id="rt-oilMoney" name="oilMoney"
								pattern="^[1-9]\d*|0$" maxlength="8" value="0"
								placeholder="请输入整数0-10000000">
						</div>
						<div class="form-group">
							<label for="rtu-remark" class="col-sm-2 control-label">备注</label>
							<div class="col-sm-offset-2 col-sm-10">
								<textarea class="form-control" id="rtu-remark" name="remark"
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
