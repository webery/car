$(function() {

	//
	$('#q-employee')
			.on(
					'click',
					function() {
						var page = new AjaxPageParser(new EmployeeTargetObject(
								'#employee-page', '#employee-content',
								'/api/employees'), 'page');
						page.pageCallBack('1');
					});
	//
	$('#q-oilcard').on(
			'click',
			function() {
				var page = new AjaxPageParser(new OilcardTargetObject(
						'#oilcard-page', '#oilcard-content', '/api/oilcards'),
						'page');
				page.pageCallBack('1');
			});
	//
	$('#q-trailer').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TrailerTargetObject(
						'#trailer-page', '#trailer-content', '/api/trailers'),
						'page');
				page.pageCallBack('1');
			});
	//
	$('#q-container').on(
			'click',
			function() {
				var page = new AjaxPageParser(new ContainerTargetObject(
						'#container-page', '#container-content',
						'/api/containers'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#q-bracket').on(
			'click',
			function() {
				var page = new AjaxPageParser(new BracketTargetObject(
						'#bracket-page', '#bracket-content', '/api/brackets'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#trip-form').submit(function(e) {

		var salary = $('#salary').val();
		if (salary > 10000000) {
			alert('工资小于10000000');
			e.preventDefault();
			return false;
		}
		var cash = $('#cash').val();
		if (cash > 10000000) {
			alert('出车费小于10000000');
			e.preventDefault();
			return false;
		}
		var oilBalance = $('#oilBalance').val();
		if (oilBalance > 10000000) {
			alert('油卡余额小于10000000');
			e.preventDefault();
			return false;
		}

		if ($('#employee-id').val() == '') {
			alert('请选择司机');
			e.preventDefault();
			return false;
		}

		if ($('#oilcard-id').val().length != 24) {
			alert('请选择油卡');
			e.preventDefault();
			return false;
		}

		if ($('#trailer-id').val().length != 24) {
			alert('请选择拖车');
			e.preventDefault();
			return false;
		}

		if ($('#container-id').val().length != 24) {
			alert('请选择货柜');
			e.preventDefault();
			return false;
		}

		if ($('#bracket-id').val().length != 24) {
			alert('请选择拖架');
			e.preventDefault();
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#trip-form').ajaxSubmit({
			url : '/api/trip',
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
});

// 司机
var EmployeeTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.employees.pageList, function(index, employee) {

			var html = '<tr data-id="' + employee.id + '">' + '<td>'
					+ employee.id + '</td>' + '<td>' + employee.name + '</td>'
					+ '<td>' + employee.position + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-emp').val();
		var paramData = {
			name : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}
EmployeeTargetObject.prototype.bind = function() {

	$('#employee-content td button').on(
			'click',
			function() {
				$('#employee-id').val($(this).closest("tr").attr('data-id'));
				$('#employee-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#employeeModal').modal('hide');
			});
}
// 油卡
var OilcardTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$.each(jsonPageInfo.oilcards.pageList, function(index, oilCard) {

			var html = '<tr data-id="' + oilCard.id + '">' + '<td>'
					+ oilCard.code + '</td>' + '<td>' + oilCard.name + '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-oilcard').val();

		var paramData = {
			code : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}
OilcardTargetObject.prototype.bind = function() {

	$('#oilcard-content td button').on(
			'click',
			function() {
				$('#oilcard-id').val($(this).closest("tr").attr('data-id'));
				$('#oilcard-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#oilcardModal').modal('hide');
			});
}
// 拖车
var TrailerTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.trailers.pageList, function(index, trailer) {
			var buyDate = trailer.buyDate ? dateFormat.format(new Date(
					trailer.buyDate)) : '';
			var html = '<tr data-id="' + trailer.id + '">' + '<td>'
					+ trailer.plateNum + '</td>' + '<td>' + trailer.name
					+ '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-trailer').val();
		var paramData = {
			plateNum : search,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

TrailerTargetObject.prototype.bind = function() {

	$('#trailer-content td button').on(
			'click',
			function() {
				$('#trailer-id').val($(this).closest("tr").attr('data-id'));
				$('#trailer-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#trailerModal').modal('hide');
			});
};
//
var ContainerTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.containers.pageList, function(index, container) {
			var buyDate = container.buyDate ? dateFormat.format(new Date(
					container.buyDate)) : '';
			var html = '<tr data-id="' + container.id + '">' + '<td>'
					+ container.code + '</td>' + '<td>' + container.name
					+ '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var code = $('#search-container').val();

		var paramData = {
			code : code,
		};

		return paramData;
	}

	// 分页请求参数，不需要传请求页码信息,上面使用类方法取得请求参数，是为了实现封装。使用者只需要在发送分页请求之前给这个参数赋值，都可以实现。建议使用面向对象的方式
	this.paramData = this.getParamData();

}

ContainerTargetObject.prototype.bind = function() {
	$('#container-content td button').on(
			'click',
			function() {
				$('#container-id').val($(this).closest("tr").attr('data-id'));
				$('#container-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#containerModal').modal('hide');
			});
};
//
var BracketTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;// 分页或者下拉信息显示标签的ID
	this.pageContainer = pageContainer;// 分页信息显示的容器(也就是json数据显示的地方)
	this.url = url;// 请求路径
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;
		$.each(jsonPageInfo.brackets.pageList, function(index, bracket) {
			var buyDate = bracket.buyDate ? dateFormat.format(new Date(
					bracket.buyDate)) : '';
			var html = '<tr data-id="' + bracket.id + '">' + '<td>'
					+ bracket.plateNum + '</td>' + '<td>' + bracket.name
					+ '</td>'
					+ '<td><button class="btn btn-info">选择</button></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var search = $('#search-bracket').val();
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

BracketTargetObject.prototype.bind = function() {
	$('#bracket-content td button').on(
			'click',
			function() {
				$('#bracket-id').val($(this).closest("tr").attr('data-id'));
				$('#bracket-show').val(
						$(this).closest("tr").children('td').eq(1).html());
				//
				$('#bracketModal').modal('hide');
			});
};
