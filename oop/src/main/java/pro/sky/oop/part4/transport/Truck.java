package pro.sky.oop.part4.transport;

import pro.sky.oop.part4.person.DriverCategoryC;

public class Truck extends Transport<DriverCategoryC> {
    {
        transportName = "Грузовой автомобиль";
    }

    public Truck(String brand, String model, float engineVolume) {
        super(brand, model, engineVolume);
    }

    public Truck(String brand, String model, float engineVolume, int maxSpeed) {
        super(brand, model, engineVolume, maxSpeed);
    }

    @Override
    public boolean isPitStop() {
        return false;
    }
}
