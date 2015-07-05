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
												</tr>
											</thead>
											<tbody id="oilCard-content">
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="oilCard-create-tab"></div>
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