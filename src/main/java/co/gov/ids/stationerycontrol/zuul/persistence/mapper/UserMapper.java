package co.gov.ids.stationerycontrol.zuul.persistence.mapper;

import org.mapstruct.*;
import co.gov.ids.stationerycontrol.zuul.persistence.entity.UserEntity;
import co.gov.ids.stationerycontrol.zuul.domain.dto.AuthenticationRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "password", target = "password"),
    })
    AuthenticationRequest toUser(UserEntity entity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "phone", ignore = true),
            @Mapping(target = "department", ignore = true),
            @Mapping(target = "township", ignore = true),
            @Mapping(target = "institution", ignore = true),
    })
    UserEntity toUserEntity(AuthenticationRequest authenticationRequest);

}
