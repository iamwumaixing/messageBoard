package wu.jdbc.service;

import wu.jdbc.bean.User;
import wu.jdbc.dao.UserDAO;

/**
 * UserService
 */
public class UserService {
    private UserDAO userDAO;
    public UserService(){
        userDAO = new UserDAO();
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 成功返回Bean，失败返回null
     */
    public User login(String username, String password){
        return userDAO.login(username,password);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }

    public User getUserById(Long id){
        return userDAO.getUserById(id);
    }
}
