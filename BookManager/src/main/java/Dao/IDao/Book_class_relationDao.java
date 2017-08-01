package Dao.IDao;


import Dao.ValueObject.Book;
import Dao.ValueObject.Book_class_relation;

import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public interface Book_class_relationDao {
    public List<Book_class_relation> relations (int book_classId);

    public List<Book> queryByClass(int classId);

    public void insert(int labelId, int bookId);

    public void deleteByBookId(int bookId);
}
