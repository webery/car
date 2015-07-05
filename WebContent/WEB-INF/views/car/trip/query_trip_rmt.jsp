<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!--  -->
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
						<div class="panel-heading"></div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>路线</th>
										<th>提货仓库</th>
										<th>交货仓库</th>
										<th>开始日期</th>
										<th>完成日期</th>
										<th>收益</th>
										<th>状态</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="tripRoute-content">
								</tbody>
							</table>
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
<div id="tripMaintenance-detail">
	<div class="modal fade" id="tripMaintenanceModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">维修记录详情</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>维修名称</th>
										<th>维修金额</th>
										<th>维修日期</th>
										<th>维修地址</th>
										<th>状态</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="tripMaintenance-content">
								</tbody>
							</table>
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
<!--  -->
<div id="tripTraffic-detail">
	<div class="modal fade" id="tripTrafficModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">违章记录详情</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<!-- panel-heading  -->
						<div class="panel-body">
							<div class="row-fluid"></div>
						</div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>违章名称</th>
										<th>罚款金额</th>
										<th>违章扣分</th>
										<th>违章日期</th>
										<th>违章地址</th>
										<th>状态</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="tripTraffic-content">
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
<!--  -->
