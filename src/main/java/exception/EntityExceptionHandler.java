package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleEntityNotFoundException(EntityNotFoundException exc) {
        EntityErrorResponse error = new EntityErrorResponse(
                                        HttpStatus.NOT_FOUND.value(),
                                        exc.getMessage(),
                                        System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(Exception exc) {
        EntityErrorResponse error = new EntityErrorResponse(
                                        HttpStatus.BAD_REQUEST.value(),
                                        exc.getMessage(),
                                        System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<EntityErrorResponse> handleConstraintViolationException(ConstraintViolationException exc) {

        List<ConstraintViolation<?>> violations = new ArrayList<>();
        violations.addAll(exc.getConstraintViolations());
        EntityErrorResponse error = new EntityErrorResponse(
                                        HttpStatus.BAD_REQUEST.value(),
                                        violations.get(0).getMessage(),
                                        System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
