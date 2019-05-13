package cn.rivamed.service.impl;

import cn.rivamed.dao.DeptDao;
import cn.rivamed.entity.Dept;
import cn.rivamed.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Dept findById(Integer id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> findByUserId(Integer id) {
        return deptDao.findByUserId(id);
    }
}
