<%--
  Created by IntelliJ IDEA.
  User: dela
  Date: 7/25/17
  Time: 5:39 PM
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
        <title>Not Found</title>
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

        <div>
            <h3>
                <p>
                    抱歉！没有找到相关图书信息哦！
                </p>
            </h3>
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
