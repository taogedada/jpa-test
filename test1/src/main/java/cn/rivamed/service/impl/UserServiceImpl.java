package cn.rivamed.service.impl;

import cn.rivamed.dao.UserDao;
import cn.rivamed.dao.impl.UserDaoImpl;
import cn.rivamed.entity.User;
import cn.rivamed.service.UserService;
import cn.rivamed.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoImpl user;

    @Override
    public boolean savaInfo(User u) {
            userDao.save(u);
            return true;
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public List<UserVo> findUserVo(Integer id) {
        //return userDao.findUserVo(id);
        return user.findUserVo(id);
    }

    @Override
    public User findByBirthday(Date date) {
        return userDao.findByBirthday(date);
    }
}
