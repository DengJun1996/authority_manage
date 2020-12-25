layui.define([
	'jquery'
], function(exports) {
	var $ = layui.jquery;
	var myAjax = {
		// 封装ajax
		ajax: function(type, uri, data, callback) {
			$.ajax({
				type: type,
				headers: { // 设置头部
					dnToken: localStorage.getItem('token')
				},
				url: uri,
				dataType: "json",
				data: data,
				traditional: true, //这里设置为true
				success: function(response) {
					callback(response);
				},
				error: function(response) {
					layer.msg(response.responseJSON.message, {
						icon: 5,
						time: 2000,
					}, function() {
						if (response.responseJSON.message === 'Token失效，请重新登录') {

							$.ajax({
								url: "/tokenLogout",
								headers: {
									'dnToken': localStorage.getItem('token')
								},
								type: "Post",
								success: function(data) {
									console.log(data);
									layer.msg(data.msg);
									localStorage.removeItem('token');
									localStorage.removeItem('userCount');
									location.reload(); //删除成功后再刷新
									parent.window.location = '/login';
								},
								error: function(data) {
									$.messager.alert('错误', data.msg);
								}
							});
						}
					});
				}
			})
		}
	}

	exports('myAjax', myAjax)
});
