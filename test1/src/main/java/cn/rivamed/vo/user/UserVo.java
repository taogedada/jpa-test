package cn.rivamed.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserVo {

    private String userName;

    private String userSex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String deptName;

    private String deptCode;

    public UserVo(){}

    public UserVo(String userName, String userSex, Date birthday, String deptName, String deptCode) {
        this.userName = userName;
        this.userSex = userSex;
        this.birthday = birthday;
        this.deptName = deptName;
        this.deptCode = deptCode;
    }

//    public UserVo(String userName, String userSex, Date birthday){
//        this.userName=userName;
//        this.userSex=userSex;
//        this.birthday=birthday;
//    }
}
