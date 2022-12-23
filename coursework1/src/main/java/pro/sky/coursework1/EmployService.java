package pro.sky.coursework1;

public class EmployService {

    public Employee[] employeeGenerate() {
        Employee[] employees = new Employee[10];
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
        return employees;
    }
}
