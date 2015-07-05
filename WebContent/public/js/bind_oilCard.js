$(function() {

	$('#oilCard-query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new OilCardTargetObject(
						'#oilCard-page', '#oilCard-content', '/api/oilCards'), 'page');
				page.pageCallBack('1');
			});
	$('#employee-query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new EmployeeTargetObject(
						'#employee-page', '#employee-content', 
								'/api/employees'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#submit').on(
			'click',
			function() {
				var oilCardId = $('#oilCard').attr('data-id');
				var employeeId = $('#emp').attr('data-id');
				if (typeof (oilCardId) == 'undefined' || oilCardId.length != 24
						|| typeof (employeeId) == 'undefined'
						|| employeeId.length < 5) {
					alert('参数错误!');
					return false;
				}
				ajaxAction({
					url : '/api/oilCard/' + oilCardId + '/binding?employeeId=' + employeeId,
					method : 'PUT'
				}, {
					oilCardId : oilCardId,
					employeeId : employeeId,
				}, {
					callbackHandler : function(data) {
						if(typeof(data.message) != 'undefined') {
							alert(data.message);
						}
						else {
							alert('系统未知错误!');
						}
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});
});

var OilCardTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$.each(jsonPageInfo.oilCards.pageList, function(index, oilCard) {
			var empName = null;
			if (oilCard.employee != null) {
				empName = oilCard.employee.name;
			}

			var html = '<tr data-id="' + oilCard.id + '" data-info="'
					+ oilCard.code + '(' + oilCard.name + ')' + '">' + '<td>'
					+ oilCard.code + '</td>' + '<td>' + oilCard.name + '</td>'
					+ '<td>' + oilCard.money + '</td>' + '<td>'
					+ oilCard.enrtyDate + '</td>' + '<td>' + empName + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	};

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

OilCardTargetObject.prototype.bind = function() {
	$('#oilCard-content td button').on('click', function() {
		$('#oilCard').attr('data-id', $(this).closest("tr").attr('data-id'));
		$('#oilCard').val($(this).closest("tr").attr('data-info'));
		$('#oilCardModal').modal('hide');
	});
};
//
var EmployeeTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.employees.pageList, function(index, employee) {
			var html = '<tr data-id="' + employee.id + '" data-info="'
					+ employee.id + '(' + employee.name + ')' + '">' + '<td>'
					+ employee.id + '</td>' + '<td>' + employee.name + '</td>'
					+ '<td>' + employee.gender + '</td>' + '<td>'
					+ employee.birth + '</td>' + '<td>' + employee.position
					+ '</td>' + '<td>' + employee.entryDate + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

EmployeeTargetObject.prototype.bind = function() {
	$('#employee-content td button').on('click', function() {
		$('#emp').attr('data-id', $(this).closest("tr").attr('data-id'));
		$('#emp').val($(this).closest("tr").attr('data-info'));
		$('#employeeModal').modal('hide');
	});
}