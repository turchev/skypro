package pro.sky.methods;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1");
        int year = 1696;
        printYearInformation(year);

        // Задача 2
        System.out.println("\nЗадача 2");
        int clientDeviceYear = 2022;
        int clientOS = 0; //  0 — iOS, 1 — Android
        printRequiredVersionApplication(clientDeviceYear, clientOS);

        // Задача 3
        System.out.println("\nЗадача 3");
        int deliveryDistance = 95;
        try {
            System.out.println("Потребуется дней: " + calculateDeliveryTime(deliveryDistance) + " срок доставки");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printYearInformation(int year) {
        if (year <= 0) {
            System.out.println("Год не может быть отрицательным числом или нулем");
            return;
        }
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " год - високосный год");
        } else {
            System.out.println(year + " год - невисокосный год");
        }
    }

    private static void printRequiredVersionApplication(int clientDeviceYear, int clientOS) {
        if (clientOS != 0 && clientOS != 1) {
            System.out.println("Корректные параметры clientOS: 0 — iOS, 1 — Android");
            return;
        }
        int currentYear = LocalDate.now().getYear();
        if (clientDeviceYear <= 0 || clientDeviceYear > currentYear) {
            System.out.println("Год не может быть отрицательным числом, нулем и больше текущего");
            return;
        }
        if (clientOS == 0 && clientDeviceYear == currentYear) {
            System.out.println("Установите версию приложения для iOS по ссылке");
        } else if (clientOS == 0) {
            System.out.println("Установите облегченную версию приложения для iOS по ссылке");
        } else if (clientDeviceYear == currentYear) {
            System.out.println("Установите версию приложения для Android по ссылке");
        } else {
            System.out.println("Установите облегченную версию приложения для Android по ссылке");
        }
    }

    private static int calculateDeliveryTime(int deliveryDistance) {
        if (deliveryDistance < 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным числом");
        } else if (deliveryDistance == 0) {
            throw new IllegalArgumentException("Расстояние не может равняться 0");
        }
        int tmpValue = 0;
        int deliveryDay = 0;
        if (deliveryDistance <= 20) {
            return 1;
        }
        tmpValue = deliveryDistance - 20;
        deliveryDay = (tmpValue / 40) + 1;
        if (tmpValue % 40 != 0) {
            deliveryDay += 1;
        }
        return deliveryDay;
    }

}
