package Controller.Comments;

import Dao.DBConn.DBUtils;
import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.Book_comments;
import Model.DateToString;

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
import java.util.Date;

/**
 * Created by dongmengyuan on 17-7-28.
 */

@WebServlet("/addComments.do")
public class addComments extends HttpServlet {

//    private final String ADDCOMMENTS_VIEW = "addcomments.jsp";
    private final String ALLCOMMENTS_VIEW = "bookDetails.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String book_id = new String(request.getParameter("bookid").getBytes("iso-8859-1"), "utf-8");
        String username = (String) request.getSession().getAttribute("username");
        //uid要通过username在数据库中查询才能拿到
        int user_id = DaoFactory.getUserDaoInstance().queryIdByName(username);

        String detail = new String(request.getParameter("detail").getBytes("iso-8859-1"), "utf-8");
        //拿到detail后，判断detail是不是＝＝null, 如果等于null，就直接返回上一页，即图书详情页面
        //如果不等于null,就进行数据库插入操作，即调用下面的dao层，再返回上一页

        Date date = new Date();
        String comments_datetime = DateToString.getStringDate(date);

        if(detail == null) {
            request.getRequestDispatcher(ALLCOMMENTS_VIEW).forward(request,response);

        }
        else {
            Book_comments comments = new Book_comments();
            comments.setBook_id(Integer.parseInt(book_id));
            comments.setUser_id(user_id);
            comments.setDetail(detail);
            comments.setComment_datetime(comments_datetime);
            //增加评论
            DaoFactory.getBook_commentsDaoInstance().insert(comments);
            request.getRequestDispatcher(ALLCOMMENTS_VIEW).forward(request,response);
        }
    }
}