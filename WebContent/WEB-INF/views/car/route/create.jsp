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
		<h3 class="panel-title">创建新路线</h3>
	</div>
	<!-- panel-heading  -->
	<div class="panel-body">
		<div class="row-fluid">
			<form id="route-form" class="form-horizontal">
				<div class="form-group">
					<label for="start" class="col-sm-2 control-label">城市1</label>
					<div class="col-sm-4">
						<input type="text" style="display: none;" id="start-id"> <input
							type="text" class="form-control" name="start" id="start"
							placeholder="始发地" required readonly>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-info" id="btn-start"
							data-toggle="modal" data-target="#citySelectModal"
							data-whatever="start">选择</button>
					</div>
				</div>
				<div class="form-group">
					<label for="start" class="col-sm-2 control-label">城市2</label>
					<div class="col-sm-4">
						<input type="text" style="display: none;" id="destination-id">
						<input type="text" class="form-control" name="destination"
							id="destination" placeholder="终到地" required readonly>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-info" id="btn-destination"
							data-toggle="modal" data-target="#citySelectModal"
							data-whatever="destination">选择</button>
					</div>
				</div>
				<div class="form-group">
					<label for="result" class="col-sm-2 control-label">生成路线</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="result" id="result"
							placeholder="生成路线" required readonly>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" id="submit" class="btn btn-primary">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- panel-body  -->
	<div class="panel-footer"></div>
	<!-- panel-footer  -->
	<!-- panel  -->
</div>

<!--  -->
<div id="city-select">
	<div class="modal fade" id="citySelectModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">城市选择</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<p id="error-message" class="bg-danger" style="color: red;"></p>
						</div>
						<div class="form-group">
							<label class="col-sm-4  control-label" for="province">省份</label>
							<div class="col-sm-8">
								<select name="s_province" id="s_province" class="form-control">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4  control-label" for="city">城市</label>
							<div class="col-sm-8">
								<select name="s_city" id="s_city" class="form-control">
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-offset-4">
								<button type="button" class="btn btn-primary" id="s_city_ok"
									data-type="start">确定</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>