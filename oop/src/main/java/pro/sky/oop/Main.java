package pro.sky.oop;

import pro.sky.oop.part2.transport.Car;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Car car1 = new Car("Lada", "Granta", 1.7f, "желтый", 2015, "Россия",
                "Механическая 4-х ступенчатая", "Седан", "DF478JJ163RUS", 5);

        Car.Key keyNoOption = new Car.Key(false, false);
        Car car2 = new Car("Audi", "A8 50 L TDI quattro", 3.0f, "черный", 2020, "Германия",
                "Автоматическая 5-и ступенчатая", "Лифтбек", "DЕ448УК163RUS", 5,
                false, keyNoOption);

        Car car3 = new Car("BMW", "Z8", 3.0f, "черный", 2021, "Германия",
                "Автоматическая 6-и ступенчатая", "Хетчбэк", "АЕ438УК163RUS", 0,
                true, new Car.Key(true, true));

        Car car4 = new Car("", null, -1, null, -10, "",
                null, null, null, 0, true, null);
        Car car5 = new Car("Hyundai", "Avante", 1.6f, "оранжевый", 2016, "Южная Корея",
                " ", " ", " ", -34, false, null);

        Arrays.stream(new Car[]{car1, car2, car3, car4, car5})
                .forEach(System.out::println);
    }
}
