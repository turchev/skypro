package pro.sky.listqueue.rally.transport.truck;

import pro.sky.listqueue.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.listqueue.rally.person.CategoryType;
import pro.sky.listqueue.rally.person.Driver;
import pro.sky.listqueue.rally.transport.Transport;

import java.util.Objects;

public class Truck extends Transport {

    private WeightType weightType;

    public Truck(String brand, String model, Driver driver, WeightType weightType) {
        super(brand, model, driver);
        this.weightType = weightType;
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    @Override
    public void setDriver(Driver driver) throws IllegalDrivingLicenseException {
        super.setDriver(driver);
        if (!driver.isCategory(CategoryType.C)) {
            throw new IllegalDrivingLicenseException("Требуется категория C");
        }
    }

    public void printType() {
        System.out.println(Objects.requireNonNullElse(getWeightType(),
                "Данных по транспортному средству недостаточно"));
    }

    @Override
    public void passDiagnostics() {
        System.out.println("Прошел диагностику");
    }


    @Override
    public String toString() {
        return "Грузовой автомобиль: " + super.toString();
    }

    public enum WeightType {
        N1(null, 3.5f),
        N2(3.5f, 12.0f),
        N3(12.0f, null);

        private final Float minValue;
        private final Float maxValue;

        WeightType(Float minValue, Float maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        @Override
        public String toString() {
            return String.format("Грузоподъемность: %s%s",
                    minValue == null ? "" : "от " + minValue + " ",
                    maxValue == null ? "" : "до " + maxValue).trim() + " тонн";
        }
    }
}
