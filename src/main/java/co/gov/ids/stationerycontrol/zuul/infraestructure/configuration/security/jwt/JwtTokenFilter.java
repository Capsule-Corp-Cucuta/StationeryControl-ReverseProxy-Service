package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.UserDetailsServiceImplementation;

/**
 * Class that identified token filter.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImplementation userDetailsServiceImplementation;

    /**
     * Function that do filter internal token
     *
     * @param req         request.
     * @param res         response.
     * @param filterChain filter chain.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(req, res);
    }

    /**
     * Function to get token.
     *
     * @param request request.
     * @return token.
     */
    private String getToken(HttpServletRequest request) {
        String authReq = request.getHeader("Authorization");
        if (authReq != null && authReq.startsWith("Bearer "))
            return authReq.replace("Bearer ", "");
        return null;
    }
}
