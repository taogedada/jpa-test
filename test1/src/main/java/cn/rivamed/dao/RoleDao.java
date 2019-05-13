package cn.rivamed.dao;

import cn.rivamed.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleDao extends PagingAndSortingRepository<Role,Integer>,JpaSpecificationExecutor<Role> {


    @Query("select r from Role r left join RoleUser ru on r.id=ru.roleId where ru.userId=?1")
    List<Role> findByUserId(int id);
}
