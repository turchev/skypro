package pro.sky.coursework2.diary.exception;

import lombok.Getter;

public class IncorrectArgumentException extends Exception {

    @Getter
    private final String argument;

    public IncorrectArgumentException(String message, String argument) {
        super(message);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return super.toString() + ": argument = (" + this.argument + ")";
    }
}
