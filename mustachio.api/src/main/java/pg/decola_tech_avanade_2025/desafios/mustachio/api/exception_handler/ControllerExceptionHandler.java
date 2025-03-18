package pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler  {
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
