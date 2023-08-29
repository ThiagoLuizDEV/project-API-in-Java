package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farms;

/** DTO que encapsula informações de uma fazenda. */
public record FarmDto(Long id, String name, Double size) {
  /** Converte o objeto FarmDto em um objeto Farms. */
  public Farms toFarms() {
    return new Farms(id, name, size);
  }
}