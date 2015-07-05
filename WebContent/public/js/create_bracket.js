$(function() {

	$('#bracket-form').submit(function(e) {

		var size = $('#size').val();

		if (size < 0 || size > 100) {
			alert('托架尺寸0-100');
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#bracket-form').ajaxSubmit({
			url : '/api/bracket',
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
