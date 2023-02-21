package pro.sky.recipesite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.recipesite.configuration.FilesProperties;
import pro.sky.recipesite.exeption.FileReadException;
import pro.sky.recipesite.exeption.FileWriteException;
import pro.sky.recipesite.repository.RecipeRepository;
import pro.sky.recipesite.service.FileService;
import pro.sky.recipesite.service.RecipeReportService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;


@Validated
@RestController
@RequestMapping("/files")
@Tag(name = "Работа с файлами", description = "Импорт и экспорт рецептов и ингредиентов")
@RequiredArgsConstructor
public class FileController {
    private final FilesProperties filesProperties;
    private final FileService fileService;
    private final RecipeReportService recipeReportService;
    private final RecipeRepository recipeRepository;

    @Operation(
            summary = "Выгрузка файла рецептов",
            description = "По умолчанию выгрузка осуществляется в формате json. " +
                    "Если указать явно параметр markdown=true, то выгрузка будет осуществлена в виде отчета в формате markdown")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл доступен для загрузки"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос"),
            @ApiResponse(responseCode = "404", description = "Файл недоступен для чтения или не существует по указанному пути"),
            @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка при чтении файла")})
    @GetMapping("/recipe/download")
    public ResponseEntity<InputStreamResource> downloadRecipeFile(
            @RequestParam(name = "markdown", required = false, defaultValue = "false") Boolean markdown) {
        try {
            String filePath;
            if (markdown) {
                filePath = filesProperties.filesDir() + "/" + filesProperties.recipeMarkdownFileName();
                fileService.saveToFile(recipeReportService.createReportInMarkdown(recipeRepository.findAll()), Path.of(filePath));
            } else {
                filePath = filesProperties.filesDir() + "/" + filesProperties.recipeFileName();
            }
            File file = fileService.readFile(filePath);
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(filePath));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                    .body(inputStreamResource);
        } catch (FileReadException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка при чтении файла");
        }
    }

    @Operation(summary = "Загрузка файла рецептов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл успешно загружен на сервер"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос"),
            @ApiResponse(responseCode = "500", description = "Ошибка создания файла или Непредвиденная ошибка")})
    @PostMapping(value = "/recipe/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadRecipeFile(@RequestParam MultipartFile file) {
        try {
            File newFile = fileService.createFile(filesProperties.filesDir() + "/" + filesProperties.recipeFileName());
            FileOutputStream fos = new FileOutputStream(newFile);
            IOUtils.copy(file.getInputStream(), fos);
        } catch (FileWriteException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка");
        }
        return ResponseEntity.ok().body("Файл успешно загружен на сервер");
    }

    @Operation(summary = "Загрузка файла ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл успешно загружен на сервер"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос"),
            @ApiResponse(responseCode = "500", description = "Ошибка создания файла или Непредвиденная ошибка")})
    @PostMapping(value = "/ingredient/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadIngredientFile(@RequestParam MultipartFile file) {
        try {
            File newFile = fileService.createFile(filesProperties.filesDir() + "/" + filesProperties.ingredientFileName());
            FileOutputStream fos = new FileOutputStream(newFile);
            IOUtils.copy(file.getInputStream(), fos);
        } catch (FileWriteException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка");
        }
        return ResponseEntity.ok().body("Файл успешно загружен на сервер");
    }
}
