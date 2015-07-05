function initTripMaintenanceForm() {

	$('#tc-id').val('');
	$('#tm-name').val('');
	$('#tm-money').val(0);
	$('#tm-address').val('');
	$('#tm-remark').val('');
}

//
function loadMaintenance() {

	var tripId = $('#current-trip-id').attr('data-id');

	ajaxAction({
		url : '/api/trip/' + tripId + '/tripMaintenance',
		method : 'GET',
	}, {}, {
		callbackHandler : function(msg) {
			var data = msg.data;

			initTripMaintenanceForm();

			// 显示存在想信息
			$('#tm-id').val(data.id);
			$('#tm-name').val(data.name);
			$('#tm-money').val(data.money);
			$('#tm-address').val(data.address);
			$('#tm-remark').val(data.remark);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}
