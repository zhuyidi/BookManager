package Controller.UnLoginAndLogin;

import Dao.DBConn.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dela on 7/17/17.
 */

@WebServlet("/addbook.do")
public class addBook extends HttpServlet {

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String resultInfo = null;

    private static final String ADD_BOOK_VIEW = "addBook.jsp";
    private static final String OWNER_MAIN_VIEW = "ownerMain.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //tomcat的编码为iso-8895-1,表单中有中文时会乱码,所以要将编码转换成utf-8
        String name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String author = new String(req.getParameter("author").getBytes("iso-8859-1"), "utf-8");
        String owner = new String(req.getParameter("owner").getBytes("iso-8859-1"), "utf-8");
        String count = req.getParameter("count");
        int cou = Integer.parseInt(count);

        if ((name == null) || (author == null) || (owner == null) || cou <= 0) {
            resultInfo = "输入信息有误, 请核对后重新输入!";
            req.setAttribute("resultInfo", resultInfo);
            req.getRequestDispatcher(ADD_BOOK_VIEW).forward(req, resp);
        } else {
            resultInfo = "添加图书信息成功!";
            saveBookInfo(name, author, owner, cou);
            req.setAttribute("resultInfo", resultInfo);
            req.getRequestDispatcher(OWNER_MAIN_VIEW).forward(req, resp);
        }
    }

    private void saveBookInfo(String name, String author, String owner, int count) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("INSERT INFO bookInfo(name, author, owner, count)VALUE(?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setString(3, owner);
            statement.setInt(4, count);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}