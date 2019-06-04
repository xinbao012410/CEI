package cn.javabs.dao.impl;

import cn.javabs.dao.UserDao;
import cn.javabs.entity.User;
import cn.javabs.utils.DbcpUtil;
import org.apache.commons.dbutils.QueryRunner;


import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner(DbcpUtil.getDataSource());
    @Override
    public int addUser(User user) {
        try {
            return queryRunner.update("insert into user(username,password,sex) values (?,?,?)",
                    user.getUsername(),user.getPassword(),user.getSex());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
