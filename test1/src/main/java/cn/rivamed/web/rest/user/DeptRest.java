package cn.rivamed.web.rest.user;

import cn.rivamed.entity.Dept;
import cn.rivamed.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class DeptRest {

    @Autowired
    private DeptService deptService;

    @RequestMapping("findById")
    public Dept findById(Dept dept){
        Dept d = deptService.findById(dept.getId());
                return d;
    }

    @RequestMapping("findByUserId")
    public List<Dept> findByUserId(Dept dept){
        List<Dept> depts = deptService.findByUserId(dept.getUser().getId());
        return depts;
    }
}
