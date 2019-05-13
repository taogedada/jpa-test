package cn.rivamed.web.rest.user;

import cn.rivamed.entity.RoleUser;
import cn.rivamed.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleUserRest {

    @Autowired
    private RoleUserService roleUserService;

    @RequestMapping("queryRoleByUsername")
    public List<RoleUser> findByUserId(RoleUser roleUser){
        return roleUserService.findByUserId(roleUser.getUserId());
    }
}
