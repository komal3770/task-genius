package com.taskgenius.exceptions;

import com.taskgenius.dto.ApiError;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(new ApiError("Invalid method type", false));
  }

  /**
   * @param ex
   * @param headers
   * @param status
   * @param request
   * @return
   */
  @Override
  protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiError("Error occurred", false));
  }

  /**
   * @param ex
   * @param body
   * @param headers
   * @param statusCode
   * @param request
   * @return
   */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    ex.printStackTrace();
    if (ex instanceof MethodArgumentNotValidException exception) {
      if (!exception.getBindingResult().getAllErrors().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                new ApiError(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                    false));
      }
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiError("Internal error occurred", false));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Object> handleForbiddenException(Exception ex){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ApiError("Authentication token missing or invalid", false));
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<ApiError> handleAuthenticationException(Exception ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ApiError("Access denied", false));
  }

  @ExceptionHandler({ IllegalArgumentException.class })
  public ResponseEntity<ApiError> handleIllegalArugment(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiError(ex.getMessage(), false));
  }
}