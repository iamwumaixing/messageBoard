package wu.jdbc.dao;

import wu.jdbc.bean.Message;
import wu.jdbc.common.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize  每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message order by create_time desc limit ?,?";//limit m, n：从第m条开始，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return messages;
    }
    /**
     * 计算所有留言数量
     * @return
     */
    public int countMessages(){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select count(*) total from message";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,stmt,conn);
        }
        return 0;
    }

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    public boolean addMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "INSERT INTO message(user_id,username,title,content,create_time) VALUES (?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,message.getUserId());
            stmt.setString(2,message.getUsername());
            stmt.setString(3,message.getTitle());
            stmt.setString(4,message.getContent());
            stmt.setTimestamp(5,new Timestamp(message.getCreateTime().getTime()));
            stmt.execute();
        }catch (Exception e){
            System.out.println("保存留言信息失败！");
            e.printStackTrace();
            return false;
        }finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }

    /**
     * 分页查询所登录用户的留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @param id userId
     * @return
     */
    public List<Message> getMyMessages(int page,int pageSize,Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message where user_id = ? order by create_time desc limit ?,?";//limit m, n：从第m条开始，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.setInt(2, (page - 1) * pageSize);
            stmt.setInt(3, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return messages;
    }

    /**
     * 计算指定用户留言数量
     * @return count
     */
    public int countMyMessages(Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select count(*) total from message where user_id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,stmt,conn);
        }
        return 0;
    }

    /**
     * 修改留言DAO
     * @param title
     * @param content
     * @return boolean
     */
    public boolean alterMessage(String title,String content,Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET title = ?,content = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setString(2,content);
            stmt.setLong(3,id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("修改留言失败！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }

    /**
     * 删除留言Service
     * @param id
     * @return
     */
    public boolean deleteMessage(Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "DELETE FROM message WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("修改留言失败！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }
}
