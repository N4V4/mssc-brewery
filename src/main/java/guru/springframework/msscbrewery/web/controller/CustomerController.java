package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		// TODO Auto-generated constructor stub
		this.customerService = customerService;
	}
	
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
		return new ResponseEntity(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity createCustomer(@RequestBody CustomerDto customerDto) {
		
		CustomerDto savedCustomer = customerService.createCustomer(customerDto);
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Location", savedCustomer.getCustomerId().toString());
		return new ResponseEntity(headers,HttpStatus.CREATED);
	}
	
	@PutMapping({"/{customerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity updateCustomer(@PathVariable UUID customerId, CustomerDto customerDto) {
		
		CustomerDto user = customerService.updateCustomer(customerId,customerDto);
		HttpStatus updated = HttpStatus.ACCEPTED;
		return null;
	}
	
	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity deleteCustomer(@PathVariable UUID customerId) {
		
		customerService.deleteCustomer(customerId);
		return null;
	}
	

}
