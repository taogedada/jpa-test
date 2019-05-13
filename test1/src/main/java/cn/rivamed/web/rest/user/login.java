package cn.rivamed.web.rest.user;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 文件上传与下载总结
 * 1，上传的路径最好写配置文件里，这样灵活性较高，方便修改
 * 2，上传和下载都需要得到文件的绝对路径，可以把配置文件里的路径取出来拼接上你的文件名实现上传路径和下载
 */
@RestController
@RequestMapping(value = "/user")
public class login {

    @Value("${file.upload.url}")
    private String url;

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("downLoad")
    public void downLoad(HttpServletRequest request,HttpServletResponse response) throws Exception{
        OutputStream outputStream = response.getOutputStream();
//        String[] ps = url.split("/");
//        String path = request.getSession().getServletContext().getRealPath(ps[ps.length-1])+"\\低值245账号密码.docx";
//        System.out.println(path);
//        InputStream inputStream = new FileInputStream(path);
//        String p = path.substring(path.lastIndexOf("\\")+1);
//        System.out.println(p);
        InputStream inputStream = new FileInputStream(url+"/低值245账号密码.docx");
        String fileName = "低值245账号密码.docx";
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件,用utf-8编码
        response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len=inputStream.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 文件上传
     * 把一些会变化的参数写到配置文件里，如上传的url等
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file){
       try {
           File file1 = new File(url);
           boolean b = file1.exists();
           if (b==false){
               file1.mkdir();
           }
           //方法一
           // String[] p = url.split("/");
//           String name = file.getOriginalFilename();
//           System.out.println(name);
           //String path = request.getRealPath(p[p.length-1]);
           //System.out.println(path);
           //无论是/或者\\都是没问题的，都可以上传成功
           //String fileName = path+"\\"+name;
           //方法二,因为配置的url已是绝对路劲，所以不用像方法一那么写
           String name = file.getOriginalFilename();
           System.out.println(name);
           String fileName = url+"\\"+name;
           System.out.println(fileName);
           file.transferTo(new File(fileName));
           return "上传成功";
       }catch (Exception e){
           e.printStackTrace();
       }
       return "上传失败";
    }

}
