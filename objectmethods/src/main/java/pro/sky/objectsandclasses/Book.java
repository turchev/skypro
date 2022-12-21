package pro.sky.objectsandclasses;

import pro.sky.service.Printable;

import java.util.Objects;

public class Book implements Printable {
    private final String name;
    private final Author author;
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
        if (yearRelease < 0) {
            throw new IllegalArgumentException("Год не может быть отрицательным числом");
        }
        this.yearRelease = yearRelease;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
                author != null ? author : "",
                name != null ? " \"" + name + "\" " : "",
                yearRelease == 0 ? "" : yearRelease).trim();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, yearRelease);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book otherBook = (Book) obj;
        return name.equals(otherBook.name)
                && author.equals(otherBook.author)
                && yearRelease == (otherBook.yearRelease);
    }

}
