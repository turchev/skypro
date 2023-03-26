package pro.sky.jdbc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(length = 6)
    private String gender;
    @Column
    private Integer age;
    @Column(name = "city_id")
    private int city;

    public Employee(String firstName, String lastName, String gender, Integer age, int city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }
}
