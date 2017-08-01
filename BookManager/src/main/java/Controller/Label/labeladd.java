package Controller.Label;

import Dao.DaoFactory.DaoFactory;
import Dao.DaoObject.Book_classDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hg_yi on 17-7-24.
 */
@WebServlet("/labeladd.do")
public class labeladd extends HttpServlet {
    private final String RESULT_PAGE = "allBook_class.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String firstLabel = new String(request.getParameter("firstlabel").getBytes(
                "iso-8859-1"), "utf-8");
        String leafLabel = new String(request.getParameter("leaflabel").getBytes(
                "iso-8859-1"), "utf-8");
        Book_classDaoImpl book_classDao = (Book_classDaoImpl) DaoFactory.getBook_classDaoInstance();

        //阻止进行非法输入
        if (firstLabel.equals("") || leafLabel.equals("")) {
            request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
        } else {
            //检查父子标签是否已经存在
            int firstId = book_classDao.queryIdByName(firstLabel);
            int leafId = book_classDao.queryIdByName(leafLabel);

            if (firstId != -1 || leafId != -1) {
                request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
            } else {
                book_classDao.insertClass(firstLabel, 0);
                int parentId = book_classDao.queryIdByName(firstLabel);
                book_classDao.insertClass(leafLabel, parentId);
                request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
            }
        }
    }
}
