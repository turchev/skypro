package pro.sky.enums.rally;

import pro.sky.enums.rally.person.DriverCategoryB;
import pro.sky.enums.rally.person.DriverCategoryC;
import pro.sky.enums.rally.person.DriverCategoryD;
import pro.sky.enums.rally.transport.Transport;
import pro.sky.enums.rally.transport.bus.Bus;
import pro.sky.enums.rally.transport.car.Car;
import pro.sky.enums.rally.transport.truck.Truck;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        DriverCategoryB driverCatB_1 = new DriverCategoryB("Пушкин А.С.", true, 10);
        DriverCategoryB driverCatB_2 = new DriverCategoryB("Есенин С.А.", true, 7);
        DriverCategoryC driverCatC_1 = new DriverCategoryC("Толстой Л.Н.", true, 28);
        DriverCategoryC driverCatC_2 = new DriverCategoryC("Достоевский Ф.М.", true, 22);
        DriverCategoryD driverCatD_1 = new DriverCategoryD("Паустовский К.Г.", true, 18);
        DriverCategoryD driverCatD_2 = new DriverCategoryD("Тургенев И.С.", true, 19);

        Car car1 = new Car("Lada", "Granta", driverCatB_1, Car.BodyType.MINIVAN);
        Car car2 = new Car("Audi", "A8 50 L TDI quattro", driverCatB_2, Car.BodyType.COUPE);

        Bus bus1 = new Bus("Mercedes-Benz", "Citaro L");
        bus1.setCapacityType(Bus.CapacityType.MEDIUM);
        bus1.setDriver(driverCatD_1);
        Bus bus2 = new Bus("ISUZU", "Gala", driverCatD_2, Bus.CapacityType.VERY_SMALL);

        Truck truck1 = new Truck("Renault", "D-Truck Wide", driverCatC_1, Truck.WeightType.N1);
        Truck truck2 = new Truck("Mitsubishi", "Fuso Canter");
        truck2.setDriver(driverCatC_2);
        truck2.setType(null);
        Truck truck3 = new Truck("КрАЗ", "255Б", driverCatC_1, -158.0f);
        Truck truck4 = new Truck("КамАЗ", "5460-046-22", driverCatC_2, 10.5f);

        Arrays.stream(new Transport[]{car1, car2, bus1, bus2, truck1, truck2, truck3, truck4})
                .forEach(t -> {
                    System.out.println(t.getDriver());
                    t.printType();
                    System.out.println(t + "\n");
                });
    }
}
