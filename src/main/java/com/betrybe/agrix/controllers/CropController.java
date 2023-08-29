package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.exceptions.ErrorManagement;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/** controller da rota /crops. */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /** disponibilza todas as plantações. */
  @GetMapping
  public List<CropDto> getAllCrops() {
    List<Crop> all = cropService.getAllCrops();
    return all.stream().map(crop1 -> new CropDto(crop1.getId(), crop1.getName(),
        crop1.getPlantedArea(), crop1.getPlantedDate(), crop1.getHarvestDate(),
        crop1.getFarms().getId())).toList();
  }

  /** pesquisa pela plantação específica. */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getByIdCrop(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getByIdCrop(id);

    if (optionalCrop.isEmpty()) {
      throw new ErrorManagement("Plantação não encontrada!");
    }
    Crop crop = optionalCrop.get();
    return ResponseEntity.status(HttpStatus.OK).body(new CropDto(crop.getId(), crop.getName(),
        crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate(),
        crop.getFarms().getId()));
  }

  /** pesquisa pela colheita com data. */
  @GetMapping("/search")
  public List<CropDto> getCropsOnHarvestDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
  ) {
    List<Crop> crop = cropService.getCropsOnHarvestDate(start, end);

    return crop.stream().map(crop1 -> new CropDto(crop1.getId(), crop1.getName(),
        crop1.getPlantedArea(), crop1.getPlantedDate(), crop1.getHarvestDate(),
        crop1.getFarms().getId())).toList();
  }
}
