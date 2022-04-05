package com.wilsoncajisaca.vacunacion.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoAuthentication {
    //Obtiene el username de la persona logeada
    public static String getUsername (){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        System.out.println(String.format("********** %s **********", userDetails.getUsername()));
        return !userDetails.getUsername().trim().isEmpty() ? userDetails.getUsername() : "";
    }
}