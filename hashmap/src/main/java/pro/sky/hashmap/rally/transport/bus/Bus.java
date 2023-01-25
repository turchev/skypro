package pro.sky.hashmap.rally.transport.bus;

import pro.sky.hashmap.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.hashmap.rally.person.CategoryType;
import pro.sky.hashmap.rally.person.Driver;
import pro.sky.hashmap.rally.transport.Transport;

import java.util.Objects;

public class Bus extends Transport {

    private CapacityType capacityType;

    public Bus(String brand, String model, Driver driver, CapacityType capacityType) {
        super(brand, model, driver);
        this.capacityType = capacityType;
    }

    public CapacityType getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(CapacityType capacityType) {
        this.capacityType = capacityType;
    }

    public void setDriver(Driver driver) throws IllegalDrivingLicenseException {
        super.setDriver(driver);
        if (!driver.isCategory(CategoryType.D)) {
            throw new IllegalDrivingLicenseException("Требуется категория D");
        }
    }

    public void printType() {
        System.out.println(Objects.requireNonNullElse(getCapacityType(),
                "Данных по транспортному средству недостаточно"));
    }

    @Override
    public void passDiagnostics() {
        throw new UnsupportedOperationException(this + " - Автобусы диагностику проходить не могут");
    }

    @Override
    public String toString() {
        return "Автобус: " + super.toString() + ", " + getCapacityType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bus)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Bus bus = (Bus) o;
        return getCapacityType() == bus.getCapacityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCapacityType());
    }

    public enum CapacityType {
        VERY_SMALL(null, 10),
        SMALL(null, 25),
        MEDIUM(40, 50),
        LARGE(60, 80),
        ESPECIALLY_LARGE(80, 120);

        private final Integer capacityMin;
        private final Integer capacityMax;

        CapacityType(Integer capacityMin, Integer capacityMax) {
            this.capacityMin = capacityMin;
            this.capacityMax = capacityMax;
        }

        @Override
        public String toString() {
            return String.format("Вместимость: %s - %s мест",
                    capacityMin == null ? ".." : capacityMin,
                    capacityMax == null ? ".." : capacityMax);
        }

    }
}
