package co.gov.ids.stationerycontrol.zuul.domain.repository;

import java.util.Optional;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;

public interface IUserRepository {

    Optional<AuthenticationRequest> findById(String id);

}
