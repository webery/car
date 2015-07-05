$(function() {
	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(
						new TargetObject('#page', '#warehouse-content',
								'/api/warehouses'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#warehouse-form').submit(
			function(e) {
				var warehouseId = $('#id-value').val();
				if (typeof (warehouseId) == 'undefined'
						|| warehouseId.length != 24) {
					alert('数据加载异常，不能提交，请重新加载!');
					e.preventDefault();
					return false;
				}
				if (typeof ($('#client-value').val()) == 'undefined'
						|| $('#client-value').val().length != 24) {
					alert('请选择仓库所属的客户!');
					e.preventDefault();
					return false;
				}
				$("#submit").attr("disabled", true);
				$('#warehouse-form').ajaxSubmit({
					url : '/api/warehouse/' + warehouseId,
					type : 'PUT',
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

var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$
				.each(
						jsonPageInfo.warehouses.pageList,
						function(index, warehouse) {
							var html = '<tr data-id="'
									+ warehouse.id
									+ '">'
									+ '<td>'
									+ warehouse.code
									+ '</td>'
									+ '<td>'
									+ warehouse.name
									+ '</td>'
									+ '<td>'
									+ warehouse.linkman
									+ '</td>'
									+ '<td>'
									+ warehouse.phone
									+ '</td>'
									+ '<td>'
									+ '<span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#clientModal"></span'
									+ '</td>' + '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {
	$('td .glyphicon-eye-open').on(
			'click',
			function() {
				var warehouseId = $(this).closest('tr').attr('data-id');
				if (typeof (warehouseId) == 'undefined' || warehouseId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/warehouse/' + warehouseId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(data) {
						if (typeof (data.message) != 'undefined') {
							alert(data.message);
							return false;
						}
						$('#warehouse-form').attr('data-id', data.id);
						$('#id-value').val(data.id);
						$('#code').val(data.code);
						$('#name').val(data.name);
						$('#linkman').val(data.linkman);
						$('#phone').val(data.phone);
						$('#address').val(data.address);
						$('#introduce').val(data.introduce);
						$('#client-value').val(data.client.id);
						$('#client-show').val(
								data.client.code + '-' + data.client.name);
						$('#province').html(
								'<option value="' + data.province.id + '">'
										+ data.province.name + '</option>');
						$('#city').html(
								'<option value="' + data.city.id + '">'
										+ data.city.name + '</option>');
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});
}
