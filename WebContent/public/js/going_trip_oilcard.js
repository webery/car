$(function() {

	$('#oilCardCreate-form').submit(function(e) {

		var clientId = $('#oc-client-id').val();
		if (clientId.length != 24) {
			alert('请选择充值对象!');
			e.preventDefault();
			return false;
		}

		$("#oilCardCreate-submit").attr("disabled", true);
		$('#oilCardCreate-form').ajaxSubmit({
			url : '/api/oilCardPayment',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#oilCardCreate-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#oilCardCreate-submit").attr("disabled", false);
				alert('提交失败!服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
	$('#q-oilCardClient').on(
			'click',
			function() {
				var page = new AjaxPageParser(new OilCardClientTargetObject(
						'#oilCardClient-page', '#oilCardClient-content',
						'/api/clients'), 'page');
				page.pageCallBack('1');
			});
	// 加载单路线
	$('#btn-create-oilCard').on('click', function() {
		loadTripRouteSelect($('#current-trip-id').attr('data-id'));
	});
	$('#btn-query-oilCard').on('click', function() {
		loadOilCardPayment($('#current-trip-id').attr('data-id'));
	});

});

function loadTripRouteSelect(tripId) {
	ajaxAction({
		url : '/api/trip/' + tripId + '/tripRoutes/',
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {

			$('#oc-tripRoute').empty();
			$.each(data.tripRoutes, function(index, tripRoute) {
				var html = '<option value="' + tripRoute.id + '">'
						+ tripRoute.route.name + '</option>';
				$('#oc-tripRoute').append(html);
			}); // $.each
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});

}
//
function loadOilCardPayment(tripId) {
	ajaxAction(
			{
				url : '/api/trip/' + tripId + '/oilCardPayments/',
				method : 'GET',
			},
			{},
			{
				callbackHandler : function(data) {

					$('#oilCard-content').empty();
					$
							.each(
									data.oilCardPayments,
									function(index, payment) {
										var payDate = payment.payDate ? dateFormat
												.format(new Date(
														payment.payDate))
												: '';
										var html = '<tr data-id="'
												+ payment.id
												+ '">'
												+ '<td>'
												+ payment.oilCard.code
												+ '</td>'
												+ '<td>'
												+ payment.client.name
												+ '</td>'
												+ '<td>'
												+ payment.money
												+ '</td>'
												+ '<td>'
												+ payDate
												+ '<td>'
												+ payment.route.name
												+ '</td>'
												+ '<td>'
												+ payment.remark
												+ '</td>'
												+ '<td><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></td>'
												+ '</td>' + '</tr>';
										$('#oilCard-content').append(html);
									}); // $.each
					//
					$('#oilCard-content td .glyphicon-trash').on(
							'click',
							function() {
								var wait = confirm("确定要删除数据吗？删除后不可恢复!");
								if (!wait) {
									return false;
								}
								var oilCardPaymentId = $(this).closest("tr")
										.attr('data-id');
								var target = $(this).closest("tr");
								ajaxAction({
									url : '/api/oilCardPayment/'
											+ oilCardPaymentId,
									method : 'DELETE',
								}, {}, {
									callbackHandler : function(msg) {
										alert(msg.message);
										if (msg.code == 1000) {
											target.remove();
										}
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
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
//
var OilCardClientTargetObject = function(dom, pageContainer, url) {
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
		var name = $('#search-ocp-client').val();
		var paramData = {
			name : name,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

OilCardClientTargetObject.prototype.bind = function() {

	$('#oilCardClient-content td button').on(
			'click',
			function() {
				$('#oc-client-id').val($(this).closest("tr").attr('data-id'));
				$('#oc-client-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#oilCardClientModal').modal('hide');
			});
};