package pro.sky.jdbc.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "jdbc")
public record FilesProperties(String user, String password, String url) {
}
