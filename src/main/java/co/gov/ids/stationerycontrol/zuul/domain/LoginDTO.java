package co.gov.ids.stationerycontrol.zuul.domain;

import lombok.Data;

/**
 * Class to represent Login object.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
public class LoginDTO {

    private String identificationCard;
    private String password;

}
