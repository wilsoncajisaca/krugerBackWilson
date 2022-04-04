package com.wilsoncajisaca.vacunacion.pojos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Data for save a employee
 */
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class JwtINP implements Serializable {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";

    public JwtINP(String tokenDeAcceso) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public JwtINP(String tokenDeAcceso, String tipoDeToken) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }
}
