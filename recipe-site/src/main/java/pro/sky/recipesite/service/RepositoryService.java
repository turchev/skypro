package pro.sky.recipesite.service;

import org.springframework.lang.Nullable;
import pro.sky.recipesite.model.AbstractEntity;

import java.util.Optional;

public interface RepositoryService<T extends AbstractEntity> {

    /**
     * @param modelImpl object that extends the {@link AbstractEntity}
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also indicate that the map previously associated null with key)
     */
    @Nullable
    T save(T modelImpl);

    /**
     * @param primaryKey the primary key of the entity that extends {@link AbstractEntity}
     * @return the entity that extends the {@link AbstractEntity} to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Optional<T> findById(long primaryKey);
}
