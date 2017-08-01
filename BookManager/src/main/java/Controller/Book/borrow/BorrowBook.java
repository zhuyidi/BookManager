package Controller.Book.borrow;

import Dao.DaoFactory.DaoFactory;
import Dao.IDao.BookDao;
import Dao.IDao.Borrow_infoDao;
import Dao.ValueObject.Borrow_info;
import Model.DateToString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hg_yi on 17-7-28.
 */

@WebServlet("/borrowbook.do")
public class BorrowBook extends HttpServlet {
    private final String BOOKDETAILPAGE = "bookDetails.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String bookId = request.getParameter("bookid");
        String username = (String) (request.getSession().getAttribute("username"));
        String currentDate = DateToString.getStringDate(new Date());

        //在cs_user里根据用户名得到用户id
        int userId = DaoFactory.getUserDaoInstance().queryIdByName(username);

        Borrow_infoDao borrowInfoDao = DaoFactory.getBorrow_infoDaoInstance();
        BookDao bookDao = DaoFactory.getBookDaoInstance();

        Borrow_info borrowInfo = new Borrow_info();
        borrowInfo.setBook_id(Integer.parseInt(bookId));
        borrowInfo.setUser_id(userId);
        borrowInfo.setBorrow_date(currentDate);
        borrowInfo.setFlag(1);

        //将借阅信息插入至数据库中
        borrowInfoDao.insert(borrowInfo);

        //将book_info中的borrow_num进行加一并将书本总数进行减一
        bookDao.updateBorrowInfoById(Integer.parseInt(bookId));

        request.setAttribute("mark", "1");
        request.getRequestDispatcher(BOOKDETAILPAGE).forward(request, response);
    }
}
