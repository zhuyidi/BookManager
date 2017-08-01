<%@ page import="Dao.ValueObject.Book_class" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.IDao.Book_classDao" %>
<%@ page import="Dao.DaoFactory.DaoFactory" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Dao.ValueObject.Book" %>
<%@ page import="Controller.Book.borrow.FormatBorrowLog" %>
<!DOCTYPE html>
<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
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
        <title>图书馆</title>
        <link rel="stylesheet" href="/css/index.css">
    </head>

    <style>

        #footer {
            background-color:white;
            color:black;
            text-align:center;
            padding:5px;
        }


        #content_left {
            height:auto;
            width: 73%;
            margin:5px;
            float:left;
        }

        #content_right {
            height: auto;
            width: 23%;
            margin:5px;
            float:right;
            background:white;
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
                    <div>
                        <h5>
                        <p>
                            <a href="myBooks.jsp">我的书籍</a>
                            <a href="uploadBook.jsp">上传书籍</a>
                            <a href="index.jsp?logout=1">退出登录</a>
                        </p>
                        </h5>
                    </div>
                    <div>
                        <form action="/search.do?mark=0" method="post">
                            <input type="text" name="keyWords" placeholder="书名/作者/归属者"></input>
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
            <%--以上是页面的上半部分，也就是标题部分，下面应分为左右两个div--%>
                <div id = "content_left" >
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
                            <%--<button>我要借阅</button>--%>
                            <form action="bookDetails.jsp?bookid=<%=book.getId()%>" method="post">
                                <input type="submit" value="点我借阅"></input>
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
                            <%--<button>我要借阅</button>--%>
                            <form action="bookDetails.jsp?bookid=<%=book.getId()%>" method="post">
                                <input type="submit" value="点我借阅"></input>
                            </form>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
                </div>

                <div id = "content_right" >
                    <marquee behavior = "scroll" direction = "up" behavior = "scroll" height="auto" scrolldelay=500 scrollamount=10 >
                        <%
                            List<String> logs = FormatBorrowLog.getLog();
                            String temp = null;
                            for(int i = 0; i < logs.size(); i++){
                                temp = logs.get(i);
                        %>
                                <%=temp%><br>
                        <%
                            }
                        %>
                        <%--董恒毅从杜肖孟处借走C++ Primer Plus</br>--%>
                        <%--康艺杰从祝一迪处借走Java学习笔记 </br>--%>
                        <%--杜肖孟从董孟愿处借走计算机网络--%>
                    </marquee>
                </div>
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
</html> 
