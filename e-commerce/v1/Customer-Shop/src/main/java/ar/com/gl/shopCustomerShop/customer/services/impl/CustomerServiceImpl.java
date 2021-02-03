package ar.com.gl.shopCustomerShop.customer.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shopCustomerShop.customer.model.Customer;
import ar.com.gl.shopCustomerShop.customer.dtos.CustomerDTO;
import ar.com.gl.shopCustomerShop.customer.repository.CustomerRepository;
import ar.com.gl.shopCustomerShop.customer.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {

		Customer customer = Customer.createFromDTO(customerDTO);
		// customer.setActive("activo");

		try {
			return CustomerDTO.createFromEntity(this.repository.save(customer));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		return CustomerDTO.createFromEntity(this.repository.getOne(id));
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return this.repository.findAll().stream().map(CustomerDTO::createFromEntity).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO updatedCustomerDTO) {

		Customer updatedCustomer = Customer.createFromDTO(updatedCustomerDTO);

		return CustomerDTO.createFromEntity(repository.save(updatedCustomer));
	}

	@Override
	public void deleteCustomerById(Long customerId) {

		repository.deleteById(customerId);
	}

	/*
	 * @Override public List<CustomerDTO> getAllCustomersByName(String name) { //
	 * TODO Auto-generated method stub return null; }
	 */

}
