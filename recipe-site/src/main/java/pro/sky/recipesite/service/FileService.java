package pro.sky.recipesite.service;

import com.fasterxml.jackson.core.type.TypeReference;
import pro.sky.recipesite.model.AbstractEntity;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public interface FileService {
    /**
     * Сохраняет значения из локального хранилища в виде карты в файл в формате json
     *
     * @param entityMap карта сущностей
     * @param path      путь файла
     */
    <T extends AbstractEntity> void saveToFile(Map<Long, T> entityMap, Path path);

    /**
     * Читает данные из файла формата json в локальное хранилище в виде карты
     *
     * @param path          путь файла
     * @param typeReference ссылка на тип возвращаемого значения
     * @return карта сущностей
     */
    <T> Map<Long, T> readFromFile(Path path, TypeReference<HashMap<Long, T>> typeReference);
}
