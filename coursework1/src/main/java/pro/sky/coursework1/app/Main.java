package pro.sky.coursework1.app;

import pro.sky.coursework1.data.EmployeesData;
import pro.sky.coursework1.entity.Employee;
import pro.sky.coursework1.service.EmployeeBook;

import java.util.Arrays;
import java.util.Objects;

public class Main {
    private static final double TEST_INDEXATION_PERCENT = 10.00;
    private static final int ALL_DEPARTMENT = 0; // Все отделы (организация)
    private static final int TEST_DEPARTMENT = 5; // Отделы для тестирования программы от 1 до 5
    private static final double TEST_SALARY_LEVEL = 145_000.00; // Критерий для зарплаты

    public static void main(String[] args) {

        EmployeesData employeesData = EmployeesData.getInstance();

        EmployeeBook employeeBook = new EmployeeBook();
        EmployeeBook.PrintService printService = employeeBook.new PrintService();

        printService.addTitle("- Список всех сотрудников со всеми имеющимися по ним данными")
                .addBody(employeesData.getEmployees(), EmployeeBook.PrintType.ALL_EMPLOYEE).print();

        printService.clearBody().clearTitle().addTitle("- Сумма затрат организации на зарплаты в месяц")
                .addBody(employeeBook.calculateTotalSalaries(employeesData.getEmployees(), ALL_DEPARTMENT) + "\n")
                .print();

        printService.clearBody().clearTitle().addTitle("- Cотрудник с минимальной зарплатой")
                .addBody(employeeBook.getEmployeeWithMinSalary(employeesData.getEmployees(), ALL_DEPARTMENT) + "\n")
                .print()
                .clearBody().addTitle(" в отделе " + TEST_DEPARTMENT)
                .addBody(employeeBook.getEmployeeWithMinSalary(employeesData.getEmployees(), TEST_DEPARTMENT) + "\n")
                .print();

        printService.clearBody().clearTitle().addTitle("- Cотрудник с максимальной зарплатой")
                .addBody(employeeBook.getEmployeeWithMaxSalary(employeesData.getEmployees(), ALL_DEPARTMENT) + "\n")
                .print()
                .clearBody().addTitle(" в отделе " + TEST_DEPARTMENT)
                .addBody(employeeBook.getEmployeeWithMaxSalary(employeesData.getEmployees(), TEST_DEPARTMENT) + "\n")
                .print();

        printService.clearBody().clearTitle().addTitle("- Среднее значение зарплат")
                .addBody(employeeBook.calculateAvgSalary(employeesData.getEmployees(), ALL_DEPARTMENT) + "\n")
                .print()
                .clearBody().addTitle(" в отделе " + TEST_DEPARTMENT)
                .addBody(employeeBook.calculateAvgSalary(employeesData.getEmployees(), TEST_DEPARTMENT) + "\n")
                .print();

        printService.clearBody().clearTitle()
                .addTitle("- Ф. И. О. всех сотрудников")
                .addBody(employeesData.getEmployees(), EmployeeBook.PrintType.NICK_ONLY).print();

        printService.clearBody().clearTitle()
                .addTitle("- Проиндексируем зарплаты всех сотрудников на " + TEST_INDEXATION_PERCENT +
                        "% и выведем результат")
                .addBody(employeeBook.increaseSalary(employeesData.getEmployees(), TEST_INDEXATION_PERCENT),
                        EmployeeBook.PrintType.NICK_AND_SALARY).print();

        printService.clearBody().clearTitle()
                .addTitle("- Проиндексируем зарплаты всех сотрудников " + TEST_DEPARTMENT + " отдела на " +
                        TEST_INDEXATION_PERCENT + "% и выведем результат")
                .addBody(employeeBook.increaseSalary(employeesData.getEmployees(), TEST_INDEXATION_PERCENT, TEST_DEPARTMENT),
                        EmployeeBook.PrintType.NOT_DEPARTMENT, TEST_DEPARTMENT).print();

        printService.clearBody().clearTitle()
                .addTitle("- Сотрудники с зарплатой меньше " + TEST_SALARY_LEVEL)
                .addBody(employeeBook.getEmployeesWithSalaryLowerThan(employeesData.getEmployees(), TEST_SALARY_LEVEL),
                        EmployeeBook.PrintType.NOT_DEPARTMENT).print().clearBody().clearTitle()
                .addTitle("- Сотрудники с зарплатой больше " + TEST_SALARY_LEVEL)
                .addBody(employeeBook.getEmployeesWithSalaryGreaterThan(employeesData.getEmployees(), TEST_SALARY_LEVEL),
                        EmployeeBook.PrintType.NOT_DEPARTMENT).print();

        // Добавляем сотрудника
        boolean modifiedArray = employeesData.addEmployee(
                new Employee("Влади́мир", "Влади́мирович", "Маяко́вский", 89_000.00, 5));
        // Увеличился массив при этом или нет
        System.out.println("Массив увеличен:" + modifiedArray + "\n");

        // Удаляем сотрудника по id
        employeesData.delEmployee(1008); // id 1008 - "Донцо́ва Агриппи́на Арка́дьевна"

        // Изменяем ЗП и отдел с поиском по Ф.И.О
        Employee employee = employeesData.getEmployeeByNick("Толсто́й Лев Никола́евич");
        if (employee != null) {
            employee.setSalary(350_000.00);
            employee.setDepartment(5);
        }

        printService.clearTitle().clearBody().addTitle("- Посмотрим что получилось. Список всех сотрудников")
                .addBody(employeesData.getEmployees(), EmployeeBook.PrintType.ALL_EMPLOYEE).print();

        // Добавляем еще одного сотрудника и проверим измениться ли массив при этом
        modifiedArray = employeesData.addEmployee(
                new Employee("Алекса́ндр", "Серге́евич", "Грибое́дов", 129_000.00, 3));
        System.out.println("Массив увеличен:" + modifiedArray + "\n");

        printService.clearTitle().clearBody().addTitle("- Посмотрим что получилось. Список всех сотрудников")
                .addBody(employeesData.getEmployees(), EmployeeBook.PrintType.ALL_EMPLOYEE).print();

        printService.clearBody().clearTitle()
                .addTitle("- Ф. И. О. всех сотрудников")
                .addBody(employeesData.getEmployees(), EmployeeBook.PrintType.NOT_DEPARTMENT).print();

        //  Ф. И. О. всех сотрудников по отделам (список отделов и их сотрудников)
        Arrays.stream(employeesData.getEmployees())
                .filter(Objects::nonNull)
                .mapToInt(Employee::getDepartment)
                .distinct()
                .sorted()
                .forEach(d -> {
                    printService.clearBody().clearTitle().addTitle("Отдел №" + d);
                    printService.addBody(employeesData.getEmployees(), EmployeeBook.PrintType.NICK_ONLY, d).print();
                });

    }
}
