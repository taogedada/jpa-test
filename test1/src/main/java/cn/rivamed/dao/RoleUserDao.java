package cn.rivamed.dao;

import cn.rivamed.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleUserDao extends PagingAndSortingRepository<RoleUser,Integer>,JpaSpecificationExecutor<RoleUser> {

    List<RoleUser> findByUserId(Integer userId);
}
