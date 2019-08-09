package ampos.restaurant;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Class implements resource handler for restaurant web. This class will load
 * content web in the resources location.
 * 
 */
@Configuration
public class RestaurantWebConfigureAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/**" ).addResourceLocations( "file:uploads/" );
    }

}
