package pro.sky.enums.rally.transport.car;

import pro.sky.enums.rally.person.DriverCategoryB;
import pro.sky.enums.rally.transport.Transport;
import pro.sky.enums.rally.transport.TransportType;

import java.util.Objects;

public class Car extends Transport<DriverCategoryB> {
    {
        type = TransportType.CAR;
    }

    private BodyType bodyType;

    public Car(String brand, String model, DriverCategoryB driver, BodyType bodyType) {
        super(brand, model, driver);
        this.bodyType = bodyType;
    }

    public Car(String brand, String model) {
        super(brand, model);
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return (super.toString() + ", " + Objects.requireNonNullElse(getBodyType(), "")).trim();
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
