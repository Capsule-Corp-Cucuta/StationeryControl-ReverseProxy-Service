package co.gov.ids.stationerycontrol.zuul.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import co.gov.ids.stationerycontrol.zuul.persistence.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, String> {}
