package cn.rivamed.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "sys_user")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //MySQL设置主键自增，如果设置成GenerationType.AUTO会报错java.sql.SQLSyntaxErrorException: Table 'a2.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    public Account(){}

}
