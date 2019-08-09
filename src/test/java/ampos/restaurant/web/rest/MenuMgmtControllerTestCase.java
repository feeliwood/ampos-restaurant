package ampos.restaurant.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import ampos.restaurant.BaseTestCase;
import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * MenuMgmtControllerTestCase is used to test CRUD menu items
 *
 */
public class MenuMgmtControllerTestCase extends BaseTestCase {

    /**
     * Test create menu item
     */
    @Test
    public void testCreateMenuItem() throws Exception {
        // The main idea for this test is to verify rest api calls
        // from client (RestTemplate) to server (end to end tests) 
        String name = "Kimchi";
        String description = "Kimchi";
        BigDecimal price = new BigDecimal( 30 );
        MenuItem menuItem = new MenuItem();
        menuItem.setName( name );
        menuItem.setDescription( description );
        menuItem.setPrice( price );
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add( "file", new ClassPathResource( "a.png" ) );
        body.add( "menuItem", toJSON( menuItem ).toString() );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.MULTIPART_FORM_DATA );
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>( body, headers );
        // the response entity
        // the input object
        post( "menu", MenuItemDTO.class, requestEntity );
        MenuItemDTO responseEntity = get( "/menu/" + 1, MenuItemDTO.class );
        assertNotNull( responseEntity );
        assertEquals( menuItem.getName(), responseEntity.getName() );
        assertEquals( menuItem.getDescription(), responseEntity.getDescription() );
        assertEquals( menuItem.getDescription(), responseEntity.getDescription() );
        assertTrue( menuItem.getPrice() == responseEntity.getPrice() );
    }
}
