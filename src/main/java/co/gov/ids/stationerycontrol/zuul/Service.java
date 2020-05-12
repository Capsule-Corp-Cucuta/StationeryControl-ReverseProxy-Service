package co.gov.ids.stationerycontrol.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * ReverseProxy Service for stationery control of live births and deaths.
 *
 * @author Sergio Rodríguez
 * @version 0.0.1
 * @since 2020
 */
@Controller
@EnableZuulProxy
@SpringBootApplication
public class Service {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Service.class).web(true).run(args);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
