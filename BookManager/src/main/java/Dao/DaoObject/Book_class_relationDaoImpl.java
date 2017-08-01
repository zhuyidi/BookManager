package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.DaoFactory.DaoFactory;
import Dao.IDao.BookDao;
import Dao.IDao.Book_class_relationDao;
import Dao.ValueObject.Book;
import Dao.ValueObject.Book_class;
import Dao.ValueObject.Book_class_relation;
import org.apache.commons.lang.StringEscapeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public class Book_class_relationDaoImpl implements Book_class_relationDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public List<Book_class_relation> relations (int book_classId){
        List<Book_class_relation> relations = new ArrayList<Book_class_relation>();
        Book_class_relation relation = null;
        String sql = "select * from book_class_relation where book_classId = " +
                            book_classId + ";";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                relation = new Book_class_relation();
                relation.setBook_classId(resultSet.getInt(1));
                relation.setBook_id(resultSet.getInt(2));
                relations.add(relation);
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Book> queryByClass(int classId) {
        List<Integer> booksId = new ArrayList<Integer>();
        List<Book> books = new ArrayList<Book>();

//        BookDaoImpl bookDao = (BookDaoImpl) DaoFactory.getBookDaoInstance();
        BookDao bookDao = DaoFactory.getBookDaoInstance();

        String sql = "select * from book_class_relation where book_classId = " + classId + ";";
        System.out.println(sql);
//        sql = StringEscapeUtils.escapeSql(sql);
        Connection connection = DBUtils.getConnection();

        //得到该分类下的bookId
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                booksId.add(resultSet.getInt(2));
            }

            //通过bookId得到每本书的信息
            for (Integer bookId : booksId) {
                Book book = (Book) bookDao.queryById(bookId);
                books.add(book);
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<Book> bookIterator = books.iterator();
        Book book = null;
        if(books.size() == 0){
            System.out.println(true);
        }
        while(bookIterator.hasNext()) {
            book = bookIterator.next();
            System.out.printf("###%s\n",book.getName());
        }
        return books;
    }

    public void insert(int labelId, int bookId) {
        String sql = "INSERT INTO book_class_relation(book_classId, book_id) " +
                "VALUES (?, ?);";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, labelId);
            statement.setInt(2, bookId);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByBookId(int bookId) {
        String sql = "delete from book_class_relation where book_id = " + bookId + ";";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
