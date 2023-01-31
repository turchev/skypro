package pro.sky.coursework2.diary.scanned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.task.Task;

import javax.annotation.Nonnull;
import java.time.LocalDate;

@Component(ScannedService.NAME)
public class ScannedComponent implements ScannedService {

    @Autowired
    private TaskBuilderComponent taskBuilderComponent;
    @Autowired
    private LocalDateTimeComponent localDateTimeComponent;
    @Autowired
    private IdComponent idComponent;
    @Autowired
    private TextComponent textComponent;

    @Override
    public Task buildTask() {
        return taskBuilderComponent.buildTask();
    }

    @Override
    public LocalDate inLocalDate() {
        return localDateTimeComponent.createLocalDate();
    }

    @Override
    public int inTaskId() {
        return idComponent.createId();
    }

    @Override
    public String inText(@Nonnull String textType) {
        return textComponent.createText(textType);
    }
}
