package co.gov.ids.stationerycontrol.zuul.domain.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String id;
    private String password;
    private UserType userType;

}