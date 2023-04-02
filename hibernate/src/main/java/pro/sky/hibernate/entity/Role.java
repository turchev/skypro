package pro.sky.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static pro.sky.hibernate.Application.APPLICATION_PREFIX;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = APPLICATION_PREFIX + "_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getId(), role.getId())
                && Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
