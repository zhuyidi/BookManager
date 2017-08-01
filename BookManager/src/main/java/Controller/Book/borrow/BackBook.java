package Controller.Book.borrow;

import Dao.DaoFactory.DaoFactory;
import Dao.IDao.BookDao;
import Dao.IDao.Borrow_infoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hg_yi on 17-7-28.
 */
@WebServlet("/backbook.do")
public class BackBook extends HttpServlet {
    private final String MYBOOKPAGE = "myBooks.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String bookId = new String(request.getParameter("bookid").
                getBytes("iso-8859-1"), "utf-8");
        String username = (String) request.getSession().getAttribute("username");

        //在数据库中将用户借阅的flag字段设置为0
        Borrow_infoDao borrowInfoDao = DaoFactory.getBorrow_infoDaoInstance();
        //在cs_user中由用户名查找到用户id
        int userId = DaoFactory.getUserDaoInstance().queryIdByName(username);

        borrowInfoDao.updateByBookIdAndBorrowUser(Integer.parseInt(bookId), userId);

        //归还之后将书本中的总数进行加一
        BookDao bookDao = DaoFactory.getBookDaoInstance();
        bookDao.updateAmountById(Integer.parseInt(bookId));

        request.getRequestDispatcher(MYBOOKPAGE).forward(request, response);
    }
}
