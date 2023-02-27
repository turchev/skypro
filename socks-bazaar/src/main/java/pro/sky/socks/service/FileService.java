package pro.sky.socks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.socks.exeption.FileReadException;
import pro.sky.socks.exeption.FileWriteException;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;

public interface FileService {

    /**
     * Сохраняет объект в файл по указанному пути
     *
     * @param content контент
     * @param path    путь файла для сохранения
     * @return путь до сохраненного файла
     * @throws FileWriteException в случае ошибки при сохранении файла или преобразования объекта
     */
    <T> Path saveToFile(@NotNull T content, @NotNull Path path) throws FileWriteException;

    /**
     * Читает данные с объекта {@link MultipartFile} и преобразовывает в объект T
     *
     * @param file          объект {@link MultipartFile}
     * @param typeReference тип объекта
     * @return объект типа T
     * @throws FileReadException в случае ошибки при преобразовании данных файла в объект
     */
    <T> T readFromMultipartFile(@NotNull MultipartFile file, @NotNull TypeReference<T> typeReference) throws FileReadException;
}
