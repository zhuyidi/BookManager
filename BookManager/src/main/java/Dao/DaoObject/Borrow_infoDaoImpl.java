package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.IDao.Borrow_infoDao;
import Dao.ValueObject.BorrowLog;
import Dao.ValueObject.Borrow_info;
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
public class Borrow_infoDaoImpl implements Borrow_infoDao {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement statement = null;

    public void deleteBookById(int bookId) {
        String sql = "update borrow_info set flag = " + 0 + "where book_id = " + bookId + ";";
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

    public void deleteBookByIdAndUsername(int id, String username) {
        String sql = "update borrow_info set flag = " + 0 + " where book_id = " + id + " and user_id = (select uid from cs_user where name = '" + username + "');";
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

    public void insert(Borrow_info borrowInfo) {
        String sql = "INSERT INTO borrow_info(book_id, user_id, borrow_date, flag)" +
                "VALUES (?, ?, ?, ?)";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, borrowInfo.getBook_id());
            statement.setInt(2, borrowInfo.getUser_id());
            statement.setString(3, borrowInfo.getBorrow_date());
            statement.setInt(4, borrowInfo.getFlag());
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //用于查看书籍的借阅线
    public List<Borrow_info> queryByBookId(int bookId) {
        List<Borrow_info> borrowInfos = new ArrayList<Borrow_info>();

        String sql = "SELECT * FROM borrow_info WHERE bookid = " + bookId;
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Borrow_info borrowInfo = new Borrow_info();
                borrowInfo.setBook_id(Integer.parseInt(resultSet.getString(1)));
                borrowInfo.setUser_id(Integer.parseInt(resultSet.getString(2)));
                borrowInfo.setBorrow_date(resultSet.getString(3));
                borrowInfo.setFlag(Integer.parseInt(resultSet.getString(4)));

                borrowInfos.add(borrowInfo);
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowInfos;
    }

    //用于查看自己目前所借阅的所有图书
    public List<Borrow_info> queryByBorrowUser(int userId) {
        List<Borrow_info> borrowInfos = new ArrayList<Borrow_info>();

        String sql = "SELECT * FROM borrow_info WHERE user_id = "
                + userId + " AND flag = 1";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Borrow_info borrowInfo = new Borrow_info();
                borrowInfo.setBook_id(Integer.parseInt(resultSet.getString(1)));
                borrowInfo.setUser_id(Integer.parseInt(resultSet.getString(2)));
                borrowInfo.setBorrow_date(resultSet.getString(3));
                borrowInfo.setFlag(Integer.parseInt(resultSet.getString(4)));

                borrowInfos.add(borrowInfo);
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowInfos;
    }

    public void updateByBookIdAndBorrowUser(int bookId, int userId) {
        String sql = "UPDATE borrow_info SET flag = 0 WHERE bookid = " + bookId +
                " AND user_id = " + userId;
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

    public List<BorrowLog> queryBorrowLog() {
        List<BorrowLog> borrowLogs = new ArrayList<BorrowLog>();

        String sql = "SELECT a.name, b.flag, b.borrow_date, c.name, c.owner FROM " +
                "cs_user AS a, borrow_info AS b, book_info AS c WHERE a.uid = " +
                "b.user_id AND c.id = b.book_id";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BorrowLog borrowLog = new BorrowLog();
                borrowLog.setBorrowUser(resultSet.getString(1));
                borrowLog.setFlag(Integer.parseInt(resultSet.getString(2)));
                borrowLog.setDate(resultSet.getString(3));
                borrowLog.setBookname(resultSet.getString(4));
                borrowLog.setOwner(resultSet.getString(5));

                borrowLogs.add(borrowLog);
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowLogs;
    }

}
