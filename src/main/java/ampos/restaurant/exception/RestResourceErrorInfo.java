package ampos.restaurant.exception;

/**
 * This class used to wrap the exceptions to be sent back to the client
 *
 */
public class RestResourceErrorInfo {

    private int httpStatus;
    private String message;
    private String uri;

    public RestResourceErrorInfo() {
    }

    public RestResourceErrorInfo( int httpStatus, String message, String uri ) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.uri = uri;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus( int httpStatus ) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri( String uri ) {
        this.uri = uri;
    }
}
