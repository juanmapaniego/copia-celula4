package ar.com.gl.shopCustomerShop.customer.services;

import java.util.List;

import ar.com.gl.shopCustomerShop.customer.dtos.CustomerDTO;

public interface CustomerService {

	// CRUD methods
	public CustomerDTO createCustomer(CustomerDTO customerDTO);

	public CustomerDTO getCustomerById(Long id);

	public List<CustomerDTO> getAllCustomers();

	// public List<CustomerDTO> getAllCustomersByName(String name);

	public CustomerDTO updateCustomer(CustomerDTO customerDTO);

	public void deleteCustomerById(Long customerId);

}
