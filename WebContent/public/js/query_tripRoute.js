$(function() {
	//
	$('#btn-query-tripRoute').on('click', function() {
		loadTripRoute($('#current-trip-id').attr('data-id'));
	});
});

//
var RouteTargetObject = function(dom, pageContainer, url) {
	this.dom = dom; // 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer; // 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url; // 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.routes.pageList, function(index, route) {
			var html = '<tr data-id="' + route.id + '" data-info="'
					+ route.name + '">' + '<td>' + route.start.name + '</td>'
					+ '<td>' + route.destination.name + '</td>' + '<td>'
					+ route.name + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		}); // $.each
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

RouteTargetObject.prototype.bind = function() {
	$('#route-content td button').on(
			'click',
			function() {
				$('#rt-route-id').val($(this).closest("tr").attr('data-id'));
				$('#rt-route-show').val(
						$(this).closest("tr").children('td').eq(2).html());
				$('#routeModal').modal('hide');
			});
};
//
var TripRouteTargetObject = function(dom, pageContainer, url) {
	this.dom = dom; // 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer; // 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url; // 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.tripRoutes.pageList, function(index, tripRoute) {
			var html = '<tr>' + '<td>' + tripRoute.trip.code + '</td>' + '<td>'
					+ tripRoute.route.name + '</td>' + '<td>'
					+ tripRoute.startWarehouse + '</td>' + '<td>'
					+ tripRoute.finishWarehouse + '</td>' + '<td>'
					+ tripRoute.earning + '</td>' + '<td>' + tripRoute.remark
					+ '</td>' + '</tr>';
			$(pageContainer).append(html);
		}); // $.each
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

TripRouteTargetObject.prototype.bind = function() {
	$('#tripRoute-content td button').on('click', function() {
	});
};
//
function loadTripRoute() {
	var tripId = $('#current-trip-id').attr('data-id');
	if (typeof (tripId) == 'undefined' || tripId.length == 0) {
		alert('数据加载异常!');
		return false;
	}
	var target = new TripRouteTargetObject('#tripRoute-page',
			'#tripRoute-content', '/api/tripRoutes/?tripId=' + tripId);
	// target.paramData.tripId = ;
	var page = new AjaxPageParser(target, 'page');
	page.pageCallBack('1');
};
//
var ClientTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.clients.pageList, function(index, client) {
			var html = '<tr data-id="' + client.id + '">' + '<td>'
					+ client.code + '</td>' + '<td>' + client.name + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</td>' + '</tr>';
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

ClientTargetObject.prototype.bind = function() {

	$('#client-content td button').on(
			'click',
			function() {
				$('#rt-client-id').val($(this).closest("tr").attr('data-id'));
				$('#rt-client-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#clientModal').modal('hide');
			});
};
//
var TripRouteTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var name = $('#search-word').val();
		var paramData = {
			name : name,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TripRouteTargetObject.prototype.bind = function() {
};

//
function loadTripRoute(tripId) {
	ajaxAction(
			{
				url : '/api/trip/' + tripId + '/tripRoutes/',
				method : 'GET',
			},
			{},
			{
				callbackHandler : function(data) {

					$('#tripRoute-content').empty();
					$
							.each(
									data.tripRoutes,
									function(index, tripRoute) {

										var startDate = tripRoute.startDate ? dateFormat
												.format(new Date(
														tripRoute.startDate))
												: '';
										var finishDate = tripRoute.finishDate ? dateFormat
												.format(new Date(
														tripRoute.finishDate))
												: '';
										var html = '<tr data-id="'
												+ tripRoute.id
												+ '">'
												+ '<td>'
												+ tripRoute.route.name
												+ '</td>'
												+ '<td>'
												+ tripRoute.client.name
												+ '</td>'
												+ '<td>'
												+ startDate
												+ '</td>'
												+ '<td>'
												+ finishDate
												+ '</td>'
												+ '<td>'
												+ tripRoute.earning
												+ '</td>'
												+ '<td>'
												+ '<span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" '
												+ ' data-target="#tripRouteGoingModal"></span>'
												+ '</td>' + '</tr>';
										$('#tripRoute-content').append(html);
									}); // $.each
					// bind
					$('#tripRoute-content td .glyphicon-eye-open')
							.on(
									'click',
									function() {
										var tripRouteId = $(this).closest('tr')
												.attr('data-id');
										if (typeof (tripRouteId) == 'undefined'
												|| tripRouteId == '') {
											return false;
										}
										var modalType = $(this).attr(
												'data-target');
										ajaxAction(
												{
													url : '/api/tripRoute/'
															+ tripRouteId,
													method : 'GET',
												},
												{},
												{
													callbackHandler : function(
															msg) {
														var data = msg.data;
														var tripRoute = data;

														var startDate = data.startDate ? dateFormat
																.format(new Date(
																		data.startDate))
																: '';
														var finishDate = data.finishDate ? dateFormat
																.format(new Date(
																		data.finishDate))
																: '';
														if (modalType == '#tripRouteGoingModal') {
															$(
																	'#rtu-tripRoute-id')
																	.val(
																			data.id);
															$('#rtu-route-show')
																	.val(
																			data.route.name);
															$(
																	'#rtu-client-show')
																	.val(
																			data.client.name);
															$('#rtu-earning')
																	.val(
																			data.earning);
															$('#rtu-payment')
																	.val(
																			data.payment);
															$('#rtu-arrearage')
																	.val(
																			data.arrearage);
															$('#rtu-startDate')
																	.val(
																			startDate);
															$('#rtu-finishDate')
																	.val(
																			finishDate);
															$('#rtu-remark')
																	.val(
																			data.remark);
														} else if (modalType == '#tripRouteOtherModal') {

														}
													},
													exceptionHandler : function(
															XMLHttpRequest,
															textStatus,
															errorThrown) {
														alert('服务器忙!');
													}
												});
									});
				},
				exceptionHandler : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert('服务器忙!');
				}
			});

}