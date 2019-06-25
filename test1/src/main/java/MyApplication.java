1import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "cn.rivamed.*")
public class MyApplication {
    public static void main(String[] args) {
        int i = 1;
        SpringApplication.run(MyApplication.class, args);
    }
}
