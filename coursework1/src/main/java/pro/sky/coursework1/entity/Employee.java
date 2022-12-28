package pro.sky.coursework1.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pro.sky.coursework1.data.EmployeesData;

@EqualsAndHashCode
@Setter
@Getter
public class Employee {

    private final int id;
    private final String name;
    private final String patronymic;
    private final String surname;
    private double salary;
    private int department;

    public Employee(String name, String patronymic, String surname, double salary, int department) {
        this.id = EmployeesData.getInstance().getNextNumber();
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.salary = salary;
        this.department = department;
    }

    public String getNick() {
        return String.format("%s%s%s",
                getSurname() == null ? "" : getSurname() + " ",
                getName() == null ? "" : getName() + " ",
                getPatronymic() == null ? "" : getPatronymic()).trim();
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s",
                getId() <= 0 ? "" : "Идентификатор = " + getId() + ", ",
                getName() == null ? "" : "Имя = " + getName() + ", ",
                getPatronymic() == null ? "" : "Отчество = " + getPatronymic() + ", ",
                getSurname() == null ? "" : "Фамилия = " + getSurname() + ", ",
                getSalary() <= 0.0 ? "" : "ЗП = " + getSalary() + ", ",
                getDepartment() <= 0 ? "" : "Отдел = " + getDepartment());
    }

}
