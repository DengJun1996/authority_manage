(function($) {

	var layer;
	layui.use(['layer', 'treetable'], function() {
		layer = layui.layer;
	})

	$.extend({
		layer: {
			msg: function(msg, {}, callback) {
				layer.msg(msg, {}, callback);
			},
			load: function() {
				let index = layer.load(1);
				return index;
			},
			close: function(index) {
				layer.close(index);
			},
			confirm: function(msg, callback) {
				layer.confirm(msg, callback)
			},
			dLayer: function(config) {
				let def = {
					type: 1,
					title: '弹窗',
					url: '',
					data: [],
					area: ['720px', '440px'],
					shade: 0.8,
					id: 'dk_layer' + new Date().getTime() / 10000,
					btn: ['确定', '关闭'],
					btnAlign: 'r',
					offset: 'auto',
					moveType: 1
				};
				let options = $.extend(def, config);
				layer.open({
					type: options.type,
					title: options.title,
					closeBtn: false,
					area: options.area,
					shade: options.shade,
					id: options.id,
					btn: options.btn,
					btnAlign: options.btnAlign,
					moveType: options.moveType,
					content: options.content,
					zIndex: options.zIndex,
					offset: options.offset,
					success: typeof(options.success) === 'function' ? options.success : function(layero, index) {},
					yes: typeof(options.yes) === 'function' ? options.yes : function(index, layero) {},
					cancel: typeof(options.cancel) === 'function' ? options.cancel : function(index, layero) {},
					end: typeof(options.end) === 'function' ? options.end : function(index, layero) {}
				});
			}
		}
	});

})(jQuery)
