package com.procesos.automovil.repository;

import com.procesos.automovil.models.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {
}
