package com.mypack.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypack.restapi.dao.CustomerDAO;
import com.mypack.restapi.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	public List<Customer> getCustomers() {
		List<Customer> theCustomers = customerDAO.getCustomers();
		return theCustomers;
	}

	@Transactional
	public Customer getCustomer(int customerId) {
		Customer theCustomer = customerDAO.getCustomer(customerId);
		return theCustomer;
	}

	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
	}

	@Transactional
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		List<Customer> theCustomers = customerDAO.searchCustomers(searchName);
		return theCustomers;
	}

}
