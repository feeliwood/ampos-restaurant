package ampos.restaurant.web.rest.util;

import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final String APPLICATION_NAME = "AmposRestaurant";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert( String message, String param ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add( "X-AmposRestaurant-alert", message );
        headers.add( "X-AmposRestaurant-params", param );
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert( String entityName, String param ) {
        return createAlert( APPLICATION_NAME + "." + entityName + ".created", param );
    }

    public static HttpHeaders createEntityUpdateAlert( String entityName, String param ) {
        return createAlert( APPLICATION_NAME + "." + entityName + ".updated", param );
    }

    public static HttpHeaders createEntityDeletionAlert( String entityName, String param ) {
        return createAlert( APPLICATION_NAME + "." + entityName + ".deleted", param );
    }
}
