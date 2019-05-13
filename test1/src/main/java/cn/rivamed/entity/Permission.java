package cn.rivamed.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_permission")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //MySQL设置主键自增，如果设置成GenerationType.AUTO会报错java.sql.SQLSyntaxErrorException: Table 'a2.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "url")
    private String url;

    public Permission(){}

}
