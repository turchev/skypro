package pro.sky.socks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.socks.exeption.FileReadException;
import pro.sky.socks.exeption.FileWriteException;
import pro.sky.socks.service.SocksService;

import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/api/socks/files")
@Tag(name = "Api для работы с файлом носков", description = "Импорт/Экспорт файла")
@RequiredArgsConstructor
public class SocksFileController {
    private final SocksService socksService;

    @GetMapping("/export")
    @Operation(summary = "Выгрузка json файла носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл доступен для загрузки"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<InputStreamResource> downloadSocksJson() {
        try {
            File socksFile = socksService.exportToFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + socksFile.getName())
                    .body(resource);
        } catch (FileWriteException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка json файла носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл доступен для загрузки"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<String> uploadSocksJson(@RequestParam MultipartFile file) {
        try {
            socksService.importFromFile(file);
            return ResponseEntity.ok().body("Файл успешно загружен на сервер");
        } catch (FileWriteException | FileReadException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }
}