package org.home.workspace.repository;

import java.util.List;

import org.home.workspace.model.Customer;

public interface CustomerRepository {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	

}
