package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		// TODO Auto-generated method stub
		return CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Niccolò").build();
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return  CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Berto").build();
	}
	

	@Override
	public void deleteCustomer(UUID customerId) {
		// TODO Auto-generated method stub
		log.debug("Customer deleted " + customerId);
	}


	@Override
	public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
		// TODO Auto-generated method stub
		log.debug("Customer modified ");
		return null;
	}
	
	

}
