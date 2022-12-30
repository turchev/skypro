package pro.sky.oop;

import pro.sky.oop.part1.Car;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nЗадание 1");

        Car car1 = new Car();
        car1.setBrand("Lada");
        car1.setModel("Granta");
        car1.setColor("желтый");
        car1.setEngineVolume(1.7f);
        car1.setYear(2015);
        car1.setCountry("Россия");

        Car car2 = new Car();
        car2.setBrand("Audi");
        car2.setModel("A8 50 L TDI quattro");
        car2.setColor("черный");
        car2.setEngineVolume(3.0f);
        car2.setYear(2020);
        car2.setCountry("Германия");

        Car car3 = new Car();
        car3.setBrand("BMW");
        car3.setModel("Z8");
        car3.setColor("черный");
        car3.setEngineVolume(3.0f);
        car3.setYear(2021);
        car3.setCountry("Германия");

        Car car4 = new Car();
        car4.setBrand("Kia");
        car4.setModel("Sportage 4-го поколения");
        car4.setColor("красный");
        car4.setEngineVolume(2.4f);
        car4.setYear(2018);
        car4.setCountry("Южная Корея");

        Car car5 = new Car();
        car5.setBrand("Hyundai");
        car5.setModel("Avante");
        car5.setColor("оранжевый");
        car5.setEngineVolume(1.6f);
        car5.setYear(2016);
        car5.setCountry("Южная Корея");

        Arrays.stream(new Car[]{car1, car2, car3, car4, car5})
                .forEach(System.out::println);

        System.out.println("\nЗадание 2");

        Car car2_1 = new Car( "Lada",  "Granta",  1.7f,  "желтый",  2015,  "Россия");
        Car car2_2 = new Car( "Audi",  "A8 50 L TDI quattro",  3.0f,  "черный",  2020,  "Германия");
        Car car2_3 = new Car( "BMW",  "Z8",  3.0f,  "черный",  2021,  "Германия");
        Car car2_4 = new Car( "Kia",  "Sportage 4-го поколения",  2.4f,  "красный",  2018,  "Южная Корея");
        Car car2_5 = new Car( "Hyundai",  "Avante",  1.6f,  "оранжевый",  2016,  "Южная Корея");

        Arrays.stream(new Car[]{car2_1, car2_2, car2_3, car2_4, car2_5})
                .forEach(System.out::println);

        System.out.println("\nЗадание 2");

        Car car3_1 = new Car();
        Car car3_2 = new Car( "Audi",  "A8 50 L TDI quattro");
        Car car3_3 = new Car( "BMW",  "Z8",  3.0f);
        Car car3_4 = new Car( "Kia",  "Sportage 4-го поколения",  2.4f);
        Car car3_5 = new Car( "Hyundai",  "Avante",  1.6f,  "оранжевый");
        Car car3_6 = new Car( "Lada",  "Vesta",  1.9f,  "красный",  2022,  "Россия");
        Car car3_7 = new Car( "Hyundai",  "Avante",  1.6f,  "белый",  2021,  "Южная Корея");

        Arrays.stream(new Car[]{car3_1, car3_2, car3_3, car3_4, car3_5, car3_6, car3_7} )
                .forEach(System.out::println);
    }
}
