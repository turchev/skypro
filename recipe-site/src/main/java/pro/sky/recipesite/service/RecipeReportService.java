package pro.sky.recipesite.service;

import pro.sky.recipesite.model.Recipe;

import java.util.Map;

public interface RecipeReportService {

    /**
     * Формирует отчет в формате markdown.
     *       Пример:
     *
     *      > Рецепт 1
     *
     *      ### Сырники из творога
     *
     *      **Время приготовления: 30 минут.**
     *
     *      **Ингредиенты:**
     *
     *      * Творог — 350 г.
     *      * Куриное яйцо — 2 шт.
     *      * Пшеничная мука — 6 ст.л.
     *      * Сахар — 2 ст.л.
     *
     *      **Инструкция приготовления:**
     *
     *      1. Смешайте весь творог с яйцами, сахаром и тщательно всё перемешайте.
     *      2. Всыпьте в творог муку и тщательно перемешайте.
     *      3. Поставьте сковороду на средний огонь и налейте в нее подсолнечное масло.
     *      4. Слепите несколько небольших шариков из получившейся творожной массы и положите их на тарелку. Затем по очереди обкатывайте творожные шарики в муке и выкладывайте на сковороду.
     *      5. Обжаривайте сырники 1–2 минуты до появления золотистой корочки. Затем переверните их на другую сторону и также обжарьте до золотистого состояния.
     *      6. Повторяйте, пока творог не закончится.
     *
     * @param recipeMap карта рецептов
     * @return отчет в виде строки
     */
    String createReportInMarkdown(Map<Long, Recipe> recipeMap);
}