package wu.jdbc.dao;

import wu.jdbc.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用户注册DAO
 */
public class RegeditDAO {

    public boolean addUser(String username,String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            String sql = "INSERT INTO user(username,password) VALUES(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("创建用户失败！");
            e.printStackTrace();
            return false;
        }finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }
}
