package com.betrybe.agrix.controllers.dto;

/** DTO que encapsula informações do token. */
public record TokenDto(String token) {
  public static TokenDto formated(String token) {
    return new TokenDto(token);
  }
}
