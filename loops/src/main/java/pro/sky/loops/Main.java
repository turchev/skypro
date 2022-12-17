package pro.sky.loops;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
    }

    private static void task1() {
        System.out.println("Задача 1");
        for (int i = 1; i <= 10; i = i + 1) {
            System.out.println(i);
        }
    }

    private static void task2() {
        System.out.println("\nЗадача 2");
        for (int i = 10; i > 0 ; i = i - 1) {
            System.out.println(i);
        }
    }

    private static void task3() {
        System.out.println("\nЗадача 3");
        for (int i = 0; i < 17; i = i + 2) {
            System.out.println(i);
        }
    }

    private static void task4() {
        System.out.println("\nЗадача 4");
        for (int i = 10; i >= -10; i = i - 1) {
            System.out.println(i);
        }
    }

    private static void task5() {
        System.out.println("\nЗадача 5");
        for (int i = 1904; i <= 2096 ; i += 4) {
            System.out.println(i + " год является високосным");
        }
    }

    private static void task6() {
        System.out.println("\nЗадача 6");
        for (int i = 7; i < 100; i += 7) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void task7() {
        System.out.println("\nЗадача 7");
        for (int i = 1; i <= 512 ; i *= 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void task8() {
        System.out.println("\nЗадача 8");
        int deposit = 29000;
        int total = 0;
        for (int i = 1; i <= 12; i++) {
            total += deposit;
            System.out.println("Месяц " + i + " сумма накоплений равна " + total);
        }
    }

    private static void task9() {
        System.out.println("\nЗадача 9");
        int deposit = 29000;
        int total = 0;
        for (int i = 1; i <= 12; i++) {
            total = total + total/100;
            total += deposit;
            System.out.println("Месяц " + i + " сумма накоплений равна " + total);
        }
    }

    private static void task10() {
        System.out.println("\nЗадача 10");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("2*%s=%s%n", i, i * 2);
        }
    }

}