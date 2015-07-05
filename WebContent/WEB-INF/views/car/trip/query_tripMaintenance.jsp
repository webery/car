<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

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
						<div class="panel-body">
							<form id="tripMaintenance-form" class="form-horizontal"
								data-type="create">
								<div class="form-group">
									<label for="tm-name" class="col-sm-2 control-label">维修名称</label>
									<div class="col-sm-4">
										<input type="text" style="display: none;" name="id" id="tc-id">
										<input type="text" class="form-control" name="name"
											id="tm-name" maxlength="30" placeholder="名称长度1-30(必填)"
											required>
									</div>
								</div>
								<div class="form-group">
									<label for="tm-money" class="col-sm-2 control-label">维修金额</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="tm-money"
											name="money" pattern="^[1-9]\d*|0$" maxlength="8"
											placeholder="请输入整数0-10000000" value="0" required>
									</div>
								</div>
								<div class="form-group">
									<label for="tm-address" class="col-sm-2 control-label">维修地址</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="tm-address"
											name="address" maxlength="30" placeholder="0-30个字(可选)"
											required>
									</div>
								</div>
								<div class="form-group">
									<label for="tm-remark" class="col-sm-2 control-label">备注</label>
									<div class="col-sm-offset-2 col-sm-10">
										<textarea class="form-control" id="tm-remark" name="remark"
											placeholder="0-150个字" maxlength="150" rows="3"
											style="resize: none"></textarea>
									</div>
								</div>
							</form>
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