package com.procesos.automovil.controller;

import com.procesos.automovil.models.User;
import com.procesos.automovil.services.UserService;
import com.procesos.automovil.utils.ApiResponse;
import com.procesos.automovil.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;

    Map data = new HashMap<>();

    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody User user){

        try {
            data.put("token",userService.login(user));
            apiResponse = new ApiResponse(Constants.USER_LOGIN,data);
            return new ResponseEntity(apiResponse,HttpStatus.OK);
        }catch  (Exception e){
            apiResponse = new ApiResponse(e.getMessage(),"");
            return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
