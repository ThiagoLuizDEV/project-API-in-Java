package com.betrybe.agrix.exceptions;

/** exeção de erro. */
public class ErrorManagement extends RuntimeException {
  public ErrorManagement(String error) {
    super(error);
  }
}
