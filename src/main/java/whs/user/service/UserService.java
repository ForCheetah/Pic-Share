package whs.user.service;


import org.springframework.transaction.annotation.Transactional;
import whs.user.dao.UserDao;
import whs.user.domain.User;


@Transactional
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 通过昵称查找
     * @param uname
     * @return
     */
    public User findUserByUname(String uname) {

        return userDao.findByUname(uname);
    }

    /**
     * 注册用户
     * @param user
     */
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public User findUserByUid(String uid) {
        return userDao.findByUid(uid);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
