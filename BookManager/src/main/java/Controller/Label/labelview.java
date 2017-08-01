package Controller.Label;

import Dao.DaoFactory.DaoFactory;
import Dao.DaoObject.Book_class_relationDaoImpl;
import Dao.ValueObject.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hg_yi on 17-7-24.
 */
@WebServlet("/label_view.do")
public class labelview extends HttpServlet {
    private final String SEARCH_RESULT_VIEW = "searchResult.jsp";
    private final String NOT_FOUND = "notFound.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyWords = new String(request.getParameter("keyWords").getBytes("iso-8859-1"), "utf-8");

        System.out.println("***");
        System.out.println(keyWords);
        List<Book> books = new ArrayList<Book>();

        System.out.println("连接数据库之前");
        Book_class_relationDaoImpl relationDao= (Book_class_relationDaoImpl) DaoFactory.getBook_class_relationDAOInstance();
        books = relationDao.queryByClass(Integer.parseInt(keyWords));
        System.out.println("连接数据库之后");
        if(books.size() == 0){
            request.getRequestDispatcher(NOT_FOUND).forward(request, response);
        }else {
            System.out.println("++++++++++++++++++++++++++++++++");
            request.setAttribute("keyWords", keyWords);
            request.getRequestDispatcher(SEARCH_RESULT_VIEW).forward(request, response);
        }
    }
}
