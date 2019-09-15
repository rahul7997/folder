package com.mypack.restclient.service;

import java.util.List;

import com.mypack.restclient.entity.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int theId);

	void deleteCustomer(int theId);

	List<Customer> searchCustomer(String searchName);

}
