package cn.rivamed.service.impl;

import cn.rivamed.dao.FileDao;
import cn.rivamed.entity.File;
import cn.rivamed.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

//    public FileServiceImpl(FileDao fileDao){
//        this.fileDao=fileDao;
//    }


    @Override
    public boolean upload(File f) {
            if(f==null){
                return false;
            }
            fileDao.save(f);
            return true;
    }

    @Override
    public List<File> queryFiles() {
        List<File> iterable = (List)fileDao.findAll();
        return iterable;
    }
}
