package ar.com.gl.shop.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.api.controller.CustomerController;
import ar.com.gl.shop.api.feign.dto.CustomerDTO;
import ar.com.gl.shop.api.feign.proxy.CustomerShopClient;

@RestController
@RequestMapping(path = "/customers")
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerShopClient feign;

    @Override
    @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> findById(@PathVariable(name = "customerId") Long customerId) {

	return new ResponseEntity<>(feign.findById(customerId), HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CustomerDTO>> findAll() {
	Iterable<CustomerDTO> response = feign.findAll();

	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
	return new ResponseEntity<>(feign.create(customerDTO), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(name = "customerId") Long customerId) {

	feign.delete(customerId);

	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> update(@PathVariable("customer") Long customerId,
	    @RequestBody CustomerDTO updateCustomerDTO) {

	return new ResponseEntity<>(feign.update(customerId, updateCustomerDTO), HttpStatus.OK);
    }

}
