package Dao.IDao;

import Dao.ValueObject.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dela on 7/20/17.
 */
public interface UserDao {
    //按名字查询操作
    public List<User> queryByName(String name) throws SQLException;

    //根据用户名检验密码
    public User checkPassword(String username);

    public User queryById(int id);

    //根据用户名查取用户id
    int queryIdByName(String name);

    //根据某本书的评论去逐条查找评论过这本书的用户
    public List<User> queryByBookCommentsBookId(int bookid);
}
