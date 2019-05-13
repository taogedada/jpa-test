package cn.rivamed.web.rest.file;

import cn.rivamed.entity.File;
import cn.rivamed.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class QueryFile {

    @Resource
    private FileService fileService;

    @RequestMapping(value = "queryFiles")
    public List queryFiles(){
        List<File> files =fileService.queryFiles();
        return files;
    }
}
