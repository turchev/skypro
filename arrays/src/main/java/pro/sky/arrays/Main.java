package pro.sky.arrays;

import java.util.Arrays;

public class Main {
    static {
        System.out.println("Задача 1. Объявление и инициализация массивов");
    }
    // Задача 1
    // 1.1
    private static final int[] intArray = new int[]{1, 2, 3};
    // 1.2
    private static final double[] doubleArray = {1.57, 7.654, 9.986};
    // 1.3
    private static final char[] charArray = {72, 101, 108, 108, 111, 32, 109, 101, 110, 116, 111, 114, 33};

    public static void main(String[] args) {
        task2();
        task3();
        task4();
    }

    // Задача 2
    private static void task2() {
        System.out.println("Задача 2");
        // 2.1
        System.out.println(intArray[0] + ", " + intArray[1] + ", " + intArray[2]);
        // 2.2
        for (int i = 0; i < doubleArray.length; i++) {
            if (i == doubleArray.length - 1) {
                System.out.println(doubleArray[i]);
                break;
            }
            System.out.print(doubleArray[i] + ", ");
        }
        // 2.3
        System.out.println(Arrays.toString(charArray).replaceAll("^\\[|\\]$", ""));
    }

    // Задача 3
    private static void task3() {
        System.out.println("\nЗадача 3");
        // 3.1
        for (int i = intArray.length - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.println(intArray[i]);
                break;
            }
            System.out.print(intArray[i] + ", ");
        }
        // 3.2
        for (int i = doubleArray.length - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.println(doubleArray[i]);
                break;
            }
            System.out.print(doubleArray[i] + ", ");
        }
        // 3.3
        String stringFromCharArray = Arrays.toString(charArray);
        String replacedString = new StringBuffer(stringFromCharArray).reverse().toString()
                .replaceAll("^\\]|\\[$", "");
        System.out.println(replacedString.replaceAll(" ,", ", "));
    }

    // Задача 4
    private static void task4() {
        System.out.println("\nЗадача 4");
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] % 2 != 0) {
                intArray[i] ++;
            }
        }
        System.out.println(Arrays.toString(intArray));
    }

}
