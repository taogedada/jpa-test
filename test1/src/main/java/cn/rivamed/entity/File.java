package cn.rivamed.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "file")
@JsonInclude(JsonInclude.Include.NON_NULL)
//设置为true,表示insert对象的时候,生成动态的insert语句,如果这个字段的值是null就不会加入到insert语句当中.默认false。
@DynamicInsert(true)
//动态修改，在做update操作时只会修改你改的字段，如果设置为FALSE，则修改全部字段，默认为FALSE
@DynamicUpdate(true)
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //MySQL设置主键自增，如果设置成GenerationType.AUTO会报错java.sql.SQLSyntaxErrorException: Table 'a2.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public File(){}
    public File(String name,String url){
        this.name=name;
        this.url=url;
    }
}
