$(function() {

	loadNtPv();
	
	$('#employee-form').submit(function(e) {
		$("#submit").attr("disabled", true);
		$('#employee-form').ajaxSubmit({
			url : '/api/employee',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message);
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('请求失败!');
			},
		});
		e.preventDefault();
		return false;
	});
});

function loadNtPv() {
	ajaxAction({url: '/api/ntpvs',method:'GET'}, {}, {
		callbackHandler : function(data) {

			var provinces = data.provinces;
			var nations = data.nations;

			$.each(provinces, function(index, province) {
				var html = ' <option value ="' + province.id + '">'
						+ province.name + '</option>';
				$('#province').append(html);
			});
			$.each(nations, function(index, nation) {
				var html = ' <option value ="' + nation.id + '">' + nation.name
						+ '</option>';
				$('#nation').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});
}
