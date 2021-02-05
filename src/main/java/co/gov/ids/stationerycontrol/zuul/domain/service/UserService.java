package co.gov.ids.stationerycontrol.zuul.domain.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;
import co.gov.ids.stationerycontrol.zuul.domain.repository.IUserRepository;

@Service
public class UserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public Optional<AuthenticationRequest> findById(String username) {
        return repository.findById(username);
    }

}
