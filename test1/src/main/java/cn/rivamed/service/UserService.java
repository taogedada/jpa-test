package cn.rivamed.service;

import cn.rivamed.entity.User;
import cn.rivamed.vo.user.UserVo;

import java.util.Date;
import java.util.List;

public interface UserService {

    boolean savaInfo(User u);

    User queryUserById(Integer id);

    List<UserVo> findUserVo(Integer id);

    User findByBirthday(Date date);
}
