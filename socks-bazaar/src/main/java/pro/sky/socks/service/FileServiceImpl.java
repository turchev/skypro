package pro.sky.socks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.socks.exeption.FileReadException;
import pro.sky.socks.exeption.FileWriteException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ObjectMapper objectMapper;

    @Override
    public <T> Path saveToFile(T content, Path path) throws FileWriteException {
        try {
            String json = objectMapper.writeValueAsString(content);
            createNewFile(path);
            return Files.writeString(path, json);
        } catch (Exception e) {
            throw new FileWriteException("Ошибка создания файла");
        }
    }

    @Override
    public <T> T readFromMultipartFile(MultipartFile file, TypeReference<T> typeReference) throws FileReadException {
        try {
            return objectMapper.readValue(file.getInputStream(), typeReference);
        } catch (Exception e) {
            throw new FileReadException("Ошибка преобразования данных файла в объект");
        }
    }

    private File createNewFile(Path path) throws FileWriteException {
        try {
            Files.deleteIfExists(path);
            return Files.createFile(path).toFile();
        } catch (Exception e) {
            throw new FileWriteException("Ошибка создания файла");
        }
    }
}
