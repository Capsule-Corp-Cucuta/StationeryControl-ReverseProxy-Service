package co.gov.ids.stationerycontrol.zuul.domain.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
    private UserType userType;
    private boolean enable;

}