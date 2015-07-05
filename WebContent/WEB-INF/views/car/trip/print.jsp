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
		<ul id="print-tab" class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#allEmp-trip" role="tab"
				data-toggle="tab">导出所有员工派单</a></li>
			<li><a href="#singleEmp-trip" role="tab" data-toggle="tab">导出单个员工派单</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="print-tab" class="tab-content">
			<div class="tab-pane active" id="allEmp-trip">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="yearMonth-all">请输入导出的年月</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="yearMonth-all"
								id="yearMonth-all" maxlength="7"
								placeholder="yyyy-mm(例如：2011-01)">
						</div>
						<div class="col-sm-2">
							<a type="button" id="create-all" class="btn btn-primary">生成</a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="yearMonth-all">点击图标下载</label>
						<div id="btn-all-print" style="display: none;">
							<a class="glyphicon glyphicon-print btn btn-info" href=""></a>
						</div>
					</div>
				</form>
			</div>
			<div class="tab-pane" id="singleEmp-trip"></div>
		</div>
	</div>
	<div class="panel-footer"></div>
</div>