package ampos.restaurant.web.rest.errors;

import java.util.List;
import java.util.concurrent.TimeoutException;

import ampos.restaurant.exception.ApplicationException;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintViolationCreationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ExceptionHandlerController used to handle all exceptions and return specific
 * errors object based on the exception
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerController {
    /**
     * Handle bad request 400
     * 
     * @param e
     * @return
     */
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ResponseEntity<String> handleBadRequest( ApplicationException e ) {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    /**
     * Handle bad request 400
     * 
     * @param e
     * @return
     */
    @ExceptionHandler( ApplicationException.class )
    public ResponseEntity<String> handleCodeFightException( ApplicationException e ) {
        e.printStackTrace();
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    /**
     * Handle bad request MethodArgumentNotValidException
     * 
     * @param e
     * @return
     */

    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<String> handleBadRequestNull( MethodArgumentNotValidException e ) {
        e.printStackTrace();
        StringBuilder errors = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for ( FieldError fieldError : fieldErrors ) {
            String error = fieldError.getDefaultMessage() + "\r\n";
            errors.append( error );
        }
        return new ResponseEntity<>( errors.toString(), HttpStatus.BAD_REQUEST );
    }

    /**
     * Handle internal server errors with timeout exception
     * 
     * @param e
     * @return
     */
    @ExceptionHandler( TimeoutException.class )
    public ResponseEntity<String> handleTimeOutError( Exception e ) {
        e.printStackTrace();
        return new ResponseEntity<>( "Request Timeout", HttpStatus.INTERNAL_SERVER_ERROR );
    }

    /**
     * Handle internal server errors with timeout exception
     * 
     * @param e
     * @return
     */
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler( Exception.class )
    public ResponseEntity<String> handleGeneralError( Exception e ) {
        e.printStackTrace();
        return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
