package cn.rivamed.service;


import cn.rivamed.entity.File;

import java.util.List;

public interface FileService {


    boolean upload(File f);

    List<File> queryFiles();
}
