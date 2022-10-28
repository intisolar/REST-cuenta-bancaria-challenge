package com.devsu.challenge.intigarcia.exception;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*400*/
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    for (FieldError fieldError : e.getFieldErrors()) {
      error.add(fieldError.getDefaultMessage());
    }
    error.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(value = InvalidOperationException.class)
  public ResponseEntity<ErrorResponse> handleInvalidOperationException(
      InvalidOperationException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /*401*/
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = InsufficientPermissionsException.class)
  public ResponseEntity<ErrorResponse> handleInsufficientPermissionsException(
      InsufficientPermissionsException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.FORBIDDEN, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }

  /*200>404*/
  @ExceptionHandler(value = DataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e) {

    ErrorResponse error = buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    ResponseEntity response = new ResponseEntity(error, HttpStatus.OK);

    return response;
  }

  /*404*/
  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      ResourceNotFoundException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  /*500*/
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    ErrorResponse error = buildErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "{Message: }" + e.getMessage() + "{StackTrace: }" + sw.toString());

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    ErrorResponse error = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        "{Message: }" + e.getMessage() + "{StackTrace: }" + sw.toString());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(httpStatus.value());
    error.add(message);
    error.setTimestamp(LocalDateTime.now());
    return error;
  }


}
