package cn.rivamed.dao.impl;

import cn.rivamed.vo.user.UserVo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl {

    @PersistenceContext
    EntityManager entityManager;


    public List<UserVo> findUserVo(Integer id) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> map = new HashMap<String,Object>();
        stringBuffer.append("select new cn.rivamed.vo.user.UserVo(u.userName,u.userSex,u.birthday,d.deptName,d.deptCode)")
        .append(" from User u left join Dept d on u.id=d.user.id")
        .append(" where 1=1");
        if(id!=null){
            stringBuffer.append(" and u.id=:id");
            //map.put("id",id);
        }
        Query query = entityManager.createQuery(stringBuffer.toString(),UserVo.class);
        query.setParameter("id",id);
        List<UserVo> userVos = query.getResultList();
        return userVos;

    }
}
