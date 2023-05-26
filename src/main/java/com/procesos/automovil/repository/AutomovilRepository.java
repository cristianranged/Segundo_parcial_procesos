package com.procesos.automovil.repository;

import com.procesos.automovil.models.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AutomovilRepository extends JpaRepository<Automovil, Long> {
}

