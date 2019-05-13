package cn.rivamed.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    /**
     * springmvc运行流程：
     * 用户发起请求，被DispatcherServlet（前端控制器）拦截到后，使用HandlerMapping（处理器映射器）
     * 来处理，HandlerMapping会获取请求对应的Handler（处理器），封装成一个HandlerExecutionChain 对象（里面包括了拦截器和处理请求的Handler（处理器）对象）返回
     * 然后被DispatcherServlet会去获取请求的HandleAdaptor（处理器适配器）执行Handler方法
     * 执行完后返回一个ModelAndView对象给DispatcherServlet，DispatcherServlet会去寻找对应的视图解析器渲染视图
     * 然后返还给浏览器
     * @param registry
     */
    //配置拦截器
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            //方法调用之前执行，可以做权限控制，跨域等
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, java.lang.Object handler) throws Exception {
//                if(request.getRequestURI().equals("/login")){
//                    if(request.getParameter("name")==null||!request.getParameter("name").equals("张三")){
//                        //解决response返回中文乱码,通过设置contentType来指定浏览器的接收格式和字符集(默认字符集是iso-8859-1)
//                    response.setContentType("application/json;charset=utf-8");
//                    response.getWriter().write("请先登陆");
//                        //解决不了乱码
////                    String s = new String("请先登陆");
////                    byte[] bytes = s.getBytes("UTF-8");
////                    String s1 = new String(bytes);
//                        //response.getWriter().write("s1");
//                        //如果跳转页面，上面的response将不会返回信息，相当于没写
//                        request.getRequestDispatcher(request.getContextPath()+"/loginfaile.jsp").forward(request,response);
//                        return false;
//                    }
//                }
                System.out.println("拦截器执行拉");
                if(request.getRequestURI().equals("/")){
                    return true;
                }else if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/gotologin")){
                    return true;
                }else if(request.getSession().getAttribute("userName")==null){
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write("请登录");
                    return false;
                }else{
                    int i = 0;
                    List<String> roles = (List)request.getSession().getAttribute("roles");
                    for(String role : roles){
                        if(role.equals(request.getRequestURI()) || request.getRequestURI().contains(role)){
                            i = 1;
                            break;
                        }
                    }
                    if(i==0){
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write("权限不够");
                        return false;
                    }
                }
                return true;
            }

            //在方法调用后执行，也就是controller之后，视图渲染之前执行，这里可以设置返回的视图和参数
            @Override
            public void postHandle(HttpServletRequest request,HttpServletResponse response,java.lang.Object handler,ModelAndView modelAndView) throws Exception {
                //注意：modelAndView保存的数据是存在request域里，重定向后数据会丢失
//                modelAndView.addObject("name","张三");
//                modelAndView.setViewName("login");
//                System.out.println(handler);
//                System.out.println(modelAndView.getModel()+"===="+modelAndView.getView());
//                System.out.println(modelAndView.getModelMap()+"===="+modelAndView.getViewName());
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, java.lang.Object handler, Exception ex) throws Exception {

            }
        }).addPathPatterns("/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    //配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                //很重要，如果不设置则cookie不能跨域，session会丢失，前端也需要相应的Ajax请求里配置xhrFields:{withCredentials:true}
                .allowCredentials(true)
                .allowedHeaders("X-Requested-With","Content-Type")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedOrigins("*")
                .maxAge(3600);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    //配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //方法一
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setSuffix(".jsp");
//        internalResourceViewResolver.setPrefix("/");
//        registry.viewResolver(internalResourceViewResolver);

        //方法二返回的是这个，如果前缀和后缀匹配不上会404
//        public UrlBasedViewResolverRegistration jsp() {
//            return jsp("/WEB-INF/", ".jsp");
//        }
        //registry.jsp();

        //方法三
        registry.jsp("/",".jsp");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
