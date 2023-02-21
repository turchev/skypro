package pro.sky.recipesite.service;

import org.springframework.stereotype.Service;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.model.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RecipeReportServiceImpl implements RecipeReportService {

    @Override
    public String createReportInMarkdown(Map<Long, Recipe> recipeMap) {
        StringBuilder stringBuilder = new StringBuilder("## Книга рецептов\n\n");
        AtomicInteger recipeCounter = new AtomicInteger(1);
        recipeMap.values().stream()
                .filter(Objects::nonNull)
                .forEach(recipe -> {
                    stringBuilder.append("> Рецепт ").append(recipeCounter.getAndIncrement()).append("\n\n");
                    stringBuilder.append("### ").append(recipe.getName()).append("\n\n");
                    stringBuilder.append("**Время приготовления: ").append(recipe.getCookingTime()).append(" минут.**\n\n");
                    stringBuilder.append(buildIngredientsInReport(recipe.getIngredients()));
                    stringBuilder.append(buildStepsInReport(recipe.getSteps()));
                });
        return stringBuilder.toString();
    }

    private String buildIngredientsInReport(List<Ingredient> ingredients) {
        StringBuilder ingredientsInReport = new StringBuilder("**Ингредиенты:**\n\n");
        ingredients.forEach(ingredient -> {
            ingredientsInReport.append("* ").append(ingredient.getName()).append(" - ")
                    .append(ingredient.getWeight()).append(" ")
                    .append(ingredient.getMeasureUnit()).append("\n");
        });
        return ingredientsInReport.toString();
    }

    private String buildStepsInReport(List<String> steps) {
        StringBuilder stepsInReport = new StringBuilder("\n**Инструкция приготовления:**\n\n");
        for (int i = 0; i < steps.size(); i++) {
            stepsInReport.append(i + 1).append(". ").append(steps.get(i)).append("\n");
        }
        return stepsInReport.toString();
    }
}
