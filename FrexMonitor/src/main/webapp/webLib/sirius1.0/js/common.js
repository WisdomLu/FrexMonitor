/*
 dotdotdot
 device
 basicRem(720p)
 String
 clone(obj)
 requestAnimationFrame

 */
// rem
var _siriusBaseRem = 720;
var _siriusFixedWidth = 0;
window.basicRem = function(width,fixedWidth){
    if(width){
        _siriusBaseRem = width;
    }
    if(fixedWidth){
        _siriusFixedWidth = fixedWidth;
    }
    if($(".sirius-rem-body").length > 0 ){
        var actualWidth = $(".sirius-rem-body").width() - _siriusFixedWidth;
        $("html").css({
            'font-size': actualWidth *100/ _siriusBaseRem
        });
    }
};
function setCreateAdProcessOuterBorder() {
    if($('#create_ad_process_row')) {
        var createAdProcessRowLeft = $('#create_ad_process_row').css('left');
        if(createAdProcessRowLeft) {
            createAdProcessRowLeft = createAdProcessRowLeft.substring(0, createAdProcessRowLeft.length - 2);
            var createAdProcessRowMarginLeft = $('#create_ad_process_row').css('margin-left');
            createAdProcessRowMarginLeft = createAdProcessRowMarginLeft.substring(0, createAdProcessRowMarginLeft.length - 2);
            var processOuterBorderWidth = parseFloat(createAdProcessRowLeft) + parseFloat(createAdProcessRowMarginLeft);
            $('#process-outer-border').css({
                'width':processOuterBorderWidth
            });
        }
    }
}

$(window).resize(function(){
    basicRem();
    setCreateAdProcessOuterBorder();
});


// js String 扩展
String.prototype.endsWith = function(str) {
    if (str == null || str == "" || this.length == 0
        || str.length > this.length)
        return false;
    if (this.substring(this.length - str.length) == str)
        return true;
    else
        return false;
    return true;
};
String.prototype.startsWith = function(str) {
    if (str == null || str == "" || this.length == 0
        || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
    return true;
};
/**
 *删除数组指定下标或指定对象
 */
Array.prototype.remove=function(obj){
    for(var i =0;i <this.length;i++){
        var temp = this[i];
        if(!isNaN(obj)){
            temp=i;
        }
        if(temp == obj){
            this.splice(i,1);
        }
    }
};
/**
 * 删除数组一些元素
 * @param obj
 */
Array.prototype.removeAll=function(arr){
    if(arr && arr.length > 0) {
        for(var i =0;i <arr.length;i++){
                this.remove(arr[i]);
        }
    }

};

//requestAnimationFrame扩展
(function() {
    var lastTime = 0;
    var vendors = ['ms', 'moz', 'webkit', 'o'];
    for (var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
        window.cancelAnimationFrame = window[vendors[x] + 'CancelAnimationFrame'] || window[vendors[x] + 'CancelRequestAnimationFrame'];
    }
    if (!window.requestAnimationFrame) window.requestAnimationFrame = function(callback, element) {
        var currTime = new Date().getTime();
        var timeToCall = Math.max(0, 16 - (currTime - lastTime));
        var id = window.setTimeout(function() {
            callback(currTime + timeToCall);
        }, timeToCall);
        lastTime = currTime + timeToCall;
        return id;
    };
    if (!window.cancelAnimationFrame) window.cancelAnimationFrame = function(id) {
        clearTimeout(id);
    };
}());
