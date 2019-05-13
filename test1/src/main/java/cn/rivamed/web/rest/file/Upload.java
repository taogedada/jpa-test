package cn.rivamed.web.rest.file;

import cn.rivamed.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping(value = "/file")
public class Upload {

    @Value("${file.upload.url}")
    private String url;

    @Resource
    private FileService fileService;

    @RequestMapping(value = "upload")
    public String upload(@RequestParam("file")MultipartFile file){
        try {
            cn.rivamed.entity.File f = new cn.rivamed.entity.File();
            String name = file.getOriginalFilename();
            String fileName = url+"/"+name;
            f.setName(name);
            f.setUrl(fileName);
            boolean bo = fileService.upload(f);
            if(bo==true){
                File file1 = new File(url);
                boolean b = file1.exists();
                if (b==false){
                    file1.mkdir();
                }
                file.transferTo(new File(fileName));
                return "上传成功";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "上传失败";
        }
        return "上传失败";
    }
}
