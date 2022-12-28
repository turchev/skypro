package pro.sky.coursework1.data;

import pro.sky.coursework1.entity.Employee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс-заглушка, имитирующий источник данных
 * // TODO: 28.12.2022 сделать случайный забор данных с глобальной сети (API WiKi..)
 */
final class EmployeesDataStub {
    static final int ARRAY_SIZE = 10; // Начальный размер массива
    static final int ARRAY_INCREMENT_DELTA = 4; // Величина, на которую увеличивается массив
    static Employee[] employees;

    private EmployeesDataStub() {
        initEmployees();
    }

    public static EmployeesDataStub getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private void initEmployees() {
        employees = new Employee[ARRAY_SIZE];
        employees[0] = new Employee("А́нна", "Андре́евна", "Ахма́това", 177_000.00, 3);
        employees[1] = new Employee("Никола́й", "Степа́нович", "Гумилёв", 128_000.00, 3);
        employees[2] = new Employee("Серге́й", "Алекса́ндрович", "Есе́нин", 211_000.00, 2);
        employees[3] = new Employee("Лев", "Никола́евич", "Толсто́й", 249_000.00, 4);
        employees[4] = new Employee("Алекса́ндр", "Серге́евич", "Пу́шкин", 250_000.00, 2);
        employees[5] = new Employee("Ива́н", "Серге́евич", "Турге́нев", 117_000.00, 4);
        employees[6] = new Employee("А́гния", "Льво́вна", "Барто́", 199_000.00, 1);
        employees[7] = new Employee("Самуи́л", "Я́ковлевич", "Марша́к", 185_000.00, 1);
        employees[8] = new Employee("Агриппи́на", "Арка́дьевна", "Донцо́ва", 35_000.00, 5);
        employees[9] = new Employee("Евге́ний", "Никола́евич", "Приле́пин", 45_000.00, 5);
    }

    public Employee[] getEmployees() {
        return employees;
    }

    @Nullable
    public Employee getEmployeeByNick(@Nonnull String nick) {
        return Arrays.stream(employees)
                .filter(Objects::nonNull)
                .filter(e -> e.getNick().equals(nick) )
                .findFirst().orElse(null);
    }

    public boolean addEmployee(@Nonnull Employee employee) {
        // TODO: 28.12.2022 Почему-то не работает этот вариант, надо покопаться на досуге
//        for (Employee employeeItem : employees) {
//            if (employeeItem == null) {
//                employeeItem = employee;
//                return false;
//            }
//        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return false;
            }
        }
        expandEmployeesAndAddItem(employee);
        return true;
    }

    /**
     * @param employee – добавляемый элемент при расширении массива
     *
     */
    private void expandEmployeesAndAddItem(Employee employee) {
        int employeesNextIndex = employees.length;
        employees = Arrays.copyOf(employees, employeesNextIndex + ARRAY_INCREMENT_DELTA);
        employees[employeesNextIndex] = employee;
    }

    public void delEmployee(int id) {
        // TODO: 28.12.2022 такая же хрень как и в addEmployee, с foreach не робит
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                employees[i] = null;
                return;
            }
        }
    }

    private static final class SingletonHelper {
        private static final EmployeesDataStub INSTANCE = new EmployeesDataStub();
    }
}
