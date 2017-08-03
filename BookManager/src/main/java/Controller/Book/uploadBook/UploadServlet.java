package Controller.Book.uploadBook;

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
 * Created by dela on 3/5/17.
 */

@MultipartConfig
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("1");
        Part part = req.getPart("photo");
        System.out.println("2");
        String fileName = getFileName(part);
        System.out.println("3");
        writeTo(fileName, part);
        System.out.println("4");
    }
}
