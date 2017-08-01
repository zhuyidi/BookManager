var obj;//Log数组
var index; //当前索引
function renderLog() {
    if (index < obj.length) {
        //TODO
        //修改Log样式，并隐藏
        $("#log-content").prepend(obj[index++]);
        slideDown(3, function () {
            renderLog();
        });
    }
}