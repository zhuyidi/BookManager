<%@ page import="java.util.List" %>
<%@ page import="Dao.ValueObject.Book_class" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.DaoFactory.DaoFactory" %>
<%@ page import="Dao.ValueObject.Book" %><%--
  Created by IntelliJ IDEA.
  User: dela
  Date: 7/28/17
  Time: 2:40 PM
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
        /* ----表单控件CSS开始---- ↓ */
        .WellForm *{margin:0;padding:0;}
        .WellForm{font-size:12px;font-family:arial;line-height:21px;}
        .WellForm pre{float:left;margin-right:10px;}
        /*background*/
        .WellForm .TextL, .WellForm .TextR, .WellForm .WellText, .WellForm .SelectL, .WellForm .SelectR, .WellForm .WellSelect, .WellForm .WellTextArea .T, .WellForm .WellTextArea .T .TL, .WellForm .WellTextArea .T .TM, .WellForm .WellTextArea .B, .WellForm .WellTextArea .B .BL, .WellForm .WellTextArea .B .BM, .WellForm .WellRadio, .WellForm .WellCheckBox, .WellForm .ButtonL, .WellForm .ButtonR, .WellForm .WellButton{background: no-repeat;}
        /*WellText*/
        .WellForm .TextL, .WellForm .TextR, .WellForm .WellText{float:left;width:5px;height:21px;}
        .WellForm .TextL{background-position:0 0;}
        .WellForm .TextR{background-position:right 0;}
        .WellForm .TextLH{background-position:0 -21px;}
        .WellForm .TextRH{background-position:right -21px;}
        .WellForm .WellText{border:0;width:auto;height:17px;padding:2px 0;padding:3px 0 1px\9;*padding:3px 0 1px;font-family:arial;background-repeat:repeat-x;background-position:0 -42px;}
        .WellForm .WellTextH{background-position:0 -63px;}
        /*WellSelect*/
        .WellForm .SelectL, .WellForm .SelectR, .WellForm .WellSelect{float:left;height:21px;}
        .WellForm .SelectL{width:3px;background-position:0 -84px;}
        .WellForm .SelectR{width:16px;cursor:pointer;background-position:right -84px;margin-right:5px;}
        .WellForm .WellSelect{position:relative;cursor:pointer;background-repeat:repeat-x;background-position:0 -105px;}
        .WellForm .WellSelect select{display:none;}
        .WellForm .WellSelect em{position:absolute;top:0;left:3px;color:#fff;height:21px;display:block;line-height:21px;font-style:normal;}
        .WellForm .WellSelect ul{list-style-type:none;position:absolute;top:18px;left:0;z-index:1000;display:none;background:#6C6D70;}
        .WellForm .WellSelect ul li{color:#fff;height:20px;cursor:pointer;line-height:20px;padding-left:3px;}
        .WellForm .WellSelect ul li.hover{background:#333;}
        /*WellTextArea*/
        .WellForm .WellTextArea{float:left;}
        .WellForm .WellTextArea .T, .WellForm .WellTextArea .T .TL, .WellForm .WellTextArea .T .TM, .WellForm .WellTextArea .B, .WellForm .WellTextArea .B .BL, .WellForm .WellTextArea .B .BM{height:5px;overflow:hidden;}
        .WellForm .WellTextArea .T{float:left;width:100%;background-position:right -126px;}
        .WellForm .WellTextArea .TH{float:left;width:100%;background-position:right -131px;}
        .WellForm .WellTextArea .T .TL{background-position:0 -126px;}
        .WellForm .WellTextArea .TH .TL{background-position:0 -131px;}
        .WellForm .WellTextArea .T .TM{margin:0 5px;background-repeat:repeat-x;background-position:0 -136px;}
        .WellForm .WellTextArea .TH .TM{margin:0 5px;background-repeat:repeat-x;background-position:0 -141px;}
        .WellForm .WellTextArea .B{float:left;width:100%;background-position:right -146px;}
        .WellForm .WellTextArea .BH{float:left;width:100%;background-position:right -151px;}
        .WellForm .WellTextArea .B .BL{background-position:0 -146px;}
        .WellForm .WellTextArea .BH .BL{background-position:0 -151px;}
        .WellForm .WellTextArea .B .BM{margin:0 5px;background-repeat:repeat-x;background-position:0 -156px;}
        .WellForm .WellTextArea .BH .BM{margin:0 5px;background-repeat:repeat-x;background-position:0 -161px;}
        .WellForm .WellTextArea .M, .WellForm .WellTextArea .M .MR{float:left;background: repeat-y;}
        .WellForm .WellTextArea .M{background-position:0 0;}
        .WellForm .WellTextArea .M .MR{background-position:right 0;}
        .WellForm .WellTextArea .MH, .WellForm .WellTextArea .MH .MR{float:left;background:repeat-y;}
        .WellForm .WellTextArea .MH{background-position:0 0;}
        .WellForm .WellTextArea .MH .MR{background-position:right 0;}
        .WellForm .WellTextArea textarea{float:left;border:0;margin:0 5px;overflow:auto;font-family:arial;font-size:12px;resize:none;}
        /*WellRadio*/
        .WellForm .WellRadio{float:left;width:13px;height:13px;cursor:pointer;overflow:hidden;margin:4px 5px 0 0;background-position:-15px -270px;}
        .WellForm .WellRadioH{background-position:-15px -284px;}
        .WellForm .WellRadio input{margin-top:13px;display:none;}
        /*WellCheckBox*/
        .WellForm .WellCheckBox{float:left;width:12px;height:12px;cursor:pointer;overflow:hidden;margin:4px 5px 0 0;background-position:0 -270px;}
        .WellForm .WellCheckBoxH{background-position:0 -283px;}
        .WellForm .WellCheckBox input{margin-top:12px;display:none;}
        /*WellButton*/
        .WellForm .ButtonL, .WellForm .ButtonR, .WellForm .WellButton{cursor:pointer;float:left;width:5px;height:26px;}
        .WellForm .ButtonL{background-position:0 -166px;}
        .WellForm .ButtonR{background-position:right -166px;margin-right:10px;}
        .WellForm .ButtonLH{background-position:0 -192px;}
        .WellForm .ButtonRH{background-position:right -192px;}
        .WellForm .WellButton{border:0;width:auto;font-size:12px;color:#fff!important;height:26px;padding:0 10px 3px;*padding-bottom:0;padding-bottom:0\9;font-family:arial;background-repeat:repeat-x;background-position:0 -218px;}
        .WellForm .WellButtonH{background-position:0 -244px;}
        /* ----表单控件CSS结束---- ↑ */

        /* form */
        form{float:left;padding:20px;border:2px dashed #ccc;margin:20px 0 0 40px;width:380px;}
        form .item{float:left;clear:both;width:100%;margin-bottom:10px;}
        .WellForm label{float:left;width:4em;height:21px;text-align:right;}
    </style>

    <body>
    <div class="main" style="width: 560px; height: 900px">

        <div class="nav">
            <div class="nav_left">
                <img src="img/1.jpg">
                <span>XiyouLinuxGroup<br>BookManagerSystem</span>
            </div>
            <%--<div class="nav_right">--%>
                <%--<p>登录</p>--%>
                <%--<div>--%>
                    <%--<input type="text"></input>--%>
                    <%--<button>搜索</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>

        <div style="float: left">
        <p>
            和书籍生活在一起，永远不会叹气
        </p><br>
        </div>
        <br>
        <br>


        <h2 >上传图书信息</h2>
        <form action="uploadBook.do" method="post" enctype="multipart/form-data">
            <div class="item">
                <label>书名：</label>
                <input type="text" name="bookname" placeholder="书名是什么" />
            </div>
            <div class="item">
                <label>作者：</label>
                <input type="text" name="author" placeholder="谁写的呀" />
            </div>

            <div class="item">
                <label>数量：</label>
                <input type="text" name="amount" placeholder="你要上传几本书"/>
            </div>
            <div class="item">
                <label>描述：</label>
                <textarea cols="50" rows="5" style="width:300px;" name="describe" placeholder="一句话描述它"></textarea>
            </div>
            <div class="item">
                <label>请上传的图片：</label>
                <input type="file" name="photo"><br>
            </div>
            <div id="addBook" class="upload">
                <hr>
            <div class="item">
                <label>一级分类标签：</label>
                <br>
                <br>
                <%
                    List<Book_class> book_classes = new ArrayList<Book_class>();
                    Book_class book_class = null;
                    book_classes = DaoFactory.getBook_classDaoInstance().getBookClasses(0);
                    book_class = book_classes.get(0);
                %>
                <input class="on" type="radio" name="one" value="<%=book_class.getName()%>"><%=book_class.getName()%>
                <%
                    for(int i = 1; i < book_classes.size(); i++){
                        book_class = book_classes.get(i);
                        if(i % 3 == 0){
                %>
                        <br>
                <%
                        }
                %>
                <input class="on" type="radio" name="one" value="<%=book_class.getName()%>"><%=book_class.getName()%></input>
                <%
                    }
                %>

            </div>
                <hr>
                <br>
            <div class="item">
                <label>二级分类标签：</label>
                <br>
                <br>
                <div class="two">
                <%
                    List<Book_class> twoBook_class = new ArrayList<Book_class>();
                    Book_class book_class1 = null;
                    book_class = book_classes.get(0);
                    twoBook_class = DaoFactory.getBook_classDaoInstance().getBookClasses(book_class.getId());
                    for(int i = 0; i < twoBook_class.size(); i++){
                        book_class1 = twoBook_class.get(i);
                %>
                    <input type="checkbox" name="two" value="<%=book_class1.getName()%>"><%=book_class1.getName()%></input>
                <%
                    }
                %>
            </div>
                <%
                    for(int i = 1; i < book_classes.size(); i++){
                        book_class = book_classes.get(i);
                    %>

            <div class="hide two">

                <%
                    twoBook_class = DaoFactory.getBook_classDaoInstance().getBookClasses(book_class.getId());
                    for(int j = 0; j < twoBook_class.size(); j++){
                        book_class1 = twoBook_class.get(j);
                %>
                <input type="checkbox" name="two" value="<%=book_class1.getName()%>"><%=book_class1.getName()%></input>
                <%
                    }
                %>
            </div>
                <%
                    }
                %>
                <br>
                <hr>
            </div>
            </div>

            <input type="submit" value="提交"></input>
        </form>
    </div>

    <br>
    <div id = "footer" style="text-align: center">
        <p>
            Copyright @ 2006-2017 西邮Linux兴趣小组 <br>
            All Rights Reserved
        </p>
    </div>
    <br>
    <br>


    </body>
    <script>
        window.onload=function(){
            var ocontent=document.getElementById("addBook");
            var oli=ocontent.getElementsByClassName("on");
            var odiv=ocontent.getElementsByClassName("two");
            for(var i=0;i<oli.length;i++){
                oli[i].index=i; //对当前li进行编号
                oli[i].onclick=function(){
                    for(var n=0;n<oli.length;n++){
//                        oli[n].className="";
                        odiv[n].className="hide two";
                    }
//                    this.className="on";
                    odiv[this.index].className="two";
                }
            }
        }

    </script>
</html>
