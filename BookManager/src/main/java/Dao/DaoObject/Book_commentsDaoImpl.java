package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.IDao.Book_commentsDao;
import Dao.ValueObject.Book_comments;
import org.apache.commons.lang.StringEscapeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public class Book_commentsDaoImpl implements Book_commentsDao{

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public void insert(Book_comments book_comments) {
        String sql = "insert into book_comments(book_id,user_id,detail,comment_datetime) values (?,?,?,?);";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,book_comments.getBook_id());
            statement.setInt(2,book_comments.getUser_id());
            statement.setString(3,book_comments.getDetail());
            statement.setString(4,book_comments.getComment_datetime());
            statement.execute();

            DBUtils.close(resultSet,statement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int book_id, int user_id,  String comment_datetime ){
        String sql = "delete * from book_comments where (book_id = " + book_id + "'AND user_id = '" + user_id + "' AND comment_datetime = '" + comment_datetime + "');";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,book_id);
            statement.setInt(2,user_id);
            statement.setString(4, comment_datetime);
            statement.executeUpdate();

            DBUtils.close(resultSet,statement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book_comments> queryByBook_id(int bookid) {
        List<Book_comments> book_comments = new ArrayList<Book_comments>();
        Book_comments book_comments1 = null;
        String sql = "select * from book_comments where book_id = " + bookid + ";";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                book_comments1 = new Book_comments();
                book_comments1.setBook_id(resultSet.getInt(1));
                book_comments1.setUser_id(resultSet.getInt(2));
                book_comments1.setDetail(resultSet.getString(3));
                book_comments1.setComment_datetime(resultSet.getString(4));
                book_comments.add(book_comments1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book_comments;
    }

}
