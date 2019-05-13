package cn.rivamed.web.rest.user;

import cn.rivamed.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionRest {

    @Autowired
    private PermissionService permissionService;

}
