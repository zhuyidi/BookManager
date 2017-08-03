package Dao.IDao;

import Dao.ValueObject.Book;
import com.sun.javafx.scene.control.skin.LabeledImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dela on 7/20/17.
 */
public interface BookDao {
    //添加操作
    public void insert(Book book);
    //删除操作
    public void delete(String name) throws SQLException;
    //更改操作
    public void update(Book book);
    //查询操作
    //书名|作者|所属者模糊查询
    public List<Book> queryByNAO(String keyWords);

    //通过书本ID进行查询操作
    public Book queryById(int bookId);

    //查找所有的书籍
    public List<Book> queryAllBook();

    //查询一个一级标签下的所有书
    public List<Book> queryByFatherClassId(int fatherClassId);

    //查询一个用户所拥有的所有书
    public List<Book> queryByOwner(String owner);

    //查询一个用户所借的所有书
    public List<Book> queryMyBorrowBooks(String username);

    //通过ID删除一本书
    public void deleteById(int id);

    //书的数量+1
    public void addBookAmount(int id);

    //通过书本名与归属者查询书本唯一ID
    public int queryBookIdByBookNameAndOwner(String bookName, String owner);

    //通过书本Id将书本的借阅次数进行加一
    public void updateBorrowInfoById(int bookId);

    //通过书本Id将书本总数进行加一
    public void updateAmountById(int bookId);

    //得到本书所属图片的路径
    public String getFilePath(int bookId);
}
