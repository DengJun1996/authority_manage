layui.define([
	'jquery'
], function(exports) {
	var $ = layui.jquery;
	var btnPermiss = {
		// 设置按钮权限
		setBtnPermiss: function(title) {
			let AllPermiss =JSON.parse(localStorage.getItem('permissObj'));
			let PermissList = [];
			$.each(AllPermiss[title],function(i,val){
				PermissList.push(val.title);
			})
			
			return PermissList;
		}
	}

	exports('btnPermiss', btnPermiss)
});
