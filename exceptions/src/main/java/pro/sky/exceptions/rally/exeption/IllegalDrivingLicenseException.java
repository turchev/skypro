package pro.sky.exceptions.rally.exeption;

public class IllegalDrivingLicenseException extends RuntimeException {

    public IllegalDrivingLicenseException() {
        super("Необходимо указать тип прав!");
    }

    public IllegalDrivingLicenseException(String message) {
        super(message);
    }
}
