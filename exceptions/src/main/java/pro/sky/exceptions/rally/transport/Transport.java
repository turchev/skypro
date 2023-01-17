package pro.sky.exceptions.rally.transport;

import pro.sky.exceptions.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.exceptions.rally.person.Driver;

public abstract class Transport {

    private static final String DEFAULT_BRAND = "Brand";
    private static final String DEFAULT_MODEL = "Model";

    private String brand;
    private String model;
    private Driver driver;

    public Transport(String brand, String model, Driver driver) {
        setBrand(brand);
        setModel(model);
        setDriver(driver);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null || brand.isBlank() ? DEFAULT_BRAND : brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null || model.isBlank() ? DEFAULT_MODEL : model;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalDrivingLicenseException("Транспорт без водителя, необходимо назначить");
        }
        this.driver = driver;
    }

    public void printType() {
        System.out.println("Данных по транспортному средству недостаточно");
    }

    public void passDiagnostics() {
    }

    @Override
    public String toString() {
        return getBrand() + " " + getModel();
    }

}

