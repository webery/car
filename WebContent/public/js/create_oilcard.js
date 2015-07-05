$(function() {

	$('#oilcard-form').submit(function(e) {
		$("#submit").attr("disabled", true);
		$('#oilcard-form').ajaxSubmit({
			url : '/api/oilcard',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message);
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('服务器忙!');
			},
		});
		e.preventDefault();
		return false;
	});

});