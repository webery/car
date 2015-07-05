$(function() {
	//
	$('#create-all').on(
			'click',
			function() {
				var yearMonth = $('#yearMonth-all').val();
				if (/^\d{4}-?(?:0[1-9]|1[0-2])$/.test(yearMonth)) {
				} else {
					$("#btn-all-print").css("display", "none");
					alert('请输入正确的年月格式,例如2015-03');
					return false;
				}
				try {
					var printDate = Date.parse(yearMonth);
					var now = new Date();
					if (printDate > now) {
						alert("打印时间不能早于当前时间!");
						$("#btn-all-print").css("display", "none");
						return false;
					}

				} catch (exception) {
					alert("打印时间不能早于当前时间!");
					$("#btn-all-print").css("display", "none");
					return false;
				}
				$("#btn-all-print a").attr('href',
						'/doc/trip/allempTrip/excel?yearMonth=' + yearMonth);
				$("#btn-all-print").css("display", "block");
			});
	//
	$('#btn-query-emp')
			.on(
					'click',
					function() {
						var page = new AjaxPageParser(new EmployeeTargetObject(
								'#employee-page', '#employee-content',
								'/api/employees'), 'page');
						page.pageCallBack('1');
					});

});
//

//
var EmployeeTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$
				.each(
						jsonPageInfo.employees.pageList,
						function(index, employee) {
							var birth = employee.birth ? dateFormat
									.format(new Date(employee.birth)) : '';
							var startDate = employee.startDate ? dateFormat
									.format(new Date(employee.startDate)) : '';
							var html = '<tr data-id="'
									+ employee.id
									+ '">'
									+ '<td>'
									+ employee.id
									+ '</td>'
									+ '<td>'
									+ employee.name
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#employeeModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var name = $('#search-work').val();
		name = name == '' ? null : name;
		var paramData = {
			name : name,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

EmployeeTargetObject.prototype.bind = function() {
};