package ampos.restaurant.exception;

/**
 * This class used to wrap the exceptions to be sent back to the client
 *
 */
public class RestResourceErrorInfo {

    private String message;

    public RestResourceErrorInfo() {
    }

    public RestResourceErrorInfo( String message ) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}
