package Dao.DaoObject;

import Dao.DBConn.DBUtils;
import Dao.IDao.Book_classDao;
import Dao.ValueObject.Book_class;
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
public class Book_classDaoImpl implements Book_classDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public List<Book_class> getBookClasses(int parent_id) {
        List<Book_class> book_classes = new ArrayList<Book_class>();
        Book_class book_class = null;
        String sql = "select * from book_class where parent_id = " + parent_id + ";";
        sql = StringEscapeUtils.escapeSql(sql);
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                book_class = new Book_class();
                book_class.setId(resultSet.getInt(1));
                book_class.setName(resultSet.getString(2));
                book_class.setParent_id(resultSet.getInt(3));
                book_classes.add(book_class);
            }
            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book_classes;
    }

    public void insertClass(String labelName, int parentId) {
        String sql = "INSERT INTO book_class(name, parent_id) VALUES (?, ?);";
        connection = DBUtils.getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, labelName);
            statement.setInt(2, parentId);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int queryIdByName(String labelName) {
        int flag = -1;
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "SELECT * FROM book_class WHERE name = '"+ labelName +"'");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                flag = Integer.parseInt(resultSet.getString(1));
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

}
