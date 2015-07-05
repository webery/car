$(function() {
	$('#query').on(
			'click',
			function() {
				var cityName = $('#searchKeyWord').val();
				if (cityName.length > 5) {
					alert('城市名称0-5 个字');
					return false;
				}
				var page = new AjaxPageParser(new TargetObject('#page',
						'#route-content', '/api/routes'),
						'page');
				page.pageCallBack('1');
			});
});

var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.routes.pageList, function(index, route) {
			var html = '<tr>' + '<td>' + route.start.name + '</td>' + '<td>'
					+ route.destination.name + '</td>' + '<td>' + route.name
					+ '</td>' + '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {
			cityName : $('#searchKeyWord').val(),
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {
}