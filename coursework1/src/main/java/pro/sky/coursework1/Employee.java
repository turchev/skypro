package pro.sky.coursework1;

public class Employee {
    private int id;
    private String name;
    private String patronymic;
    private String surname;
    private double salary;
    private int department;

    public Employee(String name, String patronymic, String surname, double salary, int department) {
        this.id = Main.idCounter++;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Идентификатор = " + id + ", Имя = " + name + ", Отчество = " + patronymic + ", Фамилия = " + surname +
                ", ЗП = " + salary + ", Отдел = " + department;
    }
}
