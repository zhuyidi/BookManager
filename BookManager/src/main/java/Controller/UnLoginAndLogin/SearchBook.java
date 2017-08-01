package Controller.UnLoginAndLogin;

import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.Book;
import Model.escapeHtml;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dela on 7/17/17.
 */

@WebServlet("/search.do")
public class SearchBook extends HttpServlet {
    private final String SEARCH_RESULT_VIEW = "searchResult.jsp";
    private final String NOT_FOUND = "notFound.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyWords = new String(req.getParameter("keyWords").getBytes("iso-8859-1"), "utf-8");
        String mark = new String(req.getParameter("mark").getBytes("iso-8859-1"), "utf-8");
//        System.out.println(mark);
        req.setAttribute("mark", mark);
        //        System.out.printf("$$$%s\n", keyWords);
//        keyWords = escapeHtml.escape(keyWords);
//        System.out.printf("###%s\n", keyWords);
        List<Book> books = new ArrayList<Book>();
        if(mark != null){
            if(mark == "1"){
                books = DaoFactory.getBook_class_relationDAOInstance().queryByClass(Integer.parseInt(keyWords));
            }else{
                books = DaoFactory.getBookDaoInstance().queryByNAO(keyWords);
            }
        }else{
            books = DaoFactory.getBookDaoInstance().queryByNAO(keyWords);
        }

        if(keyWords == null){
            req.getRequestDispatcher(NOT_FOUND).forward(req, resp);
        }

        if(books.size() == 0){
            req.getRequestDispatcher(NOT_FOUND).forward(req, resp);
        }else {
            req.setAttribute("keyWords", keyWords);
            req.getRequestDispatcher(SEARCH_RESULT_VIEW).forward(req, resp);
        }
    }
}
