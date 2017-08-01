<%@ page import="Dao.ValueObject.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.DaoFactory.DaoFactory" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: dela
  Date: 7/24/17
  Time: 3:12 PM
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
        <meta name="x5-text-size-adjust" content="enable" />
        <title>查询结果</title>
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
                    <a href="main.jsp">返回首页</a>
                    <form action="/search.do?mark=0" method="post">
                        <input type="text" name="keyWords" placeholder="书名/作者/归属者"></input>
                        <input type="submit" value="搜索">
                    </form>
                </div>
            </div>
        </div>

        <div id="content" class="contents">
            <br>
            <br>
            <%
            String keyWords = new String(request.getParameter("keyWords").getBytes("iso-8859-1"), "utf-8");
            List<Book> books = new ArrayList<Book>();
            Book book = null;
            String mark = new String(request.getParameter("mark").getBytes("iso-8859-1"), "utf-8");
//            System.out.println("searchJSP里");
//            System.out.println(mark);
            if(mark.equals("1")){
                books = DaoFactory.getBook_class_relationDAOInstance().queryByClass(Integer.parseInt(keyWords));
            }else{
                books = DaoFactory.getBookDaoInstance().queryByNAO(keyWords);
            }
//            System.out.println("查询结果有几条");
//            System.out.println(books.size());
            Iterator<Book> bookIterator = books.iterator();
            while (bookIterator.hasNext()){
                book = bookIterator.next();
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
                <form action="bookDetails.jsp?bookid=<%=book.getId()%>" method="post">
                    <input type="submit" value="我要借阅"></input>
                </form>
            </div>
            <%
                }
            %>
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
</html>
