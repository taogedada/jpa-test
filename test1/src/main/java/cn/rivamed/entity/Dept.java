package cn.rivamed.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "t_base_dept")
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert(true)
@DynamicUpdate(true)
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //MySQL设置主键自增，如果设置成GenerationType.AUTO会报错java.sql.SQLSyntaxErrorException: Table 'a2.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_code")
    private String deptCode;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Dept(){}
}
