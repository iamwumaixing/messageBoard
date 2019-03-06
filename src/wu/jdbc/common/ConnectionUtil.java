package wu.jdbc.common;
import java.sql.*;

public final class ConnectionUtil {
    private ConnectionUtil(){}
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败！");
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库工具
     * @return Connection
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/message_board?characterEncoding=UTF-8","root","1997");
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param rs ResultSet
     * @param stmt Statement
     * @param conn Connection
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn){
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
