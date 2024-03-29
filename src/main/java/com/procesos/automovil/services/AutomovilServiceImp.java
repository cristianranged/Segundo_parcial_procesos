package com.procesos.automovil.services;


import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class AutomovilServiceImp implements AutomovilService {

    private final RestTemplate restTemplate;


    public AutomovilServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private AutomovilRepository AutomovilRepository;


    @Override
    public Automovil getAutomovil(Long id){
        return AutomovilRepository.findById(id).get();
    }

    @Override
    public Boolean createAutomovil() {

        try {
            String url="https://643c82256afd66da6adfbe5f.mockapi.io/cars/";
            Automovil[] response= restTemplate.getForObject(url, Automovil[].class);
            AutomovilRepository.saveAll(Arrays.asList(response));
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public List<Automovil> allAutomovil() {
        return AutomovilRepository.findAll();
    }









    @Override
    public Boolean updateAutomovil(Long id, Automovil car) {
        try {
            Automovil carBD = AutomovilRepository.findById(id).get();
            carBD.setCar(car.getCar());
            carBD.setCar_model(car.getCar_model());
            carBD.setCar_color(car.getCar_color());
            carBD.setCar_model_year(car.getCar_model_year());
            carBD.setCar_vin(car.getCar_vin());
            carBD.setPrice(carBD.getPrice());
            carBD.setAvailability(car.isAvailability());
            Automovil car1Up = AutomovilRepository.save(carBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
