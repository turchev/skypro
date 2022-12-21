package pro.sky.objectmethods;

import pro.sky.objectsandclasses.Author;
import pro.sky.objectsandclasses.Book;
import pro.sky.service.PrintService;

public class Main {

    public static void main(String[] args) {
        // Сравнение и вывод результата вне рамок задания, делал для тестирования
        PrintService printService = new PrintService();

        System.out.println("Создадим двух одинаковых авторов и сравним:");
        Author authorLafore = new Author("Роберт", "Лафоре");
        Author authorLafore2 = new Author("Роберт", "Лафоре");
        printService.printDifferences(authorLafore, authorLafore2);

        System.out.println("Создадим два одинаковых экземпляра книги и сравним:");
        Book bookAlgorithmsJava = new Book("Структуры данных и алгоритмы Java", authorLafore, 2015);
        Book bookAlgorithmsJava2 = new Book("Структуры данных и алгоритмы Java", authorLafore2, 2015);
        printService.printDifferences(bookAlgorithmsJava, bookAlgorithmsJava2);

        System.out.println("Внесем изменения в год выпуска книги и сравним");
        bookAlgorithmsJava.setYearRelease(2017);
        printService.printDifferences(bookAlgorithmsJava, bookAlgorithmsJava2);

        System.out.println("Вернем преждний год выпуска книги и сравним");
        bookAlgorithmsJava.setYearRelease(2015);
        printService.printDifferences(bookAlgorithmsJava, bookAlgorithmsJava2);

        System.out.println("Внесем изменения в имя первого автора и сравним книги");
        authorLafore.setName("Nicholas");
        printService.printDifferences(bookAlgorithmsJava, bookAlgorithmsJava2);

        System.out.println("Сравним книгу с автором, прикола ради)");
        printService.printDifferences(authorLafore, bookAlgorithmsJava);
    }

}
