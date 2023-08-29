package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/** DTO que encapsula informações das plantações. */
public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate, Long farmId) {

  /** para a plantação. */
  public Crop toCrop() {
    Crop test = new Crop();
    test.setName(name);
    test.setPlantedArea(plantedArea);
    test.setPlantedDate(plantedDate);
    test.setHarvestDate(harvestDate);
    return test;
  }

  public CropDto toDto() {
    return new CropDto(id, name, plantedArea, plantedDate, harvestDate, farmId);
  }
}
