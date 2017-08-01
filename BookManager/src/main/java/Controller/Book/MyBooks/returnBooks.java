package Controller.Book.MyBooks;

import Dao.DaoFactory.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dela on 7/28/17.
 */

@WebServlet("/returnBook.do")
public class returnBooks extends HttpServlet {
    //归还图书：先给当前这本书amount+1，然后在borrow_info里删除这条借书信息
    private final String MYBOOKS_VIEW = "myBooks.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = new String(req.getParameter("bookId").getBytes("iso-8859-1"), "utf-8");
        System.out.println(bookId);
        String username = (String) req.getSession().getAttribute("username");
        DaoFactory.getBookDaoInstance().addBookAmount(Integer.parseInt(bookId));
        DaoFactory.getBorrow_infoDaoInstance().deleteBookByIdAndUsername(Integer.parseInt(bookId), username);
        req.getRequestDispatcher(MYBOOKS_VIEW).forward(req, resp);
    }
}
