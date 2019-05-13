package cn.rivamed.service.impl;

import cn.rivamed.dao.RoleUserDao;
import cn.rivamed.entity.RoleUser;
import cn.rivamed.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleUserService")
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public List<RoleUser> findByUserId(Integer userId) {
        return roleUserDao.findByUserId(userId);
    }
}
