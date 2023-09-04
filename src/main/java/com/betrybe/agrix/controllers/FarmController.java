package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.exceptions.ErrorManagement;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** controller do farm. */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
  public ResponseEntity<Farms> createFarms(@RequestBody FarmDto farmDto) {
    Farms newFarms = farmService.insertFarm(farmDto.toFarms());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarms);
  }

  /** retorna uma lista de todas as fazendas. */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    List<Farms> newAllFarms = farmService.getAllFarms();
    return newAllFarms.stream().map((farm -> new FarmDto(farm.getId(), farm.getName(),
        farm.getSize()))).collect(Collectors.toList());
  }

  /** retorna a fazendo específica pelo @Id. */
  @GetMapping("/{id}")
  public ResponseEntity getByIdFarm(@PathVariable Long id) {
    Optional<Farms> farms = farmService.getByIdFarm(id);

    if (farms.isEmpty()) {
      String farmNotFound = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmNotFound);
    }
    return ResponseEntity.ok(farms);
  }

  /** colhetas associada a fazenda. */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> associatedWithFarm(@PathVariable Long farmId) {
    Optional<Farms> farms = farmService.getByIdFarm(farmId);

    if (farms.isEmpty()) {
      throw new ErrorManagement("Fazenda não encontrada!");
    }
    List<Crop> crop = farms.get().getCrops();
    return crop.stream().map(crop1 -> new CropDto(crop1.getId(), crop1.getName(),
        crop1.getPlantedArea(), crop1.getPlantedDate(), crop1.getHarvestDate(),
        crop1.getFarms().getId())).toList();
  }

  /** Insere uma nova cultura em uma fazenda específica. */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> insertCrop(@PathVariable Long farmId, @RequestBody CropDto crop) {
    Optional<Farms> farms = farmService.getByIdFarm(farmId);

    if (farms.isPresent()) {
      Crop present = crop.toCrop();
      present.setFarms(farms.get());
      Crop finishCrop = cropService.test(present);
      CropDto finish = new CropDto(finishCrop.getId(), finishCrop.getName(),
          finishCrop.getPlantedArea(), finishCrop.getPlantedDate(), finishCrop.getHarvestDate(),
          finishCrop.getFarms().getId());
      return ResponseEntity.status(HttpStatus.CREATED).body(finish);
    } else {
      throw new ErrorManagement("Fazenda não encontrada!");
    }

  }
}
