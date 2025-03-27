package pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ex.getBody();
        problemDetail.setProperty(
            "invalid-params",
            ex.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError -> Map.of(
                "rejected-value", FieldError.getRejectedValue() == null ? "" : FieldError.getRejectedValue(),
                "reason", FieldError.getDefaultMessage() == null ? "" : FieldError.getDefaultMessage())
            ))
        );

        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource not found");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setInstance(URI.create(String.format("%s/%s", ex.getResourceName(), ex.getResourceId())));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(ReservationConflictException.class)
    private ResponseEntity<Object> handleReservationConflict(ReservationConflictException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Reservation conflict");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty(
            "invalid-params",
            Map.of(
                "rejected-value", ex.getEditorDto(),
                "conflicting-reservations", ex.getConflictingReservationDtos()
            )
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
}
