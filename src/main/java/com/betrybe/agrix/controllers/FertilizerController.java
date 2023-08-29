package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** controller da rota /fertilizer. */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Fertilizer insertFertilizer(@RequestBody Fertilizer fertilizer) {
    return fertilizerService.insertFertilizer(fertilizer);
  }

  @GetMapping
  public List<Fertilizer> getAllFertilizer() {
    return fertilizerService.getAllFertilizer();
  }

  /** procura pelo id. */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity getByIdFertilizer(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerService.getByIdFertilizer(fertilizerId);

    if (fertilizer.isEmpty()) {
      String fertilizerNotFound = "Fertilizante não encontrado!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fertilizerNotFound);
    }
    return ResponseEntity.ok(fertilizer);
  }
}
