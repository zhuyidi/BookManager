<%@ page import="Dao.DaoObject.Book_classDaoImpl" %>
<%@ page import="Dao.ValueObject.Book_class" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: hg_yi
  Date: 17-7-22
  Time: 上午8:57
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>标签树</title>
    <link rel="shortcut icon" href="https://www.xiyoulinux.org/images/xiyoulinux.png"/>
    <link rel="stylesheet" href="https://static.segmentfault.com/v-59689f47/global/css/global.css"/>
    <link rel="stylesheet" href="https://static.segmentfault.com/v-59689f47/tag/css/tag.css"/>
    <link rel="stylesheet" href="https://static.segmentfault.com/v-59689f47/global/css/responsive.css"/>
    <style type="text/css">
        .btn{
            width:100px;
            margin:0 auto;
        }

        .box{
            width: auto;
            font-size: 200px;
        }

        .footer {
            margin-top:1px;
            padding:10px;
            text-align:center;
        }

        .ui-dialog{
            width: 380px;height: 300px;display: none;
            position: absolute;z-index: 9000;
            top: 0px;left: 0px;
            border: 1px solid #D5D5D5;background: #fff;
        }

        .ui-dialog a{text-decoration: none;}

        .ui-dialog-title{
            height: 48px;line-height: 48px; padding:0px 20px;color: #535353;font-size: 16px;
            border-bottom: 1px solid #efefef;background: #f5f5f5;
            cursor: move;
            user-select:none;
        }

        .ui-dialog-closebutton{
            width: 16px;height: 16px;display: block;
            position: absolute;top: 12px;right: 20px;
            background: url(img/close_def.png) no-repeat;cursor: pointer;

        }

        .ui-dialog-closebutton:hover{background:url(img/close_hov.png);}

        .ui-dialog-content{
            padding: 15px 20px;
        }

        .ui-dialog-pt15{
            padding-top: 15px;
        }

        .ui-dialog-l40{
            height: 40px;line-height: 40px;
            text-align: right;
        }

        .ui-dialog-input{
            width: 100%;height: 40px;
            margin: 0px;padding:0px;
            border: 1px solid #d5d5d5;
            font-size: 16px;color: #c1c1c1;
            text-indent: 25px;
            outline: none;
        }

        .ui-dialog-submit{
            width: 100%;height: 50px;background: #3b7ae3;border:none;font-size: 16px;color: #fff;
            outline: none;text-decoration: none;
            display: block;text-align: center;line-height: 50px;
        }

        .ui-dialog-submit:hover{
            background: #3f81b0;
        }

        .link{
            text-align: right;
            line-height: 20px;
            padding-right: 40px;
        }

        .ui-mask{
            width: 100%;height:100%;background: #000;
            position: absolute;top: 0px;height: 0px;z-index: 8000;
            opacity:0.4; filter: Alpha(opacity=40);
        }
    </style>
</head>

