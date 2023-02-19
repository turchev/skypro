package pro.sky.recipesite.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pro.sky.recipesite.model.AbstractEntity;

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
    public <T extends AbstractEntity> void saveToFile(Map<Long, T> entityMap, Path path) {
        try {
            newFile(path);
            String json = objectMapper.writeValueAsString(entityMap);
            Files.writeString(path, json);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> Map<Long, T> readFromFile(Path path, TypeReference<HashMap<Long, T>> typeReference) {
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

    private void newFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
    }
}
