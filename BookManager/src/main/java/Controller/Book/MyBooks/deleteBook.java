package Controller.Book.MyBooks;

import Dao.DaoFactory.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dela on 7/27/17.
 */

@WebServlet("/deleteBook.do")
public class deleteBook extends HttpServlet{
    private final String MYBOOKS_VIEW = "myBooks.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = new String(req.getParameter("bookId").getBytes("iso-8859-1"), "utf-8");
        DaoFactory.getBookDaoInstance().deleteById(Integer.parseInt(bookId));
        DaoFactory.getBorrow_infoDaoInstance().deleteBookById(Integer.parseInt(bookId));
        DaoFactory.getBook_class_relationDAOInstance().deleteByBookId(Integer.parseInt(bookId));
        req.getRequestDispatcher(MYBOOKS_VIEW).forward(req, resp);
    }
}
