package co.gov.ids.stationerycontrol.zuul.domain.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import co.gov.ids.stationerycontrol.zuul.domain.dto.UserType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class SecurityDetailsService implements UserDetailsService {

    private UserService service;

    public SecurityDetailsService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationRequest authenticationRequest = service.findById(username).get();

        List<GrantedAuthority> authorities = new ArrayList();
        if (authenticationRequest.getUserType().equals(UserType.ADMINISTRATOR)
                || authenticationRequest.getUserType().equals(UserType.IDS)
                || authenticationRequest.getUserType().equals(UserType.DANE)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new User(authenticationRequest.getId(), authenticationRequest.getPassword(), authorities);
    }
}
