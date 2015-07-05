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
				<!-- 
				<select id="status" name="status" class="form-control">
					<option value="-1" selected="selected">全部</option>
					<option value="0">在职</option>
					<option value="1">休假</option>
					<option value="2">离职</option>
				</select>
				 -->
			</div>
			<div class="form-group">
				<label class="sr-only" for="name"></label> <input type="text"
					class="form-control" name="search-work" id="search-work"
					placeholder="姓名">
			</div>
			<div class="form-group">
				<a id="query" class="btn btn-primary">查詢</a>
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
					<th>详情</th>
				</tr>
			</thead>
			<tbody id="employee-content">
			</tbody>
		</table>
	</div>
	<div class="panel-footer">
		<nav>
			<ul id="page" class="pagination">
			</ul>
		</nav>
	</div>
</div>
<!--  -->
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
					<div class="row">
						<div class="col-md-10">
							<form id="employee-form" class="form-horizontal">
								<div class="form-group">
									<label for="id" class="col-sm-2 control-label">工号</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="id" id="id"
											pattern="[A-Za-z0-9]{5,10}" maxlength="10" required readonly>
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">姓名</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="name" name="name"
											maxlength="15" placeholder="1-15个字符" required>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sex">性别</label>
									<div class="col-sm-2">
										<label class="radio-inline"> <input type="radio"
											name="gender" id="gender1" value="男" checked>男
										</label> <label class="radio-inline"> <input type="radio"
											name="gender" id="gender2" value="女">女
										</label>
									</div>
									<label for="birth" class="col-sm-2 control-label">出生</label>
									<div class="col-sm-3 input-append date form_datetime">
										<input type="text"
											pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
											class="form-control span2" id="birth" name="birth" size="10"
											placeholder="xxxx-xx-xx" required>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="nation">民族</label>
									<div class="col-sm-2">
										<select name="nation.id" id="nation" class="form-control"
											required>
										</select>
									</div>
									<label for="phone" class="col-sm-2 control-label">电话</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="phone"
											id="phone"
											pattern="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}"
											placeholder="电话" required>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="province">籍贯</label>
									<div class="col-sm-2">
										<select name="province.id" id="province" class="form-control"
											required>
										</select>
									</div>
									<label for="address" class="col-sm-2 control-label">地址</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="address"
											name="address" maxlength="25" placeholder="地址0-25个字">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="position">职位</label>
									<div class="col-sm-2">
										<select name="position" id="position" class="form-control"
											required>
											<option value="司机">司机</option>
										</select>
									</div>
									<label for="startDate" class="col-sm-2 control-label">入职时间</label>
									<div class="col-sm-3 input-append date form_datetime">
										<input type="text"
											pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
											class="form-control span2" id="startDate" name="startDate"
											size="10" placeholder="xxxx-xx-xx" required>
									</div>
								</div>
								<div class="form-group">
									<label for="status" class="col-sm-2 control-label">状态</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" name="status"
											id="status" placeholder="在职" readonly>
									</div>
									<label for="leave" class="col-sm-2 control-label">离职时间</label>
									<div class="col-sm-3 input-append date form_datetime">
										<input type="text"
											pattern="^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$"
											class="form-control span2" id="leaveDate" name="leaveDate"
											size="10" placeholder="xxxx-xx-xx" readonly> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="remark" class="col-sm-2 control-label">备注</label>
									<div class="col-sm-offset-2 col-sm-10">
										<textarea class="form-control" id="remark" name="remark"
											rows="3" style="resize: none"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" id="submit" class="btn btn-primary">保存</button>
									</div>
								</div>
							</form>
						</div>
						<!--<div class="col-md-10.2">  -->
						<div class="col-md-2">
							<!--  
							<a href="#" class="thumbnail"> <img id="employeeIconShow"
								src="images/employee_default_pic.jpg" alt="...">
							</a><input type="file" id="employeeIcon" name="employeeIcon">
							-->
						</div>
						<!--<div class="col-md-4">  -->
					</div>
					<!--rw2  -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>