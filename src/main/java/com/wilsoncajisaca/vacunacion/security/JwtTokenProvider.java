package com.wilsoncajisaca.vacunacion.security;

import com.wilsoncajisaca.vacunacion.exception.TokenException;
import com.wilsoncajisaca.vacunacion.pojos.errors.ApiError;
import com.wilsoncajisaca.vacunacion.pojos.properties.Constants;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private Constants constants;

    private Integer tokenTime;

    public String generateToken(String username, Integer tokenTime) {
        this.tokenTime = tokenTime;
        return doGenerateToken(username);
    }

    private String doGenerateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, constants.getSIGNING_KEY())
                .setExpiration(new Date(System.currentTimeMillis() + tokenTime * 1000))
                .compact();
    }

    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(constants.getSIGNING_KEY()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * Validates if a token has the correct unmalformed signature and is not expired or unsupported.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(constants.getSIGNING_KEY()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            throw new TokenException(new ApiError("Firma del token invalida"));
        } catch (MalformedJwtException ex) {
            throw new TokenException(new ApiError("Token invalida"));
        } catch (ExpiredJwtException ex) {
            throw new TokenException(new ApiError("El token expiró"));
        } catch (UnsupportedJwtException ex) {
            throw new TokenException(new ApiError("Token no soportado"));
        } catch (IllegalArgumentException ex) {
            throw new TokenException(new ApiError("Token invalido"));
        }
    }

}
