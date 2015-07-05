$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#trailer-content', '/api/trailers'),
						'page');
				page.pageCallBack('1');
			});

	$('#trailer-form').submit(function(e) {

		var trailerId = $('#id-value').val();

		if (typeof (trailerId) == 'undefined' || trailerId.length != 24) {
			alter('车辆信息加载错误，不能提交信息，请重新加载车辆信息!');
			e.preventDefault();
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#trailer-form').ajaxSubmit({
			url : '/api/trailer/' + trailerId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('修改失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
});

var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$
				.each(
						jsonPageInfo.trailers.pageList,
						function(index, trailer) {
							var buyDate = trailer.buyDate ? dateFormat
									.format(new Date(trailer.buyDate)) : '';
							var html = '<tr data-id="'
									+ trailer.id
									+ '">'
									+ '<td>'
									+ trailer.plateNum
									+ '</td>'
									+ '<td>'
									+ trailer.name
									+ '</td>'
									+ '<td>'
									+ buyDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#trailerModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-word').val();
		var paramData = {
			plateNum : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {
	$('#trailer-content td .glyphicon-eye-open').on(
			'click',
			function() {
				var trailerId = $(this).closest('tr').attr('data-id');
				if (typeof (trailerId) == 'undefined' || trailerId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/trailer/' + trailerId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						var data = msg.data;
						var buyDate = data.buyDate ? dateFormat
								.format(new Date(data.buyDate)) : '';
						$('#trailer-form').attr('data-id', data.id);
						$('#id-value').val(data.id);
						$('#plateNum').val(data.plateNum);
						$('#name').val(data.name);
						$('#buyDate').val(buyDate);
						$('#price').val(data.price);
						$('#remark').val(data.remark);

						var employee = data.employee ? data.employee.name
								: '未绑定';
						var container = data.container ? data.container.code
								+ '(' + data.container.name + ')' : '未绑定';
						var bracket = data.bracket ? data.bracket.code + '('
								+ data.bracket.name + ')' : '未绑定';
						$('#emp-show').val(employee);
						$('#container-show').val(container);
						$('#bracket-show').val(bracket);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});

			});
}