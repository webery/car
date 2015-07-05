$(function() {

	$('#client-form').submit(function(e) {
		$("#submit").attr("disabled", true);
		$('#client-form').ajaxSubmit({
			url : '/api/client',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
	ajaxAction({
		url : '/api/provinces',
		method : 'GET',
	}, {}, {
		callbackHandler : function(data) {
			var provinces = data;
			$('#province').empty();
			$.each(provinces, function(index, province) {
				var html = ' <option value ="' + province.id + '">'
						+ province.name + '</option>';
				$('#province').append(html);
			});
			//
			loadCityByProvince(provinces[0].id);
			//
			$('#province').change(function() {
				var id = $('#province').val();
				loadCityByProvince(id);
			});

		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});
	//

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
			$('#city').empty();
			$.each(citys, function(index, city) {
				var html = ' <option value ="' + city.id + '">' + city.name
						+ '</option>';
				$('#city').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}
