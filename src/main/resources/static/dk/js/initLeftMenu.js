layui.define(["element", "jquery"], function(exports) {
	var element = layui.element(),
		$ = layui.$,
		layer = layui.layer;
	var initLeftMenu = {
		LeftMenu: function(data) {
			var leftMenuHtml = '<ul class="layui-nav layui-nav-tree">\n';
			$.each(data, function(index, menu) {
				leftMenuHtml += '<li class="layui-nav-item">\n';
				if (menu.childTree != undefined && menu.childTree.length != 0) {
					leftMenuHtml += '<a href="javascript:;" ><i class="' + menu.icon + '"></i><span> ' + menu.title +
						'</span><em class="layui-nav-more"></em></a>';
					leftMenuHtml += '<dl class="layui-nav-child">\n';
					$.each(menu.childTree, function(childIndex, childMenu) {
						leftMenuHtml += '<dd>\n';
						leftMenuHtml += '<a href="javascript:;" data-url="' + childMenu.href + '"><i class="' + childMenu.icon +
							'" data-icon="' + childMenu.icon + '"></i><span> ' + childMenu.title + '</span></a>\n';
						leftMenuHtml += '</dd>\n';
					});

					leftMenuHtml += '</dl>\n';
				} else {
					leftMenuHtml += '<a href="javascript:;"  data-url="' + menu.href + '"><i class="' + menu.icon +
						'" data-icon="' + menu.icon + '"></i><span> ' + menu.title + '</span></a>\n';
				}
				leftMenuHtml += '</li>\n';
			})
			leftMenuHtml += '</ul>\n';

			//console.log(leftMenuHtml)
			$('#larry-nav-side').html(leftMenuHtml);
			element.init();
		}
	}

	exports('initLeftMenu', initLeftMenu)
})
