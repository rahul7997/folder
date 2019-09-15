package com.mypack.restapi.service;

import java.util.List;

import com.mypack.restapi.entity.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	Customer getCustomer(int customerId);

	void saveCustomer(Customer theCustomer);

	void deleteCustomer(int customerId);

	List<Customer> searchCustomers(String searchName);

}
