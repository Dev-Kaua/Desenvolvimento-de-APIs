package com.KauaReis.zombiessurvival.repository;

import com.KauaReis.zombiessurvival.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
}
