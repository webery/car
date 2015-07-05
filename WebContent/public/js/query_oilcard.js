$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#oilcard-page',
						'#oilcard-content', '/api/oilcards'),
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

		$.each(jsonPageInfo.oilcards.pageList, function(index, oilCard) {

			var entryDate = oilCard.entryDate ? dateFormat.format(new Date(
					oilCard.entryDate)) : '';
			var html = '<tr data-id="' + oilCard.id + '">' + '<td>'
					+ oilCard.code + '</td>' + '<td>' + oilCard.name + '</td>'
					+ '<td>' + oilCard.money + '</td>' + '<td>'
					+ entryDate + '</td>' + '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-oilcard').val();

		var paramData = {
			name : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {
}
//
