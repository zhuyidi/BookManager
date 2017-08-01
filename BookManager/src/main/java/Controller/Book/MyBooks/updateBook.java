package Controller.Book.MyBooks;

import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.Book;
import Model.DateToString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by dela on 7/26/17.
 */

@WebServlet("/updateBook.do")
public class updateBook extends HttpServlet{
    private final String MYBOOKS_VIEW = "myBooks.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        Date date = new Date();
        String bookId = new String(req.getParameter("bookId").getBytes("iso-8859-1"), "utf-8");
        System.out.println(bookId);
        String name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String author = new String(req.getParameter("author").getBytes("iso-8859-1"), "utf-8");
        String count = new String(req.getParameter("count").getBytes("iso-8859-1"), "utf-8");
        String desc = new String(req.getParameter("desc").getBytes("iso-8859-1"), "utf-8");
        String curDate = DateToString.getStringDate(date);
        System.out.println(curDate);

        book = DaoFactory.getBookDaoInstance().queryById(Integer.parseInt(bookId));
        book.setName(name);
        book.setAuthor(author);
        book.setAmount(Integer.parseInt(count));
        book.setDescribe(desc);
        book.setUpload_date(curDate);

        DaoFactory.getBookDaoInstance().update(book);
        req.getRequestDispatcher(MYBOOKS_VIEW).forward(req, resp);
    }
}
