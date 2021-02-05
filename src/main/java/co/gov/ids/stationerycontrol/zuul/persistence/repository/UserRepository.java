package co.gov.ids.stationerycontrol.zuul.persistence.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import co.gov.ids.stationerycontrol.zuul.persistence.mapper.UserMapper;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;
import co.gov.ids.stationerycontrol.zuul.domain.repository.IUserRepository;

@Repository
public class UserRepository implements IUserRepository {

    private final UserMapper mapper;
    private final IUserCrudRepository repository;

    public UserRepository(UserMapper mapper, IUserCrudRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<AuthenticationRequest> findById(String username) {
        return Optional.of(mapper.toUser(repository.findOne(username)));
    }
}
