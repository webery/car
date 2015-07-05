//
function initTraffic() {
	$('#tt-id').val('');
	$('#tt-name').val('');
	$('#tt-fine').val(0);
	$('#tt-point').val(0);
	$('#tt-address').val('');
	$('#tt-remark').val('');
}
//
function loadTraffic() {

	initTraffic();
	var tripId = $('#current-trip-id').attr('data-id');

	ajaxAction({
		url : '/api/trip/' + tripId + '/tripTraffic/',
		method : 'GET',
	}, {}, {
		callbackHandler : function(msg) {
			var data = msg.data;

			// 显示存在想信息
			$('#tt-id').val(data.id);
			$('#tt-name').val(data.name);
			$('#tt-fine').val(data.fine);
			$('#tt-point').val(data.point);
			$('#tt-address').val(data.address);
			$('#tt-remark').val(data.remark);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}
