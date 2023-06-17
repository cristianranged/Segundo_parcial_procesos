package com.procesos.automovil.controller;

import com.procesos.automovil.models.Automovil;
import com.procesos.automovil.services.AutomovilServiceImp;
import com.procesos.automovil.utils.ApiResponse;
import com.procesos.automovil.utils.Constants;
import com.procesos.automovil.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.procesos.automovil.validations.TokenValidation;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carss")
@CrossOrigin
public class AutomovilController {

    private final  RestTemplate restTemplate;
    @Autowired
    private AutomovilServiceImp automovilServiceImp;
    @Autowired
    private JWTUtil jwtUtil;
    public void AutomovilController(AutomovilServiceImp automovilServiceImp) {

        this.automovilServiceImp = automovilServiceImp;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity fi(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(automovilServiceImp.getAutomovil(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el vehiculo!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    //crear automoviles  en la DB traidos de la api
        @PostMapping("")
        public ResponseEntity saveAutomovil(@RequestBody Automovil automovil,@RequestHeader(value = "Authorization") String token){
            System.out.println(automovil.getUser());
            Long id = automovil.getId();

            if(!validateToken(token)){
                return new ResponseEntity("Token invalid", HttpStatus.UNAUTHORIZED);
            }
            if (automovilServiceImp.validarIdExistente(id)) {
                return ResponseEntity.badRequest().body("El ID ya esta asociado");
            }

            Map response = new HashMap();
            Boolean userResp = automovilServiceImp.createAutomovil(automovil.getId(),automovil.getUser());
            if(userResp == true){

                response.put("status", "201");
                response.put("message","Se creo el vehiculo");
                return new ResponseEntity(response, HttpStatus.CREATED);
            }
            response.put("status","400");
            response.put("message","Hubo un error al crear el vehiculo");
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }


    //visualizar automoviles almacenados en la DB
    @GetMapping("")
    public ResponseEntity allautomovil(@RequestHeader(value = "Authorization") String token){
        Map response = new HashMap();

        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(automovilServiceImp.allAutomovil(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontraron los vehiculos!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }



    //actualizar vehiculo almacenados en la DB por id
    @CrossOrigin(origins = "http://localhost:8088")
    @PutMapping(value = "/{id}")
    public  ResponseEntity updateAutomovil(@PathVariable Long id, @RequestBody Automovil automovil, @RequestHeader(value = "Authorization") String token) {
        Map response = new HashMap();
        Boolean automovilDB = automovilServiceImp.updateAutomovil(id, automovil);
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            if (automovilDB == null) {
                response.put("status", "201");
                response.put("massage", "se actualizo el automovil");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(automovilServiceImp.getAutomovil(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("massage", "se no se pudo actualizar el automovil");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteautomovil(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(automovilServiceImp.delete(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el vehiculo!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    private Boolean validateToken(String token){
        try{
            if(jwtUtil.getKey(token) != null){
                return true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }
}