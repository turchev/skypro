package pro.sky.hashmap.rally.exeption;

public class IllegalDrivingLicenseException extends Exception {

    public IllegalDrivingLicenseException() {
        super("Необходимо указать тип прав!");
    }

    public IllegalDrivingLicenseException(String message) {
        super(message);
    }
}
