package pro.sky.coursework2.diary.scanned;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component(IdComponent.NAME)
class IdComponent {
    static final String NAME = "scan_IdComponent";

    protected int createId() {
        int id;
        do {
            id = scanIdFromStdin();
        } while (id == 0);
        return id;
    }

    private int scanIdFromStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tВведите id задачи");
        try {
            int inputValue = sc.nextInt();
            return Math.max(inputValue, 0);
        } catch (Exception e) {
            System.out.println("Выберите корректный id > 0");
        }
        return 0;
    }
}
