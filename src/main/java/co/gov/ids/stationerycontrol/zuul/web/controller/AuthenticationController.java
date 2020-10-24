package co.gov.ids.stationerycontrol.zuul.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.gov.ids.stationerycontrol.zuul.web.security.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;
import org.springframework.security.authentication.BadCredentialsException;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationResponse;
import co.gov.ids.stationerycontrol.zuul.domain.service.SecurityDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final SecurityDetailsService service;

    public AuthenticationController(JwtUtil jwtUtil, AuthenticationManager manager, SecurityDetailsService service) {
        this.jwtUtil = jwtUtil;
        this.manager = manager;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getId(), request.getPassword()));
            UserDetails userDetails = service.loadUserByUsername(request.getId());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt, userDetails.getAuthorities()), HttpStatus.OK);
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
