package pro.sky.enums.rally.transport;

import pro.sky.enums.rally.person.Driver;

public abstract class Transport<T extends Driver> {

    protected TransportType type;

    private static final String DEFAULT_BRAND = "Brand";
    private static final String DEFAULT_MODEL = "Model";


    private String brand;
    private String model;
    private T driver;

    public Transport(String brand, String model, T driver) {
        this(brand, model);
        this.driver = driver;
    }

    public Transport(String brand, String model) {
        setBrand(brand);
        setModel(model);
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public void printType() {
        System.out.println(type == null ? "Данных по транспортному средству недостаточно" : type.toString());
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

    public T getDriver() {
        return driver;
    }

    public void setDriver(T driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return getBrand() + " " + getModel();
    }

}

