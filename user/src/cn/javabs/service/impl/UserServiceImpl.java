package cn.javabs.service.impl;

import cn.javabs.dao.UserDao;
import cn.javabs.dao.impl.UserDaoImpl;
import cn.javabs.dao.impl.UserDaoImpl;
import cn.javabs.entity.User;
import cn.javabs.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao=  new UserDaoImpl();
    @Override
    public int adduser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int addUser(User user) {
        return 0;
    }


}
