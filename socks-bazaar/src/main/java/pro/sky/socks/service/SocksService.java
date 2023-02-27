package pro.sky.socks.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.socks.exeption.FileReadException;
import pro.sky.socks.exeption.FileWriteException;
import pro.sky.socks.model.ColorType;
import pro.sky.socks.model.SizeType;
import pro.sky.socks.model.Socks;
import pro.sky.socks.model.SocksResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.File;

@Validated
public interface SocksService {

    /**
     * Сохраняет {@link Socks} в хранилище данных в выбранном количестве
     *
     * @param socks    тип носков
     * @param quantity количество пар носков
     * @return DTO объект, включающий в себя тип носков и суммарное количество на складе после добавления
     */
    SocksResponseDTO save(@Valid Socks socks, @Positive int quantity);

    /**
     * Удаляет {@link Socks} из хранилища данных в выбранном количестве
     *
     * @param socks    тип носков
     * @param quantity количество пар носков
     * @return DTO объект, включающий в себя тип носков и оставшееся количество на складе после удаления
     */
    SocksResponseDTO remove(Socks socks, int quantity);

    /**
     * Подсчитывает и возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса
     *
     * @param color     цвет носков
     * @param size      размер носков
     * @param cottonMin минимальное значение хлопка
     * @param cottonMax максимальное значение хлопка
     * @return возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса
     */
    int countBy(ColorType color, SizeType size, int cottonMin, int cottonMax);

    /**
     * Экспортирует информацию об {@link Socks} и их количестве в файл
     *
     * @return {@link File}
     * @throws FileWriteException в случае ошибки при записи файла
     */
    File exportToFile() throws FileWriteException;

    /**
     * Импортирует данные из {@link MultipartFile} в хранилище {@link Socks}
     *
     * @param file объект {@link MultipartFile}
     * @throws FileWriteException в случае шибки при записи в файл
     * @throws FileReadException  в случае ошибки при чтении данных из {@link MultipartFile}
     */
    void importFromFile(@NotNull MultipartFile file) throws FileWriteException, FileReadException;
}