package pro.sky.recipesite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("pro.sky.recipesite.configuration")
public class RecipeSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSiteApplication.class, args);
    }

}
