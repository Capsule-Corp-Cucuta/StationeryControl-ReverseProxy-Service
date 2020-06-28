package co.gov.ids.stationerycontrol.zuul.infraestructure.persistence.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import co.gov.ids.stationerycontrol.zuul.infraestructure.persistence.entities.UserEntity;

/**
 * Interface that declare functions to consult in database.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Function to find user by identification card.
     *
     * @param identificationCard identification of user.
     * @return user.
     */
    Optional<UserEntity> findByIdentificationCard(String identificationCard);

}
