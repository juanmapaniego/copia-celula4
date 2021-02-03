package ar.com.gl.shopCustomerShop.customer.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shopCustomerShop.customer.model.Customer;

@Repository
public interface CustomerRepository extends Serializable, JpaRepository<Customer, Long> {

}
