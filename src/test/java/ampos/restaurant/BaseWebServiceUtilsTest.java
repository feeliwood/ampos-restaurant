package ampos.restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseWebServiceUtilsTest {

    private static final int HTTP_PORT = 9080;

    private static final String HTTP_HOST = "localhost";

    protected RestTemplate restTemplate = new RestTemplate();

    @Before
    public void before() throws Exception {
    }

    @After
    public void afterWait() throws Exception {
    }

    /**
     * Convert to JSON
     *
     * @param obj
     * @return
     */
    public String toJSON( Object obj ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( obj );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Convert JSON String to object
     *
     * @param jsonContent
     * @param valueType
     * @return
     */
    public <T> T jsonToObject( String jsonContent, Class<T> valueType ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue( jsonContent, valueType );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Load file
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    protected String loadFile( String fileName ) throws IOException {

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        Path path = Paths.get( classLoader.getResource( fileName ).getFile() );
        String content = new String( Files.readAllBytes( path ) );
        return content;

    }

    /**
     * Generic Get method
     *
     * @param url
     * @param returnClass
     * @return
     * @throws Exception
     */
    protected <T> T get( String url, Class<T> returnClass ) throws Exception {
        ResponseEntity<T> response = restTemplate.exchange( getUrl() + url, HttpMethod.GET, null, returnClass );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        return response.getBody();
    }

    /**
     * Generic Get method with paging
     * 
     * @param url
     * @param responseType
     * @return
     */
    protected <T> Page<T> get( String url, ParameterizedTypeReference<PageImpl<T>> responseType ) {
        ResponseEntity<PageImpl<T>> response = restTemplate.exchange( getUrl() + url, HttpMethod.GET, null, responseType );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        return response.getBody();
    }

    /**
     * Generic Get method
     *
     * @param url
     * @param returnClass
     * @param inputObject
     * @return
     * @throws Exception
     */
    protected <T> T get( String url, Class<T> returnClass, Object inputObject ) throws Exception {

        HttpEntity<Object> httpEntity = new HttpEntity<>( inputObject );
        ResponseEntity<T> response = restTemplate.exchange( getUrl() + url, HttpMethod.GET, httpEntity, returnClass );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        return response.getBody();

    }

    /**
     * Generic post method
     *
     * @param url
     * @param returnClass
     * @param inputObject
     * @return
     * @throws Exception
     */
    protected <T> T post( String url, Class<T> returnClass, Object inputObject ) throws Exception {

        ResponseEntity<T> response = restTemplate.postForEntity( getUrl() + url, inputObject, returnClass );
        assertNotNull( response );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        T responseBody = response.getBody();

        return responseBody;

    }

    protected String post( String url, Class<String> returnClass, HttpEntity entity, Object inputObject ) throws Exception {

        ResponseEntity<String> response = restTemplate.exchange( getUrl() + url, HttpMethod.POST, entity, String.class, inputObject );
        assertNotNull( response );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        String responseBody = response.getBody();

        return responseBody;

    }

    /**
     * Generic put method
     * 
     * @param <D>
     *
     * @param url
     * @param returnClass
     * @param inputObject
     * @return
     * @throws Exception
     */
    protected <T, D> T put( String url, Class<T> returnClass, D inputObject ) throws Exception {

        HttpEntity<D> httpEntity = new HttpEntity<D>( inputObject );
        ResponseEntity<T> response = restTemplate.exchange( getUrl() + url, HttpMethod.PUT, httpEntity, returnClass );
        assertNotNull( response );
        assertEquals( HttpStatus.OK, response.getStatusCode() );
        return response.getBody();

    }

    /**
     * Generic delete method
     *
     * @param url
     * @throws Exception
     */
    protected void delete( String url ) throws Exception {
        restTemplate.delete( getUrl() + url );
    }

    /**
     * Get url
     * 
     * @return
     */
    public static String getUrl() {
        return buildURL( "http://", String.valueOf( HTTP_PORT ) );
    }

    /**
     * Build url
     * 
     * @param protocol
     * @param port
     * @return
     */
    private static String buildURL( String protocol, String port ) {
        StringBuilder sb = new StringBuilder();
        sb.append( protocol );
        sb.append( HTTP_HOST );
        sb.append( ":" );
        sb.append( port );
        sb.append( "/" );
        return sb.toString();
    }

    /**
     * Assert its a web service exception
     *
     * @param e
     * @param status
     * @param messages
     */
    protected void assertWebServiceException( Exception e, HttpStatus status, String... messages ) {
        if ( !(e instanceof HttpStatusCodeException) ) {
            fail( "Unexpected errors occured" );
        } else {
            HttpStatusCodeException ce = (HttpStatusCodeException) e;
            assertEquals( status, ce.getStatusCode() );
            if ( messages.length == 1 ) {
                assertTrue( "Exception does not contain [" + messages[0] + "]", ce.getResponseBodyAsString().contains( messages[0] ) );
            } else {
                boolean found = false;
                StringBuilder sb = new StringBuilder();
                for ( String message : messages ) {
                    sb.append( message );
                    sb.append( " " );

                    if ( ce.getResponseBodyAsString().contains( message ) ) {
                        found = true;
                    }
                }

                assertTrue( "Exception does not contain any of the messages [" + sb.toString() + "]", found );
            }
        }
    }

    /**
     * Extract list from JSON
     *
     * @param json
     * @param typeReference
     * @return
     * @throws Exception
     */
    protected <T> Collection<T> extractListFromJson( String json, TypeReference<Collection<T>> typeReference ) throws Exception {
        JSONObject jsonObj = new JSONObject( json );
        if ( jsonObj.isNull( "content" ) )
            return new ArrayList<T>();
        Collection<T> dtos = fromJSON( typeReference, jsonObj.get( "content" ).toString() );
        return dtos;
    }

    /**
     * Convert JSON string to an instance of object
     *
     * @param type
     * @param jsonPacket
     * @return
     * @throws Exception
     */
    public static <T> T fromJSON( final TypeReference<T> type, final String jsonPacket ) throws Exception {
        T data = null;

        try {
            data = new ObjectMapper().readValue( jsonPacket, type );
        } catch ( Exception e ) {
            // Handle the problem
            throw e;
        }
        return data;
    }
}
