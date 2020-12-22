
layui.define(['element','jquery'], function(exports){
   let  element = layui.element(),
        $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		module_name = 'navtab',
		globalTabIdIndex = 0,
		DkTab = function(){
              this.config ={
              	  elem: undefined,
				  closed: true 
              };
		};
        let ELEM = {};
        /**
         * [参数设置 options]
         */
		DkTab.prototype.set = function(options){
              let _this = this;
              $.extend(true, _this.config, options);
              return _this;
        };
        /**
         * [init 对象初始化]
         * @return {[type]} [返回对象初始化结果]
         */
		DkTab.prototype.init  = function(){
             let _this = this;
             let _config = _this.config;
             if(typeof(_config.elem) !== 'string' && typeof(_config.elem) !== 'object') {
			       layer.alert('Tab选项卡错误提示: elem参数未定义或设置出错，具体设置格式请参考文档API.');
		     }
		     let $container;
		     if(typeof(_config.elem) === 'string') {
			     $container = $('' + _config.elem + '');
		     }
		     if(typeof(_config.elem) === 'object') {
			     $container = _config.elem;
		     }

		     if($container.length === 0) {
				 $container = $(".larry-tab-box")
					 layer.alert('Tab选项卡错误提示:找不到elem参数配置的容器，请检查.');
		     }
		     let filter = $container.attr('lay-filter');
		     if(filter === undefined || filter === '') {
		     	filter = 'demo';
		     	//layer.alert('Tab选项卡错误提示:请为elem容器设置一个lay-filter过滤器');
		     }
		     _config.elem = $container;
		     ELEM.titleBox = $container.children('ul.layui-tab-title');
		     ELEM.contentBox = $container.children('div.layui-tab-content');
		     ELEM.tabFilter = filter;
		     return _this;
        };
        /**
         * [exists 在layui-tab中检查对应layui-tab-title是否存在，如果存在则返回索引值，不存在返回-1]
         * @param  {[type]} title [description]
         * @return {[type]}       [description]
         */
		DkTab.prototype.exists = function(title){
            let _this = ELEM.titleBox === undefined ? this.init() : this,
			    tabIndex = -1;
			ELEM.titleBox.find('li').each(function(i, e) {
			    let $em = $(this).children('em');
			    if($em.text() === title) {
				      tabIndex = i;
			    };
		    });
		    return tabIndex;
        };
        /**
         * [tabAdd 增加选项卡，如果已存在则增加this样式]
         * @param  {[type]} data [description]
         * @return {[type]}      [description]
         */
		DkTab.prototype.tabAdd = function(data){
            let _this = this;
		    let tabIndex = _this.exists(data.title);
		    // 若不存在
		    if(tabIndex === -1){
		    	globalTabIdIndex++;
		    	let dataId = data.href.toString().trim().replace('/',"").trim();
		    	let content = '<iframe src="' + data.href + '" data-id="' + globalTabIdIndex + '" class="larry-iframe" id="dk-iframe-'+dataId+'"></iframe>';
			    let title = '';
			    // 若icon有定义
			    if(data.icon !== undefined){
                    if(data.icon.indexOf('icon-') !== -1) {
					    title += '<i class="iconfont ' + data.icon + '"></i>';
				    } else {
				    	title += '<i class="layui-icon ">' + data.icon + '</i>';
				    }
			    }
			    title += '<em>' + data.title + '</em>';
			    if(_this.config.closed) {
				    title += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + globalTabIdIndex + '">&#x1006;</i>';
			    }
			    //添加tab
			    element.tabAdd(ELEM.tabFilter, {
				    title: title,
				    content: content
			    });
			    //iframe 自适应
			    ELEM.contentBox.find('iframe[data-id=' + globalTabIdIndex + ']').each(function() {
			    	$(this).height(ELEM.contentBox.height());
			    });
			    if(_this.config.closed) {
				//监听关闭事件
				    ELEM.titleBox.find('li').children('i.layui-tab-close[data-id=' + globalTabIdIndex + ']').on('click', function() {
				    	element.tabDelete(ELEM.tabFilter, $(this).parent('li').index()).init();
				    });
			    };
			    //切换到当前打开的选项卡
			    element.tabChange(ELEM.tabFilter, ELEM.titleBox.find('li').length - 1);
		    }else {
			    element.tabChange(ELEM.tabFilter, tabIndex);
		    }
        };
    let navtab = new DkTab();
    exports(module_name, function(options) {
		return navtab.set(options);
	});
		
});
