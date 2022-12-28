package pro.sky.coursework1.service;

import pro.sky.coursework1.entity.Employee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class EmployeeBook {

    public double calculateTotalSalaries(@Nonnull Employee[] employees, int department) {
        return Arrays.stream(employees)
                .filter(e -> department == 0 || e.getDepartment() == department)
                .mapToDouble(Employee::getSalary).sum();
    }

    @Nullable
    public Employee getEmployeeWithMinSalary(@Nonnull Employee[] employees, int department) {
        return Arrays.stream(employees)
                .filter(e -> department == 0 || e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    @Nullable
    public Employee getEmployeeWithMaxSalary(@Nonnull Employee[] employees, int department) {
        return Arrays.stream(employees)
                .filter(e -> department == 0 || e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public double calculateAvgSalary(@Nonnull Employee[] employees, int department) {
        return Arrays.stream(employees)
                .filter(e -> department == 0 || e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .average().orElseThrow();
    }

    public Employee[] increaseSalary(@Nonnull Employee[] employees, double percent) {
        return increaseSalary(employees, percent, 0);
    }

    public Employee[] increaseSalary(@Nonnull Employee[] employees, double percent, int department) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Программа отказывается работать, если индексация меньше или равна нулю");
        }
        Arrays.stream(employees)
                .filter(e -> department == 0 || e.getDepartment() == department)
                .forEach(em -> {
                    em.setSalary((em.getSalary() / 100) * percent + em.getSalary());
                });
        return employees;
    }

    public Employee[] getEmployeesWithSalaryGreaterThan(@Nonnull Employee[] employees, final double testSalaryLevel) {
        return Arrays.stream(employees)
                .filter(e -> e.getSalary() >= testSalaryLevel)
                .toArray(Employee[]::new);
    }

    public Employee[] getEmployeesWithSalaryLowerThan(@Nonnull Employee[] employees, final double testSalaryLevel) {
        return Arrays.stream(employees)
                .filter(e -> e.getSalary() < testSalaryLevel)
                .toArray(Employee[]::new);
    }

    public enum PrintType {ALL_EMPLOYEE, NICK_ONLY, NICK_AND_SALARY, NOT_DEPARTMENT;}

    /**
     * Принт-сервис
     *
     */
    public class PrintService {
        private final StringBuilder title = new StringBuilder();
        private final StringBuilder body = new StringBuilder();

        public PrintService addTitle(@Nonnull String title) {
            this.title.append(title);
            return this;
        }

        public PrintService clearTitle() {
            this.title.delete(0, Integer.MAX_VALUE);
            return this;
        }

        public PrintService addBody(@Nonnull String body) {
            this.body.append(body);
            return this;
        }

        public PrintService addBody(@Nonnull Employee employee, @Nonnull EmployeeBook.PrintType fieldType) {
            switch (fieldType) {
                case ALL_EMPLOYEE:
                    this.body.append(employee);
                    break;
                case NICK_ONLY:
                    this.body.append(employee.getNick());
                    break;
                case NOT_DEPARTMENT:
                    this.body.append(String.format("%s%s%s",
                            employee.getId() <= 0 ? "" : employee.getId() + ", ",
                            employee.getNick() == null ? "" : employee.getNick() + ", ",
                            employee.getSalary() <= 0.0 ? "" : employee.getSalary()));
                    break;
                case NICK_AND_SALARY:
                    this.body.append(String.format("%s%s",
                            employee.getNick() == null ? "" : employee.getNick() + ", ",
                            employee.getSalary() <= 0.0 ? "" : employee.getSalary()));

            }
            return this;
        }

        public PrintService addBody(@Nonnull Employee[] employees, @Nonnull EmployeeBook.PrintType fieldType) {
            return addBody(employees, fieldType, 0);
        }

        public PrintService addBody(@Nonnull Employee[] employees, @Nonnull EmployeeBook.PrintType fieldType, int department) {
            Arrays.stream(employees)
                    .filter(Objects::nonNull)
                    .filter(e -> department == 0 || e.getDepartment() == department)
                    .forEach(em -> {
                        addBody(em, fieldType);
                        addBody("\n");
                    });
            return this;
        }

        public PrintService clearBody() {
            this.body.delete(0, Integer.MAX_VALUE);
            return this;
        }

        public PrintService print() {
            System.out.println(title);
            System.out.println(body);
            return this;
        }
    }
}
