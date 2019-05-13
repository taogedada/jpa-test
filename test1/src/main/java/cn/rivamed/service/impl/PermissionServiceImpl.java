package cn.rivamed.service.impl;

import cn.rivamed.dao.PermissionDao;
import cn.rivamed.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

}
