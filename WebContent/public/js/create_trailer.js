$(function() {
	//
	$('#trailer-form').submit(function(e) {

		var price = $('#price').val();
		if (price > 10000000 || price < 1) {
			alert('价格在0-10000000');
			e.preventDefault();
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#trailer-form').ajaxSubmit({
			url : '/api/trailer',
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

});
