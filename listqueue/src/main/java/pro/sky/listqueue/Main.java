package pro.sky.listqueue;

import pro.sky.listqueue.rally.person.CategoryType;
import pro.sky.listqueue.rally.person.Driver;
import pro.sky.listqueue.rally.person.Mechanic;
import pro.sky.listqueue.rally.transport.Transport;
import pro.sky.listqueue.rally.transport.car.Car;
import pro.sky.listqueue.rally.transport.truck.Truck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Driver driverCatB = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCatBC = new Driver("Паустовский К.Г.", 18, CategoryType.B, CategoryType.C);
        Driver driverCatC = new Driver("Достоевский Ф.М.", 12, CategoryType.C);
        Driver driverCatBCD = new Driver("Пушкин А.С.", 22, CategoryType.B, CategoryType.C, CategoryType.D);

        Mechanic mechanicVaz = new Mechanic("Толстой Л.Н.", "АО АВТОВАЗ");
        Mechanic mechanicVaz2 = new Mechanic("Тургенев И.С.", "АО АВТОВАЗ");
        Mechanic mechanicAudi = new Mechanic("Тургенев И.С.", "Audi");
        Mechanic mechanicAudi2 = new Mechanic("Барто А.Л.", "Audi");
        Mechanic mechanicRenault = new Mechanic("Ахматова А.А.", "Renault");
        Mechanic mechanicMitsubishi = new Mechanic("Гумилёв Н.C.", "Mitsubishi");
        Mechanic mechanicMitsubishi2 = new Mechanic("Маршак С.Я.", "Mitsubishi");

        Car car1 = new Car("Lada", "Granta", driverCatB, Car.BodyType.MINIVAN);
        car1.addMechanic(mechanicVaz);
        car1.addMechanic(mechanicVaz2);
        Car car2 = new Car("Audi", "A8 50 L TDI quattro", driverCatBC, Car.BodyType.COUPE);
        car2.addMechanic(mechanicAudi);
        car2.addMechanic(mechanicAudi2);
        Truck truck1 = new Truck("Renault", "D-Truck Wide", driverCatC, Truck.WeightType.N1);
        truck1.addMechanic(mechanicRenault);
        Truck truck2 = new Truck("Mitsubishi", "Fuso Canter", driverCatBCD, Truck.WeightType.N3);
        truck2.addMechanic(mechanicMitsubishi);
        truck2.addMechanic(mechanicMitsubishi2);

        // Создан список всех автомобилей с помощью List.
        List<Transport> automobiles = Arrays.asList(car1, car2, truck1, truck2);

        // Создан список водителей с помощью List.
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driverCatB);
        drivers.add(driverCatBC);
        drivers.add(driverCatC);
        drivers.add(driverCatBCD);

        // "Программа", с помощью которой можно узнать, как зовут водителя авто, какие механики у нее есть
        automobiles.forEach(t -> {
            System.out.printf("%s, Водитель: %s, Механики: ",
                    t, t.getDriver().getNick());
            if (t.getMechanics() != null) {
                t.getMechanics().forEach(m -> System.out.print(m.getNick() + " "));
            }
            System.out.println();
        });
    }
}
