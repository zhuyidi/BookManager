package Controller.Book.uploadBook;

import Dao.DaoFactory.DaoFactory;
import Dao.DaoObject.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by hg_yi on 17-7-26.
 */
@WebServlet("/uploadpicture.do")
public class UpLoadPicture extends HttpServlet {
    private final String UPLOADBOOK_PAGE = "upload.jsp";
    private final String SUCCESS_PAGE = "successpage.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        //这里应该拿到用户的姓名（uid），在一个用户进行登录的时候，我们应该将它的uid设置为会话属性，
        //直到关闭浏览器为止，姓名（uid）是不能消失的
        String bookName = new String(request.getParameter("bookname").
                getBytes("iso-8859-1"), "utf-8");
        String userName = new String(request.getParameter("username").
                getBytes("iso-8859-1"), "utf-8");

        //在数据库中查找book_id，因为书本id是唯一的
        BookDaoImpl bookDao = (BookDaoImpl) DaoFactory.getBookDaoInstance();
        int bookId = bookDao.queryBookIdByBookNameAndOwner(bookName, userName);
        if (bookId == -1) {
            //告诉用户照片上传失败，重新返回上传书籍页面
            request.getRequestDispatcher(UPLOADBOOK_PAGE).forward(request, response);
        } else {
            request.setCharacterEncoding("utf-8");
            //以bookId为文件夹名，里面存放书籍照片
            String path = createDir(bookId);
            Part part = request.getPart("picture");
            String filename = getFileName(part);
            saveTo(filename, path, part);
            request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
        }
    }

    private String createDir(int bookId) {
        String pathname = "/home/hg_yi/Web项目/BookManager_Picture/" + bookId;
        File file = new File(pathname);

        if (!file.exists()) {
            file.mkdir();
            return file.getPath();
        } else {
            return file.getPath();
        }
    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"")+10,
                header.lastIndexOf("\""));

        return fileName;
    }

    private void saveTo(String filename, String path, Part part) throws IOException {
        InputStream inputStream = part.getInputStream();
        OutputStream outputStream = new FileOutputStream(path + filename);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        inputStream.close();
        outputStream.close();
    }

}
