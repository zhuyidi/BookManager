package Dao.IDao;

import Dao.ValueObject.BorrowLog;
import Dao.ValueObject.Borrow_info;

import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public interface Borrow_infoDao {
    public void deleteBookById(int bookId);

    public void deleteBookByIdAndUsername(int id, String username);

    //向借阅者表单中添加数据
    public void insert(Borrow_info borrowInfo);

    //通过bookId进行查询
    public List<Borrow_info> queryByBookId(int bookId);

    //通过bookId与借阅者姓名修改借阅信息
    public void updateByBookIdAndBorrowUser(int bookId, int userId);

    //将借阅表中的所有信息拿出来，并将book_info中的owner拿出来
    public List<BorrowLog> queryBorrowLog();

}
