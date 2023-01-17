package pro.sky.exceptions;

import pro.sky.exceptions.rally.exeption.IllegalDrivingLicenseException;
import pro.sky.exceptions.rally.person.CategoryType;
import pro.sky.exceptions.rally.person.Driver;
import pro.sky.exceptions.rally.transport.Transport;
import pro.sky.exceptions.rally.transport.bus.Bus;
import pro.sky.exceptions.rally.transport.car.Car;
import pro.sky.exceptions.rally.transport.truck.Truck;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задание 1, 2 \n");

        Driver driverCat_B = new Driver("Есенин С.А.", 7, CategoryType.B);
        Driver driverCat_B_C = new Driver("Паустовский К.Г.", 18, CategoryType.B, CategoryType.C);
        Driver driverCat_C_D = new Driver("Толстой Л.Н.", 28, CategoryType.C, CategoryType.D);
        Driver driverCat_B_D = new Driver("Тургенев И.С.", 19, CategoryType.B, CategoryType.D);
        Driver driverCat_C = new Driver("Достоевский Ф.М.", 12, CategoryType.C);
        Driver driverCat_B_C_D = new Driver("Пушкин А.С.", 22, CategoryType.B, CategoryType.C, CategoryType.D);

        Car car1 = new Car("Lada", "Granta", driverCat_B, Car.BodyType.MINIVAN);
        Car car2 = new Car("Audi", "A8 50 L TDI quattro", driverCat_B_C, Car.BodyType.COUPE);

        Bus bus1 = new Bus("Mercedes-Benz", "Citaro L", driverCat_C_D, Bus.CapacityType.MEDIUM);
        Bus bus2 = new Bus("ISUZU", "Gala", driverCat_B_D, Bus.CapacityType.VERY_SMALL);

        Truck truck1 = new Truck("Renault", "D-Truck Wide", driverCat_C, Truck.WeightType.N1);
        Truck truck2 = new Truck("Mitsubishi", "Fuso Canter", driverCat_B_C_D, Truck.WeightType.N3);

        Arrays.stream(new Transport[]{car1, car2, bus1, bus2, truck1, truck2})
                .forEach(t -> {
                    System.out.println(t.getDriver());
                    t.printType();
                    System.out.println(t);
                    try {
                        t.passDiagnostics();
                    } catch (UnsupportedOperationException e) {
                        System.out.println("Операция не поддерживается - " + e.getMessage());
                    }
                    System.out.println();
                });

        System.out.println("Задание 3, 4 \n");

        // null водитель
        Car car3 = null;
        try {
            car3 = new Car("Lada", "Vesta", null, Car.BodyType.SEDAN);
        } catch (IllegalDrivingLicenseException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(car3);
        }

        // водитель без категории
        System.out.println();
        Driver driverNotCat = new Driver("Стажер Ч.Ч.", 0);
        try {
            car3 = new Car("Lada", "Vesta", driverNotCat, Car.BodyType.SEDAN);
        } catch (IllegalDrivingLicenseException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(car3);
        }

        // добавим необходимую категорию водителю и проверим
        System.out.println();
        driverNotCat.setDrivingLicense(CategoryType.B);
        try {
            car1.setDriver(driverNotCat);
        } catch (IllegalDrivingLicenseException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(car1.getDriver() + "\n" + car1);
        }
    }
}
