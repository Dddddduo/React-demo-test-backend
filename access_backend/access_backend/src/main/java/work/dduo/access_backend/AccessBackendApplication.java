package work.dduo.access_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("work.dduo.access_backend.mapper")
public class AccessBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessBackendApplication.class, args);
    }

}
