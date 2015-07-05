$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#bracket-content', '/api/oilCardPayments'), 'page');
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
		$
				.each(
						jsonPageInfo.brackets.pageList,
						function(index, bracket) {
							var buyDate = bracket.buyDate ? dateFormat
									.format(new Date(bracket.buyDate)) : '';
							var html = '<tr data-id="'
									+ bracket.id
									+ '">'
									+ '<td>'
									+ bracket.plateNum
									+ '</td>'
									+ '<td>'
									+ bracket.name
									+ '</td>'
									+ '<td>'
									+ bracket.size
									+ '</td>'
									+ '<td>'
									+ buyDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#bracketModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-Word').val();
		if (search.length == 0) {
			search = null;
		}
		var paramData = {
			plateNum : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {

	$('td .glyphicon-eye-open').on(
			'click',
			function() {
				var bracketId = $(this).closest('tr').attr('data-id');
				if (typeof (bracketId) == 'undefined' || bracketId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/bracket/' + bracketId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						var data = msg.data;
						$('#bracket-form').attr('data-id', data.id);
						$('#plateNum').val(data.plateNum);
						$('#name').val(data.name);
						$('#size').val(data.size);
						var buyDate = data.buyDate ? dateFormat
								.format(new Date(data.buyDate)) : '';
						$('#buyDate').val(buyDate);
						$('#price').val(data.price);
						$('#remark').val(data.remark);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});

}
//
