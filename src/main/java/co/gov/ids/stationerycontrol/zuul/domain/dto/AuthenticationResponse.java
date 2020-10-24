package co.gov.ids.stationerycontrol.zuul.domain.dto;

import lombok.Data;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthenticationResponse {

    private String jwt;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> authorities) {
        this.jwt = jwt;
        this.authorities = authorities;
    }

}
