package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.gov.ids.stationerycontrol.zuul.infraestructure.persistence.entities.UserType;
import co.gov.ids.stationerycontrol.zuul.infraestructure.persistence.entities.UserEntity;
import co.gov.ids.stationerycontrol.zuul.infraestructure.persistence.repositories.IUserRepository;

/**
 * Class that implements UserDetailService for Authenticate Login.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private IUserRepository repository;

    public UserDetailsServiceImplementation(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Function that get user of database for know if should be authenticated.
     *
     * @param identificationCard user identification.
     * @return user logged.
     * @throws UsernameNotFoundException not found exception.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String identificationCard) throws UsernameNotFoundException {
        UserEntity entity = repository.findByIdentificationCard(identificationCard).get();
        System.err.println(entity.toString());

        List<GrantedAuthority> authorities = new ArrayList();
        if (entity.getUserType().equals(UserType.ADMINISTRATOR)
                || entity.getUserType().equals(UserType.IDS)
                || entity.getUserType().equals(UserType.DANE)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new User(entity.getIdentificationCard(), entity.getPassword(), authorities);
    }
}
