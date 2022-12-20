package pro.sky.objectsandclasses;

public class Main {
    public static void main(String[] args) {

        Author authorLafore = new Author("Роберт", "Лафоре");
        Author authorStevens = new Author("Эл", "Стивенс");

        Book bookAlgorithmsJava = new Book("Структуры данных и алгоритмы Java", authorLafore, 2015);
        Book bookWilleyCPlusPlus = new Book("Самоучитель по C++ от Willey", authorStevens, 2005);

        System.out.println(bookAlgorithmsJava);
        System.out.println(bookWilleyCPlusPlus);

        bookAlgorithmsJava.setYearRelease(2017);
        System.out.println(bookAlgorithmsJava);

    }
}
