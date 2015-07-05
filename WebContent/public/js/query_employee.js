$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#employee-content', '/api/employees'),
						'page');
				page.pageCallBack('1');
			});
	//
	$('#employee-form').submit(function(e) {
		var employeeId = $('#id').val();
		$("#submit").attr("disabled", true);
		$('#employee-form').ajaxSubmit({
			url : '/api/employee/' + employeeId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message);
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('请求失败!');
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
									+ '<td>'
									+ employee.gender
									+ '</td>'
									+ '<td>'
									+ birth
									+ '</td>'
									+ '<td>'
									+ employee.position
									+ '</td>'
									+ '<td>'
									+ startDate
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

TargetObject.prototype.bind = function() {
	$('#employee-content td .glyphicon-eye-open').on(
			'click',
			function() {
				var employeeId = $(this).closest('tr').attr('data-id');
				if (typeof (employeeId) == 'undefined' || employeeId == '') {
					return false;
				}
				ajaxAction({
					url : '/api/employee/' + employeeId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(data) {
						if (typeof (data.message) != 'undefined') {
							alert(data.message);
							return false;
						}

						var birth = data.birth ? dateFormat.format(new Date(
								data.birth)) : '';
						var startDate = data.startDate ? dateFormat
								.format(new Date(data.startDate)) : '';
						$('#employee-form').attr('data-id', data.id);
						$('#id').val(data.id);
						$('#name').val(data.name);
						$('#gender').val(data.gender);
						$('#birth').val(birth);
						$('#nation').html(
								'<option value="' + data.nation.id + '">'
										+ data.nation.name + '</option>');
						$('#province').html(
								'<option value="' + data.province.id + '">'
										+ data.province.name + '</option>');
						$('#phone').val(data.phone);
						$('#address').val(data.address);
						$('#position').val(data.position);
						$('#startDate').val(startDate);
						$('#remark').val(data.remark);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});

			});
};