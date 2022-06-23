package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import guru.springframework.msscbrewery.web.model.CustomerDto;

public interface CustomerService {
	
	CustomerDto getCustomerById(UUID customerId);

	CustomerDto createCustomer(CustomerDto customerDto);

	void deleteCustomer(UUID customerId);

	CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto);

}
