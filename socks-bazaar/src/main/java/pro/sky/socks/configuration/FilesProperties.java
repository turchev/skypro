package pro.sky.socks.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "files")
public record FilesProperties(String filesDir, String socksFileName) {
}
