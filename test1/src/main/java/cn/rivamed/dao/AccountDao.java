package cn.rivamed.dao;

import cn.rivamed.entity.Account;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountDao extends PagingAndSortingRepository<Account,Integer>,JpaSpecificationExecutor<Account> {


    Account findByUserName(String username);

    Account findByUserNameAndPassword(String na, String s);
}
