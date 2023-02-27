package pro.sky.socks.exeption;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Violation violation = new Violation(e.getName(), "Некорректное значение: " +
                Objects.requireNonNull(e.getValue()));
        final List<Violation> violations = new ArrayList<>();
        violations.add(violation);
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onJsonParseException(JsonParseException e) {
        Violation violation = new Violation("Parsing json", e.getOriginalMessage());
        final List<Violation> violations = new ArrayList<>();
        violations.add(violation);
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onInvalidFormatException(InvalidFormatException e) {
        Violation violation = new Violation(e.getPathReference(), e.getValue().toString());
        final List<Violation> violations = new ArrayList<>();
        violations.add(violation);
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(FileWriteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ValidationErrorResponse onAppException(FileWriteException e) {
        Violation violation = new Violation("Application handled exception", e.getMessage());
        final List<Violation> violations = new ArrayList<>();
        violations.add(violation);
        return new ValidationErrorResponse(violations);
    }

    private record ValidationErrorResponse(List<Violation> violations) {
    }

    private record Violation(String fieldName, String message) {
    }
}