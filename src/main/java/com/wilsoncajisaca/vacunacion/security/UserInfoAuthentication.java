package com.wilsoncajisaca.vacunacion.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class UserInfoAuthentication {
    //Get user logged
    public static String getUsername (){
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        log.debug("Get username of logged User: {}", userDetails.getUsername());
        return !userDetails.getUsername().trim().isEmpty() ? userDetails.getUsername() : "";
    }
}