$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TripTargetObject(
						'#Trip-page', '#trip-content', '/api/trips'), 'page');
				page.pageCallBack('1');
			});

	$('#warehouseModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var recipient = button.data('whatever');
		$('#s-warehouse-form').attr('data-type', recipient);
	});

	// trip-submit
	$('#trip-form').submit(function(e) {
		var tripId = $('#trip-id').val();
		if (typeof (tripId) == 'undefined' || tripId.length == 0) {
			e.preventDefault();
			return false;
		}
		$("#trip-submit").attr("disabled", true);
		$('#trip-form').ajaxSubmit({
			url : '/api/trip/' + tripId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#trip-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#trip-submit").attr("disabled", false);
				alert('修改失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
});

var TripTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		var statusMapper = {
			'0' : '进行中',
			'1' : '完成',
		}
		$
				.each(
						jsonPageInfo.trips.pageList,
						function(index, trip) {
							var startDate = trip.startDate ? dateFormat
									.format(new Date(trip.startDate)) : '';
							var finishDate = trip.finishDate ? dateFormat
									.format(new Date(trip.finishDate)) : '';
							var html = '<tr data-id="'
									+ trip.id
									+ '">'
									+ '<td>'
									+ trip.id
									+ '</td>'
									+ '<td>'
									+ trip.employee.name
									+ '</td>'
									+ '<td>'
									+ trip.trailer.plateNum
									+ '</td>'
									+ '<td>'
									+ startDate
									+ '</td>'
									+ '<td>'
									+ finishDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-resize-horizontal" aria-hidden="true" data-toggle="modal" data-target="#tripRouteModal"></span></td>'
									+ '<td><span class="glyphicon glyphicon-oil" aria-hidden="true" data-toggle="modal" data-target="#oilCardModal"></span></td>'
									+ '<td><span class="glyphicon glyphicon-wrench" aria-hidden="true" data-toggle="modal" data-target="#tripMaintenanceModal"></span></td>'
									+ '<td><span class="glyphicon glyphicon-book" aria-hidden="true" data-toggle="modal" data-target="#tripTrafficModal"></span></td>'
									+ '<td>'
									+ statusMapper[trip.status]
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" whatever="'
									+ trip.id
									+ '" data-target="#tripModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {
			type : 'all'
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TripTargetObject.prototype.bind = function() {

	// 存储当前行trip id
	$('#trip-content td .glyphicon').on(
			'click',
			function() {
				$('#current-trip-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});
	//
	$('#trip-content td .glyphicon-resize-horizontal').on('click', function() {
		$('#rt-trip-id').val($(this).closest('tr').attr('data-id'));// copy
		loadTripRoute($(this).closest('tr').attr('data-id'));
	});
	//
	$('#trip-content td .glyphicon-book').on('click', function() {
		initTraffic();
		loadTraffic();
	});
	//
	$('#trip-content td .glyphicon-wrench').on('click', function() {
		initTripMaintenanceForm();
		loadMaintenance();
	});
	//
	$('#trip-content td .glyphicon-eye-open').on(
			'click',
			function() {
				var tripId = $(this).closest('tr').attr('data-id');
				if (typeof (tripId) == 'undefined' || tripId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/trip/' + tripId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						var data = msg.data;
						var startDate = data.startDate ? dateFormat
								.format(new Date(data.startDate)) : '';
						var finishDate = data.finishDate ? dateFormat
								.format(new Date(data.finishDate)) : '';
						// $('#bracket-form').attr('data-id', data.id);
						$('#trip-id').val(data.id);
						$('#startDate').val(startDate);
						$('#finishDate').val(finishDate);
						$('#employee-name').val(data.employee.name);
						$('#salary').val(data.salary);
						$('#cash').val(data.cash);
						$('#trailer-plateNum').val(data.trailer.plateNum);
						$('#container-code').val(data.container.code);
						$('#bracket-plateNum').val(data.bracket.plateNum);
						$('#oilcard-code').val(data.oilcard.code);
						$('#oilBalance').val(data.oilBalance);
						$('#oilPayment').val(data.oilPayment);
						$('#oilMoney').val(data.oilMoney);
						$('#earning').val(data.earning);
						$('#payment').val(data.payment);
						$('#profit').val(data.profit);
						$('#roadToll').val(data.roadToll);
						$('#maintenanceCosts').val(data.maintenanceCosts);
						$('#trafficTicket').val(data.trafficTicket);
						$('#allowance').val(data.allowance);
						$('#reward').val(data.reward);
						$('#deductMoney').val(data.deductMoney);
						$('#remark').val(data.remark);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});
	//
	$('#trip-content td .glyphicon-check').on(
			'click',
			function() {
				var tripId = $(this).closest('tr').attr('data-id');
				if (tripId == '') {
					alert('加载数据异常,无法完成取消!');
					return false;
				}

				var wait = confirm('确定完成该派单?完成后将不能修改!');
				if (wait == false) {
					return false;
				}
				var target = $(this).closest('tr');
				ajaxAction({
					url : '/api/trip/' + tripId + '/status',
					method : 'PUT',
				}, {
					type : 'finish',
				}, {
					callbackHandler : function(data) {
						alert(data.message);
						if (data.code == 1000) {
							target.remove();
						}

					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});
	//
	$('#trip-content td .glyphicon-trash')
			.on(
					'click',
					function() {
						var tripId = $(this).closest('tr').attr('data-id');
						if (tripId == '') {
							alert('加载数据异常,无法完成取消！');
							return false;
						}

						var wait = confirm('确定删除该派单?删除后该派单的所有信息都将被删除(路线，油卡充值，维修记录等信息)!');
						if (wait == false) {
							return false;
						}
						var target = $(this).closest('tr');
						ajaxAction({
							url : '/api/trip/' + tripId,
							method : 'DELETE',
						}, {}, {
							callbackHandler : function(msg) {
								if (typeof (msg.message) != 'undefined') {
									alert(msg.message);
									if (msg.code == 1000) {
										target.remove();
									}
								}
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});

};
//
