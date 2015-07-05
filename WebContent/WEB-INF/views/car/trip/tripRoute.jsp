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
													<th>删除</th>
													<th>详情</th>
												</tr>
											</thead>
											<tbody id="tripRoute-content">
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="tr-create-tab">
									<form id="tripRouteCreate-form" class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label" for="rt-route-show">路线</label>
											<div class="col-sm-3">
												<input type="text" style="display: none;" name="trip.id"
													id="rt-trip-id"> <input type="text"
													style="display: none;" name="route.id" id="rt-route-id">
												<input type="text" class="form-control" name="route-show"
													id="rt-route-show" placeholder="必选" required readonly>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-info" id="btn-start"
													data-toggle="modal" data-target="#routeModal">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="client-show">客户</label>
											<div class="col-sm-3">
												<input type="text" style="display: none;" name="client.id"
													id="rt-client-id"> <input type="text"
													class="form-control" name="rt-client-show"
													id="rt-client-show" placeholder="必选" required readonly>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-info" id="btn-start"
													data-toggle="modal" data-target="#clientModal">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label for="rt-earning" class="col-sm-2 control-label">货款</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="rt-earning"
													name="earning" pattern="^[1-9]\d*|0$" maxlength="8"
													placeholder="请输入整数0-10000000" required>
											</div>
										</div>
										<div class="form-group">
											<label for="rt-startDate" class="col-sm-2 control-label">开始时间</label>
											<div class="col-sm-3">
												<input type="text"
													pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
													class="form-control" id="rt-startDate" maxlength="10"
													name="startDate" size="10" placeholder="xxxx-xx-xx"
													required> <input type="text"
													pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
													style="display: none;" id="rt-finishDate"
													value="2011-11-11" name="finishDate">
											</div>
										</div>
										<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="rt-payment"
												name="payment" pattern="^[1-9]\d*|0$" maxlength="8"
												value="0" placeholder="请输入整数0-10000000"> <input
												type="text" class="form-control" id="rt-arrearage"
												name="arrearage" pattern="^[1-9]\d*|0$" maxlength="8"
												value="0" placeholder="请输入整数0-10000000"> <input
												type="text" class="form-control" id="rt-salary"
												name="salary" pattern="^[1-9]\d*|0$" maxlength="8" value="0"
												placeholder="请输入整数0-10000000"> <input type="text"
												class="form-control" id="rt-oilPayment" name="oilPayment"
												pattern="^[1-9]\d*|0$" maxlength="8" value="0"
												placeholder="请输入整数0-10000000"> <input type="text"
												class="form-control" id="rt-oilMoney" name="oilMoney"
												pattern="^[1-9]\d*|0$" maxlength="8" value="0"
												placeholder="请输入整数0-10000000"> <input type="text"
												class="form-control" id="rt-oilMoney" name="oilMoney"
												pattern="^[1-9]\d*|0$" maxlength="8" value="0"
												placeholder="请输入整数0-10000000">
										</div>
										<div class="form-group">
											<label for="rt-remark" class="col-sm-2 control-label">备注</label>
											<div class="col-sm-offset-2 col-sm-10">
												<textarea class="form-control" id="rt-remark" name="remark"
													placeholder="0-150个字" maxlength="150" rows="3"
													style="resize: none"></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="tripRouteCreate-submit"
													class="btn btn-primary">保存</button>
											</div>
										</div>
									</form>
								</div>
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
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" id="tripRouteGoing-submit"
									class="btn btn-primary">保存修改</button>
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
<!-- 路线选择 -->
<div id="route-detail">
	<div class="modal fade" id="routeModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">增加路线</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="searchKeyWord" id="search-route"
										placeholder="城市名称(0-5个字)">
								</div>
								<a type="button" id="q-route" class="btn btn-primary">查詢</a>
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
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="route-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="route-page" class="pagination">
								</ul>
							</nav>
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
<!-- 客户选择弹窗 -->
<div id="client-detail">
	<div class="modal fade" id="clientModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">客户选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="search-word" id="search-tr-client"
										placeholder="客户名称">
								</div>
								<a type="button" id="q-client" class="btn btn-primary">查詢</a>
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
										<th>编号</th>
										<th>客户名称</th>
										<th>选择</th>
									</tr>
								</thead>
								<tbody id="client-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="client-page" class="pagination">
								</ul>
							</nav>
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
