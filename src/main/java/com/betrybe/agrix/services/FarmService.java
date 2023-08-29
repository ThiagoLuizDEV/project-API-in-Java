package com.betrybe.agrix.services;


import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Esta classe fornece os serviços relacionados a fazendas. */
@Service
public class FarmService {

  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepo) {
    this.farmRepository = farmRepo;
  }

  public Farms insertFarm(Farms farms) {
    return farmRepository.save(farms);
  }

  public List<Farms> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farms> getByIdFarm(Long id) {
    return farmRepository.findById(id);
  }
}