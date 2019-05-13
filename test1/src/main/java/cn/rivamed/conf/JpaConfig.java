package cn.rivamed.conf;

import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//扫描@Repository标注的类,basePackages指定扫描路径，dao层其实不加注解也可以
//basePackages = {"cn.rivamed.dao"}不能少，否则会报缺少dao组件的错误
@EnableJpaRepositories(basePackages = {"cn.rivamed.dao"},transactionManagerRef = "jpaTransactionManager",entityManagerFactoryRef = "entityManagerFactoryBean")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
//@EntityScan("cn.rivamed.entity")
public class JpaConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    /*
    LocalContainerEntityManagerFactoryBean 提供了对JPA EntityManagerFactory 的全面控制，非常适合那种需要细粒度定制的环境
    这是最为强大的JPA配置方式，允许在应用程序中灵活进行本地配置。它支持连接现有JDBC DataSource ， 支持本地事务和全局事务等等
    //配置持久化提供方
    factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    //配置数据源
    factoryBean.setDataSource(dataSource());
    //配置包
    factoryBean.setPackagesToScan("cn.rivamed.entity");
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws Exception{
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        DataSource dataSource = new DataSourceWrapper();
        ((DataSourceWrapper) dataSource).setJdbcURL(url);
        ((DataSourceWrapper) dataSource).setUserName(username);
        ((DataSourceWrapper) dataSource).setPassword(password);
        ((DataSourceWrapper) dataSource).setDriverClassName(driver);
        entityManagerFactoryBean.setDataSource(dataSource);
        //配置jpa供应商
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        //用于扫描JPA实体类 @Entity，也可以用注解@EntityScan("com.boot.demo.xxx.*.*")
        entityManagerFactoryBean.setPackagesToScan("cn.rivamed.entity");
        return entityManagerFactoryBean;
    }

    /**
     * 创建entityManager
     * @return
     * @throws Exception
     */
    @Bean
    public EntityManager entityManager()throws Exception{
        LocalContainerEntityManagerFactoryBean entityManager = this.entityManagerFactoryBean();
        return entityManager.getObject().createEntityManager();
    }

    /**
     * 配置jpa事物
     * @return
     * @throws Exception
     */
    @Bean
    public JpaTransactionManager jpaTransactionManager()throws Exception{
        LocalContainerEntityManagerFactoryBean entityManager = this.entityManagerFactoryBean();
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager.getObject());
        return transactionManager;
    }

    /**
     * 配置jpa事物拦截
     * @return
     * @throws Exception
     */
    @Bean
    public TransactionInterceptor transactionInterceptor()throws Exception{
        //LocalContainerEntityManagerFactoryBean entityManager = this.entityManagerFactoryBean();
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(jpaTransactionManager());
        Properties properties = new Properties();
        properties.setProperty("save*","PROPAGATION_REQUIRED");
        properties.setProperty("find*","PROPAGATION_REQUIRED");
        properties.setProperty("insert*","PROPAGATION_REQUIRED");
        properties.setProperty("update*","PROPAGATION_REQUIRED");
        properties.setProperty("delete*","PROPAGATION_REQUIRED");
        properties.setProperty("*", "PROPAGATION_REQUIRED");
        transactionInterceptor.setTransactionAttributes(properties);
        return transactionInterceptor;
    }

    /**
     * 配置切入点表达式
     * @return
     * @throws Exception
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor()throws Exception{
        DefaultPointcutAdvisor transactionAdvisor = new DefaultPointcutAdvisor();
        transactionAdvisor.setAdvice(transactionInterceptor());
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* cn.rivamed..service..*Service*.*(..))");
        transactionAdvisor.setPointcut(aspectJExpressionPointcut);
        return transactionAdvisor;
    }
}
