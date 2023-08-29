package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.ErrorManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** atribui o erro 404 para o ErrorManagement. */
@RestControllerAdvice
public class ExceptionHandlerGlobal {
  @ExceptionHandler(ErrorManagement.class)
  public ResponseEntity<String> handleNotFound(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
