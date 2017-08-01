package Dao.DBConn;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by dela on 7/17/17.
 */
public class DBUtils {
    public static String URL;
    public static String USERNAME;
    public static String PASSWD;
    public static String Driver;

    //加载配置文件
    //如果利用resourse目录操作配置文件时,应该用resourceBundel类来操作文件
    private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

    //静态代码块在加载类时只会被执行一次
    static {
        URL = rb.getString("jdbc.URL");
        USERNAME = rb.getString("jdbc.USERNAME");
        PASSWD = rb.getString("jdbc.PASSWD");
        Driver = rb.getString("jdbc.Driver");

        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //开启数据库连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败!");
        }
        return conn;
    }

    //关闭数据库连接
    public static void close(ResultSet rs, Statement stat, Connection conn){
        try{
            if(rs != null){
                rs.close();
            }
            if(stat != null){
                stat.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
