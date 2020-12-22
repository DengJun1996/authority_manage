/**
 * 该JS文件为统一的工具类模块
 * @author wxl
 * @date 2020-02-29
 * @company www.dfdk.com.cn
 */
layui.define(['jquery', 'layer', 'upload', 'tree', 'table', 'base64', 'form'], function(exports) {

	let $ = layui.jquery,
		layer = layui.layer,
		dUpload = layui.upload,
		dTree = layui.tree,
		base64 = layui.base64,
		form = layui.form,
		table = layui.table;

	function DUtil() {
		this.layer = layer;
		this.$ = $;
	}

	DUtil.prototype.$ = function() {
		return $;
	}

	/**
	 *
	 * @param config
	 *      id:
	 *      url:
	 *      title:
	 *      param:
	 *      tableId:
	 *      cols:
	 * @return {*}
	 */
	DUtil.prototype.initTable = function(config) {
		let t = table.render({
			elem: '#' + config.id,
			url: config.url,
			toolbar: config.toolbar,
			data: config.data,
			height: config.height ? config.height : 500,
			parseData: function(res) {
				return {
					"code": res.code,
					"count": res.count,
					"data": res.data,
					"msg": res.msg,
				}
			},
			title: '<h3>' + config.title + '</h3>',
			method: config.method !== undefined ? config.method : 'get',
			request: {
				pageName: 'currentPage',
				limitName: 'pageSize'
			},
			where: config.param,
			headers: {
				'dnToken': config.dnToken
			},
			page: true,
			cols: [
				config.cols
			],
			loading: true,
			skin: 'line',
			even: true,
			id: config.tableId,
			done: typeof(config.done) === 'function' ? config.done : function(res, curr, count) {},
			error: function(r) {
				
				layer.msg(r.message, {
					icon: 5,
					time: 2000,
				}, function() {
					// $.ajax({
					// 	url: "/tokenLogout",
					// 	headers: {
					// 		'dnToken': localStorage.getItem('token')
					// 	},
					// 	type: "Post",
					// 	success: function(data) {
					// 		console.log(data);
					// 		layer.msg(data.msg);
					// 		localStorage.removeItem('token');
					// 		localStorage.removeItem('userCount');
					// 		location.reload(); //删除成功后再刷新
					// 		parent.window.location = '/login';
					// 	},
					// 	error: function(data) {
					// 		$.messager.alert('错误', data.msg);
					// 	}
					// });
				});
			}
		});
		return t;
	};



	/**
	 * 加密
	 * @param str  加密对象
	 * @param type 加密类型
	 *      0:默认加密类型Base64
	 *      1: MD5
	 *      2:SHA1
	 * @param key  加密key 可有可无
	 */
	DUtil.prototype.encode = function(data) {
		return base64.encode(data);
	}
	/**
	 * 解密
	 */
	DUtil.prototype.decode = function(data) {
		return base64.decode(data)
	}

	/**
	 * 认证
	 * 1.认证数字
	 * 2.认证字符串
	 * 3.认证IP
	 * @param args
	 *      需要被验证的参数
	 */
	DUtil.prototype.auth = function(args) {
		// 认证的正则表达式
		let regExpList = {
			ipReg: /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/,
			strReg: /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
			phnReg: /^[1][3,4,5,6,7,8,9][0-9]{9}$/
		};

		let exp = ".",
			flag = true;

		if (regExpList.ipReg.test(args)) {
			let arr = args.trim().split(exp),
				last = arr[arr.length - 1];
			if (parseInt(last) > 255) {
				layer.msg('输入有误');
				flag = false;
			}
		} else if (!regExpList.phnReg.test(args)) {
			layer.msg('输入有误');
			flag = false;
		} else if (!regExpList.strReg.test(args)) {
			layer.msg('输入有误');
			flag = false;
		} else {
			flag = false;
		}

		return flag;

	}

	/**
	 * 上传
	 * @param config
	 *      tBodyId: 表格tbodyId
	 *      qFileButtonId:选择文件按钮id
	 *      url:上传接口
	 *      submitId: 提交按钮Id
	 */
	DUtil.prototype.load = function(config) {
		let btn = '#' + config.btn;
		let tBody = $('#' + config.tBodyId);
		let dUploadIns = dUpload.render({
			elem: '#' + config.qFileButtonId,
			url: config.url,
			accept: 'file',
			data: config.data,
			headers: {
				'dnToken': config.dnToken
			},
			multiple: true,
			auto: false,
			bindAction: '#' + config.bindAction,
			before: function(obj) {

			},
			choose: function(obj) {
				let _this = this;
				let type = _this.data['type'];
				let val = $('#layui-select-origin').val();
				// 将每次选择的文件追加到文件队列
				let files = this.files = obj.pushFile();
				obj.preview(function(index, file, result) {
					if (type === '1' || type === '3') {
						if (val === '' || val.length === 0) {
							layer.msg('请选择拓扑来源');
							return false;
						}
						_this.data['origin'] = val;
					} else {
						_this.data['origin'] = -1;
					}
					let tr = $(['<tr id="upload-' + index + '">', '<td>' + file.name + '</td>', '<td>' + (file.size / 1024).toFixed(
							1) + 'kb</td>', '<td>等待上传</td>', '<td>',
						'<button class="layui-btn layui-btn-xs d-reload layui-hide">重传</button>&nbsp;&nbsp;',
						'<button class="layui-btn layui-btn-xs layui-btn-danger d-delete">删除</button>', '</td>', '</tr>'
					].join(''));

					// 单个上传
					tr.find('.d-reload').on('click', function() {
						obj.upload(index, file);
					});

					// 删除
					tr.find('.d-delete').on('click', function() {
						// 删除对应的文件
						delete files[index];
						tr.remove();
						setBtnStatus();
						//清空 input file 值，以免删除后出现同名文件不可选
						dUploadIns.config.elem.next()[0].value = '';
					});
					tBody.append(tr);
					setBtnStatus();
				});
			},
			done: function(res, index, upload) {			
				if (res.msg === '请求成功') {
					let tr = tBody.find('tr#upload-' + index),
						tds = tr.children();
					tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
					//清空操作
					tds.eq(3).html('');
					return delete this.files[index];
				} else {
					let tr = tBody.find('tr#upload-' + index),
						tds = tr.children();
					tds.eq(2).html('<span style="color: #FF5722;">'+res.msg+'</span>');
					//显示重传
					tds.eq(3).find('.d-reload').removeClass('layui-hide');
				}
				//删除文件队列已经上传成功的文件
				this.error(index, upload);
			},
			error: function(index, upload) {
				let tr = tBody.find('tr#upload-' + index),
					tds = tr.children();
				tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
				//显示重传
				tds.eq(3).find('.d-reload').removeClass('layui-hide');
			}
		});

		function setBtnStatus() {
			let trs = tBody.children('tr');
			if (trs.length > 0) {
				$(btn).attr('class', 'layui-btn layui-btn-normal layui-btn-small');
			} else {
				$(btn).attr('class', 'layui-btn layui-btn-normal layui-btn-small layui-btn-disabled');
			}
		}

	};

	/**
	 * 同步GET请求
	 * @param config
	 *      url:请求接口
	 *      param:参数
	 * @returns {*}
	 */
	DUtil.prototype.dGet = function(config) {
		var result;
		$.ajax({
			type: "GET",
			url: config.url,
			data: config.param,
			dataType: "json",
			async: false,
			headers: {
				'dnToken': window.localStorage.getItem('token')
			},
			success: function(data) {
				result = data;
			}
		});
		return result;
	}

	/**
	 * 同步POST请求
	 * @param config
	 *      url:请求接口
	 *      param:参数
	 * @returns {*}
	 */
	DUtil.prototype.dPost = function(config) {
		var result;
		$.ajax({
			type: "POST",
			url: config.url,
			data: config.param,
			dataType: "json",
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	};

	DUtil.prototype.ajax = function(config) {
		$.ajax({
			type: config.type === 'undefined' ? 'GET' : config.type,
			url: config.url,
			data: config.data,
			dataType: "json",
			success: typeof(config.success) === 'function' ? config.success : function(data) {}
		});
	}

	/**
	 * 加载树layui
	 * @param config
	 * @return {*}
	 */
	DUtil.prototype.initTree = function(config) {
		let treeInstan = dTree.render({
			elem: '#' + config.tId,
			showCheckbox: true,
			showLine: true,
			data: config.data,
			id: this.UUID()
		});
		return treeInstan;
	}

	DUtil.prototype.reLoadTree = function(ins, config) {
		let id = ins.config.id,
			data = this.dGet(config.url, config.param);
		dTree.reload(id, {
			data: data
		});
	}

	DUtil.prototype.dLayer = function(config) {
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
	};


	/**
	 * 生成唯一标示号
	 * @return {string}
	 * @constructor
	 */
	DUtil.prototype.UUID = function() {
		return Number(Math.random().toString().substr(3, 12) + Date.now()).toString(36);
	}

	/**
	 * 选项----单行选
	 * @param config
	 */
	DUtil.prototype.selectRow = function(config) {
		let defaultConfig = {
			id: '',
			pageSize: 5,
			url: '',
			data: [],
			prop: {}
		};

		let option = $.extend(defaultConfig, config);

		let ds = xmSelect.render({
			el: '#' + option.id,
			language: 'zn',
			toolbar: {
				show: true,
				list: ['ALL', 'CLEAR', 'REVERSE']
			},
			max: 1,
			repeat: false,
			paging: true,
			pageSize: option.pageSize,
			filterable: true,
			on: function(data) {
				data = data.arr[0];
				if (typeof(data) !== 'undefined') {
					$(ds.options.el).next().val(data['name']);
				}
			},
			data: []
		});

		if (option.data.length === 0 && option.url !== '') {
			$.get(option.url, function(res) {
				ds.update({
					data: res,
					autoRow: true
				});
			});
		} else if (option.data.length !== 0 && option.url === '') {
			ds.update({
				data: option.data,
				autoRow: true
			});
		}
	};

	DUtil.prototype.trim = String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}

	let dUtil = new DUtil();
	exports('dUtil', dUtil);
})
