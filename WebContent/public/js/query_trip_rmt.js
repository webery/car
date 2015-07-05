//
$(function() {
	$('#btn-tripRoute').on('click', function() {
		loadTripRoute();
	});
	// btn-tripMaintenance
	$('#btn-tripMaintenance').on('click', function() {
		loadTripMaintenance();
	});
	//
	$('#btn-tripTraffic').on('click', function() {
		loadTripTraffic();
	});
});

function loadTripRoute() {
	var tripId = $('#code').attr('data-id');
	if (typeof (tripId) == 'undefined' || tripId.length == 0) {
		alert('数据加载异常!');
		return false;
	}
	ajaxAction(
			{
				url : '/api/trip/' + tripId + '/tripRoutes',
				method : 'GET',
			},
			{
			},
			{
				callbackHandler : function(data) {
					if (typeof (data.message) != 'undefined') {
						alert(data.message);
						return false;
					}
					var statusMapper = {
						'0' : '进行中',
						'1' : '完成',
						'2' : '取消',
						'3' : '删除'
					};
					$('#tripRoute-content').empty();
					$
							.each(
									data.tripRoutes,
									function(index, tripRoute) {
										var startWarehouse = tripRoute.startWarehouse ? tripRoute.startWarehouse.name
												: '无';
										var finishWarehouse = tripRoute.finishWarehouse ? tripRoute.finishWarehouse.name
												: '无';
										var startDate = tripRoute.startDate ? dateFormat
												.format(new Date(tripRoute.startDate)) : '';
										var finishDate = tripRoute.finishDate ? dateFormat.format(new Date(tripRoute.finishDate)) : '';
												
										var html = '<tr>'
												+ '<td>'
												+ tripRoute.route.name
												+ '</td>'
												+ '<td>'
												+ startWarehouse
												+ '</td>'
												+ '<td>'
												+ finishWarehouse
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
												+ statusMapper[tripRoute.status]
												+ '</td>' + '<td>'
												+ tripRoute.remark + '</td>'
												+ '</tr>';
										$('#tripRoute-content').append(html);
									});// $.each

				},
				exceptionHandler : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert('服务器忙!');
				}
			});
}
//
function loadTripMaintenance() {

	var tripId = $('#code').attr('data-id');
	if (typeof (tripId) == 'undefined' || tripId.length != 24) {
		alert('数据加载异常!');
		return false;
	}
	ajaxAction({
		url : /api/trip/' + tripId + '/tripMaintenances',
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {
			if (typeof (data.message) != 'undefined') {
				alert(data.message);
				return false;
			}
			var statusMapper = {
				'0' : '进行中',
				'1' : '完成',
				'2' : '取消',
				'3' : '删除'
			};
			$('#tripMaintenance-content').empty();
			$.each(data.tripMaintenances, function(index, tripMaintenance) {
				var maintenanceDate = tripMaintenance.maintenanceDate ? dateFormat
						.format(new Date(tripMaintenance.maintenanceDate)) : '';
				var html ='<tr data-id="' + tripMaintenance.id + '">'
					+ '<td>' + tripMaintenance.name + '</td>'
					+ '<td>' + tripMaintenance.money + '</td>'
					+ '<td>' + maintenanceDate + '</td>'
					+ '<td>' + tripMaintenance.address + '</td>'
					+ '<td>' + statusMapper[tripMaintenance.status] + '</td>'
					+ '<td>' + tripMaintenance.remark + '</td>'
					+ '</tr>';
				$('#tripMaintenance-content').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}
//
//
function loadTripTraffic() {

	var tripId = $('#code').attr('data-id');
	if (typeof (tripId) == 'undefined' || tripId.length != 24) {
		alert('数据加载异常!');
		return false;
	}
	ajaxAction({
		url : '/api/trip/' + tripId + '/tripTraffics',
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {
			if (typeof (data.message) != 'undefined') {
				alert(data.message);
				return false;
			}
			var statusMapper = {
				'0' : '进行中',
				'1' : '完成',
				'2' : '取消',
				'3' : '删除'
			};
			$('#tripTraffic-content').empty();
			$.each(data.tripTraffics, function(index, tripTraffic) {
				
				var fineDate = tripTraffic.fineDate ? dateFormat
						.format(new Date(tripTraffic.fineDate)) : '';
				var html ='<tr data-id="' + tripTraffic.id + '">'
					+ '<td>' + tripTraffic.name + '</td>'
					+ '<td>' + tripTraffic.fine + '</td>'
					+ '<td>' + tripTraffic.point + '</td>'
					+ '<td>' + fineDate + '</td>'
					+ '<td>' + tripTraffic.address + '</td>'
					+ '<td>' + statusMapper[tripTraffic.status] + '</td>'
					+ '<td>' + tripTraffic.remark + '</td>'
					+ '</tr>';
				$('#tripTraffic-content').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});

}