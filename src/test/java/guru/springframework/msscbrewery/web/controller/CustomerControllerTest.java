package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@MockBean
	private CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	CustomerDto customerDto;

	@Before
	public void contextLoads() {
		customerDto = CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Alessandro").build();
	}

	@Test
	public void getCustomer() throws Exception {

		when(customerService.getCustomerById(Mockito.any(UUID.class))).thenReturn(customerDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + customerDto.getCustomerId().toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void createCustomer() throws Exception {
		
		when(customerService.createCustomer(customerDto)).thenReturn(customerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/createCustomer")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(customerDto)))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateCustomer() throws JsonProcessingException, Exception {

		CustomerDto updateCustomer = CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Roa").build();
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/" + customerDto.getCustomerId().toString())
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateCustomer))).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}
	
	@Test
	public void deleteCustomer() {
		
	}
	

}
