package cn.rivamed.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_base_user")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //MySQL设置主键自增，如果设置成GenerationType.AUTO会报错java.sql.SQLSyntaxErrorException: Table 'a2.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_sex")
    private String userSex;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    //使用mapperBy就不需要写@JoinColumn，主键维护会放到多的那一方
    //@JoinColumn(name = "dept_id")
    //加上次注解，在返回实体的时候会忽略该属性，如果不加会报Json映射失败，因为user表没有这个属性
    @JsonIgnore
    private List<Dept> depts;

    //此注解使时间以下列格式返回，如果不加这个注解则返回的是时间戳
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //如果前端传递的时间格式是字符串，此注解会将字符串转换成date类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="birthday")
    private Date birthday;

    public User(){}

}
