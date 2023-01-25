package pro.sky.exceptions.rally.transport.car;

import pro.sky.exceptions.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.exceptions.rally.person.CategoryType;
import pro.sky.exceptions.rally.person.Driver;
import pro.sky.exceptions.rally.transport.Transport;

import java.util.Objects;

public class Car extends Transport {

    private BodyType bodyType;

    public Car(String brand, String model, Driver driver, BodyType bodyType) {
        super(brand, model, driver);
        this.bodyType = bodyType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public void setDriver(Driver driver) {
        super.setDriver(driver);
        if (!driver.isCategory(CategoryType.B)) {
            throw new IllegalDrivingLicenseException("Требуется категория B");
        }
    }

    public void printType() {
        System.out.println(Objects.requireNonNullElse(getBodyType(),
                "Данных по транспортному средству недостаточно"));
    }

    public void passDiagnostics() {
        System.out.println("Прошел диагностику");
    }

    @Override
    public String toString() {
        return "Легковой автомобиль: " + super.toString();
    }

    public enum BodyType {
        SEDAN("Седан"),
        HATCHBACK("Хэтчбек"),
        COUPE("Купе"),
        UNIVERSAL("Универсал"),
        SUV("Внедорожник"),
        CROSSOVER("Кроссовер"),
        PICKUP("Пикап"),
        VAN("Фургон"),
        MINIVAN("Минивэн");

        private final String value;

        BodyType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Тип кузова: " + value;
        }
    }
}