package pro.sky.oop.part1;

import java.util.Objects;

public class Car {
    private static final String DEFAULT_CAR = "default";
    private static final float DEFAULT_ENGINE_VOLUME = 1.5f;
    private static final String DEFAULT_COLOR = "белый";
    private static final int DEFAULT_YEAR = 2000;


    private String brand;
    private String model;
    private float engineVolume;
    private String color;
    private int year;
    private String country;

    // для задания 1 значения по умолчанию не обязательны
    public Car() {
        this(DEFAULT_CAR, DEFAULT_CAR);
    }

    public Car(String brand, String model) {
        this(brand, model, DEFAULT_ENGINE_VOLUME);
    }

    public Car(String brand, String model, float engineVolume) {
        this(brand, model, engineVolume, DEFAULT_COLOR);
    }

    public Car(String brand, String model, float engineVolume, String color) {
        this(brand, model, engineVolume, color, DEFAULT_YEAR);
    }

    public Car(String brand, String model, float engineVolume, String color, int year) {
        this(brand, model, engineVolume, color, year, DEFAULT_CAR);
    }

    public Car(String brand, String model, float engineVolume, String color, int year, String country) {
        this.brand = Objects.requireNonNullElse(brand, DEFAULT_CAR);
        this.model = Objects.requireNonNullElse(model, DEFAULT_CAR);
        this.engineVolume = engineVolume <= 0.0f ? DEFAULT_ENGINE_VOLUME : engineVolume;
        this.color = Objects.requireNonNullElse(color, DEFAULT_COLOR);
        this.year = year <= 0 ? DEFAULT_YEAR : year;
        this.country = Objects.requireNonNullElse(country, DEFAULT_CAR);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("%s %s, цвет кузова - %s, объем двигателя - %.1f л., " +
                        "страна сборки - %s, год производства - %d",
                getBrand(), getModel(), getColor(), getEngineVolume(), getCountry(), getYear());
    }
}
