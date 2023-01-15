package pro.sky.enums.rally.transport.bus;

import pro.sky.enums.rally.person.DriverCategoryD;
import pro.sky.enums.rally.transport.Transport;
import pro.sky.enums.rally.transport.TransportType;

import java.util.Objects;

public class Bus extends Transport<DriverCategoryD> {
    {
        type = TransportType.BUS;
    }

    private CapacityType capacityType;

    public Bus(String brand, String model, DriverCategoryD driver, CapacityType capacityType) {
        super(brand, model, driver);
        this.capacityType = capacityType;
    }

    public Bus(String brand, String model) {
        super(brand, model);
    }

    public CapacityType getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(CapacityType capacityType) {
        this.capacityType = capacityType;
    }

    @Override
    public String toString() {
        return (super.toString() + ", " + Objects.requireNonNullElse(getCapacityType(), "")).trim();
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
