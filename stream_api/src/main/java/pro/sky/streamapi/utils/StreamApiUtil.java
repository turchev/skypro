package pro.sky.streamapi.utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public final class StreamApiUtil {

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<? extends T> collect = stream.toList();
        T min = collect.stream().min(order).orElse(null);
        T max = collect.stream().max(order).orElse(null);

        minMaxConsumer.accept(min, max);
    }

    public static void findEvenInt(Stream<Integer> intStream, BiConsumer<List<Integer>, Integer> evenIntConsumer) {
        List<Integer> integerList = intStream
                .filter(d -> d % 2 == 0)
                .toList();

        evenIntConsumer.accept(integerList, integerList.size());
    }
}
