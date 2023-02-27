package pro.sky.socks.exeption;

public class LimitViolationException extends RuntimeException {
    public LimitViolationException(String message) {
        super(message);
    }
}
