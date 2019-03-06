package wu.jdbc.service;

import wu.jdbc.bean.Message;
import wu.jdbc.dao.MessageDAO;

import java.util.Date;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * 获取Message
     * @param page 页数
     * @param pageSize 每页个数
     * @return List<Message>
     */
    public List<Message> getMessages(int page,int pageSize) {
        return messageDAO.getMessages(page,pageSize);
    }

    /**
     * 统计Message总数
     * @return int Count
     */
    public int countMessages(){
        return messageDAO.countMessages();
    }

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    public boolean addMessage(Message message){
        message.setCreateTime(new Date());
        return messageDAO.addMessage(message);
    }

    /**
     * 分页查询所登录用户的留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @param id userId
     * @return
     */
    public List<Message> getMyMessages(int page,int pageSize,Long id){
        return messageDAO.getMyMessages(page,pageSize,id);
    }

    /**
     * 计算指定用户留言数量
     * @return count
     */
    public int countMyMessages(Long id){
        return messageDAO.countMyMessages(id);
    }

    /**
     * 修改留言Service
     * @param title
     * @param content
     * @return boolean
     */
    public boolean alterMessage(String title,String content,Long id){
        return messageDAO.alterMessage(title,content,id);
    }

    /**
     * 删除留言Service
     * @param id
     * @return
     */
    public boolean deleteMessage(Long id){
        return messageDAO.deleteMessage(id);
    }
}
