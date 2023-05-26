package com.procesos.automovil.services;

import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.models.User;

import java.util.List;


public interface AutomovilService {
    Automovil getAutomovil(Long id);
    Boolean createAutomovil(Long automovil, User user);
    List<Automovil> allAutomovil();
    Boolean updateAutomovil(Long id, Automovil car);


}


