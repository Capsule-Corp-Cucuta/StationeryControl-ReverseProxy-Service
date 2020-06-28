package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt;

import java.util.Date;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;

/**
 * Class that represents jwt provider.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * Function to generate token.
     *
     * @param authentication user authenticated.
     * @return jwt.
     */
    public String generateToken(Authentication authentication) {
        User userDetailsImplementation = (User) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetailsImplementation.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Function to get username by token.
     *
     * @param token token.
     * @return username.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Function to validate token.
     *
     * @param token token.
     * @return boolean to identify if is authorized.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }
}
