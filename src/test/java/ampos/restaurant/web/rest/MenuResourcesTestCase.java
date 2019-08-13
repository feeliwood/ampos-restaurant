package ampos.restaurant.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

import ampos.restaurant.BaseTestCase;
import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.models.MenuRequest;
import ampos.restaurant.repository.MenuItemRepository;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class MenuResourcesTestCase extends BaseTestCase {

    @Autowired
    private MenuItemRepository menuRepos;

    /**
     * test case for create menu
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void createMenuTestCase() throws IOException, Exception {
        String[] str = { "Italian", "Thai" };
        ArrayList<String> additionalData = Stream.of( str ).collect( Collectors.toCollection( ArrayList::new ) );
        MenuRequest input = new MenuRequest( (long) 2, "Oolong tea 2",
                "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style",
                "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
                new BigDecimal( 300 ), additionalData );
        // compare
        MvcResult result = mockMvc.perform( post( "/menu-items" )
                .contentType( MimeTypeUtils.APPLICATION_JSON_VALUE ).content( asJsonString( input ) ) )
                .andExpect( status().is( 201 ) ).andReturn();
        MenuItemDTO resultData = jsonToObject( result.getResponse().getContentAsString(), MenuItemDTO.class );
        assertEquals( input.getName(), resultData.getName() );
        assertEquals( input.getDescription(), resultData.getDescription() );
        assertEquals( input.getImageUrl(), resultData.getImageUrl() );
        assertEquals( input.getPrice(), resultData.getPrice() );
        assertEquals( input.getDetails(), resultData.getDetails() );
        // check database
        MenuItem menuItems = menuRepos.findById( resultData.getId() ).get();
        assertEquals( input.getName(), menuItems.getName() );
        assertEquals( input.getDescription(), menuItems.getDescription() );
        assertEquals( input.getImageUrl(), menuItems.getImageUrl() );
        assertEquals( input.getPrice().setScale( 2 ), menuItems.getPrice() );
    }

    /**
     * Test case of get menu
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void getMenuTestCase() throws IOException, Exception {
        // expected data
        String[] str = { "Italian", "Thai" };
        ArrayList<String> additionalData = Stream.of( str ).collect( Collectors.toCollection( ArrayList::new ) );
        MenuItemDTO expectedResult = new MenuItemDTO( (long) 1, "Chicken Tom Yum Pizza",
                "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style.",
                "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
                new BigDecimal( 300.00 ).setScale( 2 ), additionalData );
        // compare
        MvcResult result = mockMvc.perform( get( "/menu-items/1" ) ).andExpect( status().is( 200 ) ).andReturn();
        assertEquals( asJsonString( expectedResult ), result.getResponse().getContentAsString() );
    }

    /**
     * test case for update menu
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void updateMenuTestCase() throws IOException, Exception {
        // expected data
        String[] str = { "Italian", "Thai" };
        ArrayList<String> additionalData = Stream.of( str ).collect( Collectors.toCollection( ArrayList::new ) );
        MenuRequest input = new MenuRequest( (long) 1, "Oolong tea edit",
                "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style",
                "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
                new BigDecimal( 300 ), additionalData );
        // compare
        MvcResult result = mockMvc.perform( put( "/menu-items/1" )
                .contentType( MimeTypeUtils.APPLICATION_JSON_VALUE ).content( asJsonString( input ) ) )
                .andExpect( status().is( 200 ) ).andReturn();
        MenuItemDTO resultData = jsonToObject( result.getResponse().getContentAsString(), MenuItemDTO.class );
        assertEquals( input.getName(), resultData.getName() );
        assertEquals( input.getDescription(), resultData.getDescription() );
        assertEquals( input.getImageUrl(), resultData.getImageUrl() );
        assertEquals( input.getPrice(), resultData.getPrice() );
        assertEquals( input.getDetails(), resultData.getDetails() );
        // check database
        MenuItem menuItems = menuRepos.findById( resultData.getId() ).get();
        assertEquals( input.getName(), menuItems.getName() );
        assertEquals( input.getDescription(), menuItems.getDescription() );
        assertEquals( input.getImageUrl(), menuItems.getImageUrl() );
        assertEquals( input.getPrice().setScale( 2 ), menuItems.getPrice() );
    }

    /**
     * Test case for delete menu
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void deleteMenuTestCase() throws IOException, Exception {
        assertTrue( menuRepos.existsById( (long) 2 ) );
        MvcResult result = mockMvc.perform( delete( "/menu-items/2" )
                .contentType( MimeTypeUtils.APPLICATION_JSON_VALUE ) )
                .andExpect( status().is( 200 ) ).andReturn();
        assertEquals( "", result.getResponse().getContentAsString() );
        // check database
        assertFalse( menuRepos.existsById( (long) 2 ) );
    }
}
