package by.rozmysl.booking.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
/**
 * This class is responsible for handling all application exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * The method overrides the handler responsible for HttpMessageNotReadableException
     * @param ex      exception
     * @param headers headers
     * @param status  status
     * @param request request
     * @return response
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError("Malformed JSON Request", ex.getMessage()), status);
    }

    /**
     * The method overrides the handler responsible for MethodArgumentNotValid
     * @param ex      exception
     * @param headers headers
     * @param status  status
     * @param request request
     * @return response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError("Method Argument Not Valid", ex.getMessage(),
                ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())), status);
    }

    /**
     * The method overrides the handler responsible for NoHandlerFoundException
     * @param ex      exception
     * @param headers headers
     * @param status  status
     * @param request request
     * @return response
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError("No Handler Found", ex.getMessage()), status);
    }

    /**
     * The method defines the handler responsible for exceptions: MyEntityNotFoundException, EntityNotFoundException,
     * MessagingException, FileNotFoundException, IOException, AuthenticationException
     * @param ex      exception
     * @param request request
     * @return response
     */
    @ExceptionHandler({MyEntityNotFoundException.class, EntityNotFoundException.class, MessagingException.class,
            FileNotFoundException.class, IOException.class, AuthenticationException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(MyEntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError("Entity Not Found Exception", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * The method defines the handler responsible for exceptions: InvocationTargetException, IllegalArgumentException,
     * ClassCastException, MethodArgumentTypeMismatchException, ConversionFailedException
     * @param ex      exception
     * @param request request
     * @return response
     */
    @ExceptionHandler({InvocationTargetException.class, IllegalArgumentException.class, ClassCastException.class,
            MethodArgumentTypeMismatchException.class, ConversionFailedException.class})
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    /** This method will catch exceptions not caught by previous handlers
     * @param ex      exception
     * @param request request
     * @return response
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError("Internal Exception", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}