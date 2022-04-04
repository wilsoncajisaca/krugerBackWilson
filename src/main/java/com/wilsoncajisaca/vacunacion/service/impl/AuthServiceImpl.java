package com.wilsoncajisaca.vacunacion.service.impl;

import com.wilsoncajisaca.vacunacion.entities.Auth;
import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.exception.AuthException;
import com.wilsoncajisaca.vacunacion.exception.GeneralException;
import com.wilsoncajisaca.vacunacion.pojos.AuthINP;
import com.wilsoncajisaca.vacunacion.pojos.errors.ApiError;
import com.wilsoncajisaca.vacunacion.repositories.AuthRepository;
import com.wilsoncajisaca.vacunacion.service.AuthService;
import com.wilsoncajisaca.vacunacion.service.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Service implementation for managing {@link com.wilsoncajisaca.vacunacion.entities.Auth}
 */
@Slf4j
@Service
public class AuthServiceImpl extends Tools implements AuthService {

    @Autowired
    private AuthenticationManager authenticateManager;

    @Autowired
    private AuthRepository authRepository;

    @Override
    public Map<String,Object> autenticate(AuthINP auth) throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authenticateUser(auth));
        Map<String,Object> res = new LinkedHashMap<>();
        res.put("response", "Iniciaste Sesion con exito.");
        return res;
    }

    private Authentication authenticateUser(AuthINP auth) throws AuthException {
        try {
            Auth authOpt = authRepository.findByUsername(auth.getUsername())
                                            .orElseThrow(this::generateErrorNotFoundAuth);
			if(!authOpt.getStatus()) {
				throw new AuthException(new ApiError("Tu usuario no se encuentra disponible para transaccionar"));
			}
            return authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        }catch(BadCredentialsException e) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }catch(UsernameNotFoundException user) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }

    }

}
