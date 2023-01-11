package pro.sky.oop.part4.transport;

import pro.sky.oop.part4.Driving;
import pro.sky.oop.part4.person.Driver;

public abstract class Transport<T extends Driver> implements Competing, Driving {
    protected String transportName = "Транспортное средство";

    private static final String DEFAULT_BRAND = "Brand";
    private static final String DEFAULT_MODEL = "Model";
    private static final float DEFAULT_ENGINE_VOLUME = 1.6f;
    private static final int DEFAULT_MAX_SPEED = 180;
    private static final boolean DEFAULT_PIT_STOP = false;
    private static final int LAP_DISTANCE = 12800;

    private String brand;
    private String model;
    private float engineVolume;
    private int maxSpeed;
    private T driver;

    public Transport(String brand, String model, float engineVolume) {
        this(brand, model, engineVolume, DEFAULT_MAX_SPEED);
    }

    public Transport(String brand, String model, float engineVolume, int maxSpeed) {
        setBrand(brand);
        setModel(model);
        setEngineVolume(engineVolume);
        setMaxSpeed(maxSpeed);
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

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume < 0.0f ? DEFAULT_ENGINE_VOLUME : engineVolume;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed <= 0 ? DEFAULT_MAX_SPEED : maxSpeed;
    }

    public T getDriver() {
        return driver;
    }

    public void setDriver(T driver) {
        this.driver = driver;
    }

    @Override
    public void startDriving() {
        System.out.println("Стартует " + this);
    }

    @Override
    public void finishDriving() {
        System.out.println("Финиширует " + this);
    }

    @Override
    public void fillTransport() {
        System.out.println("Заправляется " + this);
    }

    /**
     * Для примера дополнительно переопределен в наследниках
     */
    @Override
    public boolean isPitStop() {
        return DEFAULT_PIT_STOP;
    }

    /**
     * Так как средняя скорость прохождения круга не известна, то просто для примера,
     * лучшее время прохождения круга считается исходя по максимальной скорости
     * транспортного средства и длины круга. В каждой имплементации оно свое.
     */
    @Override
    public double getBestLapTime() {
        return LAP_DISTANCE / (getMaxSpeed() * 1000.0 / 60.0);
    }

    @Override
    public String toString() {
        return transportName + " " + getBrand() + " " + getModel();
    }
}

