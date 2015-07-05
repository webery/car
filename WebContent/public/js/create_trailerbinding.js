$(function() {

	$('#submit').on(
			'click',
			function() {
				//
				var trailer = $('#trailer').val();
				if (trailer == '') {
					alert('请选择拖车!');
					return false;
				}
				ajaxAction({
					url : '/api/trailerBinding',
					method : 'POST'
				}, {}, {
					callbackHandler : function(data) {

					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('数据库异常!');
					}
				});
			});
});