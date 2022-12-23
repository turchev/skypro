package pro.sky.coursework1;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final Employee[] employees;
    static int idCounter;

    static {
        idCounter = 1000; // Начальное значение id
        employees = new EmployService().employeeGenerate();
    }

    public static void main(String[] args) {

        System.out.println("Список всех сотрудников со всеми имеющимися по ним данными: ");
        Arrays.stream(employees).forEach(System.out::println);

        System.out.println("\nСумма затрат на зарплаты в месяц: " +
                Arrays.stream(employees).mapToDouble(Employee::getSalary).sum());

        Employee employeeWithMinSalary = Arrays.stream(employees)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        System.out.printf("%nCотрудник с минимальной зарплатой: %s%n",
                employeeWithMinSalary == null ? "Не найден" : employeeWithMinSalary);

        Employee employeeWithMaxSalary = Arrays.stream(employees)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        System.out.printf("%nСотрудник с максимальной зарплатой: %s%n",
                employeeWithMaxSalary == null ? "Не найден" : employeeWithMaxSalary);

//      Вот тут интересно, orElseThrow может бросить исключение или нет?
//      По сути, поле 'salary' класса 'Employee' в любом случае будет проинициализировано, там примитивный тип
        System.out.println("\nСреднее значение зарплат: " +
                Arrays.stream(employees).mapToDouble(Employee::getSalary).average().orElseThrow());

        System.out.println("\nФ. И. О. всех сотрудников:");
        Arrays.stream(employees)
                .forEach(employee -> System.out.printf("\t%s %s %s%n",
                        employee.getName() == null ? "" : employee.getName(),
                        employee.getPatronymic() == null ? "" : employee.getPatronymic(),
                        employee.getSurname() == null ? "" : employee.getSurname()));
    }
}
