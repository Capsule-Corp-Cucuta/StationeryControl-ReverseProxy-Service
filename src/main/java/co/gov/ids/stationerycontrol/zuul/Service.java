package co.gov.ids.stationerycontrol.zuul;

import org.springframework.stereotype.Controller;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
