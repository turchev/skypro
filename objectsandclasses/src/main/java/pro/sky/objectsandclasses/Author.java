package pro.sky.objectsandclasses;

public class Author {
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

    @Override
    public String toString() {
        return String.format("%s %s",
                name != null ? name : "",
                surname != null ? surname : "").trim();
    }
}
