package pro.sky.recipesite.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pro.sky.recipesite.exeption.FileReadException;
import pro.sky.recipesite.exeption.FileWriteException;
import pro.sky.recipesite.model.AbstractEntity;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ObjectMapper objectMapper;

    @Override
    public <T extends AbstractEntity> void saveEntitiesToFile(@NotNull Map<Long, T> entityMap, @NotNull Path path) {
        try {
            cleanFile(path);
            String json = objectMapper.writeValueAsString(entityMap);
            Files.writeString(path, json);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> Map<Long, T> readEntitiesFromFile(@NotNull Path path, @NotNull TypeReference<Map<Long, T>> typeReference) {
        Map<Long, T> resultMap = null;
        try {
            String json = Files.readString(path);
            if (json.isEmpty()) {
                return new HashMap<>();
            }
            resultMap = objectMapper.readValue(json, typeReference);
        } catch (NoSuchFileException e) {
            return new HashMap<>();
        } catch (RuntimeException | IOException ex) {
            ex.printStackTrace();
        }
        return resultMap;
    }

    private void cleanFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
    }

    @Override
    public File readFile(@NotNull String pathFile) throws FileReadException {
        Path path = Path.of(pathFile);
        if (!Files.isReadable(path)) {
            throw new FileReadException("Файл недоступен для чтения или не существует по указанному пути");
        }
        return path.toFile();
    }

    @Override
    public File createFile(@NotNull String pathFile) throws FileWriteException {
        try {
            Path path = Path.of(pathFile);
            cleanFile(path);
            return path.toFile();
        } catch (IOException e) {
            throw new FileWriteException("Ошибка создания файла");
        }
    }
}