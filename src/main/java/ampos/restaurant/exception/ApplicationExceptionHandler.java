package ampos.restaurant.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mysql.jdbc.MysqlErrorNumbers;

/**
 * This class used to handle all exceptions and return specific errors object
 * based on the exception
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
    @ResponseBody
    public RestResourceErrorInfo handleBadRequest( ApplicationException e ) {
        logger.warn( "Bad request ", e );
        return exceptionHandling( e );
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
    public RestResourceErrorInfo handleRequestNotFound( Exception e ) {
        logger.warn( "Request is not found ", e );
        return exceptionHandling( e );

    }

    /**
     * Handle constraint violation exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler( { ConstraintViolationException.class } )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public RestResourceErrorInfo handleConstraintViolationException( ConstraintViolationException e ) {
        logger.warn( "Request is not found ", e );
        return exceptionHandling( e );
    }

    /**
     * Exception handling
     *
     * @param e
     * @param httpStatus
     * @return
     */
    private RestResourceErrorInfo exceptionHandling( Exception e ) {
        if ( e instanceof ConstraintViolationException ) {
            return new RestResourceErrorInfo( ErrorMessage.getErrorMessage( ((ConstraintViolationException) e).getSQLException().getErrorCode() ).getMessage() );
        }
        return new RestResourceErrorInfo( e.getMessage() );
    }

    enum ErrorMessage {
        MESSAGE_FK_CONSTRAINT_KEY( MysqlErrorNumbers.ER_ROW_IS_REFERENCED_2, "Cannot delete or update due to forgein key constraint" ),
        MESSAGE_DUP_ENTRY( MysqlErrorNumbers.ER_DUP_ENTRY, "Duplicate entry name" ),
        UNKNOWN( -1, "Unkown error" );

        private int errorCode;
        private String message;

        private ErrorMessage( int errorCode, String message ) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode( int errorCode ) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage( String message ) {
            this.message = message;
        }

        /**
         * Get error message
         *
         * @param code
         * @return
         */
        public static ErrorMessage getErrorMessage( int code ) {
            for ( ErrorMessage e : values() ) {
                if ( e.errorCode == code ) {
                    return e;
                }
            }
            return UNKNOWN;
        }
    }

}
