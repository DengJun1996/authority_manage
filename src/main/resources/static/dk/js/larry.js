layui.config({
	base: '/dk/js/'
}).use(['jquery', 'element', 'layer', 'navtab'], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	let element = layui.element(),
		navtab = layui.navtab({
			elem: '.larry-tab-box'
		});


	//iframe自适应
	$(window).on('resize', function() {
		let $content = $('#larry-tab .layui-tab-content');
		$content.height('100%');
		$content.find('iframe').each(function() {
			$(this).height('100%');
		});
		tab_W = $('#larry-tab').width();
		// larry-footer：p-admin宽度设定
		let larryFoot = $('#larry-footer').width();
		$('#larry-footer p.p-admin').width(larryFoot - 300);
		$('.layui-tab-content').height("100%");
		$('.layui-tab-item').height("100%");
	}).resize();

	// 左侧菜单导航-->tab
	$(function() {
		// 注入菜单
		// var $menu = $('.larry-tab-menu');
		// console.log($menu);
		// $('#larry-tab .layui-tab-title').append($menu);
		$('#larry-nav-side').click(function() {
			if ($(this).attr('lay-filter') !== undefined) {
				$(this).children('ul').find('li').each(function() {
					let $this = $(this);
					if ($this.find('dl').length > 0) {
						let $dd = $this.find('dd').each(function() {
							$(this).click(function() {
								let $a = $(this).children('a');
								let href = $a.data('url');
								let icon = $a.children('i:first').data('icon');
								let title = $a.children('span').text();
								let data = {
									href: href,
									icon: icon,
									title: title
								}
								navtab.tabAdd(data);
							});
						});
					} else {
						$this.click(function() {
							let $a = $(this).children('a');
							let href = $a.data('url');
							let icon = $a.children('i:first').data('icon');
							let title = $a.children('span').text();
							let data = {
								href: href,
								icon: icon,
								title: title
							}
							navtab.tabAdd(data);
						});
					}
				});
			}
		})
	})


});
