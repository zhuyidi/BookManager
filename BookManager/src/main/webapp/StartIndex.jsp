<%@ page import="Dao.ValueObject.Book_class" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.IDao.Book_classDao" %>
<%@ page import="Dao.DaoFactory.DaoFactory" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Dao.ValueObject.Book" %>
<%@ page import="java.util.Enumeration" %>
<%--
  Created by IntelliJ IDEA.
  User: dela
  Date: 7/24/17
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
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
        <title>图书馆</title>
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
            width: 100%;height:100%;background: #000;
            position: absolute;top: 0px;height: 0px;z-index: 8000;
            opacity:0.4; filter: Alpha(opacity=40);
        }
    </style>


    <body onload="goPage(1, 10);">

    <%
//        String logout = "0";
//        String logout = new String(request.getParameter("logout").getBytes("iso-8859-1"), "utf-8");
//        if(logout != null){
//            if(logout.equals("1")){
//                request.getSession().invalidate();
//            }
//        }
    %>

        <div class="main">
            <div class="nav">
                <div class="nav_left">
                    <img src="img/1.jpg">
                    <span>XiyouLinuxGroup<br>BookManagerSystem</span>
                </div>

                <div class="nav_right">
                    <div class="link">
                        <a href="javascript:showDialog();">登录</a>
                    </div>
                    <div>
                        <form action="/search.do?mark=0" method="post">
                            <input type="text" name="keyWords" placeholder="书名/作者/归属者" ></input>
                            <input type="submit" value="搜索">
                        </form>
                    </div>
                </div>
            </div>

            <div id="content" class="contents">
                <ul>
                    <%
                        List<Book_class> book_classes = new ArrayList<Book_class>();
                        //通过Dao工厂获得工厂实现类实例
                        Book_classDao book_classDao = DaoFactory.getBook_classDaoInstance();
                        book_classes = book_classDao.getBookClasses(0);
                        Book_class book_class = new Book_class();
                        book_class = book_classes.get(0);
                    %>
                    <font size=2px>
                        <li class="on"><%=book_class.getName()%></li>

                        <%
                            for(int i = 1; i < book_classes.size(); i++){
                                book_class = book_classes.get(i);
                        %>

                        <li><%=book_class.getName()%></li>

                        <%
                            }
                        %>
                    </font>
                    <h4>
                        <p>
                            <br>
                            <a href="allBook_class.jsp" style="color: #535353">全部标签</a>
                        </p>
                    </h4>

                </ul>

                <br>
                <%
                    List<Book> books = new ArrayList<Book>();
                    Book_class bookClass = new Book_class();
                    Book book = null;
                    bookClass = book_classes.get(0);
                    books = DaoFactory.getBookDaoInstance().queryByFatherClassId(bookClass.getId());

                %>
                    <div class="bts">
                    <%
                      for(int i = 0; i < books.size(); i++){
                          book = books.get(i);
                    %>
                    <div class="books">
                        <p>
                            <img src="img/kernel.jpg">
                        </p>
                        <p><%=book.getName()%></p>
                        <p><%=book.getAuthor()%></p>
                        <p><%=book.getDescribe()%></p>
                        <p><%=book.getOwner()%></p>
                        <p>被借:<%=book.getBorrow_num()%>次</p>
                        <form action="javascript:showDialog();" method="post">
                            <input type="submit" value="我要借阅"></input>
                        </form>

                    </div>
                    <%
                        }
                    %>
                </div>

                <%
                    for(int i = 1; i < book_classes.size(); i++){
                        bookClass = book_classes.get(i);
                        books = DaoFactory.getBookDaoInstance().queryByFatherClassId(bookClass.getId());
                %>
                    <div class="hide bts">
                <%
                    for(int j = 0; j < books.size(); j++){
                        book = books.get(j);
                %>
                    <div class="books">
                        <p>
                            <img src="img/kernel.jpg">
                        </p>
                        <p><%=book.getName()%></p>
                        <p><%=book.getAuthor()%></p>
                        <p><%=book.getDescribe()%></p>
                        <p><%=book.getOwner()%></p>
                        <p>被借:<%=book.getBorrow_num()%>次</p>
                        <button>我要借阅</button>
                    </div>
                    <%
                            }
                    %>
                    </div>
                    <%
                        }
                    %>
            </div>
        </div>


        <div class="ui-mask" id="mask" onselectstart="return false"></div>

        <div class="ui-dialog" id="dialogMove" onselectstart='return false;'>
            <div class="ui-dialog-title" id="dialogDrag"  onselectstart="return false;" >

                登录通行证

                <a class="ui-dialog-closebutton" href="javascript:hideDialog();"></a>

            </div>
            <div class="ui-dialog-content">
                <form action="/login.do" method="post">
                    <div class="ui-dialog-l40 ui-dialog-pt15">
                        <input class="ui-dialog-input ui-dialog-input-username" type="text" placeholder="用户名" name="username"/>
                    </div>
                    <div class="ui-dialog-l40 ui-dialog-pt15">
                        <input class="ui-dialog-input ui-dialog-input-password" type="password" placeholder="密码" name="password" />
                    </div>
                    <%--<div class="ui-dialog-l40">--%>
                        <%--<a href="#">忘记密码</a>--%>
                    <%--</div>--%>

                    <br>
                    <br>
                    <br>
                    <br>
                    <div>
                        <%--<a class="ui-dialog-submit" href="#" >登录</a>--%>
                        <input class="ui-dialog-submit" type="submit" name="login" value="登录" />
                    </div>
                    <%--<div class="ui-dialog-l40">--%>
                        <%--<a href="#">立即注册</a>--%>
                    <%--</div>--%>
                </form>

            </div>
        </div>

        <br>
        <br>
        <br>
        <div id = "footer">
            <p>
                Copyright @ 2006-2017 西邮Linux兴趣小组 <br>
                All Rights Reserved
            </p>
        </div>
    </body>

    <script>
        window.onload=function(){
            var ocontent=document.getElementById("content");
            var oli=ocontent.getElementsByTagName("li");
            var odiv=ocontent.getElementsByClassName("bts");
            for(var i=0;i<oli.length;i++){
                oli[i].index=i; //对当前li进行编号
                oli[i].onclick=function(){
                    for(var n=0;n<oli.length;n++){
                        oli[n].className="";
                        odiv[n].className="hide bts";
                    }
                    this.className="on";
                    odiv[this.index].className="bts";
                }
            }
        }
    </script>

    <script type="text/javascript">

        var dialogInstace , onMoveStartId , mousePos = {x:0,y:0};	//	用于记录当前可拖拽的对象

        // var zIndex = 9000;

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
                // instace.moveElement.style.zIndex = zIndex ++;
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

        //取出传回来的参数error并与yes比较
        var errori ='<%=request.getParameter("error")%>';
        if(errori=='yes'){
            alert("登录失败！用户名或密码不正确！");
            showDialog();
        }



        function goPage(pno,psize){
            var itable = document.getElementsByClassName("bts");
            var num = itable.length;//表格所有行数(所有记录数)
            console.log(num);
            var totalPage = 0;//总页数
            var pageSize = psize;//每页显示行数
            //总共分几页
            if(num/pageSize > parseInt(num/pageSize)){
                totalPage=parseInt(num/pageSize)+1;
            }else{
                totalPage=parseInt(num/pageSize);
            }
            var currentPage = pno;//当前页数
            var startRow = (currentPage - 1) * pageSize+1;//开始显示的行  31
            var endRow = currentPage * pageSize;//结束显示的行   40
            endRow = (endRow > num)? num : endRow;    40
            console.log(endRow);
            //遍历显示数据实现分页
            for(var i=1;i<(num+1);i++){
                var irow = itable.rows[i-1];
                if(i>=startRow && i<=endRow){
                    irow.style.display = "block";
                }else{
                    irow.style.display = "none";
                }
            }
            var pageEnd = document.getElementById("pageEnd");
            var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
            if(currentPage>1){
                tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+","+psize+")\">首页</a>";
                tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage-1)+","+psize+")\"><上一页</a>"
            }else{
                tempStr += "首页";
                tempStr += "<上一页";
            }

            if(currentPage<totalPage){
                tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage+1)+","+psize+")\">下一页></a>";
                tempStr += "<a href=\"#\" onClick=\"goPage("+(totalPage)+","+psize+")\">尾页</a>";
            }else{
                tempStr += "下一页>";
                tempStr += "尾页";
            }

            document.getElementById("barcon").innerHTML = tempStr;

        }



    </script>
</html>
