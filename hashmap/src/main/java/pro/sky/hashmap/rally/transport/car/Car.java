package pro.sky.hashmap.rally.transport.car;

import pro.sky.hashmap.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.hashmap.rally.person.CategoryType;
import pro.sky.hashmap.rally.person.Driver;
import pro.sky.hashmap.rally.transport.Transport;

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

    public void setDriver(Driver driver) throws IllegalDrivingLicenseException {
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
        System.out.println(this + " - Прошел диагностику");
    }

    @Override
    public String toString() {
        return "Легковой автомобиль: " + super.toString() + ", " + getBodyType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Car car = (Car) o;
        return getBodyType() == car.getBodyType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBodyType());
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
