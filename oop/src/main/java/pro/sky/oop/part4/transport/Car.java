package pro.sky.oop.part4.transport;

import pro.sky.oop.part4.person.DriverCategoryB;

import java.util.Random;

public class Car extends Transport<DriverCategoryB> {
    {
        transportName = "Легковой автомобиль";
    }

    public Car(String brand, String model, float engineVolume) {
        super(brand, model, engineVolume);
    }

    public Car(String brand, String model, float engineVolume, int maxSpeed) {
        super(brand, model, engineVolume, maxSpeed);
    }

    @Override
    public boolean isPitStop() {
        return new Random().nextBoolean();
    }
}
