package cn.rivamed.web.rest.file;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/file")
public class DownLoad {

    @Value("${file.upload.url}")
    private String url;

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("downLoad")
    public void downLoad(@RequestParam("name")String name, HttpServletRequest request, HttpServletResponse response) throws Exception{
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(url+"/"+name);
        String fileName = name;
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len=inputStream.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
        inputStream.close();
        outputStream.close();
    }
}
