package pro.sky.recipesite.exeption;

import java.io.IOException;

public class FileReadException extends IOException {
    public FileReadException(String message) {
        super(message);
    }
}
