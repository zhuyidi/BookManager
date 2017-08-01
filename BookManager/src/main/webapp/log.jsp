<%--
  Created by IntelliJ IDEA.
  User: dela
  Date: 7/25/17
  Time: 8:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body style="margin-bottom: 150px;">
    <ul id="logs">
        <li>This is log 1</li>
        <li>This is log 2</li>
        <li>This is log 3</li>
        <li>This is log 4</li>
        <li>This is log 5</li>
        <li>This is log 6</li>
        <li>This is log 7</li>
        <li>This is log 8</li>
    </ul>
    <div>
    <marquee behavior = "scroll" direction = "up" behavior = "scroll">
        ·12·12·12 </br>
        12312312 </br>
        123123123
    </marquee>
    </div>
    <script src="jquery.js"></script>
    <script>

        var i = 9;
        var $logs = $('#logs');

        setInterval(
            function () {
                var $el = $('<li>This is log ' + (i++) + '</li>');
                $el.appendTo($logs);
                $('body').animate({scrollTop: $el.offset().top});
            },
            1000
        )

    </script>
    </body>
</html>

