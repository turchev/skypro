package pro.sky.socks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.socks.configuration.FilesProperties;
import pro.sky.socks.exeption.EntityNotFoundException;
import pro.sky.socks.exeption.FileReadException;
import pro.sky.socks.exeption.FileWriteException;
import pro.sky.socks.exeption.LimitViolationException;
import pro.sky.socks.model.ColorType;
import pro.sky.socks.model.SizeType;
import pro.sky.socks.model.Socks;
import pro.sky.socks.model.SocksResponseDTO;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final FileService fileService;
    private final FilesProperties filesProperties;

    private final HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public SocksResponseDTO save(Socks socks, int quantity) {
        if (socksMap.containsKey(socks)) {
            quantity = socksMap.get(socks) + quantity;
            socksMap.replace(socks, quantity);
        } else {
            socksMap.put(socks, quantity);
        }
        return new SocksResponseDTO(socks, quantity);
    }

    @Override
    public SocksResponseDTO remove(Socks socks, int quantity) {
        if (!socksMap.containsKey(socks)) {
            throw new EntityNotFoundException("На складе нет выбранного типа носков");
        }
        int currentQuantity = socksMap.get(socks);
        if (currentQuantity > quantity) {
            int newQuantity = currentQuantity - quantity;
            socksMap.replace(socks, newQuantity);
            return new SocksResponseDTO(socks, newQuantity);
        } else {
            throw new LimitViolationException("На складе недостаточно выбранного типа носков");
        }
    }

    @Override
    public int countBy(ColorType color, SizeType size, int cottonMin, int cottonMax) {
        int count = 0;
        for (Socks socks : socksMap.keySet()) {
            if (socks.getColorType() != color || socks.getSizeType() != size) {
                continue;
            }
            if (socks.getCottonPart() < cottonMin || socks.getCottonPart() > cottonMax) {
                continue;
            }
            count = count + socksMap.get(socks);
        }
        return count;
    }

    @Override
    public File exportToFile() throws FileWriteException {
        return fileService
                .saveToFile(convertToDtoList(), Path.of(filesProperties.filesDir(), filesProperties.socksFileName()))
                .toFile();
    }

    private List<SocksResponseDTO> convertToDtoList() {
        List<SocksResponseDTO> dtoList = new ArrayList<>();
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            dtoList.add(new SocksResponseDTO(socksItem.getKey(), socksItem.getValue()));
        }
        return dtoList;
    }

    @Override
    public void importFromFile(MultipartFile file) throws FileWriteException, FileReadException {
        List<SocksResponseDTO> dtoList = fileService.readFromMultipartFile(file, new TypeReference<>() {
        });
        dtoList.forEach(s -> save(s.socks(), s.quantity()));
        fileService.saveToFile(socksMap, Path.of(filesProperties.filesDir(), filesProperties.socksFileName()));
    }
}
