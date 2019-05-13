package cn.rivamed.dao;

import cn.rivamed.entity.User;
import cn.rivamed.vo.user.UserVo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User,Integer>,JpaSpecificationExecutor<User> {

    //如果加了hibernate依赖，则可以这么写，不然只能写成User u left join u.depts d(否则会报错)
    @Query("select new cn.rivamed.vo.user.UserVo(u.userName,u.userSex,u.birthday,d.deptName,d.deptCode)"
    + " from User u left join Dept d on u.id=d.user.id"
    + " where u.id=?1")
    List<UserVo> findUserVo(Integer id);

    @Query("select u from User u where u.birthday<?1")
    User findByBirthday(Date date);
}
