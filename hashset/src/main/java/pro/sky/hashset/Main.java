package pro.sky.hashset;

import pro.sky.hashset.rally.maintenance.MaintenanceStation;
import pro.sky.hashset.rally.person.CategoryType;
import pro.sky.hashset.rally.person.Driver;
import pro.sky.hashset.rally.person.Mechanic;
import pro.sky.hashset.rally.transport.bus.Bus;
import pro.sky.hashset.rally.transport.car.Car;
import pro.sky.hashset.rally.transport.truck.Truck;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Driver driverCatB = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatBC = new Driver("Паустовский К.Г.", 18, CategoryType.B, CategoryType.C);
        Driver driverCatC = new Driver("Достоевский Ф.М.", 12, CategoryType.C);
        Driver driverCatBCD = new Driver("Пушкин А.С.", 22, CategoryType.B, CategoryType.C, CategoryType.D);

        Mechanic<Car> mechanicVaz = new Mechanic<>("Толстой Л.Н.", "АО АВТОВАЗ");
        Mechanic<Car> mechanicVaz2 = new Mechanic<>("Тургенев И.С.", "АО АВТОВАЗ");
        Mechanic<Truck> mechanicMitsubishi = new Mechanic<>("Гумилёв Н.C.", "Mitsubishi");
        Mechanic<Truck> mechanicMitsubishi2 = new Mechanic<>("Ахматова А.А.", "Renault");
        Mechanic<Truck> mechanicMercedes = new Mechanic<>("Маршак С.Я.", "Mercedes-Benz");

        Car car = new Car("Lada", "Granta", driverCatB, Car.BodyType.MINIVAN);
        car.addMechanic(mechanicVaz);
        car.addMechanic(mechanicVaz2);
        Truck truck = new Truck("Mitsubishi", "Fuso Canter", driverCatBCD, Truck.WeightType.N3);
        truck.addMechanic(mechanicMitsubishi);
        truck.addMechanic(mechanicMitsubishi2);
        Bus bus = new Bus("Mercedes-Benz", "Citaro L", driverCatBCD, Bus.CapacityType.LARGE);
        bus.addMechanic(mechanicMercedes);

        // множество водителей с повторениями
        Set<Driver> drivers = new HashSet<>();
        drivers.add(driverCatB);
        drivers.add(driverCatBC);
        drivers.add(driverCatC);
        drivers.add(driverCatBCD);
        Driver driverCatB2 = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatB3 = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatB4 = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatB5 = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatB6 = new Driver("Есенин С.А.", 7, CategoryType.B);
        drivers.add(driverCatB2);
        drivers.add(driverCatB3);
        drivers.add(driverCatB4);
        drivers.add(driverCatB5);
        drivers.add(driverCatB6);


        // вывод всех водителей в консоль с помощью итератора
        Iterator<Driver> iterator = drivers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Дополнительное задание по очередям, взял у коллег из другого потока, так как у меня его не было в прошлом уроке
        System.out.println();
        MaintenanceStation station = new MaintenanceStation();
        station.addToInspectionQueue(car);
        station.addToInspectionQueue(truck);
        station.addToInspectionQueue(bus);

        int queueSize = station.getQueueSize();
        for (int i = 0; i < queueSize; i++) {
            station.carryDiagnostics();
        }
    }
}
