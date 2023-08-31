package com.transactions.configurations.exceptions;

import com.transactions.configurations.exceptions.enums.ErrorType;
import com.transactions.configurations.exceptions.error.Error;
import com.transactions.configurations.exceptions.error.ErrorField;
import com.transactions.configurations.exceptions.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Void> handleNullPointerException() {
        log.info("Handle NullPointerException");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(final Exception ex) {
        log.error(INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<StandardError> handleBaseBusinessException(final BaseBusinessException exception,
                                                                     final HttpServletRequest request) {
        log.info("Handle business exceptions.");

        return ResponseEntity
                .status(exception.getStatus())
                .body(exception.getStandardError(request.getRequestURI(), request.getLocale()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<StandardError> handleBindException(final BindException ex,
                                                             final HttpServletRequest request) {
        log.error("BindException: ", ex);
        return ResponseEntity.status(BAD_REQUEST)
                .body(this.standardErrorBuild(request, BAD_REQUEST, ErrorType.ERROR_CODE_001, this.getErrorFields(ex.getBindingResult())));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<StandardError> handleServletRequestBindingException(final ServletRequestBindingException exception,
                                                                              final HttpServletRequest request) {
        log.info("Handle ServletRequestBindingException: ", exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(this.standardErrorBuild(request, HttpStatus.BAD_REQUEST, ErrorType.ERROR_CODE_001, null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex,
                                                                               final HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: ", ex);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(this.standardErrorBuild(request, BAD_REQUEST, ErrorType.ERROR_CODE_001, null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handleConstraintViolation(final ConstraintViolationException ex,
                                                                   final HttpServletRequest request) {
        log.error("ConstraintViolationException: ", ex);

        final var errors = this.buildValidationErrors(ex.getConstraintViolations());

        return ResponseEntity.status(BAD_REQUEST).body(this.standardErrorBuild(request, BAD_REQUEST, ErrorType.ERROR_CODE_003, errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleHttpMessageNotReadableException(final MethodArgumentNotValidException ex,
                                                                               final HttpServletRequest request) {

        log.error("MethodArgumentNotValidException: ", ex);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(this.standardErrorBuild(request, BAD_REQUEST, ErrorType.ERROR_CODE_001, this.getErrorFields(ex)));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupported(final HttpServletRequest request) {
        return ResponseEntity
                .status(METHOD_NOT_ALLOWED)
                .body(this.standardErrorBuild(request, METHOD_NOT_ALLOWED, ErrorType.ERROR_CODE_002, null));
    }

    private List<ErrorField> getErrorFields(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> new ErrorField(fieldError.getField(), fieldError.getDefaultMessage()))
                .distinct()
                .toList();
    }

    private List<ErrorField> buildValidationErrors(final Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(this::mapToErrorField)
                .toList();
    }

    private ErrorField mapToErrorField(final ConstraintViolation<?> violation) {
        final var field = this.extractFieldName(violation.getPropertyPath());
        final var message = violation.getMessage();
        return ErrorField.builder()
                .field(field)
                .message(message)
                .build();
    }

    private String extractFieldName(final Path propertyPath) {
        return StreamSupport.stream(propertyPath.spliterator(), false)
                .reduce((first, second) -> second)
                .map(Object::toString)
                .orElse(null);
    }

    private StandardError standardErrorBuild(final HttpServletRequest request,
                                             final HttpStatus status,
                                             final ErrorType errorType,
                                             final List<ErrorField> errors) {
        return StandardError.builder()
                .path(request.getRequestURI())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .error(Error.builder()
                        .code(errorType)
                        .message(errorType.getMessage(request.getLocale()))
                        .fields(errors)
                        .build())
                .build();
    }
}
