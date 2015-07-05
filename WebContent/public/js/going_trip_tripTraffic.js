$(function() {
	$('#tripTraffic-form').submit(function(e) {

		var tripTrafficId = $('#current-trip-id').attr('data-id');
		
		if(tripTrafficId == '') {
			e.preventDefault();
			return false;
		}
		
		$("#tripTraffic-submit").attr("disabled", true);
		$('#tripTraffic-form').ajaxSubmit({
			url : '/api/tripTraffic/' + tripTrafficId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#tripTraffic-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#tripTraffic-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

	$('#btn-delete-tripTraffic').on(
			'click',
			function() {
				var tripTrafficId = $('#tt-id').val();
				if (tripTrafficId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/tripTraffic/' + tripTrafficId,
					method : 'DELETE',
				}, {}, {
					callbackHandler : function(msg) {
						alert(msg.message);
						if (msg.code == 1000) {
							initTraffic();
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
