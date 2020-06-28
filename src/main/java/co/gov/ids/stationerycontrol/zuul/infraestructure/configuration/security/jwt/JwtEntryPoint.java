package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Class that represents jwt entry point.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException,
            ServletException {
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
    }
}
