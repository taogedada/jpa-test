package cn.rivamed.dao;

import cn.rivamed.entity.Dept;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeptDao extends PagingAndSortingRepository<Dept,Integer>,JpaSpecificationExecutor<Dept> {

    Dept findById(Integer id);

    @Query("select d.deptCode,d.deptName from Dept d where d.user.id=?1")
    List<Dept> findByUserId(Integer id);
}
