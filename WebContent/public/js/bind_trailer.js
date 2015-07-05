$(function() {

	// 取消选择
	$('#c-trailer').on('click', function() {
		$('#trailer-value').val('');
		$('#trailer-value').attr('data-id', '');
	});
	$('#c-container').on('click', function() {
		$('#container-value').val('');
		$('#container-value').attr('data-id', '');
	});
	$('#c-bracket').on('click', function() {
		$('#bracket-value').val('');
		$('#bracket-value').attr('data-id', '');
	});
	$('#c-employee').on('click', function() {
		$('#employee-value').val('');
		$('#employee-value').attr('data-id', '');
	});
	//
	$('#q-trailer').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TrailerTargetObject(
						'#trailer-page', '#trailer-content', '/api/trailers'), 'page');
				page.pageCallBack('1');
			});
	//
	//
	$('#q-container').on(
			'click',
			function() {
				var page = new AjaxPageParser(new ContainerTargetObject(
						'#container-page', '#container-content', '/api/containers'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#q-bracket').on(
			'click',
			function() {
				var page = new AjaxPageParser(new BracketTargetObject(
						'#bracket-page', '#bracket-content','/api/brackets'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#q-employee').on(
			'click',
			function() {
				var page = new AjaxPageParser(new EmployeeTargetObject(
						'#employee-page', '#employee-content', '/api/employees'), 'page');
				page.pageCallBack('1');
			});

	$('#submit')
			.on(
					'click',
					function() {
						var trailerId = $('#trailer-value').attr('data-id');
						if (typeof (trailerId) == 'undefined'
								|| trailerId.length != 24) {
							alert('请选择拖车!');
							return false;
						}
						var containerId = $('#container-value').attr('data-id');
						if (typeof (containerId) == 'undefined'
								|| containerId.length != 24) {
							containerId = null;
						}
						var bracketId = $('#bracket-value').attr('data-id');
						if (typeof (bracketId) == 'undefined'
								|| bracketId.length != 24) {
							bracketId = null;
						}
						var employeeId = $('#employee-value').attr('data-id');
						if (typeof (employeeId) == 'undefined'
								|| employeeId.length < 5) {
							employeeId = null;
						}
						ajaxAction({
							url : '/api/trailer/' + trailerId
									+ '/binding',
							method : 'POST'
						}, {
							'trailer.id' : trailerId,
							'container.id' : containerId,
							'bracket.id' : bracketId,
							'employee.id' : employeeId,
						}, {
							callbackHandler : function(data) {
								if (typeof (data.message) != 'undefined') {
									alert(data.message);
								} else {
									alert('系统未知错误!');
								}
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});
});

var TrailerTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.trailers.pageList, function(index, trailer) {
			var buyDate = trailer.buyDate ? dateFormat.format(new Date(
					trailer.buyDate)) : '';
			var html = '<tr data-id="' + trailer.id + '" data-info="'
					+ trailer.code + '(' + trailer.plateNum + ')' + '">'
					+ '<td>' + trailer.code + '</td>' + '<td>'
					+ trailer.plateNum + '</td>' + '<td>' + trailer.name
					+ '</td>' + '<td>' + buyDate + '</td>'
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

TrailerTargetObject.prototype.bind = function() {
	$('#trailer-content td button').on(
			'click',
			function() {
				$('#trailer-value').attr('data-id',
						$(this).closest("tr").attr('data-id'));
				$('#trailer-value')
						.val($(this).closest("tr").attr('data-info'));
				//
				loadTrailerBinding($(this).closest("tr").attr('data-id'));
				$('#trailerModal').modal('hide');
			});
}
function loadTrailerBinding(trailerId) {

	ajaxAction({
		url : '/api/trailer/' + trailerId,
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {
			if (data.container) {
				$('#container-value').val(
						data.container.code + '(' + data.container.name + ')');
				$('#container-value').attr('data-id', data.container.id);
			}
			if (data.bracket) {
				$('#bracket-value').val(
						data.bracket.code + '(' + data.bracket.name + ')');
				$('#bracket-value').attr('data-id', data.bracket.id);
			}
			if (data.employee) {
				$('#employee-value').val(
						data.employee.id + '(' + data.employee.name + ')');
				$('#employee-value').attr('data-id', data.employee.id);
			}
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('拖车绑定信息加载失败!');
		}
	});

}
//
var ContainerTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.containers.pageList, function(index, container) {
			var buyDate = container.buyDate ? dateFormat.format(new Date(
					container.buyDate)) : '';
			var html = '<tr data-id="' + container.id + '" data-info="'
					+ container.code + '(' + container.name + ')' + '">'
					+ container.id + '">' + '<td>' + container.code + '</td>'
					+ '<td>' + container.name + '</td>' + '<td>'
					+ container.containerType.name + '</td>' + '<td>' + buyDate
					+ '</td>'
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

ContainerTargetObject.prototype.bind = function() {
	$('#container-content td button').on(
			'click',
			function() {
				$('#container-value').attr('data-id',
						$(this).closest("tr").attr('data-id'));
				$('#container-value').val(
						$(this).closest("tr").attr('data-info'));
				$('#containerModal').modal('hide');
			});
}

//
var BracketTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.brackets.pageList, function(index, bracket) {
			var buyDate = bracket.buyDate ? dateFormat.format(new Date(
					bracket.buyDate)) : '';
			var html = '<tr data-id="' + bracket.id + '" data-info="'
					+ bracket.code + '(' + bracket.plateNum + ')' + '">'
					+ '<td>' + bracket.code + '</td>' + '<td>'
					+ bracket.plateNum + '</td>' + '<td>' + bracket.name
					+ '</td>' + '<td>' + buyDate + '</td>'
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

BracketTargetObject.prototype.bind = function() {

	$('#bracket-content td button').on(
			'click',
			function() {
				$('#bracket-value').attr('data-id',
						$(this).closest("tr").attr('data-id'));
				$('#bracket-value')
						.val($(this).closest("tr").attr('data-info'));
				$('#bracketModal').modal('hide');
			});

}
//
var EmployeeTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.employees.pageList, function(index, employee) {
			var entryDate = employee.entryDate ? dateFormat.format(new Date(
					employee.entryDate)) : '';
			var html = '<tr data-id="' + employee.id + '" data-info="'
					+ employee.id + '(' + employee.name + ')' + '">' + '<td>'
					+ employee.id + '</td>' + '<td>' + employee.name + '</td>'
					+ '<td>' + employee.gender + '</td>' + '<td>'
					+ employee.birth + '</td>' + '<td>' + employee.position
					+ '</td>' + '<td>' + entryDate + '</td>'
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

	$('#employee-content td button').on(
			'click',
			function() {
				$('#employee-value').attr('data-id',
						$(this).closest("tr").attr('data-id'));
				$('#employee-value').val(
						$(this).closest("tr").attr('data-info'));
				$('#employeeModal').modal('hide');
			});
}
