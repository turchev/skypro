package pro.sky.oop;

import pro.sky.oop.part2.transport.Bus;
import pro.sky.oop.part2.transport.Car;
import pro.sky.oop.part2.transport.Transport;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // разные варианты:

        Car car1 = new Car("Lada", "Granta", 2015, "Россия", "желтый",
                180, "Седан", 5, 1.7f, "Механическая 4-х ступенчатая",
                "DF478JJ163RUS", true, null);

        Car car2 = new Car("Audi", "A8 50 L TDI quattro", 2020, "Германия", "черный",
                220, "Лифтбек", 5);
        car2.setEngineVolume(3.0f);
        car2.setGearbox("Автоматическая 5-и ступенчатая");
        car2.setLicensePlate("DЕ448УК163RUS");
        car2.setWinterTires(false);
        Car.Key keyNoOption = new Car.Key(false, false);
        car2.setKey(keyNoOption);

        Car car3 = new Car("BMW", "Z8", 2021, "Германия", "черный",
                240, "Хетчбэк", 0, 3.0f, "Автоматическая 6-и ступенчатая",
                "АЕ438УК163RUS", true,
                new Car.Key(true, true));

        Car car4 = new Car("", null, -1, null, null, -1, null, 0, -1,
                null, null, true, null);

        Car car5 = new Car("Hyundai", "Avante", 2016, "Южная Корея", "оранжевый",
                200, " ", -34, 1.6f, " ", " ", false, null);

        Bus bus1 = new Bus("Mercedes-Benz", "Citaro L", 2008, "Германия", "белый", 159);
        Bus bus2 = new Bus("ISUZU", "Gala", 2012, "Япония", "красный", 120);
        Bus bus3 = new Bus("НЕФАЗ", "52998", 2010, "Россия", "синий", 130);

        Arrays.stream(new Transport[]{car1, car2, car3, car4, car5, bus1, bus2, bus3})
                .forEach(System.out::println);
    }
}
