$(function() {
	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(
						new TargetObject('#page', '#container-content',
								'/api/containers'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#container-form').submit(
			function(e) {

				var size = $('#size').val();

				if (size < 0 || size > 300) {
					alter('方数大小在0-300之间');
					return false;
				}

				var containerId = $('#container-form').attr('data-id');
				if (typeof (containerId) == 'undefined' || containerId == null
						|| containerId.length > 24) {
					alert('页面加载的信息错误!请重新加载!');
					e.preventDefault();
					return false;
				}
				$("#submit").attr("disabled", true);
				$('#container-form').ajaxSubmit({
					url : '/api/container/' + containerId,
					type : 'PUT',
					dataType : 'json',
					success : function(data) {
						$("#submit").attr("disabled", false);
						alert(data.message)
					},
					error : function() {
						$("#submit").attr("disabled", false);
						alert('修改失败!服务器错误!')
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
						jsonPageInfo.containers.pageList,
						function(index, container) {
							var buyDate = container.buyDate ? dateFormat
									.format(new Date(container.buyDate)) : '';
							var html = '<tr data-id="'
									+ container.id
									+ '">'
									+ '<td>'
									+ container.code
									+ '</td>'
									+ '<td>'
									+ container.name
									+ '</td>'
									+ '<td>'
									+ container.size
									+ '</td>'
									+ '<td>'
									+ buyDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#containerModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var code = $('#search-word').val();

		var paramData = {
			code : code,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {
	$('#container-content td .glyphicon-eye-open').on(
			'click',
			function() {
				var containerId = $(this).closest('tr').attr('data-id');
				if (typeof (containerId) == 'undefined' || containerId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/container/' + containerId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						
						var data = msg.data;

						$('#container-form').attr('data-id', data.id);
						$('#code').val(data.code);
						$('#name').val(data.name);
						var buyDate = data.buyDate ? dateFormat
								.format(new Date(data.buyDate)) : '';
						$('#buyDate').val(buyDate);
						$('#price').val(data.price);
						$('#size').val(data.size);
						$('#remark').val(data.remark);

					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});

			});
};
//