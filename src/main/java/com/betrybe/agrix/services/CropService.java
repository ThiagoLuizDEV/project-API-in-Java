package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Esta classe fornece os serviços relacionados a plantações. */
@Service
public class CropService {
  private final CropRepository cropRepository;

  private FarmService farmService;

  @Autowired
  public CropService(CropRepository cropRepo) {
    this.cropRepository = cropRepo;
  }

  public Crop insertCrop(Crop crop, Farms farms) {
    crop.setFarms(farms);
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getByIdCrop(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> associatedWithFarm(Farms farm) {
    return cropRepository.findByFarms(farm);
  }

  public List<Crop> getCropsOnHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  public Crop test(Crop crop) {
    return cropRepository.save(crop);
  }
}
