package cn.rivamed.service.impl;

import cn.rivamed.service.ProxyTest;
//动态代理
public class ProxyTestImpl implements ProxyTest {


    @Override
    public String getName(String name) {
        System.out.println("目标方法被调用");
        return name;
    }

    @Override
    public int getAge(int age) {
        System.out.println("目标方法被二次调用");
        return age;
    }
}
