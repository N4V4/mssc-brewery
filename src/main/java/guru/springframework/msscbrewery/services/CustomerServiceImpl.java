package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		// TODO Auto-generated method stub
		return CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Niccolò").build();
	}

	@Override
	public CustomerDto createUser(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return  CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Berto").build();
	}

}
