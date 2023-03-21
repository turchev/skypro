package pro.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("pro.sky.jdbc.configuration")
public class SqlBasicsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqlBasicsApplication.class, args);
    }
}