package pro.sky.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

import static pro.sky.hibernate.Application.APPLICATION_PREFIX;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = APPLICATION_PREFIX + "_user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "name", nullable = false)
    protected String name;

    @NotEmpty
    @NotNull
    @Column(name = "login", unique = true, nullable = false)
    protected String login;

    @NotEmpty
    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = APPLICATION_PREFIX + "_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login, String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId())
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return String.format("ID:%d, Имя:%s, Логин:%s", getId(), getName(), getLogin());
    }
}
