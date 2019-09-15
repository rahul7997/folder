package com.mypack.restapi.dao;

import java.util.List;

import com.mypack.restapi.entity.Customer;

public interface CustomerDAO {

	List<Customer> getCustomers();

	Customer getCustomer(int customerId);

	void saveCustomer(Customer theCustomer);

	void deleteCustomer(int customerId);

	List<Customer> searchCustomers(String searchName);

}
