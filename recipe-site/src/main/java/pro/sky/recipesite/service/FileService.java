package pro.sky.recipesite.service;

import com.fasterxml.jackson.core.type.TypeReference;
import pro.sky.recipesite.exeption.FileReadException;
import pro.sky.recipesite.exeption.FileWriteException;
import pro.sky.recipesite.model.AbstractEntity;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public interface FileService {
    /**
     * Сохраняет значения из локального хранилища в виде карты в файл в формате json
     *
     * @param entityMap карта сущностей
     * @param path      путь файла
     */
    <T extends AbstractEntity> void saveEntitiesToFile(@NotNull Map<Long, T> entityMap, @NotNull Path path);

    /**
     * Читает данные из файла формата json в локальное хранилище в виде карты
     *
     * @param path          путь файла
     * @param typeReference ссылка на тип возвращаемого значения
     * @return карта сущностей
     */
    <T> Map<Long, T> readEntitiesFromFile(@NotNull Path path, @NotNull TypeReference<Map<Long, T>> typeReference);

    /**
     * Читает файл по указанному пути и возвращает в виде объекта File
     *
     * @param filePath путь файла
     * @return объект {@link File}
     */
    File readFile(@NotNull String filePath) throws FileReadException;

    /**
     * Создает файл по указанному пути, если файл уже существует, то заменяет на новый
     *
     * @param pathFile путь файла
     * @return объект {@link File}
     * @throws FileWriteException если не удалось создать файл или удалить существующий
     */
    File createFile(@NotNull String pathFile) throws FileWriteException;

    /**
     * Сохраняет контент строкового формата в файл по указанному пути, если файл уже существует, то заменяет на новый
     *
     * @param content контент строкового формата
     * @param path путь файла
     * @return объект {@link Path}
     * @throws FileWriteException если не удалось сохранить файл или перезаписать существующий
     */
    Path saveToFile(@NotNull String content, @NotNull Path path) throws FileWriteException;
}
