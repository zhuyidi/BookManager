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
    public Book queryById(int bookId);
    public List<Book> queryAllBook();
    public List<Book> queryByFatherClassId(int fatherClassId);
    public List<Book> queryByOwner(String owner);
    public List<Book> queryMyBorrowBooks(String username);
    public void deleteById(int id);
    public void addBookAmount(int id);
    //通过书本名与归属者查询书本唯一ID
    public int queryBookIdByBookNameAndOwner(String bookName, String owner);

    //通过书本Id将书本的借阅次数进行加一
    public void updateBorrowInfoById(int bookId);

    //通过书本Id将书本总数进行加一
    public void updateAmountById(int bookId);
}
