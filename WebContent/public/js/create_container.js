$(function() {

	//
	$('#container-form').submit(function(e) {

		var size = $('#size').val();

		if (size < 0 || size > 300) {
			alter('方数大小在0-300之间');
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#container-form').ajaxSubmit({
			url : '/api/container',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('保存失败!服务器错误!')
			},
		});
		e.preventDefault();
		return false;
	});
});