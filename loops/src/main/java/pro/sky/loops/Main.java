package pro.sky.loops;

import java.time.LocalDate;

public class Main {
    static final int CURRENT_YEAR = LocalDate.now().getYear();

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
        task2_1();
        task2_2();
        task2_3();
        task2_4();
        task2_5();
        task2_6();
        task2_7();
        task2_8();
    }

    private static void task1() {
        System.out.println("Задача 1");
        for (int i = 1; i <= 10; i = i + 1) {
            System.out.println(i);
        }
    }

    private static void task2() {
        System.out.println("\nЗадача 2");
        for (int i = 10; i > 0; i = i - 1) {
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
        for (int i = 1904; i <= 2096; i += 4) {
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
        for (int i = 1; i <= 512; i *= 2) {
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
            total = total + total / 100;
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

    private static void task2_1() {
        System.out.println("Задача 1");
        int deposit = 15000;
        int total = 0;
        int i = 0;
        int amountPurpose = 2_459_000;
        while (total < amountPurpose) {
            i++;
            total += (deposit + total / 100); // С учетом процентов, согласно задачи 9 прошлого урока
            System.out.println("Месяц " + i + " сумма накоплений равна " + total + " рублей");
        }
    }

    private static void task2_2() {
        System.out.println("\nЗадача 2");
        int i = 1;
        while (i <= 10) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();
        for (i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void task2_3() {
        System.out.println("\nЗадача 3");
        int populationCountry = 12_000_000;
        int fertilityPercent = 17;
        int mortalityPercent = 8;
        int year = 0;
        while (year < 10) {
            year++;
            int fertility = (populationCountry / 1000) * fertilityPercent;
            int mortality = (populationCountry / 1000) * mortalityPercent;
            populationCountry += (fertility - mortality);
            System.out.printf("Год %s, численность населения составляет %s%n", year, populationCountry);
        }
    }

    private static void task2_4() {
        System.out.println("\nЗадача 4");
        double depositAmount = 15000.00;
        int monthNumber = 1;
        int amountPurpose = 12_000_000;
        double montlyPercent = 0.07;
        while (depositAmount < amountPurpose) {
            depositAmount += (depositAmount * montlyPercent);
            System.out.printf("Месяц %s, сумма вклада %.2f%n", monthNumber, depositAmount);
            monthNumber++;
        }
    }

    private static void task2_5() {
        System.out.println("\nЗадача 5");
        double depositAmount = 15000.00;
        int monthNumber = 1;
        int amountPurpose = 12_000_000;
        double montlyPercent = 0.07;
        while (depositAmount < amountPurpose) {
            depositAmount += (depositAmount * montlyPercent);
            if (monthNumber % 6 == 0) {
                System.out.printf("Месяц %s, сумма вклада %.2f%n", monthNumber, depositAmount);
            }
            monthNumber++;
        }
    }

    private static void task2_6() {
        System.out.println("\nЗадача 6");
        double depositAmount = 15000.00;
        int monthNumber = 1;
        int yourNumber = 1;
        double montlyPercent = 0.07;
        while (yourNumber <= 9) {
            depositAmount += (depositAmount * montlyPercent);
            if (monthNumber % 6 == 0) {
                System.out.printf("Месяц %s, сумма вклада %.2f%n", monthNumber, depositAmount);
            }
            if (monthNumber % 12 == 0) {
                yourNumber++;
            }
            monthNumber++;
        }
    }

    private static void task2_7() {
        System.out.println("\nЗадача 7");
        for (int dayNumber = 2; dayNumber <= 31; dayNumber += 7) {
            System.out.println("Сегодня пятница, " + dayNumber + "-е число. Необходимо подготовить отчет");
        }
    }

    private static void task2_8() {
        System.out.println("\nЗадача 8");
        int startYear = CURRENT_YEAR - 200;
        int endYear = CURRENT_YEAR + 100;
        int cometPeriod = 79;
        for (int year = startYear; year < endYear; year++) {
            if (year % cometPeriod == 0) {
                System.out.println(year);
            }
        }
    }

}



