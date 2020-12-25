
'use strict';
layui.use(['jquery','layer','element'],function(){
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
  let element = layui.element();
  
    // larry-side-menu向左折叠
    $('.larry-side-menu').click(function() {
      let sideWidth = $('#larry-side').width();
      if(sideWidth === 200) {
          $('#larry-body').animate({
            left: '0'
          }); //admin-footer
          $('#larry-footer').animate({
            left: '0'
          });
          $('#larry-side').animate({
            width: '0'
          });
      } else {
          $('#larry-body').animate({
            left: '200px'
          });
          $('#larry-footer').animate({
            left: '200px'
          });
          $('#larry-side').animate({
            width: '200px'
          });
      }
    });

 
    $(function(){
       // 刷新iframe操作
        $(".refreshThis").click(function(){
           $(".layui-tab-content .layui-tab-item").each(function(){
              if($(this).hasClass('layui-show')){
                 $(this).children('iframe')[0].contentWindow.location.reload(true);
              }
           });
        });

        // 关闭其他,除了当前页和首页
        $('.closePageOther').click(function () {
           let arrLi = $('.layui-body .layui-tab-title').find('li'),
               len   = arrLi.length;
           if (len === 1 || len === 2) {
               layer.msg('没有可以关闭的Tab');
           } else if (len > 2) {
               $.each(arrLi,function () {
                   if ($(this).hasClass('layui-this') || $(this).hasClass('admin-home')) {
                   } else {
                       let id = $(this).children('.layui-tab-close').data('id');
                       $(this).remove();
                       $('.layui-tab-content .layui-tab-item').children('iframe').each(function () {
                           if ($(this).data('id') === id) {
                               $(this).parent().remove();
                           }
                       });
                   }
               })
           }
        });

        // 关闭其他的TAB
        $('.closePageAll').click(function () {
            let arrLi = $('.layui-body .layui-tab-title').find('li'),
                len   = arrLi.length;
            if (len === 1) {
                layer.msg('没有可以关闭的Tab');
                return;
            } else if (len > 1) {
                $.each(arrLi,function () {
                    if ($(this).hasClass('admin-home')) {
                    } else {
                        $(this).remove();
                        $('.layui-tab-content .layui-tab-item').children('iframe').each(function () {
                            if ($(this).data('id') !== 0) {
                                $(this).parent().remove();
                            } else {
                                $(this).parent().addClass('layui-show')
                            }
                        });
                    }
                });
            }
        });

    });


});
