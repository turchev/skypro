package pro.sky.streamapi;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static pro.sky.streamapi.utils.StreamApiUtil.findEvenInt;
import static pro.sky.streamapi.utils.StreamApiUtil.findMinMax;

@Component
public class AppStart {

    @PostConstruct
    private void init() {
        task1();
        task2();
    }

    private void task1() {
        System.out.println("\nЗадача 1. Пример с символами");
        Stream<Character> characterStream = Stream.of('D', 'N', 'R', 'A', 'a', 'y', 'Z');
        findMinMax(characterStream, Character::compareTo, createPrintConsumer("Первый символ: ", "\nПоследний символ: "));

        System.out.println("\nЗадача 1. Пример с целыми числами");
        Stream<Integer> integerStream = Stream.of(7, 34, 1, 87, 45, 11);
        findMinMax(integerStream, Integer::compareTo, createPrintConsumer("Минимальное число: ", "\nМаксимальное число: "));
    }

    private void task2() {
        System.out.println("\nЗадача 2");
        Stream<Integer> integerStream = Stream.of(7, 34, 1, 87, 45, 11, 28, 44, 179, 122, 12);
        findEvenInt(integerStream, createPrintConsumer("Список четных чисел: ", "\nКоличество четных чисел: "));
    }

    private <T, S> BiConsumer<T, S> createPrintConsumer(String firstArgName, String secondArgName) {
        return (firstArg, secondArg) -> System.out.println(firstArgName + firstArg + ", " + secondArgName + secondArg);
    }
}
