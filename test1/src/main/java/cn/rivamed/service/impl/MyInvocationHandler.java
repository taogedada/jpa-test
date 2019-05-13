package cn.rivamed.service.impl;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
//动态代理cglib，比jdk的动态代理优势在于灵活度高，被代理的类不需要实现接口
public class MyInvocationHandler implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用目标方法"+method.getName());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("得到结果"+o1);
        return o1;
    }
}
