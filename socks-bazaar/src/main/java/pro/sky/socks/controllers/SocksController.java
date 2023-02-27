package pro.sky.socks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.socks.model.ColorType;
import pro.sky.socks.model.SizeType;
import pro.sky.socks.model.Socks;
import pro.sky.socks.model.SocksResponseDTO;
import pro.sky.socks.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Tag(name = "Склад носков", description = "Учет товаров на складе интернет-магазина носков. ")
public class SocksController {
    private final SocksService socksService;

    @Operation(summary = "Регистрирует приход носков на склад")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось добавить носки на склад"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping
    public ResponseEntity<SocksResponseDTO> addToWarehouse(
            @RequestBody @Valid Socks socks,
            @RequestParam(name = "quantity", defaultValue = "1") @Positive(message = "Ожидается положительное число") int quantity
    ) {
        try {
            return ResponseEntity.ok().body(socksService.save(socks, quantity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }

    @Operation(summary = "Регистрирует отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада"),
            @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PutMapping
    public ResponseEntity<SocksResponseDTO> pickUpFromWarehouse(
            @RequestBody @Valid Socks socks,
            @RequestParam(name = "quantity", defaultValue = "1") @Positive(message = "Ожидается положительное число") int quantity
    ) {
        try {
            return ResponseEntity.ok().body(socksService.remove(socks, quantity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }

    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    @GetMapping
    public ResponseEntity<Integer> countSocksByParameters(
            @RequestParam(name = "color") ColorType color,
            @RequestParam(name = "size") SizeType size,
            @RequestParam(name = "cottonMin", required = false, defaultValue = "0") @Min(0) @Max(100) int cottonMin,
            @RequestParam(name = "cottonMax", required = false, defaultValue = "100") @Min(1) @Max(100) int cottonMax) {
        try {
            return ResponseEntity.ok(socksService.countBy(color, size, cottonMin, cottonMax));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }

    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @DeleteMapping
    public ResponseEntity<SocksResponseDTO> deleteFromWarehouse(
            @RequestBody @Valid Socks socks,
            @RequestParam(name = "quantity", defaultValue = "1") @Positive(message = "Ожидается положительное число") int quantity
    ) {
        try {
            return ResponseEntity.ok().body(socksService.remove(socks, quantity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }
}
