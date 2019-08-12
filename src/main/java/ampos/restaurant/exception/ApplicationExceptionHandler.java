package ampos.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * ExceptionHandlerController used to handle all exceptions and return specific
 * errors object based on the exception
 */
@ControllerAdvice
@ResponseBody
public class ApplicationExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger( ApplicationExceptionHandler.class );

    /**
     * Handle bad request
     * 
     * @param e
     * @return
     */
    @ExceptionHandler( ApplicationException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ResponseEntity<String> handleBadRequest( ApplicationException e ) {
        logger.warn( "Bad request ", e );
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    /**
     * Handle internal server errors with run time exception
     * 
     * @param e
     * @return
     */
    @ExceptionHandler( RuntimeException.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public ResponseEntity<String> handleRuntimeException( Exception e ) {
        logger.warn( "Internal server error ", e );
        return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    /**
     * Handle request not found
     * 
     * @param e
     * @return
     */
    @ExceptionHandler( NoHandlerFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ResponseBody
    public ResponseEntity<String> handleThrowable( Exception e ) {
        logger.warn( "Request is not found ", e );
        return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );

    }
}
