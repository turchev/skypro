package pro.sky.socks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("pro.sky.socks.configuration")
public class SocksSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksSiteApplication.class, args);
    }

}
