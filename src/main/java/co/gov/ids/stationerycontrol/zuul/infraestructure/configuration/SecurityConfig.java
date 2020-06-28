package co.gov.ids.stationerycontrol.zuul.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt.JwtEntryPoint;
import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.jwt.JwtTokenFilter;
import co.gov.ids.stationerycontrol.zuul.infraestructure.configuration.security.UserDetailsServiceImplementation;

/**
 * Class for config spring security.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    /**
     * Function to get encoder password.
     *
     * @return password encoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Function to get filter token.
     *
     * @return token filter.
     */
    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    /**
     * Function to implements authentication manager.
     *
     * @return authentication manager.
     * @throws Exception exception.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Function to implements authentication manager.
     *
     * @return authentication manager.
     * @throws Exception exception.
     */
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Function to configure encoder password.
     *
     * @param auth data of login.
     * @throws Exception exception.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Function to configure spring security, auth path do not need authentication.
     *
     * @param http petition.
     * @throws Exception exception.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
