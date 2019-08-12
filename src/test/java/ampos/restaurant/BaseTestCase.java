package ampos.restaurant;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class BaseTestCase {

    @Autowired
    protected MockMvc mockMvc;

    /**
     * Convert object to json
     */
    public String asJsonString(final Object obj) {
	try {

	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Convert JSON String to object
     *
     * @param jsonContent
     * @param valueType
     * @return
     */
    public <T> T jsonToObject(String jsonContent, Class<T> valueType) {
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(jsonContent, valueType);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
