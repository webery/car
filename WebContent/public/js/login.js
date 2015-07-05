$(function() {
	
	$('#login-form').submit(
			function(e) {
				var account = $("#account").val();
				var password = $("#password").val();
				$("#login-submit").attr("disabled", true);
				ajaxAction({
					url : '/api/login',
					method : 'POST',
				}, {
					account : account,
					password : password,
				}, {
					callbackHandler : function(data) {
						alert(data.message);
						if (data.message == '登陆成功') {
							window.location.href = '/view';
						}
						$("#login-submit").attr("disabled", false);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
						$("#login-submit").attr("disabled", false);
					}
				});
				alert(11);
				e.preventDefault();
				return false;
			});
});
