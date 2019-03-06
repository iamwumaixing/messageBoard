package wu.jdbc.service;

import wu.jdbc.dao.RegeditDAO;

/**
 * 用户注册Service
 *
 */
public class RegeditService {
    private RegeditDAO regeditDAO;
    public RegeditService(){
        regeditDAO = new RegeditDAO();
    }
    public boolean addUser(String username,String password){
        return regeditDAO.addUser(username,password);
    }
}
