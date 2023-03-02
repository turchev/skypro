package pro.sky.junit;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    private String login;

    private String email;

    public User(String login, String email) {
        setLogin(login);
        setEmail(email);
    }

    public void setLogin(String login) {
        if (login != null && !isValidLogin(login)) {
            throw new IllegalArgumentException("Некорректный логин");
        }
        this.login = login;
    }

    public void setEmail(String email) {
        if (email != null && !isValidEmail(email)) {
            throw new IllegalArgumentException("Некорректный адрес электронной почты");
        }
        this.email = email;
    }

    private boolean isValidEmail(@NonNull String email) {
        if (getLogin() != null && email.equals(getLogin())) {
            return false;
        }
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidLogin(@NonNull String login) {
        if (getEmail() == null) {
            return true;
        }
        return !login.equals(getEmail());
    }

}
