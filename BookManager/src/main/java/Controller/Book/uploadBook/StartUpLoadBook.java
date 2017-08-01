package Controller.Book.uploadBook;

import Dao.DaoFactory.DaoFactory;
import Dao.DaoObject.BookDaoImpl;
import Dao.DaoObject.Book_classDaoImpl;
import Dao.DaoObject.Book_class_relationDaoImpl;
import Dao.ValueObject.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hg_yi on 17-7-26.
 */

@MultipartConfig
//@WebServlet("/uploadBook.do")
public class StartUpLoadBook extends HttpServlet {
    private final String SUCCESS_PAGE = "successpage.jsp";
    private final String FAILD_PAGE = "faild.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        //拿到表单里面所有的参数值
        String bookName = new String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8");
        String author = new String(request.getParameter("author").getBytes("iso-8859-1"), "utf-8");
        String owner = (String) request.getSession().getAttribute("username");
//        String owner = new String(request.getParameter("owner").getBytes("iso-8859-1"), "utf-8");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String upload_date = Date.getCurrentTime();
        String describe = new String(request.getParameter("describe").getBytes("iso-8859-1"), "utf-8");
        String label = new String(request.getParameter("label").getBytes("iso-8859-1"), "utf-8");

        Part part = request.getPart("photo");
        String fileName = getFileName(part);
        writeTo(fileName, part);

        //将标签分割，得到一个String数组
        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setOwner(owner);
        book.setAmount(amount);
        book.setUpload_date(upload_date);
        book.setDescribe(describe);
        book.setBorrow_num(0);

        //将书本信息添加到book_info数据库中
        BookDaoImpl bookDao = (BookDaoImpl) DaoFactory.getBookDaoInstance();
        bookDao.insert(book);

        //根据用户输入的标签名在book_class中找到对应的标签id
        Book_classDaoImpl book_classDao = (Book_classDaoImpl) DaoFactory.
                getBook_classDaoInstance();
        int labelId = book_classDao.queryIdByName(label);

        //得到刚才插入book_info数据库中的书本id
        int bookId = bookDao.queryBookIdByBookNameAndOwner(bookName, owner);
        //将bookId和labelId插入至book_class_relation数据库中
        Book_class_relationDaoImpl book_class_relationDao = (Book_class_relationDaoImpl)
                DaoFactory.getBook_class_relationDAOInstance();
        book_class_relationDao.insert(labelId, bookId);

        request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
    }

    private String getFileName(Part part){
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return fileName;
    }

    private void writeTo(String fileName, Part part)
            throws IOException, FileNotFoundException {
        InputStream in = part.getInputStream();
        FileOutputStream out = new FileOutputStream("/home/dela/test/ServletTest/" + fileName);
        byte[] buffer = new byte[1024];
        int length = -1;

        while ((length = in.read(buffer)) != -1){
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }

}
