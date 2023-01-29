package pro.sky.coursework2;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pro.sky.coursework2.diary.view.FrontEnd;

@Log4j2
@Configuration
@ComponentScan("pro.sky.coursework2.diary")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        FrontEnd frontEnd = context.getBean("diary_FrontEnd", FrontEnd.class);
        frontEnd.run();
    }
}
