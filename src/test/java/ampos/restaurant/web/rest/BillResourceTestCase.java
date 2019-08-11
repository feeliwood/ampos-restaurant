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
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

import ampos.restaurant.BaseTestCase;
import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.models.MenuRequest;
import ampos.restaurant.repository.BillRepository;
import ampos.restaurant.repository.MenuItemRepository;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BillResourceTestCase extends BaseTestCase {
	@Autowired
	private MenuItemRepository menuRepos;
	@Autowired
	private BillRepository billRepos;

	@Before()
	public void setUp() throws Exception {
		// insert menu
		menuRepos.save(new MenuItem((long) 1, "Chicken Tom Yum Pizza",
				"All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style",
				"https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
				new BigDecimal(300), "Italian,Thai", true));
		// insert bill
		Set<BillItem> billItems = new HashSet<>();
		BillItem billItem = new BillItem((long)1,1,menuRepos.findById((long)1).get(),Instant.parse("2019-11-11T11:11:11Z"));
		billItems.add(billItem);
		Bill bill = new Bill((long)1,billItems);
		billItem.setBill(bill);
		billRepos.save(bill);
		

	}

	/**
	 * test case for create Bill successfully
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void createBillSuccessfully() throws IOException, Exception {
		
		MvcResult result = mockMvc
				.perform(post("/bills").contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().is(201)).andReturn();
		//MenuItemDTO resultData = jsonToObject(result.getResponse().getContentAsString(), MenuItemDTO.class);
		//assertEquals(input.getName(), resultData.getName());

	}
	/**
	 * Test case for delete bill
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void deleteBillSuccessfully() throws IOException, Exception {
		assertTrue(billRepos.existsById((long) 1));
		MvcResult result = mockMvc.perform(delete("/bills/1").contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200)).andReturn();
		assertEquals("", result.getResponse().getContentAsString());
		// check database
		assertFalse(billRepos.existsById((long) 1));

	}
	/**
	 * Test case of get Bill
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void getBillSuccessfully() throws IOException, Exception {
		// expected data
		String[] str = { "Italian", "Thai" };
		ArrayList<String> additionalData = Stream.of(str).collect(Collectors.toCollection(ArrayList::new));
		MenuItemDTO expectedResult = new MenuItemDTO((long) 1, "Chicken Tom Yum Pizza",
				"All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style",
				"https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
				new BigDecimal(300.00).setScale(2), additionalData);
		BillDTO expectedBill = new BillDTO();
		Set<BillItemDTO> billItems = new HashSet<>();
		BillItemDTO billItem = new BillItemDTO();
		billItem.setBillId((long)1);
		billItem.setMenuItem(expectedResult);
		billItem.setOrderedTime(Instant.parse("2019-11-11T11:11:11Z"));
		billItem.setQuantity(1);
		billItem.setId((long)1);
		billItem.setSubTotal(new BigDecimal(300.00).setScale(2));
		billItems.add(billItem);
		expectedBill.setId((long)1);
		expectedBill.setBillItems(billItems);
		expectedBill.setTotal(new BigDecimal(300.00).setScale(2));

		MvcResult result = mockMvc.perform(get("/bills/1")).andExpect(status().is(200)).andReturn();
		assertEquals(asJsonString(expectedBill), result.getResponse().getContentAsString());

	}


	/**
	 * test case for create BillItem successfully
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void createBillItemSuccessfully() throws IOException, Exception {
		String[] str = { "Italian", "Thai" };
		ArrayList<String> additionalData = Stream.of(str).collect(Collectors.toCollection(ArrayList::new));
		MenuRequest input = new MenuRequest((long) 1, "Oolong tea edit",
				"All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style",
				"https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg",
				new BigDecimal(300), additionalData);
		MvcResult result = mockMvc.perform(
				put("/menu-items/1").contentType(MimeTypeUtils.APPLICATION_JSON_VALUE).content(asJsonString(input)))
				.andExpect(status().is(200)).andReturn();
		MenuItemDTO resultData = jsonToObject(result.getResponse().getContentAsString(), MenuItemDTO.class);
		assertEquals(input.getName(), resultData.getName());
	}
	

	

}
