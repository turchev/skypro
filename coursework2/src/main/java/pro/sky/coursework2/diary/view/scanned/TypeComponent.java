package pro.sky.coursework2.diary.view.scanned;

import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.task.Type;

import java.util.Scanner;

@Component(TypeComponent.NAME)
class TypeComponent {
    static final String NAME = "scan_TypeComponent";

    protected Type createType() {
        Type type;
        do {
            type = scanTypeFromStdin();
        } while (type == null);
        return type;
    }

    private Type scanTypeFromStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nВыберите тип задачи \n\t1 - личная\n\t2 - рабочая");
        try {
            int inputValue = sc.nextInt();
            switch (inputValue) {
                case 1:
                    return Type.PERSONAL;
                case 2:
                    return Type.WORK;
                default:
                    System.out.println("Выберите корректный тип задачи, допустимо: 1 или 2");
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Выберите корректный тип задачи, допустимо: 1 или 2");
            return null;
        }
    }
}
