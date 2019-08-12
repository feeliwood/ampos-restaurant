package ampos.restaurant.exception;

import javax.servlet.http.HttpServletRequest;

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
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler( ApplicationException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public RestResourceErrorInfo handleBadRequest( HttpServletRequest httpServletRequest, ApplicationException e ) {
        logger.warn( "Bad request ", e );
        return exceptionHandling( httpServletRequest, e, HttpStatus.BAD_REQUEST );
    }

    /**
     * Handle request not found
     * 
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler( NoHandlerFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ResponseBody
    public RestResourceErrorInfo handleRequestNotFound( HttpServletRequest httpServletRequest, Exception e ) {
        logger.warn( "Request is not found ", e );
        return exceptionHandling( httpServletRequest, e, HttpStatus.NOT_FOUND );

    }

    /**
     * Handle constraint violation exception
     * 
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler( { ConstraintViolationException.class } )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public RestResourceErrorInfo handleConstraintViolationException( HttpServletRequest httpServletRequest, ConstraintViolationException e ) {
        logger.warn( "Request is not found ", e );
        return exceptionHandling( httpServletRequest, e, HttpStatus.BAD_REQUEST );
    }

    /**
     * Exception handling
     *
     * @param httpServletRequest
     * @param e
     * @param httpStatus
     * @return
     */
    private RestResourceErrorInfo exceptionHandling( HttpServletRequest httpServletRequest, Exception e, HttpStatus httpStatus ) {
        if ( e instanceof ConstraintViolationException ) {
            ConstraintViolationException cve = ((ConstraintViolationException) e);
            int sqlErrorCode = cve.getSQLException().getErrorCode();

            if ( sqlErrorCode == MysqlErrorNumbers.ER_ROW_IS_REFERENCED_2 ) {
                return new RestResourceErrorInfo( httpStatus.value(), ErrorMessage.MESSAGE_FK_CONSTRAINT_KEY.getMessage(), httpServletRequest.getRequestURI() );
            }

            if ( sqlErrorCode == MysqlErrorNumbers.ER_DUP_ENTRY ) {
                return new RestResourceErrorInfo( httpStatus.value(), ErrorMessage.MESSAGE_DUP_ENTRY.getMessage(), httpServletRequest.getRequestURI() );
            }
        }
        return new RestResourceErrorInfo( httpStatus.value(), e.getMessage(), httpServletRequest.getRequestURI() );
    }

    enum ErrorMessage {
        MESSAGE_FK_CONSTRAINT_KEY( "Cannot delete or update due to forgein key constraint" ),
        MESSAGE_DUP_ENTRY( "Duplicate entry name" );
        private String message;

        private ErrorMessage( String message ) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