<body data-mod="tag" class="tag-index ">
<div class="wrap">
    <div class="container">
        <strong>
            <h1 style="text-align:center">XiyouLinux Group 图书标签树</h1>
        </strong>
        <div style="text-align: right">
            <a href="main.jsp">返回首页</a>
        </div>
        <h1 class="h3 mt30">标签</h1>
        <p>沉淀下来的，才是最好的～～～</p>

        <h2 class="h4 mt30">全部标签（添加标签的时候需要同时指定一级标签和二级标签哦～～～）</h2>
        <div class="row tag-list mt20">
            <%
                int format;
                Book_classDaoImpl bookClass = new Book_classDaoImpl();
                List<Book_class> bookClasses = bookClass.getBookClasses(0);

                if (bookClasses.size() % 4 != 0) {
                    format = bookClasses.size()/4 + 1;
                } else {
                    format = bookClasses.size()/4;
                }
                for(int i = 0; i < 4; i++) {
            %>
            <section class="tag-list__item col-md-3">
                <%
                    for (int j = i*format; j < i*format+format; j++) {
                        if (j == bookClasses.size()) {
                            break;
                        }
                %>
                <div class="tag-list__itemWraper">
                    <h3 class="h5 tag-list__itemheader">
                        <%= bookClasses.get(j).getName()%>
                    </h3>
                    <ul class="tag-list__itembody taglist--inline multi">
                        <%
                            List<Book_class> bookChildClasses = bookClass.getBookClasses(bookClasses.get(j).getId());
                            for (Book_class book_childClass : bookChildClasses) {
                        %>
                        <li class="tagPopup">
                            <a href=<%=request.getContextPath()%>/label_view.do?keyWords=<%= book_childClass.getId()%>&mark=1 class="tag" data-toggle="popover" keyWords=<%= book_childClass.getId()%> data-original-title= <%=bookClasses.get(j).getName()%>>
                                <%= book_childClass.getName()%>
                            </a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
                <%
                    }
                %>
            </section>
            <%
                }
            %>
        </div>
    </div>
</div>

<div class="ui-dialog" id="dialogMove" onselectstart='return false;'>
    <div class="ui-dialog-title" id="dialogDrag"  onselectstart="return false;" >
        添加标签
        <a class="ui-dialog-closebutton" href="javascript:hideDialog();"></a>
    </div>

    <div class="ui-dialog-content">
        <form action="<%=request.getContextPath()%>/labeladd.do" method="post">
            <div class="ui-dialog-l40 ui-dialog-pt15">
                <input class="ui-dialog-input ui-dialog-input-username" placeholder="一级标签" type="text" value="" name="firstlabel" />
            </div>
            <br> <br>
            <div class="ui-dialog-l40 ui-dialog-pt15">
                <input class="ui-dialog-input ui-dialog-input-password" placeholder="二级标签" type="text" value="" name="leaflabel" />
            </div>
            <br> <br> <br>
            <div>
                <input class="ui-dialog-submit" type="submit" name="create" value="确认"/>
            </div>
        </form>
    </div>
</div>

<div class="box footer">
    <a href="javascript:showDialog();" style="text-decoration:none">
        <button  type="button" class="btn">添加标签</button>
    </a>
</div>

<footer class="footer">
    <div class="container">
        <div class="copyright">
            Copyright &copy; 2006 - 2017 西邮Linux兴趣小组 All Rights Reserved.<br>
        </div>
    </div>
</footer>

<script type="text/javascript">
    var dialogInstace , onMoveStartId , mousePos = {x:0,y:0};	//	用于记录当前可拖拽的对象
    //	获取元素对象
    function g(id){return document.getElementById(id);}
    //	自动居中元素（el = Element）
    function autoCenter(el){
        var bodyW = document.documentElement.clientWidth;
        var bodyH = document.documentElement.clientHeight;

        var elW = el.offsetWidth;
        var elH = el.offsetHeight;

        el.style.left = (bodyW-elW)/2 + 'px';
        el.style.top = (bodyH-elH)/2 + 'px';
    }

    //	自动扩展元素到全部显示区域
    function fillToBody( el ){
        el.style.width  = document.documentElement.clientWidth  +'px';
        el.style.height = document.documentElement.clientHeight + 'px';
    }

    //	Dialog实例化的方法
    function Dialog(dragId, moveId){
        var instace = {} ;

        instace.dragElement  = g(dragId);	//	允许执行 拖拽操作 的元素
        instace.moveElement  = g(moveId);	//	拖拽操作时，移动的元素

        stace = instace;

        instace.mouseOffsetLeft = 0;			//	拖拽操作时，移动元素的起始 X 点
        instace.mouseOffsetTop = 0;			//	拖拽操作时，移动元素的起始 Y 点

        instace.dragElement.addEventListener('mousedown',function(e){
            var e = e || window.event;

            instace.mouseOffsetLeft = e.pageX - instace.moveElement.offsetLeft ;
            instace.mouseOffsetTop  = e.pageY - instace.moveElement.offsetTop ;

            onMoveStartId = setInterval(onMoveStart,10);
            return false;
        })

        return instace;
    }

    //	在页面中侦听 鼠标弹起事件
    document.onmouseup = function(e){
        dialogInstace = false;
        clearInterval(onMoveStartId);
    }
    document.onmousemove = function( e ){
        var e = window.event || e;
        mousePos.x = e.clientX;
        mousePos.y = e.clientY;

        e.stopPropagation && e.stopPropagation();
        e.cancelBubble = true;
        e = this.originalEvent;
        e && ( e.preventDefault ? e.preventDefault() : e.returnValue = false );

        document.body.style.MozUserSelect = 'none';
    }

    function onMoveStart(){
        var instace = dialogInstace;
        if (instace) {
            var maxX = document.documentElement.clientWidth -  instace.moveElement.offsetWidth;
            var maxY = document.documentElement.clientHeight - instace.moveElement.offsetHeight ;

            instace.moveElement.style.left = Math.min( Math.max( ( mousePos.x - instace.mouseOffsetLeft) , 0 ) , maxX) + "px";
            instace.moveElement.style.top  = Math.min( Math.max( ( mousePos.y - instace.mouseOffsetTop ) , 0 ) , maxY) + "px";
        }
    }

    //重新调整对话框的位置和遮罩，并且展现
    function showDialog(){
        g('dialogMove').style.display = 'block';
        g('mask').style.display = 'block';
        autoCenter( g('dialogMove') );
        fillToBody( g('mask') );
    }

    //	关闭对话框
    function hideDialog(){
        g('dialogMove').style.display = 'none';
        g('mask').style.display = 'none';
    }

    //	侦听浏览器窗口大小变化
    window.onresize = showDialog;

    Dialog('dialogDrag','dialogMove');
</script>

</body>
</html>
