package com.procesos.automovil.repository;

import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {
}

