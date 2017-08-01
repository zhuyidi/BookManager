package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.IDao.UserDao;
import Dao.ValueObject.User;
import org.apache.commons.lang.StringEscapeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dela on 7/20/17.
 */
public class UserDaoImpl implements UserDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public List<User> queryByName(String name) throws SQLException {
        List<User> users = new ArrayList<User>();
        User user = null;
        String sql = "select * from cs_user where userid = ?";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            sql = StringEscapeUtils.escapeSql(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setUid(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPrivilege(resultSet.getInt(3));
                user.setPassword(resultSet.getString(4));
                user.setSex(resultSet.getInt(5));
                user.setPhone(resultSet.getString(6));
                user.setMail(resultSet.getString(7));
                user.setWechat(resultSet.getString(8));
                user.setBlog(resultSet.getString(9));
                user.setGithub(resultSet.getString(10));
                user.set_native(resultSet.getString(11));
                user.setGrade(resultSet.getString(12));
                user.setMajor(resultSet.getString(13));
                user.setWorkplace(resultSet.getString(14));
                user.setJob(resultSet.getString(15));
                users.add(user);
            }
            resultSet.close();
            statement.close();
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User checkPassword(String username) {
        connection = DBUtils.getConnection();
        User user = null;
        username = StringEscapeUtils.escapeSql(username);
        String sql = "select * from cs_user where name =  '" + username + "';";
        System.out.println(sql);
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setUid(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPrivilege(resultSet.getInt(3));
                user.setPassword(resultSet.getString(4));
                user.setSex(resultSet.getInt(5));
                user.setPhone(resultSet.getString(6));
                user.setMail(resultSet.getString(7));
                user.setQq(resultSet.getString(8));
                user.setWechat(resultSet.getString(9));
                user.setBlog(resultSet.getString(10));
                user.setGithub(resultSet.getString(11));
                user.set_native(resultSet.getString(12));
                user.setGrade(resultSet.getString(13));
                user.setMajor(resultSet.getString(14));
                user.setWorkplace(resultSet.getString(15));
                user.setJob(resultSet.getString(16));
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User queryById(int id) {
        User user = null;
        String sql = "select * from cs_user where uid = " + id + ";";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setUid(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPrivilege(resultSet.getInt(3));
                user.setPassword(resultSet.getString(4));
                user.setSex(resultSet.getInt(5));
                user.setPhone(resultSet.getString(6));
                user.setMail(resultSet.getString(7));
                user.setQq(resultSet.getString(8));
                user.setWechat(resultSet.getString(9));
                user.setBlog(resultSet.getString(10));
                user.setGithub(resultSet.getString(11));
                user.set_native(resultSet.getString(12));
                user.setGrade(resultSet.getString(13));
                user.setMajor(resultSet.getString(14));
                user.setWorkplace(resultSet.getString(15));
                user.setJob(resultSet.getString(16));
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int queryIdByName(String name) {
        name = StringEscapeUtils.escapeSql(name);
        String sql = "select uid from cs_user where name = '" + name + "';";
        System.out.println(sql);
        connection = DBUtils.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<User> queryByBookCommentsBookId(int bookid) {
        List<User> users = new ArrayList<User>();
        User user = null;
        String sql = "select * from cs_user where uid in (select user_id from book_comments where book_id = " + bookid + ");";
        System.out.println(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUid(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPrivilege(resultSet.getInt(3));
                user.setPassword(resultSet.getString(4));
                user.setSex(resultSet.getInt(5));
                user.setPhone(resultSet.getString(6));
                user.setMail(resultSet.getString(7));
                user.setWechat(resultSet.getString(8));
                user.setBlog(resultSet.getString(9));
                user.setGithub(resultSet.getString(10));
                user.set_native(resultSet.getString(11));
                user.setGrade(resultSet.getString(12));
                user.setMajor(resultSet.getString(13));
                user.setWorkplace(resultSet.getString(14));
                user.setJob(resultSet.getString(15));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}