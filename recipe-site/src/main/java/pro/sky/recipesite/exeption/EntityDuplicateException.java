package pro.sky.recipesite.exeption;

public class EntityDuplicateException extends RuntimeException {
    public EntityDuplicateException(String message) {
        super(message);
    }
}
