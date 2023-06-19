package com.procesos.automovil.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.models.User;
import com.procesos.automovil.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class AutomovilServiceImp implements AutomovilService {
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private AutomovilRepository automovilRepository;

    @Override
    public Automovil getAutomovil(Long id){

        return automovilRepository.findById(id).get();
    }

    @Override
    public Boolean createAutomovil(Long id, User user) {
        try {
            String baseUrl = "https://myfakeapi.com/api/cars/";
            ResponseEntity<String> response = restTemplate.getForEntity(baseUrl.concat(String.valueOf(id)), String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Automovil automovil = objectMapper.readValue(response.getBody().substring(7), Automovil.class);
            automovil.setUser(user);
            automovilRepository.save(automovil);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public Boolean delete(Long id){
        try {
            Automovil automovil = automovilRepository.findById(id).get();
            automovilRepository.delete(automovil);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Automovil> allAutomovil() {
        return automovilRepository.findAll();
    }

    @Override
    public Boolean updateAutomovil(Long id, Automovil automovil) {
        try {
            Automovil automovilBD = automovilRepository.findById(id).get();
            automovilBD.setCar(automovil.getCar());
            automovilBD.setCar_model(automovil.getCar_model());
            automovilBD.setCar_color(automovil.getCar_color());
            automovilBD.setCar_model_year(automovil.getCar_model_year());
            automovilBD.setPrice(automovil.getPrice());
            automovilBD.setAvailability(automovil.isAvailability());
            automovilBD.setUser(automovil.getUser());
            Automovil automovil1Up = automovilRepository.save(automovilBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public boolean validarIdExistente(Long id) {
        return automovilRepository.existsById(id);
    }
}
