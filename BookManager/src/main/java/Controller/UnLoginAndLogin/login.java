package Controller.UnLoginAndLogin;

import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.User;
import Model.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dela on 7/24/17.
 */

@WebServlet("/login.do")
public class login extends HttpServlet {
    private final String SUCCESS_VIEW = "main.jsp";
    private final String FAIL_VIEW = "index.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = new String(req.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        String password = new String(req.getParameter("password").getBytes("iso-8859-1"), "utf-8");
        password = MD5.useMD5(password);
        User user = DaoFactory.getUserDaoInstance().checkPassword(username);
        if(user != null){
            String getPassword = user.getPassword();
            if(getPassword.equals(password)){
                req.getSession().setAttribute("username", username);
                req.getRequestDispatcher(SUCCESS_VIEW).forward(req, resp);
            }else{
                resp.sendRedirect("index.jsp?error=yes");
            }
        }else{
            resp.sendRedirect("index.jsp?error=yes");
        }
    }
}
