package guru.springframework.msscbrewery.web.controller;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.controller.v2.BeerControllerV2;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyleEnum;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerControllerV2.class)
public class BeerControllerTest2 {

	@MockBean
	private BeerServiceV2 beerService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	BeerDtoV2 beerDto;

	@Before
	public void contextLoads() {
		beerDto = BeerDtoV2.builder().
				beerName("Nastro Azzurro").
				beerStyle(BeerStyleEnum.TRAPPISTA).				
				id(UUID.randomUUID()).build();
	}

	@Test
	public void getCustomer() throws Exception {

		when(beerService.getBeerById(any(UUID.class))).thenReturn(beerDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/beer/" + beerDto.getId().toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.beerStyle").exists());
	}
	
	@Test
	public void saveNewBeer() throws Exception {
		
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/beer").contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson)).andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateBeerById() throws Exception {
		
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v2/beer/"+UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson)).andExpect(MockMvcResultMatchers.status().isNoContent());
			
	}
}
