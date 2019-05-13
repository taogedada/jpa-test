package cn.rivamed.dao;


import cn.rivamed.entity.File;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
//可以不加
//@Repository
public interface FileDao extends PagingAndSortingRepository<File,Integer>,JpaSpecificationExecutor<File> {

}
