package pro.sky.oop.part4.transport;

import pro.sky.oop.part4.person.DriverCategoryD;

import java.util.Random;

public class Bus extends Transport<DriverCategoryD> {
    {
        transportName = "Автобус";
    }

    public Bus(String brand, String model, float engineVolume) {
        super(brand, model, engineVolume);
    }

    public Bus(String brand, String model, float engineVolume, int maxSpeed) {
        super(brand, model, engineVolume, maxSpeed);
    }

    @Override
    public boolean isPitStop() {
        return new Random().nextInt() < 0;
    }
}
