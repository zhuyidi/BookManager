package Dao.IDao;

import Dao.ValueObject.Book_comments;

import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public interface Book_commentsDao {
    //增加
    public void insert(Book_comments book_comments);
    //删除
    public void delete(int book_id, int user_id, String comment_datetime);

    //根据book_id查询
    public List<Book_comments> queryByBook_id(int bookid);

}
