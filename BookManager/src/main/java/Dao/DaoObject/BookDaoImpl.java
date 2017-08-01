package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.IDao.BookDao;
import Dao.ValueObject.Book;
import Model.DealKeywords;
import org.apache.commons.lang.StringEscapeUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dela on 7/21/17.
 */
public class BookDaoImpl implements BookDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public void insert(Book book) {
        String sql = "insert into book_info values(?, ?, ?, ?, ?, ?, ?, ?);";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getOwner());
            statement.setInt(5, book.getAmount());
            statement.setString(6, book.getUpload_date());
            statement.setString(7, book.getDescribe());
            statement.setInt(8, book.getBorrow_num());
            sql = StringEscapeUtils.escapeSql(sql);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) throws SQLException {
        String sql = "delete * from book_info where name = ?;";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update (Book book) {
        String sql = "update book_info set name = '" + book.getName()+
                "', author = '" + book.getAuthor() +
                "', owner = '" + book.getOwner() +
                "', amount = " + book.getAmount() +
                ", upload_date = '" + book.getUpload_date() +
                "', describ = '" + book.getDescribe() +
                "', borrow_num = " + book.getBorrow_num() +
                " where id = " + book.getId() + ";";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            System.out.println(sql);
            statement.executeUpdate();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> queryByNAO(String keyWords) {
        keyWords = StringEscapeUtils.escapeSql(keyWords);
//        System.out.println(keyWords);
        keyWords = DealKeywords.dealKeyWords(keyWords);
        String sql = "select * from book_info where (owner like '" + keyWords + "' or name like '"
                + keyWords + "' or author like '" + keyWords + "') and amount > 0;";
        System.out.println(sql);
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
                books.add(book);
            }

            System.out.printf("bookDAO里模糊查询的结果有%d条\n", books.size());
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book queryById(int bookId) {
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        String sql = "select * from book_info where id = " + bookId  + ";";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<Book> queryAllBook() {
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        String sql = "select * from book_info where amount > 0;";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
                books.add(book);
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> queryByFatherClassId(int fatherClassId) {
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        String sql = "select * from book_info where id in (select book_id from book_class_relation where book_classId in " +
                "(select id from book_class where parent_id = " + fatherClassId + ")) and amount > 0;";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
                books.add(book);
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> queryByOwner(String owner) {
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        owner = StringEscapeUtils.escapeSql(owner);
        String sql = "select * from book_info where owner = '" + owner + "';";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
                books.add(book);
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> queryMyBorrowBooks(String username) {
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        StringEscapeUtils.escapeSql(username);
        String sql = "select * from book_info where id in (select book_id from borrow_info where user_id in" +
                "(select uid from cs_user where name ='" + username + "' and flag = 1 " + "));";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            System.out.println(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setOwner(resultSet.getString(4));
                book.setAmount(resultSet.getInt(5));
                book.setUpload_date(resultSet.getString(6));
                book.setDescribe(resultSet.getString(7));
                book.setBorrow_num(resultSet.getInt(8));
                books.add(book);
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void deleteById(int id){
        String sql = "delete from book_info where id =" + id +";";
        System.out.println(sql);
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBookAmount(int id){
        Book book = queryById(id);
        String sql = "update book_info set amount = " + (book.getAmount()+1) + " where id = " + id + ";";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //通过书本名与归属者查询书本唯一ID
    public int queryBookIdByBookNameAndOwner(String bookName, String owner) {
        bookName = StringEscapeUtils.escapeSql(bookName);
        owner = StringEscapeUtils.escapeSql(owner);
        String sql = "SELECT id FROM book_info WHERE " +
                "name = '" + bookName + "' AND owner = '" + owner + "';";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void updateBorrowInfoById(int bookId) {
        String sql = "UPDATE book_info SET borrow_num = borrow_num+1, amount = amount-1 " +
                "WHERE id = " + bookId;
        System.out.println(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAmountById(int bookId) {
        String sql = "UPDATE book_info SET amount = amount+1 WHERE bookid = " + bookId;
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}