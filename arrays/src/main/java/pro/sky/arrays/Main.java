package pro.sky.arrays;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static {
        System.out.println("Массивы. Части 1 и 2");
        System.out.println("\nЧасть 1");
        System.out.println("\nЗадача 1. Объявление и инициализация массивов");
        expensesPerMonth = generateRandomArray();
    }
    // Задача 1
    // 1.1
    private static final int[] intArray = new int[]{1, 2, 3};
    // 1.2
    private static final double[] doubleArray = {1.57, 7.654, 9.986};
    // 1.3
    private static final char[] charArray = {72, 101, 108, 108, 111, 32, 109, 101, 110, 116, 111, 114, 33};
    // Задача 2
    private final static int[] expensesPerMonth;

    public static void main(String[] args) {
        task2();
        task3();
        task4();
        System.out.println("\nЧасть 2\n");
        task2_1();
        task2_2();
        task2_3();
        task2_4();
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

    // Задача 2.1
    private static void task2_1() {
        System.out.println("Задача 1");
        int totalExpenses = 0;
        for (int expensesPerDay : expensesPerMonth) {
            totalExpenses += expensesPerDay;
        }
        System.out.println("Сумма трат за месяц составила " + totalExpenses + " рублей");
    }

    // Задача 2.2
    private static void task2_2() {
        System.out.println("\nЗадача 2");

        // вариант решения 1
        System.out.println("  Вариант 1");
        int expensesPerDayMax = Integer.MIN_VALUE;
        int expensesPerDayMin = Integer.MAX_VALUE;
        for (int expensesPerDay : expensesPerMonth) {
            if (expensesPerDayMax < expensesPerDay) {
                expensesPerDayMax = expensesPerDay;
            }
            if (expensesPerDayMin > expensesPerDay) {
                expensesPerDayMin = expensesPerDay;
            }
        }
        System.out.println("Минимальная сумма трат за день составила " + expensesPerDayMin + " рублей");
        System.out.println("Максимальная сумма трат за день составила " + expensesPerDayMax + " рублей");

        // вариант решения 2
        System.out.println("  Вариант 2");
        System.out.printf("Минимальная сумма трат за день составила %d рублей%n",
                Arrays.stream(expensesPerMonth).min().orElse(-1));
        System.out.printf("Максимальная сумма трат за день составила %d рублей%n",
                Arrays.stream(expensesPerMonth).max().orElse(-1));
    }

    // Задача 2.3
    private static void task2_3() {
        System.out.println("\nЗадача 3");

        // вариант решения 1
        System.out.println("  Вариант 1");
        double totalExpenses = 0;
        for (int expensesPerDay : expensesPerMonth) {
            totalExpenses += expensesPerDay;
        }
        System.out.printf("Средняя сумма трат за месяц составила %.2f рублей%n",
                totalExpenses / expensesPerMonth.length);

        // вариант решения 2
        System.out.println("  Вариант 2");
        System.out.printf("Средняя сумма трат за месяц составила %.2f рублей%n",
                Arrays.stream(expensesPerMonth).average().orElse(-1.0));
    }

    // Задача 2.4
    private static void task2_4() {
        System.out.println("\nЗадача 4");
        char[] reverseFullName = {'n', 'a', 'v', 'I', ' ', 'v', 'o', 'n', 'a', 'v', 'I'};

        // вариант решения 1
        System.out.println("  Вариант 1");
        for (int i = 0; i < reverseFullName.length; i++) {
            System.out.print(reverseFullName[reverseFullName.length - 1 - i]);
        }
        System.out.println();

        // вариант решения 2
        System.out.println("  Вариант 2");
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(reverseFullName).forEach(stringBuilder::append);
        System.out.println(stringBuilder.reverse());
    }

    private static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }
}
