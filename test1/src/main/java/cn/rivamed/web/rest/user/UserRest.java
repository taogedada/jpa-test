package cn.rivamed.web.rest.user;

import cn.rivamed.entity.Dept;
import cn.rivamed.entity.User;
import cn.rivamed.service.UserService;
import cn.rivamed.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserRest {

    @Autowired
    private UserService userService;

    @RequestMapping("savaInfo")
    public boolean savaInfo(){
        User u = new User();
        u.setBirthday(new Date());
        u.setUserName("张三");
        u.setUserSex("1");
        List<Dept> deptList = new ArrayList<Dept>();
        Dept d = new Dept();
        d.setDeptCode("a");
        d.setDeptName("测试");
        d.setUser(u);
        Dept d2 = new Dept();
        d2.setDeptCode("b");
        d2.setDeptName("质量");
        d2.setUser(u);
        Dept d3 = new Dept();
        d3.setDeptCode("c");
        d3.setDeptName("研发");
        d3.setUser(u);
        deptList.add(d);
        deptList.add(d2);
        deptList.add(d3);
        u.setDepts(deptList);
        try {
            boolean b = userService.savaInfo(u);
            if (b==true){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("queryUserById")
    public User queryUserById(User user){
        User users = userService.queryUserById(user.getId());
        return users;
    }

    @RequestMapping("findUserVo")
    public List<UserVo> findUserVo(User user){
        List<UserVo> userVo = userService.findUserVo(user.getId());
        return userVo;
    }

    @RequestMapping("findByBirthday")
    public User findByBirthday(User u){
        User user = userService.findByBirthday(u.getBirthday());
        return user;
    }
}
