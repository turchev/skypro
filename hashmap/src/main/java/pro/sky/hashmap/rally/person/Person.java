package pro.sky.hashmap.rally.person;

import java.util.Objects;

public abstract class Person {
    protected String nick;

    public Person(String nick) {
        setNick(nick);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        if (nick == null) {
            throw new IllegalArgumentException();
        }
        this.nick = nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(getNick(), person.getNick());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNick());
    }
}
