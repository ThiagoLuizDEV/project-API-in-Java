package com.betrybe.agrix.models.entities;

import  jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;

/** Esta classe representa uma entidade de crop. */
@Entity
@Table(name = "crop")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double plantedArea;
  private LocalDate plantedDate;

  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farms_id")
  private Farms farms;

  public Crop() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Long getFarmId() {
    return farms == null ? null : farms.getId();
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public Farms getFarms() {
    return farms;
  }

  public void setFarms(Farms farm) {
    this.farms = farm;
  }
}
