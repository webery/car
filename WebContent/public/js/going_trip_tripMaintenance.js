$(function() {
	$('#tripMaintenance-form').submit(function(e) {

		var tripMaintenanceId = $('#current-trip-id').attr('data-id');

		if (tripMaintenanceId == '') {
			e.preventDefault();
			return false;
		}

		$("#tripMaintenance-submit").attr("disabled", true);
		$('#tripMaintenance-form').ajaxSubmit({
			url : '/api/tripMaintenance/' + tripMaintenanceId,
			type : 'PUT',
			dataType : 'json',
			success : function(msg) {
				$("#tripMaintenance-submit").attr("disabled", false);
				alert(msg.message)

			},
			error : function() {
				$("#tripMaintenance-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

	$('#btn-delete-tripMaintenance').on(
			'click',
			function() {
				var tripMaintenanceId = $('#tc-id').val();
				if (tripMaintenanceId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/tripMaintenance/' + tripMaintenanceId,
					method : 'DELETE',
				}, {}, {
					callbackHandler : function(msg) {
						alert(msg.message);
						if (msg.code == 1000) {
							initTripMaintenanceForm();
						}
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});

			});

});
//
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
