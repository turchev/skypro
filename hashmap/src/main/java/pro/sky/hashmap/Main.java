package pro.sky.hashmap;

import pro.sky.hashmap.rally.person.CategoryType;
import pro.sky.hashmap.rally.person.Driver;
import pro.sky.hashmap.rally.person.Mechanic;
import pro.sky.hashmap.rally.transport.Transport;
import pro.sky.hashmap.rally.transport.bus.Bus;
import pro.sky.hashmap.rally.transport.car.Car;
import pro.sky.hashmap.rally.transport.truck.Truck;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Driver driverCatB = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatBC = new Driver("Паустовский К.Г.", 18, CategoryType.B, CategoryType.C);
        Driver driverCatC = new Driver("Достоевский Ф.М.", 12, CategoryType.C);
        Driver driverCatBCD = new Driver("Пушкин А.С.", 22, CategoryType.B, CategoryType.C, CategoryType.D);

        Mechanic<Car> mechanicVaz = new Mechanic<>("Толстой Л.Н.", "АО АВТОВАЗ");
        Mechanic<Car> mechanicVaz2 = new Mechanic<>("Тургенев И.С.", "АО АВТОВАЗ");
        Mechanic<Car> mechanicVaz3 = new Mechanic<>("Тургенев И.С.", "АО АВТОВАЗ");
        Mechanic<Truck> mechanicMitsubishi = new Mechanic<>("Гумилёв Н.C.", "Mitsubishi");
        Mechanic<Truck> mechanicMitsubishi2 = new Mechanic<>("Ахматова А.А.", "Renault");
        Mechanic<Truck> mechanicMitsubishi3 = new Mechanic<>("Гумилёв Н.C.", "Mitsubishi");
        Mechanic<Truck> mechanicMercedes = new Mechanic<>("Маршак С.Я.", "Mercedes-Benz");

        Car car = new Car("Lada", "Granta", driverCatB, Car.BodyType.MINIVAN);
        car.addMechanic(mechanicVaz);
        car.addMechanic(mechanicVaz2);
        Car car2 = new Car("Lada", "Granta", driverCatB, Car.BodyType.MINIVAN);
        car2.addMechanic(mechanicVaz3);
        Car car3 = new Car("Lada", "Granta", driverCatB, Car.BodyType.MINIVAN);
        car3.addMechanic(mechanicVaz2);
        Truck truck = new Truck("Mitsubishi", "Fuso Canter", driverCatBCD, Truck.WeightType.N3);
        truck.addMechanic(mechanicMitsubishi);
        truck.addMechanic(mechanicMitsubishi2);
        Truck truck2= new Truck("Mitsubishi", "Fuso Canter", driverCatBCD, Truck.WeightType.N3);
        truck.addMechanic(mechanicMitsubishi);
        Truck truck3 = new Truck("Mitsubishi", "Fuso Canter", driverCatBCD, Truck.WeightType.N2);
        truck.addMechanic(mechanicMitsubishi3);
        Bus bus = new Bus("Mercedes-Benz", "Citaro L", driverCatBCD, Bus.CapacityType.LARGE);
        bus.addMechanic(mechanicMercedes);
        Bus bus2 = new Bus("Mercedes-Benz", "Citaro L", driverCatBCD, Bus.CapacityType.VERY_SMALL);
        bus2.addMechanic(mechanicMercedes);

        /**
         * ТЗ:
         * 1. Трансформируйте список механиков в map, где в качестве ключа будет автомобиль,
         * а в качестве значения — механик, который его обслуживает.
         * 2. Перепишите приложение: если в случае ошибки какой-либо из объектов будет занесен в базу два раза,
         * то в консоль выведется информация без повторов.
         *
         * P/S:
         * 1. У нас механиков может быть несколько, поэтому беру первого по индексу.
         * 2. Надеюсь я правильно понял что без повторов касается только ключей мапы, а не значения?
         * По условиям из прежних заданий - один механик мог обслуживать несколько автомобилей,
         * соответственно повторы механиков допустимы на разных транспортных средствах.
         * Ну во всяком случае я от этого отталкивался.
         */

        Map<Transport, Mechanic<?>> mechanicMap = new HashMap<>();
        Set<Transport> transportSet = new HashSet<>(Set.copyOf(
                List.of(car, car2, car3, truck, truck2, truck3, bus, bus2)));
        transportSet.forEach(t -> {
            if (t.getMechanics().isEmpty()) {
                return;
            }
            Mechanic<?> mechanic = t.getMechanics().get(0);
            mechanicMap.put(t, mechanic);
        });

        mechanicMap.forEach((t, m) -> {
            System.out.println(t + ";  " + m);
        });
    }
}
