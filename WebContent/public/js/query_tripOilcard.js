$(function() {
	
	$('#btn-query-oilCard').on('click', function() {
		loadOilCardPayment($('#current-trip-id').attr('data-id'));
	});

});

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