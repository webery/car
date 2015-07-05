$(function() {
	// 1.
	createRouteInit();
	// 2.
	$('#submit').on(
			'click',
			function() {

				var start = $('#start-id').val();
				var destination = $('#destination-id').val();

				if (start == '' || destination == '') {
					alert('请选择城市!');
					return false;
				}

				$('#submit').attr('disabled', true);
				ajaxAction({
					url : '/api/route',
					method : 'POST',
				}, {
					'start.id' : start,
					'destination.id' : destination,
				}, {
					callbackHandler : function(data) {
						$('#submit').attr('disabled', false);
						alert(data.message);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						$('#submit').attr('disabled', false);
						alert('服务器忙!');
					}
				});

			});
});

function loadCityByProvince(province) {
	ajaxAction({
		url : '/api/citys',
		method : 'GET',
	}, {
		province : province
	}, {
		callbackHandler : function(data) {
			var citys = data;
			$('#s_city').empty();
			$.each(citys, function(index, city) {
				var html = ' <option value ="' + city.id + '">' + city.name
						+ '</option>';
				$('#s_city').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

function createRouteInit() {
	// 1.load province
	ajaxAction({
		url : '/api/provinces',
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {
			var provinces = data;
			$('#s_province').empty();
			$.each(provinces, function(index, province) {
				var html = ' <option value ="' + province.id + '">'
						+ province.name + '</option>';
				$('#s_province').append(html);
			});
			//
			loadCityByProvince(provinces[0].id);
			//
			$('#s_province').change(function() {
				var id = $('#s_province').val();
				loadCityByProvince(id);
			});

		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});
	//
	$('#citySelectModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var whatever = button.data('whatever');
		$('#s_city_ok').attr('data-type', whatever);
	});
	$('#s_city_ok').on(
			'click',
			function() {
				var type = $(this).attr('data-type');
				var value = $('#s_city').val() + '-'
						+ $('#s_city option:selected').text();
				if (type == 'start') {
					var destinationValue = $('#destination').val();

					$('#start').val(value);
					$('#start-id').val($('#s_city').val());
					$('#citySelectModal').modal('hide');
					if (destinationValue != '') {
						var value1 = destinationValue
								.substring(destinationValue.indexOf('-') + 1);
						var value2 = value.substring(value.indexOf('-') + 1);
						$('#result').val(value1 + '<--->' + value2);
					}

				} else if (type == 'destination') {
					var startValue = $('#start').val();

					$('#destination').val(value);
					$('#destination-id').val($('#s_city').val());
					$('#citySelectModal').modal('hide');
					if (startValue != '') {
						var value1 = startValue.substring(startValue
								.indexOf('-') + 1);
						var value2 = value.substring(value.indexOf('-') + 1);
						$('#result').val(value1 + '<--->' + value2);
					}
				}
			});
	//

}

function createRouteBind() {

}
