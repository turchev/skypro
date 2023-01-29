package pro.sky.coursework2.diary.view.scanned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.task.Task;

import java.time.LocalDate;

@Component(FrontEndService.NAME)
public class FrontEndComponent implements FrontEndService {

    @Autowired
    private TaskBuilderComponent taskBuilderComponent;
    @Autowired
    private LocalDateTimeComponent localDateTimeComponent;
    @Autowired
    private IdComponent idComponent;


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
}
