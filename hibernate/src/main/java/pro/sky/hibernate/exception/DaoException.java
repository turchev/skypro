package pro.sky.hibernate.exception;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }
}
