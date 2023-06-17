package com.procesos.automovil.validations;

import com.procesos.automovil.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenValidation {
    @Autowired
    private JWTUtil jwtUtil;
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
