package com.mypack.restapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.restapi.entity.Customer;
import com.mypack.restapi.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		List<Customer> theCustomers = customerService.getCustomers();
		return theCustomers;
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.getCustomer(customerId);
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer not found with id - "+customerId);
		}
		return theCustomer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		if(customerService.getCustomer(customerId) ==null ) {
			throw new CustomerNotFoundException("Customer not found id -"+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Customer deleted with id - "+customerId;
	}
	
	@GetMapping("/customers/search/{searchName}")
	public List<Customer> theCustomers(@PathVariable String searchName){
		List<Customer> theCustomers = customerService.searchCustomers(searchName);
		return theCustomers;
	}
	
}
