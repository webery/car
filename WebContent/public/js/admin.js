
var adminRouter = {
	'root' : 'http://localhost:8080/weber/admin/view',//
	// 1.st
	'stu-query' : '/student/query',
	'stu-entry' : '/student/save',
	// 2.tc
	'tc-query' : '/teacher/query',
	'tc-entry' : '/teacher/save',
	// 3.cs
	'cs-query' : '/course/query',
	'cs-entry' : '/course/save',
};

function routerHandle(container) {
	var hash = window.location.hash;
	var url = adminRouter[hash.replace('#', '')];

	if (typeof (url) == 'undefined') {
		return false;
	}

	$(container).load(adminRouter.root + url, function(response, status) {
		if (status == "success") {
			if (hash == "#stu-entry") {
				loadStuInfo();
			} else if (hash == "#stu-query") {
				loadCollege();
			}
			if (hash == "#tc-entry") {
				loadTcInfo();
			} else {

			}
		} else {
			alert('数据加载失败!')
		}
	});
}

$(window).bind('hashchange', function() {
	routerHandle('#content');
});

// tc
function bindTcEntry() {

	// serialize()
	$('#tcForm').submit(function(e) {
		$('#tcForm').ajaxSubmit({
			url : 'http://localhost:8080/weber/teacher/create',
			type : 'POST',
			dataType : 'json',
			success : function() {
				alert('保存成功')
			},
			error : function() {
				alert('保存失败')
			},
		});
		e.preventDefault();
		return false;
	});
}
function loadTcInfo() {

	ajaxAction('http://localhost:8080/weber/entry/teacher/teacherinfo', {}, {
		callbackHandler : function(data) {

			var provinces = data.provinces;
			var colleges = data.colleges;
			var nations = data.nations;
			var degrees = data.degrees;
			var jobTitles = data.jobTitles;

			$.each(provinces, function(index, province) {
				var html = ' <option value ="' + province.id + '">'
						+ province.name + '</option>';
				$('#province').append(html);
			});
			$.each(colleges, function(index, college) {
				var html = ' <option value ="' + college.id + '">'
						+ college.name + '</option>';
				$('#college').append(html);
			});
			$.each(nations, function(index, nation) {
				var html = ' <option value ="' + nation.id + '">' + nation.name
						+ '</option>';
				$('#nation').append(html);
			});
			$.each(degrees, function(index, degree) {
				var html = ' <option value ="' + degree.id + '">' + degree.name
						+ '</option>';
				$('#degree').append(html);
			});
			$.each(jobTitles, function(index, jobTitle) {
				var html = ' <option value ="' + jobTitle.id + '">'
						+ jobTitle.name + '</option>';
				$('#jobTitle').append(html);
			});
			//
			bindTcEntry();
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});
}

// Stu
function bindStuQuery() {

	//
	$('#college').change(
			function() {
				var id = $('#college').val();
				ajaxAction('http://localhost:8080/weber/collegemajor', {
					college : id
				}, {
					callbackHandler : function(data) {
						var majors = data;
						$('#major').empty();
						$('#major').append('<option value="-1">全部</option>');
						$.each(majors, function(index, major) {
							var html = ' <option value ="' + major.id + '">'
									+ major.name + '</option>';
							$('#major').append(html);
						});
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('数据加载异常!请刷新页面!');
					}
				});
			});
	//
	$('#query')
			.on(
					'click',
					function() {
						var params = {};
						var college = $('#college').val();
						var major = $('#major').val();
						var name = $('#name').val();
						if (college != -1) {
							params['college.id'] = college;
						}
						if (major != -1) {
							params['major.id'] = major;
						}
						if (name != '') {
							params['name'] = name;
						}
						//
						ajaxAction(
								'http://localhost:8080/weber/student/students',
								params,
								{
									callbackHandler : function(data) {
										var students = data;
										$('#student-content').empty();
										$
												.each(
														students,
														function(index, student) {
															var html = '<tr>'
																	+ '<td>'
																	+ student.id
																	+ '</td>'
																	+ '<td>'
																	+ student.name
																	+ '</td>'
																	+ '<td>'
																	+ student.college.name
																	+ '</td>'
																	+ '<td>'
																	+ student.major.name
																	+ '</td>'
																	+ '<td>'
																	+ student.gender
																	+ '</td>'
																	+ '<td>'
																	+ student.status
																	+ '</td>'
																	+ '<td>'
																	+ '<span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#detailModal"></span>'
																	+ '</td>'
															'</tr>'
															$(
																	'#student-content')
																	.append(
																			html);
														});
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert('数据加载异常!');
									}
								});

					});
}

function bindStuEntry() {
	// 1.
	$('#college').change(
			function() {
				var id = $('#college').val();
				ajaxAction('http://localhost:8080/weber/collegemajor', {
					college : id
				}, {
					callbackHandler : function(data) {
						var majors = data;
						$('#major').empty();
						$.each(majors, function(index, major) {
							var html = ' <option value ="' + major.id + '">'
									+ major.name + '</option>';
							$('#major').append(html);
						});
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('数据加载异常!请刷新页面!');
					}
				});
			});
	// 2..serialize()
	$('#stuform').submit(function(e) {
		$('#stuform').ajaxSubmit({
			url : 'http://localhost:8080/weber/student/create',
			type : 'POST',
			dataType : 'json',
			success : function() {
				alert(1)
			},
			error : function() {
				alert(2)
			},
		});
		e.preventDefault();
		return false;
	});
	/*
	 * $('#submit').on('click', function () {
	 * 
	 * 
	 * alert($('form').serialize());
	 * ajaxAction('http://localhost:8080/weber/student/create',
	 * $('form').serialize(), { callbackHandler : function(data) { alert(data); },
	 * exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
	 * alert('添加失败!'); } });
	 * 
	 * });
	 */

}

function loadCollege() {

	ajaxAction('http://localhost:8080/weber/colleges', {}, {
		callbackHandler : function(data) {

			var colleges = data;
			$('#college').append('<option value="-1">全部</option>');
			$.each(colleges, function(index, college) {
				var html = ' <option value ="' + college.id + '">'
						+ college.name + '</option>';
				$('#college').append(html);
			});
			//
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});

	//
	bindStuQuery();
}

function loadStuInfo() {

	ajaxAction('http://localhost:8080/weber/entry/student/studentinfo', {}, {
		callbackHandler : function(data) {

			var provinces = data.provinces;
			var colleges = data.colleges;
			var nations = data.nations;

			$.each(provinces, function(index, province) {
				var html = ' <option value ="' + province.id + '">'
						+ province.name + '</option>';
				$('#province').append(html);
			});
			$.each(colleges, function(index, college) {
				var html = ' <option value ="' + college.id + '">'
						+ college.name + '</option>';
				$('#college').append(html);
			});
			$.each(nations, function(index, nation) {
				var html = ' <option value ="' + nation.id + '">' + nation.name
						+ '</option>';
				$('#nation').append(html);
			});
			//
			bindStuEntry();
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('数据加载异常!请刷新页面!');
		}
	});

}