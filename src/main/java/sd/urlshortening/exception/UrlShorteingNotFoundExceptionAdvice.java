package sd.urlshortening.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class UrlShorteingNotFoundExceptionAdvice {
    @ExceptionHandler(UrlShorteningNotFoundException.class)
    public ResponseEntity<?> urlShorteningNotFoundHandler(UrlShorteningNotFoundException ex) {
        log.info(ex.getMessage());
        return ResponseEntity.notFound().build();
    }
}
