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
		<h3 class="panel-title">录入员工信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-10">
				<form id="employee-form" class="form-horizontal">
					<div class="form-group">
						<label for="id" class="col-sm-2 control-label">工号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="id" id="id"
								pattern="[A-Za-z0-9]{5,10}" maxlength="10" required>
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
								maxlength="10" placeholder="xxxx-xx-xx" required>
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
							<input type="text" class="form-control" name="phone" id="phone"
								pattern="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}"
								maxlength="15" placeholder="电话" required>
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
								maxlength="10" placeholder="xxxx-xx-xx" required>
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
	<div class="panel-footer"></div>
</div>