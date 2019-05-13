package cn.rivamed.dao;
import cn.rivamed.entity.Permission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PermissionDao extends PagingAndSortingRepository<Permission,Integer>,JpaSpecificationExecutor<Permission> {


    @Query("select p from Permission p where p.roleId in(?1)")
    List<Permission> findByRoleIds(List list);
}
