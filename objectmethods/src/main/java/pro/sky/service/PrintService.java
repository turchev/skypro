package pro.sky.service;

public class PrintService {

    public <T extends Printable> void printDifferences(T obj1, T obj2) {
        System.out.printf("\t%s 1: %s, hash code: %s%n" +
                        "\t%s 2: %s, hash code: %s%n" +
                        "\tЭти объекты: %s%n%n",
                obj1.getClass().getSimpleName(), obj1, obj1.hashCode(),
                obj2.getClass().getSimpleName(), obj2, obj2.hashCode(),
                obj1.equals(obj2) ? "одинаковые" : "разные");
    }
}
