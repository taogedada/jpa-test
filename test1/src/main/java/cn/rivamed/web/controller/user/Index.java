package cn.rivamed.web.controller.user;

import cn.rivamed.dao.AccountDao;
import cn.rivamed.dao.PermissionDao;
import cn.rivamed.dao.RoleDao;
import cn.rivamed.entity.Account;
import cn.rivamed.entity.File;
import cn.rivamed.entity.Permission;
import cn.rivamed.entity.Role;
import cn.rivamed.service.FileService;
import cn.rivamed.service.ProxyTest;
import cn.rivamed.service.impl.MyInvocationHandler;
import cn.rivamed.service.impl.ProxyTestImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Index {

    @Autowired
    AccountDao accountDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/")
    public String toIndex(){
        //测试动态代理
//        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(ProxyTestImpl.class);
//        enhancer.setCallback(myInvocationHandler);
//        ProxyTestImpl o = (ProxyTestImpl)enhancer.create();
//        String name = o.getName("张三");
//        System.out.println("代理完毕！");
        return "index";
    }

    @RequestMapping(value = "gotologin")
    public String gotologin(){
        return "login";
    }

    @RequestMapping(value = "loginfaile")
    public String loginfaile(){
        return "loginfaile";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String userName,String password) throws IOException {
        List<File> files = fileService.queryFiles();
        List list = new ArrayList();
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();

        ServletInputStream inputStream = request.getInputStream();

        int len;
                 while((len = inputStream.read(bytes)) != -1) {
                         outSteam.write(bytes, 0, len);
                   }
                outSteam.close();
        inputStream.close();
        String s = outSteam.toString();
        String[] split = s.split("&");
        String p = split[1];
        String[] split2 = p.split("=");
        System.out.println(request.getSession().getId());
        System.out.println(URLDecoder.decode(split[0],"UTF-8"));
        String decode = URLDecoder.decode(split[0], "UTF-8");
        System.out.println(decode);
        String[] split1 = decode.split("=");
        String na = split1[1];
        Account user = accountDao.findByUserNameAndPassword(na,split2[1]);
        if(user==null){
            return "loginfaile";
        }
        List<Role> byUserId = roleDao.findByUserId(user.getId());
        for (Role role : byUserId){
            list.add(role.getId());
        }
        List<Permission> byRoleIds = permissionDao.findByRoleIds(list);
        list.clear();
        for (Permission permission : byRoleIds){
            list.add(permission.getUrl());
        }
        HttpSession session = request.getSession();
        session.setAttribute("userName",user.getUserName());
        session.setAttribute("roles",list);
        session.setAttribute("files",files);
        return "books";
    }
    @RequestMapping(value = "logout")
    public String hello(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("userName");
        return "index";
    }
}
