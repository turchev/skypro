package pro.sky.oop.part4;

import pro.sky.oop.part4.person.DriverCategoryB;
import pro.sky.oop.part4.person.DriverCategoryC;
import pro.sky.oop.part4.person.DriverCategoryD;
import pro.sky.oop.part4.transport.Bus;
import pro.sky.oop.part4.transport.Car;
import pro.sky.oop.part4.transport.Transport;
import pro.sky.oop.part4.transport.Truck;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("\nЗадание 1");

        Car car1 = new Car("Lada", "Granta", 1.7f);
        Car car2 = new Car("Audi", "A8 50 L TDI quattro", 3.0f, 200);
        Car car3 = new Car("BMW", "Z8", 3.0f, 220);
        Car car4 = new Car("Hyundai", "Avante", 1.6f, 185);

        Bus bus1 = new Bus("Mercedes-Benz", "Citaro L", 11.98f);
        Bus bus2 = new Bus("ISUZU", "Gala", 9.0f, 120);
        Bus bus3 = new Bus("НЕФАЗ", "52998", 3.9f, 110);
        Bus bus4 = new Bus("Ikarus", "250", 10.3f, 140);

        Truck truck1 = new Truck("Renault", "D-Truck Wide", 7.7f, 130);
        Truck truck2 = new Truck("Mitsubishi", "Fuso Canter", 3.0f, 125);
        Truck truck3 = new Truck("КрАЗ", "255Б", 14.9f, 90);
        Truck truck4 = new Truck("КамАЗ", "5460-046-22", 11.7f);

        Transport[] transports = new Transport[]{car1, car2, car3, car4, bus1, bus2, bus3, bus4, truck1, truck2, truck3, truck4};
        System.out.println("\nСтарт");
        Arrays.stream(transports).forEach(Transport::startDriving);
        System.out.println("\nФиниш");
        Arrays.stream(transports).forEach(Transport::finishDriving);

        System.out.println("\nЗадание 2,3");
        Arrays.stream(transports)
                .forEach(t -> {
                    System.out.println(t + ":");
                    System.out.printf("\tЗаехал на пит-стоп - %s%n", t.isPitStop() ? "Да" : "Нет");
                    System.out.printf("\tЛучшее время -  %.2f мин%n", t.getBestLapTime());
                    System.out.println("\tМаксимальная скорость - " + t.getMaxSpeed() + " км/ч");
                });

        System.out.println("\nЗадание 4");
        DriverCategoryB driverCatB_1 = new DriverCategoryB("Пушкин А.С.", true, 10);
        DriverCategoryB driverCatB_2 = new DriverCategoryB("Есенин С.А.", true, 7);
        DriverCategoryC driverCatC_1 = new DriverCategoryC("Толстой Л.Н.", true, 28);
        DriverCategoryC driverCatC_2 = new DriverCategoryC("Достоевский Ф.М.", true, 22);
        DriverCategoryD driverCatD_1 = new DriverCategoryD("Паустовский К.Г.", true, 18);
        DriverCategoryD driverCatD_2 = new DriverCategoryD("Тургенев И.С.", true, 19);

        car1.setDriver(driverCatB_1);
        car2.setDriver(driverCatB_2);
        truck1.setDriver(driverCatC_1);
        truck2.setDriver(driverCatC_2);
        bus1.setDriver(driverCatD_1);
        bus2.setDriver(driverCatD_2);

        Arrays.stream(new Transport[]{car1, car2, bus1, bus2, truck1, truck2})
                .forEach(t -> System.out.printf("%s управляет %s и будет участвовать в заезде%n",
                        t.getDriver(), t));
    }
}
