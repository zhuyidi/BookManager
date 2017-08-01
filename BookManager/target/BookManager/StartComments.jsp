<%--
  Created by IntelliJ IDEA.
  User: dongmengyuan
  Date: 17-7-29
  Time: 下午2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Dao.ValueObject.Book_comments" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.DaoFactory.DaoFactory" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Dao.ValueObject.User" %>
<%@ page import="Dao.IDao.Book_commentsDao" %>
<%@ page import="Dao.ValueObject.Book" %>

<%@ page import="Dao.DaoObject.BookDaoImpl"%>
<%@ page import="Dao.IDao.BookDao" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-style-bar" content="black" />
    <meta name="format-detection" content="telephone=no,address=no" />
    <meta name="x5-text-size-adjust" content="enable" />
    <title>图书详情</title>
    <link rel="stylesheet" href="css/index.css">
</head>

<style>
    #footer {
        background-color:white;
        color:black;
        text-align:center;
        padding:5px;
    }
</style>

<style type="text/css">

    body{
        padding: 0px;
        margin: 0px;
        /*font-size: 12px;*/
        font-family: "微软雅黑";
    }

    .link{text-align: right;line-height: 20px;padding-right: 40px;}

    .ui-dialog{
        width: 380px;height: auto;display: none;
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
    .ui-dialog-input-username{
        background: url(img/input_username.png) no-repeat 2px ;
    }

    .ui-dialog-input-password{
        background: url(img/input_password.png) no-repeat 2px ;
    }
    .ui-dialog-submit{
        width: 100%;height: 50px;background: #3b7ae3;border:none;font-size: 16px;color: #fff;
        outline: none;text-decoration: none;
        display: block;text-align: center;line-height: 50px;
    }
    .ui-dialog-submit:hover{
        background: #3f81b0;
    }

    .ui-mask{
        width: 100%;注销height:100%;background: #000;
        position: absolute;top: 0px;height: 0px;z-index: 8000;
        opacity:0.4; filter: Alpha(opacity=40);
    }
</style>

<body>
<div class="main">
    <div class="nav">
        <div class="nav_left">
            <img src="img/1.jpg">
            <span>XiyouLinuxGroup<br>BookManagerSystem</span>
        </div>

        <div class="nav_right">
            <br>
            <br>
            <div>
                    <a href = "main.jsp">返回首页</a>
            </div>
        </div>
    </div>

    <div id = "contents" style="width: 800px;height: 250px">
        <div id="left" style="float: left; width: 50%; height: 40%;">
            <img src="img/kernel.jpg" width="300" height="220">
        </div>

        <form action="/addComments.do" method="post">

        </form>
        <div id = "right" style="float: left; width: 50%; height: 40%;">

            <%
//                String bookid = new String(request.getParameter("bookid").getBytes("iso-8859-1"),"utf-8");
//                System.out.println(bookid);
                String bookid = "4";
                List<Book_comments> commentss = new ArrayList<>();
                List<User> users = new ArrayList<>();
                commentss = DaoFactory.getBook_commentsDaoInstance().queryByBook_id(Integer.parseInt(bookid));
                users = DaoFactory.getUserDaoInstance().queryByBookCommentsBookId(Integer.parseInt(bookid));
                Book book = null;
                book = DaoFactory.getBookDaoInstance().queryById(Integer.parseInt(bookid));
            %>
            书名：
            <%=book.getName()%>
            <br><br>
            作者：
            <%=book.getAuthor()%>
            <br><br>
            归属者：
            <%=book.getOwner()%>
            <br><br>
            被借
            <%=book.getBorrow_num()%>
            次                                                                                                                                                                                                                                                                                                                                                                     </次>
        </div>

    </div>
    <fo id = "pinglun" style="width: 300px;height: 200px">
        <div>
            评论：

            <%
                List<Book_comments> book_commentss = new ArrayList<Book_comments>();
                List<User> users1 = new ArrayList<User>();
                List<String> comments = new ArrayList<String>();
                String comment = null;
                users1 = DaoFactory.getUserDaoInstance().queryByBookCommentsBookId(Integer.parseInt(bookid));
                book_commentss = DaoFactory.getBook_commentsDaoInstance().queryByBook_id(Integer.parseInt(bookid));
                for(int i = 0; i < users1.size(); i++) {
                    comment = book_commentss.get(i).getComment_datetime() + " 　 " + users1.get(i).getName() + ":　 " + book_commentss.get(i).getDetail();
                    comments.add(comment);
                }

            %>

            <marquee behavior = "scroll" direction = "up" behavior = "scroll" height="auto" scrolldelay=1500 scrollamount=10 >
                <%

                    for(int i = 0; i < users1.size(); i++){
                %>
                    <%=comments.get(i)%><br>
                <%
                    }
                %>
            </marquee>
            <br><br><br>
            <center>
                <form action="javascript:showDialog();" method="post">
                    <input type="submit" value="我要评论"></input>
                </form>
            </center>
        </div>
    </fo>
</div>

<div class="ui-mask" id="mask" onselectstart="return false"></div>

<div class="ui-dialog" id="dialogMove" onselectstart='return false;'>
    <div class="ui-dialog-title" id="dialogDrag"  onselectstart="return false;" >
        我要评论
        <a class="ui-dialog-closebutton" href="javascript:hideDialog();"></a>

    </div>
    <div class="ui-dialog-content">
        <form action="/addComments.do" method="post">
            <div class="ui-dialog-l40 ui-dialog-pt15">
                <center>
                    <textarea cols="50" rows="5" style="width:300px;" name="detail" placeholder="想说点什么?"></textarea>
                </center>
            </div>

            <br>
            <br>
            <br>
            <br>
            <div>
                <input class="ui-dialog-submit" type="submit" name="login" value="提交评论" />
            </div>
        </form>
    </div>
</div>

<br>

<div id = "footer">
    <p>
        Copyright @ 2006-2017 西邮Linux兴趣小组 <br>
        All Rights Reserved
    </p>

</div>
</body>

<script type="text/javascript">

    var dialogInstace , onMoveStartId , mousePos = {x:0,y:0};	//	用于记录当前可拖拽的对象

    //	获取元素对象
    function g(id){return document.getElementById(id);}

    //	自动居中元素（el = Element）
    function autoCenter( el ){
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
    function Dialog( dragId , moveId ){
        var instace = {} ;

        instace.dragElement  = g(dragId);	//	允许执行 拖拽操作 的元素
        instace.moveElement  = g(moveId);	//	拖拽操作时，移动的元素
        stace = instace;
        instace.mouseOffsetLeft = 0;			//	拖拽操作时，移动元素的起始 X 点
        instace.mouseOffsetTop = 0;			//	拖拽操作时，移动元素的起始 Y 点

        instace.dragElement.addEventListener('mousedown',function(e){
            var e = e || window.event;
            dialogInstace = instace;
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

    //	重新调整对话框的位置和遮罩，并且展现
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

    //默认设置弹出层启动
    //showDialog();

</script>

</html>