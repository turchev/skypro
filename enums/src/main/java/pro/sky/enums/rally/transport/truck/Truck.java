package pro.sky.enums.rally.transport.truck;

import pro.sky.enums.rally.person.DriverCategoryC;
import pro.sky.enums.rally.transport.Transport;
import pro.sky.enums.rally.transport.TransportType;

import java.util.Objects;

public class Truck extends Transport<DriverCategoryC> {
    {
        type = TransportType.TRUCK;
    }

    private WeightType weightType;

    public Truck(String brand, String model, DriverCategoryC driver, WeightType weightType) {
        super(brand, model, driver);
        this.weightType = weightType;
    }

    public Truck(String brand, String model, DriverCategoryC driver, Float weightValue) {
        super(brand, model, driver);
        this.weightType = WeightType.getTypeByValue(weightValue);
    }

    public Truck(String brand, String model) {
        super(brand, model);
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    @Override
    public String toString() {
        return (super.toString() + ", " + Objects.requireNonNullElse(getWeightType(), "")).trim();
    }

    public enum WeightType {
        N1(null, 3.5f),
        N2(3.5f, 12.0f),
        N3(12.0f, null);

        private final Float minValue;
        private final Float maxValue;

        // это не по заданию, пробовал по грузоподъемности определить тип
        static WeightType getTypeByValue(Float value) {
            for (WeightType weightType : WeightType.values()) {
                if (value > Objects.requireNonNullElse(weightType.minValue, 0.0f)
                        && value <= Objects.requireNonNullElse(weightType.maxValue, Float.MAX_VALUE)) {
                    return weightType;
                }
            }
            return null;
        }

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
