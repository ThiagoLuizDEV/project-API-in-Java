package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface que define um repositório para entidades do tipo Farms. */
@Repository
public interface FarmRepository extends JpaRepository<Farms, Long> {

}
