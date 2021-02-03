package ar.com.gl.shop.api.controller;

import org.springframework.http.ResponseEntity;

import ar.com.gl.shop.api.feign.dto.CustomerDTO;

public interface CustomerController {

    public ResponseEntity<CustomerDTO> findById(Long customerId);

    public ResponseEntity<Iterable<CustomerDTO>> findAll();

    public ResponseEntity<CustomerDTO> create(CustomerDTO customerDTO);

    public ResponseEntity<Void> delete(Long customerId);

    public ResponseEntity<CustomerDTO> update(Long customerId, CustomerDTO updateCustomerDTO);

}
