package ampos.restaurant.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "vilafApp";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
	HttpHeaders headers = new HttpHeaders();
	headers.add("X-vilafApp-alert", message);
	headers.add("X-vilafApp-params", param);
	return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
	return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
	return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
	return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
	log.error("Entity processing failed, {}", defaultMessage);
	HttpHeaders headers = new HttpHeaders();
	headers.add("X-vilafApp-error", "error." + errorKey);
	headers.add("X-vilafApp-params", entityName);
	return headers;
    }

    public static HttpHeaders createFileDownload(String fileName) {
	HttpHeaders headers = new HttpHeaders();
	headers.add("X-filename", fileName);
	headers.add("Content-Disposition","attachment; filename=" + fileName);

	return headers;
    }
}
