package pro.sky.string;

public class Main {
    private static final String FULL_NAME_TITLE = "ФИО сотрудника";
    private static final String FULL_NAME_FOR_REPORT_TITLE = "Данные ФИО сотрудника для заполнения отчета";
    private static final String FULL_NAME_FOR_DATA_TITLE = "Данные ФИО сотрудника";

    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    // Задача 1
    private static void task1() {
        System.out.println("\nЗадача 1");
        // Вариант 1, в рамках тех задания
        System.out.println("  Вариант 1");
        String lastName = "Ivanov";
        String firstName = "Ivan";
        String middleName = "Ivanovich";
        String fullName = lastName + " " + firstName + " " + middleName;
        System.out.println(FULL_NAME_TITLE + " — " + fullName);

        // Вариант 2, не совсем по ТЗ
        System.out.println("  Вариант 2");
        System.out.println(FULL_NAME_TITLE + " — " +
                getFullName("Ivanov", "Ivan", "Ivanovich"));
    }

    // Задача 2
    private static void task2() {
        System.out.println("\nЗадача 2");
        System.out.println(FULL_NAME_FOR_REPORT_TITLE + " - " +
                getFullName("Ivanov", "Ivan", "Ivanovich").toUpperCase());
    }

    // Задача 3
    private static void task3() {
        System.out.println("\nЗадача 3");
        String fullName = getFullName("Иванов", "Семён", "Семёнович");
        System.out.println(FULL_NAME_FOR_DATA_TITLE + " - " + fullName.replace("ё", "е"));
    }

    private static String getFullName(String lastName, String firstName, String middleName) {
        return String.format("%s%s%s",
                lastName == null ? "" : lastName + " ",
                firstName == null ? "" : firstName + " ",
                middleName == null ? "" : middleName);
    }
}
