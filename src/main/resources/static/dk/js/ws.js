layui.define([
    'layer'
], function(exports) {
    'use strict';
    var layer = layui.layer;
    var Ws = function () {
       
    }

    Ws.prototype = {
        make:  (url) => {
            var socket;
            if (typeof(WebSocket) !== 'undefined') {
                socket = new WebSocket(url);
            } else {
                layer.msg("你的浏览器不支持WebSocket,请更换为Chrome或FireFox浏览器!")
            }

            socket.onopen = function () {
                console.log('=========>> 连接成功!')
            }
            socket.onerror = function () {
                console.log('=========>> 连接失败!')
            }
            socket.onclose = function () {
                console.log('=========>> 关闭连接!')
            }
            return socket;
        }
    }

    var ws = new Ws();
    exports('ws',ws)
});