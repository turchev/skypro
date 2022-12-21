package pro.sky.objectsandclasses;

import pro.sky.service.Printable;

import java.util.Objects;

public class Author implements Printable {
    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("%s %s",
                name != null ? name : "",
                surname != null ? surname : "").trim();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Author)) {
            return false;
        }
        Author otherAuthor = (Author) obj;
        return name.equals(otherAuthor.name) && surname.equals(otherAuthor.surname);
    }

}
