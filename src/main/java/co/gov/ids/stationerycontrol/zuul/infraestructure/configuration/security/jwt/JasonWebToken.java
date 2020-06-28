package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt;

import lombok.Data;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 * Class that represents jwt dto.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
public class JasonWebToken {

    private String token;
    private String identificationCard;
    private Collection<? extends GrantedAuthority> authorities;

    private final String bearer = "Bearer";

    public JasonWebToken(String token, String identificationCard, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.identificationCard = identificationCard;
        this.authorities = authorities;
    }

}
