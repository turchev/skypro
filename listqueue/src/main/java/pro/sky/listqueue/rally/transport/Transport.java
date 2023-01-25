package pro.sky.listqueue.rally.transport;

import pro.sky.listqueue.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.listqueue.rally.person.Driver;
import pro.sky.listqueue.rally.person.Mechanic;

import java.util.LinkedList;
import java.util.List;

public abstract class Transport {

    private static final String DEFAULT_BRAND = "Brand";
    private static final String DEFAULT_MODEL = "Model";

    private String brand;
    private String model;
    private Driver driver;
    private List<Mechanic> mechanics;

    public Transport(String brand, String model, Driver driver) {
        setBrand(brand);
        setModel(model);
        try {
            setDriver(driver);
        } catch (IllegalDrivingLicenseException e) {
            System.out.println("Водитель не допущен к управлению: " + e.getMessage());
        }
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

    public void setDriver(Driver driver) throws IllegalDrivingLicenseException {
        if (driver == null) {
            throw new IllegalArgumentException("Транспорт без водителя, необходимо назначить");
        }
        this.driver = driver;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public boolean addMechanic(Mechanic mechanic) {
        if (mechanic == null) return false;
        if (this.mechanics == null) {
            this.mechanics = new LinkedList<>();
        }
        return this.mechanics.add(mechanic);
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
