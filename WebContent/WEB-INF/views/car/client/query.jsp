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
					class="form-control" name="search-word" id="search-word"
					placeholder="客户名称">
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
					<th>编号</th>
					<th>名称</th>
					<th>联系人</th>
					<th>电话</th>
					<th>详情</th>
				</tr>
			</thead>
			<tbody id="client-content">
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
					<h4 class="modal-title">客户详情</h4>
				</div>
				<div class="modal-body">
					<form id="client-form" class="form-horizontal">
						<!--  -->
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">客户编号</label>
							<div style="display: none;">
								<input type="text" class="form-control" name="id" id="id-value">
							</div>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="code" id="code"
									pattern="[A-Za-z0-9]{5,10}" placeholder="5-10个数字字母组合"
									maxlength="10" required>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">客户名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="name" id="name"
									maxlength="20" placeholder="名称长度1-20" required>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="linkman"
									id="linkman" maxlength="15" placeholder="长度0-15" required>
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="phone" id="phone"
									maxlength="15"
									pattern="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}"
									placeholder="联系电话" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="province">省份</label>
							<div class="col-sm-2">
								<select name="province.id" id="province" class="form-control"
									required>
								</select>
							</div>
							<label class="col-sm-2 control-label" for="city">城市</label>
							<div class="col-sm-2">
								<select name="city.id" id="city" class="form-control" required>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-sm-2 control-label">地址</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="address"
									name="address" maxlength="30" placeholder="0-30个字" required>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-2 control-label">简介</label>
							<div class="col-sm-offset-2 col-sm-10">
								<textarea class="form-control" id="introduce" name="introduce"
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
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>