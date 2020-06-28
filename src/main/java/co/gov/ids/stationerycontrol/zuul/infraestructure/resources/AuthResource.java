package co.gov.ids.stationerycontrol.zuul.infraestructure.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import co.gov.ids.stationerycontrol.zuul.domain.LoginDTO;
import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt.JwtProvider;
import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt.JasonWebToken;

/**
 * Class that represent auth resource.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Function to authenticate a User.
     *
     * @param dto user.
     * @return jwt.
     */
    @PostMapping
    public ResponseEntity<JasonWebToken> login(@RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getIdentificationCard(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JasonWebToken jwtDTO = new JasonWebToken(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

}
