package task.raif.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import task.raif.exception.NotFoundException;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleConverterErrors() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMANVE() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMATVE() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMSRP() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleCVE() {
        return ResponseEntity.status(400)
                             .body("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRT(RuntimeException e) {
        System.out.println("run - "+ e.getClass() +" --- "+e.getMessage());
        return ResponseEntity.status(500)
                             .body("HTTP 500 — произошла ошибка, независящая от вызывающей стороны "
                                           + "(например, база данных недоступна)");
    }

}

