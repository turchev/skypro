package pro.sky.coursework2.diary.view.scanned;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Scanner;

@Component(TextComponent.NAME)
class TextComponent {
    static final String NAME = "scan_TextComponent";

    protected String createText(@Nonnull String textType) {
        String text;
        do {
            text = scanTextFromStdin(textType);
        } while (text == null);
        return text;
    }

    private String scanTextFromStdin(String textType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t" + textType + ":");
        try {
            return sc.nextLine();
        } catch (Exception e) {
            System.out.println("Не корректные данные: " + textType);
            return null;
        }
    }
}
