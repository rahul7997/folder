package com.mypack.restapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mypack.restapi.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by id", Customer.class);
		List<Customer> theCustomers = theQuery.getResultList();
		return theCustomers;
	}

	public Customer getCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, customerId);
		return theCustomer;
	}

	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCustomer);
	}

	public void deleteCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Customer where id=:theId");
		theQuery.setParameter("theId", customerId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		if(searchName != null && searchName.trim().length()>0) {
			theQuery = currentSession.createQuery
					("from Customer where lower(firstName) like :theName or "
							+ "lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%"+searchName.toLowerCase()+"%");
		}
		else {
			theQuery = currentSession.createQuery("from Customer order by id", Customer.class);
		}
		List<Customer> theCustomers = theQuery.getResultList();		
		return theCustomers;
	}

}
