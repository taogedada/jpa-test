package cn.rivamed.conf;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "MyFilter",urlPatterns = "/")
//如果用上面的注解，需要在启动类里加@ServletComponentScan注解，自定义过滤器才能生效
@Component
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
//        if(request.getRequestURI().equals("/index.jsp") || request.getRequestURI().equals("/login.jsp")){
//            //chain.doFilter(req, resp);
//        } else if(request.getRequestURI().contains(".jsp")){
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write("无效访问!");
//            return;
//        }
        System.out.println("过滤器执行拉");
        //设置了contentType还是会被@ResponseBody（如果没指定返回类型，默认返回text/html）这个注解返回类型覆盖掉
//        resp.setContentType("application/json;charset=utf-8");
//        resp.getWriter().write("json来了!");
        //除了contentType其余的设置依然有效
        //response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化拉");
    }

}
