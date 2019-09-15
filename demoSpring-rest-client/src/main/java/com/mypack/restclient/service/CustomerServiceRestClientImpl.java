package com.mypack.restclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mypack.restclient.entity.Customer;

@Service
public class CustomerServiceRestClientImpl implements CustomerService {
	
	private String crmRestUrl;
	private RestTemplate restTemplate;
	
	public CustomerServiceRestClientImpl(RestTemplate theRestTemplate,
											@Value("${crm.rest.url}") String theUrl) {
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
	}

	@Override
	public List<Customer> getCustomers() {
		ResponseEntity<List<Customer>> responseEntity = 
				restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
						new ParameterizedTypeReference<List<Customer>>() {});
		List<Customer> theCustomers = responseEntity.getBody();
		return theCustomers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		int theId = theCustomer.getId();
		if(theId == 0) {
			restTemplate.postForEntity(crmRestUrl, theCustomer, String.class);
		}else {
			restTemplate.put(crmRestUrl, theCustomer);
		}
	}

	@Override
	public Customer getCustomer(int theId) {
		Customer theCustomer = 
				restTemplate.getForObject(crmRestUrl+"/"+theId, Customer.class);
		return theCustomer;
	} 

	@Override
	public void deleteCustomer(int theId) {
		restTemplate.delete(crmRestUrl+"/"+theId);
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		if(searchName != null && searchName != "") {
			ResponseEntity<List<Customer>> responseEntity = 
					restTemplate.exchange(crmRestUrl+"/search/"+searchName, HttpMethod.GET, null,
							new ParameterizedTypeReference<List<Customer>>() {});
		List<Customer> theCustomers = responseEntity.getBody();
		return theCustomers;
		}
		return null;
	}
	
}
