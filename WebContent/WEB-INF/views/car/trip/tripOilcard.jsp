<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div id="oilCard-detail">
	<div class="modal fade" id="oilCardModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">油卡充值</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul id="oilCard-tab" class="nav nav-tabs" role="tablist">
								<li id="btn-query-oilCard"><a href="#oilCard-query-tab"
									role="tab" data-toggle="tab">油卡充值记录</a></li>
								<li id="btn-create-oilCard"><a href="#oilCard-create-tab"
									role="tab" data-toggle="tab">油卡充值</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<div id="oilCard-tab" class="tab-content">
								<div class="tab-pane active" id="oilCard-query-tab">
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>油卡编号</th>
													<th>充值对象</th>
													<th>充值金额</th>
													<th>充值时间</th>
													<th>充值路线</th>
													<th>备注</th>
													<th>删除</th>
												</tr>
											</thead>
											<tbody id="oilCard-content">
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="oilCard-create-tab">
									<form id="oilCardCreate-form" class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label" for="oc-route-show">路线</label>
											<div class="col-sm-3">
												<select name="tripRoute.id" id="oc-tripRoute"
													class="form-control" required>
												</select>
											</div>
											<label class="col-sm-2 control-label" for="oc-client-show">充值对象</label>
											<div class="col-sm-3">
												<input type="text" style="display: none;" name="client.id"
													id="oc-client-id"> <input type="text"
													class="form-control" name="oc-client-show"
													id="oc-client-show" maxlength="10" placeholder="必选"
													required readonly>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-info"
													data-toggle="modal" data-target="#oilCardClientModal">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label for="oc-money" class="col-sm-2 control-label">充值金额</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="oc-money"
													name="money" pattern="^[1-9]\d*|0$" maxlength="8"
													placeholder="请输入整数0-10000000" required>
											</div>
											<label for="oc-payDate" class="col-sm-2 control-label">充值时间</label>
											<div class="col-sm-3">
												<input type="text"
													pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
													class="form-control" id="oc-payDate" maxlength="10"
													name="payDate" placeholder="xxxx-xx-xx" required>
											</div>
										</div>
										<div class="form-group">
											<label for="oc-remark" class="col-sm-2 control-label">备注</label>
											<div class="col-sm-offset-2 col-sm-10">
												<textarea class="form-control" id="oc-remark" name="remark"
													placeholder="0-150个字" maxlength="150" rows="3"
													style="resize: none"></textarea>
											</div>
										</div>
										<div class="form-group">
											<input type="text" style="display: none;" id="oc-trip-id"
												name="trip.id" value="0"> <input type="text"
												style="display: none;" id="oc-oilCard-id" name="oilCard.id"
												value="0"> <input type="text" style="display: none;"
												id="oc-balance" name="balance" pattern="^[1-9]\d*|0$"
												value="0" maxlength="8" placeholder="请输入整数0-10000000">
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="oilCardCreate-submit"
													class="btn btn-primary">保存</button>
											</div>
										</div>
									</form>
								</div>
							</div>

						</div>

						<div class="panel-footer"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 充值对象选择弹窗 -->
<div id="oilCardClient-detail">
	<div class="modal fade" id="oilCardClientModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
										class="form-control" name="search-word" id="search-ocp-client"
										placeholder="客户名称">
								</div>
								<a type="button" id="q-oilCardClient" class="btn btn-primary">查詢</a>
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
								<tbody id="oilCardClient-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="oilCardClient-page" class="pagination">
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
