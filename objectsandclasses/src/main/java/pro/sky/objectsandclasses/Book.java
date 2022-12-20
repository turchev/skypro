package pro.sky.objectsandclasses;

import java.time.LocalDate;

public class Book {
    private String name;
    private Author author;
    private int yearRelease;

    public Book(String name, Author author, int yearRelease) {
        this.name = name;
        this.author = author;
        this.yearRelease = yearRelease;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
                author != null ? author : "",
                name != null ? " \"" + name + "\" " : "",
                yearRelease <=0 || yearRelease > LocalDate.now().getYear() ? "" : yearRelease).trim();
    }
}
