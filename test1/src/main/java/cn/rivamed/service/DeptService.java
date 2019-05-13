package cn.rivamed.service;

import cn.rivamed.entity.Dept;

import java.util.List;

public interface DeptService {

    Dept findById(Integer id);

    List<Dept> findByUserId(Integer id);
}
