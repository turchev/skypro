package pro.sky.socks.exeption;

import java.io.IOException;

public class FileReadException extends IOException {
    public FileReadException(String message) {
        super(message);
    }
}
