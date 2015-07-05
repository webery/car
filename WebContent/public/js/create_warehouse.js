$(function() {

	//
	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#client-content', '/api/clients'),
						'page');
				page.pageCallBack('1');
			});
	//
	$('#warehouse-form').submit(
			function(e) {
				if (typeof ($('#client-value').val()) == 'undefined'
						|| $('#client-value').val().length == 0) {
					alert('请选择仓库所属的客户!');
					e.preventDefault();
					return false;
				}
				$("#submit").attr("disabled", true);
				$('#warehouse-form').ajaxSubmit({
					url : '/api/warehouse',
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
//
var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.clients.pageList, function(index, client) {
			var html = '<tr data-id="' + client.id + '" data-info="'
					+ client.code + '(' + client.name + ')' + '">' + '<td>'
					+ client.code + '</td>' + '<td>' + client.name + '</td>'
					+ '<td>' + client.linkman + '</td>' + '<td>' + client.phone
					+ '</td>' + '<td>'
					+ '<button class="btn btn-info">选择</button>' + '</td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	}
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {

	$('#client-content td button').on('click', function() {
		$('#client-value').val($(this).closest("tr").attr('data-id'));
		$('#client-show').val($(this).closest("tr").attr('data-info'));
		$('#clientModal').modal('hide');
	});

}
