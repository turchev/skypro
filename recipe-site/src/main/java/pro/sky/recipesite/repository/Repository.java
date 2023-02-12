package pro.sky.recipesite.repository;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pro.sky.recipesite.model.AbstractEntity;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Validated
public interface Repository<T extends AbstractEntity> {

    /**
     * Saves an entity in the repository
     *
     * @param entity object that extends the {@link AbstractEntity}
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also indicate that the map previously associated null with key)
     */
    @Nullable
    T save(@Valid T entity);

    /**
     * Finds an entity by ID in the repository
     *
     * @param id the primary key of the entity that extends {@link AbstractEntity}
     * @return the entity that extends the {@link AbstractEntity} to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Optional<T> findById(@Valid long id);

    /**
     * Replaces an entity in the repository
     *
     * @param entity object that extends the {@link AbstractEntity}
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also indicate that the map previously associated null with key)
     */
    @Nullable
    T update(@Valid T entity);

    /**
     * Removes an entity from the repository
     *
     * @param entity object that extends the {@link AbstractEntity}
     * @return The previous value of the entity, or null if there was no match
     */
    @Nullable
    T delete(@Valid T entity);

    /**
     * Returns all entities from the repository
     * @return all entities
     */
    Map<Long, T> findAll();
}

