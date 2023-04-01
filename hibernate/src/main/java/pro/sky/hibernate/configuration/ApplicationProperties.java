package pro.sky.hibernate.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "hibernate")
public record ApplicationProperties(
        String user,
        String password,
        String url,
        String driverClassName,
        String entityPackages) {
}
