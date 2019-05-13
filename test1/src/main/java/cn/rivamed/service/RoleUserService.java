package cn.rivamed.service;

import cn.rivamed.entity.RoleUser;

import java.util.List;

public interface RoleUserService {

    List<RoleUser> findByUserId(Integer userId);
}
