package pro.sky.coursework2.diary.textparser;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.TreeMap;

public interface TextParserService {
    String NAME = "parser_TextParserService";

    /**
     * Получает текст, разделяет его на слова, группирует и выдаст карту, где ключ - слово, а значение - количество повторений
     *
     * @param text Строка для анализа
     * @param stringMinLength минимальная длина слова для статистики
     * @return {@link TreeMap<Integer, HashSet<String>>} Возвращает карту, где ключ - количество повторений слова в тексте,
     *  а значение - множество слов
     */
    TreeMap<Integer, HashSet<String>> countNumberWordMatchesInText(@Nonnull String text, final int stringMinLength);
}
