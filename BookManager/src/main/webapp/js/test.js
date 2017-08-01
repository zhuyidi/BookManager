$(document).ready(function(){
    //找到下拉框
    var carnameSelect = $(".carname").children("select");
    var cartypeSelect = $(".cartype").children("select");

    //给下拉框注册事件
    carnameSelect.change(function(){
        var carnameValue = $(this).val();
        if( carnameValue != ""){
            //carnameValue不为空的情况接着判断
            if(!carnameSelect.data(carnameValue)){
                //不在缓冲区中,需要向服务器请求
                $.post("ChainSelect",{keyword:carnameValue,type:"top"},function(data){
                    if (data.length != 0){
                        //返回的数据不为空
                        cartypeSelect.html("");
                        $("<option value=''>请选择汽车类型</option>").appendTo(cartypeSelect);
                        for(var i = 0;i < data.length; i++ ){
                            $("<option value =' " + data[i] + " '> "+ data[i] +"</option>").appendTo(cartypeSelect);
                        }
                        cartypeSelect.parent().show();
                        carnameSelect.next().show();
                    }else{
                        //返回的数据为空
                        cartypeSelect.parent().hide();
                        carnameSelect.next().hide();
                    }
                    carnameSelect.data(carnameValue,data);
                },"json");
            }else{
                //在缓冲区中
                var data = carnameSelect.data(carnameValue);
                if (data.length != 0){
                    //返回的数据不为空
                    cartypeSelect.html("");
                    $("<option value=''>请选择汽车类型</option>").appendTo(cartypeSelect);
                    for(var i = 0;i < data.length; i++ ){
                        $("<option value =' " + data[i] + " '> "+ data[i] +"</option>").appendTo(cartypeSelect);
                    }
                    cartypeSelect.parent().show();
                    carnameSelect.next().show();
                }else{
                    //返回的数据为空
                    cartypeSelect.parent().hide();
                    carnameSelect.next().hide();
                }
            }
        }else{
            //carnameValue为空的情况，隐藏第二个下拉框
            cartypeSelect.parent().hide();
            carnameSelect.next().hide();
        }
    });

});
