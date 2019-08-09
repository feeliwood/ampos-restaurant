package ampos.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * RestaurantApplication is used to initialize application
 *
 */
@SpringBootApplication
@ComponentScan( { "ampos.restaurant" } )
public class RestaurantApplication {
    public static void main( String[] args ) {
        SpringApplication.run( RestaurantApplication.class, args );
    }
}
