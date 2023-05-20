package com.procesos.automovil.controller;
import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.services.AutomovilServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AutomovilController {
    @Autowired
    private AutomovilServiceImp automovilServiceImp;


    public  AutomovilController(AutomovilServiceImp automovilServiceImp) {
        this.automovilServiceImp = automovilServiceImp;
    }
    @GetMapping(value = "/cars/{id}")
    public ResponseEntity findAutomovilById(@PathVariable Long id ){
        Map response = new HashMap();
        try {
            return new ResponseEntity(automovilServiceImp.getAutomovil(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el vehiculo!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    //crear vehiculos  en la DB traidos de la api

    @PostMapping(value = "/cars")
    public ResponseEntity createAutomovil(@RequestBody Automovil automovil){
        System.out.println(automovil);

        Long id = automovil.getId();
        if (automovilServiceImp.validarIdExistente(id)) {
            return  ResponseEntity.badRequest().body("El ID ya existe en la base de datos.");
        }

        Map response = new HashMap();
        Boolean userResp = automovilServiceImp.createAutomovil();


        if(userResp == true  ){

            response.put("status", "201");
            response.put("message","Se creo el vehiculo");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("message","Hubo un error al crear el vehiculo");
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    //visualizar carros almacenados en la API
    @GetMapping(value = "/cars")
    public ResponseEntity findAllAutomovil(){
        Map response = new HashMap();
        try {
            return new ResponseEntity(automovilServiceImp.allAutomovil(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontraron los vehiculos!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    //visualizar carros almacenados en la DB
    @GetMapping(value = "/carss")
    public ResponseEntity AllAutomovil(){
        Map response = new HashMap();
        try {
            return new ResponseEntity(automovilServiceImp.allAutomovils(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontraron los vehiculos!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    //visualizar carros almacenados en la DB por id
    @GetMapping(value = "/carss/{id}")
    public ResponseEntity AutomovilById(@PathVariable Long id ){
        Map response = new HashMap();
        try {
            return new ResponseEntity(automovilServiceImp.getAutomovils(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el vehiculo!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    //actualizar vehiculo almacenados en la DB por id
    @PutMapping(value = "/carss/{id}")
    public ResponseEntity updateAutomovil(@PathVariable Long id, @RequestBody Automovil automovil){
        Map response = new HashMap();
        Boolean userResp = automovilServiceImp.updateAutomovil(id,automovil);

        if(userResp == true){
            response.put("status", "200");
            response.put("message","Se actualizo el vehiculo");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        response.put("status","400");
        response.put("message","Hubo un error al actualizar el vehiculo");
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }


}
